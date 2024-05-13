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
        return fieldDVT_add;
    }

    public void setFieldDVT_add(JTextField fieldDVT_add) {
        this.fieldDVT_add = fieldDVT_add;
    }

    public JTextField getFieldDonGia_add() {
        return fieldDonGia_add;
    }

    public void setFieldDonGia_add(JTextField fieldDonGia_add) {
        this.fieldDonGia_add = fieldDonGia_add;
    }

    public JTextField getFieldMaHang_add() {
        return fieldMaHang_add;
    }

    public void setFieldMaHang_add(JTextField fieldMaHang_add) {
        this.fieldMaHang_add = fieldMaHang_add;
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
        return fieldTenHang_add;
    }

    public void setFieldTenHang_add(JTextField fieldTenHang_add) {
        this.fieldTenHang_add = fieldTenHang_add;
    }

    public JTextField getFieldTongPR() {
        return fieldTongPR;
    }

    public void setFieldTongPO(JTextField fieldTongPO) {
        this.fieldTongPR = fieldTongPO;
    }

    public JTextField getFieldUser_add() {
        return fieldUser_add;
    }

    public void setFieldUser_add(JTextField fieldUser_add) {
        this.fieldUser_add = fieldUser_add;
    }

    public JSpinField getSpinFieldSL_add() {
        return spinFieldSL_add;
    }

    public void setSpinFieldSL_add(JSpinField spinFieldSL_add) {
        this.spinFieldSL_add = spinFieldSL_add;
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
        fieldDVT_add = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnTimPR_add = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        spinFieldSL_add = new com.toedter.components.JSpinField();
        btnAdd_add = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fieldTenHang_add = new javax.swing.JTextField();
        fieldMaHang_add = new javax.swing.JTextField();
        fieldDonGia_add = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnChonNCC_add = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPOdraft = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnDelete_add = new javax.swing.JButton();
        btnTinhTongPO = new javax.swing.JButton();
        fieldTongPR = new javax.swing.JTextField();
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
        btnSelect_add = new javax.swing.JButton();
        btnTotalItemPrice2 = new javax.swing.JButton();
        fieldTotalPrice2 = new javax.swing.JTextField();
        btnLoadPR = new javax.swing.JButton();
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Item"));

        fieldDVT_add.setEditable(false);

        jLabel7.setText("Tên hàng");

        btnTimPR_add.setText("Tìm PR");

        jLabel8.setText("ĐVT");

        spinFieldSL_add.setMinimum(1);

        btnAdd_add.setText("Thêm");

        jLabel6.setText("Mã hàng");

        jLabel9.setText("Đơn giá (VND)");

        fieldTenHang_add.setEditable(false);

        fieldMaHang_add.setEditable(false);

        jLabel10.setText("Số lượng");

        btnChonNCC_add.setText("Chọn NCC");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTimPR_add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(fieldMaHang_add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(fieldTenHang_add, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(fieldDVT_add, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldDonGia_add, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(spinFieldSL_add, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnChonNCC_add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd_add)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10))
                    .addComponent(btnTimPR_add))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinFieldSL_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldMaHang_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldTenHang_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldDVT_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldDonGia_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd_add)
                    .addComponent(btnChonNCC_add))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Bản thảo PO"));

        tbPOdraft.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Số PR", "PR Line", "Mã hàng", "Tên hàng", "Mã NCC", "Tên NCC", "ĐVT", "Đơn giá", "Số lượng", "VAT", "Tổng giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbPOdraft);

        btnCreate.setText("Tạo PO");

        btnDelete_add.setText("Xoá Item");

        btnTinhTongPO.setText("Tính tổng");

        fieldTongPR.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTinhTongPO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldTongPR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete_add)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreate)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTinhTongPO)
                    .addComponent(btnDelete_add)
                    .addComponent(btnCreate)
                    .addComponent(fieldTongPR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(8, 8, 8)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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

        btnSelect_add.setText("Thêm");

        btnTotalItemPrice2.setText("Tính tổng giá");
        btnTotalItemPrice2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        fieldTotalPrice2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        btnLoadPR.setText("Load");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTotalItemPrice2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldTotalPrice2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSelect_add)
                .addGap(18, 18, 18)
                .addComponent(btnLoadPR))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldTotalPrice2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSelect_add)
                        .addComponent(btnLoadPR))
                    .addComponent(btnTotalItemPrice2)))
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
    private javax.swing.JDialog DialogUpdate;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddItemInfo;
    private javax.swing.JButton btnAdd_add;
    private javax.swing.JButton btnChonNCC_add;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete_add;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnLoadItem_add;
    private javax.swing.JButton btnLoadPR;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchItem;
    private javax.swing.JButton btnSearchPR;
    private javax.swing.JButton btnSelect_add;
    private javax.swing.JButton btnTimPR_add;
    private javax.swing.JButton btnTinhTongPO;
    private javax.swing.JButton btnTinhTongPR1;
    private javax.swing.JButton btnTotalItemPrice;
    private javax.swing.JButton btnTotalItemPrice2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate_update;
    private com.toedter.calendar.JDateChooser date_add;
    private com.toedter.calendar.JDateChooser date_update;
    private javax.swing.JTextField fieldDVT_add;
    private javax.swing.JTextField fieldDonGia_add;
    private javax.swing.JTextField fieldMaHang_add;
    private javax.swing.JTextField fieldSearchMaHang;
    private javax.swing.JTextField fieldSearchSoCT;
    private javax.swing.JTextField fieldSearchSoCTPR;
    private javax.swing.JTextField fieldSearchTenHang;
    private javax.swing.JTextField fieldSearchUser;
    private javax.swing.JTextField fieldSearchUserPR;
    private javax.swing.JTextField fieldSoCT_add;
    private javax.swing.JTextField fieldSoCT_update;
    private javax.swing.JTextField fieldTenHang_add;
    private javax.swing.JTextField fieldTongPR;
    private javax.swing.JTextField fieldTongPR1;
    private javax.swing.JTextField fieldTotalPrice;
    private javax.swing.JTextField fieldTotalPrice2;
    private javax.swing.JTextField fieldUser_add;
    private javax.swing.JTextField fieldUser_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JScrollPane jScrollPane6;
    private com.toedter.components.JSpinField spinFieldSL_add;
    private javax.swing.JTable tbItem;
    private javax.swing.JTable tbPO;
    private javax.swing.JTable tbPOdraft;
    private javax.swing.JTable tbPR;
    private javax.swing.JTable tbPRupdate;
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
    
    
    
    public void btnSelectAddActionListener(ActionListener listener){
        btnSelect_add.addActionListener(listener);
    }
    
