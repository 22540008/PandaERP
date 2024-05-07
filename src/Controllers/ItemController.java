/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Item;
import Services.ItemManager;
import Views.ItemView;
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
public class ItemController {
    private ItemManager model;
    private ItemView view;

    public ItemController(ItemManager model, ItemView view) {
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
    
        public ItemController() {
        this.model = new ItemManager();
    }

    public ItemManager getModel() {
        return model;
    }

    public void setModel(ItemManager model) {
        this.model = model;
    }

    public void setView(ItemView itemView) {
        this.view = itemView;
    }

    public ItemView getView() {
        return view;
    }
    
    private class LoadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoad is clicked");
            try {
                model.loadData_DB();
                Object[][] dsObjItem = model.getObjDsItem();               
                view.setColumn(Item.getColumns());
                view.setData(dsObjItem);
                view.loadData();
            } catch (SQLException ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class DialogUpdateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnDialogUpdate is clicked");
            Item updateItem = view.getFieldDiaglogUpdate();
            try {
                model.updateDB(updateItem);
                Object[] rowData = updateItem.getObjectItem();
                view.getTableERP().sua(view.getSelRow(), rowData);
                view.getDialogUpdate().dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // criteria 1: Mã hàng, cri2: Tên hàng
    public Object[][] SearchItembyCriteria(ItemManager itemManager, String[] paramSearch) throws SQLException{
        ItemManager itemManager1 = itemManager;
        int trackMaHang; String trackTenHang;
        
        if (paramSearch[0].isBlank() && paramSearch[1].isBlank()){
            JOptionPane.showMessageDialog(view, "Vui lòng nhập từ khoá tìm kiếm");
            return null;
        }
        ArrayList<Item> loadData = model.loadData_DB();
        ArrayList<Item> trackResult = new ArrayList();
        if (!paramSearch[0].isBlank()){
            try {
                trackMaHang = Integer.parseInt(paramSearch[0]);
                for (Item item : loadData){
                    if (item.getMaHang() == trackMaHang && item.getTrangThai() == 0){
                        trackResult.add(item);
                        break;
                    }
                }         
            } catch (NumberFormatException numE) {
                JOptionPane.showMessageDialog(view, "Mã hàng phải ở định dạng số");
                return null;
            }
        }
        else {
            if (!paramSearch[1].isBlank()){
                trackTenHang = paramSearch[1];
                for (Item item : loadData){
                    if (item.getTenHang().equals(trackTenHang)){
                        trackResult.add(item);
                    }
                }
            }
        }

        if (trackResult.isEmpty()){
            JOptionPane.showMessageDialog(view, "Không tìm thấy");
            return null;
        }
        int row = trackResult.size();
        Object[][] trackObjItem2D = new Object[row][Item.getColumns().length];
        for (int i = 0; i < row; i++){
            Object[] objItem = trackResult.get(i).getObjectItem();
            for (int j = 0; j < objItem.length; j++){
                trackObjItem2D[i][j] = objItem[j];
            }
        }
        return trackObjItem2D;
    }
    
    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSearch is clicked");
            try {
                String[] paramSearch = view.getSearchParams();
//                if (paramSearch[0].isBlank() && paramSearch[1].isBlank()){
//                    JOptionPane.showMessageDialog(view, "Vui lòng nhập từ khoá tìm kiếm");
//                    return;
//                }
//                int trackMaHang; String trackTenHang;
//                ArrayList<Item> loadData = model.loadData_DB();
//                ArrayList<Item> trackResult = new ArrayList();
//                if (!paramSearch[0].isBlank()){
//                    try {
//                        trackMaHang = Integer.parseInt(paramSearch[0]);
//                        for (Item item : loadData){
//                            if (item.getMaHang() == trackMaHang && item.getTrangThai() == 0){
//                                trackResult.add(item);
//                                break;
//                            }
//                        }         
//                    } catch (NumberFormatException numE) {
//                        JOptionPane.showMessageDialog(view, "Mã hàng phải ở định dạng số");
//                        return;
//                    }
//                              
//                }
//                else {
//                    if (!paramSearch[1].isBlank()){
//                        trackTenHang = paramSearch[1];
//                        for (Item item : loadData){
//                            if (item.getTenHang().equals(trackTenHang)){
//                                trackResult.add(item);
//                            }
//                        }
//                    }
//                }
//               
//                if (trackResult.isEmpty()){
//                    JOptionPane.showMessageDialog(view, "Không tìm thấy");
//                    return;
//                }
//                int row = trackResult.size();
//                Object[][] trackObjItem2D = new Object[row][Item.getColumns().length];
//                for (int i = 0; i < row; i++){
//                    Object[] objItem = trackResult.get(i).getObjectItem();
//                    for (int j = 0; j < objItem.length; j++){
//                        trackObjItem2D[i][j] = objItem[j];
//                    }
//                }
                Object[][] trackObjItem2D = SearchItembyCriteria(model, paramSearch);
                view.setColumn(Item.getColumns());
                view.setData(trackObjItem2D);
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
            Item newItem = view.getFieldDiaglogAdd();
            try {
                model.addDB(newItem);
                Object[] rowData = newItem.getObjectItem();
                view.getTableERP().addRow(rowData);
                view.DialogAddClearField(); // Xoá các trường đã nhập để chuẩn bị cho lần tạo tiếp theo
                view.getDialogAdd().dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
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
            
            int maxMaHang = 0;
            for (Item item : model.getDsItem()){
                if (item.getMaHang() > maxMaHang){
                    maxMaHang = item.getMaHang();
                }
            }
            view.getFieldMaHang_add().setText(String.valueOf(maxMaHang + 1));
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
            if (view.getTbItem().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng Item cần sửa");
                return;
            }
            view.setFieldMaHang_update();
            view.getDialogUpdate().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
            view.getDialogUpdate().setVisible(true);
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
            if (view.getTbItem().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng Item cần sửa");
                return;
            }
            int selRow = view.getTbItem().getSelectedRow();
            //int deleteMaHang = Integer.parseInt(String.valueOf(view.getTbItem().getValueAt(selRow, 0)));
            int deleteMaHang = (int)view.getTbItem().getValueAt(selRow, 0);
            try {
                model.updateDB(deleteMaHang);
                view.getTableERP().xoa(view.getSelRow());
            } catch (SQLException ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }


}
