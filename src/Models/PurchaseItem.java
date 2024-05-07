/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Ricardo
 */
public class PurchaseItem {
    private Item item;
    private int soLuong;
    private double thue;
    private Vendor vendor;
    private String maChiPhi;
    private int tongNhan;
    private int slConLai;
    private double giaItem;

    public PurchaseItem() {
    }

    public PurchaseItem(Item item, int soLuong, double thue, Vendor vendor, String maChiPhi, int tongNhan, int slConLai, double giaItem) {
        this.item = item;
        this.soLuong = soLuong;
        this.thue = thue;
        this.vendor = vendor;
        this.maChiPhi = maChiPhi;
        this.tongNhan = tongNhan;
        this.slConLai = slConLai;
        this.giaItem = 0;
    }

    public PurchaseItem(int maItem, int soLuong, Vendor vendor, String maChiPhi) {
        this.item = item;
        this.soLuong = soLuong;
        this.vendor = vendor;
        this.maChiPhi = maChiPhi;
        this.thue = 0.1;
        this.vendor = new Vendor();
        this.tongNhan = 0;
        this.slConLai = 0;
        this.giaItem = 0;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThue() {
        return thue;
    }

    public void setThue(double thue) {
        this.thue = thue;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getMaChiPhi() {
        return maChiPhi;
    }

    public void setMaChiPhi(String maChiPhi) {
        this.maChiPhi = maChiPhi;
    }

    public int getTongNhan() {
        return tongNhan;
    }

    public void setTongNhan(int tongNhan) {
        this.tongNhan = tongNhan;
    }

    public int getSlConLai() {
        return slConLai;
    }

    public void setSlConLai(int slConLai) {
        this.slConLai = slConLai;
    }

    public double getGiaItem() {
        return giaItem;
    }

    public void setGiaItem(double giaItem) {
        this.giaItem = giaItem;
    }

    @Override
    public String toString() {
        return "PurchaseItem{" + "item=" + item + ", soLuong=" + soLuong + ", thue=" + thue + ", vendor=" + vendor + ", maChiPhi=" + maChiPhi + ", tongNhan=" + tongNhan + ", slConLai=" + slConLai + ", giaItem=" + giaItem + '}';
    }
    
 
    public double tinhGiaItem(){
        this.giaItem = this.soLuong * item.getDonGia() * (1 + this.thue);
        return this.giaItem;
    }
    
    public int tinhSLConLai(){
        this.slConLai = this.soLuong - this.tongNhan;
        return this.slConLai;
    }
    

    

    
    
    

    

    
    

    
    
    
}
