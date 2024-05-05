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
public class GoodsReceipt extends Transaction {
    private PurchaseOrder purchaseOrder;

    public GoodsReceipt(int soCT, User user, LocalDate ngayTao, LocalDate ngaySua, String trangThai, PurchaseOrder purchaseOrder) {
        super(soCT, user, ngayTao, ngaySua, trangThai);
        this.purchaseOrder = purchaseOrder;
    }


    
    
}
