/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PandaERP;

import Controllers.DangNhapController;
import Controllers.MainController;
import Controllers.UserController;
import Models.User;
import Views.DangNhapView;
import Views.MainFrame;
import Views.UserView;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ricardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Chế độ default
//        User model = new User();
//        DangNhapView view = new DangNhapView();
//        view.setVisible(true);
//        DangNhapController ct1 = new DangNhapController(model, view);
        
        // Chế độ test, skip sign in step
        User user = new User();
        user.setTenTK("tqhung");
        MainController mainFrameCtl = new MainController(user);
        MainFrame mainFrameView = new MainFrame(mainFrameCtl);
        mainFrameCtl.setView(mainFrameView);
        mainFrameView.setVisible(true);
        
    }
    
}
