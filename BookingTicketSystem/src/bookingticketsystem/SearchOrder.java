/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import MyComponent.OrderButtonEditor;
import MyComponent.OrderButtonRender;
import MyComponent.OrderTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Administrator
 */
public class SearchOrder extends JFrame{
    String[] Names = {"Username", "MovieName", "BeginTime", "Row", "Column","Price","Location","Purchase Time",""};
    OrderTable table;
    Object[][] orderInfo;
    
    public SearchOrder(ResultSet result,int row){
        orderInfo = new Object[row][9];
        try{
            int i =0;
            while(result.next()){
                orderInfo[i][0] = result.getString("USERNAME");
                orderInfo[i][1] = result.getString("MOVIENAME");
                String time = result.getString("BEGINTIME");
                orderInfo[i][2] = time.substring(0, time.length() - 2);
                orderInfo[i][3] = result.getString("ROW");
                orderInfo[i][4] = result.getString("COL");
                orderInfo[i][5] = result.getString("TOTALPRICE");
                orderInfo[i][6] = result.getString("LOCATION");
                String purchaseTime = result.getString("PURCHASETIME");
                orderInfo[i][7] = purchaseTime.substring(0, purchaseTime.length() - 4);
                i++;
            }
        }
        catch(Exception event){
            event.printStackTrace();
        }
        table = new OrderTable(orderInfo,Names);
        table.getColumnModel().getColumn(8).setCellEditor(new OrderButtonEditor(this));
        table.getColumnModel().getColumn(8).setCellRenderer(new OrderButtonRender());
        table.isCellEditable(0, 9);
        
        table.setPreferredScrollableViewportSize(new Dimension(550, 100));
       
        JScrollPane scrollPane = new JScrollPane(table);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.setTitle("Order List");
        this.setBounds(100,100,1500,450);
        this.setVisible(true);
    }
}
