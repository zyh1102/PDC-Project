package bookingticketsystem;

import MyComponent.PicturePasswordField;
import MyComponent.PictureTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class login extends JFrame implements ActionListener, MouseListener {

    public static String url = "jdbc:derby:MOVIESYSTEM; create=true";
    public static String name = "root";
    public static String password = "root";
    public Connection con;
    JLayeredPane layeredPane;
    JPanel backgroundPanel;
    JLabel background;
    JLabel userLabel = new JLabel();
    JLabel userPassword = new JLabel();
    PictureTextField userText = new PictureTextField("./src/Images/user.png");
    PicturePasswordField passwordText = new PicturePasswordField("./src/Images/password.png");
    JButton loggin = new JButton("Login");
    JButton register = new JButton();
    ImageIcon image;

    ButtonGroup choice = new ButtonGroup();
    JRadioButton user = new JRadioButton("user", true);
    JRadioButton admin = new JRadioButton("admin");

    ArrayList userList = new ArrayList();
    ArrayList mailList = new ArrayList();

    public boolean isfail = true;

    public login() {

        this.insertUserTable();
        this.insertAdminTable();
        this.insertMovieTable();
        this.insertOrderTable();
        this.insertCommentTable();
        //this.setLayout(new GridLayout(5,1));
        Font font = new Font("Serial", Font.PLAIN, 13);
        layeredPane = new JLayeredPane();
        image = new ImageIcon("./src/Images/background.jpg");
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        background = new JLabel(image);
        backgroundPanel.add(background);
        userLabel.setText("Username:");
        userLabel.setFont(font);
        userPassword.setText("Password:");
        userPassword.setFont(font);
        passwordText.setEchoChar('●');

        loggin.setFont(font);
        loggin.setBackground(Color.GRAY);
//        loggin.setText("Loggin");
        loggin.setForeground(Color.WHITE);

        loggin.addActionListener(this);
        loggin.addMouseListener(this);
        register.setFont(font);
        register.setBackground(Color.GRAY);
        register.setText("Register");
        register.setForeground(Color.WHITE);

        register.addActionListener(this);
        register.addMouseListener(this);
        loggin.setBounds(210, 365, 80, 30);
        register.setBounds(300, 365, 100, 30);
        user.setBounds(230, 320, 80, 30);
        user.setBackground(Color.WHITE);
        admin.setBounds(320, 320, 80, 30);
        admin.setBackground(Color.WHITE);
        user.setFont(font);
        admin.setFont(font);
        choice.add(user);
        choice.add(admin);
        userLabel.setBounds(230, 200, 100, 100);
        userText.setBounds(300, 235, 100, 30);
        userPassword.setBounds(230, 250, 100, 100);
        passwordText.setBounds(300, 285, 100, 30);

        //将jp放到最底层。
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        //将jb放到高一层的地方

        layeredPane.add(loggin, JLayeredPane.MODAL_LAYER);
        layeredPane.add(register, JLayeredPane.MODAL_LAYER);
        layeredPane.add(userLabel, JLayeredPane.MODAL_LAYER);
        layeredPane.add(userText, JLayeredPane.MODAL_LAYER);
        layeredPane.add(userPassword, JLayeredPane.MODAL_LAYER);
        layeredPane.add(passwordText, JLayeredPane.MODAL_LAYER);
        layeredPane.add(user, JLayeredPane.MODAL_LAYER);
        layeredPane.add(admin, JLayeredPane.MODAL_LAYER);

        this.setLayeredPane(layeredPane);
        this.setTitle("Log in");
        this.setBounds(100, 100, image.getIconWidth(), image.getIconHeight());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loggin) {

            if (user.isSelected()) {
                try {
                    String userName = userText.getText();
                    String userPassword = String.valueOf(passwordText.getPassword());
                    con = DriverManager.getConnection(url, name, password);
                    Statement statement = con.createStatement();
                    String sqlStatement = "SELECT * FROM USERINFO";
                    ResultSet result = statement.executeQuery(sqlStatement);
                    boolean logginfail = false;
                    while (result.next()) {
                        if (result.getString("USERNAME").equals(userName) && result.getString("PASSWORD").equals(userPassword)) {
                            logginfail = true;
                            dispose();
                            new Home(userName);
                        }
                    }
                    if (logginfail == false) {
                        JOptionPane.showMessageDialog(new JDialog(), "Invaildated Username or Password!");
                        isfail = true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String userName = userText.getText();
                String userPassword = String.valueOf(passwordText.getPassword());
                try {
                    con = DriverManager.getConnection(url, name, password);
                    Statement statement = con.createStatement();
                    String sqlStatement = "SELECT * FROM ADMININFO";
                    ResultSet result = statement.executeQuery(sqlStatement);
                    boolean logginfail = false;
                    while (result.next()) {

                        if (result.getString("ADMINNAME").equals(userName) && result.getString("PASSWORD").equals(userPassword)) {
                            logginfail = true;
                            dispose();
                            new AdminWorkStation(userName);
                        }
                    }
                    if (logginfail == false) {
                        JOptionPane.showMessageDialog(new JDialog(), "Invaildated Username or Password!");
                        isfail = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        } else {
            try {
                con = DriverManager.getConnection(url, name, password);
                Statement statement = con.createStatement();
                String searchStatement = "SELECT * FROM USERINFO";
                ResultSet result = statement.executeQuery(searchStatement);
                while (result.next()) {
                    userList.add(result.getString("USERNAME"));
                    mailList.add(result.getString("EMAIL"));
                }
                new Register(userList, mailList);
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void insertUserTable() {

        try {
            con = DriverManager.getConnection(url, name, password);
            if (!checkTableExisting("USERINFO")) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String sqlStatement = "CREATE TABLE USERINFO(ID VARCHAR(40),USERNAME VARCHAR(20) not null,PASSWORD VARCHAR(20) not null,PHONENUMBER VARCHAR(11),EMAIL VARCHAR(30),PROFILEPHOTO VARCHAR(100),primary key(USERNAME))";
                Statement statement = con.createStatement();
                statement.executeUpdate(sqlStatement);
                String sqlStatement1 = "INSERT INTO USERINFO(ID,USERNAME,PASSWORD,PHONENUMBER,EMAIL,PROFILEPHOTO) VALUES ('" + uuid + "','admin','123','17816122250','5791159@qq.com','./src/ProfilePhoto/1.jpg')";
                statement.executeUpdate(sqlStatement1);
                uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String insertStatement = "INSERT INTO USERINFO(ID,USERNAME,PASSWORD,PHONENUMBER,EMAIL,PROFILEPHOTO) VALUES ('" + uuid + "','Alex','123','13221087179','1019808503@qq.com','./src/ProfilePhoto/timg.jpg')";
                statement.executeUpdate(insertStatement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertAdminTable() {
        try {
            con = DriverManager.getConnection(url, name, password);
            if (!checkTableExisting("ADMININFO")) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String sqlStatement = "CREATE TABLE ADMININFO(ID VARCHAR(40),ADMINNAME VARCHAR(20) NOT NULL, PASSWORD VARCHAR(20) NOT NULL,PRIMARY KEY(ID))";
                Statement statement = con.createStatement();
                statement.executeUpdate(sqlStatement);
                String sqlStatement1 = "INSERT INTO ADMININFO(ID,ADMINNAME,PASSWORD) VALUES ('" + uuid + "','admin','123')";
                statement.executeUpdate(sqlStatement1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertMovieTable() {
        try {
            con = DriverManager.getConnection(url, name, password);
            if (!checkTableExisting("MOVIEINFO")) {

                String insertTabelStatement = "CREATE TABLE MOVIEINFO(MOVIENAME VARCHAR(100) NOT NULL,PRICE FLOAT,BEGINTIME TIMESTAMP,LOCATION VARCHAR(10),DURATION INT,POSTER VARCHAR(100))";
                Statement statement = con.createStatement();
                statement.executeUpdate(insertTabelStatement);
                String insertMovie = "INSERT INTO MOVIEINFO(MOVIENAME,PRICE,BEGINTIME,DURATION,LOCATION,POSTER) VALUES ('Venom',6.5,'2019-01-30 12:30:00',112,'ROOM 1','./src/Poster/Venom.jpg'),('Fantastic Beasts 2',5.5,'2019-01-30 13:30:00',112,'ROOM 2','./src/Poster/Fantastic Beasts.jpg'),('Aquaman',5.5,'2019-01-30 13:30:00',112,'ROOM 3','./src/Poster/Aquaman.jpg')";

                statement.executeUpdate(insertMovie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertOrderTable() {
        try {
            con = DriverManager.getConnection(url, name, password);
            if (!checkTableExisting("ORDERINFO")) {
                Statement statement = con.createStatement();
                String insertStatement = "CREATE TABLE ORDERINFO(USERNAME VARCHAR(20),MOVIENAME VARCHAR(100),BEGINTIME TIMESTAMP,ROW INT,COL INT,TOTALPRICE FLOAT,LOCATION VARCHAR(10),PURCHASETIME TIMESTAMP)";
                statement.executeUpdate(insertStatement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertCommentTable() {
        try {
            con = DriverManager.getConnection(url, name, password);
            if (!checkTableExisting("COMMENTINFO")) {
                String sqlStatement = "CREATE TABLE COMMENTINFO(ID INT NOT NULL,MOVIENAME VARCHAR(100),USERNAME VARCHAR(20),TIME TIMESTAMP,PROFILE VARCHAR(100),MESSAGE VARCHAR(70))";
                Statement statement = con.createStatement();
                statement.executeUpdate(sqlStatement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkTableExisting(String newTableName) {
        try {
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);

            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    return true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }

        } catch (SQLException ex) {
        }
        return false;
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
        if (e.getSource() == loggin) {
            loggin.setBackground(Color.red);
        } else {
            register.setBackground(Color.red);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loggin) {
            loggin.setBackground(Color.GRAY);
        } else {
            register.setBackground(Color.GRAY);
        }
    }
}
