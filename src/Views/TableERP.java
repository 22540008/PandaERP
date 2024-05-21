/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Ricardo
 */
public class TableERP extends DefaultTableModel {
    private Object[][] data;
    private String[] column;
    private boolean editable = false; 
    private Set<Integer> editableColumns = new HashSet<>();
    private Set<Object> specialValues = new HashSet<>();
    private Map<Integer, Class<?>> columnTypes = new HashMap<>();
    private int specialColumn; // Cột chứa giá trị đặc biệt không cho edit
    private Map<String, TableColumn> columnMap = new HashMap<>(); // Map tên cột với cột

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
    
    // PP tao5 set các values trạng thái không cho edit hàng
    public void setSpecialValues(Set<Object> specialValues, int specialColumn) {
        this.specialValues = specialValues;
        this.specialColumn = specialColumn;
    }

    // Override Cho phép edit 1 số cột nhất định và không cho edit các hàng có giá trị đặc biệt.
    @Override
    public boolean isCellEditable(int row, int column) {
        //return editable && editableColumns.contains(column);
        // Check if the cell at the special column in this row has a special value
        if (specialValues.contains(getValueAt(row, specialColumn))) {
            // If it does, the entire row is not editable
            return false;
        } else {
            // Otherwise, the cell is editable if the table is editable and the column is in the set of editable columns
            return editable && editableColumns.contains(column);
        }
      
    }
    
