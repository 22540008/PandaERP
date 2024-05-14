/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DBConnection.SQLConnection;
import Models.Vendor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class VendorManager {
    private ArrayList<Vendor> dsVendor;

    public VendorManager() {
        this.dsVendor = new ArrayList();
    }

    public ArrayList<Vendor> getDsVendor() {
        return dsVendor;
    }

    public void setDsVendor(ArrayList<Vendor> dsVendor) {
        this.dsVendor = dsVendor;
    }
    
    public Object[][] getObjDsVendor(){
        int column = Vendor.getColumns().length;
        ArrayList<Vendor> dsVendorActive = filterActiveVendor();
        Object[][] dsObjVendor = new Object[dsVendorActive.size()][column];
        for (int i = 0; i < dsVendorActive.size(); i++){
            Object[] objVendor = dsVendorActive.get(i).getObjectVendor();
            for (int j=0; j < column; j++){
                dsObjVendor[i][j] = objVendor[j];
            }
        }
        return dsObjVendor;
    }
    
    // Lấy ds Vendor đang hoạt động
    public ArrayList<Vendor> filterActiveVendor(){
        ArrayList<Vendor> dsActiveVendor = new ArrayList();
        for (Vendor vendor : dsVendor){
            if (vendor.getTrangThai() == 0){
                dsActiveVendor.add(vendor);
            }
        }
        return dsActiveVendor;
    }
    
    public Vendor getVendorviaMaNCC(int maNCC){
        for (Vendor vendor : dsVendor){
            if (vendor.getMaNCC() == maNCC){
                return vendor;
            }
        }
        return null;
    }
    
    // Phương thức import data từ CSDL
    public ArrayList<Vendor> loadData_DB() throws SQLException{
        // ArrayList<Vendor> dsVendor = new ArrayList<>();
        dsVendor = new ArrayList(); // Khởi tạo lại dsVendor như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "sa");
        // Chuỗi truy vấn SQL q
        String q = "SELECT * FROM Vendor";
        PreparedStatement stmt = conn.getConnection().prepareStatement(q);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Vendor vendor = new Vendor(
                    rs.getInt("maNCC"),
                    rs.getString("tenNCC"),
                    rs.getString("diaChi"),
                    rs.getString("mST"),
                    rs.getFloat("vat"),
                    rs.getInt("trangThai"));
            System.out.println(vendor);
            dsVendor.add(vendor);
        }
        conn.close();
        return dsVendor;
    }
    
    // Phương thức import data từ CSDL
    public Vendor loadData_DB(int maNCC) throws SQLException{
        // ArrayList<Vendor> dsVendor = new ArrayList<>();
        dsVendor = new ArrayList(); // Khởi tạo lại dsVendor như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "sa");
        // Chuỗi truy vấn SQL q
        String q = "SELECT * FROM Vendor WHERE maNCC = ?";
        PreparedStatement stmt = conn.getConnection().prepareStatement(q);
        stmt.setInt(1, maNCC);
        ResultSet rs = stmt.executeQuery();
        Vendor vendor = new Vendor(
                rs.getInt("maNCC"),
                rs.getString("tenNCC"),
                rs.getString("diaChi"),
                rs.getString("mST"),
                rs.getFloat("vat"),
                rs.getInt("trangThai"));
        System.out.println(vendor);
        conn.close();
        return vendor;
    }
               
    // Add 1 sample từ JAVA về CSDL
    public int addDB(Vendor vendor) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng Vendors
        String sql = "INSERT INTO Vendor (maNCC, tenNCC, diaChi, mST, vat, trangThai) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, vendor.getMaNCC());
            stmt.setString(2, vendor.getTenNCC());
            stmt.setString(3, vendor.getDiaChi());
            stmt.setString(4, vendor.getmST());
            stmt.setFloat(5, vendor.getVat());
            stmt.setInt(6, vendor.getTrangThai());

            rowEffect = stmt.executeUpdate();
        }

        conn.close();      
        return rowEffect;
    }
    
    // Update 1 sample từ JAVA về CSDL
    public int updateDB(Vendor vendor) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng Vendors
        String sql1 = "UPDATE Vendor SET tenNCC = ?, diaChi = ?, mST = ?, vat = ?, trangThai = ? WHERE maNCC = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setString(1, vendor.getTenNCC());
            stmt.setString(2, vendor.getDiaChi());
            stmt.setString(3, vendor.getmST());
            stmt.setFloat(4, vendor.getVat());
            stmt.setInt(5, vendor.getTrangThai());
            stmt.setInt(6, vendor.getMaNCC());

            rowEffect = stmt.executeUpdate();
        }

        conn.close();    
        return rowEffect;
    }
    
    //  Update Hiden status cho sample từ JAVA về CSDL
    public int updateDB(int deleteMaNCC) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng Vendors
        String sql1 = "UPDATE Vendor SET trangThai = 1 WHERE maNCC = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setInt(1, deleteMaNCC);
            
            System.out.println(stmt);

            rowEffect = stmt.executeUpdate();
        }

        conn.close();    
        return rowEffect;
    }
    
    
}
