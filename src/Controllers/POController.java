/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Item;
import Models.PurchaseOrder;
import Models.PurchaseRequest;
import Models.User;
import Models.Vendor;
import Services.POManager;
import Views.PRView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Utility.CurrencyUtils;
import Views.POView;
import Views.TableERP;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.TableModel;


/**
 * https://tips4java.wordpress.com/2008/12/12/table-stop-editing/
 * @author Ricardo
 */
public class POController {
    private POManager model;
    private POView view;
    private User loginUser;
//    private ItemManager modelItem;
    private ItemController itemCtl;
    private VendorController vendorCtl;
    private PRController prCtl;
    
    public POController(POManager poModel, POView poView) {
        this.model = poModel;
        this.view = poView;
        this.view.btnLoadActionListener(new LoadActionListener());
        this.view.btnSearchActionListener(new SearchActionListener());
        this.view.btnCalItemPriceActionListener(new CalItemPriceActionListener());
        this.view.btnAddActionListener(new AddActionListener());
        this.view.btnDiagTimPRaddActionListener(new TimPRaddActionListener());
        this.view.btnLoadPRActionListener(new LoadPRActionListener());
        this.view.btnSearchPRActionListener(new SearchPRActionListener());
        this.view.btnSelectPRActionListener(new SelectPRActionListener());
        this.view.btnRemove_addActionListener(new RemoveAddActionListener());
        this.view.btnDiagTimNCCaddActionListener(new TimNCCaddActionListener());
        this.view.btnLoadVendorActionListener(new LoadVendorActionListener());
        this.view.btnSearchVendorActionListener(new SearchVendorActionListener());
        this.view.btnSelVendorActionListener(new SelVendorActionListener());
        this.view.btnTinhTongPOdraftActionListener (new TinhTongPOdraftActionListener());
        this.view.btnCreateActionListener(new CreateActionListener());
        
        

        
//        this.view.btnUpdateActionListener(new UpdateActionListener());
//        this.view.DialogUpdateActionListener(new DialogUpdateActionListener());
//        this.view.btnCloseActionListener(new CloseActionListener());
//        this.view.btnDialogAddActionListener(new DialogAddActionListener());
//        this.view.btnUpdateActionListener(new UpdateActionListener());
//        this.view.btnDialogUpdateActionListener(new DialogUpdateActionListener());
//        this.view.btnDeleteActionListener(new DeleteActionListener());
        
        
    }

    public POManager getModel() {
        return model;
    }

    public void setModel(POManager model) {
        this.model = model;
    }

    public void setOtherModel(User loginUser, ItemController itemController, VendorController vendorController, PRController pRController){
        this.loginUser = loginUser;
        this.itemCtl = itemController;  
        this.vendorCtl = vendorController;
        this.prCtl = pRController;
    }

    public POView getView() {
        return view;
    }


    // Action khi nút Load ở panel "danh sách PO" được nhấn
    private class LoadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoad is clicked");
            try {
                model.loadData_DB();
                Object[][] dsObjPO = model.getObjDsPO();   
                view.setColumn(PurchaseOrder.columns);
                view.setData(dsObjPO);
                view.loadData();   
            } catch (SQLException ex) {
                Logger.getLogger(POController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSearch is clicked");
            String[] paramSearch = view.getSearchParams();
            Object[][] trackObjPO;
            trackObjPO = view.getTableERP().searchByCriteria(paramSearch, new int[]{0, 1}, "match");
            view.getTableERP().setData(trackObjPO, PurchaseOrder.columns, view.getTbPO());
        }
    }
    
