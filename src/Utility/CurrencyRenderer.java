/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Lớp CurrencyRenderer kế thừa từ DefaultTableCellRenderer, 
 * được sử dụng để định dạng hiển thị của các ô trong một bảng JTable 
 * khi chúng chứa dữ liệu kiểu số (Number) dưới dạng tiền tệ.
 * 
 * <p>Đối tượng của lớp này sẽ định dạng các giá trị số thành định dạng tiền tệ 
 * của Việt Nam (vi-VN) và căn lề phải cho các ô.</p>
 */
public class CurrencyRenderer extends DefaultTableCellRenderer {
    private NumberFormat formatter;

    public CurrencyRenderer() {
        formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        setHorizontalAlignment(JLabel.RIGHT);
    }

    @Override
    public void setValue(Object value) {
        if (formatter == null) {
            formatter = NumberFormat.getCurrencyInstance();
        }
        if (value instanceof Number) {
            setText((value == null) ? "" : formatter.format(value));
        } else {
            // Handle the case where value is not a Number
            System.err.println("Cannot format given Object as a Number: " + value);
        }
    }
}
