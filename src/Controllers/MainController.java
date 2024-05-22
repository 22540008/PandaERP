/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.User;
import Services.GRManager;
import Services.ItemManager;
import Services.POManager;
import Services.PRManager;
import Services.UserManager;
import Services.VendorManager;
import Views.ExpenseFilterView;
import Views.GRView;
import Views.ItemView;
import Views.MainFrame;
import Views.POView;
import Views.PRView;
import Views.UserView;
import Views.VendorView;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * mô hình "singleton" hoặc "shared instance"
 * @author Ricardo
 */


public class MainController {
    private MainFrame view;  
    private User loginUser;
    private DangNhapController dangNhapController;
    private UserManager userModel;
    private UserController userController;
    private UserView userView;
    private ItemManager itemModel;
    private ItemController itemController;
    private ItemView itemView;
    private VendorManager vendorModel;
    private VendorController vendorController;
    private VendorView vendorView;
    private PRManager prModel;
    private PRController prController;
    private PRView prView;
    private POManager poModel;
    private POController poController;
    private POView poView;
    private GRManager grModel;
    private GRController grController;
    private GRView grView;
    private GRManager expenseModel;
    private ExpenseFilterController expenseController;
    private ExpenseFilterView expenseView;
    
    

    
    public MainController(User loginUser) {
        this.loginUser = loginUser;
        System.out.println(loginUser);
        this.view = view;
        // Khởi tạo User component
        userModel = new UserManager();
        userView = new UserView();
        userController = new UserController(userModel, userView); 
        // UserView_add & UserView_update
//        UserView_add userView_add = new UserView_add(view, true, userController);
//        UserView_update userView_update = new UserView_update(view, true, userController, rowData);
        
        
        // Khởi tạo Item component
        itemModel = new ItemManager();
        itemView = new ItemView();
        itemController = new ItemController(itemModel, itemView);
        // Khởi tạo Vendor component
        vendorModel = new VendorManager();
        vendorView = new VendorView();
        vendorController = new VendorController(vendorModel, vendorView);
        // Khởi tạo PR component
        prModel = new PRManager();
        prView = new PRView();
        prController = new PRController(prModel, prView);
        // Khởi tạo PO component
        poModel = new POManager();
        poView = new POView();
        poController = new POController(poModel, poView);
        // Khởi tạo GR component
        grModel = new GRManager();
        grView = new GRView();
        grController = new GRController(grModel, grView);
        // Khởi tạo Expense Filter component
        expenseModel = new GRManager();
        expenseView = new ExpenseFilterView();
        expenseController = new ExpenseFilterController(expenseModel, expenseView);
        
        
        // Set các Model tương quan
        prController.setOtherModel(loginUser, itemController);
        poController.setOtherModel(loginUser, itemController, vendorController, prController);
        grController.setOtherModel(loginUser, itemController, vendorController, prController, poController);
        expenseController.setOtherModel(loginUser, itemController, vendorController, prController, poController);

    }
    
    public void setView(MainFrame view){
        this.view = view;
        view.menuDSTaiKhoanActionListener(new DSTaiKhoanActionListener());
        view.menuItemActionListener(new ItemActionListener());
        view.menuVendorActionListener(new VendorActionListener());
        view.menuPRActionListener(new PRActionListener());
        view.menuPOActionListener(new POActionListener());
        view.menuGRActionListener(new GRActionListener());
        view.menuExpenseFilter(new ExpenseFilterActionListener());
        
    }
    
    public UserView getUserView() {
        return userView;
    }

    public ItemView getItemView() {
        return itemView;
    }
    
    public VendorView getVendorView() {
        return vendorView;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public PRView getPrView() {
        return prView;
    }

    public Component getPoView() {
        return poView;
    }

    public Component getGrView() {
        return grView;
    }

    public ExpenseFilterView getExpenseView() {
        return expenseView;
    }

    private class ExpenseFilterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuExpenseFilter is clicked");
            boolean permission = false;
            for (String role : loginUser.getSystemRoles()){
                if (role.equals("admin") || role.equals("buyer")){
                    permission = true;
                    break;
                }
            }
            if (permission == false){
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                return;
            }
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "ExpenseView");
            expenseController.getView().setVisible(true);
        }
    }
    
    private class GRActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuGR is clicked");
            boolean permission = false;
            for (String role : loginUser.getSystemRoles()){
                if (role.equals("admin") || role.equals("requester")){
                    permission = true;
                    break;
                }
            }
            if (permission == false){
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                return;
            }
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "GRView");
            grController.getView().setVisible(true);
        }
    }

    private class POActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuPO is clicked");
            boolean permission = false;
            for (String role : loginUser.getSystemRoles()){
                if (role.equals("admin") || role.equals("buyer")){
                    permission = true;
                    break;
                }
            }
            if (permission == false){
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                return;
            }
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "POView");
            poController.getView().setVisible(true);
        }
    }

    private class PRActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuPR is clicked");
            boolean permission = false;
            for (String role : loginUser.getSystemRoles()){
                if (role.equals("admin") || role.equals("requester")){
                    permission = true;
                    break;
                }
            }
            if (permission == false){
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                return;
            }
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "PRView");
            prController.getView().setVisible(true);
        }
    }
    
    

    private class VendorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuVendor is clicked");
            boolean permission = false;
            for (String role : loginUser.getSystemRoles()){
                if (role.equals("admin") || role.equals("buyer")){
                    permission = true;
                    break;
                }
            }
            if (permission == false){
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                return;
            }
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "VendorView");
            vendorController.getView().setVisible(true);
        }
    }


    private class ItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuItem is clicked");
            boolean permission = false;
            for (String role : loginUser.getSystemRoles()){
                if (role.equals("admin") || role.equals("buyer")){
                    permission = true;
                    break;
                }
            }
            if (permission == false){
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                return;
            }
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "ItemView");
            itemController.getView().setVisible(true);
        }
    }

    private class DSTaiKhoanActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuDSUser is clicked");
            boolean permission = false;
            for (String role : loginUser.getSystemRoles()){
                if (role.equals("admin")){
                    permission = true;
                    break;
                }
            }
            if (permission == false){
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                return;
            }
            CardLayout cl = (CardLayout)(view.getPanelMain().getLayout());
            cl.show(view.getPanelMain(), "UserView");
            userController.getView().setVisible(true);
        }
    }
    
    
    
}
