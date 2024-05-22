/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Controllers.UserController;
import Models.User;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Ricardo
 */
public class UserView extends javax.swing.JPanel {
    UserController userCtl;
    Object[][] data;
    String[] column;
    TableERP tableERP;
    int selectedUpdatedRow;

    /**
     * Creates new form UserView
     */
    public UserView() {
        initComponents();
        
        
        
        // Sử dụng DocumentListener theo dõi hoạt động sửa thuộc tính/thêm/xoá nội dung, nếu rỗng thì cho phép trường còn lại edit được, nếu không rỗng thì khoá.
        fieldMaNV.getDocument().addDocumentListener(new DocumentListener() {
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
                if (fieldMaNV.getText().trim().isEmpty()) {
                    fieldTenTK.setEnabled(true);
                } else {
                    fieldTenTK.setEnabled(false);
                }
            }
        });
        fieldTenTK.getDocument().addDocumentListener(new DocumentListener() {
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
                if (fieldTenTK.getText().trim().isEmpty()) {
                    fieldMaNV.setEnabled(true);
                } else {
                    fieldMaNV.setEnabled(false);
                }
            }
        });

    }

    public void setColumn(String[] column) {
        this.column = column;
    }
    
    
    public void setData(Object[][] data) {
        this.data = data;
    }
   

    public TableERP getTableERP() {
        return tableERP;
    }
    

    public JTable getTbUser() {
        return tbUser;
    }

    public JDialog getDialogAdd() {
        return DialogAdd;
    }
    
    

    
    public int getSelectedUpdatedRow() {
        return selectedUpdatedRow;
    }

    public JTextField getFieldChucVu_add() {
        return fieldChucVu_add;
    }

    public void setFieldChucVu_add(JTextField fieldChucVu_add) {
        this.fieldChucVu_add = fieldChucVu_add;
    }

    public JTextField getFieldDiaChi_add() {
        return fieldDiaChi_add;
    }

    public void setFieldDiaChi_add(JTextField fieldDiaChi_add) {
        this.fieldDiaChi_add = fieldDiaChi_add;
    }

    public JTextField getFieldHo_add() {
        return fieldHo_add;
    }

    public void setFieldHo_add(JTextField fieldHo_add) {
        this.fieldHo_add = fieldHo_add;
    }

    public JTextField getFieldMaNV() {
        return fieldMaNV;
    }

    public void setFieldMaNV(JTextField fieldMaNV) {
        this.fieldMaNV = fieldMaNV;
    }

    public JTextField getFieldMaNV_add() {
        return fieldMaNV_add;
    }

    public void setFieldMaNV_add(JTextField fieldMaNV_add) {
        this.fieldMaNV_add = fieldMaNV_add;
    }

    public JPasswordField getFieldMatKhau_add() {
        return fieldMatKhau_add;
    }

    public void setFieldMatKhau_add(JPasswordField fieldMatKhau_add) {
        this.fieldMatKhau_add = fieldMatKhau_add;
    }

    public JTextField getFieldPhongBan_add() {
        return fieldPhongBan_add;
    }

    public void setFieldPhongBan_add(JTextField fieldPhongBan_add) {
        this.fieldPhongBan_add = fieldPhongBan_add;
    }

    public JTextField getFieldSoDT_add() {
        return fieldSoDT_add;
    }

    public void setFieldSoDT_add(JTextField fieldSoDT_add) {
        this.fieldSoDT_add = fieldSoDT_add;
    }

    public JTextField getFieldTenTK() {
        return fieldTenTK;
    }

    public void setFieldTenTK(JTextField fieldTenTK) {
        this.fieldTenTK = fieldTenTK;
    }

    public JTextField getFieldTenTK_add() {
        return fieldTenTK_add;
    }

    public void setFieldTenTK_add(JTextField fieldTenTK_add) {
        this.fieldTenTK_add = fieldTenTK_add;
    }

    public JTextField getFieldTen_add() {
        return fieldTen_add;
    }

    public void setFieldTen_add(JTextField fieldTen_add) {
        this.fieldTen_add = fieldTen_add;
    }

    public JDialog getDialogUpdate() {
        return DialogUpdate;
    }

    public void setDialogUpdate(JDialog DialogUpdate) {
        this.DialogUpdate = DialogUpdate;
    }

    public JTextField getFieldChucVu_update() {
        return fieldChucVu_update;
    }

    public void setFieldChucVu_update(JTextField fieldChucVu_update) {
        this.fieldChucVu_update = fieldChucVu_update;
    }

    public JTextField getFieldDiaChi_update() {
        return fieldDiaChi_update;
    }

    public void setFieldDiaChi_update(JTextField fieldDiaChi_update) {
        this.fieldDiaChi_update = fieldDiaChi_update;
    }

    public JTextField getFieldHo_update() {
        return fieldHo_update;
    }

    public void setFieldHo_update(JTextField fieldHo_update) {
        this.fieldHo_update = fieldHo_update;
    }

    public JTextField getFieldMaNV_update() {
        return fieldMaNV_update;
    }

    public void setFieldMaNV_update(JTextField fieldMaNV_update) {
        this.fieldMaNV_update = fieldMaNV_update;
    }

    public JPasswordField getFieldMatKhau_update() {
        return fieldMatKhau_update;
    }

    public void setFieldMatKhau_update(JPasswordField fieldMatKhau_update) {
        this.fieldMatKhau_update = fieldMatKhau_update;
    }

    public JTextField getFieldPhongBan_update() {
        return fieldPhongBan_update;
    }

    public void setFieldPhongBan_update(JTextField fieldPhongBan_update) {
        this.fieldPhongBan_update = fieldPhongBan_update;
    }

    public JTextField getFieldSoDT_update() {
        return fieldSoDT_update;
    }

    public void setFieldSoDT_update(JTextField fieldSoDT_update) {
        this.fieldSoDT_update = fieldSoDT_update;
    }

    public JTextField getFieldTenTK_update() {
        return fieldTenTK_update;
    }

    public void setFieldTenTK_update(JTextField fieldTenTK_update) {
        this.fieldTenTK_update = fieldTenTK_update;
    }

    public JTextField getFieldTen_update() {
        return fieldTen_update;
    }

    public void setFieldTen_update(JTextField fieldTen_update) {
        this.fieldTen_update = fieldTen_update;
    }

    public JList<String> getListSystemRole_add() {
        return listSystemRole_add;
    }

    public void setListSystemRole_add(JList<String> listSystemRole_add) {
        this.listSystemRole_add = listSystemRole_add;
    }

    public JList<String> getListSystemRole_update() {
        return listSystemRole_update;
    }

    public void setListSystemRole_update(JList<String> listSystemRole_update) {
        this.listSystemRole_update = listSystemRole_update;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fieldMaNV_add = new javax.swing.JTextField();
        fieldTenTK_add = new javax.swing.JTextField();
        fieldMatKhau_add = new javax.swing.JPasswordField();
        fieldHo_add = new javax.swing.JTextField();
        fieldTen_add = new javax.swing.JTextField();
        fieldChucVu_add = new javax.swing.JTextField();
        fieldPhongBan_add = new javax.swing.JTextField();
        fieldDiaChi_add = new javax.swing.JTextField();
        fieldSoDT_add = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        listSystemRole_add = new javax.swing.JList<>();
        btnCreate = new javax.swing.JButton();
        DialogUpdate = new javax.swing.JDialog();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        fieldMaNV_update = new javax.swing.JTextField();
        fieldTenTK_update = new javax.swing.JTextField();
        fieldMatKhau_update = new javax.swing.JPasswordField();
        fieldHo_update = new javax.swing.JTextField();
        fieldTen_update = new javax.swing.JTextField();
        fieldChucVu_update = new javax.swing.JTextField();
        fieldPhongBan_update = new javax.swing.JTextField();
        fieldDiaChi_update = new javax.swing.JTextField();
        fieldSoDT_update = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        listSystemRole_update = new javax.swing.JList<>();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUser = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        fieldTenTK = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fieldMaNV = new javax.swing.JTextField();

        DialogAdd.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogAdd.setTitle("Dialog Thêm User");
        DialogAdd.setModal(true);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Mã nhân viên");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tên tài khoản");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Mật khẩu");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Họ");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Tên");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Chức vụ");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Phòng ban");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Địa chỉ");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Số điện thoại");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("System Roles");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("TẠO MỚI NGƯỜI DÙNG");

        fieldMatKhau_add.setToolTipText("");

        listSystemRole_add.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "admin", "requester", "purchaser" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listSystemRole_add.setToolTipText("");
        listSystemRole_add.setName(""); // NOI18N
        listSystemRole_add.setSelectedIndex(1);
        jScrollPane3.setViewportView(listSystemRole_add);

        btnCreate.setText("Tạo");

        javax.swing.GroupLayout DialogAddLayout = new javax.swing.GroupLayout(DialogAdd.getContentPane());
        DialogAdd.getContentPane().setLayout(DialogAddLayout);
        DialogAddLayout.setHorizontalGroup(
            DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogAddLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DialogAddLayout.createSequentialGroup()
                        .addGap(0, 220, Short.MAX_VALUE)
                        .addComponent(btnCreate))
                    .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fieldSoDT_add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                        .addComponent(fieldDiaChi_add, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldPhongBan_add, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldChucVu_add, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldTen_add, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldHo_add, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldMatKhau_add, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldTenTK_add, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldMaNV_add, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogAddLayout.setVerticalGroup(
            DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogAddLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldMaNV_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldTenTK_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldMatKhau_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldHo_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fieldTen_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(fieldChucVu_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fieldPhongBan_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(fieldDiaChi_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(fieldSoDT_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnCreate)
                .addContainerGap())
        );

        DialogUpdate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogUpdate.setTitle("Dialog Sửa User");
        DialogUpdate.setModal(true);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Mã nhân viên");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Tên tài khoản");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Mật khẩu");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Họ");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Tên");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Chức vụ");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Phòng ban");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Địa chỉ");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Số điện thoại");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("System Roles");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("CẬP NHẬT THÔNG TIN NGƯỜI DÙNG");

        fieldMatKhau_update.setToolTipText("");

        listSystemRole_update.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "admin", "requester", "purchaser" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listSystemRole_update.setToolTipText("");
        listSystemRole_update.setName(""); // NOI18N
        listSystemRole_update.setSelectedIndex(1);
        jScrollPane4.setViewportView(listSystemRole_update);

        btnUpdate.setText("Cập nhật");

        javax.swing.GroupLayout DialogUpdateLayout = new javax.swing.GroupLayout(DialogUpdate.getContentPane());
        DialogUpdate.getContentPane().setLayout(DialogUpdateLayout);
        DialogUpdateLayout.setHorizontalGroup(
            DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogUpdateLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DialogUpdateLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(btnUpdate)))
                    .addComponent(fieldSoDT_update, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldDiaChi_update, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldPhongBan_update, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldChucVu_update, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldTen_update, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldMaNV_update, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(fieldTenTK_update)
                    .addComponent(fieldMatKhau_update)
                    .addComponent(fieldHo_update))
                .addContainerGap(41, Short.MAX_VALUE))
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        DialogUpdateLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fieldChucVu_update, fieldDiaChi_update, fieldHo_update, fieldMaNV_update, fieldMatKhau_update, fieldPhongBan_update, fieldSoDT_update, fieldTenTK_update, fieldTen_update});

        DialogUpdateLayout.setVerticalGroup(
            DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogUpdateLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(fieldMaNV_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(fieldTenTK_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(fieldMatKhau_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(fieldHo_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(fieldTen_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(fieldChucVu_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(fieldPhongBan_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(fieldDiaChi_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(fieldSoDT_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnUpdate)
                .addContainerGap())
        );

        setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý DS User"));

        tbUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên tài khoản", "Mật khẩu", "Họ", "Tên", "Chức vụ", "Phòng ban", "Địa chỉ", "Số điện thoại", "Quyền"
            }
        ));
        tbUser.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbUser.setMinimumSize(null);
        tbUser.setPreferredSize(null);
        tbUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbUser);

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btnDelete.setText("Xoá");

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Custom-Icon-Design-Pretty-Office-10-Pencil.24.png"))); // NOI18N
        btnEdit.setText("Sửa");

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        btnAdd.setText("Thêm");

        btnLoad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refresh.png"))); // NOI18N
        btnLoad.setText("Load");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm"));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã NV");

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btnSearch.setText("Tìm kiếm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Tài khoản");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(jLabel2)
                    .addComponent(fieldTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoad)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnLoad)))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public void btnAddActionListener(ActionListener listener){
        btnAdd.addActionListener(listener);
    }
    
    public void btnCreateActionListener(ActionListener listener){
        btnCreate.addActionListener(listener);
    }
    
    // Lấy content các field trên Dialog "Tạo User" (trừ tenTK vì xét điều kiện ở Controller)
    public User getNewUserFromFields() {
        User newUser = new User();
        newUser.setMaNV(Integer.parseInt(String.valueOf(fieldMaNV_add.getText())));
        String tenTK = fieldTenTK_add.getText();
        newUser.setMatKhau(fieldMatKhau_add.getText());
        newUser.setHo(fieldHo_add.getText());
        newUser.setTen(fieldTen_add.getText());
        newUser.setChucVu(fieldChucVu_add.getText());
        newUser.setPhongBan(fieldPhongBan_add.getText());
        newUser.setDiaChi(fieldDiaChi_add.getText());
        newUser.setSoDT(fieldSoDT_add.getText());
        String[] systemRoles = listSystemRole_add.getSelectedValuesList().toArray(new String[0]); // Lấy giá trị được chọn, chuyển thành Object Array với các phần tử sắp xếp từ trái qua phải và kiểu mảng trả về là kiểu mảng được truyền vào (new String[0])
        newUser.setSystemRoles(systemRoles);
        return newUser;
    }
        
    

    
    public void btnLoadActionListener(ActionListener listener) {
        System.out.println("btnLoad Listener is listening");
        btnLoad.addActionListener(listener);
    }
    
    public void loadData() {        
        tableERP = new TableERP(data, column);
        tbUser.setModel(tableERP);
    }
    
    public void updateTbERP(){
        tbUser.setModel(tableERP);
    }
    
    public void btnSearchActionListener(ActionListener listener){
        btnSearch.addActionListener(listener);
    }
    
    // Lấy content các field Sarch ở cửa sổ chính của quản lý User
    public String[] getSearchParams(){
        String[] searchParams = new String[2];
        searchParams[0] = fieldMaNV.getText();
        searchParams[1] = fieldTenTK.getText();
        return searchParams;
    } 
    
    public void btnEditActionListener(ActionListener listener){
        btnEdit.addActionListener(listener);
    }
    
    public void setUpdateFieldData(Object[] rowData) {
        fieldMaNV_update.setText(String.valueOf(rowData[0]));
        fieldTenTK_update.setText((String) rowData[1]);
        fieldMatKhau_update.setText((String) rowData[2]);
        fieldHo_update.setText((String) rowData[3]);
        fieldTen_update.setText((String) rowData[4]);
        fieldChucVu_update.setText((String) rowData[5]);
        fieldPhongBan_update.setText((String) rowData[6]);
        fieldDiaChi_update.setText((String) rowData[7]);
        fieldSoDT_update.setText((String) rowData[8]);
        String[] selectedRoles = null;
        if (rowData[9] != null){
            selectedRoles = ((String) rowData[9]).split(";");
            for (String role : selectedRoles) {
                for (int i = 0; i < listSystemRole_update.getModel().getSize(); i++) {
                    if (listSystemRole_update.getModel().getElementAt(i).equals(role)) {
                        listSystemRole_update.addSelectionInterval(i, i);
                    }
                }
            }

        }
        fieldMaNV_update.setEditable(false);
        fieldTenTK_update.setEditable(false);
    }
    
    public void btnDeleteActionListener(ActionListener listener){
        btnDelete.addActionListener(listener);
    };
    
    public void btnUpdateActionListener(ActionListener listener){
        btnUpdate.addActionListener(listener);
    }
    
    public User getUpdateUserFromFields() {
        User user = new User();
        user.setMaNV(Integer.parseInt(fieldMaNV_update.getText()));
        user.setTenTK(fieldTenTK_update.getText());
        user.setMatKhau(fieldMatKhau_update.getText());
        user.setHo(fieldHo_update.getText());
        user.setTen(fieldTen_update.getText());
        user.setChucVu(fieldChucVu_update.getText());
        user.setPhongBan(fieldPhongBan_update.getText());
        user.setDiaChi(fieldDiaChi_update.getText());
        user.setSoDT(fieldSoDT_update.getText());
        String[] systemRoles = listSystemRole_update.getSelectedValuesList().toArray(new String[0]);
        String systemRoleString = String.join(";", systemRoles);
        user.setSystemRole(systemRoleString);
        return user;
    }
    
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogAdd;
    private javax.swing.JDialog DialogUpdate;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField fieldChucVu_add;
    private javax.swing.JTextField fieldChucVu_update;
    private javax.swing.JTextField fieldDiaChi_add;
    private javax.swing.JTextField fieldDiaChi_update;
    private javax.swing.JTextField fieldHo_add;
    private javax.swing.JTextField fieldHo_update;
    private javax.swing.JTextField fieldMaNV;
    private javax.swing.JTextField fieldMaNV_add;
    private javax.swing.JTextField fieldMaNV_update;
    private javax.swing.JPasswordField fieldMatKhau_add;
    private javax.swing.JPasswordField fieldMatKhau_update;
    private javax.swing.JTextField fieldPhongBan_add;
    private javax.swing.JTextField fieldPhongBan_update;
    private javax.swing.JTextField fieldSoDT_add;
    private javax.swing.JTextField fieldSoDT_update;
    private javax.swing.JTextField fieldTenTK;
    private javax.swing.JTextField fieldTenTK_add;
    private javax.swing.JTextField fieldTenTK_update;
    private javax.swing.JTextField fieldTen_add;
    private javax.swing.JTextField fieldTen_update;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listSystemRole_add;
    private javax.swing.JList<String> listSystemRole_update;
    private javax.swing.JTable tbUser;
    // End of variables declaration//GEN-END:variables

    

    
}