    // Action khi nút "Tính tổng" của Panel "Quản lý danh sách PO" được click
    private class CalItemPriceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnCalItemPrice is clicked");
            //if (model.getDsPO() == null){
            if (view.getTableERP() == null) {
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            double totalPrice = view.getTableERP().capNhatTongGia(13, 14, 16, 15);
            System.out.println("totalPrice: " + totalPrice);
            view.updateTbPO();
            view.getFieldTotalPrice().setText(CurrencyUtils.VN_FORMAT.format(totalPrice));    
        }
    }
    
    
    // Action khi nút Thêm của Panel "Quản lý danh sách PO" được click
    private class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnAdd is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            // Tìm số PO lớn nhất hoặc theo format (6 chữ số): 2 (đại diện PO) + YY (2 số cuối của năm) + 0000
            String lastYear2Digit = String.valueOf(Year.now().getValue() % 100);
            int maxSoCT = Integer.parseInt("2" + lastYear2Digit + "0000");
            for (PurchaseOrder po : model.getDsPO()){
                if (po.getSoCT() > maxSoCT){
                    maxSoCT = po.getSoCT();
                }
            }
            view.getFieldSoCT_add().setText(String.valueOf(maxSoCT + 1));
            view.getDate_add().setDate(new Date()); // set ngày PR hiện tại
            view.getFieldUser_add().setText(loginUser.getTenTK()); // set người tạo PR là tài khoản đang dùng
            //view.setColumnPOdraft(new String[] {"Số PR", "PR line", "Mã hàng", "Tên hàng", "Mã NCC", "Tên NCC", "ĐVT", "Đơn giá", "Số lượng", "VAT", "Tổng giá"});
            view.setColumnPOdraft(PurchaseOrder.columns);
            view.setDataPOdraft(new Object[0][0]);
            view.initTablePOdraft();
            view.getDialogAdd().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
            view.getDialogAdd().setVisible(true);
        }
    }
    
    // Action khi nút "Tìm PR" của Dialog "PO draft" dược nhấn
    private class TimPRaddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnTimPR_add is click");
            if (view.getTablePR() != null){
                view.getTablePR().setRowCount(0); // Xoá tất cả dữ liệu cũ để tránh đã add PR vào PO draft rồi vẫn hiện lại ds cũ.
            }
            view.getDialogTimPR().pack();
            view.getDialogTimPR().setVisible(true);
            
        }
    }
    
    // Action khi nút "Load" của Dialog "Tìm PR" được nhấn
    private class LoadPRActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoadPR is clicked");
            ArrayList<PurchaseRequest> listPRleftover = new ArrayList();
            try {
                listPRleftover = prCtl.getModel().loadData_DB(0); // Lấy các PR có đang có trạng thái active (0)
            } catch (SQLException ex) {
                Logger.getLogger(POController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (view.getTablePOdraft() != null){
                int rowCount = view.getTablePOdraft().getRowCount();
                for (int i = 0; i < rowCount; i++){
                    Iterator<PurchaseRequest> iterator = listPRleftover.iterator();
                    while (iterator.hasNext()) {
                        PurchaseRequest pr = iterator.next();
                        if ( String.valueOf(pr.getSoCT()).equals(String.valueOf(view.getTablePOdraft().getValueAt(i, 0))) && String.valueOf(pr.getItemLine()).equals(String.valueOf(view.getTablePOdraft().getValueAt(i, 1))) ){
                            System.out.println("iterator: " + iterator.toString());
                            iterator.remove();
                            break;
                        }
                    }
                }
                
                prCtl.getModel().setDsPR(listPRleftover);
            }
            
            Object[][] dsObjPR = prCtl.getModel().getObjDsPR();
            System.out.println("leftover prlist Object: " + dsObjPR.length);
            view.setColumnPR(PurchaseRequest.columns);
            view.setDataPR(dsObjPR);
            view.loadDataPR();
            //view.getTablePR().setRowCount(0);
            view.getDialogTimPR().setVisible(true);
        }
    }
    
    // Action Tìm PR theo điều kiện trong danh sách pending list trong Dialog "Tìm PR"
    private class SearchPRActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSearchPR is clicked");
            if (view.getTablePR()== null){
                JOptionPane.showMessageDialog(null, "Vui lòng Load dữ liệu trước");
            }
            String[] paramSearch = view.getSearchParamPR();
            Object[][] trackObjPR;
            trackObjPR = view.getTablePR().searchByCriteria(paramSearch, new int[]{0, 1}, "match");
            view.getTablePR().setData(trackObjPR, PurchaseRequest.columns, view.getTbPR());

        }
    }
    
    // Action khi nút "Thêm" ở Dialog "Danh sách pending PR" được nhấn
    private class SelectPRActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSelectPR is clicked");
            int[] selRows = view.getTbPR().getSelectedRows();
            boolean selRow = true;
            int[] selColumns = new int[] {0, 5, 6, 7, 8, 9, 10, 11}; // soCT, itemLine, maHang, tenHang, dvt, donGia, soLuong, itemPrice
            boolean selCol = true;
            Object[][] listObjData = view.getTablePR().filter(selRows, selRow, selColumns, selCol);
            view.getTablePOdraft().add(new int[]{6, 7, 8, 9, 10, 13, 14, 16}, listObjData, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
            view.getTablePOdraft().setData(view.getTbPOdraft());
            view.getDialogTimPR().dispose(); 
        }
    }
    
    //Action khi nút "Xoá Item" của Dialog "Tạo draft PO" được nhấn
    private class RemoveAddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnRemove_add is clicked");
            if (view.getTbPOdraft().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng PR cần bỏ ra");
                return;
            }
            int[] selRows = view.getTbPOdraft().getSelectedRows();
            view.getTablePOdraft().removeRow(selRows);
            view.getTablePOdraft().setData(view.getTbPOdraft());
        }
    }
    
    // Action khi nút "Chọn NCC" ở Dialog "Tạo đơn hàng (PO)" được nhấn
    private class TimNCCaddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnTimNCC_add is clicked");
            view.getDialogTimVendor().pack();
            view.getDialogTimVendor().setVisible(true);
        }
    }
    
    // Action khi nút "Load" của Dialog "Tìm NCC" được nhấn
    private class LoadVendorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoadVendor is clicked");
            try {
                vendorCtl.getModel().loadData_DB();
            } catch (SQLException ex) {
                Logger.getLogger(POController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object[][] dsObjVendor = vendorCtl.getModel().getObjDsVendor();               
            view.setColumnVendor(Vendor.columns);
            view.setDataVendor(dsObjVendor);
            view.loadDataVendor();   
        }
    }
    
    // Action khi nút "Tìm" của Dialog "DS NCC" được nhấn
    private class SearchVendorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSearchVendor is clicked");
            if (view.getTableVendor() == null){
                JOptionPane.showMessageDialog(null, "Vui lòng Load dữ liệu trước");
            }
            String[] paramSearch = view.getSearchParamVendor();
            Object[][] trackObjVendor;
            trackObjVendor = view.getTableVendor().searchByCriteria(paramSearch, new int[]{0, 1}, "match");
            view.getTableVendor().setData(trackObjVendor, Vendor.columns, view.getTbVendor());
            
        }
    }

    private class SelVendorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSelectVendor is clicked");
            view.getVendorInfo();
            view.getDialogTimVendor().dispose(); 
        }
    }
    
    //Action khi nút "Tính tổng" của Dialog PO draft được nhấn
    private class TinhTongPOdraftActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnTinhTongPOdraft is clicked");
            if (view.getTbPOdraft().isEditing()){
                view.getTbPOdraft().getCellEditor().stopCellEditing(); // tắt edit để lưu data vào TableModel
            }
            float vat = Float.parseFloat(view.getFieldVAT_add().getText());
            for (int i = 0; i < view.getTablePOdraft().getRowCount(); i++){
                view.getTablePOdraft().setValueAt(vat, i, 15);
            }
            
            double tong = view.getTablePOdraft().capNhatTongGia(13, 14, 16, 15);
            view.getFieldTongPOdraft().setText(CurrencyUtils.format(String.valueOf(tong)));
        }
    }
    
    // Action khi nút "Tạo PO" của Dialog "Tạo PO" được nhấn
    private class CreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnCreate is clicked");
            int rowCount = view.getTbPOdraft().getRowCount();
            if (rowCount == 0){
                JOptionPane.showMessageDialog(null, "Không có PR được chọn");
                return;
            }
            ArrayList<PurchaseOrder> newPOlist = new ArrayList();
            for (int i = 0; i < rowCount; i++){
                int soCT = Integer.parseInt(String.valueOf(view.getFieldSoCT_add().getText()));
                int maNCC = Integer.parseInt(String.valueOf(view.getFieldMaNCC_add().getText()));
                PurchaseOrder po = new PurchaseOrder();
                po.setSoCT(soCT);
                po.setUser(view.getFieldUser_add().getText());
                po.setNgayTao(view.getDate_add().getDate());
                po.setNgaySua(view.getDate_add().getDate());
                po.setTrangThai(0);
                po.setItemLine(i+1);
                po.setSoPR((int)(view.getTablePOdraft().getValueAt(i, 6)));
                po.setPRline((int)(view.getTablePOdraft().getValueAt(i, 7)));
                po.setMaHang((int)(view.getTablePOdraft().getValueAt(i, 8)));
                po.setTenHang(String.valueOf(view.getTablePOdraft().getValueAt(i, 9)));
                po.setDvt(String.valueOf(view.getTablePOdraft().getValueAt(i, 10)));
                po.setMaNCC(maNCC);
                po.setTenNCC(view.getFieldTenNCC_add().getText());
                po.setGia((long)view.getTablePOdraft().getValueAt(i, 13));
                po.setSoLuong((int)view.getTablePOdraft().getValueAt(i, 14));
                if (String.valueOf(view.getTablePOdraft().getValueAt(i, 15)).isBlank()){
                    po.setVat(Float.parseFloat(String.valueOf(view.getFieldVAT_add().getText())));
                }
                else {
                    po.setVat((float) view.getTablePOdraft().getValueAt(i, 15));
                }
                po.setGiaItem((double) view.getTablePOdraft().getValueAt(i, 16));
                double giaPO = CurrencyUtils.parseToDouble(view.getFieldTongPOdraft().getText());
                po.setGiaDonHang(giaPO);
                
                //System.out.println(po);
                newPOlist.add(po);
            }
            
            try {
                model.addDB(newPOlist);
            } catch (SQLException ex) {
                Logger.getLogger(POController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (PurchaseOrder po : newPOlist){
                Object[] objPO = po.getObjPO();
                view.getTableERP().addRow(objPO);
            }
            
            view.getDialogAdd().dispose();
            //view.updateTbPO();
            //JOptionPane.showMessageDialog(view.getDialogAdd(), "Vui lòng \"Load\" lại dữ liệu để cập nhật mới nhất");
            //view.getDialogAdd().setVisible(false);
            
        }
    }




