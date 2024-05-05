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
public class Transaction {
    protected int soCT;
    protected User user;
    protected LocalDate ngayTao;
    protected LocalDate ngaySua;
    protected String trangThai;
    

    public Transaction(int soCT, User user, LocalDate ngayTao, LocalDate ngaySua, String trangThai) {
        this.soCT = soCT;
        this.user = user;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

     
    
}
