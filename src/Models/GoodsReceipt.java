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
public class GoodsReceipt extends Transaction {

    
    private PurchaseOrder po;
    private int slNhan;
    private int luuKho; // 0: không lưu kho; 1: lưu kho
    private boolean nhanLanCuoi;
    

    
    public static final String[] columns = {"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PR", "PR line", "Số PO", "PO line", 
        "Mã hàng", "Tên hàng", "ĐVT", "Mã NCC", "Tên NCC", "Số chưa nhận", "Số lượng nhận", "Lưu kho", "Nhận Lần Cuối"};

    public GoodsReceipt() {
        po = new PurchaseOrder();
        slNhan = 0;
        luuKho = 0;
        nhanLanCuoi = false;
    }

    public GoodsReceipt(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        po = new PurchaseOrder();
        slNhan = 0;
        luuKho = 0;
        nhanLanCuoi = false;
    }

    public PurchaseOrder getPo() {
        return po;
    }

    public void setPo(PurchaseOrder po) {
        this.po = po;
    }
    
    public void setVendor(Vendor vendor) {
        po.setVendor(vendor);
    }

    public void setSoLuong(int aInt) {
        po.setSoLuong(soCT);
    }

    public int getSlNhan() {
        return slNhan;
    }

    public void setSlNhan(int slNhan) {
        this.slNhan = slNhan;
    }


    public int getLuuKho() {
        return luuKho;
    }

    public void setLuuKho(int luuKho) {
        this.luuKho = luuKho;
    }
    

    public PurchaseRequest getPr() {
        return po.getPr();
    }

    public void setPr(PurchaseRequest pr) {
        this.po.setPr(pr);;
    }

    public Vendor getVendor() {
        return po.getVendor();
    }


    public Object[] getObjPO(){
        Object[] objPO =  new Object[]{this.getSoCT(), 
            this.getUser(), 
            DateUtils.format(this.getNgayTao()),
            DateUtils.format(this.getNgaySua()),
            this.getTrangThaiStr(),
            this.itemLine,
            getSoPO(),
            getPOline(),
            getSoPR(),
            getPRline(),
            getMaHang(),
            getTenHang(),
            getDvt(),
            getMaNCC(),
            getTenNCC(),
            po.getSlChoNhan(),
            getSlNhan(),
            this.luuKho,
            this.nhanLanCuoi
        };
                   
        return objPO;
    }

    public String getTenNCC() {
        return po.getVendor().getTenNCC();
    }
    
    public void setTenNCC(String tenNCC){
        po.getVendor().setTenNCC(tenNCC);
    }

    public int getMaNCC() {
        return po.getVendor().getMaNCC();
    }
    
    public void setMaNCC(int maNCC){
        po.getVendor().setMaNCC(maNCC);
    }

    public int getSoLuong() {
        return po.getSoLuong();
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
        this.po.setSoCT(soPR);
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
        return po.getPr().getItem().getDvt();
    }
    
    public void setDvt(String dvt) {
        this.po.setDvt(dvt);
    }
    
    private Object getSoPO() {
        return po.getSoCT();
    }

    private Object getPOline() {
        return po.getItemLine();
    }

    private int tinhSLConLai() {
        int slChoNhan = po.getSlChoNhan() - this.slNhan;
        this.po.setSlChoNhan(slChoNhan);
        return slChoNhan;
    }

}
