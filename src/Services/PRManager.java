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
        int column = PurchaseRequest.getColumns().length;
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
            if (pr.getTrangThai() == 0){
                dsActivePR.add(pr);
            }
        }
        return dsActivePR;
    }
    
    // Phương thức import data từ CSDL
    public ArrayList<PurchaseRequest> loadData_DB() throws SQLException{
//        private static final String[] columns = {"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Mã hàng", "Tên hàng", "ĐVT", 
//        "Số lượng", "Giá Est", "Tổng giá"};
        dsPR = new ArrayList(); // Khởi tạo lại dsPurchaseRequest như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "159753");
        // Chuỗi truy vấn SQL q
        String q = """
                   SELECT *
                   FROM PurchaseRequest JOIN Item ON PurchaseRequest.maHang = Item.maHang
                   """;
        PreparedStatement stmt = conn.getConnection().prepareStatement(q);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
//            Item item = itemManager.loadData_DB(rs.getInt("maHang"));
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
                rs.getInt("soLuong")          
            );
            
            pr.setTrangThaiStr();
            
            //System.out.println(pr);
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
        String sql = "INSERT INTO PurchaseRequest (soCT_line, soCT, NguoiTao, ngayTao, ngaySua, trangThai, itemLine, maHang, soLuong, tongGia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            stmt.setInt(9, pr.getSoLuong());
            stmt.setLong(10, pr.getGiaItem());
            
            rowEffect += stmt.executeUpdate();
        }
        
        
//        // Chuỗi truy vấn SQL q cho bảng PurchaseRequests
//        String sql = "INSERT INTO PurchaseRequest (maNCC, tenNCC, diaChi, mST, trangThai) VALUES (?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
//            stmt.setInt(1, PR.getMaNCC());
//            stmt.setString(2, PR.getTenNCC());
//            stmt.setString(3, PR.getDiaChi());
//            stmt.setString(4, PR.getmST());
//            stmt.setInt(5, PR.getTrangThai());
//
//            rowEffect = stmt.executeUpdate();
//        }

        conn.close();      
        return rowEffect;
    }
    
//    // Update 1 sample từ JAVA về CSDL
//    public int updateDB(PurchaseRequest PR) throws SQLException{
//        int rowEffect = 0;
//        // đối tượng s kết nối SQL Server
//        SQLConnection conn = new SQLConnection("", "");
//        // Chuỗi truy vấn SQL q cho bảng PurchaseRequests
//        String sql1 = "UPDATE PurchaseRequest SET tenNCC = ?, diaChi = ?, mST = ?, trangThai = ? WHERE maNCC = ?";
//        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
//            stmt.setString(1, PR.getTenNCC());
//            stmt.setString(2, PR.getDiaChi());
//            stmt.setString(3, PR.getmST());
//            stmt.setInt(4, PR.getTrangThai());
//            stmt.setInt(5, PR.getMaNCC());
//
//            rowEffect = stmt.executeUpdate();
//        }
//
//        conn.close();    
//        return rowEffect;
//    }
    
    //  Update Hiden status cho sample từ JAVA về CSDL
    public int updateDB(int deleteMaNCC) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng PurchaseRequests
        String sql1 = "UPDATE PurchaseRequest SET trangThai = 1 WHERE maNCC = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setInt(1, deleteMaNCC);
          
            rowEffect = stmt.executeUpdate();
        }

        conn.close();    
        return rowEffect;
    }
    
    
    
    
}
