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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.TableModel;


/**
 * https://tips4java.wordpress.com/2008/12/12/table-stop-editing/
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
        this.view.btnAddItemAddActionListener(new AddItemAddActionListener());
        this.view.btnDeleteAddActionListener(new DeleteAddActionListener());
        this.view.btnTinhTongPRActionListener(new TinhTongActionListener());
        this.view.btnCreateActionListener(new CreateActionListener());
        this.view.btnUpdateActionListener(new UpdateActionListener());
        this.view.btnTinhTongPrUpdateActionListener(new TinhTongPrUpdateActionListener());
        this.view.DialogUpdateActionListener(new DialogUpdateActionListener());
        this.view.btnCloseActionListener(new CloseActionListener());
        this.view.btnDeleteActionListener(new DeleteActionListener());
        
        
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
    
    // Action khi nút Load của cửa sổ "quản lý PR" được nhấn -> load các PR từ CSDL
    private class LoadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoad is clicked");
            try {
                model.loadData_DB();
                Object[][] dsObjPR = model.getObjDsPR();               
                view.setColumn(PurchaseRequest.columns);
                view.setData(dsObjPR);
                view.loadData();   
            } catch (SQLException ex) {
                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Action khi nút Search ở cửa sổ "Quản lý PR" được nhấn -> Lọc danh sách PR
    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSearch is clicked");
            String[] paramSearch = view.getSearchParams();
            Object[][] trackObjPR2D;
            trackObjPR2D = view.getTableERP().searchByCriteria(paramSearch, new int[]{0, 1}, "match");
            view.getTableERP().setData(trackObjPR2D, PurchaseRequest.columns, view.getTbPR());
            
//            try {
//                trackObjPR2D = searchPRbyCriteria(model, paramSearch);
//                view.setColumn(PurchaseRequest.columns);
//                view.setData(trackObjPR2D);
//                view.loadData();
//            } catch (SQLException ex) {
//                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    // Action khi nút Tính Tổng ở cửa sổ "Quản lý PR" được nhấn -> tính value từng item và tổng value tất cả item
    private class CalItemPriceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnCalItemPrice is clicked");
            if (model.getDsPR() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            double totalPrice = view.getTableERP().capNhatTongGia(9, 10, 11);
            view.getFieldTotalPrice().setText(CurrencyUtils.VN_FORMAT.format(totalPrice));
            
            
        }
    }
    
    // Action khi nút Thêm của Panel "Quản lý danh sách PR" được click -> Mở cửa sổ Tạo PR
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
    
    // Action khi nút Tìm item của Dialog "Tạo PR" được click -> Mở cửa sổ tìm Item
    private class DialogTimIemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getDialogTimItem().pack();
            view.getDialogTimItem().setVisible(true);
        }
    }
    
    // Action khi nút Load của Dialog "Tìm Item" được click -> Load danh sách Item từ CSDL
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
    
    // Action khi nút "Tìm kiếm" của Dialog "Tìm item" được nhấn -> Lọc item
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
    
    // Action khi nút "Thêm" của Dialog "Tìm Item" được nhấn -> Chọn và đưa vào các trường của Item trong PR draft để có thể chỉnh sửa
    private class AddItemInfoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnAddItemInfo is clicked");
            view.getItemInfo();
            view.getDialogTimItem().setVisible(false);
            
        }
    }
    
    // Action khi nút "Thêm" của Dialog "Tạo PR" được nhấn -> Thêm item vào bảng PR draft
    private class AddItemAddActionListener implements ActionListener {
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
            //view.updateTbPRdraft();
        }
    }
    
    // Action khi nút "Xoá Item" của Dialog "Tạo PR" được nhấn -> xoá item trên PR draft
    private class DeleteAddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnDelete_add is clicked");
            if (view.getTbPRdraft().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng Vendor cần sửa");
                return;
            }
            int selRow = view.getTbPRdraft().getSelectedRow();
            view.getTablePRdraft().removeRow(selRow);
            //view.updateTbPRdraft();
        }
    }
    
    // Action khi nút "Tính tổng" của Dialog Tạo new PR được nhấn -> Tính valut từng item và tổng value của cả PR draft
    private class TinhTongActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnTinhTongPR is clicked");
            int rowCount = view.getTablePRdraft().getRowCount();
            for (int i = 0; i < rowCount; i++){
                double value = (long)view.getTablePRdraft().getValueAt(i, 3) * (int)view.getTablePRdraft().getValueAt(i, 4);
                view.getTablePRdraft().setValueAt(value, i, 5);
            }
            double tong = 0;
            for (int i = 0; i < rowCount; i++){
                tong += (double) view.getTablePRdraft().getValueAt(i, 5);
            }
            //view.updateTbPRdraft();
            //view.getFieldTongPR().setText(String.valueOf(tong));
            view.getFieldTongPR().setText(CurrencyUtils.format(String.valueOf(tong)));
        }
    }
    
    // Action khi nút "Tạo PR" của Dialog "Tạo PR" được nhấn -> PR draft được thêm vào CSDL & trở thành chính thức
    private class CreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnCreate is clicked");
            double totalPrice = view.getTablePRdraft().capNhatTongGia(3, 4, 5);
            view.getFieldTongPR().setText(CurrencyUtils.VN_FORMAT.format(totalPrice));
            
            int rowCount = view.getTbPRdraft().getRowCount();
            ArrayList<PurchaseRequest> newPRlist = new ArrayList();
            for (int i = 0; i < rowCount; i++){
                int maHang = (int) view.getTbPRdraft().getValueAt(i, 0);
                Item item = new Item();
                item.setMaHang(maHang);
                item.setTenHang(String.valueOf(view.getTbPRdraft().getValueAt(i, 1)));
                item.setDvt(String.valueOf(view.getTbPRdraft().getValueAt(i, 2)));
                
                PurchaseRequest pr = new PurchaseRequest();
                pr.setSoCT(Integer.parseInt(view.getFieldSoCT_add().getText()));
                pr.setTenTK(view.getFieldUser_add().getText());
                pr.setNgayTao(view.getDate_add().getDate());
                pr.setNgaySua(view.getDate_add().getDate()); // Ngày sửa = ngày tạo khi tạo mới PR.
                pr.setItemLine(i+1);
                pr.setItem(item);
                pr.setDonGia((long)view.getTbPRdraft().getValueAt(i, 3));
                pr.setSoLuong((int)view.getTbPRdraft().getValueAt(i, 4));
                if (view.getTbPRdraft().getValueAt(i, 5) != null){
                    //pr.setGiaItem(0);
                    pr.setGiaItem((double)view.getTbPRdraft().getValueAt(i, 5));
                }
                else {
                    //pr.setGiaItem((double)view.getTbPRdraft().getValueAt(i, 5));
                    pr.setGiaItem(0);
                }
                
                
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
            //view.updateTbPR();
            view.getDialogAdd().setVisible(false);
        }
    }
    
    // Action khi nút "Sửa" của quản lý PR được nhấn -> Từ PR đang chọn, mở cả list các item cùng số PR trên Dialog "Sửa PR"
    private class UpdateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnUpdate is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            if (view.getTbPR().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn PR cần sửa");
                return;
            }
            
            String[] columPRupdate = {"Trạng thái", "ItemLine", "Mã hàng", "Tên hàng", "ĐVT", "Giá est", "Số lượng", "Tổng giá"};
            view.setColumPRupdate(columPRupdate);
            
            
            int selRow = view.getTbPR().getSelectedRow();
            String selSoCT = String.valueOf(view.getTbPR().getValueAt(selRow, 0));
            view.getFieldSoCT_update().setText(selSoCT);
            view.getDate_update().setDate(new Date()); // set ngày PR hiện tại
            view.getFieldUser_update().setText(loginUser.getTenTK()); // set người tạo PR là tài khoản đang dùng
               
            int rowCount = view.getTbPR().getRowCount();
            int soCTitemCount = 0;
            for (int i = 0; i < rowCount; i++){
                if (selSoCT.equals(String.valueOf(view.getTableERP().getValueAt(i, 0)))){
                    soCTitemCount++;
                }
            }
            
            Object[][] selData = new Object[soCTitemCount][columPRupdate.length];
            int selSoCTitems = 0;
            for (int i = 0; i < rowCount; i++){
                if (String.valueOf(view.getTableERP().getValueAt(i, 0)).equals(selSoCT)){
                    System.out.println("Khớp " + view.getTbPR().getValueAt(i, 0));
                    Object[] data = view.getTableERP().getDataVector().get(i).toArray();
                    for (int j = 0; j < columPRupdate.length; j++){
                        selData[selSoCTitems][j] = data[j+4]; // [j+4] vì bỏ 4 cột đầu: soCT, nguoiTao, ngày tạo/sửa
                        //System.out.print(selData[selSoCTitems][j] + "\t");    
                    }
                    selSoCTitems++;
                }
            }
            view.setDataPRupdate(selData);
            view.initTablePRUpdate();
            view.getDialogUpdate().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
            view.getDialogUpdate().setVisible(true);
        }
    }
    
    // Action khi nút "Tính Tổng" của Dialog "sửa PR" được nhấn -> Tính value từng item và tổng value cả PR đang sửa
    private class TinhTongPrUpdateActionListener implements ActionListener {

        public TinhTongPrUpdateActionListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnTinhTongPR_update is clicked");
            double totalPrice = view.getTableERP().capNhatTongGia(5, 6, 7);
            view.getFieldTongPR_update().setText(CurrencyUtils.VN_FORMAT.format(totalPrice));
        }
    }
    
    // Action khi nút Cập nhật của Dialog "Sửa PR" được nhấn -> Các thay đổi của các item có cùng số PR sẽ được cập nhật ở CSDL và đồng thời thay đổi ở cửa cửa sổ "quản lý PR"
    private class DialogUpdateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnUpdate_update is clicked");
            // Phải stop edit JTable thì mới lưu dữ liệu vào bảng được.
            if (view.getTbPRupdate().isEditing())
                view.getTbPRupdate().getCellEditor().stopCellEditing();
            double totalPrice = view.getTableERP().capNhatTongGia(5, 6, 7);
            view.getFieldTongPR_update().setText(CurrencyUtils.VN_FORMAT.format(totalPrice));
            

            //view.updateTbPR();
            view.setVisible(true);
            int soCT = Integer.parseInt(view.getFieldSoCT_update().getText());
            String user = view.getFieldUser_update().getText();
            Date date = view.getDate_update().getDate();
            //System.out.println(date.toString());
            ArrayList<PurchaseRequest> prList = new ArrayList();
            int rowCount = view.getTbPRupdate().getRowCount();
            for (int i = 0; i < rowCount; i++){
                PurchaseRequest updatePR = view.getUpdatePRinfo(i);
                updatePR.setSoCT(soCT);
                updatePR.setTenTK(user);
                updatePR.setNgaySua(date);
                updatePR.setNgayTao(date);

                prList.add(updatePR);
            }
            
//            for (PurchaseRequest pr: prList){
//                System.out.println(pr);
//            }
            System.out.println("số lượng pr sẽ update: " + prList.size());
            
            try {
                model.updateDB(prList);
            } catch (SQLException ex) {
                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (PurchaseRequest pr : prList){
                Object[] objPR = pr.getObjPR();
                int tbERProwCount = view.getTableERP().getRowCount();
                for (int i = 0; i < tbERProwCount; i++){
                    String tbPRsoCT = String.valueOf(view.getTableERP().getValueAt(i, 0));
                    String tbPRitemLine = String.valueOf(view.getTableERP().getValueAt(i, 5));
                    if (tbPRsoCT.equals(String.valueOf(objPR[0])) && tbPRitemLine.equals(String.valueOf(objPR[5]))){
                        view.getTableERP().sua(i, objPR);
//                        for (int k = 0; k < objPR.length; k++){
//                            System.out.println(tbPRsoCT + String.valueOf(objPR[0]));
//                            System.out.println(tbPRitemLine + String.valueOf(objPR[5]));
//                            System.out.print(objPR[k]);
//                        }
                    }
                    else {
                        //System.out.println("Khong map: " + tbPRsoCT + "\t" + tbPRitemLine);
                    }
                }
               
            }
            //Object[] rowData = updateItem.getObjectItem();
            //view.getTableERP().sua(view.getSelRow(), rowData);
            //view.updateTbPR();
            view.getDialogUpdate().dispose();
            
        }
    }
    
    // Action khi nút "Đóng" ở cửa sổ "Quản lý PR" được nhấn -> đóng item lại (tương đương "HUỶ" PR đối với user)
    private class CloseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnClose is clicked");
            int confirmResult = JOptionPane.showConfirmDialog(view.getDialogUpdate(), "Vui lòng xác nhận chắc chắn muốn đóng PR.", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if(confirmResult == JOptionPane.NO_OPTION){
                return;
            }
            int rowCount = view.getTablePRupdate().getRowCount();
            ArrayList<PurchaseRequest> prList = new ArrayList();
            for (int i = 0; i < rowCount; i++){
                PurchaseRequest pr = new PurchaseRequest();
                String trangThaiStr = String.valueOf(view.getTablePRupdate().getValueAt(i, 0));
                System.out.println(trangThaiStr);
                if (trangThaiStr.equals("Đang xử lý")){
                    pr.setTrangThai(2); // 2: Đóng (inactive)
                }
                else {
                    pr.encodeTrangThai(trangThaiStr);
                }
                pr.setSoCT(Integer.parseInt(view.getFieldSoCT_update().getText()));
                pr.setNgaySua(view.getDate_update().getDate());
                pr.setItemLine((int) view.getTablePRupdate().getValueAt(i, 1));
                
                prList.add(pr);
            }
            
            try {
                model.updateDBClose(prList);
            } catch (SQLException ex) {
                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int i = 0; i < rowCount; i++){
                view.getTablePRupdate().setValueAt(prList.get(i).getTrangThaiStr(), i, 0);
            }

        }
    }

    //Action khi nút "Xoá" của màn hình quản lý danh sách PR được nhấn -> Xoá hoàn toàn khỏi giao diện người dùng nhưng thực chất chỉ ẩn đi và vẫn tồn tại ở CSDL
    private class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnDelete is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            if (view.getTbPR().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng PR cần xoá");
                return;
            }
            int confirmResult = JOptionPane.showConfirmDialog(null, "Vui lòng xác nhận chắc chắn muốn xoá", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if(confirmResult == JOptionPane.NO_OPTION){
                return;
            }
            int selRow = view.getTbPR().getSelectedRow();
            int deleteSoCT = (int)view.getTbPR().getValueAt(selRow, 0);
            int deleteItemLine = (int)view.getTbPR().getValueAt(selRow, 5);
            
            try {
                model.updateDB(deleteSoCT, deleteItemLine);
            } catch (SQLException ex) {
                Logger.getLogger(PRController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            view.getTableERP().removeRow(selRow);
            //view.updateTbPR();
            JOptionPane.showMessageDialog(view, "Đã xoá thành công!");
            
        }
    }







}
