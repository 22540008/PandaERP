/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JTextField;

/**
 *
 * @author Ricardo
 */
public class CurrencyTextField extends JTextField {
    private NumberFormat formatter;
    
    public CurrencyTextField(){
        formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }
    
    public void setVaue(double value){
        setText(formatter.format(value));
    }
    
}
