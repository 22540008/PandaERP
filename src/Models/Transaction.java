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
public class Transaction {
    protected int soCT;
    protected String user;
    protected Date ngayTao;
    protected Date ngaySua;
    protected int trangThai; // 0: active; 1: deleted; 2: inactive
    protected String trangThaiStr;
    protected int itemLine;

    public Transaction() {
    }


    public Transaction(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine) {
        this.soCT = soCT;
        this.user = user;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.trangThaiStr = "";
        this.itemLine = itemLine;
    }

    public int getSoCT() {
        return soCT;
    }

    public void setSoCT(int soCT) {
        this.soCT = soCT;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThaiStr() {
        return trangThaiStr;
    }

    public void setTrangThaiStr(String trangThaiStr) {
        this.trangThaiStr = trangThaiStr;
    }
    
    


    @Override
    public String toString() {
        return "Transaction{" + "soCT=" + soCT + ", user=" + user + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", trangThai=" + trangThai + ", trangThaiStr=" + trangThaiStr + '}';
    }

    public void setTrangThaiStr() {
        if (trangThai == 0) {
            this.trangThaiStr = "Đang xử lý";
        }
        else if (trangThai == 1) {
            this.trangThaiStr = "Xoá";
        }
        else if (trangThai == 2) {
            this.trangThaiStr = "Đóng";
        }
    }
    
}
