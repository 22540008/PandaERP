/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.GoodsReceipt;
import Models.Item;
import Models.PurchaseOrder;
import Models.PurchaseRequest;
import Models.Transaction;
import Models.User;
import Models.Vendor;
import Services.GRManager;
import Views.PRView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Utility.CurrencyUtils;
import Views.GRView;
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
public class GRController {
    private GRManager model;
    private GRView view;
    private User loginUser;
    private ItemController itemCtl;
    private VendorController vendorCtl;
    private PRController prCtl;
    private POController poCtl;
    
    private int[] updateIndex; // mảng lưu các rowIndex khi edit PO
    private Object[][] updateListObjPO; // mảng lưu các 
    
    public GRController(GRManager poModel, GRView poView) {
        this.model = poModel;
        this.view = poView;
        this.view.btnLoadActionListener(new LoadActionListener());
        this.view.btnSearchActionListener(new SearchActionListener());
        this.view.btnCalItemPriceActionListener(new CalItemPriceActionListener());
        this.view.btnAddActionListener(new AddActionListener());
        this.view.btnDiagTimPOAddActionListener(new TimPOAddActionListener());
        this.view.btnLoadPOActionListener(new LoadPOActionListener());
//        this.view.btnSearchPOActionListener(new SearchPOActionListener());
        this.view.btnSelectPORActionListener(new SelectPOActionListener());
        this.view.btnRemoveAddActionListener(new RemoveAddActionListener());
//        this.view.btnDiagTimNCCaddActionListener(new TimNCCaddActionListener());
//        this.view.btnLoadVendorActionListener(new LoadVendorActionListener());
//        this.view.btnSearchVendorActionListener(new SearchVendorActionListener());
//        //this.view.btnSelVendorActionListener(new SelVendorActionListener());
//        this.view.btnTinhTongPOdraftActionListener (new TinhTongPOdraftActionListener());
//        this.view.btnCreateActionListener(new CreateActionListener());
//        this.view.btnEditActionListener(new EditActionListener());
//        this.view.btnDiagTimNCCupdateActionListener(new TimNCCaddActionListener());
//        this.view.btnUpdateActionListener(new UpdateActionListener());
//        this.view.btnCloseActionListener(new CloseActionListener());
//        this.view.btnDeleteActionListener(new DeleteActionListener());
 
        
    }

    public GRManager getModel() {
        return model;
    }

    public void setModel(GRManager model) {
        this.model = model;
    }

    public void setOtherModel(User loginUser, ItemController itemController, VendorController vendorController, PRController prController, POController poController){
        this.loginUser = loginUser;
        this.itemCtl = itemController;  
        this.vendorCtl = vendorController;
        this.prCtl = prController;
        this.poCtl = poController;
    }

    public GRView getView() {
        return view;
    }
    

