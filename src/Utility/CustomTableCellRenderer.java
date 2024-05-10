/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;



/**
 *
 * @author Ricardo
 */
public class CustomTableCellRenderer extends CurrencyRenderer {
    
    private JTable table;

    public CustomTableCellRenderer(JTable table) {
        super();
        this.table = table;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!table.isCellEditable(row, column)) {
            c.setBackground(Color.GRAY); // set màu cell edit được
        } else {
            c.setBackground(Color.WHITE); // set màu cho cell không edit được
        }

        return c;
    }
    
}
