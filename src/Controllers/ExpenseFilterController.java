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
import Views.ExpenseFilterView;
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
public class ExpenseFilterController {
    private GRManager model;
    private ExpenseFilterView view;
    private User loginUser;
    private ItemController itemCtl;
    private VendorController vendorCtl;
    private PRController prCtl;
    private POController poCtl;
    
    private String[] filterType;
    
    
    public ExpenseFilterController(GRManager grModel, ExpenseFilterView expenseView) {
        this.model = grModel;
        this.view = expenseView;
        this.filterType = new String[] {"Người mua hàng", "Mã hàng", "Mã NCC"};
        this.view.btnFilterActionListener(new FilterActionListener());

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

    public ExpenseFilterView getView() {
        return view;
    }

    private class FilterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("btnFilter is clicked");
            int selYear = 0;
            String selYearStr = view.getFieldYear().getText();
            if (!selYearStr.matches("\\d+")){
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập đúng định dạng năm YYYY");
                return;
            }
            selYear = Integer.parseInt(selYearStr);
            int filterIdx = view.getCboxFilter().getSelectedIndex();
            
            
            try {
                model.loadData_DB("");
            } catch (SQLException ex) {
                Logger.getLogger(ExpenseFilterController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Object[][] dsObjGR = model.getObjDsGR("");   
            for (int i = 0; i < dsObjGR.length; i++){
                for (int j = 0; j < dsObjGR[i].length; j++){
                    System.out.print(dsObjGR[i][j] + " ");
                }
                System.out.println();
            }
            
            TableERP tableGR = new TableERP(dsObjGR, GoodsReceipt.expenseReportCols);
            tableGR.capNhatTongGia(18, 21, 23, 22); // 18: slNhan; 21: donGia; 23:itemValue; 22: vat
            //tableGR.displayTable(view.getTbFilter()); 
            Object[] filterData = new Object[2]; // 0: expense; 1: Object2D filter
//            switch (filterIdx) {
//                case 0:
//                    filterData = tableGR.filterExpense(8, 23); // 8: người tạo PO
//                    view.setColumn(new String[]{"Tài khoản", "Họ tên", "Tổng chi phí"});
//                    break;
//                case 1:
//                    filterData = tableGR.filterExpense(12, 23); // 12: mã hàng
//                    view.setColumn(new String[]{"Mã hàng", "Tên hàng", "Tổng chi phí"});
//                    break;
//                case 2:
//                    filterData = tableGR.filterExpense(15, 23); // 15: mã NCC
//                    view.setColumn(new String[]{"Mã NCC", "Tên NCC", "Tổng chi phí"});
//                    break;      
//                default:
//                    throw new AssertionError();
//            }
//            Object[][] listObjData = (Object[][]) filterData[1];
//            for (int i = 0; i < listObjData.length; i++){
//                
//            }
//            String[] description = new String[filterData[2]];
//            
//            
//            view.setColumn(GoodsReceipt.columns);
//            view.displayTable(dsObjGR);
//            view.loadData();   
            
            
        }
    }
    

    
}
