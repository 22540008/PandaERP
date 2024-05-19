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
public class GRView extends javax.swing.JPanel {
    private POController poCtl;
    private TableERP tableERP;
    private String[] column;
    private Object[][] data;
    
//    private TableERP tableItem;
//    private String[] columnItem;
//    private Object[][] dataItem;
    
    private TableERP tableGRdraft;
    private String[] columnGRdraft;
    private Object[][] dataGRdraft;
    
//    private TableERP tablePOupdate;
//    private String[] columPOupdate;
//    private Object[][] dataPOupdate;
    
    private TableERP tablePO;
    private String[] columnPO;
    private Object[][] dataPO;
    
//    private TableERP tableVendor;
//    private String[] columnVendor;
//    private Object[][] dataVendor;
    


    /**
     * Creates new form PRView
     */
    public GRView() {
        
        initComponents();
        btnEdit.setVisible(false); // ẩn đi nút Sửa GR, không cho sử dụng

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
        return tbGR;
    }

    public void setTbPO(JTable tbPO) {
        this.tbGR = tbPO;
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
    
    public JDateChooser getDate_add() {
        return date_add;
    }

    public void setDate_add(JDateChooser date_add) {
        this.date_add = date_add;
    }

    public JTextField getFieldSearchSoCT() {
        return fieldSearchSoCT;
    }

    public void setFieldSearchSoCT(JTextField fieldSearchSoCT) {
        this.fieldSearchSoCT = fieldSearchSoCT;
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


    public JTextField getFieldUser_add() {
        return fieldUser_add;
    }

    public void setFieldUser_add(JTextField fieldUser_add) {
        this.fieldUser_add = fieldUser_add;
    }

    public JTable getTbGRdraft() {
        return tbGRdraft;
    }

    public void setTbPOdraft(JTable tbPOdraft) {
        this.tbGRdraft = tbPOdraft;
    }

//    public TableERP getTableItem() {
//        return tableItem;
//    }
//
//    public void setTableItem(TableERP tableItem) {
//        this.tableItem = tableItem;
//    }
//
//    public String[] getColumnItem() {
//        return columnItem;
//    }
//
//    public void setColumnItem(String[] columnItem) {
//        this.columnItem = columnItem;
//    }
//
//    public Object[][] getDataItem() {
//        return dataItem;
//    }
//
//    public void setDataItem(Object[][] dataItem) {
//        this.dataItem = dataItem;
//    }

    public TableERP getTableGRdraft() {
        return tableGRdraft;
    }

    public void setTableGRdraft(TableERP tableGRdraft) {
        this.tableGRdraft = tableGRdraft;
    }

    public String[] getColumnGRdraft() {
        return columnGRdraft;
    }

    public void setColumnGRdraft(String[] columnPOdraft) {
        this.columnGRdraft = columnPOdraft;
    }

    public Object[][] getDataGRdraft() {
        return dataGRdraft;
    }

    public void setDataGRdraft(Object[][] dataPOdraft) {
        this.dataGRdraft = dataPOdraft;
    }

    public JDialog getDialogUpdate() {
        return DialogUpdate;
    }

    public void setDialogUpdate(JDialog DialogUpdate) {
        this.DialogUpdate = DialogUpdate;
    }

//    public TableERP getTablePOupdate() {
//        return tablePOupdate;
//    }
//
//    public void setTablePOupdate(TableERP tablePOupdate) {
//        this.tablePOupdate = tablePOupdate;
//    }
//
//    public Object[][] getDataPOupdate() {
//        return dataPOupdate;
//    }
//
//    public void setDataPOupdate(Object[][] dataPOupdate) {
//        this.dataPOupdate = dataPOupdate;
//    }

    public JTable getTbPOupdate() {
        return tbPOupdate;
    }

    public void setTbPOupdate(JTable tbPOupdate) {
        this.tbPOupdate = tbPOupdate;
    }

    public JDateChooser getDate_update() {
        return date_update;
    }

    public void setDate_update(JDateChooser date_update) {
        this.date_update = date_update;
    }

//    public String[] getColumPOupdate() {
//        return columPOupdate;
//    }
//
//    public void setColumPOupdate(String[] columPOupdate) {
//        this.columPOupdate = columPOupdate;
//    }

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

    
    
    public JDialog getDialogTimPO() {
        return DialogTimPO;
    }

    public TableERP getTablePO() {
        return tablePO;
    }

    public void setTablePO(TableERP tablePO) {
        this.tablePO = tablePO;
    }

    public String[] getColumnPO() {
        return columnPO;
    }

    public void setColumnPO(String[] columnPO) {
        this.columnPO = columnPO;
    }

    public Object[][] getDataPO() {
        return dataPO;
    }

    public void setDataPO(Object[][] dataPO) {
        this.dataPO = dataPO;
    }

    public JTable getTbPR() {
        return tbPO;
    }

    public JTextField getFieldSearchSoCTPO() {
        return fieldSearchSoCTPO;
    }
    
    



//    public TableERP getTableVendor() {
//        return tableVendor;
//    }
//
//    public void setTableVendor(TableERP tableVendor) {
//        this.tableVendor = tableVendor;
//    }
//
//    public String[] getColumnVendor() {
//        return columnVendor;
//    }
//
//    public void setColumnVendor(String[] columnVendor) {
//        this.columnVendor = columnVendor;
//    }
//
//    public Object[] getDataVendor() {
//        return dataVendor;
//    }
//
//    public void setDataVendor(Object[][] dataVendor) {
//        this.dataVendor = dataVendor;
//    }



    public JTextField getFieldTongPOupdate() {
        return fieldTongPOupdate;
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
        btnTimPO_add = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGRdraft = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnRemove_add = new javax.swing.JButton();
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
        tbPOupdate = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnTinhTongPR1 = new javax.swing.JButton();
        fieldTongPOupdate = new javax.swing.JTextField();
        btnClose = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        fieldVAT_update = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        fieldTenNCC_update = new javax.swing.JTextField();
        fieldMaNCC_update = new javax.swing.JTextField();
        btnTimNCC_update = new javax.swing.JButton();
        DialogTimPO = new javax.swing.JDialog();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbPO = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        fieldSearchSoCTPO = new javax.swing.JTextField();
        btnLoadPO = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        btnSelectPO = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGR = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        fieldSearchUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fieldSearchSoCT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnLoad = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnTotalItemPrice = new javax.swing.JButton();
        fieldTotalPrice = new javax.swing.JTextField();

        DialogAdd.setModal(true);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("TẠO PHIẾU NHẬN HÀNG (GR)");

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

        btnTimPO_add.setText("Tìm PO");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnTimPO_add)
                .addContainerGap(522, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTimPO_add)
                .addGap(0, 56, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Bản thảo PO"));

        tbGRdraft.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PR", "PR Line", "Mã hàng", "Tên hàng", "ĐVT", "Mã NCC", "Tên NCC", "Đơn giá", "Số lượng", "VAT (%)", "Tổng giá", "SL chưa nhận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbGRdraft);

        btnCreate.setText("Tạo GR");

        btnRemove_add.setText("Xoá Item");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
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
                    .addComponent(btnRemove_add))
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

        DialogUpdate.setModal(true);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setText("SỬA ĐƠN HÀNG (PO)");

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

        tbPOupdate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PR", "PR Line", "Mã hàng", "Tên hàng", "DVT", "Mã NCC", "Tên NCC", "Giá Est", "Số lượng", "VAT%", "Tổng giá", "SL chưa nhận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbPOupdate.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(tbPOupdate);

        btnUpdate.setText("Cập nhật");

        btnTinhTongPR1.setText("Tính tổng");

        fieldTongPOupdate.setEditable(false);

        btnClose.setText("Đóng (Inactive PO)");

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
                        .addComponent(fieldTongPOupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTinhTongPR1)
                    .addComponent(btnUpdate)
                    .addComponent(fieldTongPOupdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose)))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Sửa nhà cung cấp"));

        fieldVAT_update.setEditable(false);

        jLabel9.setText("Tên NCC");

        jLabel10.setText("VAT%");

        jLabel23.setText("Mã NCC");

        fieldTenNCC_update.setEditable(false);

        fieldMaNCC_update.setEditable(false);

        btnTimNCC_update.setText("Sửa NCC");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(btnTimNCC_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldMaNCC_update, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 213, Short.MAX_VALUE))
                    .addComponent(fieldTenNCC_update, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(fieldVAT_update, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldVAT_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldTenNCC_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldMaNCC_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimNCC_update))
                .addGap(5, 5, 5))
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
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        DialogUpdateLayout.setVerticalGroup(
            DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        DialogTimPO.setModal(true);

        tbPO.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPO.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPO.setMinimumSize(new java.awt.Dimension(400, 80));
        tbPO.setPreferredSize(new java.awt.Dimension(1080, 600));
        tbPO.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane6.setViewportView(tbPO);
        if (tbPO.getColumnModel().getColumnCount() > 0) {
            tbPO.getColumnModel().getColumn(0).setPreferredWidth(60);
            tbPO.getColumnModel().getColumn(1).setMinWidth(60);
            tbPO.getColumnModel().getColumn(1).setPreferredWidth(60);
            tbPO.getColumnModel().getColumn(1).setMaxWidth(60);
            tbPO.getColumnModel().getColumn(2).setPreferredWidth(15);
            tbPO.getColumnModel().getColumn(3).setPreferredWidth(15);
            tbPO.getColumnModel().getColumn(4).setPreferredWidth(10);
            tbPO.getColumnModel().getColumn(5).setPreferredWidth(10);
            tbPO.getColumnModel().getColumn(6).setPreferredWidth(10);
            tbPO.getColumnModel().getColumn(7).setMinWidth(100);
            tbPO.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbPO.getColumnModel().getColumn(8).setPreferredWidth(10);
            tbPO.getColumnModel().getColumn(9).setPreferredWidth(10);
            tbPO.getColumnModel().getColumn(10).setPreferredWidth(20);
            tbPO.getColumnModel().getColumn(11).setPreferredWidth(20);
        }

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm"));

        jLabel21.setText("Số PO");

        btnLoadPO.setText("Tìm PO");
        btnLoadPO.setToolTipText("");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchSoCTPO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLoadPO)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(fieldSearchSoCTPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadPO))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnSelectPO.setText("Thêm");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSelectPO))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSelectPO))
        );

        javax.swing.GroupLayout DialogTimPOLayout = new javax.swing.GroupLayout(DialogTimPO.getContentPane());
        DialogTimPO.getContentPane().setLayout(DialogTimPOLayout);
        DialogTimPOLayout.setHorizontalGroup(
            DialogTimPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogTimPOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogTimPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)))
        );
        DialogTimPOLayout.setVerticalGroup(
            DialogTimPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogTimPOLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBorder(javax.swing.BorderFactory.createTitledBorder("Phiếu nhận hàng (GR)"));

        tbGR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Số PO", "PO line", "Số PR", "PR line", "Mã hàng", "Tên hàng", "ĐVT", "Mã NCC", "Tên NCC", "SL chưa nhận", "SL nhận", "Lưu kho", "Nhận lần cuối"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbGR.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbGR.setMinimumSize(new java.awt.Dimension(400, 80));
        tbGR.setPreferredSize(new java.awt.Dimension(1080, 400));
        tbGR.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbGR);

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

        btnEdit.setText("Sửa");

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
                .addComponent(btnEdit)
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
                        .addComponent(btnEdit)
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
    private javax.swing.JDialog DialogTimPO;
    private javax.swing.JDialog DialogUpdate;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnLoadPO;
    private javax.swing.JButton btnRemove_add;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSelectPO;
    private javax.swing.JButton btnTimNCC_update;
    private javax.swing.JButton btnTimPO_add;
    private javax.swing.JButton btnTinhTongPR1;
    private javax.swing.JButton btnTotalItemPrice;
    private javax.swing.JButton btnUpdate;
    private com.toedter.calendar.JDateChooser date_add;
    private com.toedter.calendar.JDateChooser date_update;
    private javax.swing.JTextField fieldMaNCC_update;
    private javax.swing.JTextField fieldSearchSoCT;
    private javax.swing.JTextField fieldSearchSoCTPO;
    private javax.swing.JTextField fieldSearchUser;
    private javax.swing.JTextField fieldSoCT_add;
    private javax.swing.JTextField fieldSoCT_update;
    private javax.swing.JTextField fieldTenNCC_update;
    private javax.swing.JTextField fieldTongPOupdate;
    private javax.swing.JTextField fieldTotalPrice;
    private javax.swing.JTextField fieldUser_add;
    private javax.swing.JTextField fieldUser_update;
    private javax.swing.JTextField fieldVAT_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tbGR;
    private javax.swing.JTable tbGRdraft;
    private javax.swing.JTable tbPO;
    private javax.swing.JTable tbPOupdate;
    // End of variables declaration//GEN-END:variables

    public void btnLoadActionListener(ActionListener listener){
        btnLoad.addActionListener(listener);
    }
    
    public void loadData() {
        tableERP = new TableERP(data, column);
        tableERP.setColumnType(0, Integer.class); // Số CT
        tableERP.setColumnType(5, Integer.class); // ItemLine
        tableERP.setColumnType(6, Integer.class); // số PO
        tableERP.setColumnType(7, Integer.class); // Po line
        tableERP.setColumnType(8, Integer.class); // số PR
        tableERP.setColumnType(9, Integer.class); // Pr line
        tableERP.setColumnType(10, Integer.class); // Mã hàng
        tableERP.setColumnType(13, Integer.class); // Mã NCC
        tableERP.setColumnType(15, Integer.class); // Số lượng chưa nhận
        tableERP.setColumnType(16, Integer.class); // Số lượng nhận
        tableERP.setColumnType(17, Boolean.class); // Lưu kho
        tableERP.setColumnType(18, Boolean.class); // Nhận lần cuối
        tbGR.setModel(tableERP);
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
    
    public void initTableGRdraft() {
        tableGRdraft = new TableERP(dataGRdraft, columnGRdraft);
        tableGRdraft.setEditable(true);
        tableGRdraft.addEditableColumn(16); // cho phép Edit số lượng nhận
        tableGRdraft.addEditableColumn(17); // lưu kho
        tableGRdraft.addEditableColumn(18); // nhân lần cuối
        tableGRdraft.setColumnType(0, Integer.class); // Số CT
        tableGRdraft.setColumnType(5, Integer.class); // ItemLine
        tableGRdraft.setColumnType(6, Integer.class); // số PO
        tableGRdraft.setColumnType(7, Integer.class); // Po line
        tableGRdraft.setColumnType(8, Integer.class); // số PR
        tableGRdraft.setColumnType(9, Integer.class); // Pr line
        tableGRdraft.setColumnType(10, Integer.class); // Mã hàng
        tableGRdraft.setColumnType(13, Integer.class); // Mã NCC
        tableGRdraft.setColumnType(15, Integer.class); // Số lượng chưa nhận
        tableGRdraft.setColumnType(16, Integer.class); // Số lượng nhận
        tableGRdraft.setColumnType(17, Boolean.class); // Lưu kho
        tableGRdraft.setColumnType(18, Boolean.class); // Nhận lần cuối
        tbGRdraft.setModel(tableGRdraft);
        //tableGRdraft.setColumnVisible(tbGRdraft, new String[]{"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Mã NCC", "Tên NCC"}, false);
    }
    
    public void btnAddActionListener(ActionListener listener){
        btnAdd.addActionListener(listener);
    }
    
    public void btnDiagTimPOAddActionListener(ActionListener listener){
        btnTimPO_add.addActionListener(listener);   
    }
    
    public void loadDataPO() {
        tablePO = new TableERP(dataPO, columnPO);
        tbPO.setModel(tablePO);
    }
    
//    public String[] getSearchParamPO() {
//        String[] searchParams = new String[1];
//        searchParams[0] = fieldSearchSoCTPO.getText();
//        return searchParams;
//    }
    
    public void btnLoadPOActionListener(ActionListener listener){
        btnLoadPO.addActionListener(listener);
    }
    
//    public void btnSearchPOActionListener(ActionListener listener){
//        btnSearchPO.addActionListener(listener);
//    }
    
    public void btnSelectPORActionListener(ActionListener listener){
        btnSelectPO.addActionListener(listener);
    }
    
    
    public void btnRemoveAddActionListener(ActionListener listener){
        btnRemove_add.addActionListener(listener);
    }
    
    
    
    public void btnCreateActionListener(ActionListener listener){
        btnCreate.addActionListener(listener);
    }
    
    
//    public void initTablePOUpdate() {
//        tablePOupdate = new TableERP(dataPOupdate, columPOupdate);
//        tablePOupdate.setEditable(true);
//        tablePOupdate.addEditableColumn(13); // Giá
//        tablePOupdate.addEditableColumn(14); // Số lượng
//        tablePOupdate.addEditableColumn(15); // VAT%
//        // thiết lập các giá trị đặc biệt và cột xét để không cho edit
//        Set<Object> specialValues = new HashSet<>();
//        specialValues.add("Đã đóng");
//        specialValues.add("Đã được xử lý");
//        tablePOupdate.setSpecialValues(specialValues, 0);
//        // Thiết lập kiểu giá trị cho các cột
//        tablePOupdate.setColumnType(6, Integer.class); // Số PR
//        tablePOupdate.setColumnType(7, Integer.class); // PR line
//        tablePOupdate.setColumnType(8, Integer.class); // Mã hàng
//        tablePOupdate.setColumnType(10, Integer.class); // Mã vendor
//        tablePOupdate.setColumnType(13, Long.class); // Đơn giá
//        tablePOupdate.setColumnType(14, Integer.class); // Số lượng
//        tablePOupdate.setColumnType(15, Float.class); // VAT
//        tablePOupdate.setColumnType(16, Double.class); // Tổng giá item
//        tablePOupdate.setColumnType(17, Integer.class); // sl chờ nhận
//        tbPOupdate.setModel(tablePOupdate);
//        tbPOupdate.getColumnModel().getColumn(13).setCellRenderer(new CurrencyRenderer()); // VND format cột đơn giá
//        tbPOupdate.getColumnModel().getColumn(16).setCellRenderer(new CurrencyRenderer()); // cột tổng giá item
//        //tablePOdraft.setColumnVisible(tbPOdraft, new String[]{"Số CT", "Người tạo", "Ngày tạo", "Ngày sửa", "Trạng thái", "ItemLine", "Mã NCC", "Tên NCC"}, false);
//    }
    
//    public void btnDiagTimNCCupdateActionListener(ActionListener listener){
//        btnTimNCC_update.addActionListener(listener);
//    }
    
    
//    public PurchaseOrder getUpdatePOinfo(int row) {
//        PurchaseOrder po = new PurchaseOrder();
//        po.setSoCT((int) tablePOupdate.getValueAt(row, 0));
//        po.setNgaySua(date_update.getDate());
//        po.setItemLine((int) tablePOupdate.getValueAt(row, 5));
//        String maNCCstr = fieldMaNCC_update.getText();
//        if (maNCCstr.isBlank()){
//            po.setMaNCC((int)tablePOupdate.getValueAt(row, 11));
//        }
//        else {
//            po.setMaNCC(Integer.parseInt(maNCCstr));
//        }
//        po.setGia((long)tablePOupdate.getValueAt(row, 13));
//        po.setSoLuong((int)tablePOupdate.getValueAt(row, 14));
//        po.setVat((float)tablePOupdate.getValueAt(row, 15));
//        po.setGiaItem((double)tablePOupdate.getValueAt(row, 16));
//        
//        System.out.println(po);
//        return po;
//    }
    
 
    
    public void btnUpdateActionListener(ActionListener listener){
        btnUpdate.addActionListener(listener);
    }

    
    
    public void btnEditActionListener(ActionListener listener){
        btnEdit.addActionListener(listener);
    }

    public void updateTbPOdraft() {
        tbGRdraft.setModel(tableGRdraft);
    }

    public void updateTbPO() {
        tbGR.setModel(tableERP);
    }
    
    public void btnCloseActionListener(ActionListener listener){
        btnClose.addActionListener(listener);
    }
    
    public void btnDeleteActionListener(ActionListener listener){
        btnDelete.addActionListener(listener);
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

//    public void updateTbPOupdate() {
//        tbPOupdate.setModel(tablePOupdate);
//    }


    


    
}
