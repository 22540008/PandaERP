/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


import java.util.ArrayList;
import Utility.DateUtils;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class PurchaseRequest extends Transaction {
    private Item item;
    private long donGia;
    private int soLuong;
    private double giaItem;
    private float vat;
    

    private static final String[] columns = {"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Mã hàng", "Tên hàng", "ĐVT", 
        "Giá Est", "Số lượng", "Tổng giá"};

    public PurchaseRequest() {
    }
    
    public PurchaseRequest(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
    }
    
    public PurchaseRequest(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine, Item item, long giaEst, int soLuong) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        this.itemLine = itemLine;
        this.item = item;
        this.donGia = giaEst;
        this.soLuong = soLuong;
        this.vat = 0f;
        this.giaItem = 0f;
    }

    public static String[] getColumns() {
        return columns;
    }

    public int getItemLine() {
        return itemLine;
    }

    public void setItemLine(int itemLine) {
        this.itemLine = itemLine;
    }
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }
    
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaItem() {
        return giaItem;
    }

    public void setGiaItem(double giaItem) {
        this.giaItem = giaItem;
    }

    @Override
    public int getSoCT() {
        return soCT;
    }

    @Override
    public void setSoCT(int soCT) {
        this.soCT = soCT;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public Date getNgayTao() {
        return ngayTao;
    }

    @Override
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public Date getNgaySua() {
        return ngaySua;
    }

    @Override
    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    @Override
    public int getTrangThai() {
        return trangThai;
    }

    @Override
    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String getTrangThaiStr() {
        return super.getTrangThaiStr();
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + "PurchaseRequest{" + "item=" + item + ", giaEst=" + donGia + ", soLuong=" + soLuong + ", giaItem=" + giaItem + '}';
    }
    
    public Object[] getObjPR(){
        Object[] objPR =  new Object[]{this.getSoCT(), 
            this.getUser(), 
            //this.getNgayTao().format(DateUtils.DATE_FORMAT), 
            //this.getNgaySua().format(DateUtils.DATE_FORMAT), 
            DateUtils.format(this.getNgayTao()),
            DateUtils.format(this.getNgaySua()),
            this.getTrangThaiStr(),
            this.itemLine,
            this.getItem().getMaHang(), 
            this.getItem().getTenHang(), 
            this.getItem().getDvt(), 
            this.donGia,
            this.soLuong, 
            this.giaItem};
        return objPR;
    }
    
    public double tinhGiaItem() {
        return this.giaItem = this.item.getDonGia() * this.soLuong * (1 + this.vat);
    }
    
    
}
