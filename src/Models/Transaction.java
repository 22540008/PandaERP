/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Ricardo
 */
public class Transaction {
    protected int soCT;
    protected String user;
    protected Date ngayTao;
    protected Date ngaySua;
    protected int trangThai; // 0: active; 1: deleted; 2: inactive; 3: processed; 4: duyệt
    //protected String trangThaiStr;
    protected int itemLine;
    
    public final static HashMap<Integer, String> status = new HashMap<Integer, String>(){{
        put(0, "Đang xử lý");
        put(1, "Đã xoá"); 
        put(2, "Đã huỷ");
        put(3, "Đã được xử lý");
        put(4, "Duyệt"); // kết thúc chứng từ, không có step tiếp theo, nhưng vẫn có thể "đóng" lại
                }};

    public Transaction() {
        ngayTao = new Date(); // bắt buộc phải khởi tạo. Trống sẽ lỗi bước truy vấn SQL
        ngaySua = new Date();
    }


    public Transaction(int soCT, String user, Date ngayTao, Date ngaySua, int trangThai, int itemLine) {
        this.soCT = soCT;
        this.user = user;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
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
        return Transaction.status.get(this.trangThai);
    }

    public int getItemLine() {
        return itemLine;
    }

    public void setItemLine(int itemLine) {
        this.itemLine = itemLine;
    }
    
    

    @Override
    public String toString() {
        return "Transaction{" + "soCT=" + soCT + ", user=" + user + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", trangThai=" + trangThai + ", itemLine=" + itemLine + '}';
    }
    
    
    
    public int encodeTrangThai(String trangThaiStr){
        //System.out.println(trangThai);
        HashMap<String, Integer> mapConvert = new HashMap<>();
        for (Integer i : status.keySet()){
            mapConvert.put(status.get(i), i);
            //System.out.println(status.get(i) + " " + i);
        }
        this.trangThai =  mapConvert.get(trangThaiStr);
        return this.trangThai;
    }

    
}
