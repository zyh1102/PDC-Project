/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import bookingticketsystem.Purchase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class LocationListener implements ActionListener{
    String type;
    int row = 0;
    private static String url = "jdbc:derby:MOVIESYSTEM; create=true";
    private static String name = "root";
    private static String password = "root";
    private Connection con;
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonInTable button = (ButtonInTable) e.getSource();
        row = button.getRow();
        type = button.getType();
        String username = button.getUsername();
        try{
            con = DriverManager.getConnection(url, name, password);
            Statement statement = con.createStatement();
            String searchStatement = null;
            if(type.equals("*")){
                searchStatement = "SELECT * FROM MOVIEINFO";
            }
            else{
                searchStatement = "SELECT * FROM MOVIEINFO WHERE MOVIENAME='" + this.type + "'";
            }
            ResultSet result = statement.executeQuery(searchStatement);
            int i = 0;
            while(result.next()){
                if(i == row){
                    
                   
                    new Purchase(result,username);
                    
                }
                i++;
            }
        }
        catch(Exception event){
            event.printStackTrace();
        }
    }
}
