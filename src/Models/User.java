/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DBConnection.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * PreparedStatement, đặt các giá trị vào SQL query mà không cần nối chuỗi -> Chống injection; dễ đọc
 * try-with-resoure tự giải phóng bộ nhớ. Với các con số trong stmt tương ứng vị trí của '?'
 * rowEffect: dòng bị ảnh hưởng trong SQL
 * 
 * @author Ricardo
 */
public class User {
    private int maNV;
    private String tenTK;
    private String matKhau;
    private String ho;
    private String ten;
    private String chucVu;
    private String phongBan;
    private String diaChi;
    private String soDT;
    private String[] systemRoles;
    private int trangThai; // 0: active; 1: inactive;

    private ArrayList<User> dsUser;
    public final static String[] columns = {"Mã NV", "Tên tài khoản", "Mật khẩu", "Họ", "Tên", "Chức vụ", "Phòng ban", "Địa chỉ", "Số điện thoại", "Quyền hệ thống"}; // Tên cột trong bảng  

    public User(int maNV, String tenTK, String matKhau, String ho, String ten, String chucVu, String phongBan, String diaChi, String soDT, int trangThai) {
        this.maNV = maNV;
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.ho = ho;
        this.ten = ten;
        this.chucVu = chucVu;
        this.phongBan = phongBan;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.systemRoles = null;
        this.trangThai = trangThai;
    }
    
    // Khởi tạo User khi dùng add
    public User(Object[] objUser){
        int column = objUser.length;
        if (column == 10){
            for (int i=0; i < objUser.length; i++){
                this.maNV = (int) objUser[0];
                this.tenTK = (String) objUser[1];
                this.matKhau = (String) objUser[2];
                this.ho = (String) objUser[3];
                this.ten = (String) objUser[4];
                this.chucVu = (String) objUser[5];
                this.phongBan = (String) objUser[6];
                this.diaChi = (String) objUser[7];
                this.soDT = (String) objUser[8];
                this.systemRoles = ((String) objUser[9]).split(";");
                this.trangThai = 0;
            }  
        }
        else {
            throw new IllegalArgumentException("Object[] user không khớp số lượng thuộc tính");
        }
    }
    
    public static String[] getColumns() {
        return columns;
    }

    public User() {
    }   
    
    public int getMaNV() {
        return this.maNV;
    }
    
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }
    
    public String getTenTK() {
        return this.tenTK;
    }
    
    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }
            
    public String getMatKhau() {
        return this.matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    public String getHo() {
        return this.ho;
    }
    
    public void setHo(String ho) {
        this.ho = ho;
    }
    
    public String getTen() {
        return this.ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }    
    
    public String getChucVu() {
        return this.chucVu;
    }
    
    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    public String getPhongBan() {
        return this.phongBan;
    }
    
    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }
    
    public String getDiaChi() {
        return this.diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getSoDT() {
        return this.soDT;
    }
    
    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }
    
    public String getSystemRole() {
        return String.join(";", this.systemRoles);
    }

    public String[] getSystemRoles() {
        return systemRoles;
    }
    
    public void setSystemRole(String systemRoles) {
        this.systemRoles = systemRoles.split(";");
    }

    public void setSystemRoles(String[] systemRoles) {
        this.systemRoles = systemRoles;
    }
    
    public int getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "User{" + "maNV=" + maNV + ", tenTK=" + tenTK + ", matKhau=" + matKhau + ", ho=" + ho + ", ten=" + ten + ", chucVu=" + chucVu + ", phongBan=" + phongBan + ", diaChi=" + diaChi + ", soDT=" + soDT + ", systemRoles=" + Arrays.toString(systemRoles) + ", trangThai=" + trangThai + '}';
    }
    
    public Object[] getObjectUser(){
        return new Object[] {maNV, tenTK, matKhau, ho, ten, chucVu, phongBan, diaChi, soDT, this.getSystemRole()};
    }
   
    
    public User dangnhap(String userName, String passWord) throws SQLException{
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q
        String q =  """
                    SELECT maNV, Users.tenTK, matKhau, systemRoles, trangThai, ho, ten, chucVu, phongBan, diaChi, soDT
                    FROM Users JOIN NhanVien ON Users.tenTK = NhanVien.tenTK
                    WHERE Users.tenTK = ? AND MatKhau = ?
                    """;
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(q)) {
            stmt.setString(1, userName);
            stmt.setString(2, passWord);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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
                conn.close();
                return user;
            }                    
        }        
        conn.close();
        return null;
    }

    
}
