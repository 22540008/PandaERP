/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Controllers.DangNhapController;
import Controllers.MainController;
import DBConnection.SQLConnection;
import Models.Item;
import Models.GoodsReceipt;
import Models.PurchaseOrder;
import Models.PurchaseRequest;
import Models.Vendor;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 *
 * @author Ricardo
 */
public class GRManager {
    ArrayList<GoodsReceipt> dsGR;
    ItemManager itemManager = new ItemManager();

    public GRManager() {
    }
    
    public GRManager(ArrayList<GoodsReceipt> dsGR) {
        this.dsGR = dsGR;
    }

    public ArrayList<GoodsReceipt> getDsGR() {
        return dsGR;
    }

    public void setDsGR(ArrayList<GoodsReceipt> dsGR) {
        this.dsGR = dsGR;
    }
   
    
    public Object[][] getObjDsGR(){
        int column = GoodsReceipt.columns.length;
        ArrayList<GoodsReceipt> dsPOActive = filterActivePO();
        Object[][] dsObjGoodsReceipt = new Object[dsPOActive.size()][column];
        //System.out.println("Convert to Object[][]");
        for (int i = 0; i < dsPOActive.size(); i++){
            Object[] objGoodsReceipt = dsPOActive.get(i).getObjPO();
            //System.out.println();
            for (int j=0; j < column; j++){
                dsObjGoodsReceipt[i][j] = objGoodsReceipt[j];
                //System.out.print(dsObjGoodsReceipt[i][j] + " ");
            }
        }
        return dsObjGoodsReceipt;
    }
    
    
    // Lấy ds GoodsReceipt đang hoạt động
    public ArrayList<GoodsReceipt> filterActivePO(){
        ArrayList<GoodsReceipt> dsActivePO = new ArrayList();
        for (GoodsReceipt po : dsGR){
            if (po.getTrangThai() != 1){
                dsActivePO.add(po);
            }
        }
        return dsActivePO;
    }
    
    // Phương thức import data từ CSDL
    public ArrayList<GoodsReceipt> loadData_DB() throws SQLException{

        dsGR = new ArrayList(); // Khởi tạo lại dsGoodsReceipt như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "sa");
        // Chuỗi truy vấn SQL q
        String q = """
                    SELECT *
                    FROM GoodsReceipt JOIN PurchaseOrder ON GoodsReceipt.soPO_line = PurchaseOrder.soCT_line
                    	JOIN PO_PR ON PurchaseOrder.soCT_line = PO_PR.soPO_line
                    	JOIN PurchaseRequest ON PO_PR.soPR_line = PurchaseRequest.soCT_line
                    	JOIN Item ON PurchaseRequest.maHang = Item.maHang
                    	JOIN Vendor ON PurchaseOrder.maNCC = Vendor.maNCC
                    WHERE GoodsReceipt.trangThai NOT IN (1);
                   """;
        PreparedStatement stmt = conn.getConnection().prepareStatement(q);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
//            Item item = itemManager.loadData_DB(rs.getInt("maHang"));
            Item item = new Item();
            item.setMaHang(rs.getInt("maHang")); // đúng cho truy vấn cột maHang đầu tiên
            item.setTenHang(rs.getString("tenHang"));
            item.setDvt(rs.getString("dvt"));
            
            Vendor vendor = new Vendor();
            vendor.setMaNCC(rs.getInt("maNCC"));
            vendor.setTenNCC(rs.getString("tenNCC"));
            
            PurchaseRequest pr = new PurchaseRequest();
            String[] soPR_line = rs.getString("soPR_line").split("_");
            pr.setSoCT(Integer.parseInt(soPR_line[0]));
            pr.setItemLine(Integer.parseInt(soPR_line[1]));
            pr.setItem(item);
            
            PurchaseOrder po = new PurchaseOrder();
            String[] soPO_line = rs.getString("soPO_line").split("_");
            po.setSoCT(Integer.parseInt(soPO_line[0]));
            po.setItemLine(Integer.parseInt(soPO_line[1]));
            
            GoodsReceipt gr = new GoodsReceipt();
            gr.setSoCT(rs.getInt("soCT"));
            gr.setUser(rs.getString("nguoiTao"));
            gr.setNgayTao(rs.getDate("ngayTao"));
            gr.setNgaySua(rs.getDate("ngaySua"));
            gr.setTrangThai(rs.getInt("trangThai"));
            gr.setItemLine(rs.getInt("itemLine"));
            gr.setPo(po);
            gr.setPr(pr);
            gr.setVendor(vendor);
            gr.setSoLuong(rs.getInt("soLuong"));
            
            dsGR.add(gr);
        }
        conn.close();
        return dsGR;
    }
               
