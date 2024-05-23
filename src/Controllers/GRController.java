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
import Utility.DateUtils;
import Views.GRView;
import Views.TableERP;
import com.toedter.calendar.DateUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
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
        this.view.btnAddActionListener(new AddActionListener());
        this.view.btnDiagTimPOAddActionListener(new TimPOAddActionListener());
        this.view.btnSearchPOActionListener(new SearchPOActionListener());
//        this.view.btnSearchPOActionListener(new SearchPOActionListener());
        this.view.btnSelectPORActionListener(new SelectPOActionListener());
        this.view.btnRemoveAddActionListener(new RemoveAddActionListener());
        this.view.btnCreateActionListener(new CreateActionListener());
        this.view.btnDeleteActionListener(new DeleteActionListener());
//        this.view.btnEditActionListener(new EditActionListener());
//        this.view.btnDiagTimNCCupdateActionListener(new TimNCCaddActionListener());
//        this.view.btnUpdateActionListener(new UpdateActionListener());
//        this.view.btnCloseActionListener(new CloseActionListener());
       
 
        
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
    

    // Action khi nút Load ở panel "danh sách GR" được nhấn
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
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(null, "Vui lòng Load dữ liệu trước");
            }
            String[] paramSearch = view.getSearchParams();
            Object[][] trackObjPO;
            trackObjPO = view.getTableERP().searchByCriteria(paramSearch, new int[]{0, 1}, "match");
            view.getTableERP().setData(trackObjPO, PurchaseOrder.columns, view.getTbGR());
        }
    }
    
    // Action khi nút Thêm của Panel "Quản lý danh sách GR" được click
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
    
    // Action khi nút "Tìm PO" của Dialog "GR draft" dược nhấn
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
    
    // Action khi nút "Tìm PO" của Dialog "Tìm PO" được nhấn
    private class SearchPOActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnSearchPO is clicked");
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
                if (view.getTableGRdraft().getRowCount() > 0){
                    String draftGrPoStr = String.valueOf(view.getTableGRdraft().getValueAt(0, 6));
                    if (!soPOStr.equals(draftGrPoStr)){
                        JOptionPane.showMessageDialog(null, "1 GR chỉ có thể dùng cho 1 PO");
                    }
                }
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
    
    // Action khi nút "Thêm" ở Dialog "Danh sách pending PO" được nhấn
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
            view.getTableGRdraft().displayTable(view.getTbGRdraft());
            view.getDialogTimPO().dispose(); 
        }
    }
    
    //Action khi nút "Xoá Item" của Dialog "Tạo draft GR" được nhấn
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
            view.getTableGRdraft().displayTable(view.getTbGRdraft());
        }
    }
    
    // Action khi nút "Tạo GR" của Dialog "Tạo GR draft" được nhấn
    private class CreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnCreate is clicked");
            int rowCount = view.getTbGRdraft().getRowCount();
            if (rowCount == 0){
                JOptionPane.showMessageDialog(null, "Không có PR được chọn");
                return;
            }
            ArrayList<GoodsReceipt> newGRlist = new ArrayList();
            for (int i = 0; i < rowCount; i++){
                int soCT = Integer.parseInt(String.valueOf(view.getFieldSoCT_add().getText()));
                GoodsReceipt gr = new GoodsReceipt();
                gr.setSoCT(soCT);
                gr.setTenTK(view.getFieldUser_add().getText());
                gr.setNgayTao(view.getDate_add().getDate());
                gr.setNgaySua(view.getDate_add().getDate());
                gr.setTrangThai(4); // Trạng thái "Duyệt"
                gr.setItemLine(i+1);
                gr.setSoPO((int)(view.getTableGRdraft().getValueAt(i, 6)));
                gr.setPOline((int)(view.getTableGRdraft().getValueAt(i, 7)));
                gr.setSoPR((int)(view.getTableGRdraft().getValueAt(i, 8)));
                gr.setPRline((int)(view.getTableGRdraft().getValueAt(i, 9)));
                //System.out.println("So PO: " + gr.getPo().getSoCT());
                //System.out.println("So PR: " + gr.getPr().getSoCT());
                gr.setMaHang((int)(view.getTableGRdraft().getValueAt(i, 10)));
                gr.setTenHang(String.valueOf(view.getTableGRdraft().getValueAt(i, 11)));
                gr.setDvt(String.valueOf(view.getTableGRdraft().getValueAt(i, 12)));
                gr.setMaNCC((int)(view.getTableGRdraft().getValueAt(i, 13)));
                gr.setTenNCC(String.valueOf(view.getTableGRdraft().getValueAt(i, 14)));
                int slNhan = (int)(view.getTableGRdraft().getValueAt(i, 16));
                gr.setSlNhan(slNhan);
                gr.getPo().setSlChoNhan((int)(view.getTableGRdraft().getValueAt(i, 15)));
                int slConLai = 0;
                if ((view.getTableGRdraft().getValueAt(i, 18)) != null){
                    gr.getPo().setSlChoNhan(0);
                }
                else{
                    slConLai = gr.tinhSLConLai();
                }

                System.out.println("Số lượng chờ nhận còn lại: " + slConLai);
                if (slConLai < 0){
                    JOptionPane.showMessageDialog(null, "Số nhận lớn hơn hơn số có thể nhận của đơn hàng");
                    return;
                }
                
                if (view.getTableGRdraft().getValueAt(i, 17) != null){
                    gr.encodeLuuKho((boolean)view.getTableGRdraft().getValueAt(i, 17));
                }
                else{
                    gr.setLuuKho(0);
                }
                
                if (view.getTableGRdraft().getValueAt(i, 18) != null){
                    gr.setLanCuoi(true);
                }
                else{
                    gr.setLanCuoi(false);
                }

                newGRlist.add(gr);
            }
            
            try {
                model.addDB(newGRlist);
            } catch (SQLException ex) {
                Logger.getLogger(GRController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (GoodsReceipt gr : newGRlist){
                Object[] objPO = gr.getObjPO();
                view.getTableERP().addRow(objPO);
            }
            
            view.getDialogAdd().dispose();
        }
    }
    
    //Action khi nút "Xoá" của màn hình quản lý danh sách PO được nhấn
    private class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnDelete is clicked");
            if (view.getTableERP() == null){
                JOptionPane.showMessageDialog(view, "Vui lòng Load dữ liệu trước");
                return;
            }
            if (view.getTbGR().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng PR cần xoá");
                return;
            }
            int selRow = view.getTbGR().getSelectedRow();
            if (!String.valueOf(view.getTableERP().getValueAt(selRow, 4)).equals("Duyệt")){
                JOptionPane.showMessageDialog(null, "Bạn chỉ có thể xoá khi transaction ở trạng thái \"Duyệt\" ");
                return;
            }
            String unDeleteSoPOStr = String.valueOf(view.getTbGR().getValueAt(selRow, 6));
            String unDeletePoLineStr = String.valueOf(view.getTbGR().getValueAt(selRow, 7));
            int[] PoLineMap = view.getTableERP().mapRow(new int[]{6,7}, new String[]{unDeleteSoPOStr, unDeletePoLineStr});
            for (int i= 0; i < PoLineMap.length; i++){
                if (Boolean.TRUE.equals(view.getTableERP().getValueAt(i, 18))){
                    JOptionPane.showMessageDialog(null, "Không thể xoá vì item đã đóng lại do đã \"nhận lần cuối\" ");
                    return;
                }
                
                int soGR = Integer.parseInt(String.valueOf(view.getTableERP().getValueAt(i, 0)));
                int selSoGR = Integer.parseInt(String.valueOf(view.getTableERP().getValueAt(selRow, 0)));
//                if (soGR < selSoGR){
//                    JOptionPane.showMessageDialog(null, "Tồn tại GR khác được tạo trước đó cho PO item tương ứng, nên không thể xoá được");
//                    return;
//                }
            }
            
            int confirmResult = JOptionPane.showConfirmDialog(null, "Vui lòng xác nhận chắc chắn muốn xoá", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirmResult == JOptionPane.NO_OPTION){
                return;
            }   
            
            int deleteSoCT = (int)view.getTbGR().getValueAt(selRow, 0);
            int deleteItemLine = (int)view.getTbGR().getValueAt(selRow, 5);
            //String soGR_line = deleteSoCT + "_" + deleteItemLine;
            //String soPO_line= unDeleteSoPOStr + "_" + unDeletePoLineStr;
            GoodsReceipt deleteGR = new GoodsReceipt();
            deleteGR.setSoCT(deleteSoCT);
            deleteGR.setItemLine(deleteItemLine);
            deleteGR.setSoPO(Integer.parseInt(unDeleteSoPOStr));
            deleteGR.setPOline(Integer.parseInt(unDeletePoLineStr));
            deleteGR.setSlNhan(Integer.parseInt(String.valueOf(view.getTableERP().getValueAt(selRow, 16))));
            
            try {
                model.updateDB(deleteGR);
            } catch (SQLException ex) {
                Logger.getLogger(GRController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            view.getTableERP().removeRow(selRow);
            view.updateTbGR();
            JOptionPane.showMessageDialog(view, "Đã xoá thành công!");
            
        }
    }
    
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
//            if (view.getTbGR().getSelectedRow() == -1){
//                JOptionPane.showMessageDialog(view, "Vui lòng chọn PO cần sửa");
//                return;
//            }
//            
//            view.setColumPOupdate(PurchaseOrder.columns);
//            
//            int selRow = view.getTbGR().getSelectedRow();
//            String selSoCT = String.valueOf(view.getTbGR().getValueAt(selRow, 0));
//            view.getFieldSoCT_update().setText(selSoCT);
//            view.getDate_update().setDate(new Date()); // set ngày PR hiện tại
//            view.getFieldUser_update().setText(loginUser.getTenTK()); // set người tạo PR là tài khoản đang dùng
//               
//            int rowCount = view.getTbGR().getRowCount();
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
//            view.updateTbGR();
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


    
}
