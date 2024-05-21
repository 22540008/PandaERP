/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.User;
import Services.UserManager;
import Views.TableERP;
import Views.UserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo
 */
public class UserController {
    private UserManager model;  
    private UserView view;

    public UserController(UserManager model, UserView view) {
        this.model = model;
        this.view = view;
        this.view.btnLoadActionListener(new LoadActionListener());
        this.view.btnDeleteActionListener(new DeleteActionListener());
        this.view.btnSearchActionListener(new SearchActionListener());
        this.view.btnAddActionListener(new AddActionListener());
        this.view.btnCreateActionListener(new CreateActionListener());
        this.view.btnEditActionListener(new EditActionListener());
        this.view.btnUpdateActionListener(new UpdateActionListener());
    }
    
    public UserController() {
        this.model = new UserManager();
        // Không được tạo View ở đây -> StackOverflow
        //this.view.btnLoadActionListener(new LoadActionListener());
    }
    
    public void setView(UserView view) {
        this.view = view;
    }

    public void addUser(int maNV, String tenTK, String matKhau, String ho, String ten, String chucVu, String phongBan, String diaChi, String soDT, String[] systemRoles) throws SQLException {
        User user = new User();
        user.setMaNV(maNV);
        user.setTenTK(tenTK);
        user.setMatKhau(matKhau);
        user.setHo(ho);
        user.setTen(ten);
        user.setChucVu(chucVu);
        user.setPhongBan(phongBan);
        user.setDiaChi(diaChi);
        user.setSoDT(soDT);
        user.setSystemRoles(systemRoles);
        model.addDB(user);
        
        String systemRolesString = String.join(";", systemRoles);
        Object[] newRow = new Object[]{maNV, tenTK, matKhau, ho, ten, chucVu, phongBan, diaChi, soDT, systemRolesString};
        view.getTableERP().addRow(newRow);
        //view.addData(newRow);
        view.updateTbERP();
    }

    
    private class LoadActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoad is clicked");
            try {
                model.loadData_DB();
                Object[][] dsObjUser = model.getObjDsUser();
                view.setColumn(model.getColumnns());
                view.setData(dsObjUser);
                view.loadData();
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
    }

    public void upDateUser(Object[] rowData) throws SQLException {
        User updatedUser = new User(rowData);
        model.updateDB(updatedUser);
        
    }

    private class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSearch is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(null, "Vui lòng Load dữ liệu trước");
            }
            String[] paramSearch = view.getSearchParams();
            Object[][] trackObjUser;
            trackObjUser = view.getTableERP().searchByCriteria(paramSearch, new int[]{0, 1}, "match");
            view.getTableERP().setData(trackObjUser, User.columns, view.getTbUser());
        }
    }

    private class DeleteActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnDelte is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            int deleteRow = view.getTbUser().getSelectedRow();
            if (deleteRow == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn hàng cần xoá");
                return;
            }
            String deleteTenTK = (String) view.getTbUser().getValueAt(deleteRow, 1);
            System.out.println(deleteTenTK);
            User deleteUser = new User();
            for (User user : model.getDSUser()){
                if (user.getTenTK().equals(deleteTenTK)){
                    deleteUser = user;
                }
            }
            deleteUser.setTrangThai(1);
            System.out.println("sau set trang thai (1): " + deleteUser);
            try {
                model.updateDB(deleteUser);
                JOptionPane.showMessageDialog(view, "Đã xoá thành công");
                view.getTableERP().removeRow(deleteRow);
                view.updateTbERP();
                
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }  
    }
    
    // Tìm Mã nhân viên lớn nhất
    public int getMaxMaNV() {
        ArrayList<User> dsUser = model.getDSUser();
        int rowCount = dsUser.size();       
        int maxMaNV = 0;
        for (User user : dsUser){
            if (maxMaNV < user.getMaNV()){
                maxMaNV = user.getMaNV();
            }
        }
        return maxMaNV;
    };

    private class AddActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnAdd is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            // Mã nhân viên mới sẽ = mã lớn nhất của dsActiveUser + 1
            int maxMaNV = getMaxMaNV();
            view.getFieldMaNV_add().setText(String.valueOf(maxMaNV + 1));
            view.getFieldMaNV_add().setEditable(false);
            view.getDialogAdd().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
            view.getDialogAdd().setVisible(true);
               
            
        }   
    }
    
    // Action khi nút "Tạo" của Dialog "Thêm User" được nhấn
    private class CreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnCreate is clicked");
            String tenTK = view.getFieldTenTK_add().getText();
            if (isDuplicateTenTK(tenTK)){
                JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại, vui lòng đặt tên khác");
                return;
            };
            User newUser = view.getNewUserFromFields();
            newUser.setTenTK(tenTK);
            System.out.println(newUser);
            try {
                model.addDB(newUser);
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object[] newRow = newUser.getObjectUser();
            view.getTableERP().addRow(newRow);
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            view.getDialogAdd().dispose();
        }
    }
    
    // Action khi nút "Sửa" của cửa sổ quản lý User được nhấn
    private class EditActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnEdit is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(null, "Vui lòng load dữ liệu trước");
                return;
            }
            int selectedUpdatedRow = view.getTbUser().getSelectedRow();
            if (selectedUpdatedRow == -1){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cần cập nhật");
                return;
            }
            // Lấy dữ liệu từ hàng được chọn
            Object[] rowData = new Object[User.columns.length];
            for (int i = 0; i < User.columns.length; i++){
                rowData[i] = view.getTbUser().getValueAt(selectedUpdatedRow, i);
            }
            view.setUpdateFieldData(rowData);
            view.getDialogUpdate().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
            view.getDialogUpdate().setVisible(true);
        }
    }

    // Action khi nút "Cập nhật" của Dialog Update được nhấn
    private class UpdateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnUpdate is clicked");
            int selRow = view.getTbUser().getSelectedRow();
            User updatedUser = view.getUpdateUserFromFields();
            System.out.println(updatedUser);
            try {
                model.updateDB(updatedUser);
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JOptionPane.showMessageDialog(null, "Update thành công");
            Object[] rowData = updatedUser.getObjectUser();
            for (int i = 0; i < rowData.length; i++){
                System.out.print(rowData[i] + " ");
            }
            //view.getTableERP().sua(view.getSelectedUpdatedRow(), rowData);
            view.getTableERP().sua(selRow, rowData);
            view.getDialogUpdate().dispose();
        }  
    }
    

    

    public UserView getView() {
        return view;
    }

    public void showView() {
        System.out.println("showView is called");
        view.setVisible(true);
    }

    public String[] getColumns() {
        return model.getColumnns();
    }
    
    
    public void updateData() {
        try {
            model.loadData_DB();
            Object[][] dsObjUser = model.getObjDsUser();
            view.setData(dsObjUser);
            view.loadData();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    private boolean hasRole(User user, String role) {
        for (String r : model.getSystemRoles(user)) {
            if (r.equals(role)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasAccessToUser(User user) {
        return hasRole(user, "admin");
    }

    public boolean hasAccessToPR(User user) {
        return hasRole(user, "PR");
    }

    public boolean hasAccessToPO(User user) {
        return hasRole(user, "PO");
    }

    public boolean hasAccessToGR(User user) {
        return hasRole(user, "GR");
    }
    

    
    public boolean isDuplicateTenTK(String tenTK){
        ArrayList<User> dsAciveUser = model.filterActiveUser();
        int rowCount = dsAciveUser.size();       
        for (User user : dsAciveUser){
            if (user.getTenTK().equals(tenTK)){
                return true; // trùng
            }
        }
        return false; // không trùng
    }
}
