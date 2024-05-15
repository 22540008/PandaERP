/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Controllers.POController;
import Utility.CurrencyRenderer;
import Controllers.PRController;
import Models.Item;
import Models.PurchaseItem;
import Models.PurchaseOrder;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Utility.CurrencyUtils;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ricardo
 */
public class POView extends javax.swing.JPanel {
    private POController poCtl;
    private TableERP tableERP;
    private String[] column;
    private Object[][] data;
    
    private TableERP tableItem;
    private String[] columnItem;
    private Object[][] dataItem;
    
    private TableERP tablePOdraft;
    private String[] columnPOdraft;
    private Object[][] dataPOdraft;
    
    private TableERP tablePOupdate;
    private String[] columPOupdate;
    private Object[][] dataPOupdate;
    
    private TableERP tablePR;
    private String[] columnPR;
    private Object[][] dataPR;
    
    private TableERP tableVendor;
    private String[] columnVendor;
    private Object[][] dataVendor;
    


    /**
     * Creates new form PRView
     */
    public POView() {
        
        
        initComponents();
        // Thiết lập mỗi lần chỉ Edit được 1 trường Search
        fieldSearchSoCT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                disableField();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                disableField();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                disableField();
            }

            public void disableField() {
                if (fieldSearchSoCT.getText().trim().isEmpty()) {
                    fieldSearchUser.setEnabled(true);
                } else {
                    fieldSearchUser.setEnabled(false);
                }
            }
        });
        fieldSearchUser.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                disableField();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                disableField();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                disableField();
            }

            public void disableField() {
                if (fieldSearchUser.getText().trim().isEmpty()) {
                    fieldSearchSoCT.setEnabled(true);
                } else {
                    fieldSearchSoCT.setEnabled(false);
                }
            }
        });
    }

    
    
    public TableERP getTableERP() {
        return tableERP;
    }

    public void setTableERP(TableERP tableERP) {
        this.tableERP = tableERP;
    }

    public JTable getTbPO() {
        return tbPO;
    }

    public void setTbPO(JTable tbPO) {
        this.tbPO = tbPO;
    }
    
    public void setColumn(String[] columns) {
        this.column = columns;
    }

    public void setData(Object[][] dsObjPO) {
        this.data = dsObjPO;
    }
    
    public void setController(POController poCtl){
        this.poCtl = poCtl;
    }

    public JTextField getFieldTotalPrice() {
        return fieldTotalPrice;
    }

    public JTextField getFieldUser() {
        return fieldUser_add;
    }

    public POController getPoCtl() {
        return poCtl;
    }

    public void setPoCtl(POController poCtl) {
        this.poCtl = poCtl;
    }

    public JDialog getDialogAdd() {
        return DialogAdd;
    }

    public JDialog getDialogTimItem() {
        return DialogTimItem;
    }

    public void setDialogTimItem(JDialog DialogTimItem) {
        this.DialogTimItem = DialogTimItem;
    }

    public JDateChooser getDate_add() {
        return date_add;
    }

    public void setDate_add(JDateChooser date_add) {
        this.date_add = date_add;
    }

    public JTextField getFieldDVT_add() {
        return fieldVAT_add;
    }

    public void setFieldDVT_add(JTextField fieldDVT_add) {
        this.fieldVAT_add = fieldDVT_add;
    }


    public JTextField getFieldMaHang_add() {
        return fieldMaNCC_add;
    }

    public void setFieldMaHang_add(JTextField fieldMaHang_add) {
        this.fieldMaNCC_add = fieldMaHang_add;
    }

    public JTextField getFieldSearchMaHang() {
        return fieldSearchMaHang;
    }

    public void setFieldSearchMaHang(JTextField fieldSearchMaHang) {
        this.fieldSearchMaHang = fieldSearchMaHang;
    }

    public JTextField getFieldSearchSoCT() {
        return fieldSearchSoCT;
    }

    public void setFieldSearchSoCT(JTextField fieldSearchSoCT) {
        this.fieldSearchSoCT = fieldSearchSoCT;
    }

    public JTextField getFieldSearchTenHang() {
        return fieldSearchTenHang;
    }

    public void setFieldSearchTenHang(JTextField fieldSearchTenHang) {
        this.fieldSearchTenHang = fieldSearchTenHang;
    }

    public JTextField getFieldSearchUser() {
        return fieldSearchUser;
    }

    public void setFieldSearchUser(JTextField fieldSearchUser) {
        this.fieldSearchUser = fieldSearchUser;
    }

    public JTextField getFieldSoCT_add() {
        return fieldSoCT_add;
    }

    public void setFieldSoCT_add(JTextField fieldSoCT_add) {
        this.fieldSoCT_add = fieldSoCT_add;
    }

    public JTextField getFieldTenHang_add() {
        return fieldTenNCC_add;
    }

    public void setFieldTenHang_add(JTextField fieldTenHang_add) {
        this.fieldTenNCC_add = fieldTenHang_add;
    }

    public void setFieldTongPO(JTextField fieldTongPO) {
        this.fieldTongPOdraft = fieldTongPO;
    }

    public JTextField getFieldUser_add() {
        return fieldUser_add;
    }

    public void setFieldUser_add(JTextField fieldUser_add) {
        this.fieldUser_add = fieldUser_add;
    }

    public JTable getTbItem() {
        return tbItem;
    }

    public void setTbItem(JTable tbItem) {
        this.tbItem = tbItem;
    }

    public JTable getTbPOdraft() {
        return tbPOdraft;
    }

    public void setTbPOdraft(JTable tbPOdraft) {
        this.tbPOdraft = tbPOdraft;
    }

    public TableERP getTableItem() {
        return tableItem;
    }

    public void setTableItem(TableERP tableItem) {
        this.tableItem = tableItem;
    }

    public String[] getColumnItem() {
        return columnItem;
    }

    public void setColumnItem(String[] columnItem) {
        this.columnItem = columnItem;
    }

    public Object[][] getDataItem() {
        return dataItem;
    }

    public void setDataItem(Object[][] dataItem) {
        this.dataItem = dataItem;
    }

    public TableERP getTablePOdraft() {
        return tablePOdraft;
    }

    public void setTablePOdraft(TableERP tablePOdraft) {
        this.tablePOdraft = tablePOdraft;
    }

    public String[] getColumnPOdraft() {
        return columnPOdraft;
    }

    public void setColumnPOdraft(String[] columnPOdraft) {
        this.columnPOdraft = columnPOdraft;
    }

    public Object[][] getDataPOdraft() {
        return dataPOdraft;
    }

    public void setDataPOdraft(Object[][] dataPOdraft) {
        this.dataPOdraft = dataPOdraft;
    }

    public JDialog getDialogUpdate() {
        return DialogUpdate;
    }

    public void setDialogUpdate(JDialog DialogUpdate) {
        this.DialogUpdate = DialogUpdate;
    }

    public TableERP getTablePOupdate() {
        return tablePOupdate;
    }

    public void setTablePOupdate(TableERP tablePOupdate) {
        this.tablePOupdate = tablePOupdate;
    }

    public Object[][] getDataPOupdate() {
        return dataPOupdate;
    }

    public void setDataPOupdate(Object[][] dataPOupdate) {
        this.dataPOupdate = dataPOupdate;
    }

    public JTable getTbPRupdate() {
        return tbPRupdate;
    }

    public void setTbPRupdate(JTable tbPRupdate) {
        this.tbPRupdate = tbPRupdate;
    }

    public JDateChooser getDate_update() {
        return date_update;
    }

    public void setDate_update(JDateChooser date_update) {
        this.date_update = date_update;
    }

    public String[] getColumPOupdate() {
        return columPOupdate;
    }

    public void setColumPOupdate(String[] columPOupdate) {
        this.columPOupdate = columPOupdate;
    }

    public JTextField getFieldSoCT_update() {
        return fieldSoCT_update;
    }

    public void setFieldSoCT_update(JTextField fieldSoCT_update) {
        this.fieldSoCT_update = fieldSoCT_update;
    }

    public JTextField getFieldUser_update() {
        return fieldUser_update;
    }

    public void setFieldUser_update(JTextField fieldUser_update) {
        this.fieldUser_update = fieldUser_update;
    }

    
    
    public JDialog getDialogTimPR() {
        return DialogTimPR;
    }

    public TableERP getTablePR() {
        return tablePR;
    }

    public void setTablePR(TableERP tablePR) {
        this.tablePR = tablePR;
    }

    public String[] getColumnPR() {
        return columnPR;
    }

    public void setColumnPR(String[] columnPR) {
        this.columnPR = columnPR;
    }

    public Object[][] getDataPR() {
        return dataPR;
    }

    public void setDataPR(Object[][] dataPR) {
        this.dataPR = dataPR;
    }

    public JTable getTbPR() {
        return tbPR;
    }

    public JDialog getDialogTimVendor() {
        return DialogTimVendor;
    }

    public void setDialogTimVendor(JDialog DialogTimVendor) {
        this.DialogTimVendor = DialogTimVendor;
    }

    public JTable getTbVendor() {
        return tbVendor;
    }

    public void setTbVendor(JTable tbVendor) {
        this.tbVendor = tbVendor;
    }

    public TableERP getTableVendor() {
        return tableVendor;
    }

    public void setTableVendor(TableERP tableVendor) {
        this.tableVendor = tableVendor;
    }

    public String[] getColumnVendor() {
        return columnVendor;
    }

    public void setColumnVendor(String[] columnVendor) {
        this.columnVendor = columnVendor;
    }

    public Object[] getDataVendor() {
        return dataVendor;
    }

    public void setDataVendor(Object[][] dataVendor) {
        this.dataVendor = dataVendor;
    }

    public JTextField getFieldMaNCC_add() {
        return fieldMaNCC_add;
    }

    public void setFieldMaNCC_add(JTextField fieldMaNCC_add) {
        this.fieldMaNCC_add = fieldMaNCC_add;
    }

    public JTextField getFieldTenNCC_add() {
        return fieldTenNCC_add;
    }

    public void setFieldTenNCC_add(JTextField fieldTenNCC_add) {
        this.fieldTenNCC_add = fieldTenNCC_add;
    }

    public JTextField getFieldVAT_add() {
        return fieldVAT_add;
    }

    public void setFieldVAT_add(JTextField fieldVAT_add) {
        this.fieldVAT_add = fieldVAT_add;
    }

    public JTextField getFieldTongPOdraft() {
        return fieldTongPOdraft;
    }

    public void setFieldTongPOdraft(JTextField fieldTongPOdraft) {
        this.fieldTongPOdraft = fieldTongPOdraft;
    }
    
    
    
    
    

   

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogAdd = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fieldUser_add = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fieldSoCT_add = new javax.swing.JTextField();
        date_add = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        fieldVAT_add = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnTimPR_add = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fieldTenNCC_add = new javax.swing.JTextField();
        fieldMaNCC_add = new javax.swing.JTextField();
        btnTimNCC_add = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPOdraft = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnRemove_add = new javax.swing.JButton();
        btnTinhTongPOdraft = new javax.swing.JButton();
        fieldTongPOdraft = new javax.swing.JTextField();
        DialogTimItem = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        fieldSearchTenHang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnSearchItem = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        fieldSearchMaHang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnAddItemInfo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbItem = new javax.swing.JTable();
        btnLoadItem_add = new javax.swing.JButton();
        DialogUpdate = new javax.swing.JDialog();
        jLabel15 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        fieldUser_update = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        fieldSoCT_update = new javax.swing.JTextField();
        date_update = new com.toedter.calendar.JDateChooser();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbPRupdate = new javax.swing.JTable();
        btnUpdate_update = new javax.swing.JButton();
        btnTinhTongPR1 = new javax.swing.JButton();
        fieldTongPR1 = new javax.swing.JTextField();
        btnClose = new javax.swing.JButton();
        DialogTimPR = new javax.swing.JDialog();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbPR = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        fieldSearchUserPR = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnSearchPR = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        fieldSearchSoCTPR = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        btnSelectPR = new javax.swing.JButton();
        btnLoadPR = new javax.swing.JButton();
        DialogTimVendor = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        fieldSearchTenNCC = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnSearchVendor = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        fieldSearchMaNCC = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbVendor = new javax.swing.JTable();
        btnLoadVendor = new javax.swing.JButton();
        btnSelectVendor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPO = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        fieldSearchUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fieldSearchSoCT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnLoad = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnTotalItemPrice = new javax.swing.JButton();
        fieldTotalPrice = new javax.swing.JTextField();

        DialogAdd.setModal(true);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("TẠO ĐƠN HÀNG (PO)");

        jLabel3.setText("Ngày");

        jLabel4.setText("Tài khoản:");

        fieldUser_add.setEditable(false);

        jLabel5.setText("Số PR");

        fieldSoCT_add.setEditable(false);

        date_add.setDateFormatString("dd-MM-yyyy");
        date_add.setPreferredSize(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldUser_add, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date_add, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(fieldSoCT_add))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldUser_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(fieldSoCT_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm"));

        fieldVAT_add.setEditable(false);

        jLabel7.setText("Tên NCC");

        btnTimPR_add.setText("Tìm PR");

        jLabel8.setText("VAT%");

        jLabel6.setText("Mã NCC");

        fieldTenNCC_add.setEditable(false);

        fieldMaNCC_add.setEditable(false);

        btnTimNCC_add.setText("Chọn NCC");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTimPR_add)
                        .addGap(15, 15, 15))
                    .addComponent(btnTimNCC_add, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldMaNCC_add, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 203, Short.MAX_VALUE))
                    .addComponent(fieldTenNCC_add, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(fieldVAT_add, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTimPR_add)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 29, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldVAT_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldTenNCC_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldMaNCC_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimNCC_add))
                        .addGap(5, 5, 5))))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Bản thảo PO"));

        tbPOdraft.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PR", "PR Line", "Mã hàng", "Tên hàng", "Mã NCC", "Tên NCC", "ĐVT", "Đơn giá", "Số lượng", "VAT (%)", "Tổng giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbPOdraft);

        btnCreate.setText("Tạo PO");

        btnRemove_add.setText("Xoá Item");

        btnTinhTongPOdraft.setText("Tính tổng");

        fieldTongPOdraft.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTinhTongPOdraft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldTongPOdraft, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemove_add)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreate)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnRemove_add)
                    .addComponent(fieldTongPOdraft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTinhTongPOdraft))
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogAddLayout = new javax.swing.GroupLayout(DialogAdd.getContentPane());
        DialogAdd.getContentPane().setLayout(DialogAddLayout);
        DialogAddLayout.setHorizontalGroup(
            DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogAddLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DialogAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        DialogAddLayout.setVerticalGroup(
            DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        DialogTimItem.setModal(true);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm"));

        jLabel12.setText("Mã hàng");

        btnSearchItem.setText("Tìm kiếm");

        jLabel13.setText("Tên hàng");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchMaHang)
                .addGap(26, 26, 26)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchTenHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearchItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(fieldSearchMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchItem)
                    .addComponent(jLabel13)
                    .addComponent(fieldSearchTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText("TÌM ITEM");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Item"));

        btnAddItemInfo.setText("Chọn");

        tbItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hàng", "Tên hàng", "Đơn vị", "Đơn giá (VND)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbItem.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbItem.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tbItem);

        btnLoadItem_add.setText("Load");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddItemInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLoadItem_add))
                    .addComponent(jScrollPane3)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoadItem_add)
                    .addComponent(btnAddItemInfo))
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogTimItemLayout = new javax.swing.GroupLayout(DialogTimItem.getContentPane());
        DialogTimItem.getContentPane().setLayout(DialogTimItemLayout);
        DialogTimItemLayout.setHorizontalGroup(
            DialogTimItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogTimItemLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(248, 248, 248))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogTimItemLayout.setVerticalGroup(
            DialogTimItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DialogTimItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        DialogUpdate.setModal(true);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setText("SỬA ĐỀ NGHỊ MUA HÀNG (PR)");

        jLabel16.setText("Ngày sửa");

        jLabel17.setText("Tài khoản:");

        fieldUser_update.setEditable(false);

        jLabel18.setText("Số PR");

        fieldSoCT_update.setEditable(false);

        date_update.setDateFormatString("dd-MM-yyyy");
        date_update.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldUser_update, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldSoCT_update))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(fieldUser_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(fieldSoCT_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Sửa PR"));

        tbPRupdate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Trạng thái", "ItemLine", "Mã hàng", "Tên hàng", "DVT", "Giá Est", "Số lượng", "Tổng giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbPRupdate);

        btnUpdate_update.setText("Cập nhật");

        btnTinhTongPR1.setText("Tính tổng");

        fieldTongPR1.setEditable(false);

        btnClose.setText("Đóng (Inactive PR)");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnTinhTongPR1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldTongPR1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate_update)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTinhTongPR1)
                    .addComponent(btnUpdate_update)
                    .addComponent(fieldTongPR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose)))
        );

        javax.swing.GroupLayout DialogUpdateLayout = new javax.swing.GroupLayout(DialogUpdate.getContentPane());
        DialogUpdate.getContentPane().setLayout(DialogUpdateLayout);
        DialogUpdateLayout.setHorizontalGroup(
            DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogUpdateLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DialogUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        DialogUpdateLayout.setVerticalGroup(
            DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        DialogTimPR.setModal(true);

        tbPR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Mã hàng", "Tên hàng", "ĐVT", "Số lượng", "Giá Est (VND)", "Tổng giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbPR.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPR.setMinimumSize(new java.awt.Dimension(400, 80));
        tbPR.setPreferredSize(new java.awt.Dimension(1080, 600));
        tbPR.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane6.setViewportView(tbPR);
        if (tbPR.getColumnModel().getColumnCount() > 0) {
            tbPR.getColumnModel().getColumn(0).setPreferredWidth(60);
            tbPR.getColumnModel().getColumn(1).setMinWidth(60);
            tbPR.getColumnModel().getColumn(1).setPreferredWidth(60);
            tbPR.getColumnModel().getColumn(1).setMaxWidth(60);
            tbPR.getColumnModel().getColumn(2).setPreferredWidth(15);
            tbPR.getColumnModel().getColumn(3).setPreferredWidth(15);
            tbPR.getColumnModel().getColumn(4).setPreferredWidth(10);
            tbPR.getColumnModel().getColumn(5).setPreferredWidth(10);
            tbPR.getColumnModel().getColumn(6).setPreferredWidth(10);
            tbPR.getColumnModel().getColumn(7).setMinWidth(100);
            tbPR.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbPR.getColumnModel().getColumn(8).setPreferredWidth(10);
            tbPR.getColumnModel().getColumn(9).setPreferredWidth(10);
            tbPR.getColumnModel().getColumn(10).setPreferredWidth(20);
            tbPR.getColumnModel().getColumn(11).setPreferredWidth(20);
        }

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm"));

        jLabel21.setText("Số CT");

        btnSearchPR.setText("Tìm kiếm");

        jLabel22.setText("Người tạo");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchSoCTPR, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchUserPR, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(btnSearchPR, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(fieldSearchSoCTPR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchPR)
                    .addComponent(jLabel22)
                    .addComponent(fieldSearchUserPR))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnSelectPR.setText("Thêm");

        btnLoadPR.setText("Load");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLoadPR)
                .addGap(18, 18, 18)
                .addComponent(btnSelectPR))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelectPR)
                    .addComponent(btnLoadPR)))
        );

        javax.swing.GroupLayout DialogTimPRLayout = new javax.swing.GroupLayout(DialogTimPR.getContentPane());
        DialogTimPR.getContentPane().setLayout(DialogTimPRLayout);
        DialogTimPRLayout.setHorizontalGroup(
            DialogTimPRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogTimPRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogTimPRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)))
        );
        DialogTimPRLayout.setVerticalGroup(
            DialogTimPRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogTimPRLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        DialogTimVendor.setModal(true);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm"));

        jLabel19.setText("Mã NCC");

        btnSearchVendor.setText("Tìm kiếm");

        jLabel20.setText("Tên NCC");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchMaNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchTenNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearchVendor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(fieldSearchMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchVendor)
                    .addComponent(jLabel20)
                    .addComponent(fieldSearchTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Ds NCC"));

        tbVendor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã NCC", "Tên NCC", "Địa chỉ", "MST", "VAT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbVendor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbVendor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(tbVendor);

        btnLoadVendor.setText("Load");

        btnSelectVendor.setText("Chọn");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLoadVendor)
                        .addGap(18, 18, 18)
                        .addComponent(btnSelectVendor))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoadVendor)
                    .addComponent(btnSelectVendor)))
        );

        javax.swing.GroupLayout DialogTimVendorLayout = new javax.swing.GroupLayout(DialogTimVendor.getContentPane());
        DialogTimVendor.getContentPane().setLayout(DialogTimVendorLayout);
        DialogTimVendorLayout.setHorizontalGroup(
            DialogTimVendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogTimVendorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogTimVendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        DialogTimVendorLayout.setVerticalGroup(
            DialogTimVendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogTimVendorLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn hàng (PO)"));

        tbPO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PR", "PR line", "Mã hàng", "Tên hàng", "Mã NCC", "Tên NCC", "ĐVT", "Giá Est (VND)", "Số lượng", "vat", "Tổng giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbPO.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPO.setMinimumSize(new java.awt.Dimension(400, 80));
        tbPO.setPreferredSize(new java.awt.Dimension(1080, 400));
        tbPO.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbPO);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm"));

        jLabel1.setText("Số CT");

        btnSearch.setText("Tìm kiếm");

        jLabel2.setText("Người tạo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchSoCT, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchUser, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldSearchSoCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(jLabel2)
                    .addComponent(fieldSearchUser))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnLoad.setText("Load");

        btnDelete.setText("Xoá");

        btnUpdate.setText("Sửa");

        btnAdd.setText("Thêm");

        btnTotalItemPrice.setText("Tính tổng giá");
        btnTotalItemPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        fieldTotalPrice.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTotalItemPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnLoad)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldTotalPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLoad)
                        .addComponent(btnDelete)
                        .addComponent(btnUpdate)
                        .addComponent(btnAdd)
                        .addComponent(btnTotalItemPrice))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogAdd;
    private javax.swing.JDialog DialogTimItem;
    private javax.swing.JDialog DialogTimPR;
    private javax.swing.JDialog DialogTimVendor;
    private javax.swing.JDialog DialogUpdate;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddItemInfo;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnLoadItem_add;
    private javax.swing.JButton btnLoadPR;
    private javax.swing.JButton btnLoadVendor;
    private javax.swing.JButton btnRemove_add;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchItem;
    private javax.swing.JButton btnSearchPR;
    private javax.swing.JButton btnSearchVendor;
    private javax.swing.JButton btnSelectPR;
    private javax.swing.JButton btnSelectVendor;
    private javax.swing.JButton btnTimNCC_add;
    private javax.swing.JButton btnTimPR_add;
    private javax.swing.JButton btnTinhTongPOdraft;
    private javax.swing.JButton btnTinhTongPR1;
    private javax.swing.JButton btnTotalItemPrice;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate_update;
    private com.toedter.calendar.JDateChooser date_add;
    private com.toedter.calendar.JDateChooser date_update;
    private javax.swing.JTextField fieldMaNCC_add;
    private javax.swing.JTextField fieldSearchMaHang;
    private javax.swing.JTextField fieldSearchMaNCC;
    private javax.swing.JTextField fieldSearchSoCT;
    private javax.swing.JTextField fieldSearchSoCTPR;
    private javax.swing.JTextField fieldSearchTenHang;
    private javax.swing.JTextField fieldSearchTenNCC;
    private javax.swing.JTextField fieldSearchUser;
    private javax.swing.JTextField fieldSearchUserPR;
    private javax.swing.JTextField fieldSoCT_add;
    private javax.swing.JTextField fieldSoCT_update;
    private javax.swing.JTextField fieldTenNCC_add;
    private javax.swing.JTextField fieldTongPOdraft;
    private javax.swing.JTextField fieldTongPR1;
    private javax.swing.JTextField fieldTotalPrice;
    private javax.swing.JTextField fieldUser_add;
    private javax.swing.JTextField fieldUser_update;
    private javax.swing.JTextField fieldVAT_add;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tbItem;
    private javax.swing.JTable tbPO;
    private javax.swing.JTable tbPOdraft;
    private javax.swing.JTable tbPR;
    private javax.swing.JTable tbPRupdate;
    private javax.swing.JTable tbVendor;
    // End of variables declaration//GEN-END:variables

    public void btnLoadActionListener(ActionListener listener){
        btnLoad.addActionListener(listener);
    }
    
    public void loadData() {
        tableERP = new TableERP(data, column);
        tableERP.setColumnType(0, Integer.class);
        tableERP.setColumnType(5, Integer.class);
        tableERP.setColumnType(6, Integer.class);
        tableERP.setColumnType(7, Integer.class);
        tableERP.setColumnType(8, Integer.class);
        tableERP.setColumnType(11, Integer.class);
        tableERP.setColumnType(13, Long.class);
        tableERP.setColumnType(14, Integer.class);
        tableERP.setColumnType(15, Float.class);
        tableERP.setColumnType(16, Double.class);
        tbPO.setModel(tableERP);
        
        tbPO.getColumnModel().getColumn(13).setCellRenderer(new CurrencyRenderer()); // format VND
        tbPO.getColumnModel().getColumn(16).setCellRenderer(new CurrencyRenderer());
        
    } 
    
    public String[] getSearchParams() {
        String[] searchParams = new String[2];
        searchParams[0] = fieldSearchSoCT.getText();
        searchParams[1] = fieldSearchUser.getText();
        return searchParams;
    }
    
    public void btnSearchActionListener(ActionListener listener){
        btnSearch.addActionListener(listener);
    }
    
    public void btnCalItemPriceActionListener(ActionListener listener){
        btnTotalItemPrice.addActionListener(listener);
    }
    
    public void initTablePOdraft() {
        tablePOdraft = new TableERP(dataPOdraft, columnPOdraft);
        tablePOdraft.setEditable(true);
        tablePOdraft.addEditableColumn(13); // Edit đơn giá
        tablePOdraft.addEditableColumn(14); // số lượng
        tablePOdraft.addEditableColumn(15); // VAT
        tablePOdraft.setColumnType(6, Integer.class); // Số PR
        tablePOdraft.setColumnType(7, Integer.class); // PR line
        tablePOdraft.setColumnType(8, Integer.class); // Mã hàng
        tablePOdraft.setColumnType(10, Integer.class); // Mã vendor
        tablePOdraft.setColumnType(13, Long.class); // Đơn giá
        tablePOdraft.setColumnType(14, Integer.class); // Số lượng
        tablePOdraft.setColumnType(15, Float.class); // VAT
        tablePOdraft.setColumnType(16, Double.class); // Tổng giá item
        tbPOdraft.setModel(tablePOdraft);
        tbPOdraft.getColumnModel().getColumn(13).setCellRenderer(new CurrencyRenderer()); // VND format cột đơn giá
        tbPOdraft.getColumnModel().getColumn(16).setCellRenderer(new CurrencyRenderer()); // cột tổng giá item
        //tablePOdraft.setColumnVisible(tbPOdraft, new String[]{"Mã NCC", "Tên NCC"}, false);
    }
    
    public void btnAddActionListener(ActionListener listener){
        btnAdd.addActionListener(listener);
    }
    
    public void btnDiagTimPRaddActionListener(ActionListener listener){
        btnTimPR_add.addActionListener(listener);   
    }
    
    public void loadDataPR() {
        tablePR = new TableERP(dataPR, columnPR);
        tbPR.setModel(tablePR);
    }
    
    public String[] getSearchParamPR() {
        String[] searchParams = new String[2];
        searchParams[0] = fieldSearchSoCTPR.getText();
        searchParams[1] = fieldSearchUserPR.getText();
        return searchParams;
    }
    
    public void btnLoadPRActionListener(ActionListener listener){
        btnLoadPR.addActionListener(listener);
    }
    
    public void btnSearchPRActionListener(ActionListener listener){
        btnSearchPR.addActionListener(listener);
    }
    
    public void btnSelectPRActionListener(ActionListener listener){
        btnSelectPR.addActionListener(listener);
    }
    
    public void btnDiagTimNCCaddActionListener(ActionListener listener){
        btnTimNCC_add.addActionListener(listener);
    }
    
    public void loadDataVendor() {
        tableVendor = new TableERP(dataVendor, columnVendor);
        tableVendor.setColumnType(0, Integer.class);
        tableVendor.setColumnType(4, Float.class);
        tbVendor.setModel(tableVendor);
    }

    public void btnLoadVendorActionListener(ActionListener listener){
        btnLoadVendor.addActionListener(listener);
    }
    
    public String[] getSearchParamVendor() {
        String[] searchParams = new String[2];
        searchParams[0] = fieldSearchMaNCC.getText();
        searchParams[1] = fieldSearchTenNCC.getText();
        return searchParams;
    }
    
    public void btnSearchVendorActionListener(ActionListener listener){
        btnSearchVendor.addActionListener(listener);
    }
    
    public void getVendorInfo(){ 
        int selRow = tbVendor.getSelectedRow();
        String maNCCStr = String.valueOf(tableVendor.getValueAt(selRow, 0));
        String tenNCCStr = String.valueOf(tableVendor.getValueAt(selRow, 1));
        String vatStr = String.valueOf(tableVendor.getValueAt(selRow, 4));
        fieldMaNCC_add.setText(maNCCStr);
        fieldTenNCC_add.setText(tenNCCStr);
        fieldVAT_add.setText(vatStr);
    }
    
    public void btnSelVendorActionListener(ActionListener listener){
        btnSelectVendor.addActionListener(listener);
    }
    
    public void btnRemove_addActionListener(ActionListener listener){
        btnRemove_add.addActionListener(listener);
    }
    
    
    public void btnTinhTongPOdraftActionListener(ActionListener listener){
        btnTinhTongPOdraft.addActionListener(listener);
    }
    
    public void btnCreateActionListener(ActionListener listener){
        btnCreate.addActionListener(listener);
    }
    
    


    
    public void initTablePRUpdate() {
        tablePOupdate = new TableERP(dataPOupdate, columPOupdate);
        tablePOupdate.setEditable(true);
        //tablePRupdate.addEditableColumn(3);
        //tablePRupdate.addEditableColumn(4);
        tablePOupdate.addEditableColumn(5);
        tablePOupdate.addEditableColumn(6);
        // thiết lập các giá trị đặc biệt và cột xét để không cho edit
        Set<Object> specialValues = new HashSet<>();
        specialValues.add("Đã đóng");
        specialValues.add("Đã được xử lý");
        tablePOupdate.setSpecialValues(specialValues, 0);
        // Thiết lập kiểu giá trị cho các cột
        tablePOupdate.setColumnType(1, Integer.class);
        tablePOupdate.setColumnType(5, Long.class);
        tablePOupdate.setColumnType(6, Integer.class);
        tbPRupdate.setModel(tablePOupdate);
        tbPRupdate.getColumnModel().getColumn(5).setCellRenderer(new CurrencyRenderer()); // VND format
        tbPRupdate.getColumnModel().getColumn(7).setCellRenderer(new CurrencyRenderer());
    }

    // Chèm item mới vào bảng PRdraft
    public Object[] addNewItem() {
        Object[] addItem;
        addItem = new Object[]{Integer.valueOf(fieldMaNCC_add.getText()),
                fieldTenNCC_add.getText(),
                fieldVAT_add.getText(),
                        };
        return addItem;
    }
    

    public void btnDeleteActionListener(ActionListener listener){
        btnDelete.addActionListener(listener);
    }
    
    public void btnUpdateActionListener(ActionListener listener){
        btnUpdate.addActionListener(listener);
    }
    
    public void btnCloseActionListener(ActionListener listener){
        btnClose.addActionListener(listener);
    }
    
    public void DialogUpdateActionListener(ActionListener listener){
        btnUpdate_update.addActionListener(listener);
    }

    public void updateTbPRdraft() {
        tbPOdraft.setModel(tablePOdraft);
    }

    public void updateTbPR() {
        tbPO.setModel(tableERP);
    }
    
    

