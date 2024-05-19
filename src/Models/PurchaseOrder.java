/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Utility.DateUtils;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class PurchaseOrder extends Transaction {

    
    private PurchaseRequest pr;
    private Vendor vendor;
    private long gia;
    private int soLuong;
    private float vat;
    private double giaItem;
    //private double giaDonHang;
    private int slChoNhan;
    
    public static final String[] columns = {"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PR", "PR line", "Mã hàng", "Tên hàng", "ĐVT", "Mã NCC", "Tên NCC",
        "Giá", "Số lượng", "VAT%", "Giá tổng", "SL chờ nhận"};

    public PurchaseOrder() {
        pr = new PurchaseRequest();
        vendor = new Vendor();
        //giaDonHang = 0f;
    }

    public PurchaseOrder(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        pr = new PurchaseRequest();
        vendor = new Vendor();
        //giaDonHang = 0f;
    }
    
    public PurchaseRequest getPr() {
        return pr;
    }

    public void setPr(PurchaseRequest pr) {
        this.pr = pr;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

//    public double getGiaDonHang() {
//        return giaDonHang;
//    }
//
//    public void setGiaDonHang(double giaDonHang) {
//        this.giaDonHang = giaDonHang;
//    }

    public int getSlChoNhan() {
        return slChoNhan;
    }

    public void setSlChoNhan(int slChoNhan) {
        this.slChoNhan = slChoNhan;
    }

    @Override
    public String toString() {
        return super.toString() + "PurchaseOrder{" + "pr=" + pr + ", vendor=" + vendor + ", gia=" + gia + ", soLuong=" + soLuong + ", vat=" + vat + ", giaItem=" + giaItem + '}';
    }

    //public static final String[] columns = {"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PR", "PR line", "Mã hàng", 
    //    "Tên hàng", "ĐVT", "Mã NCC", "Tên NCC", "Giá", "Số lượng", "VAT", "Tổng giá"};
    
    public Object[] getObjPO(){
        Object[] objPR =  new Object[]{this.getSoCT(), 
            this.getUser(), 
            DateUtils.format(this.getNgayTao()),
            DateUtils.format(this.getNgaySua()),
            this.getTrangThaiStr(),
            this.itemLine,
            getSoPR(),
            getPRline(),
            getMaHang(),
            getTenHang(),
            getDvt(),
            getMaNCC(),
            getTenNCC(),
            getGia(),
            getSoLuong(),
            getVat(),
            getGiaItem(),
            getSlChoNhan()
        };
                   
        return objPR;
    }

    public String getTenNCC() {
        return vendor.getTenNCC();
    }
    
    public void setTenNCC(String tenNCC){
        vendor.setTenNCC(tenNCC);
    }

    public int getMaNCC() {
        return vendor.getMaNCC();
    }
    
    public void setMaNCC(int maNCC){
        vendor.setMaNCC(maNCC);
    }

    public double getGiaItem() {
        return this.giaItem;
    }
    
    public void setGiaItem(double giaItem) {
        this.giaItem = giaItem;
    }

    public float getVat() {
        return this.vat;
    }
    
    public void setVat(float vat){
        this.vat = vat;
    }

    public int getSoLuong() {
        return this.soLuong;
    }
    
    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }

    public long getGia() {
        return this.gia;
    }
    
    public void setGia(long gia){
        this.gia = gia;
    }

    public int getPRline() {
        return this.getPr().getItemLine();
    }
    
    public void setPRline(int itemLine) {
        this.getPr().setItemLine(itemLine);
    }

    public int getSoPR() {
        return this.getPr().getSoCT();
    }
    
     public void setSoPR(int soPR) {
        this.pr.setSoCT(soPR);
    }

    public String getTenHang() {
        return this.getPr().getItem().getTenHang();
    }
    
    public void setTenHang(String tenHang){
        this.getPr().getItem().setTenHang(tenHang);
    }

    public int getMaHang() {
        return this.getPr().getItem().getMaHang();
    }
    
    public void setMaHang(int maHang){
        this.getPr().getItem().setMaHang(maHang);
    }

    public String getDvt() {
        return this.pr.getItem().getDvt();
    }
    
    public void setDvt(String dvt) {
        this.pr.getItem().setDvt(dvt);
    }
    
    public double tinhGiaItem() {
        return this.giaItem = this.gia * this.soLuong * (1 + this.vat / 100);
    }
       
}