//    private class CloseActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnClose is clicked");
//            int confirmResult = JOptionPane.showConfirmDialog(view.getDialogUpdate(), "Vui lòng xác nhận chắc chắn muốn đóng PR.", "Xác nhận", JOptionPane.YES_NO_OPTION);
//            if(confirmResult == JOptionPane.NO_OPTION){
//                return;
//            }
//            int rowCount = view.getTablePRupdate().getRowCount();
//            ArrayList<PurchaseRequest> prList = new ArrayList();
//            for (int i = 0; i < rowCount; i++){
//                PurchaseRequest pr = new PurchaseRequest();
//                String trangThaiStr = String.valueOf(view.getTablePRupdate().getValueAt(i, 0));
//                System.out.println(trangThaiStr);
//                if (trangThaiStr.equals("Đang xử lý")){
//                    pr.setTrangThai(2); // 2: Đóng (inactive)
//                }
//                else {
//                    pr.encodeTrangThai(trangThaiStr);
//                }
//                pr.setSoCT(Integer.parseInt(view.getFieldSoCT_update().getText()));
//                pr.setNgaySua(view.getDate_update().getDate());
//                pr.setItemLine((int) view.getTablePRupdate().getValueAt(i, 1));
//                
//                prList.add(pr);
//            }
//            
//            try {
//                model.updateDBClose(prList);
//            } catch (SQLException ex) {
//                Logger.getLogger(POController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            for (int i = 0; i < rowCount; i++){
//                view.getTablePRupdate().setValueAt(prList.get(i).getTrangThaiStr(), i, 0);
//            }
//                
//
//        }
//    }

   
    

