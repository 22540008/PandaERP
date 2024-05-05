/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.User;
import Views.ItemView;
import Views.MainFrameView;
import Views.UserView;
import Views.VendorView;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ricardo
 */
public class MainFrameController {
    private MainFrameView view;  
    private User loginUser;
    private UserController userController;
    private UserView userView;
    private DangNhapController dangNhapController;
    private ItemController itemController;
    private ItemView itemView;
    private VendorController vendorController;
    private VendorView vendorView;
    

    
    public MainFrameController(User loginUser) {
        this.loginUser = loginUser;
        System.out.println(loginUser);
        this.view = view;
        // Khởi tạo User component
        userController = new UserController(); 
        userView = new UserView();
        userController.setView(userView);   
        userView.setController(userController);
        // Khởi tạo Item component
        itemController = new ItemController();
        itemView = new ItemView();
        itemController.setView(itemView);
        itemView.setController(itemController);
        // Khởi tạo Vendor component
        vendorController = new VendorController();
        vendorView = new VendorView();
        vendorController.setView(vendorView);
        vendorView.setController(vendorController);
    }
    
    public void setView(MainFrameView view){
        this.view = view;
        view.menuDSTaiKhoanActionListener(new DSTaiKhoanActionListener());
        view.menuItemActionListener(new ItemActionListener());
        view.menuVendorActionListener(new VendorActionListener());
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
