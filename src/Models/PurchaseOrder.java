/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class PurchaseOrder extends Transaction {
    private ArrayList<PurchaseRequest> purchaseRequests;
    private ArrayList<PurchaseItem> purchaseItems;
    private double giaDonHang;

    public PurchaseOrder(double giaDonHang, int soCT, User user, LocalDate ngayTao, LocalDate ngaySua, String trangThai, ArrayList<PurchaseRequest> purchaseRequests) {
        super(soCT, user, ngayTao, ngaySua, trangThai);
        this.purchaseRequests = purchaseRequests;
        this.purchaseItems = new ArrayList<>();
        this.giaDonHang = tinhTongGia();
    }
    
    public double tinhTongGia(){
        return 0;
    }
    
    
  

    
    

    
       
}