//    public PurchaseOrder getUpdatePRinfo(int row) {
//        PurchaseOrder po = new PurchaseOrder();
//        
//        System.out.println("row: " + row);
//        System.out.println((String) tablePOupdate.getValueAt(row, 0));
//        po.setItemLine((int) tablePOupdate.getValueAt(row, 1));
//        Item item = new Item();
//        item.setMaHang((int) tablePOupdate.getValueAt(row, 2));
//        item.setTenHang((String) tablePOupdate.getValueAt(row, 3));
//        item.setDvt((String) tablePOupdate.getValueAt(row, 4));
//        pr.setDonGia((long) tablePOupdate.getValueAt(row, 5));
//        pr.setItem(item);
//        pr.setSoLuong((int) tablePOupdate.getValueAt(row, 6));
//        pr.setGiaItem((double) tablePOupdate.getValueAt(row, 7));
//        
//        System.out.println("tablePRupdate" + tablePOupdate.getValueAt(row, 6));
//        System.out.println("tbPRupdate" + tablePOupdate.getValueAt(row, 6));
//        System.out.println("PR sau lấy thông tin từ bảng: " + pr);
//
//        return pr;
//    }

    public void setDataSearch(Object[][] trackObjPO, String[] column, JTable table) {
        TableERP tableERPSearch = new TableERP(trackObjPO, column);
        table.setModel(tableERPSearch);
    }

    


    
}
