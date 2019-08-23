/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class TableButtonEditor extends DefaultCellEditor {
    private ButtonInTable button;
    public TableButtonEditor(String username,String type){
        super(new JTextField());
        button = new ButtonInTable("purchase");
        button.setUsername(username);
        button.setType(type);
        button.addActionListener(new LocationListener());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
        button.setRow(row);
        button.setColumn(column);
        return button;
    }

}
