/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.User;
import Services.ItemManager;
import Services.POManager;
import Services.PRManager;
import Services.UserManager;
import Services.VendorManager;
import Views.ItemView;
import Views.MainFrame;
import Views.POView;
import Views.PRView;
import Views.UserView;
import Views.UserView_add;
import Views.UserView_update;
import Views.VendorView;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private UserView_add userView_add; ////
    private UserView_update userView_update; ////
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
        
        
        // Set các Model tương quan
        prController.setOtherModel(loginUser, itemController);
        poController.setOtherModel(loginUser, itemController, vendorController, prController);
        

        
    }
    
    public void setView(MainFrame view){
        this.view = view;
        view.menuDSTaiKhoanActionListener(new DSTaiKhoanActionListener());
        view.menuItemActionListener(new ItemActionListener());
        view.menuVendorActionListener(new VendorActionListener());
        view.menuPRActionListener(new PRActionListener());
        view.menuPOActionListener(new POActionListener());
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

    private class POActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuPO is clicked");
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "POView");
            poController.getView().setVisible(true);
        }
    }

    private class PRActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuPR is clicked");
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "PRView");
            prController.getView().setVisible(true);
        }
    }
    
    

    private class VendorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuVendor is clicked");
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "VendorView");
            vendorController.getView().setVisible(true);
        }
    }


    private class ItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuItem is clicked");
            CardLayout cl = (CardLayout) view.getPanelMain().getLayout();
            cl.show(view.getPanelMain(), "ItemView");
            itemController.getView().setVisible(true);
        }
    }

    private class DSTaiKhoanActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("menuDSUser is clicked");
            CardLayout cl = (CardLayout)(view.getPanelMain().getLayout());
            cl.show(view.getPanelMain(), "UserView");
            userController.getView().setVisible(true);
        }
    }
    
    
    
}
