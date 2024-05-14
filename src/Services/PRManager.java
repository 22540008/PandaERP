/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Controllers.DangNhapController;
import Controllers.MainController;
import DBConnection.SQLConnection;
import Models.Item;
import Models.PurchaseRequest;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 *
 * @author Ricardo
 */
public class PRManager {
    ArrayList<PurchaseRequest> dsPR;
    ItemManager itemManager = new ItemManager();

    public PRManager() {
    }
    
    public PRManager(ArrayList<PurchaseRequest> dsPR) {
        this.dsPR = dsPR;
    }

    public ArrayList<PurchaseRequest> getDsPR() {
        return dsPR;
    }

    public void setDsPR(ArrayList<PurchaseRequest> dsPR) {
        this.dsPR = dsPR;
    }
   
    
    public Object[][] getObjDsPR(){
        int column = PurchaseRequest.columns.length;
        ArrayList<PurchaseRequest> dsPRActive = filterActivePR();
        Object[][] dsObjPurchaseRequest = new Object[dsPRActive.size()][column];
        //System.out.println("Convert to Object[][]");
        for (int i = 0; i < dsPRActive.size(); i++){
            Object[] objPurchaseRequest = dsPRActive.get(i).getObjPR();
            for (int j=0; j < column; j++){
                dsObjPurchaseRequest[i][j] = objPurchaseRequest[j];
                //System.out.print(dsObjPurchaseRequest[i][j]);
            }
        }
        return dsObjPurchaseRequest;
    }
    
    
    // Lấy ds PurchaseRequest đang hoạt động
    public ArrayList<PurchaseRequest> filterActivePR(){
        ArrayList<PurchaseRequest> dsActivePR = new ArrayList();
        for (PurchaseRequest pr : dsPR){
            if (pr.getTrangThai() != 1){
                dsActivePR.add(pr);
            }
        }
        return dsActivePR;
    }
    
    // Phương thức import data từ CSDL
    public ArrayList<PurchaseRequest> loadData_DB() throws SQLException{
        dsPR = new ArrayList(); // Khởi tạo lại dsPurchaseRequest như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "sa");
        // Chuỗi truy vấn SQL q
        String q = """
                   SELECT *
                   FROM PurchaseRequest JOIN Item ON PurchaseRequest.maHang = Item.maHang
                   """;
        PreparedStatement stmt = conn.getConnection().prepareStatement(q);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Item item = new Item();
            item.setMaHang(rs.getInt("maHang")); // đúng cho truy vấn cột maHang đầu tiên
            item.setTenHang(rs.getString("tenHang"));
            item.setDvt(rs.getString("dvt"));
            item.setDonGia(rs.getLong("donGia"));
            
            PurchaseRequest pr = new PurchaseRequest(
                rs.getInt("soCT"),
                rs.getString("nguoiTao"),
                rs.getDate("ngayTao"),
                rs.getDate("ngaySua"),
                rs.getInt("trangThai"),
                rs.getInt("itemLine"),
                item,
                rs.getLong("giaEst"),
                rs.getInt("soLuong")         
            );
            dsPR.add(pr);
        }
        conn.close();
        return dsPR;
    }
    
    public ArrayList<PurchaseRequest> loadData_DB(int trangThai) throws SQLException {
        dsPR = new ArrayList(); // Khởi tạo lại dsPurchaseRequest như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "sa");
        // Chuỗi truy vấn SQL q
        String q = """
                   SELECT *
                   FROM PurchaseRequest JOIN Item ON PurchaseRequest.maHang = Item.maHang WHERE PurchaseRequest.trangThai = ?
                   """;
        PreparedStatement stmt = conn.getConnection().prepareStatement(q);
        stmt.setInt(1, trangThai);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Item item = new Item();
            item.setMaHang(rs.getInt("maHang")); // đúng cho truy vấn cột maHang đầu tiên
            item.setTenHang(rs.getString("tenHang"));
            item.setDvt(rs.getString("dvt"));
            item.setDonGia(rs.getLong("donGia"));
            
            PurchaseRequest pr = new PurchaseRequest(
                rs.getInt("soCT"),
                rs.getString("nguoiTao"),
                rs.getDate("ngayTao"),
                rs.getDate("ngaySua"),
                rs.getInt("trangThai"),
                rs.getInt("itemLine"),
                item,
                rs.getLong("giaEst"),
                rs.getInt("soLuong")         
            );
            dsPR.add(pr);
        }
        conn.close();
        return dsPR;
        
    }
               
    // Add 1 sample từ JAVA về CSDL
    public int addDB(ArrayList<PurchaseRequest> prList) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        String sql = "INSERT INTO PurchaseRequest (soCT_line, soCT, NguoiTao, ngayTao, ngaySua, trangThai, itemLine, maHang, giaEst, soLuong, tongGia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.getConnection().prepareStatement(sql);

        for (PurchaseRequest pr : prList){
            String soCT_line = pr.getSoCT() + "_" + pr.getItemLine();
            stmt.setString(1, soCT_line);
            stmt.setInt(2, pr.getSoCT());
            stmt.setString(3, pr.getUser());
            //stmt.setDate(4, (Date) pr.getNgayTao());
            Date ngayTao = pr.getNgayTao();
            stmt.setDate(4, new java.sql.Date(ngayTao.getTime()));
            //stmt.setDate(5, (Date) pr.getNgaySua());
            Date ngaySua = pr.getNgaySua();
            stmt.setDate(5, new java.sql.Date(ngaySua.getTime()));
            stmt.setInt(6, pr.getTrangThai());
            stmt.setInt(7, pr.getItemLine());
            stmt.setInt(8, pr.getItem().getMaHang());
            stmt.setLong(9, pr.getDonGia());
            stmt.setInt(10, pr.getSoLuong());
            stmt.setDouble(11, pr.getGiaItem());
            
            rowEffect += stmt.executeUpdate();
        }
        conn.close();      
        return rowEffect;
    }
    
    // Update Array<DataType> từ JAVA về CSDL
    public int updateDB(ArrayList<PurchaseRequest> prList) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng PurchaseRequests
        String sql1 = "UPDATE PurchaseRequest SET ngaySua = ?, trangThai = ?, giaESt = ?, soLuong = ? WHERE soCT = ? AND itemLine = ?";
