/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class AdminWorkStation extends JFrame implements ActionListener,MouseListener {
    Connection con;
    String url = "jdbc:derby:MOVIESYSTEM; create=true";
    String name = "root";
    String password = "password";
    String username;
    JLayeredPane layeredPane = new JLayeredPane();
    JPanel backgroundPanel;
    JLabel background;
    ImageIcon image;
    JPanel titlePanel;
    JLabel title;
    JPanel tipPanel;
    JLabel tip;
    JButton insertMovie;
    JButton insertAdmin;
    JButton searchOrder;
    JButton back;
    
    public AdminWorkStation(String username){
        this.username = username;
        layeredPane = new JLayeredPane();
        image = new ImageIcon("./src/Images/whiteBackground.jpg");
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        background = new JLabel(image);
        backgroundPanel.add(background);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.MAGENTA);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,390,100);
        Font titleFont = new Font("Serial",Font.BOLD,20);
        title = new JLabel("Welcome , " + this.username + "!",JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(titleFont);
        titlePanel.add(title,BorderLayout.CENTER);
        layeredPane.add(titlePanel,JLayeredPane.MODAL_LAYER);
        
        tipPanel = new JPanel();
        tipPanel.setLayout(new BorderLayout());
        tipPanel.setBounds(0, 150, 390, 30);
        Font tipFont = new Font("Serial",Font.BOLD,16);
        tip = new JLabel("Please choose",JLabel.CENTER);
        tip.setFont(tipFont);
        tipPanel.add(tip,BorderLayout.CENTER);
        layeredPane.add(tipPanel,JLayeredPane.MODAL_LAYER);
        
        
        insertMovie = new JButton("Insert New Movie");
        insertMovie.setBounds(120,250,150,50);
        this.setButton(insertMovie);
        
        insertAdmin = new JButton("Insert New Admin");
        insertAdmin.setBounds(120,370,150,50);
        this.setButton(insertAdmin);
        
        searchOrder = new JButton("Search Order");
        searchOrder.setBounds(120, 490, 150, 50);
        this.setButton(searchOrder);
        
        back = new JButton("Back");
        back.setBounds(120,610,150,50);
        this.setButton(back);
        
        
        
        this.setLayeredPane(layeredPane);
        this.setTitle("Work Station");
        this.setBounds(100, 100, 400, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void setButton(JButton button){
        Font textFont = new Font("Serial",Font.BOLD,13);
        button.setFont(textFont);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(this);
        button.addMouseListener(this);
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);
    }
    private Integer getResultSetRow(ResultSet result){
        int count = 0;
        try{
            
            while(result.next()){
                count++;
               
            }
        }
        catch(Exception event){
            event.printStackTrace();
        }
        return count;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == insertMovie){
            new InsertMovieWorkStation(this.username);
            dispose();
        }
        else if (e.getSource() == insertAdmin){
            new InsertAdminWorkStation(this.username);
            dispose();
        }
        else if (e.getSource() == searchOrder){
            try{
                con = DriverManager.getConnection(url,name,password);
                Statement statement = con.createStatement();
                String searchStatement = "SELECT * FROM ORDERINFO";
                ResultSet result = statement.executeQuery(searchStatement);
                int row = getResultSetRow(result);
                if(row == 0){
                    JOptionPane.showMessageDialog(new JDialog(), "No Found!");
                }
                else{
                    result = statement.executeQuery(searchStatement);
                    new SearchOrder(result,row);
                }
            }
            catch(Exception event){
                event.printStackTrace();
            }
        }
        else{
            new login();
            dispose();
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == insertMovie){
            insertMovie.setBackground(Color.red);
        }
        else if(e.getSource() == insertAdmin){
            insertAdmin.setBackground(Color.red);
        }
        else if(e.getSource() == searchOrder){
            searchOrder.setBackground(Color.red);
        }
        else{
            back.setBackground(Color.red);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == insertMovie){
            insertMovie.setBackground(Color.GRAY);
        }
        else if(e.getSource() == insertAdmin){
            insertAdmin.setBackground(Color.GRAY);
        }
        else if(e.getSource() == searchOrder){
            searchOrder.setBackground(Color.GRAY);
        }
        else{
            back.setBackground(Color.GRAY);
        }
    }
    
}
