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
public class MovieTabel extends JTable{
    public MovieTabel(int row,int col){
        super(row,col);
    }
    public MovieTabel(Object[][] rowData, Object[] columnNames){
        super(rowData,columnNames);
    }
    @Override
    public boolean isCellEditable(int row,int col){
        if(col == 5){
            return true;
        }
        return false;
    }
}
