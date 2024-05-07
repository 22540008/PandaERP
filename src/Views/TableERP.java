/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ricardo
 */
public class TableERP extends DefaultTableModel {
    private Object[][] data;
    private String[] column;
    private boolean editable = false; 
    private Set<Integer> editableColumns = new HashSet<>();
    private Map<Integer, Class<?>> columnTypes = new HashMap<>();

    public TableERP(Object[][] data, String[] column) {
        super(data, column);
    }
    // Không cho phép sửa trực tiếp trong Table
    // New method to set whether the table is editable
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    // New method to add an editable column
    public void addEditableColumn(int column) {
        editableColumns.add(column);
    }

    // Override Cho phép edit 1 số cột nhất định
    @Override
    public boolean isCellEditable(int row, int column) {
        return editable && editableColumns.contains(column);
        // return editable;
    }
    
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
    
    
    public void setColumnType(int column, Class<?> type) {
        columnTypes.put(column, type);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> type = columnTypes.get(columnIndex);
        if (type != null) {
            return type;
        }
        return super.getColumnClass(columnIndex);
    }
    
    public long capNhatTongGia(int colSoLuong, int colDonGia, int colTongGia) {
        long total = 0;
        for (int i = 0; i < getRowCount(); i++) {
            Object soLuongObj = getValueAt(i, colSoLuong);
            Object donGiaObj = getValueAt(i, colDonGia);
            if (soLuongObj instanceof Number && donGiaObj instanceof Number) {
                long soLuong = ((Number) soLuongObj).longValue();
                long donGia = ((Number) donGiaObj).longValue();
                long giaItem = soLuong * donGia;
                setValueAt(giaItem, i, colTongGia);
                total += giaItem;
            }
        }
        return total;
    }
    
 
}

/*
// Thiết lập kiểu dữ liệu
table.setColumnType(0, Integer.class);
table.setColumnType(1, Long.class);
*/