/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import MyComponent.BannerPanel;
import MyComponent.RoundRectTextFieldBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 */
public class Home extends JFrame implements ActionListener{
    Connection con;
    String url = "jdbc:derby:MOVIESYSTEM; create=true";
    String name = "root";
    String password = "password";
    String username;
    
    JLayeredPane layeredPane = new JLayeredPane();
    JPanel backgroundPanel;
    JLabel background;
    ImageIcon image;
    JButton back;
    JTextField searchTextField;
    JButton searchButton;
    JPanel recommandTitle;
    JLabel recommandLabel;
    JLabel posterVenom;
    JLabel VenomName;
    JLabel VenomPrice;
    JButton VenomSearch;
    JLabel VenomPurchaseText;
    JLabel posterAnimal;
    JLabel AnimalName;
    JLabel AnimalPrice;
    JButton AnimalSearch;
    JLabel AnimalPurchaseText;
    JPanel footPanel;
    JButton homeButton;
    JButton orderButton;
    JButton accountButton;
    
    BannerPanel banner;
    public Home(String username){
        this.username = username;
        layeredPane = new JLayeredPane();
        image = new ImageIcon("./src/Images/whiteBackground.jpg");
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        background = new JLabel(image);
        backgroundPanel.add(background);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setBounds(0,0,400,40);
        titlePanel.setBackground(Color.BLACK);
        back = paintButton("./src/Images/back.png");
        back.setBounds(0, 0, 30, 40);
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                backActionPerformed(e);
                dispose();
            }
            
        });
        titlePanel.add(back,BorderLayout.WEST);
        layeredPane.add(titlePanel,JLayeredPane.MODAL_LAYER);
        
        searchTextField = new JTextField(15);
        searchTextField.setBounds(80,45,200,20);
        RoundRectTextFieldBorder border = new RoundRectTextFieldBorder(new Color(192, 192, 192), 1, true);
        searchTextField.setBorder(border);
        layeredPane.add(searchTextField,JLayeredPane.MODAL_LAYER);
        
        searchButton = paintButton("./src/Images/SearchCompoent.png");
        searchButton.setBounds(300,45,30,29);
        searchButton.addActionListener(this);
        
        layeredPane.add(searchButton,JLayeredPane.MODAL_LAYER);
        
       ImageIcon []imageIcons = {
            new ImageIcon("./src/Images/banner1.jpg"),
            new ImageIcon("./src/Images/banner2.png"),
            new ImageIcon("./src/Images/banner3.jpg"),
            new ImageIcon("./src/Images/banner4.jpg"),
            new ImageIcon("./src/Images/banner5.png"),
        };
        banner = new BannerPanel(imageIcons);
        banner.setBounds(50, 80, 300, 200);
        banner.setBackground(Color.red);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                banner.repaint();
            }
        });
        timer.start();
        layeredPane.add(banner,JLayeredPane.MODAL_LAYER);
        
        recommandLabel = new JLabel("Recommended Movie");
        Font fontTitle = new Font("Serial",Font.BOLD,14);
        recommandLabel.setFont(fontTitle);
        recommandTitle = new JPanel();
        recommandTitle.setBounds(50,300,300,30);
        recommandTitle.add(recommandLabel,BorderLayout.CENTER);
        layeredPane.add(recommandTitle,JLayeredPane.MODAL_LAYER);
        
        posterVenom = new JLabel();
        posterVenom.setBounds(50,350,160,210);
        posterVenom.setIcon(paintLabel("./src/Poster/Venom.jpg",posterVenom));
        layeredPane.add(posterVenom,JLayeredPane.MODAL_LAYER);
        
        Font fontText = new Font("Serial",Font.BOLD,14);
        VenomName = new JLabel("Name : Venom");
        VenomName.setFont(fontText);
        VenomName.setBounds(250, 360, 100, 30);
        layeredPane.add(VenomName,JLayeredPane.MODAL_LAYER);
        
        VenomPrice = new JLabel("Price : $6.5");
        VenomPrice.setFont(fontText);
        VenomPrice.setBounds(250, 410, 100, 30);
        layeredPane.add(VenomPrice,JLayeredPane.MODAL_LAYER);
        
        VenomSearch = paintButton("./src/Images/purchase.png");
        VenomSearch.setBounds(248, 470, 30, 29);
        VenomSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                searchMovie(event);
            }
        });
        layeredPane.add(VenomSearch,JLayeredPane.MODAL_LAYER);
        
        VenomPurchaseText = new JLabel("Purchase");
        VenomPurchaseText.setBounds(283, 470, 100, 30);
        VenomPurchaseText.setFont(fontText);
        layeredPane.add(VenomPurchaseText,JLayeredPane.MODAL_LAYER);
        
        
        
        
        
        posterAnimal = new JLabel();
        posterAnimal.setBounds(50,600,160,210);
        posterAnimal.setIcon(paintLabel("./src/Poster/Aquaman.jpg",posterAnimal));
        layeredPane.add(posterAnimal,JLayeredPane.MODAL_LAYER);
        
        AnimalName = new JLabel("Name : Aquaman");
        AnimalName.setFont(fontText);
        AnimalName.setBounds(250, 610, 200, 30);
        layeredPane.add(AnimalName,JLayeredPane.MODAL_LAYER);
        
        AnimalPrice = new JLabel("Price : $5.5");
        AnimalPrice.setFont(fontText);
        AnimalPrice.setBounds(250, 660, 100, 30);
        layeredPane.add(AnimalPrice,JLayeredPane.MODAL_LAYER);
        
        AnimalSearch = paintButton("./src/Images/purchase.png");
        AnimalSearch.setBounds(248, 720, 30, 29);
        AnimalSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                searchMovie(event);
            }
        });
        layeredPane.add(AnimalSearch,JLayeredPane.MODAL_LAYER);
        
        AnimalPurchaseText = new JLabel("Purchase");
        AnimalPurchaseText.setBounds(283, 720, 100, 30);
        AnimalPurchaseText.setFont(fontText);
        layeredPane.add(AnimalPurchaseText,JLayeredPane.MODAL_LAYER);
        
        footPanel = new JPanel();
        footPanel.setBackground(Color.WHITE);
        homeButton = paintButton("./src/Images/pressHome.jpg");
        homeButton.setText("Home");
        homeButton.setForeground(Color.red);
        orderButton = paintButton("./src/Images/order.png");
        orderButton.setText("Order");
        orderButton.addActionListener(this);
        accountButton = paintButton("./src/Images/account.png");
        accountButton.setText("Account");
        accountButton.addActionListener(this);
        footPanel.setLayout(new BorderLayout());
        footPanel.add(homeButton,BorderLayout.WEST);
        footPanel.add(orderButton,BorderLayout.CENTER);
        footPanel.add(accountButton,BorderLayout.EAST);
        footPanel.setBounds(0,850,400,60);
        layeredPane.add(footPanel,JLayeredPane.MODAL_LAYER);
        
        this.setLayeredPane(layeredPane);
        this.setTitle("Home Page");
        this.setBounds(100, 50, 410, 950);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private JButton paintButton(String path){
        ImageIcon icon = new ImageIcon(path);
        JButton btn = new JButton();
        btn.setBounds(0,0,30,29);
        Image temp = icon.getImage().getScaledInstance(btn.getWidth(), btn.getHeight(), icon.getImage().SCALE_DEFAULT);
        icon = new ImageIcon(temp);
        btn.setIcon(icon);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setVerticalTextPosition(JButton.BOTTOM);
        btn.setHorizontalTextPosition(JButton.CENTER);
        return btn;
    }
    private ImageIcon paintLabel(String path, JLabel label) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
        imageIcon.setImage(image);
        return imageIcon;
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
    
    private void searchMovie(ActionEvent e){
        String movieName = null;
        String type = null;
        if(e.getSource() == VenomSearch){
            movieName = "Venom";
            type = "Venom";
        }
        else{
            movieName="Aquaman";
            type = "Aquaman";
        }
        try{
            con = DriverManager.getConnection(url,name,password);
            Statement statement = con.createStatement();
            String searchStatement = "SELECT * FROM MOVIEINFO WHERE MOVIENAME='"+movieName+"'  AND {fn TIMESTAMPDIFF(SQL_TSI_SECOND,BEGINTIME, CURRENT_TIMESTAMP)}<0";
            ResultSet result = statement.executeQuery(searchStatement);
            int row = this.getResultSetRow(result);
            if(row == 0){
                JOptionPane.showMessageDialog(new JDialog(), "No Found!");
            }
            else{
                result = statement.executeQuery(searchStatement);
                while(result.next()){
                    System.out.println(result.getString("MOVIENAME"));
                }
                result = statement.executeQuery(searchStatement);
                new SearchResult(result,row,this.username,type);
            }
        }
        catch(Exception event){
            event.printStackTrace();
        }
    }
    
    private void backActionPerformed(ActionEvent evt) {                                            
        
       new login();
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchButton){
            String movieName = searchTextField.getText();
            try{
                con = DriverManager.getConnection(url,name,password);
                Statement statement = con.createStatement();
                String searchStatement = "SELECT * FROM MOVIEINFO WHERE MOVIEINFO.MOVIENAME LIKE '"+movieName+"%' AND {fn TIMESTAMPDIFF(SQL_TSI_SECOND,BEGINTIME, CURRENT_TIMESTAMP)}<0";
                ResultSet result = statement.executeQuery(searchStatement);
                int row = this.getResultSetRow(result);
                if(row == 0){
                    JOptionPane.showMessageDialog(new JDialog(), "No Found!");
                }
                else{
                    result = statement.executeQuery(searchStatement);
                    new SearchResult(result,row,this.username,"*");
                }
                //new SearchResult(result);
            }
            catch(Exception event){
                event.printStackTrace();
            }
        }
        else if(e.getSource() == accountButton){
            new MyInfo(this.username);
            dispose();
        }
        else if(e.getSource() == orderButton){
            
            try{
                con = DriverManager.getConnection(url,name,password);
                Statement statement = con.createStatement();
                String searchStatement = "SELECT * FROM ORDERINFO WHERE USERNAME='"+ this.username + "'";
                ResultSet result = statement.executeQuery(searchStatement);
                int row = this.getResultSetRow(result);
                if(row == 0){
                    JOptionPane.showMessageDialog(new JDialog(), "No order list!");
                }
                else{
                    new MyOrder(this.username,row);
                    
                }
            }
            catch(Exception event){
                event.printStackTrace();
            }

        
        }
            
        else {
            dispose();
            new login();
        }
    }
}

