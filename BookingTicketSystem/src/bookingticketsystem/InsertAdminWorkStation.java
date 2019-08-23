/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrator
 */
public class InsertAdminWorkStation extends JFrame implements ActionListener,MouseListener{
    private String username;
    private String profilePhotoPath = null;
    Connection con;
    String url = "jdbc:derby:MOVIESYSTEM; create=true";
    String name = "root";
    String password = "password";
    JLayeredPane layeredPane = new JLayeredPane();
    JPanel backgroundPanel;
    JLabel background;
    ImageIcon image;
    JLabel profilePhotoLabel;
    JButton setProfilePhoto;
    JLabel adminNameLabel;
    JTextField adminNameTextField;
    JLabel adminPasswordLabel;
    JTextField adminPasswordTextField;
    JButton addButton;
    JButton back;

    public InsertAdminWorkStation(String username){
        this.username = username;
        layeredPane = new JLayeredPane();
        image = new ImageIcon("./src/Images/whiteBackground.jpg");
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        background = new JLabel(image);
        backgroundPanel.add(background);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        
        profilePhotoLabel = new JLabel();
        profilePhotoLabel.setBounds(150, 70, 100, 100);
        profilePhotoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        layeredPane.add(profilePhotoLabel, JLayeredPane.MODAL_LAYER);
        
        setProfilePhoto = new JButton("Set Photo");
        setProfilePhoto.setBounds(280,141,100,30);
        this.setButton(setProfilePhoto);
        
        adminNameLabel = new JLabel("Admin Name:");
        adminNameLabel.setBounds(150, 200, 150, 30);
        layeredPane.add(adminNameLabel,JLayeredPane.MODAL_LAYER);
        
        adminNameTextField = new JTextField();
        adminNameTextField.setBounds(250, 205, 150, 20);
        layeredPane.add(adminNameTextField,JLayeredPane.MODAL_LAYER);
        
        adminPasswordLabel = new JLabel("Password:");
        adminPasswordLabel.setBounds(150, 300, 150, 30);
        layeredPane.add(adminPasswordLabel,JLayeredPane.MODAL_LAYER);
        
        adminPasswordTextField = new JTextField();
        adminPasswordTextField.setBounds(250,305,150,20);
        layeredPane.add(adminPasswordTextField,JLayeredPane.MODAL_LAYER);
        
        addButton = new JButton("Add");
        addButton.setBounds(150,450,70,30);
        this.setButton(addButton);
        
        
        back = new JButton("Back");
        back.setBounds(330, 450, 70, 30);
        this.setButton(back);
        
        this.setLayeredPane(layeredPane);
        this.setTitle("Insert New Admin");
        this.setBounds(100, 100, 600, 600);
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
            String absolutePath = choose.getSelectedFile().getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(absolutePath);
            if(fileSet.contains(file.getName())){
                
                imageIcon = new ImageIcon(path + file.getName());
                this.profilePhotoPath = path + file.getName();
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                imageIcon.setImage(image);
                profilePhotoLabel.setIcon(imageIcon);
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
                
                while((length = input.read(buffer)) != -1){
                    output.write(buffer,0,length);
                }
                output.flush();
                imageIcon = new ImageIcon(path + file.getName());
                this.profilePhotoPath = path + file.getName();
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                imageIcon.setImage(image);
                profilePhotoLabel.setIcon(imageIcon);
                JOptionPane.showMessageDialog(null, "Succuss in upload", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(FileNotFoundException event){
                JOptionPane.showMessageDialog(null, "Failure in upload", "提示", JOptionPane.ERROR_MESSAGE);
                event.printStackTrace();
            }
            catch(IOException event){
                JOptionPane.showMessageDialog(null, "Failure in upload", "提示", JOptionPane.ERROR_MESSAGE);
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
    public boolean isValidate(String adminName){
        if(adminName.length() >= 20){
            JOptionPane.showMessageDialog(null, "The word count of the name must be smaller than 20", "Tips", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        else{
            try {
                con = DriverManager.getConnection(url,name,password);
                Statement statement = con.createStatement();
                String searchStatement = "SELECT * FROM ADMININFO WHERE ADMINNAME='" + adminName + "'";
                ResultSet adminInfoResult = statement.executeQuery(searchStatement);
                if(!adminInfoResult.next()){
                    searchStatement = "SELECT * FROM USERINFO WHERE USERNAME='" + adminName + "'";
                    ResultSet userInfoResult = statement.executeQuery(searchStatement);
                    if(!userInfoResult.next()){
                        return true;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Admin name as user name has been used!", "Tips", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Admin name has been used!", "Tips", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(InsertAdminWorkStation.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InsertAdminWorkStation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    private void addAdminInformation(){
        if(profilePhotoPath == null){
            JOptionPane.showMessageDialog(null, "Please Set Your Profile Photo", "Tips", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(adminNameTextField.getText().equals("") || adminPasswordTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please input admin's information", "Tips", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String adminName = adminNameTextField.getText();
            String adminPassword = adminPasswordTextField.getText();
            try{
                boolean isValidated = isValidate(adminName);
                con = DriverManager.getConnection(url,name,password);
                Statement statement = con.createStatement();
                if(isValidated){
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    String insertStatement = "INSERT INTO ADMININFO(ID,ADMINNAME,PASSWORD) VALUES ('" + uuid + "','"+ adminName + "','" + adminPassword + "')";
                    statement.executeUpdate(insertStatement);
                    insertStatement = "INSERT INTO USERINFO(ID,USERNAME,PASSWORD,PROFILEPHOTO) VALUES ('" + uuid + "','" + adminName + "','" + adminPassword + "','" + this.profilePhotoPath + "')";
                    statement.executeUpdate(insertStatement);
                    JOptionPane.showMessageDialog(null, "Add successfully", "Tips", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(Exception event){
                event.printStackTrace();
            }
            finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InsertAdminWorkStation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == setProfilePhoto){
            upload(setProfilePhoto);
        }
        else if(e.getSource() == addButton){
            addAdminInformation();
        }
        else{
            new AdminWorkStation(this.username);
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
        if(e.getSource() == addButton){
            addButton.setBackground(Color.red);
        }
        else if(e.getSource() == setProfilePhoto){
            setProfilePhoto.setBackground(Color.red);
        }
        else{
            back.setBackground(Color.red);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == addButton){
            addButton.setBackground(Color.GRAY);
        }
        else if(e.getSource() == setProfilePhoto){
            setProfilePhoto.setBackground(Color.GRAY);
        }
        else{
            back.setBackground(Color.GRAY);
        }
    }
    
    
    
}
