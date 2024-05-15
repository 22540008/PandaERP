/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Ricardo
 */
public class Vendor {
    private int maNCC;
    private String tenNCC;
    private String diaChi;
    private String mST;
    private float vat;
    private int trangThai; // 0: active; 1: deleted; 2: inactive
    public static final String[] columns = {"Mã NCC", "Tên NCC", "Địa chỉ", "MST", "VAT%"};

    public Vendor() {
        this.trangThai = 0;
    }
    
    public Vendor(int maNCC, String tenNCC, String diaChi, String mST, float vat, int trangThai) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.mST = mST;
        this.vat = vat;
        this.trangThai = trangThai;
    }

    public static String[] getColumns() {
        return columns;
    } 
    
    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getmST() {
        return mST;
    }

    public void setmST(String mST) {
        this.mST = mST;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }
    
    @Override
    public String toString() {
        return "Vendor{" + "maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", mST=" + mST + ", vat=" + vat + ", trangThai=" + trangThai + '}';
    }
    
    public Object[] getObjectVendor(){
        return new Object[] {maNCC, tenNCC, diaChi, mST, vat};
    }
    

}
