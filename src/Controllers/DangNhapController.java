/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.User;
import Views.DangNhapView;
import Views.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class DangNhapController {
    private User model;
    private DangNhapView view;
    private MainFrameController mainFrameCtl;
    private MainFrameView mainFrameView;
    
    public DangNhapController(User model, DangNhapView view) {
        this.model = model;
        this.view = view;
        this.view.btnLoginActionListener(new LoginActionListener());       
    }
    
    public User getUser() {
        return model;
    }
    

    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                User user = new User();
                user = model.dangnhap(view.getUsername(), view.getPassword());
                if (user == null) {
                    view.displayErrorMessage("Tên tài khoản hoặc mật khẩu bị sai");
                }
                else {
                    System.out.println(user);
                    view.displayErrorMessage("Đăng nhập thành công");
                    view.dispose();
                    model = user;
                    mainFrameCtl = new MainFrameController(user);
                    mainFrameView = new MainFrameView(mainFrameCtl);
                    mainFrameCtl.setView(mainFrameView);
                    mainFrameView.setVisible(true);
                    mainFrameView.btnSignoutActionListener(new LogoutActionListener()); // Add LogoutActionListener to menuSignout
   
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    private class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("LogoutActionListener is called"); 
            mainFrameView.dispose();
            view.setVisible(true);
        }
    }
    
    
    
    
}
