/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DBConnection;

/**
 *
 * @author Ricardo
 */
public class TestConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       // Nhập user, pass
       SQLConnection s = new SQLConnection("sa", "sa");
       
       // Không nhập user, pass
       SQLConnection s2 = new SQLConnection("","");
       
    }
    
}
