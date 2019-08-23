/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Administrator
 */
public class MyOrder extends JFrame {
    private String username = null;
    private JPanel mainPanel;
    private static String url = "jdbc:derby:MOVIESYSTEM; create=true";
    private static String name = "root";
    private static String password = "root";
    private Connection con;
   
    private JPanel[] orderPanel;
    private JPanel[] informationPanel;
    private JLabel poster;
    private JLabel movieNameLabel;
    private JLabel timeLabel;
    private JLabel rowLabel;
    private JLabel locationLabel;
    private JLabel purchaseTimeLabel;
    private JLabel statesLabel;

 
    public MyOrder(String username,int number){
      
        mainPanel = new JPanel();
        this.username = username;
        Font textFont = new Font("Serial",Font.BOLD,15);
        try{
            con = DriverManager.getConnection(url,name,password);
            Statement statement = con.createStatement();
            String searchStatement = "SELECT * FROM ORDERINFO WHERE USERNAME='"+ this.username + "'";
            ResultSet result = statement.executeQuery(searchStatement);
            int i = 0;
            informationPanel = new JPanel[number];
            orderPanel = new JPanel[number];
            for(int j=0;j<number;j++){
                JPanel ipanel = new JPanel();
                informationPanel[j] = ipanel;
                JPanel jpanel = new JPanel();
                orderPanel[j] = jpanel;
            }
            while(result.next()){
                
                String movieName = result.getString("MOVIENAME");
                String row = result.getString("ROW");
                String col = result.getString("COL");
                String time = result.getString("BEGINTIME").substring(0, result.getString("BEGINTIME").length() - 2);
                String location = result.getString("LOCATION");
                String purchaseTime = result.getString("PURCHASETIME").substring(0, result.getString("PURCHASETIME").length() - 4);
                informationPanel[i].setLayout(new GridLayout(6,1));
                String imagePath = findPath(movieName);
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(200, 250, Image.SCALE_DEFAULT);
                imageIcon.setImage(image);
                poster = new JLabel(imageIcon);
                orderPanel[i].add(poster,BorderLayout.WEST);
                movieNameLabel = new JLabel("Name:"+movieName);
                movieNameLabel.setFont(textFont);
                informationPanel[i].add(movieNameLabel);
                timeLabel = new JLabel("Time:"+time);
                timeLabel.setFont(textFont);
                informationPanel[i].add(timeLabel);
                rowLabel = new JLabel("Row:"+row+" "+"COL:"+col);
                rowLabel.setFont(textFont);
                informationPanel[i].add(rowLabel);
                locationLabel = new JLabel("Location:"+location);
                locationLabel.setFont(textFont);
                informationPanel[i].add(locationLabel);
                purchaseTimeLabel = new JLabel("Purchase Time:" + purchaseTime);
                purchaseTimeLabel.setFont(textFont);
                informationPanel[i].add(purchaseTimeLabel);
                Timestamp beginTime = Timestamp.valueOf(time);
                if(isValidated(beginTime)){
                    statesLabel = new JLabel("State:Validate");
                    statesLabel.setForeground(Color.green);
                }
                else{
                    statesLabel = new JLabel("State:Not Validate");
                    statesLabel.setForeground(Color.red);
                }
                statesLabel.setFont(textFont);
                informationPanel[i].add(statesLabel);
                orderPanel[i].add(informationPanel[i],BorderLayout.NORTH);
                
                
                mainPanel.add(orderPanel[i]);
                i++;
            }
        }
        catch(Exception event){
            event.printStackTrace();
        }
        mainPanel.setLayout(new GridLayout(number,1));
        JScrollPane jsp = new JScrollPane(mainPanel);
        this.add(jsp);  
        this.setTitle(this.username+"'s Order");
        this.setBounds(100, 50, 600, 950);
        this.setVisible(true);
    }
    public String findPath(String movieName){
        String imagePath = null;
        try{
            
            Connection conn = DriverManager.getConnection(url,name,password);
            Statement s = conn.createStatement();
            String findPathStatement = "SELECT POSTER FROM MOVIEINFO WHERE MOVIENAME='"+ movieName + "'";
            ResultSet r = s.executeQuery(findPathStatement);
            while(r.next()){
                imagePath = r.getString("POSTER");
            }
        }
        catch(Exception event){
            event.printStackTrace();
        }
        return imagePath;
    }
    public boolean isValidated(Timestamp movieTime){
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        if(movieTime.after(currentTime)){
            return true;
        }
        else{
            return false;
        }
    }
}