//    // Add new PO từ JAVA về CSDL
//    public int addDB(ArrayList<GoodsReceipt> poList) throws SQLException{
//        int rowEffect = 0;
//        // đối tượng s kết nối SQL Server
//        SQLConnection conn = new SQLConnection("", "");
//        String sql1 = "INSERT INTO GoodsReceipt (soCT_line, soCT, nguoiTao, ngayTao, ngaySua, trangThai, itemLine,"
//                + "maNCC, gia, soLuong, vat, giaTong) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);
//
//        for (GoodsReceipt po : poList){
//            String soCT_line = po.getSoCT() + "_" + po.getItemLine();
//            stmt.setString(1, soCT_line);
//            stmt.setInt(2, po.getSoCT());
//            stmt.setString(3, po.getUser());
//            Date ngayTao = po.getNgayTao();
//            stmt.setDate(4, new java.sql.Date(ngayTao.getTime()));
//            Date ngaySua = po.getNgaySua();
//            stmt.setDate(5, new java.sql.Date(ngaySua.getTime()));
//            stmt.setInt(6, po.getTrangThai());
//            stmt.setInt(7, po.getItemLine());
//            stmt.setInt(8, po.getMaNCC());
//            stmt.setLong(9, po.getGia());
//            stmt.setInt(10, po.getSoLuong());
//            stmt.setFloat(11, po.getVat());
//            stmt.setDouble(12, po.getGiaItem());
//            
//            rowEffect += stmt.executeUpdate();
//        }
//        
//        String sql2 = "INSERT INTO PO_PR (soPO_line, soPR_line) VALUES (?, ?)";
//        stmt = conn.getConnection().prepareStatement(sql2);
//        for (GoodsReceipt po : poList){
//            String soPO_line = po.getSoCT() + "_" + po.getItemLine();
//            String soPR_line = po.getSoPR() + "_" + po.getPRline();
//            stmt.setString(1, soPO_line);
//            stmt.setString(2, soPR_line);
//            
//            rowEffect += stmt.executeUpdate();
//        }
//        
//        String sql3 = "UPDATE PurchaseRequest SET trangThai = 3 WHERE soCT = ? AND itemLine = ?;";
//        stmt = conn.getConnection().prepareStatement(sql3);
//        for (GoodsReceipt po : poList){
//            stmt.setInt(1, po.getSoPR());
//            stmt.setInt(2, po.getPRline());
//            
//            rowEffect += stmt.executeUpdate();
//        }
//                      
//        conn.close();      
//        return rowEffect;
//    }
//    
//    // Update Array<DataType> từ JAVA về CSDL
//    public int updateDB(ArrayList<GoodsReceipt> poList) throws SQLException{
//        int rowEffect = 0;
//        // đối tượng s kết nối SQL Server
//        SQLConnection conn = new SQLConnection("", "");
//        // Chuỗi truy vấn SQL q cho bảng GoodsReceipts
//        String sql1 = "UPDATE GoodsReceipt SET ngaySua = ?, maNCC = ?, gia = ?, soLuong = ?, vat = ?, giaTong = ? WHERE soCT_line = ?";
//        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);
//        System.out.print("Số lượng data sẽ update trong SQL: " + poList.size());
//        for (GoodsReceipt po : poList){
//            Date ngaySua = po.getNgaySua();
//            stmt.setDate(1, new java.sql.Date(ngaySua.getTime()));
//            stmt.setInt(2, po.getMaNCC());
//            stmt.setLong(3, po.getGia());
//            stmt.setInt(4, po.getSoLuong());
//            stmt.setFloat(5, po.getVat());
//            stmt.setDouble(6, po.getGiaItem());
//            String soCT_line = po.getSoCT() + "_" + po.getItemLine();
//            stmt.setString(7, soCT_line);
//
//            
//            rowEffect += stmt.executeUpdate();
//        }
//        
//        conn.close();    
//        return rowEffect;
//        
//    }
//    
//    public int updateDBClose(ArrayList<GoodsReceipt> listPO) throws SQLException {
//        int rowEffect = 0;
//        // đối tượng s kết nối SQL Server
//        SQLConnection conn = new SQLConnection("", "");
//        // Chuỗi truy vấn SQL q cho bảng GoodsReceipts
//        String sql1 = "UPDATE GoodsReceipt SET ngaySua = ?, trangThai = ? WHERE soCT_line = ?";
//        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);
//        System.out.print("Số lượng data sẽ update trong SQL: " + listPO.size());
//        for (GoodsReceipt po : listPO){
//            System.out.println(po);
//            String soCT_line = po.getSoCT() + "_" + po.getItemLine();
//            System.out.println(soCT_line);
//            Date ngaySua = po.getNgaySua();
//            stmt.setDate(1, new java.sql.Date(ngaySua.getTime()));
//            stmt.setInt(2, po.getTrangThai());
//            stmt.setString(3, soCT_line);
//
//            rowEffect += stmt.executeUpdate();
//        }
//        
//        conn.close();    
//        return rowEffect;
//    }
//    
//    
//    //  Update Hiden status cho sample từ JAVA về CSDL
//    public int updateDB(String soPO_line, String soPR_line) throws SQLException{
//        int rowEffect = 0;
//        // đối tượng s kết nối SQL Server
//        SQLConnection conn = new SQLConnection("", "");
//        // Chuỗi truy vấn SQL q cho bảng GoodsReceipts
//        String sql1 = "UPDATE GoodsReceipt SET trangThai = 1 WHERE soCT_line = ?";
//        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
//            stmt.setString(1, soPO_line);
//            rowEffect += stmt.executeUpdate();
//        }
//        
//        String sql2 = "UPDATE PurchaseRequest SET trangThai = 0 WHERE soCT_line = ?";
//        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql2)){
//            stmt.setString(1, soPR_line);
//            rowEffect += stmt.executeUpdate();
//        }
//      
//        conn.close();    
//        return rowEffect;
//    }


    
    
    
    
}
