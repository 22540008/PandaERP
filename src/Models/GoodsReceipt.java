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
public class GoodsReceipt extends Transaction {
    private PurchaseOrder purchaseOrder;

    public GoodsReceipt(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine, PurchaseOrder purchaseOrder) {
        super(soCT, user, ngayTao, ngaySua, trangThai, itemLine);
        this.purchaseOrder = purchaseOrder;
    }


    
    
}
