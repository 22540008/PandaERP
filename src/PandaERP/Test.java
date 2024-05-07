/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PandaERP;

import Services.PRManager;
import java.sql.SQLException;

/**
 *
 * @author Ricardo
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        PRManager prManager = new PRManager();
        prManager.loadData_DB();
        
    }
    
}
