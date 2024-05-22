/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Utility.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo
 */
public class GoodsReceipt extends Transaction {

    
    private PurchaseOrder po;
    private int slNhan;
    private int luuKho; // 0: không lưu kho; 1: lưu kho
    private boolean lanCuoi;
    
    private final static HashMap<Integer, Boolean> inventory = new HashMap<Integer, Boolean>(){{
        put(0, false);
        put(1, true);
                }};

    public final static String[] columns = {"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PO", "PO line", "Số PR", "PR line", 
        "Mã hàng", "Tên hàng", "ĐVT", "Mã NCC", "Tên NCC", "Số chưa nhận", "Số lượng nhận", "Lưu kho", "Nhận Lần Cuối"};
    
    public final static String[] expenseReportCols = {"Số CT", "Người tạo PR", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PO", "PO line", "Người tạo PO", "Họ tên buyer", "Số PR", "PR line",  "Người tạo PR",
        "Mã hàng", "Tên hàng", "ĐVT", "Mã NCC", "Tên NCC", "Số chưa nhận", "Số lượng nhận", "Lưu kho", "Nhận Lần Cuối", "đơn giá", "vat", "lineValue"};

    public GoodsReceipt() {
        super();
        po = new PurchaseOrder();
        slNhan = 0;
        luuKho = 0;
        lanCuoi = false;
    }

    public GoodsReceipt(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        po = new PurchaseOrder();
        slNhan = 0;
        luuKho = 0;
        lanCuoi = false;
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
    
    public boolean getLuuKhoBool() {
        return GoodsReceipt.inventory.get(this.luuKho);
    }
    

    public PurchaseRequest getPr() {
        return po.getPr();
    }

    public void setPr(PurchaseRequest pr) {
        this.po.setPr(pr);
    }
    

    public Vendor getVendor() {
        return po.getVendor();
    }

    public boolean isLanCuoi() {
        return lanCuoi;
    }

    public void setLanCuoi(boolean lanCuoi) {
        this.lanCuoi = lanCuoi;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + "GoodsReceipt{" + "po=" + po + ", slNhan=" + slNhan + ", luuKho=" + luuKho + ", nhanLanCuoi=" + lanCuoi + '}';
    }
    
    // Export gr thành Object 2D
    public Object[] getObjPO(){
        Object[] objPO =  new Object[]{this.getSoCT(), 
            this.getTenTK(), 
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
            getLuuKhoBool(),
            this.lanCuoi
        };
                   
        return objPO;
    }
    
    // Export gr thành Object 2D cho Expense Report
    public Object[] getObjPO(String s){
        Object[] objPO =  new Object[]{this.getSoCT(), 
            this.getTenTK(), 
            DateUtils.format(this.getNgayTao()),
            DateUtils.format(this.getNgaySua()),
            this.getTrangThaiStr(),
            this.itemLine,
            getSoPO(),
            getPOline(),
            po.getTenTK(), // người tạo PO
            po.getTen(), // tên buyer
            getSoPR(),
            getPRline(),
            po.getPr().getTenTK(), // người tạo PR
            getMaHang(),
            getTenHang(),
            getDvt(),
            getMaNCC(),
            getTenNCC(),
            po.getSlChoNhan(),
            getSlNhan(),
            getLuuKhoBool(),
            this.lanCuoi,
            po.getGia(),
            po.getVat(),
            0 // giá trị của itemLine mặc định
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

    public int getPRline() {
        return this.getPr().getItemLine();
    }
    
    public void setPRline(int itemLine) {
        this.po.setPRline(itemLine);
    }

    public int getSoPR() {
        return this.po.getSoPR();
    }
    
     public void setSoPR(int soPR) {
        this.po.setSoPR(soPR);
    }
     
    public int getPOline() {
        return this.getPo().getItemLine();
    }
    
    public void setPOline(int itemLine) {
        this.getPo().setItemLine(itemLine);
    }
     
    public int getSoPO(){
        return this.getPo().getSoCT();
    }
    
    public void setSoPO(int soPO){
        this.po.setSoCT(soPO);
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
    

    public int tinhSLConLai() {
        int slChoNhan = po.getSlChoNhan() - this.slNhan;
        if (slChoNhan < 0){
            return -1;
        }
        this.po.setSlChoNhan(slChoNhan);
        return slChoNhan;
    }
    
    public int encodeLuuKho(boolean luuKho){
        HashMap<Boolean, Integer> mapConvert = new HashMap<>();
        for (Integer i : inventory.keySet()){
            mapConvert.put(inventory.get(i), i);
            //System.out.println(status.get(i) + " " + i);
        }
        this.luuKho =  mapConvert.get(luuKho);
        return this.trangThai;
    }

}
