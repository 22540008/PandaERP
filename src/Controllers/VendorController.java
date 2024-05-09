/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;


import Models.Vendor;
import Services.VendorManager;
import Views.VendorView;
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
public class VendorController {
    private VendorManager model;
    private VendorView view;

    public VendorController(VendorManager model, VendorView view) {
        this.model = model;
        this.view = view;
        this.view.btnLoadActionListener(new LoadActionListener());
        this.view.btnAddActionListener(new AddActionListener());
        this.view.btnDialogAddActionListener(new DialogAddActionListener());
        this.view.btnSearchActionListener(new SearchActionListener());
        this.view.btnUpdateActionListener(new UpdateActionListener());
        this.view.btnDialogUpdateActionListener(new DialogUpdateActionListener());
        this.view.btnDeleteActionListener(new DeleteActionListener());
    }
    
        public VendorController() {
        this.model = new VendorManager();
    }

    public VendorManager getModel() {
        return model;
    }

    public void setModel(VendorManager model) {
        this.model = model;
    }

    public void setView(VendorView vendorView) {
        this.view = vendorView;
    }

    public VendorView getView() {
        return view;
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
                int trackMaNCC; String trackTenNCC;
                ArrayList<Vendor> loadData = model.loadData_DB();
                ArrayList<Vendor> trackResult = new ArrayList();
                if (!paramSearch[0].isBlank()){
                    try {
                        trackMaNCC = Integer.parseInt(paramSearch[0]);
                        for (Vendor vendor : loadData){
                            if (vendor.getMaNCC() == trackMaNCC && vendor.getTrangThai() == 0){
                                trackResult.add(vendor);
                                break;
                            }
                        }         
                    } catch (NumberFormatException numE) {
                        JOptionPane.showMessageDialog(view, "Mã NCC phải ở định dạng số");
                        return;
                    }
                              
                }
                else {
                    if (!paramSearch[1].isBlank()){
                        trackTenNCC = paramSearch[1];
                        for (Vendor vendor : loadData){
                            if (vendor.getTenNCC().equals(trackTenNCC)){
                                trackResult.add(vendor);
                            }
                        }
                    }
                }
               
                if (trackResult.isEmpty()){
                    JOptionPane.showMessageDialog(view, "Không tìm thấy");
                    return;
                }
                int row = trackResult.size();
                Object[][] trackObjVendor2D = new Object[row][Vendor.getColumns().length];
                for (int i = 0; i < row; i++){
                    Object[] objVendor = trackResult.get(i).getObjectVendor();
                    for (int j = 0; j < objVendor.length; j++){
                        trackObjVendor2D[i][j] = objVendor[j];
                    }
                }
                view.setColumn(Vendor.getColumns());
                view.setData(trackObjVendor2D);
                view.loadData();
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    private class DialogAddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnDialogAdd is clicked");
            Vendor newVendor = view.getFieldDiaglogAdd();
            try {
                model.addDB(newVendor);
                Object[] rowData = newVendor.getObjectVendor();
                view.getTableERP().addRow(rowData);
                view.DialogAddClearField(); // Xoá các trường đã nhập để chuẩn bị cho lần tạo tiếp theo
                view.getDialogAdd().dispose();
            } catch (SQLException ex) {
                Logger.getLogger(VendorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnAdd is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            int maxMaNCC = 0;
            for (Vendor vendor : model.getDsVendor()){
                if (vendor.getMaNCC() > maxMaNCC){
                    maxMaNCC = vendor.getMaNCC();
                }
            }
            view.getFieldMaHang_add().setText(String.valueOf(maxMaNCC + 1));
            view.getDialogAdd().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
            view.getDialogAdd().setVisible(true);
        }
    }

    private class UpdateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnUpdate is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            if (view.getTbVendor().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng Vendor cần sửa");
                return;
            }
            view.setFieldMaNCC_update();
            view.getDialogUpdate().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
            view.getDialogUpdate().setVisible(true);
        }
    }
    
    private class DialogUpdateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnDialogUpdate is clicked");
            Vendor updateVendor = view.getFieldDiaglogUpdate();
            try {
                model.updateDB(updateVendor);
                Object[] rowData = updateVendor.getObjectVendor();
                view.getTableERP().sua(view.getSelRow(), rowData);
                view.getDialogUpdate().dispose();
            } catch (SQLException ex) {
                Logger.getLogger(VendorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnDelete is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            if (view.getTbVendor().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng Vendor cần sửa");
                return;
            }
            int selRow = view.getTbVendor().getSelectedRow();
            //int deleteMaNCC = Integer.parseInt(String.valueOf(view.getTbVendor().getValueAt(selRow, 0)));
            int deleteMaNCC = (int)view.getTbVendor().getValueAt(selRow, 0);
            try {
                model.updateDB(deleteMaNCC);
                view.getTableERP().removeRow(view.getSelRow());
            } catch (SQLException ex) {
                Logger.getLogger(VendorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    private class LoadActionListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoad is clicked");
            try {
                model.loadData_DB();
                Object[][] dsObjVendor = model.getObjDsVendor();               
                view.setColumn(Vendor.getColumns());
                view.setData(dsObjVendor);
                view.loadData();   
            } catch (SQLException ex) {
                Logger.getLogger(VendorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