//        String sql1 = "UPDATE PurchaseRequest SET trangThai = 0, soLuong = 1 WHERE soCT = 1240009 AND itemLine = 1";
//        String sql1 = "UPDATE PurchaseRequest SET soLuong = ? WHERE soCT = ?";
        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);
        System.out.print("Số lượng data sẽ update trong SQL: " + prList.size());
        for (PurchaseRequest pr : prList){
            System.out.print(pr);
            Date ngaySua = pr.getNgaySua();
            stmt.setDate(1, new java.sql.Date(ngaySua.getTime()));
            stmt.setInt(2, pr.getTrangThai());
            stmt.setLong(3, pr.getDonGia());
            stmt.setInt(4, pr.getSoLuong());
            stmt.setInt(5, pr.getSoCT());
            stmt.setInt(6, pr.getItemLine());

            rowEffect += stmt.executeUpdate();
        }
        
        conn.close();    
        return rowEffect;
        
    }
    
    //  Update Hiden status cho sample từ JAVA về CSDL
    public int updateDB(int deleteSoCT, int deleteItemLine) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng PurchaseRequests
        String sql1 = "UPDATE PurchaseRequest SET trangThai = 1 WHERE soCT = ? AND itemLine = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setInt(1, deleteSoCT);
            stmt.setInt(2, deleteItemLine);
          
            rowEffect = stmt.executeUpdate();
        }

        conn.close();    
        return rowEffect;
    }

    public int updateDBClose(ArrayList<PurchaseRequest> prList) throws SQLException {
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng PurchaseRequests
        String sql1 = "UPDATE PurchaseRequest SET ngaySua = ?, trangThai = ? WHERE soCT = ? AND itemLine = ?";
        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);
        System.out.print("Số lượng data sẽ update trong SQL: " + prList.size());
        for (PurchaseRequest pr : prList){
            System.out.print(pr);
            Date ngaySua = pr.getNgaySua();
            stmt.setDate(1, new java.sql.Date(ngaySua.getTime()));
            stmt.setInt(2, pr.getTrangThai());
            stmt.setInt(3, pr.getSoCT());
            stmt.setInt(4, pr.getItemLine());

            rowEffect += stmt.executeUpdate();
        }
        
        conn.close();    
        return rowEffect;
    }

    
}
