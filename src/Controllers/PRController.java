/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Item;
import Models.PurchaseRequest;
import Models.User;
import Services.PRManager;
import Views.PRView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Utility.CurrencyUtils;
import Views.TableERP;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;


/**
 *
 * @author Ricardo
 */
public class PRController {
    private PRManager model;
    private PRView view;
    private User loginUser;
//    private ItemManager modelItem;
    private ItemController itemCtl;
    
    public PRController(PRManager prModel, PRView prView) {
        this.model = prModel;
        this.view = prView;
        this.view.btnLoadActionListener(new LoadActionListener());
        this.view.btnSearchActionListener(new SearchActionListener());
        this.view.btnCalItemPriceActionListener(new CalItemPriceActionListener());
        this.view.btnAddActionListener(new AddActionListener());
        this.view.btnDialogAddTimItemActionListener(new DialogTimIemActionListener());
        this.view.btnLoadItemActionListener(new LoadFindItemActionListener());
        this.view.btnSearchItemActionListener(new SearchItemActionListener());
        this.view.btnAddItemInfoActionListener(new AddItemInfoActionListener());
        this.view.btnAddItem_addActionListener(new AddItem_addActionListener());
        this.view.btnDelete_addActionListener(new Delete_addActionListener());
        this.view.btnTinhTongPRActionListener(new TinhTongActionListener());
        this.view.btnCreateActionListener(new CreateActionListener());
//        this.view.btnDialogAddActionListener(new DialogAddActionListener());
//        this.view.btnUpdateActionListener(new UpdateActionListener());
//        this.view.btnDialogUpdateActionListener(new DialogUpdateActionListener());
//        this.view.btnDeleteActionListener(new DeleteActionListener());
        
    }

    public PRManager getModel() {
        return model;
    }

    public void setModel(PRManager model) {
        this.model = model;
    }




    public void setOtherModel(User loginUser, ItemController itemController){
        this.loginUser = loginUser;
        this.itemCtl = itemController;  
    }

    public void setView(PRView vendorView) {
        this.view = vendorView;
    }

    public PRView getView() {
        return view;
    }

    //Action khi nút "Tạo PR" của Dialog "Tạo PR" được nhấn
    private class CreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnCreate is clicked");
            int rowCount = view.getTbPRdraft().getRowCount();
            ArrayList<PurchaseRequest> newPRlist = new ArrayList();
            for (int i = 0; i < rowCount; i++){
                int maHang = (int) view.getTbPRdraft().getValueAt(i, 0);
                Item item = new Item();
                item.setMaHang(maHang);
                
                PurchaseRequest pr = new PurchaseRequest();
                pr.setSoCT(Integer.parseInt(view.getFieldSoCT_add().getText()));
                pr.setUser(view.getFieldUser_add().getText());
                pr.setNgayTao(view.getDate_add().getDate());
                pr.setNgaySua(view.getDate_add().getDate()); // Ngày sửa = ngày tạo khi tạo mới PR.
                pr.setItemLine(i+1);
                pr.setItem(item);
                pr.getItem().setDonGia((long)view.getTbPRdraft().getValueAt(i, 3));
                pr.setSoLuong((int)view.getTbPRdraft().getValueAt(i, 4));
                pr.setGiaItem((long)view.getTbPRdraft().getValueAt(i, 5));
                
                newPRlist.add(pr);
            }
            