    /**
    * Tìm các vị trí mà một chuỗi cụ thể xuất hiện trong một cột cụ thể của bảng.
    *
    * @param colIndex chỉ số của cột cần kiểm tra.
    * @param str chuỗi cần tìm vị trí xuất hiện.
    * @return một mảng int[] chứa các vị trí mà chuỗi xuất hiện trong cột.
    * @throws IllegalArgumentException nếu colIndex nằm ngoài phạm vi của bảng.
    */
    public int[] mapRow(int colIndex, String str) {
        if (colIndex < 0 || colIndex >= this.getColumnCount()) {
            throw new IllegalArgumentException("colIndex nằm ngoài phạm vi của bảng");
        }

        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < this.getRowCount(); i++) {
            String cellValueStr = String.valueOf(this.getValueAt(i, colIndex));
            if (str.equals(cellValueStr)) {
                positions.add(i);
                System.out.println(i + " : " + cellValueStr);
            }
        }
        return positions.stream().mapToInt(i -> i).toArray();
    }
    
    /**
    * Tìm các vị trí mà một chuỗi cụ thể xuất hiện trong một cột cụ thể của bảng.
    *
    * @param colIndex mảng int[] chứa các chỉ số của cột cần kiểm tra.
    * @param str mảng String[] chứa các chuỗi cần tìm vị trí xuất hiện.
    * @return một mảng int[] chứa các vị trí mà chuỗi xuất hiện trong cột.
    * @throws IllegalArgumentException nếu colIndex nằm ngoài phạm vi của bảng.
    */
    public int[] mapRow(int[] colIndex, String[] str) {
        if (colIndex.length != str.length){
            throw new IllegalArgumentException("Số cột của bảng và số phần tử cần tìm không khớp: " + colIndex.length + " - " + str.length);
        }
        for (int row : colIndex){
            if (row < 0 || row >= this.getColumnCount()) {
                throw new IllegalArgumentException("colIndex nằm ngoài phạm vi của bảng");
            }
        }

        ArrayList<Integer> positions = new ArrayList<>();
        for (int row = 0; row < this.getRowCount(); row++) {
            String searchStr = "";
            String cellsValueStr = "";
            for (int col=0; col < colIndex.length; col++){
                searchStr += str[col];
                cellsValueStr += String.valueOf(this.getValueAt(row, colIndex[col]));
            }
            //String cellsValueStr = String.valueOf(this.getValueAt(row, colIndex));
            if (searchStr.equals(cellsValueStr)) {
                positions.add(row);
                System.out.println(row + " : " + cellsValueStr);
            }
        }
        return positions.stream().mapToInt(i -> i).toArray(); // Chuyển ArrayList về mảng int[]
    }
    
    
    /**
     * Thêm dữ liệu từ một mảng hai chiều vào TableModel tại các cột được chỉ định.
     *
     * @param tbColumn Mảng chứa các chỉ số cột trong TableModel mà dữ liệu sẽ được thêm vào.
     * @param listObjData Mảng hai chiều chứa dữ liệu cần thêm vào TableModel.
     * @param col Mảng chứa các chỉ số cột trong listObjData mà dữ liệu sẽ được lấy từ.
     * @return Mảng hai chiều chứa dữ liệu của TableModel sau khi thêm.
     * @throws IllegalArgumentException Nếu số lượng cột trong tbColumn không khớp với số lượng cột trong col.
     */
    public Object[][] add(int tbColumn[], Object[][] listObjData, int [] col){
        if (tbColumn.length != col.length) {
            throw new IllegalArgumentException("Số lượng cột không khớp");
        }
    
        for (Object[] row : listObjData) {
            Object[] newRow = new Object[this.getColumnCount()];
            for (int j = 0; j < tbColumn.length; j++) {
                newRow[tbColumn[j]] = row[col[j]];
            }
            this.addRow(newRow);
        }
        return this.exportObjData();
    }

    /**
     * Thêm dữ liệu vào các cột tương ứng của TableModel.
     * Mỗi phần tử trong mảng data sẽ được đặt làm giá trị cho toàn bộ cột tương ứng trong TableModel.
     *
     * @param tbColumn Mảng chứa các chỉ số cột cần thêm dữ liệu.
     * @param data Mảng chứa dữ liệu cần thêm vào các cột.
     * @return Mảng hai chiều chứa dữ liệu của TableModel sau khi thêm.
     * @throws IllegalArgumentException Nếu số lượng cột trong tbColumn không khớp với số lượng phần tử trong data.
     */
    public Object[][] add(int[] tbColumn, Object[] data){
        if (tbColumn.length != data.length) {
            throw new IllegalArgumentException("Số lượng cột không khớp");
        }
    
        int rowCount = this.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < tbColumn.length; j++) {
                this.setValueAt(data[j], i, tbColumn[j]);
            }
        }
    
        return this.exportObjData();
    }
    
    
    /**
     * Cập nhật một hàng trong bảng với dữ liệu mới.
     *
     * @param rowIndex chỉ số của hàng cần cập nhật.
     * @param rowData mảng chứa dữ liệu mới cho mỗi cột trong hàng. Số lượng phần tử trong mảng phải khớp với số lượng cột trong bảng.
     * @throws IllegalArgumentException nếu rowIndex nằm ngoài phạm vi của bảng hoặc nếu số lượng phần tử trong rowData không khớp với số lượng cột trong bảng.
     */
    public void sua(int rowIndex, Object[] rowData) {
     
        if (rowIndex < 0 || rowIndex >= this.getRowCount()){
            // unchecked exception, thông báo đối số không hợp lệ
            throw new IllegalArgumentException("Dòng sửa nằm ngoài phạm vi dữ liệu của bảng");
        }
        if (rowData == null || rowData.length != this.getColumnCount()){
            throw new IllegalArgumentException("Dữ liệu thay đổi có số cột (thuộc tính) không khớp. Bảng: " + this.getColumnCount() + " . rowData: " + rowData.length);
        }
        for (int i=0; i < this.getColumnCount(); i++){
            this.setValueAt(rowData[i], rowIndex, i);
        }
    }
    
    
    /**
     * Loại bỏ các hàng được chỉ định từ bảng.
     *
     * @param selRows Mảng chứa các chỉ số của các hàng cần loại bỏ.
     * @return Mảng hai chiều chứa các hàng đã bị loại bỏ. Mỗi hàng là một mảng Object chứa các giá trị của các cột trong hàng đó.
     * @throws IndexOutOfBoundsException Nếu một trong các chỉ số hàng không hợp lệ (ví dụ: nếu nó âm hoặc lớn hơn hoặc bằng số hàng trong bảng).
     */
    public Object[][] removeRow(int [] selRows){
        for (int i = selRows.length-1; i >= 0; i--){
            this.removeRow(selRows[i]);
        }
        return this.exportObjData();
    }
    
    // Lấy 1 số cột chỉ định (nếu remove là false), Lấy dữ liệu đã xoá 1 số cột chỉ định (nếu remove là true)
    public Object[][] filter(int[] columns, boolean remove){
        int rowCount = this.getRowCount();
        Object[][] dsUnmatchCol = new Object[rowCount][this.getColumnCount() - columns.length];
        Object[][] dsMatchCol = new Object[rowCount][columns.length];
        for (int i = 0; i < rowCount; i++){
            int columnUnmatch = 0; int columnMatch = 0;
            System.out.println();
            for (int j = 0; j < this.getColumnCount(); j++){
                boolean find = false;
                for (int num : columns){
                    if (num == j){
                        find = true;
                        break;
                    }
                }
                if (find == true){
                    dsMatchCol[i][columnMatch] = this.getValueAt(i, j);
                    columnMatch++;
                }   
                else {
                    dsUnmatchCol[i][columnUnmatch] = this.getValueAt(i, j);
                    columnUnmatch++;
                }
            }
        }
        if (remove == true){
            return dsUnmatchCol;
        }
        return dsMatchCol;
    }
    
    // Lấy cột, hàng dựa trên chỉ định giữ hoặc xoá
    public Object[][] filter(int[] rows, boolean selRow, int[] columns, boolean selCol){
        ArrayList<Object[]> data = new ArrayList<>();
        for (int i = 0; i < getRowCount(); i++) {
            if ((selRow && Arrays.binarySearch(rows, i) >= 0) || (!selRow && Arrays.binarySearch(rows, i) < 0)) {
                ArrayList<Object> row = new ArrayList<>();
                for (int j = 0; j < getColumnCount(); j++) {
                    if ((selCol && Arrays.binarySearch(columns, j) >= 0) || (!selCol && Arrays.binarySearch(columns, j) < 0)) {
                        row.add(getValueAt(i, j));
                    }
                }
                data.add(row.toArray());
            }
        }
        return data.toArray(new Object[0][]);
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
    
    public double capNhatTongGia(int colDonGia, int colSoLuong, int colTongSub) {
        double total = 0;
        for (int i = 0; i < getRowCount(); i++) {
            Object soLuongObj = getValueAt(i, colSoLuong);
            Object donGiaObj = getValueAt(i, colDonGia);
            if (soLuongObj instanceof Number && donGiaObj instanceof Number) {
                long soLuong = ((Number) soLuongObj).longValue();
                long donGia = ((Number) donGiaObj).longValue();
                double giaItem = soLuong * donGia;
                setValueAt(giaItem, i, colTongSub);
                total += giaItem;
            }
        }
        return total;
    }
    
    public double capNhatTongGia(int colDonGia, int colSoLuong, int colTongSub, int colVAT) {
        double total = 0;
        for (int i = 0; i < getRowCount(); i++) {
            Object soLuongObj = getValueAt(i, colSoLuong);
            Object donGiaObj = getValueAt(i, colDonGia);
            Object vatObj = getValueAt(i, colVAT);
            if (soLuongObj instanceof Number && donGiaObj instanceof Number) {
                long soLuong = ((Number) soLuongObj).longValue();
                long donGia = ((Number) donGiaObj).longValue();
                float vat = ((Number) vatObj).floatValue();
                double giaItem = (long) (soLuong * donGia * (1 + vat / 100));
                setValueAt(giaItem, i, colTongSub);
                total += giaItem;
            }
        }
        return total;
    }
    
    // Tìm kiếm theo 2 trường: "soCT", "tài khoản user". searchType: "contain", "match"
    public Object[][] searchByCriteria(String[] paramSearch, int[] columnSearch, String searchType){
        if (paramSearch[0].isBlank() && paramSearch[1].isBlank()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khoá tìm kiếm"); //// null
            return null;
        }
        int rowCount = this.getRowCount();
        int columnCount = this.getColumnCount();
        //Object[][] result = new Object[rowCount][columnCount];
        Vector<Object> resultVector = new Vector();
        //int mapRow = 0; // số sample kiếm được thoả criteria
        String criteria = "";
        for (int i = 0; i < paramSearch.length; i++){
            criteria = paramSearch[i];
            if (criteria.equals("")){
                criteria = "-1"; // Tránh lỗi khi search ở chế độ "contain" từ khoá
            }
            for (int j = 0; j < rowCount; j++){
                String cellValue = String.valueOf(this.getValueAt(j, columnSearch[i]));
                boolean find = false;
                if (searchType.equals("match") && cellValue.equals(criteria) ||  searchType.equals("contain") && cellValue.contains(criteria)){
                    find = true;
                }
                
                if (find){
                    Vector<Object> rowDataVector = (Vector<Object>) this.getDataVector().elementAt(j);
                    resultVector.add(rowDataVector);
                }
            }
        }
        // Chuyển mảng kết quả từ Vector về Object 2D
        Object[][] result = new Object[resultVector.size()][];
        for (int i = 0; i < resultVector.size(); i++){
            result[i] = ( (Vector<Object>) resultVector.get(i) ).toArray();
        }
        return result;   
    }
    
    /**
     * Đặt dữ liệu cho TableModel từ một mảng hai chiều và cập nhật JTable tương ứng.
     *
     * @param trackObjPO Mảng hai chiều chứa dữ liệu cần đặt cho TableModel.
     * @param column Mảng chứa tên của các cột trong TableModel.
     * @param table Đối tượng JTable cần cập nhật sau khi dữ liệu của TableModel được đặt.
     */
    public void setData(Object[][] trackObjPO, String[] column, JTable table) {
        TableERP tableERPSearch = new TableERP(trackObjPO, column);
        table.setModel(tableERPSearch);
    }
    
    /**
     * Đặt dữ liệu cho TableModel từ một JTable.
     *
     * @param table Đối tượng JTable chứa dữ liệu cần đặt cho TableModel.
     */
    public void setData(JTable table){
        table.setModel(this);
    }
    
    // Xuất mảng Object[] chỉ định
    public Object[] exportObjData(int row){
        Vector<Object> rowDataVector = (Vector<Object>) this.getDataVector().elementAt(row);
        Object[] objData = rowDataVector.toArray(new Object[0]);
        return objData;
    }
    
    
    public Object[][] exportObjData(){
        Object[][] result = new Object[this.getRowCount()][this.getColumnCount()];
        for (int i = 0; i < this.getRowCount(); i++){
            for (int j = 0; j < this.getColumnCount(); j++){
                result[i][j] = this.getValueAt(i, j);
            }
        }
        return result;
    }
    
    // Xuất mảng Object[][] chỉ định
    public Object[][] exportObjData(int[] rows){
        int rowCount = rows.length;
        int colCount = this.getColumnCount();
        Object[][] objDataList = new Object[rowCount][colCount];
        int hang = 0;
        for (int row : rows){
            for (int col = 0; col < colCount; col++){
                objDataList[hang][col] = this.getValueAt(row, col);
            }
            hang++;
        }
        return objDataList;
    }

    /**
     * Đặt hiển thị của một hoặc nhiều cột.
     *
     * @param table Đối tượng JTable cần thay đổi hiển thị cột.
     * @param columnNames Mảng chứa tên của các cột cần đặt hiển thị.
     * @param visible Trạng thái hiển thị mới của các cột.
     */
    public void setColumnVisible(JTable table, String[] columnNames, boolean visible){
        for (String columnName : columnNames) {
            TableColumn column = columnMap.get(columnName);
            if (column == null) {
                column = table.getColumn(columnName);
                columnMap.put(columnName, column);
            }
            if (visible) {
                // Hiện cột
                table.addColumn(column);
            } else {
                // Ẩn cột
                table.removeColumn(column);
            }
        }
    }
    
   
    
}



/*
// Thiết lập kiểu dữ liệu
table.setColumnType(0, Integer.class);
table.setColumnType(1, Long.class);

// Thiết lập cho phép double click chuột để edit
TableERP model = new TableERP(data, column);
model.setAllowMouseListener(true);
model.addMouseListenerIfAllowed(yourTable, new MouseAdapter() {
});
*/