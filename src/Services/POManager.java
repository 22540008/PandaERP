/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Controllers.DangNhapController;
import Controllers.MainController;
import DBConnection.SQLConnection;
import Models.Item;
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
public class POManager {
    ArrayList<PurchaseOrder> dsPO;
    ItemManager itemManager = new ItemManager();

    public POManager() {
    }
    
    public POManager(ArrayList<PurchaseOrder> dsPR) {
        this.dsPO = dsPR;
    }

    public ArrayList<PurchaseOrder> getDsPO() {
        return dsPO;
    }

    public void setDsPO(ArrayList<PurchaseOrder> dsPO) {
        this.dsPO = dsPO;
    }
   
    
    public Object[][] getObjDsPO(){
        int column = PurchaseOrder.columns.length;
        ArrayList<PurchaseOrder> dsPOActive = filterActivePO();
        Object[][] dsObjPurchaseOrder = new Object[dsPOActive.size()][column];
        //System.out.println("Convert to Object[][]");
        for (int i = 0; i < dsPOActive.size(); i++){
            Object[] objPurchaseOrder = dsPOActive.get(i).getObjPO();
            //System.out.println();
            for (int j=0; j < column; j++){
                dsObjPurchaseOrder[i][j] = objPurchaseOrder[j];
                //System.out.print(dsObjPurchaseOrder[i][j] + " ");
            }
        }
        return dsObjPurchaseOrder;
    }
    
    
    // Lấy ds PurchaseOrder đang hoạt động
    public ArrayList<PurchaseOrder> filterActivePO(){
        ArrayList<PurchaseOrder> dsActivePO = new ArrayList();
        for (PurchaseOrder po : dsPO){
            if (po.getTrangThai() != 1){
                dsActivePO.add(po);
            }
        }
        return dsActivePO;
    }
    
