/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import MyComponent.TableButtonEditor;
import MyComponent.TableButtonRender;
import MyComponent.MovieTabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


/**
 *
 * @author Administrator
 */
public class SearchResult extends JFrame {

    String[] Names = {"Name", "Price", "Time", "Duration", "Location","  "};
    MovieTabel table;
    Object[][] movieInfo;
    String username;
    String type;

    public SearchResult(ResultSet result,int row,String username,String type) {
        this.username = username;
        this.type = type;
        movieInfo = new Object[row][6];
        try {
            int i = 0;
            while (result.next()) {
                movieInfo[i][0] = result.getString("MOVIENAME");
                movieInfo[i][1] = result.getString("PRICE");
                String time = result.getString("BEGINTIME");
                movieInfo[i][2] = time.substring(0, time.length() - 2);
                movieInfo[i][3] = result.getString("DURATION");
                movieInfo[i][4] = result.getString("LOCATION");
                i++;
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
        table = new MovieTabel(movieInfo, Names);
        table.getColumnModel().getColumn(5).setCellEditor(new TableButtonEditor(this.username,this.type));
        table.getColumnModel().getColumn(5).setCellRenderer(new TableButtonRender());
        table.isCellEditable(0, 6);
        
        
        table.setPreferredScrollableViewportSize(new Dimension(550, 100));
       
        JScrollPane scrollPane = new JScrollPane(table);
        this.setTitle("Movie List");
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.setBounds(100,100,1000,450);
        this.setVisible(true);
    }
    
}