//    public String[] getSearchParamItem() {
//        String[] searchParams = new String[2];
//        searchParams[0] = fieldSearchMaHang.getText();
//        searchParams[1] = fieldSearchTenHang.getText();
//        return searchParams;
//    }
    

    
    

    
    public void btnLoadItemActionListener(ActionListener listener){
        btnLoadItem_add.addActionListener(listener);
    }
    
    public void btnSearchItemActionListener(ActionListener listener){
        btnSearchItem.addActionListener(listener);
    }
    
    public void btnAddItemInfoActionListener(ActionListener listener){
        btnAddItemInfo.addActionListener(listener);
    }

    public void getItemInfo(){
        int selRow = tbItem.getSelectedRow();
        fieldMaHang_add.setText(String.valueOf(tbItem.getValueAt(selRow, 0)));
        fieldTenHang_add.setText((String) tbItem.getValueAt(selRow, 1));
        fieldDVT_add.setText((String) tbItem.getValueAt(selRow, 2));
        //fieldDonGia_add.setText(CurrencyUtils.format(tbItem.getValueAt(selRow, 3)));
        fieldDonGia_add.setText(String.valueOf(tbItem.getValueAt(selRow, 3)));

    }
    
    public void btnAddItem_addActionListener(ActionListener listener){
        btnAdd_add.addActionListener(listener);
    }

    public void loadDataItem() {
        tableItem = new TableERP(dataItem, columnItem);
        for (int i = 0; i < columnItem.length; i++)
            System.out.print(columnItem[i]);
        tbItem.setModel(tableItem);
        tbItem.getColumnModel().getColumn(3).setCellRenderer(new CurrencyRenderer()); // VND format
    }

    public void initTablePOdraft() {
        tablePOdraft = new TableERP(dataPOdraft, columnPOdraft);
        tablePOdraft.setEditable(true);
        tablePOdraft.addEditableColumn(7); // Edit đơn giá
        tablePOdraft.addEditableColumn(8); // số lượng
        tablePOdraft.addEditableColumn(9); // VAT
        tablePOdraft.setColumnType(0, Integer.class); // Số PR
        tablePOdraft.setColumnType(1, Integer.class); // PR line
        tablePOdraft.setColumnType(2, Integer.class); // Mã hàng
        tablePOdraft.setColumnType(4, Integer.class); // Mã vendor
        tablePOdraft.setColumnType(7, Long.class); // Đơn giá
        tablePOdraft.setColumnType(8, Integer.class); // Số lượng
        tablePOdraft.setColumnType(9, Float.class); // VAT
        tablePOdraft.setColumnType(10, Double.class); // Tổng giá item
        tbPOdraft.setModel(tablePOdraft);
        tbPOdraft.getColumnModel().getColumn(7).setCellRenderer(new CurrencyRenderer()); // VND format cột đơn giá
        tbPOdraft.getColumnModel().getColumn(10).setCellRenderer(new CurrencyRenderer()); // cột tổng giá item
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
        addItem = new Object[]{Integer.valueOf(fieldMaHang_add.getText()),
                fieldTenHang_add.getText(),
                fieldDVT_add.getText(),
                //CurrencyUtils.parseToLong(fieldDonGia_add.getText()),
                Long.valueOf(fieldDonGia_add.getText()),
                spinFieldSL_add.getValue()
                        };
        return addItem;
    }
    
    public void btnDelete_addActionListener(ActionListener listener){
        btnDelete_add.addActionListener(listener);
    }
    
    public void btnTinhTongPRActionListener(ActionListener listener){
        btnTinhTongPO.addActionListener(listener);
    }
    
    public void btnCreateActionListener(ActionListener listener){
        btnCreate.addActionListener(listener);
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