    // Action khi nút Load ở panel "danh sách PO" được nhấn
    private class LoadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoad is clicked");
            try {
                model.loadData_DB();
                Object[][] dsObjGR = model.getObjDsGR();   
                view.setColumn(GoodsReceipt.columns);
                view.setData(dsObjGR);
                view.loadData();   
            } catch (SQLException ex) {
                Logger.getLogger(GRController.class.getName()).log(Level.SEVERE, null, ex);
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
            // Tìm số GR lớn nhất hoặc theo format (6 chữ số): 2 (đại diện PO) + YY (2 số cuối của năm) + 0000
            String lastYear2Digit = String.valueOf(Year.now().getValue() % 100);
            int maxSoCT = Integer.parseInt("3" + lastYear2Digit + "0000");
            for (GoodsReceipt gr : model.getDsGR()){
                if (gr.getSoCT() > maxSoCT){
                    maxSoCT = gr.getSoCT();
                }
            }
            view.getFieldSoCT_add().setText(String.valueOf(maxSoCT + 1));
            view.getDate_add().setDate(new Date()); // set ngày PR hiện tại
            view.getFieldUser_add().setText(loginUser.getTenTK()); // set người tạo PR là tài khoản đang dùng
            view.setColumnGRdraft(GoodsReceipt.columns);
            view.setDataGRdraft(new Object[0][0]);
            view.initTableGRdraft();
            view.getDialogAdd().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
            view.getDialogAdd().setVisible(true);
        }
    }
    
    // Action khi nút "Tìm PR" của Dialog "PO draft" dược nhấn
    private class TimPOAddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnTimPO_add is click");
            if (view.getTablePO() != null){
                view.getTablePO().setRowCount(0); // Xoá tất cả dữ liệu cũ để tránh đã add PR vào PO draft rồi vẫn hiện lại ds cũ.
            }
            view.getDialogTimPO().pack();
            view.getDialogTimPO().setVisible(true);
            
        }
    }
    
    // Action khi nút "Load" của Dialog "Tìm PR" được nhấn
    private class LoadPOActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnLoadPO is clicked");
            //String[] paramSearch = view.getSearchParamPO();
            String soPOStr = view.getFieldSearchSoCTPO().getText();
            if (soPOStr.isBlank()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số PO");
            }
            
            ArrayList<PurchaseOrder> listPOleftover = new ArrayList();
            try {
                listPOleftover = poCtl.getModel().loadData_DB(Integer.parseInt(soPOStr)); // Lấy các PO có đang có trạng thái active, processed (0, 3)
            } catch (SQLException ex) {
                Logger.getLogger(GRController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Remove các dòng pending PO mà đã thêm vào GR draft thông qua soPO
            if (view.getTableGRdraft() != null){
                int rowCount = view.getTableGRdraft().getRowCount();
                for (int i = 0; i < rowCount; i++){
                    Iterator<PurchaseOrder> iterator = listPOleftover.iterator();
                    while (iterator.hasNext()) {
                        PurchaseOrder po = iterator.next();
                        // So khớp cột 6 (số PO) và cột 7 (PO line) của tbGR draft, nếu trùng thì loại bỏ ra kết quả pending GR
                        if ( String.valueOf(po.getSoCT()).equals(String.valueOf(view.getTableGRdraft().getValueAt(i, 6))) && String.valueOf(po.getItemLine()).equals(String.valueOf(view.getTableGRdraft().getValueAt(i, 7))) ){
                            System.out.println("iterator: " + iterator.toString());
                            iterator.remove();
                            break;
                        }
                    }
                }
                
                poCtl.getModel().setDsPO(listPOleftover);
            }
            
            Object[][] dsObjPO = poCtl.getModel().getObjDsPO();
            System.out.println("leftover polist Object: " + dsObjPO.length);
            view.setColumnPO(PurchaseOrder.columns);
            view.setDataPO(dsObjPO);
            view.loadDataPO();
            view.getDialogTimPO().setVisible(true);
        }
    }
    
//    // Action Tìm PR theo điều kiện trong danh sách pending list trong Dialog "Tìm PR"
//    private class SearchPOActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnSearchPR is clicked");
////            if (view.getTablePO()== null){
////                JOptionPane.showMessageDialog(null, "Vui lòng Load dữ liệu trước");
////            }
//            String[] paramSearch = view.getSearchParamPO();
//            Object[][] trackObjPO;
//            trackObjPO = view.getTablePO().searchByCriteria(paramSearch, new int[]{0}, "match");
//            view.getTablePO().setData(trackObjPO, PurchaseRequest.columns, view.getTbPR());
//
//        }
//    }
    
    // Action khi nút "Thêm" ở Dialog "Danh sách pending PR" được nhấn
    private class SelectPOActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSelectPR is clicked");
            int[] selRows = view.getTbPR().getSelectedRows();
            boolean selRow = true;
            int[] selColumns = new int[] {0, 5, 6, 7, 8, 9, 10, 11, 12, 17}; // soPO, PoLine, soPR, PrLine, maHang, tenHang, dvt, maNCC, tenNCC, pending qty
            boolean selCol = true;
            Object[][] listObjData = view.getTablePO().filter(selRows, selRow, selColumns, selCol);
            view.getTableGRdraft().add(new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, listObjData, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
            view.getTableGRdraft().setData(view.getTbGRdraft());
            view.getDialogTimPO().dispose(); 
        }
    }
    
    //Action khi nút "Xoá Item" của Dialog "Tạo draft PO" được nhấn
    private class RemoveAddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnRemove_add is clicked");
            if (view.getTbGRdraft().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng PR cần bỏ ra");
                return;
            }
            int[] selRows = view.getTbGRdraft().getSelectedRows();
            view.getTableGRdraft().removeRow(selRows);
            view.getTableGRdraft().setData(view.getTbGRdraft());
        }
    }
    