    // Phương thức import data từ CSDL
    public ArrayList<PurchaseOrder> loadData_DB() throws SQLException{

        dsPO = new ArrayList(); // Khởi tạo lại dsPurchaseOrder như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "sa");
        // Chuỗi truy vấn SQL q
        String q = """
                    SELECT *
                    FROM PurchaseOrder JOIN PO_PR ON PurchaseOrder.soCT_line = PO_PR.soPO_line
                        JOIN PurchaseRequest ON PO_PR.soPR_line = PurchaseRequest.soCT_line
                        JOIN Item ON PurchaseRequest.maHang = Item.maHang
                        JOIN Vendor ON PurchaseOrder.maNCC = Vendor.maNCC
                    WHERE PurchaseOrder.trangThai NOT IN (1);
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
            po.setSoCT(rs.getInt("soCT"));
            po.setUser(rs.getString("nguoiTao"));
            po.setNgayTao(rs.getDate("ngayTao"));
            po.setNgaySua(rs.getDate("ngaySua"));
            po.setTrangThai(rs.getInt("trangThai"));
            po.setItemLine(rs.getInt("itemLine"));
            po.setPr(pr);
            po.setVendor(vendor);
            po.setGia(rs.getLong("gia"));
            po.setSoLuong(rs.getInt("soLuong"));
            po.setVat(rs.getFloat("vat"));
            po.setGiaItem(rs.getDouble("giaTong"));
            po.setSlChoNhan(rs.getInt("slChoNhan"));
            
            dsPO.add(po);
        }
        conn.close();
        return dsPO;
    }
               
    // Add new PO từ JAVA về CSDL
    public int addDB(ArrayList<PurchaseOrder> poList) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        String sql1 = "INSERT INTO PurchaseOrder (soCT_line, soCT, nguoiTao, ngayTao, ngaySua, trangThai, itemLine,"
                + "maNCC, gia, soLuong, vat, giaTong, slChoNhan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);

        for (PurchaseOrder po : poList){
            String soCT_line = po.getSoCT() + "_" + po.getItemLine();
            stmt.setString(1, soCT_line);
            stmt.setInt(2, po.getSoCT());
            stmt.setString(3, po.getUser());
            Date ngayTao = po.getNgayTao();
            stmt.setDate(4, new java.sql.Date(ngayTao.getTime()));
            Date ngaySua = po.getNgaySua();
            stmt.setDate(5, new java.sql.Date(ngaySua.getTime()));
            stmt.setInt(6, po.getTrangThai());
            stmt.setInt(7, po.getItemLine());
            stmt.setInt(8, po.getMaNCC());
            stmt.setLong(9, po.getGia());
            stmt.setInt(10, po.getSoLuong());
            stmt.setFloat(11, po.getVat());
            stmt.setDouble(12, po.getGiaItem());
            stmt.setInt(13, po.getSlChoNhan());
            
            rowEffect += stmt.executeUpdate();
        }
        
        String sql2 = "INSERT INTO PO_PR (soPO_line, soPR_line) VALUES (?, ?)";
        stmt = conn.getConnection().prepareStatement(sql2);
        for (PurchaseOrder po : poList){
            String soPO_line = po.getSoCT() + "_" + po.getItemLine();
            String soPR_line = po.getSoPR() + "_" + po.getPRline();
            stmt.setString(1, soPO_line);
            stmt.setString(2, soPR_line);
            
            rowEffect += stmt.executeUpdate();
        }
        
        String sql3 = "UPDATE PurchaseRequest SET trangThai = 3 WHERE soCT = ? AND itemLine = ?;";
        stmt = conn.getConnection().prepareStatement(sql3);
        for (PurchaseOrder po : poList){
            stmt.setInt(1, po.getSoPR());
            stmt.setInt(2, po.getPRline());
            
            rowEffect += stmt.executeUpdate();
        }
                      
        conn.close();      
        return rowEffect;
    }
    
    // Update Array<DataType> từ JAVA về CSDL
    public int updateDB(ArrayList<PurchaseOrder> poList) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng PurchaseOrders
        String sql1 = "UPDATE PurchaseOrder SET ngaySua = ?, maNCC = ?, gia = ?, soLuong = ?, vat = ?, giaTong = ?, slChoNhan = ? WHERE soCT_line = ?";
        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);
        System.out.print("Số lượng data sẽ update trong SQL: " + poList.size());
        for (PurchaseOrder po : poList){
            Date ngaySua = po.getNgaySua();
            stmt.setDate(1, new java.sql.Date(ngaySua.getTime()));
            stmt.setInt(2, po.getMaNCC());
            stmt.setLong(3, po.getGia());
            stmt.setInt(4, po.getSoLuong());
            stmt.setFloat(5, po.getVat());
            stmt.setDouble(6, po.getGiaItem());
            stmt.setInt(7, po.getSlChoNhan());
            String soCT_line = po.getSoCT() + "_" + po.getItemLine();
            stmt.setString(8, soCT_line);

            
            rowEffect += stmt.executeUpdate();
        }
        
        conn.close();    
        return rowEffect;
        
    }
    
    public int updateDBClose(ArrayList<PurchaseOrder> listPO) throws SQLException {
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng PurchaseOrders
        String sql1 = "UPDATE PurchaseOrder SET ngaySua = ?, trangThai = ? WHERE soCT_line = ?";
        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);
        System.out.print("Số lượng data sẽ update trong SQL: " + listPO.size());
        for (PurchaseOrder po : listPO){
            System.out.println(po);
            String soCT_line = po.getSoCT() + "_" + po.getItemLine();
            System.out.println(soCT_line);
            Date ngaySua = po.getNgaySua();
            stmt.setDate(1, new java.sql.Date(ngaySua.getTime()));
            stmt.setInt(2, po.getTrangThai());
            stmt.setString(3, soCT_line);

            rowEffect += stmt.executeUpdate();
        }
        
        conn.close();    
        return rowEffect;
    }
    
    
    //  Update Hiden status cho sample từ JAVA về CSDL
    public int updateDB(String soPO_line, String soPR_line) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng PurchaseOrders
        String sql1 = "UPDATE PurchaseOrder SET trangThai = 1 WHERE soCT_line = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setString(1, soPO_line);
            rowEffect += stmt.executeUpdate();
        }
        
        String sql2 = "UPDATE PurchaseRequest SET trangThai = 0 WHERE soCT_line = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql2)){
            stmt.setString(1, soPR_line);
            rowEffect += stmt.executeUpdate();
        }
      
        conn.close();    
        return rowEffect;
    }


    
    
    
    
}
