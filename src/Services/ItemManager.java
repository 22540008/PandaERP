/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DBConnection.SQLConnection;
import Models.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class ItemManager {
    private ArrayList<Item> dsItem;

    public ItemManager() {
        this.dsItem = new ArrayList();
    }

    public ArrayList<Item> getDsItem() {
        return dsItem;
    }

    public void setDsItem(ArrayList<Item> dsItem) {
        this.dsItem = dsItem;
    }
    
    public Object[][] getObjDsItem(){
        int column = Item.getColumns().length;
        ArrayList<Item> dsItemActive = filterActiveItem();
        Object[][] dsObjItem = new Object[dsItemActive.size()][column];
        for (int i = 0; i < dsItemActive.size(); i++){
            Object[] objItem = dsItemActive.get(i).getObjectItem();
            for (int j=0; j < column; j++){
                dsObjItem[i][j] = objItem[j];
            }
        }
        return dsObjItem;
    }
    
    // Lấy ds Item đang hoạt động
    public ArrayList<Item> filterActiveItem(){
        ArrayList<Item> dsActiveItem = new ArrayList();
        for (Item item : dsItem){
            if (item.getTrangThai() == 0){
                dsActiveItem.add(item);
            }
        }
        return dsActiveItem;
    }
    
//    public Item getItemviaMaHang(int maHang){
//        for (Item item : dsItem){
//            if (item.getMaHang() == maHang){
//                return item;
//            }
//        }
//        return null;
//    }
    
    // Phương thức import data từ CSDL
    public ArrayList<Item> loadData_DB() throws SQLException{
        // ArrayList<Item> dsItem = new ArrayList<>();
        dsItem = new ArrayList(); // Khởi tạo lại dsItem như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "sa");
        // Chuỗi truy vấn SQL q
        String q = "SELECT * FROM Item";
        PreparedStatement stmt = conn.getConnection().prepareStatement(q);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Item item = new Item(
                    rs.getInt("maHang"),
                    rs.getString("tenHang"),
                    rs.getString("dvt"),
                    rs.getLong("donGia"),
                    rs.getInt("trangThai"));
                    
            //System.out.println(item);
            dsItem.add(item);
        }
        conn.close();
        return dsItem;
    }
    
    // Phương thức import data từ CSDL
    public Item loadData_DB(int maHang) throws SQLException{
        // ArrayList<Item> dsItem = new ArrayList<>();
        Item item = null; // Khởi tạo lại dsItem như một ArrayList mới (xoá data cũ) trước khi lấy dữ liệu từ SQL
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("sa", "sa");
        // Chuỗi truy vấn SQL q
        String sql1 = "SELECT * FROM Item WHERE maHang = ?";
        PreparedStatement stmt = conn.getConnection().prepareStatement(sql1);
        stmt.setInt(1, maHang);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            item = new Item(
                    rs.getInt("maHang"),
                    rs.getString("tenHang"),
                    rs.getString("dvt"),
                    rs.getLong("donGia"),
                    rs.getInt("trangThai"));
            System.out.println(item);
            
        }
        conn.close();
        return item;
    }
    
    
               
    // Add 1 sample từ JAVA về CSDL
    public int addDB(Item item) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng Items
        String sql = "INSERT INTO Item (maHang, tenHang, dvt, donGia, trangThai) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, item.getMaHang());
            stmt.setString(2, item.getTenHang());
            stmt.setString(3, item.getDvt());
            stmt.setLong(4, item.getDonGia());
            stmt.setInt(5, item.getTrangThai());

            rowEffect = stmt.executeUpdate();
        }

        conn.close();      
        return rowEffect;
    }
    
    // Update 1 sample từ JAVA về CSDL
    public int updateDB(Item item) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng Items
        String sql1 = "UPDATE Item SET tenHang = ?, dvt = ?, donGia = ?, trangThai = ? WHERE maHang = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setString(1, item.getTenHang());
            stmt.setString(2, item.getDvt());
            stmt.setLong(3, item.getDonGia());
            stmt.setInt(4, item.getTrangThai());
            stmt.setInt(5, item.getMaHang());

            rowEffect = stmt.executeUpdate();
        }

        conn.close();    
        return rowEffect;
    }
    
    //  Update Hiden status cho sample từ JAVA về CSDL
    public int updateDB(int deleteMaHang) throws SQLException{
        int rowEffect = 0;
        // đối tượng s kết nối SQL Server
        SQLConnection conn = new SQLConnection("", "");
        // Chuỗi truy vấn SQL q cho bảng Items
        String sql1 = "UPDATE Item SET trangThai = 1 WHERE maHang = ?";
        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql1)) {
            stmt.setInt(1, deleteMaHang);
            
            System.out.println(stmt);

            rowEffect = stmt.executeUpdate();
        }

        conn.close();    
        return rowEffect;
    }

    
    
    
}
