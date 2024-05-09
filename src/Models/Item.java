/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DBConnection.SQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ricardo
 */
public class Item {
    private int maHang;
    private String tenHang;
    private String dvt;
    private long donGia;
    private int trangThai;
    private static final String[] columns = {"Mã hàng", "Tên hàng", "ĐVT", "Đơn giá (VND)"}; // Tên cột trong bảng  
   
    public Item(int maHang, String tenHang, String dvt, long donGia, int trangThai) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.dvt = dvt;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public Item() {
        this.trangThai = 0; // 0: active; 1: hiden; 2: inactive
    }

    public static String[] getColumns() {
        return columns;
    }
    
    public int getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public String getDvt() {
        return dvt;
    }

    public long getDonGia() {
        return donGia;
    }
    
    public int getTrangThai() {
        return trangThai;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Item{" + "maHang=" + maHang + ", tenHang=" + tenHang + ", dvt=" + dvt + ", donGia=" + donGia + ", trangThai=" + trangThai + '}';
    }
    
    
    public Object[] getObjectItem(){
        return new Object[] {maHang, tenHang, dvt, donGia};
    }
    
}
