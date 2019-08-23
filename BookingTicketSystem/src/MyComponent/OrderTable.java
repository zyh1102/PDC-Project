/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import javax.swing.JTable;

/**
 *
 * @author Administrator
 */
public class OrderTable extends JTable{
    public OrderTable(int row,int col){
        super(row,col);
    }
    public OrderTable(Object[][] rowData, Object[] columnNames){
        super(rowData,columnNames);
    }
    @Override
    public boolean isCellEditable(int row,int col){
        if(col == 8){
            return true;
        }
        return false;
    }
}
