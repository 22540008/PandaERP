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
    

    public static final String[] columns = {"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Mã hàng", "Tên hàng", "ĐVT", 
        "Giá Est", "Số lượng", "Tổng giá"};

    public PurchaseRequest() {
        super();
        item = new Item();
    }
    
    public PurchaseRequest(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        item = new Item();
    }
    
    public PurchaseRequest(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine, Item item, long giaEst, int soLuong) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        this.itemLine = itemLine;
        this.item = item;
        this.donGia = giaEst;
        this.soLuong = soLuong;
        this.giaItem = 0f;
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
    public String toString() {
        return super.toString() + "PurchaseRequest{" + "item=" + item + ", donGia=" + donGia + ", soLuong=" + soLuong + ", giaItem=" + giaItem + '}';
    }

    public Object[] getObjPR(){
        Object[] objPR =  new Object[]{this.getSoCT(), 
            this.getTenTK(), 
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
        return this.giaItem = this.item.getDonGia() * this.soLuong;
    }
    
    
}
