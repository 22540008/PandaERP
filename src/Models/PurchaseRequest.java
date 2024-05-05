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
public class PurchaseRequest extends Transaction {
    private ArrayList<PurchaseItem> purchaseItems;

    public PurchaseRequest(int soCT, User user, LocalDate ngayTao, LocalDate ngaySua, String trangThai) {
        super(soCT, user, ngayTao, ngaySua, trangThai);
        this.purchaseItems = new ArrayList<>();
    }
    
    public double tinhTongGia(){
        return 0;
    }
    
    
    
    
}