//    private class DialogUpdateActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnUpdate_update is clicked");
//            // Phải stop edit JTable thì mới lưu dữ liệu vào bảng được.
//            if (view.getTbPRupdate().isEditing())
//                view.getTbPRupdate().getCellEditor().stopCellEditing();
//
////            // Print out the data in the JTable
////            for (int row = 0; row < view.getTbPRupdate().getRowCount(); row++) {
////                for (int col = 0; col < view.getTbPRupdate().getColumnCount(); col++) {
////                    System.out.print(view.getTbPRupdate().getValueAt(row, col) + " ");
////                }
////                System.out.println();
////            }
////
////            // Print out the data in the TableModel
////            TableModel modelTable = view.getTbPRupdate().getModel();
////            for (int row = 0; row < modelTable.getRowCount(); row++) {
////                for (int col = 0; col < modelTable.getColumnCount(); col++) {
////                    System.out.print(modelTable.getValueAt(row, col) + " ");
////                }
////                System.out.println();
////            }
//            
//            view.updateTbPO();
//            view.setVisible(true);
//            int soCT = Integer.parseInt(view.getFieldSoCT_update().getText());
//            String user = view.getFieldUser_update().getText();
//            Date date = view.getDate_update().getDate();
//            //System.out.println(date.toString());
//            ArrayList<PurchaseRequest> prList = new ArrayList();
//            int rowCount = view.getTbPRupdate().getRowCount();
//            for (int i = 0; i < rowCount; i++){
//                PurchaseRequest updatePR = view.getUpdatePRinfo(i);
//                updatePR.setSoCT(soCT);
//                updatePR.setUser(user);
//                updatePR.setNgaySua(date);
//                updatePR.setNgayTao(date);
//
//                prList.add(updatePR);
//            }
//            
////            for (PurchaseRequest pr: prList){
////                System.out.println(pr);
////            }
//            System.out.println("số lượng pr sẽ update: " + prList.size());
//            
//            try {
//                model.updateDB(prList);
//            } catch (SQLException ex) {
//                Logger.getLogger(POController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            for (PurchaseRequest pr : prList){
//                Object[] objPR = pr.getObjPR();
//                int tbERProwCount = view.getTableERP().getRowCount();
//                for (int i = 0; i < tbERProwCount; i++){
//                    String tbPRsoCT = String.valueOf(view.getTableERP().getValueAt(i, 0));
//                    String tbPRitemLine = String.valueOf(view.getTableERP().getValueAt(i, 5));
//                    if (tbPRsoCT.equals(String.valueOf(objPR[0])) && tbPRitemLine.equals(String.valueOf(objPR[5]))){
//                        view.getTableERP().sua(i, objPR);
//                        for (int k = 0; k < objPR.length; k++){
//                            System.out.println(tbPRsoCT + String.valueOf(objPR[0]));
//                            System.out.println(tbPRitemLine + String.valueOf(objPR[5]));
//                            System.out.print(objPR[k]);
//                        }
//                    }
//                    else {
//                        //System.out.println("Khong map: " + tbPRsoCT + "\t" + tbPRitemLine);
//                    }
//                }
//               
//            }
//            //Object[] rowData = updateItem.getObjectItem();
//            //view.getTableERP().sua(view.getSelRow(), rowData);
//            view.updateTbPO();
//            view.getDialogUpdate().dispose();
//            
//        }
//    }

