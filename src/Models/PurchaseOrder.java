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
    private double giaDonHang;
    
    public static final String[] columns = {"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PR", "PR line", "Mã hàng", "Tên hàng", "ĐVT", "Mã NCC", "Tên NCC",
        "Giá", "Số lượng", "VAT%", "Tổng giá"};

    public PurchaseOrder() {
        pr = new PurchaseRequest();
        vendor = new Vendor();
        giaDonHang = 0f;
    }

    public PurchaseOrder(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        pr = new PurchaseRequest();
        vendor = new Vendor();
        giaDonHang = 0f;
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

    public double getGiaDonHang() {
        return giaDonHang;
    }

    public void setGiaDonHang(double giaDonHang) {
        this.giaDonHang = giaDonHang;
    }
    
    
    
    @Override
    public String toString() {
        return super.toString() + "PurchaseOrder{" + "purchaseRequests=" + pr + ", vendor=" + vendor + ", giaDonHang=" + giaDonHang + '}';
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
            getDonGia(),
            getSoLuong(),
            getVat(),
            getGiaItem()};
                   
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
        return this.getPr().getGiaItem();
    }
    
    public void setGiaItem(double giaItem) {
        this.getPr().setGiaItem(giaItem);
    }

    public float getVat() {
        return this.getPr().getVat();
    }
    
    public void setVat(float vat){
        this.getPr().setVat(vat);
    }

    public int getSoLuong() {
        return this.getPr().getSoLuong();
    }
    
    public void setSoLuong(int soLuong){
        this.getPr().setSoLuong(soLuong);
    }

    public long getDonGia() {
        return this.getPr().getDonGia();
    }
    
    public void setDonGia(long donGia){
        this.getPr().setDonGia(donGia);
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
        this.getPr().setSoCT(soCT);
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
       
}
