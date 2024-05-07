/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.User;
import Services.UserManager;
import Views.TableERP;
import Views.UserView;
import Views.UserView_add;
import Views.UserView_update;
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
    private UserView_add viewAdd;
    private UserView_update viewUpdate;

    public UserController(UserManager model, UserView view) {
        this.model = model;
        this.view = view;
        this.view.btnLoadActionListener(new LoadActionListener());
        this.view.btnDeleteActionListener(new DeleteActionListener());
        this.view.btnSearchActionListener(new SearchActionListener());
    }
    
    public UserController() {
        this.model = new UserManager();
        // Không được tạo View ở đây -> StackOverflow
        //this.view.btnLoadActionListener(new LoadActionListener());
    }
    
    public void setView(UserView view) {
        this.view = view;
    }

    public void setViewAdd(UserView_add viewAdd) {
        this.viewAdd = viewAdd;
        this.viewAdd.btnAddAtionListener(new AddActionListener());
    }

    public void setViewUpdate(UserView_update viewUpdate) {
        this.viewUpdate = viewUpdate;
        this.viewUpdate.btnUpdateActionListener(new UpdateActionListener());
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
        view.updateTable();
    }
    
    private class LoadActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoad is clicked");
            try {
                model.loadData_DB();
                Object[][] dsObjUser = model.getObjDsUser();
//                for (int i = 0; i < dsObjUser.length; i++){
//                    for (int j = 0; j < model.getColumnns().length; j++){
//                        System.out.print(dsObjUser[i][j]);
//                    }
//                }
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
            try {
                String[] paramSearch = view.getSearchParams();
                if (paramSearch[0].isBlank() && paramSearch[1].isBlank()){
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập từ khoá tìm kiếm");
                    return;
                }
                int trackMaNV; String trackTenTK;
                ArrayList<User> loadData = model.loadData_DB();
                User trackResult = new User();
                trackResult.setMaNV(-1);
                if (!paramSearch[0].isBlank()){
                    try {
                        trackMaNV = Integer.parseInt(paramSearch[0]);
                        for (User user : loadData){
                            if (user.getMaNV() == trackMaNV){
                                trackResult = user;
                                break;
                            }
                        }         
                    } catch (NumberFormatException numE) {
                        JOptionPane.showMessageDialog(view, "Mã NV phải là một số");
                        return;
                    }
                              
                }
                else {
                    if (!paramSearch[1].isBlank()){
                        trackTenTK = paramSearch[1];
                        for (User user : loadData){
                            if (user.getTenTK().equals(trackTenTK)){
                                trackResult = user;
                                break;
                            }
                        }
                    }
                }
                
                
                //Object[][] dsObjUser = model.getObjDsUser();
                if (trackResult.getMaNV() == -1){
                    JOptionPane.showMessageDialog(view, "Không tìm thấy");
                    return;
                }
                Object[] trackObjUser = trackResult.getObjectUser();
                Object[][] trackObjUser2D = { trackObjUser };
                //Object[][] trackObjUser = new Object[1][]
                //view.setData(dsObjUser);
                view.setData((Object[][]) trackObjUser2D);
                view.loadData();
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }       
            
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
                //model.loadData_DB();
                //Object[][] dsObjUser = model.getObjDsUser();
                //view.setData(dsObjUser);
                view.getTableERP().xoa(deleteRow);
                view.updateTable();
                
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }  
    }

    private class AddActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnAdd is clicked");
            User newUser = viewAdd.getUserFromFields();
            System.out.println(newUser);
            try {
                model.addDB(newUser);
                JOptionPane.showMessageDialog(viewAdd, "Thêm thành công");
                Object[] newRow = newUser.getObjectUser();
                view.getTableERP().them(newRow);
                viewAdd.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }

    private class UpdateActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnUpdate is clicked");
            User updatedUser = viewUpdate.getUserFromFields();
            System.out.println(updatedUser);
            try {
                model.updateDB(updatedUser);
                JOptionPane.showMessageDialog(viewUpdate, "Update thành công");
                Object[] rowData = updatedUser.getObjectUser();
                for (int i = 0; i < rowData.length; i++){
                    System.out.print(rowData[i] + " ");
                }
                view.getTableERP().sua(view.getSelectedUpdatedRow(), rowData);
                viewUpdate.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }      
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