//    // Action khi nút "Sửa" của quản lý PR được nhấn   
//    private class UpdateActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnUpdate is clicked");
//            if (view.getTableERP() == null){
//                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
//                return;
//            }
//            if (view.getTbPO().getSelectedRow() == -1){
//                JOptionPane.showMessageDialog(view, "Vui lòng chọn PR cần sửa");
//                return;
//            }
//            
//            String[] columPRupdate = {"Trạng thái", "ItemLine", "Mã hàng", "Tên hàng", "ĐVT", "Giá est", "Số lượng", "Tổng giá"};
//            view.setColumPRupdate(columPRupdate);
//            
//            
//            int selRow = view.getTbPO().getSelectedRow();
//            String selSoCT = String.valueOf(view.getTbPO().getValueAt(selRow, 0));
//            view.getFieldSoCT_update().setText(selSoCT);
//            view.getDate_update().setDate(new Date()); // set ngày PR hiện tại
//            view.getFieldUser_update().setText(loginUser.getTenTK()); // set người tạo PR là tài khoản đang dùng
//               
//            int rowCount = view.getTbPO().getRowCount();
//            int soCTitemCount = 0;
//            for (int i = 0; i < rowCount; i++){
//                if (selSoCT.equals(String.valueOf(view.getTableERP().getValueAt(i, 0)))){
//                    soCTitemCount++;
//                }
//            }
//            
//            Object[][] selData = new Object[soCTitemCount][columPRupdate.length];
//            int selSoCTitems = 0;
//            for (int i = 0; i < rowCount; i++){
//                if (String.valueOf(view.getTableERP().getValueAt(i, 0)).equals(selSoCT)){
//                    System.out.println("Khớp " + view.getTbPO().getValueAt(i, 0));
//                    Object[] data = view.getTableERP().getDataVector().get(i).toArray();
//                    for (int j = 0; j < columPRupdate.length; j++){
//                        selData[selSoCTitems][j] = data[j+4]; // [j+4] vì bỏ 4 cột đầu: soCT, nguoiTao, ngày tạo/sửa
//                        //System.out.print(selData[selSoCTitems][j] + "\t");    
//                    }
//                    selSoCTitems++;
//                }
//            }
//            view.setDataPRupdate(selData);
//            view.initTablePRUpdate();
//            view.getDialogUpdate().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
//            view.getDialogUpdate().setVisible(true);
//        }
//    }