            try {
                model.addDB(newPRlist);
            } catch (SQLException ex) {
                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (PurchaseRequest pr : newPRlist){
                Object[] objPR = pr.getObjPR();
                view.getTableERP().addRow(objPR);
            }
            view.updateTbPR();
            JOptionPane.showMessageDialog(view, "Vui lòng \"Load\" lại dữ liệu để cập nhật mới nhất");

        }
    }

    
    

    //Action khi nút "Tính tổng" của Dialog Action PR được nhấn
    private class TinhTongActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnTinhTongPR is clicked");
            int rowCount = view.getTablePRdraft().getRowCount();
            for (int i = 0; i < rowCount; i++){
                long value = (long)view.getTablePRdraft().getValueAt(i, 3) * (int)view.getTablePRdraft().getValueAt(i, 4);
                view.getTablePRdraft().setValueAt(value, i, 5);
            }
            long tong = 0;
            for (int i = 0; i < rowCount; i++){
                tong += (long) view.getTablePRdraft().getValueAt(i, 5);
            }
            view.updateTbPRdraft();
            //view.getFieldTongPR().setText(String.valueOf(tong));
            view.getFieldTongPR().setText(CurrencyUtils.format(String.valueOf(tong)));
        }
    }
    
    //Action khi nút "Xoá Item" của Dialog "Tạo PR" được nhấn
    private class Delete_addActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnDelete_add is clicked");
            if (view.getTbPRdraft().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng Vendor cần sửa");
                return;
            }
            int selRow = view.getTbPRdraft().getSelectedRow();
            view.getTablePRdraft().xoa(selRow);
            view.updateTbPRdraft();
        }
    }

    // Action khi nút "Thêm" của Dialog "Tạo PR" được nhấn
    private class AddItem_addActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnAdd_add is clicked");
            //int rowCount = view.getTbPRdraft().getRowCount();     
            Object[] newItem = view.addNewItem();
            for (int i = 0; i < view.getTablePRdraft().getRowCount(); i++){
                if (String.valueOf(view.getTablePRdraft().getValueAt(i, 0)).equals(String.valueOf(newItem[0])) ){
                    JOptionPane.showMessageDialog(view.getDialogAdd(), "Trùng mã item, vui lòng chọn lại");
                    return;
                }
            }
            
            view.getTablePRdraft().addRow(newItem);
            view.updateTbPRdraft();
            
        }
    }

    // Action khi nút "Thêm" của Dialog "Tìm Item" được nhấn
    private class AddItemInfoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnAddItemInfo is clicked");
            view.getItemInfo();
            view.getDialogTimItem().setVisible(false);
            
        }
    }

    // Action khi nút "Tìm kiếm" của Dialog "Tìm item" được nhấn.
    private class SearchItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSearchItem is clicked");
            
            String[] paramSearch = view.getSearchParamItem();
            Object[][] trackObjItem2D;
            try {
                trackObjItem2D = itemCtl.SearchItembyCriteria(itemCtl.getModel(), paramSearch);
                view.setColumnItem(Item.getColumns());
                view.setDataItem(trackObjItem2D);
                view.loadDataItem();
            } catch (SQLException ex) {
                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Action khi nút Load của Dialog "Tìm Item" được click
    private class LoadFindItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoadItem_add is clicked");
            try {
                itemCtl.getModel().loadData_DB();
                Object[][] dsObjItem = itemCtl.getModel().getObjDsItem();
                view.setColumnItem(Item.getColumns());
                view.setDataItem(dsObjItem);
                view.loadDataItem();
            } catch (SQLException ex) {
                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Action khi nút Tìm của Dialog "Tìm Item" được click
    private class DialogTimIemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getDialogTimItem().pack();
            view.getDialogTimItem().setVisible(true);
        }
    }
    
    // Action khi nút Thêm của Panel "Quản lý danh sách PR" được click
    private class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnAdd is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            // Tìm số PR lớn nhất hoặc theo format (6 chữ số): 1 (đại diện PR) + YY (2 số cuối của năm) + 0000
            String lastYear2Digit = String.valueOf(Year.now().getValue() % 100);
            int maxSoCT = Integer.parseInt("1" + lastYear2Digit + "0000");
            for (PurchaseRequest pr : model.getDsPR()){
                if (pr.getSoCT() > maxSoCT){
                    maxSoCT = pr.getSoCT();
                }
            }
            view.getFieldSoCT_add().setText(String.valueOf(maxSoCT + 1));
            view.getDate_add().setDate(new Date()); // set ngày PR hiện tại
            view.getFieldUser_add().setText(loginUser.getTenTK()); // set người tạo PR là tài khoản đang dùng
            
            view.setColumnPRdraft(new String[] {"Mã hàng", "Tên hàng", "ĐVT", "Giá est", "Số lượng", "Tổng giá"});
            view.setDataPRdraft(new Object[0][0]);
            view.initTablePRdraft();
            view.getDialogAdd().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
            view.getDialogAdd().setVisible(true);
        }
    }
    
    
    
    //    private class DialogAddActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnDialogAdd is clicked");
//            PurchaseRequest newPR = view.getFieldDiaglogAdd();
//            try {
//                model.addDB(newPR);
//                Object[] rowData = newPR.getObjectPR();
//                view.getTableERP().addRow(rowData);
//                view.DialogAddClearField(); // Xoá các trường đã nhập để chuẩn bị cho lần tạo tiếp theo
//                view.getDialogAdd().dispose();
//            } catch (SQLException ex) {
//                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }


