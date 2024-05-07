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
    private int soLuong;
    private long giaItem;
    

    private static final String[] columns = {"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Mã hàng", "Tên hàng", "ĐVT", 
        "Giá Est", "Số lượng", "Tổng giá"};

    public PurchaseRequest() {
    }
    
    public PurchaseRequest(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
    }
    
    public PurchaseRequest(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine, Item item, int soLuong) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        this.itemLine = itemLine;
        this.item = item;
        this.soLuong = soLuong;
        this.giaItem = 0;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getGiaItem() {
        return giaItem;
    }

    public void setGiaItem(long giaItem) {
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
        return trangThaiStr;
    }

    
    public Object[] getObjPR(){
        Object[] objPR =  new Object[]{this.getSoCT(), 
            this.getUser(), 
            //this.getNgayTao().format(DateUtils.DATE_FORMAT), 
            //this.getNgaySua().format(DateUtils.DATE_FORMAT), 
            DateUtils.format(this.getNgayTao()),
            DateUtils.format(this.getNgaySua()),
            this.getTrangThaiStr(),
            this.getItemLine(),
            this.getItem().getMaHang(), 
            this.getItem().getTenHang(), 
            this.getItem().getDvt(), 
            this.getItem().getDonGia(),
            this.getSoLuong(), 
            this.getGiaItem()};
        return objPR;
    }
    
    public long tinhGiaItem() {
        return this.giaItem = this.item.getDonGia() * this.soLuong;
    }
    
    
}
