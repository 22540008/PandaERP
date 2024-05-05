/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ricardo
 */
public class TableERP extends DefaultTableModel {
    private Object[][] data;
    private String[] column;

    public TableERP(Object[][] data, String[] column) {
        super(data, column);
    }
    // Không cho phép sửa trực tiếp trong Table
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    };
    
    public void them(Object[] row) {
        this.addRow(row);
    }
    
    public void xoa(int rowIndex) {
        this.removeRow(rowIndex);
    }
    
    // Kiểm tra hàng nằm trong phạm vi bảng, số lượng cột khớp. Nêu đúng, cập nhật mỗi cột trong hàng với giá trị tương ứng từ rowData.
    public void sua(int rowIndex, Object[] rowData) {
     
        if (rowIndex < 0 || rowIndex >= this.getRowCount()){
            // unchecked exception, thông báo đối số không hợp lệ
            throw new IllegalArgumentException("Dòng xoá nằm ngoài phạm vi dữ liệu của bảng");
        }
        if (rowData == null || rowData.length != this.getColumnCount()){
            throw new IllegalArgumentException("Dữ liệu thay đổi có số cột (thuộc tính) không khớp");
        }
        for (int i=0; i < this.getColumnCount(); i++){
            this.setValueAt(rowData[i], rowIndex, i);
        }
    }
    
    
    
    
}