//    //Action khi nút "Xoá" của màn hình quản lý danh sách PR được nhấn
//    private class DeleteActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnDelete is clicked");
//            if (view.getTableERP() == null){
//                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
//                return;
//            }
//            if (view.getTbPO().getSelectedRow() == -1){
//                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng PR cần xoá");
//                return;
//            }
//            int confirmResult = JOptionPane.showConfirmDialog(null, "Vui lòng xác nhận chắc chắn muốn xoá", "Xác nhận", JOptionPane.YES_NO_OPTION);
//            if(confirmResult == JOptionPane.NO_OPTION){
//                return;
//            }
//            int selRow = view.getTbPO().getSelectedRow();
//            int deleteSoCT = (int)view.getTbPO().getValueAt(selRow, 0);
//            int deleteItemLine = (int)view.getTbPO().getValueAt(selRow, 5);
//            
//            try {
//                model.updateDB(deleteSoCT, deleteItemLine);
//            } catch (SQLException ex) {
//                Logger.getLogger(POController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            view.getTableERP().removeRow(selRow);
//            view.updateTbPO();
//            JOptionPane.showMessageDialog(view, "Đã xoá thành công!");
//            
//        }
//    }


    
   
    


//    // Action khi nút "Thêm" của Dialog "Tạo PR" được nhấn
//    private class AddItem_addActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnAdd_add is clicked");
//            //int rowCount = view.getTbPOdraft().getRowCount();     
//            Object[] newItem = view.addNewItem();
//            for (int i = 0; i < view.getTablePRdraft().getRowCount(); i++){
//                if (String.valueOf(view.getTablePRdraft().getValueAt(i, 0)).equals(String.valueOf(newItem[0])) ){
//                    JOptionPane.showMessageDialog(view.getDialogAdd(), "Trùng mã item, vui lòng chọn lại");
//                    return;
//                }
//            }
//            
//            view.getTablePRdraft().addRow(newItem);
//            view.updateTbPOdraft();
//            
//        }
//    }

//    // Action khi nút "Thêm" của Dialog "Tìm Item" được nhấn
//    private class AddItemInfoActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnAddItemInfo is clicked");
//            view.getItemInfo();
//            view.getDialogTimItem().setVisible(false);
//            
//        }
//    }
//
//    // Action khi nút "Tìm kiếm" của Dialog "Tìm item" được nhấn.
//    private class SearchItemActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnSearchItem is clicked");
//            
//            String[] paramSearch = view.getSearchParamItem();
//            Object[][] trackObjItem2D;
//            try {
//                trackObjItem2D = itemCtl.SearchItembyCriteria(itemCtl.getModel(), paramSearch);
//                view.setColumnItem(Item.getColumns());
//                view.setDataItem(trackObjItem2D);
//                view.loadDataItem();
//            } catch (SQLException ex) {
//                Logger.getLogger(POController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

//    // Action khi nút Load của Dialog "Tìm Item" được click
//    private class LoadFindItemActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnLoadItem_add is clicked");
//            try {
//                itemCtl.getModel().loadData_DB();
//                Object[][] dsObjItem = itemCtl.getModel().getObjDsItem();
//                view.setColumnItem(Item.getColumns());
//                view.setDataItem(dsObjItem);
//                view.loadDataItem();
//            } catch (SQLException ex) {
//                Logger.getLogger(POController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//    
//    // Action khi nút Tìm của Dialog "Tìm Item" được click
//    private class DialogTimIemActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            view.getDialogTimItem().pack();
//            view.getDialogTimItem().setVisible(true);
//        }
//    }
    

    
    
    
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
//            if (view.getTbPO().getSelectedRow() == -1){
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
//            if (view.getTbPO().getSelectedRow() == -1){
//                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng PR cần sửa");
//                return;
//            }
//            int selRow = view.getTbPO().getSelectedRow();
//            //int deleteMaNCC = Integer.parseInt(String.valueOf(view.getTbPO().getValueAt(selRow, 0)));
//            int deleteMaNCC = (int)view.getTbPO().getValueAt(selRow, 0);
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
