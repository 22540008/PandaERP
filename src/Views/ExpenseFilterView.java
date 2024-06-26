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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author Ricardo
 */
public class ExpenseFilterView extends javax.swing.JPanel {
    private POController poCtl;
    private TableERP tableERP;
    private String[] column;
    private Object[][] data;
    
    private DefaultComboBoxModel<String> comboBoxModel;
    private String[] listCombobox;
   
    /**
     * Creates new form PRView
     */
    public ExpenseFilterView() {
        listCombobox = new String[] {"Người mua hàng", "Mã hàng", "Mã NCC"};
        comboBoxModel = new DefaultComboBoxModel(listCombobox);
        
        initComponents();
        cboxFilter.setModel(comboBoxModel);
        // set fieldyear mặc định là năm hiện tại
        int currentYear = LocalDate.now().getYear();
        fieldYear.setText(String.valueOf(currentYear));
        
    }
    
    public TableERP getTableERP() {
        return tableERP;
    }

    public void setTableERP(TableERP tableERP) {
        this.tableERP = tableERP;
    }

    public JTable getTbFilter() {
        return tbFilter;
    }

    public void setTbGR(JTable tbGR) {
        this.tbFilter = tbGR;
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

    public POController getPoCtl() {
        return poCtl;
    }

    public void setPoCtl(POController poCtl) {
        this.poCtl = poCtl;
    }

    public DefaultComboBoxModel<String> getComboBoxModel() {
        return comboBoxModel;
    }

    public void setComboBoxModel(DefaultComboBoxModel<String> comboBoxModel) {
        this.comboBoxModel = comboBoxModel;
    }

    public String[] getListCombobox() {
        return listCombobox;
    }

    public void setListCombobox(String[] listCombobox) {
        this.listCombobox = listCombobox;
    }

    public JButton getBtnFilter() {
        return btnFilter;
    }

    public void setBtnFilter(JButton btnFilter) {
        this.btnFilter = btnFilter;
    }

    public JComboBox<String> getCboxFilter() {
        return cboxFilter;
    }

    public void setCboxFilter(JComboBox<String> cboxFilter) {
        this.cboxFilter = cboxFilter;
    }

    public JTextField getFieldTotalExpense() {
        return fieldTotalExpense;
    }

    public void setFieldTotalExpense(JTextField fieldTotalExpense) {
        this.fieldTotalExpense = fieldTotalExpense;
    }

    public JTextField getFieldYear() {
        return fieldYear;
    }

    public void setFieldYear(JTextField fieldYear) {
        this.fieldYear = fieldYear;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbFilter = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        cboxFilter = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        fieldYear = new javax.swing.JTextField();
        btnExport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fieldTotalExpense = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Báo cáo chi tiêu theo bộ lọc"));

        tbFilter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Filter", "Thông tin", "Tổng chi", "Tỉ lệ %"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbFilter.setToolTipText("");
        tbFilter.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbFilter.setMinimumSize(null);
        tbFilter.setPreferredSize(null);
        tbFilter.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbFilter);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm"));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Lọc theo");

        btnFilter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Fatcow-Farm-Fresh-Filter.24.png"))); // NOI18N
        btnFilter.setText("Bắt đầu lọc");

        cboxFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Năm");

        btnExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dakirby309-Simply-Styled-Microsoft-Excel-2013.24.png"))); // NOI18N
        btnExport.setText("Xuất XLS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExport)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnFilter)
                    .addComponent(cboxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(fieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Gartoon-Team-Gartoon-Misc-Stock-Sum.24.png"))); // NOI18N
        jLabel2.setText("Tổng chi tiêu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldTotalExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fieldTotalExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void btnFilterActionListener(ActionListener listener){
        btnFilter.addActionListener(listener);
    }
    
    public void loadData() {
        tableERP = new TableERP(data, column);
        tableERP.setColumnType(2, Double.class);
        tbFilter.setModel(tableERP);
        tbFilter.getColumnModel().getColumn(2).setCellRenderer(new CurrencyRenderer()); // format VND
    }
    
    public void btnExportActionListener(ActionListener listener){
        btnExport.addActionListener(listener);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnFilter;
    private javax.swing.JComboBox<String> cboxFilter;
    private javax.swing.JTextField fieldTotalExpense;
    private javax.swing.JTextField fieldYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbFilter;
    // End of variables declaration//GEN-END:variables

    

 

    
}