//    private class UpdateActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnUpdate is clicked");
//            if (view.getTableERP() == null){
//                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
//                return;
//            }
//            if (view.getTbPR().getSelectedRow() == -1){
//                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng PR cần sửa");
//                return;
//            }
//            view.setFieldMaNCC_update();
//            view.getDialogUpdate().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
//            view.getDialogUpdate().setVisible(true);
//        }
//    }
//    
//    public TableERP tinhTongGia(TableERP table, int cotGia, int cotSL, int cotTongGia){
//        int rowCount = table.getRowCount();
//        for (int i = 0; i < rowCount; i++){
//            table.getValueAt(i, cotTongGia) = (int)table.getValueAt(i, cotSL) * (long)table.getValueAt(i, cotGia);
//        }
//        return table;
//    }
    
    private class CalItemPriceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnCalItemPrice is clicked");
            if (model.getDsPR() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
//            long totalPrice = 0;
//            for (PurchaseRequest pr : model.getDsPR()){
//                totalPrice += pr.tinhGiaItem();
//            }
//            Object[][] dsObjPR = model.getObjDsPR();
            long totalPrice = view.getTableERP().capNhatTongGia(9, 10, 11);
//            view.setData(dsObjPR);
//            view.loadData();
            view.updateTbPR();
            view.getFieldTotalPrice().setText(CurrencyUtils.VN_FORMAT.format(totalPrice));
            
            
        }
    }
    
    private class LoadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoad is clicked");
            try {
                model.loadData_DB();
                Object[][] dsObjPR = model.getObjDsPR();               
                view.setColumn(PurchaseRequest.getColumns());
                view.setData(dsObjPR);
                view.loadData();   
            } catch (SQLException ex) {
                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    private Object[][] searchPRbyCriteria(PRManager pRManager, String[] paramSearch) throws SQLException{
        if (paramSearch[0].isBlank() && paramSearch[1].isBlank()){
            JOptionPane.showMessageDialog(view, "Vui lòng nhập từ khoá tìm kiếm");
            return null;
        }
        int trackSoCT; String trackUser;
        ArrayList<PurchaseRequest> loadData = model.loadData_DB();
        ArrayList<PurchaseRequest> trackResult = new ArrayList();
        if (!paramSearch[0].isBlank()){
            try {
                trackSoCT = Integer.parseInt(paramSearch[0]);
                for (PurchaseRequest pr : loadData){
                    if (pr.getSoCT() == trackSoCT && pr.getTrangThai() == 0){
                        trackResult.add(pr);
                        break;
                    }
                }         
            } catch (NumberFormatException numE) {
                JOptionPane.showMessageDialog(view, "Mã NCC phải ở định dạng số");
                return null;
            }

        }
        else {
            if (!paramSearch[1].isBlank()){
                trackUser = paramSearch[1];
                for (PurchaseRequest pr : loadData){
                    if (pr.getUser().equals(trackUser)){
                        trackResult.add(pr);
                    }
                }
            }
        }

        if (trackResult.isEmpty()){
            JOptionPane.showMessageDialog(view, "Không tìm thấy");
            return null;
        }
        int row = trackResult.size();
        Object[][] trackObjPR2D = new Object[row][PurchaseRequest.getColumns().length];
        for (int i = 0; i < row; i++){
            Object[] objPR = trackResult.get(i).getObjPR();
            for (int j = 0; j < objPR.length; j++){
                trackObjPR2D[i][j] = objPR[j];
            }
        }
        return trackObjPR2D;
    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSearch is clicked");
            String[] paramSearch = view.getSearchParams();
            Object[][] trackObjPR2D;
            try {
                trackObjPR2D = searchPRbyCriteria(model, paramSearch);
                view.setColumn(PurchaseRequest.getColumns());
                view.setData(trackObjPR2D);
                view.loadData();
            } catch (SQLException ex) {
                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


//    private class DialogUpdateActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnDialogUpdate is clicked");
//            PR updatePR = view.getFieldDiaglogUpdate();
//            try {
//                model.updateDB(updatePR);
//                Object[] rowData = updatePR.getObjectPR();
//                view.getTableERP().sua(view.getSelRow(), rowData);
//                view.getDialogUpdate().dispose();
//            } catch (SQLException ex) {
//                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    private class DeleteActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnDelete is clicked");
//            if (view.getTableERP() == null){
//                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
//                return;
//            }
//            if (view.getTbPR().getSelectedRow() == -1){
//                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng PR cần sửa");
//                return;
//            }
//            int selRow = view.getTbPR().getSelectedRow();
//            //int deleteMaNCC = Integer.parseInt(String.valueOf(view.getTbPR().getValueAt(selRow, 0)));
//            int deleteMaNCC = (int)view.getTbPR().getValueAt(selRow, 0);
//            try {
//                model.updateDB(deleteMaNCC);
//                view.getTableERP().xoa(view.getSelRow());
//            } catch (SQLException ex) {
//                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
//    }


    
}
