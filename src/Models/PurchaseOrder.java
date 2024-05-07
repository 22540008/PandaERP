/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class PurchaseOrder extends Transaction {
    private ArrayList<PurchaseRequest> purchaseRequests;
    private ArrayList<PurchaseItem> purchaseItems;
    private double giaDonHang;

    public PurchaseOrder(double giaDonHang, int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine, ArrayList<PurchaseRequest> purchaseRequests) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        this.purchaseRequests = purchaseRequests;
        this.purchaseItems = new ArrayList<>();
        this.giaDonHang = tinhTongGia();
    }
    
    public double tinhTongGia(){
        return 0;
    }
    
    
  

    
    

    
       
}
