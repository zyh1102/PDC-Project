/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class OrderButtonEditor extends DefaultCellEditor {
    private ButtonInTable button;
    public OrderButtonEditor(JFrame frame){
        super(new JTextField());
        button = new ButtonInTable("Delete");
        button.setFrame(frame);
        button.addActionListener(new DeleteListener());
       
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
        button.setRow(row);
        button.setColumn(column);
        return button;
    }

}
