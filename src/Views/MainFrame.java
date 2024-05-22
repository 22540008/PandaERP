/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Controllers.DangNhapController;
import Controllers.ItemController;
import Controllers.MainController;
import Controllers.UserController;
import Models.User;
import Services.UserManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ricardo
 */
public class MainFrame extends javax.swing.JFrame {
    // private User user;
    // private DangNhapController loginUser;
    private MainController controller;
    

    /**
     * Creates new form MainFrame
     * tiếp cận "dependency injection" tạo userController, userView ở 1 nơi rồi truyền lẫn nhau như là các tham số
     */
    // Hàm khởi tạo nhận vào data người đăng nhập
    public MainFrame(MainController controller) {
        this.controller = controller;
        initComponents(); // khởi tạo các thành phần trong Swing Design       
           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đặt hành vi mặc định khi cửa sổ đóng là kết thúc ứng dụng.
        setSize(800, 600); //
        panelWelcome.setLayout(new BorderLayout()); // BorderLayout đưa welcome vào vùng hiển thị
        addWelcomeMessage();
        
        // Đặt default Empty Jpanel vì panelMain dùng CardLayout
        JPanel emptyPanel = new JPanel();
        panelMain.add(emptyPanel, "EmptyPanel");
        // Thêm các View (User, Item, ) vào panelMain ở dạng không hiển thị.
        panelMain.add(controller.getUserView(), "UserView");
        panelMain.add(controller.getItemView(), "ItemView");
        panelMain.add(controller.getVendorView(), "VendorView");
        panelMain.add(controller.getPrView(), "PRView");
        panelMain.add(controller.getPoView(), "POView");
        panelMain.add(controller.getGrView(), "GRView");
        panelMain.add(controller.getExpenseView(), "ExpenseView");


    }

    public JPanel getPanelMain() {
        return panelMain;
    }
    
    
    // Thông điệp welcome vào panelWelcome
    private void addWelcomeMessage() {
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        
        JLabel welcomeLabel = new JLabel("Welcome, " + controller.getLoginUser().getTenTK());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.RED);
        welcomeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT); // canh lề phải
        innerPanel.add(welcomeLabel);

        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // canh phải
        outerPanel.add(innerPanel);
        // this.add(outerPanel, BorderLayout.NORTH); // nằm ở trên
        panelWelcome.add(outerPanel, BorderLayout.NORTH); // nằm ở trên
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        panelWelcome = new javax.swing.JPanel();
        panelMain = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        menuDSTaiKhoan = new javax.swing.JMenuItem();
        menuSignout = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuPR = new javax.swing.JMenuItem();
        menuPO = new javax.swing.JMenuItem();
        menuGR = new javax.swing.JMenuItem();
        menuItem = new javax.swing.JMenuItem();
        menuVendor = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        menuExpenseFilter = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout panelWelcomeLayout = new javax.swing.GroupLayout(panelWelcome);
        panelWelcome.setLayout(panelWelcomeLayout);
        panelWelcomeLayout.setHorizontalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelWelcomeLayout.setVerticalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 612;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(panelWelcome, gridBagConstraints);

        panelMain.setToolTipText("");
        panelMain.setLayout(new java.awt.CardLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 612;
        gridBagConstraints.ipady = 412;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(panelMain, gridBagConstraints);
        panelMain.getAccessibleContext().setAccessibleName("");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu5.setText("Account");
        jMenu5.setActionCommand("jMenuAcount");

        menuDSTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Users.png"))); // NOI18N
        menuDSTaiKhoan.setText("DS Tài khoản");
        jMenu5.add(menuDSTaiKhoan);

        menuSignout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        menuSignout.setText("Thoát tài khoản");
        jMenu5.add(menuSignout);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Purchasing");

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_bill_30px.png"))); // NOI18N
        jMenu3.setText("PR-PO-GR");
        jMenu3.setAutoscrolls(true);

        menuPR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/List.png"))); // NOI18N
        menuPR.setText("Yêu cầu mua hàng");
        jMenu3.add(menuPR);

        menuPO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Price list.png"))); // NOI18N
        menuPO.setText("Đơn hàng");
        jMenu3.add(menuPO);

        menuGR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_agreement_30px.png"))); // NOI18N
        menuGR.setText("Phiếu nhận hàng");
        jMenu3.add(menuGR);

        jMenu6.add(jMenu3);

        menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-categorize-30.png"))); // NOI18N
        menuItem.setText("Danh mục vật tư");
        jMenu6.add(menuItem);

        menuVendor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_client_company_30px.png"))); // NOI18N
        menuVendor.setText("Nhà cung cấp");
        jMenu6.add(menuVendor);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Best.png"))); // NOI18N
        jMenu7.setText("Báo cáo");

        menuExpenseFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Paomedia-Small-N-Flat-Funnel.24.png"))); // NOI18N
        menuExpenseFilter.setText("Lọc chi tiêu");
        menuExpenseFilter.setToolTipText("");
        jMenu7.add(menuExpenseFilter);

        jMenu6.add(jMenu7);

        jMenuBar1.add(jMenu6);

        jMenu4.setText("Help");
        jMenuBar1.add(jMenu4);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify                     

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        final User loginUser = new User();
        final MainController controller = new MainController(loginUser);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(controller).setVisible(true);
            }
        });
    }

       
    public void btnSignoutActionListener(ActionListener listener) {
        menuSignout.addActionListener(listener); 
    }
    
    public void menuDSTaiKhoanActionListener(ActionListener listner){
        menuDSTaiKhoan.addActionListener(listner);
    }
    
    public void menuItemActionListener(ActionListener listener){
        menuItem.addActionListener(listener);
    }
    
    public void menuVendorActionListener(ActionListener listener){
        menuVendor.addActionListener(listener);
    }
    
    public void menuPRActionListener(ActionListener listener){
        menuPR.addActionListener(listener);
    }
    
    public void menuPOActionListener(ActionListener listener){
        menuPO.addActionListener(listener);
    }
    
    public void menuGRActionListener(ActionListener listener){
        menuGR.addActionListener(listener);
    }
    
    public void menuExpenseFilter(ActionListener listener){
        menuExpenseFilter.addActionListener(listener);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuDSTaiKhoan;
    private javax.swing.JMenuItem menuExpenseFilter;
    private javax.swing.JMenuItem menuGR;
    private javax.swing.JMenuItem menuItem;
    private javax.swing.JMenuItem menuPO;
    private javax.swing.JMenuItem menuPR;
    private javax.swing.JMenuItem menuSignout;
    private javax.swing.JMenuItem menuVendor;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelWelcome;
    // End of variables declaration//GEN-END:variables
}
