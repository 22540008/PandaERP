/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class PurchaseItem {
    private Item item;
    private int soLuong;
    private int gia;
    private String dvTienTe;
    private double thue;
    private Vendor vendor;
    private String maChiPhi;
    private int tongNhan;
    private int slConLai;
    private double giaItem;

    public PurchaseItem(Item item, int soLuong, int gia, String dvTienTe, double thue, Vendor vendor, String maChiPhi, int tongNhan, int slConLai, double giaItem) {
        this.item = item;
        this.soLuong = soLuong;
        this.gia = gia;
        this.dvTienTe = dvTienTe;
        this.thue = thue;
        this.vendor = vendor;
        this.maChiPhi = maChiPhi;
        this.tongNhan = tongNhan;
        this.slConLai = slConLai;
        this.giaItem = tinhGiaItem();
    }
 
    public double tinhGiaItem(){
        this.giaItem = this.soLuong * this.gia * (1 + this.thue);
        return this.giaItem;
    }
    

    

    
    
    

    

    
    

    
    
    
}
