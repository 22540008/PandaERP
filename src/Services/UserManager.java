/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DBConnection.SQLConnection;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class UserManager {
    private ArrayList<User> dsUser;

    public UserManager() {
        this.dsUser = new ArrayList();
    }
    
//    public User getUser() {
//        return dsUser.get(0);
//    }
    
    public ArrayList<User> getDSUser(){
        return this.dsUser;
    }
    
    public Object[][] getObjDsUser() throws SQLException {
        ArrayList<User> dsUserActive = filterActiveUser();
        int size = dsUserActive.size();
        Object[][] dsObjUser = new Object[size][User.getColumns().length];
        for (int i = 0; i < size; i++) {
            Object[] user = dsUserActive.get(i).getObjectUser();
            for (int j = 0; j < user.length; j++) {
                dsObjUser[i][j] = user[j];
            }
        }
        return dsObjUser;
    }
    
    public String[] getColumnns() {
        return User.getColumns();
    }
    
    public String[] getSystemRoles(User user) {
        return user.getSystemRoles();
    }
      
    // Lọc lấy activeData từ dsUser
    public ArrayList<User> filterActiveUser(){
        ArrayList<User> dsUserActive = new ArrayList();
        for (User user : dsUser) {
            if (user.getTrangThai() == 0) {
                dsUserActive.add(user);
            }
        }
        return dsUserActive;
    }
    
    // Phương thức import data từ CSDL
    public ArrayList<User> loadData_DB() throws SQLException{
        // ArrayList<User> dsUser = new ArrayList<>();
        dsUser = new ArrayList(); // Khởi tạo lại dsUser như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "sa");
        // Chuỗi truy vấn SQL q
        String q = """
            SELECT maNV, Users.tenTK, matKhau, systemRoles, trangThai, ho, ten, chucVu, phongBan, diaChi, soDT FROM Users JOIN NhanVien ON Users.tenTK = NhanVien.tenTK
                   """;
        // thực hiện truy vấn bằng cách gọi phương thức query của s và lưu kết quả vào rs     
        ResultSet rs = conn.query(q);
        // Lặp qua rs vào tạo các đối tượng NhanVien
        while (rs.next()) {
            User user = new User(
                    rs.getInt("maNV"),
                    rs.getString("tenTK"),
                    rs.getString("matKhau"),
                    rs.getString("ho"),
                    rs.getString("ten"),
                    rs.getString("chucVu"),
                    rs.getString("phongBan"),
                    rs.getString("diaChi"),
                    rs.getString("soDT"),
                    rs.getInt("trangThai"));
                    
            user.setSystemRole(rs.getString("systemRoles"));
            dsUser.add(user);
        }
        conn.close();
        
        return dsUser;
    }
               
    // Export dữ liệu từ JAVA về CSDL
    public void saveData_DB() throws SQLException{
        ArrayList<User> dsUser = new ArrayList<>();
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q
        for (User user : dsUser) {
            // Chuỗi truy vấn SQL q cho bảng Users
            String q1 = "INSERT INTO Users (tenTK, matKhau, systemRoles, trangThai) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.getConnection().prepareStatement(q1)) {
                stmt.setString(1, user.getTenTK());
                stmt.setString(2, user.getMatKhau());
                stmt.setString(3, user.getSystemRole());
                stmt.setInt(4, user.getTrangThai());

                stmt.executeUpdate();
            }

            // Chuỗi truy vấn SQL q cho bảng NhanVien
            String q2 = "INSERT INTO NhanVien (maNV, tenTK, ho, ten, chucVu, phongBan, diaChi, soDT) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.getConnection().prepareStatement(q2)) {
                stmt.setInt(1, user.getMaNV());
                stmt.setString(2, user.getTenTK());
                stmt.setString(3, user.getHo());
                stmt.setString(4, user.getTen());
                stmt.setString(5, user.getChucVu());
                stmt.setString(6, user.getPhongBan());
                stmt.setString(7, user.getDiaChi());
                stmt.setString(8, user.getSoDT());

                stmt.executeUpdate();
            }
        }
        conn.close();
    }
    
    // Add 1 sample từ JAVA về CSDL
    public int addDB(User user) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng Users
        String sql1 = "INSERT INTO Users (tenTK, matKhau, systemRoles, trangThai) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setString(1, user.getTenTK());
            stmt.setString(2, user.getMatKhau());
            stmt.setString(3, user.getSystemRole());
            stmt.setInt(4, user.getTrangThai());

            rowEffect += stmt.executeUpdate();
        }

        // Chuỗi truy vấn SQL q cho bảng NhanVien
        String sql2 = "INSERT INTO NhanVien (maNV, tenTK, ho, ten, chucVu, phongBan, diaChi, soDT) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql2)) {
            stmt.setInt(1, user.getMaNV());
            stmt.setString(2, user.getTenTK());
            stmt.setString(3, user.getHo());
            stmt.setString(4, user.getTen());
            stmt.setString(5, user.getChucVu());
            stmt.setString(6, user.getPhongBan());
            stmt.setString(7, user.getDiaChi());
            stmt.setString(8, user.getSoDT());

            rowEffect += stmt.executeUpdate();
        }

        conn.close();      
        return rowEffect;
    }
    
    // Update 1 sample từ JAVA về CSDL
    public int updateDB(User user) throws SQLException{
        //System.out.println("Hàm update user" + user);
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng Users
        String sql1 = "UPDATE Users SET matKhau = ?, systemRoles = ?, trangThai = ? WHERE tenTK = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setString(1, user.getMatKhau());
            stmt.setString(2, user.getSystemRole());
            stmt.setInt(3, user.getTrangThai());
            stmt.setString(4, user.getTenTK());

            rowEffect += stmt.executeUpdate();
        }

        // Chuỗi truy vấn SQL q cho bảng NhanVien
        String sql2 = "UPDATE NhanVien SET ho = ?, ten = ?, chucVu = ?, phongBan = ?, diaChi = ?, soDT = ? WHERE tenTK = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql2)) {
            stmt.setString(1, user.getHo());
            stmt.setString(2, user.getTen());
            stmt.setString(3, user.getChucVu());
            stmt.setString(4, user.getPhongBan());
            stmt.setString(5, user.getDiaChi());
            stmt.setString(6, user.getSoDT());
            stmt.setString(7, user.getTenTK());

            rowEffect += stmt.executeUpdate();
        }

        conn.close();    
        return rowEffect;
    }
    
//    // Delete 1 sample từ JAVA về CSDL
//    public int deleteDB(User user) throws SQLException{
//        int rowEffect = 0;
//        // đối tượng s kết nối SQL Server
//        SQLConnection conn = new SQLConnection("", "");
//        // Chuỗi truy vấn SQL q cho bảng NhanVien
//        String sql1 = "DELETE FROM NhanVien WHERE tenTK = ?";
//        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
//            stmt.setString(1, user.getTenTK());
//
//            rowEffect += stmt.executeUpdate();
//        }
//
//        // Chuỗi truy vấn SQL q cho bảng Users
//        String sql2 = "DELETE FROM Users WHERE tenTK = ?";
//        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql2)) {
//            stmt.setString(1, user.getTenTK());
//
//            rowEffect += stmt.executeUpdate();
//        }
//
//        conn.close();  
//        return rowEffect;
//    }

    
}