//    // Action khi nút "Chọn NCC" ở Dialog "Tạo đơn hàng (PO)" được nhấn
//    private class TimNCCaddActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnTimNCC_add is clicked");
//            view.getDialogTimVendor().pack();
//            view.getDialogTimVendor().setVisible(true);
//        }
//    }
//    
//    // Action khi nút "Load" của Dialog "Tìm NCC" được nhấn
//    private class LoadVendorActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnLoadVendor is clicked");
//            try {
//                vendorCtl.getModel().loadData_DB();
//            } catch (SQLException ex) {
//                Logger.getLogger(GRController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            Object[][] dsObjVendor = vendorCtl.getModel().getObjDsVendor();               
//            view.setColumnVendor(Vendor.columns);
//            view.setDataVendor(dsObjVendor);
//            view.loadDataVendor();   
//        }
//    }
//    
//    // Action khi nút "Tìm" của Dialog "DS NCC" được nhấn
//    private class SearchVendorActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnSearchVendor is clicked");
//            if (view.getTableVendor() == null){
//                JOptionPane.showMessageDialog(null, "Vui lòng Load dữ liệu trước");
//            }
//            String[] paramSearch = view.getSearchParamVendor();
//            Object[][] trackObjVendor;
//            trackObjVendor = view.getTableVendor().searchByCriteria(paramSearch, new int[]{0, 1}, "match");
//            view.getTableVendor().setData(trackObjVendor, Vendor.columns, view.getTbVendor());
//            
//        }
//    }
//
//    private class AddVendorActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnSelectVendor is clicked");
//            view.addVendorInfo();
//            view.getDialogTimVendor().dispose(); 
//        }
//    }
//    
//    //Action khi nút "Tính tổng" của Dialog PO draft được nhấn
//    private class TinhTongPOdraftActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnTinhTongPOdraft is clicked");
//            if (view.getTbGRdraft().isEditing()){
//                view.getTbGRdraft().getCellEditor().stopCellEditing(); // tắt edit để lưu data vào TableModel
//            }
//            float vat = Float.parseFloat(view.getFieldVAT_add().getText());
//            for (int i = 0; i < view.getTablePOdraft().getRowCount(); i++){
//                view.getTablePOdraft().setValueAt(vat, i, 15);
//            }
//            
//            double tong = view.getTablePOdraft().capNhatTongGia(13, 14, 16, 15);
//            view.getFieldTongPOdraft().setText(CurrencyUtils.format(String.valueOf(tong)));
//        }
//    }
//    
//    // Action khi nút "Tạo PO" của Dialog "Tạo PO" được nhấn
//    private class CreateActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnCreate is clicked");
//            int rowCount = view.getTbGRdraft().getRowCount();
//            if (rowCount == 0){
//                JOptionPane.showMessageDialog(null, "Không có PR được chọn");
//                return;
//            }
//            ArrayList<PurchaseOrder> newPOlist = new ArrayList();
//            for (int i = 0; i < rowCount; i++){
//                int soCT = Integer.parseInt(String.valueOf(view.getFieldSoCT_add().getText()));
//                int maNCC = Integer.parseInt(String.valueOf(view.getFieldMaNCC_add().getText()));
//                PurchaseOrder po = new PurchaseOrder();
//                po.setSoCT(soCT);
//                po.setUser(view.getFieldUser_add().getText());
//                po.setNgayTao(view.getDate_add().getDate());
//                po.setNgaySua(view.getDate_add().getDate());
//                po.setTrangThai(0);
//                po.setItemLine(i+1);
//                po.setSoPR((int)(view.getTablePOdraft().getValueAt(i, 6)));
//                po.setPRline((int)(view.getTablePOdraft().getValueAt(i, 7)));
//                po.setMaHang((int)(view.getTablePOdraft().getValueAt(i, 8)));
//                po.setTenHang(String.valueOf(view.getTablePOdraft().getValueAt(i, 9)));
//                po.setDvt(String.valueOf(view.getTablePOdraft().getValueAt(i, 10)));
//                po.setMaNCC(maNCC);
//                po.setTenNCC(view.getFieldTenNCC_add().getText());
//                po.setGia((long)view.getTablePOdraft().getValueAt(i, 13));
//                po.setSoLuong((int)view.getTablePOdraft().getValueAt(i, 14));
//                if (String.valueOf(view.getTablePOdraft().getValueAt(i, 15)).isBlank()){
//                    po.setVat(Float.parseFloat(String.valueOf(view.getFieldVAT_add().getText())));
//                }
//                else {
//                    po.setVat((float) view.getTablePOdraft().getValueAt(i, 15));
//                }
//                po.setGiaItem((double) view.getTablePOdraft().getValueAt(i, 16));
//                double giaPO = CurrencyUtils.parseToDouble(view.getFieldTongPOdraft().getText());
//                //po.setGiaDonHang(giaPO);
//                
//                //System.out.println(po);
//                newPOlist.add(po);
//            }
//            
//            try {
//                model.addDB(newPOlist);
//            } catch (SQLException ex) {
//                Logger.getLogger(GRController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            for (PurchaseOrder po : newPOlist){
//                Object[] objPO = po.getObjPO();
//                view.getTableERP().addRow(objPO);
//            }
//            
//            view.getDialogAdd().dispose();
//        }
//    }
//    
//    // Action khi nút "Sửa" của quản lý PO được nhấn   
//    private class EditActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnEdit is clicked");
//            setupButtonAction("update"); // kích hoạt nút "Chọn" nhà cung cấp ở chế độ update
//            
//            if (view.getTableERP() == null){
//                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
//                return;
//            }
//            if (view.getTbPO().getSelectedRow() == -1){
//                JOptionPane.showMessageDialog(view, "Vui lòng chọn PO cần sửa");
//                return;
//            }
//            
//            view.setColumPOupdate(PurchaseOrder.columns);
//            
//            int selRow = view.getTbPO().getSelectedRow();
//            String selSoCT = String.valueOf(view.getTbPO().getValueAt(selRow, 0));
//            view.getFieldSoCT_update().setText(selSoCT);
//            view.getDate_update().setDate(new Date()); // set ngày PR hiện tại
//            view.getFieldUser_update().setText(loginUser.getTenTK()); // set người tạo PR là tài khoản đang dùng
//               
//            int rowCount = view.getTbPO().getRowCount();
//            int soCTitemCount = 0;
//            updateIndex = view.getTableERP().mapRow(0, selSoCT);  // tạo mới các vị trí sẽ Edit
//            updateListObjPO  = view.getTableERP().exportObjData(updateIndex); // lấy các data của PO cần edit
//
//            view.setDataPOupdate(updateListObjPO);
//            view.initTablePOUpdate();
//            view.getDialogUpdate().pack(); // giúp Dialog khởi tạo các thành phần bên trong hoàn toàn, điều chỉnh kích thước... -> tránh lỗi hiển thị.
//            view.getDialogUpdate().setVisible(true);
//        }
//    }
//    
//    // Action khi nút chọn Dialog "DS nhà cung cấp" được nhấn
//    private class UpdateVendorActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnSelectVendor is clicked");
//            view.updateVendorInfo();
//            view.getDialogTimVendor().dispose(); 
//        }
//    }
//    
//    // Action khi nút "Cập nhật" của Dialog "Sửa PO" được nhấn
//    private class UpdateActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnUpdate is clicked");
//            // Phải stop edit JTable thì mới lưu dữ liệu vào bảng được.
//            if (view.getTbPOupdate().isEditing())
//                view.getTbPOupdate().getCellEditor().stopCellEditing();
//            double totalPrice = view.getTablePOupdate().capNhatTongGia(13, 14, 16, 15); // Cập nhật giaTong ở mỗi item
//            ArrayList<PurchaseOrder> listPOupdate = new ArrayList();
//            for (int i = 0; i < updateListObjPO.length; i++){
//                PurchaseOrder po = view.getUpdatePOinfo(i);
//                listPOupdate.add(po);
//                updateListObjPO[i] = view.getTablePOupdate().exportObjData(i);
//            }
//            
//            try {
//                model.updateDB(listPOupdate);
//            } catch (SQLException ex) {
//                Logger.getLogger(GRController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            for (int i = 0; i < updateListObjPO.length; i++){
//                String tableERPsoCT = String.valueOf(view.getTableERP().getValueAt(updateIndex[i], 0));
//                String objDataSoCT = String.valueOf(updateListObjPO[i][0]);
//                if (!tableERPsoCT.equals(objDataSoCT)){
//                    JOptionPane.showMessageDialog(null, "vị trí update không khớp: " + tableERPsoCT + " - " + objDataSoCT);
//                    return;
//                }
//                
//                view.getTableERP().sua(updateIndex[i], updateListObjPO[i]);
//            }
//            view.updateTbPO();
//            view.getDialogUpdate().dispose();
//            
//        }
//    }
//
//
//
//    private class CloseActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("btnClose is clicked");
//            int confirmResult = JOptionPane.showConfirmDialog(view.getDialogUpdate(), "Vui lòng xác nhận chắc chắn muốn đóng PR. Sau khi đóng, bạn sẽ không thể chỉnh/sửa được nữa", "Xác nhận", JOptionPane.YES_NO_OPTION);
//            if(confirmResult == JOptionPane.NO_OPTION){
//                return;
//            }
//
//            int rowCount = view.getTablePOupdate().getRowCount();
//            ArrayList<PurchaseOrder> listPOupdate = new ArrayList();
//            for (int i = 0; i < rowCount; i++){
//                PurchaseOrder po = new PurchaseOrder();
//                String trangThaiStr = String.valueOf(view.getTablePOupdate().getValueAt(i, 4));
//                //System.out.println(trangThaiStr);
//                if (trangThaiStr.equals("Đang xử lý")){
//                    view.getTablePOupdate().setValueAt("Đã đóng", i, 4);
//                    po.setTrangThai(2); // 2: Đóng (inactive)
//                    updateListObjPO[i][4] = Transaction.status.get(2);
//                }
//                else {
//                    po.encodeTrangThai(trangThaiStr); // giữ nguyên trạng thái & encode về số
//                }
//                po.setSoCT(Integer.parseInt(view.getFieldSoCT_update().getText()));
//                po.setNgaySua(view.getDate_update().getDate());
//                po.setItemLine((int) view.getTablePOupdate().getValueAt(i, 5));
//                
//                listPOupdate.add(po);
//            }
//            
//            try {
//                model.updateDBClose(listPOupdate);
//            } catch (SQLException ex) {
//                Logger.getLogger(GRController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            for (int i = 0; i < updateIndex.length; i++){
//                view.getTableERP().sua(updateIndex[i], updateListObjPO[i]);
//            }
//            
//            //view.updateTbPOupdate();
//
//        }
//    }
//
//
//    //Action khi nút "Xoá" của màn hình quản lý danh sách PO được nhấn
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
//            int selRow = view.getTbPO().getSelectedRow();
//            if (!String.valueOf(view.getTableERP().getValueAt(selRow, 4)).equals("Đang xử lý")){
//                JOptionPane.showMessageDialog(null, "Bạn chỉ có thể xoá khi transaction ở trạng thái \"Đang xử lý\" ");
//                return;
//            }
//            
//            int confirmResult = JOptionPane.showConfirmDialog(null, "Vui lòng xác nhận chắc chắn muốn xoá", "Xác nhận", JOptionPane.YES_NO_OPTION);
//            if (confirmResult == JOptionPane.NO_OPTION){
//                return;
//            }
//            
//            
//            int deleteSoCT = (int)view.getTbPO().getValueAt(selRow, 0);
//            int deleteItemLine = (int)view.getTbPO().getValueAt(selRow, 5);
//            String soPO_line = deleteSoCT + "_" + deleteItemLine;
//            int deleteSoPR = (int)view.getTbPO().getValueAt(selRow, 6);
//            int deletePRline = (int)view.getTbPO().getValueAt(selRow, 7);
//            String soPR_line= deleteSoPR + "_" + deletePRline;
//            
//            try {
//                model.updateDB(soPO_line, soPR_line);
//            } catch (SQLException ex) {
//                Logger.getLogger(GRController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            view.getTableERP().removeRow(selRow);
//            view.updateTbPO();
//            JOptionPane.showMessageDialog(view, "Đã xoá thành công!");
//            
//        }
//    }

    
}
