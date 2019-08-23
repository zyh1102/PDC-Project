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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrator
 */
public class MyInfo extends JFrame implements ActionListener{
    private static String url = "jdbc:derby:MOVIESYSTEM; create=true";
    private static String name = "root";
    private static String password = "root";
    private Connection con;
    private String username;
    private String phone;
    private String email;
    private String oldPassword;
    private String profilePhotoPath;
    JLayeredPane layeredPane = new JLayeredPane();
    JPanel backgroundPanel;
    JLabel background;
    ImageIcon image;
    JPanel titlePanel;
    JPanel welcomePanel;
    JLabel welcomeLabel;
    JLabel profilePhoto;
    JButton setProfilePhoto;
    JLabel phoneNumber;
    JTextField phoneNumberTextField;
    JButton modifyNumber;
    JButton saveNumber;
    JLabel mailLabel;
    JTextField mailTextField;
    JButton modifyMail;
    JButton saveMail;
    JLabel passwordLabel;
    JPasswordField passwordTextField;
    JButton modifyPassword;
    JButton savePassword;
    JLabel inputAgainLabel;
    JPasswordField inputAgain;
    JPanel footPanel;
    JButton homeButton;
    JButton orderButton;
    JButton accountButton;
    
    
    public MyInfo(String username){
        this.username = username;
        this.setUserInformation(this.username);
        layeredPane = new JLayeredPane();
        image = new ImageIcon("./src/Images/whiteBackground.jpg");
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        background = new JLabel(image);
        backgroundPanel.add(background);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(2,1));
        titlePanel.setBounds(0,0,410,320);
        titlePanel.setBackground(Color.RED);
        layeredPane.add(titlePanel,JLayeredPane.MODAL_LAYER);
        
        welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.red);
        welcomePanel.setLayout(new BorderLayout());
        Font titleFont = new Font("Serial",Font.BOLD,20);
        welcomeLabel = new JLabel("Welcome , "+username+"!",JLabel.CENTER);
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setForeground(Color.WHITE);
        welcomePanel.add(welcomeLabel,BorderLayout.CENTER);
        titlePanel.add(welcomePanel);
        
        JPanel profilePhotoPanel = new JPanel();
        profilePhotoPanel.setBackground(Color.red);
        profilePhotoPanel.setLayout(null);
        profilePhoto = new JLabel();
        
        profilePhoto.setBorder(BorderFactory.createLineBorder(Color.white));
        profilePhoto.setBounds(150,0,100,100);
        profilePhoto.setIcon(paintLabel(this.profilePhotoPath,profilePhoto));
        profilePhotoPanel.add(profilePhoto);
        
        
        setProfilePhoto = new JButton("Change Photo");
        setProfilePhoto.setBounds(125,120,150,25);
        setProfilePhoto.setBackground(Color.BLUE);
        setProfilePhoto.setFocusPainted(false);
        setProfilePhoto.setForeground(Color.WHITE);
        setProfilePhoto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                setProfilePhotoActionPerformed(event);
            }
        });
        profilePhotoPanel.add(setProfilePhoto);
        titlePanel.add(profilePhotoPanel);
        
        Font textFont = new Font("Serial",Font.BOLD,14);
        phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setFont(textFont);
        phoneNumber.setBounds(30, 350, 120, 30);
        layeredPane.add(phoneNumber,JLayeredPane.MODAL_LAYER);
        
        phoneNumberTextField = new JTextField(this.phone);
        phoneNumberTextField.setEditable(false);
        phoneNumberTextField.setBounds(30,400,150,20);
        layeredPane.add(phoneNumberTextField,JLayeredPane.MODAL_LAYER);
                
        modifyNumber = new JButton("Modify"); 
        modifyNumber.setBackground(Color.GRAY);
        modifyNumber.setForeground(Color.WHITE);
        modifyNumber.setBounds(210, 400, 80, 20);
        modifyNumber.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyActionPerformed(e);
            }
            
        });
        layeredPane.add(modifyNumber,JLayeredPane.MODAL_LAYER);
                
        saveNumber = new JButton("Save"); 
        saveNumber.setBounds(310, 400, 70, 20);
        saveNumber.setBackground(Color.GRAY);
        saveNumber.setForeground(Color.WHITE);
        saveNumber.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInformationActionPerformed(e);
            }
            
        });
        layeredPane.add(saveNumber,JLayeredPane.MODAL_LAYER); 
        
        mailLabel = new JLabel("Email:");
        mailLabel.setFont(textFont);
        mailLabel.setBounds(30, 460, 120, 30);
        layeredPane.add(mailLabel,JLayeredPane.MODAL_LAYER);
        
        mailTextField = new JTextField(this.email);
        mailTextField.setEditable(false);
        mailTextField.setBounds(30,510,150,20);
        layeredPane.add(mailTextField,JLayeredPane.MODAL_LAYER);
                
        modifyMail = new JButton("Modify"); 
        modifyMail.setBounds(210, 510, 80, 20);
        modifyMail.setBackground(Color.GRAY);
        modifyMail.setForeground(Color.WHITE);
        modifyMail.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyActionPerformed(e);
            }
            
        });
        layeredPane.add(modifyMail,JLayeredPane.MODAL_LAYER);
                
        saveMail = new JButton("Save"); 
        saveMail.setBounds(310, 510, 70, 20);
        saveMail.setBackground(Color.GRAY);
        saveMail.setForeground(Color.WHITE);
        saveMail.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInformationActionPerformed(e);
            }
            
        });
        layeredPane.add(saveMail,JLayeredPane.MODAL_LAYER);              
                
                
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(textFont);
        passwordLabel.setBounds(30, 570, 120, 30);
        layeredPane.add(passwordLabel,JLayeredPane.MODAL_LAYER);
        
        passwordTextField = new JPasswordField(this.oldPassword);
        passwordTextField.setEditable(false);
        passwordTextField.setEchoChar('●');
        passwordTextField.setBounds(30,620,150,20);
        layeredPane.add(passwordTextField,JLayeredPane.MODAL_LAYER);
                
        modifyPassword = new JButton("Modify"); 
        modifyPassword.setBounds(210, 730, 80, 20);
        modifyPassword.setBackground(Color.GRAY);
        modifyPassword.setForeground(Color.WHITE);
        modifyPassword.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyActionPerformed(e);
            }
            
        });
        layeredPane.add(modifyPassword,JLayeredPane.MODAL_LAYER);
                
        savePassword = new JButton("Save"); 
        savePassword.setBounds(310, 730, 70, 20);
        savePassword.setBackground(Color.GRAY);
        savePassword.setForeground(Color.WHITE);
        savePassword.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInformationActionPerformed(e);
            }
            
        });
        layeredPane.add(savePassword,JLayeredPane.MODAL_LAYER);  
        
        inputAgainLabel = new JLabel("Input New Password Again:");
        inputAgainLabel.setFont(textFont);
        inputAgainLabel.setBounds(30,680,200,30);
        layeredPane.add(inputAgainLabel,JLayeredPane.MODAL_LAYER);
       
        inputAgain = new JPasswordField();
        inputAgain.setEchoChar('●');
        inputAgain.setEditable(false);
        inputAgain.setBounds(30, 730, 150, 20);
        layeredPane.add(inputAgain,JLayeredPane.MODAL_LAYER);  
        
        footPanel = new JPanel();
        footPanel.setBackground(Color.WHITE);
        homeButton = paintButton("./src/Images/home.png");
        homeButton.setText("Home");
        homeButton.addActionListener(this);
        
        orderButton = paintButton("./src/Images/order.png");
        orderButton.setText("Order");
        orderButton.addActionListener(this);
        accountButton = paintButton("./src/Images/pressAccount.jpg");
        accountButton.setText("Account");
        accountButton.setForeground(Color.red);

        footPanel.setLayout(new BorderLayout());
        footPanel.add(homeButton,BorderLayout.WEST);
        footPanel.add(orderButton,BorderLayout.CENTER);
        footPanel.add(accountButton,BorderLayout.EAST);
        footPanel.setBounds(0,850,400,60);
        layeredPane.add(footPanel,JLayeredPane.MODAL_LAYER);
        
      
        
        this.setLayeredPane(layeredPane);
        this.setTitle("My Information");
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
    private void setUserInformation(String username){
        try{
            con = DriverManager.getConnection(url,name,password);
            Statement statement = con.createStatement();
            String searchStatement = "SELECT * FROM USERINFO WHERE USERNAME='"+this.username+"'";
            ResultSet result = statement.executeQuery(searchStatement);
            result.next();
            phone = result.getString("PHONENUMBER");
            email = result.getString("EMAIL");
            profilePhotoPath = result.getString("PROFILEPHOTO");
            oldPassword = result.getString("PASSWORD");
            statement.close();
            result.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new JDialog(), "Please input legal information");
        }
    }
    private void setProfilePhotoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        upload(setProfilePhoto);
    }    
    private void upload(JButton uploadButton){
        JFileChooser choose = new JFileChooser();
        choose.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg/.png","JPG","PNG");
        choose.setFileFilter(filter);
        int value = choose.showOpenDialog(uploadButton);
        if(value == choose.APPROVE_OPTION){
            File file = choose.getSelectedFile();
            String fileName = file.getName();
            String prefix = fileName.substring(fileName.lastIndexOf(".")+1);
            if(!(prefix.equals("jpg")||prefix.equals("png"))){
                JOptionPane.showMessageDialog(new JDialog(), "Please Select the correct format of the photo(.jpg/.png)");
                return;
            }
            String path = "./src/ProfilePhoto/";
            File dir = new File(path);
            File []fileList = dir.listFiles();
            HashSet<String> fileSet = new HashSet();
            for(File f : fileList){
                fileSet.add(f.getName());
            }
            if(fileSet.contains(file.getName())){
//                JOptionPane.showMessageDialog(new JDialog(), "The picture exists");
                String absolutePath = choose.getSelectedFile().getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(absolutePath);
                imageIcon = new ImageIcon(path + file.getName());
                this.profilePhotoPath = path + file.getName();
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                imageIcon.setImage(image);
                profilePhoto.setIcon(imageIcon);
                this.updateProfilePhoto(this.profilePhotoPath);
                return;
            }
            FileInputStream input = null;
            FileOutputStream output = null;
            try{
                input = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                File outFile = new File(path , file.getName());
                output = new FileOutputStream(outFile);
                int length = 0;
                String absolutePath = choose.getSelectedFile().getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(absolutePath);
                while((length = input.read(buffer)) != -1){
                    output.write(buffer,0,length);
                }
                output.flush();
                imageIcon = new ImageIcon(path +  file.getName());
                this.profilePhotoPath = path + file.getName();
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                imageIcon.setImage(image);
                profilePhoto.setIcon(imageIcon);
                this.updateProfilePhoto(this.profilePhotoPath);
                JOptionPane.showMessageDialog(null, "Success in Upload", "Tips", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(FileNotFoundException event){
                JOptionPane.showMessageDialog(null, "Failure in Upload", "Tips", JOptionPane.ERROR_MESSAGE);
                event.printStackTrace();
            }
            catch(IOException event){
                JOptionPane.showMessageDialog(null, "Failure in Upload", "Tips", JOptionPane.ERROR_MESSAGE);
                event.printStackTrace();
            }
            
            
            finally{
                try{
                
                    output.close();
                    input.close();
                }
                catch(IOException event){
                    event.printStackTrace();
                }
               
            }
        }
    }
    public void updateProfilePhoto(String path){
        try{
            con = DriverManager.getConnection(url, name,password);
            Statement statement = con.createStatement();
            String updateStatement = "UPDATE USERINFO SET PROFILEPHOTO='"+ path +"' WHERE USERNAME='"+this.username+"'";
            statement.executeUpdate(updateStatement);
        }
        catch(Exception event){
            event.printStackTrace();
        }
    }
    private void modifyInformation(String type){
        if(type.equals("phone")){
            phoneNumberTextField.setEditable(true);
            
        }
        else if(type.equals("email")){
            mailTextField.setEditable(true);
        }
        else{
            passwordTextField.setEditable(true);
            inputAgain.setEditable(true);
        }
    }
    private void modifyActionPerformed(ActionEvent evt) {                                            
        // TODO add your handling code here:
//        JButton modifyMail;JButton modifyNumber;modifyPassword;
        if(evt.getSource() == modifyNumber){
            modifyInformation("phone");
        }
        else if(evt.getSource() == modifyMail){
            modifyInformation("email");
        }
        else{
            modifyInformation("password");
        }
    }
    private void updateInformation(String type,String context){
        try{
            con = DriverManager.getConnection(url, name,password);
            Statement statement = con.createStatement();
            String updateStatement = "UPDATE USERINFO SET " + type + "='" + context +"' WHERE USERNAME='" + this.username + "'";
            statement.executeUpdate(updateStatement);
        }
        catch(Exception event){
            event.printStackTrace();
        }
        finally{
            try{
                con.close();
            }
            catch(Exception event){
                event.printStackTrace();
            }
        }
    }
    private void updateInformationActionPerformed(ActionEvent evt) {                                            
        // TODO add your handling code here:
//        JButton modifyMail;JButton modifyNumber;modifyPassword;
        if(evt.getSource() == saveNumber){
            updateInformation("PHONENUMBER",phoneNumberTextField.getText());
            phoneNumberTextField.setText(phoneNumberTextField.getText());
            phoneNumberTextField.setEditable(false);
            JOptionPane.showMessageDialog(new JDialog(), "Modify Phone Number successfully!");
        }
        else if(evt.getSource() == saveMail){
            updateInformation("EMAIL",mailTextField.getText());
            mailTextField.setText(mailTextField.getText());
            mailTextField.setEditable(false);
            JOptionPane.showMessageDialog(new JDialog(), "Modify Email successfully!");
        }
        else{
            String newPassword =String.valueOf(passwordTextField.getPassword());
            String inputPassword = String.valueOf(inputAgain.getPassword());
            if(newPassword.equals(inputPassword)){
                updateInformation("PASSWORD",String.valueOf(passwordTextField.getPassword()));
                
                passwordTextField.setText(String.valueOf(passwordTextField.getPassword()));
                passwordTextField.setEditable(false);
                inputAgain.setText("");
                inputAgain.setEditable(false);
                JOptionPane.showMessageDialog(new JDialog(), "Modify Password successfully!");
            }
            else{
                JOptionPane.showMessageDialog(new JDialog(), "New Password is not same as password inputed again!");
            }
        }
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
        if(e.getSource() == homeButton){
            new Home(this.username);
            dispose();
        }
        else{
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
    }
}
