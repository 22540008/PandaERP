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
            System.out.println(dsPOActive.get(i));
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
            po.setSlChoNhan(rs.getInt("slChoNhan"));
            
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
            gr.setSlNhan(rs.getInt("slNhan"));
            gr.setLuuKho(rs.getInt("luuKho"));
            gr.setLanCuoi(rs.getBoolean("lanCuoi"));
            
            dsGR.add(gr);
        }
        conn.close();
        return dsGR;
    }
               
    // Add new GR từ JAVA về CSDL
    public int addDB(ArrayList<GoodsReceipt> grList) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        String sql1 = """
            INSERT INTO  GoodsReceipt (soCT_line, soCT, nguoiTao, ngayTao, ngaySua, trangThai, itemLine,
                                       soPO_line, slNhan, luuKho, lanCuoi) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);

        for (GoodsReceipt gr : grList){
            String soCT_line = gr.getSoCT() + "_" + gr.getItemLine();
            String soPO_line = gr.getSoPO() + "_" + gr.getPOline();
            stmt.setString(1, soCT_line);
            stmt.setInt(2, gr.getSoCT());
            stmt.setString(3, gr.getUser());
            Date ngayTao = gr.getNgayTao();
            stmt.setDate(4, new java.sql.Date(ngayTao.getTime()));
            Date ngaySua = gr.getNgaySua();
            stmt.setDate(5, new java.sql.Date(ngaySua.getTime()));
            stmt.setInt(6, gr.getTrangThai());
            stmt.setInt(7, gr.getItemLine());
            stmt.setString(8, soPO_line);
            stmt.setInt(9, gr.getSlNhan());
            stmt.setInt(10, gr.getLuuKho());
            stmt.setBoolean(11, gr.isLanCuoi());
            
            rowEffect += stmt.executeUpdate();
        }
        
        String sql2 = "UPDATE PurchaseOrder SET trangThai = 3, slChoNhan = ? WHERE soCT_line = ?;";
        stmt = conn.getConnection().prepareStatement(sql2);
        for (GoodsReceipt gr : grList){
            String soPO_line = gr.getSoPO() + "_" + gr.getPOline();
            stmt.setInt(1, gr.getPo().getSlChoNhan());
            stmt.setString(2, soPO_line);
            
            rowEffect += stmt.executeUpdate();
        }
                      
        conn.close();      
        return rowEffect;
    }
    
//    // Update Array<DataType> từ JAVA về CSDL
//    public int updateDB(ArrayList<GoodsReceipt> grList) throws SQLException{
//        int rowEffect = 0;
//        // đối tượng s kết nối SQL Server
//        SQLConnection conn = new SQLConnection("", "");
//        // Chuỗi truy vấn SQL q cho bảng GoodsReceipts
//        String sql1 = "UPDATE GoodsReceipt SET ngaySua = ?, maNCC = ?, gia = ?, soLuong = ?, vat = ?, giaTong = ? WHERE soCT_line = ?";
//        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);
//        System.out.print("Số lượng data sẽ update trong SQL: " + grList.size());
//        for (GoodsReceipt po : grList){
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
    
    
    //  Update Hiden status cho sample từ JAVA về CSDL
    public int updateDB(GoodsReceipt deleteGR) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng GoodsReceipts
        String soGR_line = deleteGR.getSoCT() + "_" + deleteGR.getItemLine();
        String soPO_line = deleteGR.getSoPO() + "_" + deleteGR.getPOline();
        String sql1 = "UPDATE GoodsReceipt SET trangThai = 1 WHERE soCT_line = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setString(1, soGR_line);
            rowEffect += stmt.executeUpdate();
        }
        
        String sql2 = "UPDATE PurchaseOrder SET slChoNhan = slChoNhan + ?  WHERE soCT_line = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql2)){
            stmt.setInt(1, deleteGR.getSlNhan());
            stmt.setString(2, soPO_line);
            rowEffect += stmt.executeUpdate();
        }
      
        conn.close();    
        return rowEffect;
    }


    
}
