/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import MyComponent.Seat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 *
 * @author Administrator
 */
public class Purchase extends JFrame implements ActionListener, DocumentListener {

    private ResultSet result;
    private String username;
    private String text;
    private JPanel titlePanel;
    private JLabel movieTitle;
    private JLabel timeLabel;
    private JLabel time;
    private JLabel priceLabel;
    private JLabel price;
    private JLabel locationLabel;
    private JLabel location;
    private JLabel poster;
    private JLabel selectableLabel;
    private JLabel selectable;
    private JLabel soldLabel;
    private JLabel sold;
    private JLabel selectedLabel;
    private JLabel selected;
    private JLabel purchaseResultLabel;
    private JLabel purchaseResult;
    private ArrayList<String> resultList;
    private JLabel totalPriceLabel;
    private JLabel totalPrice;
    private JLabel pageLabel;
    private JButton purchase;
    private JButton back;
    private Seat[][] buttonGroup;
    private JLabel commentTitle;
    private JPanel commentTitlePanel;
    private JTextField commentTextField;
    private JLabel userCommentTitle;
    private JLabel tips;
    private JButton submit;
    private JButton prevPage;
    private JButton nextPage;
    private float perPrice = 0;
    private float total = 0;
    private int rageOfComment = 0;
    private int page = 1;
    JLabel[] profile = new JLabel[3];
    JLabel[] usernameLabel = new JLabel[3];
    JLabel[] commentTimeLabel = new JLabel[3];
    JLabel[] commentField = new JLabel[3];
    Font titleFont = new Font("Serial", Font.BOLD, 20);
    Font textFont = new Font("Serial", Font.BOLD, 16);
    public static String url = "jdbc:derby:MOVIESYSTEM; create=true";
    public static String name = "root";
    public static String password = "root";
    private Connection con;
    ArrayList<Integer> rowList;
    ArrayList<Integer> colList;

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawLine(110, 150, 590, 150);
        g.drawLine(110, 150, 110, 470);
        g.drawLine(110, 470, 590, 470);
        g.drawLine(590, 150, 590, 470);
        selectable = new JLabel();
        selectable.setBounds(150, 390, 39, 29);
        selectable.setIcon(paintLabel("./src/Images/emptySeat.png", selectable));
        this.add(selectable);
        selectableLabel = new JLabel();
        selectableLabel.setBounds(200, 400, 100, 20);
        selectableLabel.setText("Selectable");
        this.add(selectableLabel);
        sold = new JLabel();
        sold.setBounds(300, 390, 39, 29);
        sold.setIcon(paintLabel("./src/Images/fullSeated.png", sold));
        this.add(sold);
        soldLabel = new JLabel();
        soldLabel.setBounds(350, 400, 100, 20);
        soldLabel.setText("Sold");
        this.add(soldLabel);
        selected = new JLabel();
        selected.setBounds(430, 390, 39, 29);
        selected.setIcon(paintLabel("./src/Images/selectedSeat.png", selected));
        this.add(selected);
        selectedLabel = new JLabel();
        selectedLabel.setBounds(480, 400, 100, 20);
        selectedLabel.setText("Selected");
        this.add(selectedLabel);

        purchaseResultLabel = new JLabel("Your choice is:");
        purchaseResultLabel.setFont(textFont);
        purchaseResultLabel.setBounds(110, 450, 1000, 20);
        this.add(purchaseResultLabel);

        Font importantFont = new Font("Serial", Font.BOLD, 15);
        resultList = new ArrayList<String>();
        purchaseResult = new JLabel();
        purchaseResult.setFont(importantFont);
        purchaseResult.setForeground(Color.red);
        purchaseResult.setBounds(110, 470, 1000, 20);
        this.add(purchaseResult);

        totalPriceLabel = new JLabel("The total price is:");
        totalPriceLabel.setFont(textFont);
        totalPriceLabel.setBounds(110, 510, 1000, 20);
        this.add(totalPriceLabel);

        totalPrice = new JLabel();
        totalPrice.setFont(importantFont);
        totalPrice.setForeground(Color.red);
        totalPrice.setBounds(260, 512, 100, 20);
        this.add(totalPrice);

        purchase = new JButton("Purchase");
        purchase.setFocusPainted(false);
        purchase.setBounds(120, 540, 100, 40);
        purchase.setBackground(Color.red);
        purchase.setForeground(Color.white);
        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                purchaseActionPerformed(event);
            }

        });
        this.add(purchase);

        back = new JButton("Back");
        back.setFocusPainted(false);
        back.setBounds(700, 540, 100, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                backActionPerformed(event);
            }

        });
        this.add(back);

        prevPage = paintButton("./src/Images/prevPage.png");
        prevPage.setBounds(120, 910, 50, 50);
        prevPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                prevPageActionPerformed(event);
            }
        });
        this.add(prevPage);

        nextPage = paintButton("./src/Images/nextPage.png");
        nextPage.setBounds(580, 910, 50, 50);
        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                nextPageActionPerformed(event);
            }
        });
        this.add(nextPage);
    }

    public Purchase(ResultSet result, String username) {

        buttonGroup = new Seat[6][6];
        this.username = username;
        this.result = result;
        timeLabel = new JLabel("Time:");
        timeLabel.setFont(textFont);
        timeLabel.setBounds(110, 70, 100, 20);
        this.add(timeLabel);
        priceLabel = new JLabel("Price:");
        priceLabel.setFont(textFont);
        priceLabel.setBounds(450, 70, 100, 20);
        this.add(priceLabel);
        locationLabel = new JLabel("Location:");
        locationLabel.setBounds(750, 70, 100, 20);
        locationLabel.setFont(textFont);
        this.add(locationLabel);
        try {
            titlePanel = new JPanel();
            titlePanel.setBounds(0, 0, 990, 50);
            titlePanel.setBackground(Color.BLACK);
            titlePanel.setLayout(new BorderLayout());
            movieTitle = new JLabel(result.getString("MOVIENAME"), JLabel.CENTER);
            movieTitle.setFont(titleFont);
            movieTitle.setForeground(Color.WHITE);
            titlePanel.add(movieTitle, BorderLayout.CENTER);
            this.add(titlePanel);
            String timeString = result.getString("BEGINTIME");
            timeString = timeString.substring(0, timeString.length() - 2);
            time = new JLabel(timeString);
            time.setFont(textFont);
            time.setBounds(165, 70, 200, 20);
            this.add(time);
            price = new JLabel(result.getString("PRICE"));
            price.setFont(textFont);
            price.setBounds(505, 70, 100, 20);
            this.add(price);
            location = new JLabel(result.getString("LOCATION"));
            location.setFont(textFont);
            location.setBounds(825, 70, 100, 20);
            this.add(location);

            poster = new JLabel();
            poster.setBounds(650, 120, 250, 320);
            String path = result.getString("POSTER");
            poster.setIcon(paintLabel(path, poster));
            this.add(poster);

            perPrice = Float.parseFloat(result.getString("PRICE"));
        } catch (Exception event) {
            event.printStackTrace();
        }
        for (int i = 0; i < 6; i++) {
            JLabel rowNumber = new JLabel();
            rowNumber.setText("" + (i + 1));
            rowNumber.setBounds(120, 160 + 35 * i, 20, 20);
            this.add(rowNumber);
        }
        try {
            rowList = new ArrayList<Integer>();
            colList = new ArrayList<Integer>();
            con = DriverManager.getConnection(url, name, password);
            Statement statement = con.createStatement();
            String searchStatement = "SELECT * FROM ORDERINFO WHERE MOVIENAME='" + movieTitle.getText() + "' AND BEGINTIME='" + time.getText() + "' AND LOCATION='" + location.getText() + "'";
            ResultSet orderResult = statement.executeQuery(searchStatement);
            while (orderResult.next()) {
                int row = Integer.valueOf(orderResult.getString("ROW"));
                int col = Integer.valueOf(orderResult.getString("COL"));
                rowList.add(row);
                colList.add(col);
            }
        } catch (Exception event) {
            event.printStackTrace();
        }

        if (rowList.isEmpty()) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {

                    buttonGroup[i][j] = paintButton("./src/Images/emptySeat.png");
                    buttonGroup[i][j].addActionListener(this);
                    buttonGroup[i][j].setRow(i + 1);
                    buttonGroup[i][j].setColumn(j + 1);

                    buttonGroup[i][j].setBounds(150 + 70 * j, 150 + 35 * i, 40, 40);
                    this.add(buttonGroup[i][j]);
                }
            }
        } else {
            for (int size = 0; size < rowList.size(); size++) {
                int row = rowList.get(size) - 1;
                int col = colList.get(size) - 1;
                buttonGroup[row][col] = paintButton("./src/Images/fullSeated.png");
                buttonGroup[row][col].setRow(row + 1);
                buttonGroup[row][col].setColumn(col + 1);

                buttonGroup[row][col].setBounds(150 + 70 * col, 150 + 35 * row, 40, 40);
                this.add(buttonGroup[row][col]);

                buttonGroup[row][col].setSold(true);
                buttonGroup[row][col].addActionListener(null);
            }

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    buttonGroup[i][j] = new Seat();

                    if (buttonGroup[i][j].getSold() == false) {

                        buttonGroup[i][j] = paintButton("./src/Images/emptySeat.png");
                        buttonGroup[i][j].addActionListener(this);

                        buttonGroup[i][j].setRow(i + 1);
                        buttonGroup[i][j].setColumn(j + 1);

                        buttonGroup[i][j].setBounds(150 + 70 * j, 150 + 35 * i, 40, 40);
                        this.add(buttonGroup[i][j]);
                    }
                }

            }

        }
        commentTitlePanel = new JPanel();
        commentTitlePanel.setBounds(0, 590, 980, 30);
        commentTitlePanel.setBackground(Color.BLUE);
        commentTitlePanel.setLayout(new BorderLayout());
        commentTitle = new JLabel("Message Board", JLabel.CENTER);
        commentTitle.setFont(titleFont);
        commentTitle.setForeground(Color.WHITE);

        commentTitlePanel.add(commentTitle, BorderLayout.CENTER);
        this.add(commentTitlePanel);

        for (int i = 0; i < 3; i++) {
            JLabel profilelabel = new JLabel();
            JLabel singleUsernameLabel = new JLabel();
            JLabel singleCommentTimelabel = new JLabel();
            JLabel singleCommentFieldlabel = new JLabel();
//            profilelabel.setBorder(BorderFactory.createLineBorder(Color.red));
//            singleUsernameLabel.setBorder(BorderFactory.createLineBorder(Color.red));
//            singleCommentTimelabel.setBorder(BorderFactory.createLineBorder(Color.red));
//            singleCommentFieldlabel.setBorder(BorderFactory.createLineBorder(Color.red));
            profile[i] = profilelabel;
            profile[i].setBounds(120, 630 + i * 100, 50, 50);
            usernameLabel[i] = singleUsernameLabel;
            usernameLabel[i].setBounds(180, 630 + i * 100, 120, 20);
            commentTimeLabel[i] = singleCommentTimelabel;
            commentTimeLabel[i].setBounds(180, 660 + i * 100, 200, 20);
            commentField[i] = singleCommentFieldlabel;
            commentField[i].setBounds(120, 680 + i * 100, 500, 30);
            this.add(profile[i]);
            this.add(usernameLabel[i]);
            this.add(commentTimeLabel[i]);
            this.add(commentField[i]);
        }
        pageLabel = new JLabel("Page:1");
        pageLabel.setBounds(350, 930, 50, 20);
        this.add(pageLabel);
        this.showInitializedComment();

        Font tipFont = new Font("Serial", Font.BOLD, 15);
        userCommentTitle = new JLabel("Please write your comment:");
        userCommentTitle.setFont(tipFont);
        userCommentTitle.setBounds(650, 820, 200, 40);
        this.add(userCommentTitle);

        commentTextField = new JTextField();
        Document doc = commentTextField.getDocument();
        doc.addDocumentListener(this);
        commentTextField.setBounds(650, 860, 300, 30);
        this.add(commentTextField);

        tips = new JLabel("");
        tips.setBounds(650, 900, 200, 20);
        this.add(tips);

        submit = new JButton("Submit");
        submit.setBounds(850, 900, 100, 30);
        submit.setBackground(Color.GRAY);
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                submitActionPerformed(event);
            }

        });
        this.add(submit);

        this.setLayout(null);
        this.setBounds(100, 0, 980, 1030);
        this.setTitle("Purchase");
        this.setVisible(true);
    }

    private Seat paintButton(String path) {
        ImageIcon icon = new ImageIcon(path);
        Seat btn = new Seat();
        btn.setBounds(0, 0, 30, 29);
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

    private String findProfile(String username) {
        String profilePath = null;
        try {
            con = DriverManager.getConnection(url, name, password);
            Statement statement = con.createStatement();
            String searchStatement = "SELECT PROFILEPHOTO FROM USERINFO WHERE USERNAME='" + username + "'";
            ResultSet r = statement.executeQuery(searchStatement);
            if (!r.next()) {

            } else {
                r = statement.executeQuery(searchStatement);
                r.next();
                profilePath = r.getString("PROFILEPHOTO");
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
        return profilePath;
    }
    private Integer calculateNumberOfComment(String movieName){
        int numberOfComment = 1;
        try{
            con = DriverManager.getConnection(url,name,password);
            Statement statement = con.createStatement();
            String searchComment = "SELECT * FROM COMMENTINFO WHERE MOVIENAME='" + movieName + "'";
            ResultSet result = statement.executeQuery(searchComment);
            if(!result.next())
                return numberOfComment;
            else{
                while(result.next()){
                    numberOfComment ++;
                }
                
            }
        }
        catch(Exception event){
            event.printStackTrace();
        }
        return numberOfComment;
    }
    private void showInitializedComment(){
        try {
            int count = 0;
            con = DriverManager.getConnection(url,name,password);
            Statement statement = con.createStatement();
            String searchComment = "SELECT * FROM COMMENTINFO WHERE MOVIENAME='" + movieTitle.getText() + "' AND ID>0 AND ID<4";
            ResultSet commentResult = statement.executeQuery(searchComment);
            if(!commentResult.next()){
                
            }
            else{
                for (int i = 0; i < 3; i++) {
                    profile[i].setIcon(paintLabel(null, profile[i]));
                    usernameLabel[i].setText("");
                    commentTimeLabel[i].setText("");
                    commentField[i].setText("");
                }
                commentResult = statement.executeQuery(searchComment);
                while (commentResult.next()) {
                    if(count < 3){
                    profile[count].setIcon(paintLabel(commentResult.getString("PROFILE"), profile[count]));
                    usernameLabel[count].setText(commentResult.getString("USERNAME"));
                    commentTimeLabel[count].setText(commentResult.getString("TIME").substring(0, commentResult.getString("TIME").length() - 4));
                    commentField[count].setText(commentResult.getString("MESSAGE"));
                    count++;
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void showComment(int rageOfComment) {
        try {
            int count = 0;
            con = DriverManager.getConnection(url, name, password);
            Statement statement = con.createStatement();
            String searchComment = "SELECT * FROM COMMENTINFO WHERE MOVIENAME='" + movieTitle.getText() + "' AND ID>" + rageOfComment + " AND ID<" + (rageOfComment + 4);
            ResultSet commentResult = statement.executeQuery(searchComment);
            if (!commentResult.next()) {
                //JOptionPane.showMessageDialog(new JDialog(), "There is no comment!");
                System.out.println("prev:"+rageOfComment);
                this.rageOfComment -= 3;
                System.out.println("Afte:"+rageOfComment);
                this.page--;
                System.out.println("After"+page);
            } 
            else {
                for (int i = 0; i < 3; i++) {
                    profile[i].setIcon(paintLabel(null, profile[i]));
                    usernameLabel[i].setText("");
                    commentTimeLabel[i].setText("");
                    commentField[i].setText("");
                }
                commentResult = statement.executeQuery(searchComment);
                while (commentResult.next()) {
                    profile[count].setIcon(paintLabel(commentResult.getString("PROFILE"), profile[count]));
                    usernameLabel[count].setText(commentResult.getString("USERNAME"));
                    commentTimeLabel[count].setText(commentResult.getString("TIME").substring(0, commentResult.getString("TIME").length() - 4));
                    commentField[count].setText(commentResult.getString("MESSAGE"));
                    count++;

                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void nextPageActionPerformed(ActionEvent e) {

        rageOfComment += 3;
        System.out.println("Prev:"+page);
        this.page++;
        this.showComment(rageOfComment);
        this.pageLabel.setText("Page:" + this.page);
    }

    public void prevPageActionPerformed(ActionEvent e) {
        rageOfComment -= 3;
        if (rageOfComment < 0) {
            JOptionPane.showMessageDialog(new JDialog(), "This is the first page!");
            rageOfComment += 3;
        } else {
            this.showComment(rageOfComment);
            page--;
        }
        this.pageLabel.setText("Page:" + page);
    }

    public void backActionPerformed(ActionEvent e) {
        dispose();
    }

    public void purchaseActionPerformed(ActionEvent e) {
        try {
            con = DriverManager.getConnection(url, name, password);
            Statement statement = con.createStatement();
            if (!resultList.isEmpty()) {
                for (String s : resultList) {
                    int row = Integer.parseInt("" + s.charAt(4));
                    int col = Integer.parseInt("" + s.charAt(13));
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    String insertStatement = "INSERT INTO ORDERINFO(USERNAME,MOVIENAME,BEGINTIME,ROW,COL,TOTALPRICE,LOCATION,PURCHASETIME) VALUES ('" + this.username + "','" + movieTitle.getText() + "','" + time.getText() + "'," + row + "," + col + "," + Float.parseFloat(price.getText()) + ",'" + location.getText() + "','" + currentTime + "')";
                    statement.executeUpdate(insertStatement);
                }

                JOptionPane.showMessageDialog(new JDialog(), "Success in purchase");
                dispose();
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Please select your seat");
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void submitActionPerformed(ActionEvent e) {
        try {
            String message = commentTextField.getText();
            if (message.length() <= 0) {
                JOptionPane.showMessageDialog(new JDialog(), "Please comment!");
            } else if (message.length() > 70) {
                JOptionPane.showMessageDialog(new JDialog(), "The word count is too long!");

            } else if (message.contains("'")) {
                JOptionPane.showMessageDialog(new JDialog(), "There is a illegal character:(')");
            } else {
                int id = this.calculateNumberOfComment(movieTitle.getText());
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                con = DriverManager.getConnection(url, name, password);
                Statement statement = con.createStatement();
                String profilePath = this.findProfile(username);
                String insertStatement = "INSERT INTO COMMENTINFO(ID,MOVIENAME,USERNAME,TIME,PROFILE,MESSAGE) VALUES (" + id + ",'" + movieTitle.getText() + "','" + this.username + "','" + currentTime + "','" + profilePath + "','" + message + "')";
                statement.executeUpdate(insertStatement);
                commentTextField.setText("");
                JOptionPane.showMessageDialog(new JDialog(), "Success in commenting!");
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Seat button = (Seat) e.getSource();
        if (button.getSelected() == false) {
            text = "";
            ImageIcon icon = new ImageIcon("./src/Images/selectedSeat.png");
            Image temp = icon.getImage().getScaledInstance(30, 29, icon.getImage().SCALE_DEFAULT);
            icon = new ImageIcon(temp);
            button.setIcon(icon);
            resultList.add("Row:" + button.getRow() + " Column:" + button.getColumn() + ";  ");
            for (String s : resultList) {
                text += s;
            }

            total = (resultList.size()) * perPrice;
            purchaseResult.setText(text);
            totalPrice.setText("" + total);
            button.setSelected(true);
        } else {
            text = "";
            ImageIcon icon = new ImageIcon("./src/Images/emptySeat.png");
            Image temp = icon.getImage().getScaledInstance(30, 29, icon.getImage().SCALE_DEFAULT);
            icon = new ImageIcon(temp);
            button.setIcon(icon);
            resultList.remove("Row:" + button.getRow() + " Column:" + button.getColumn() + ";  ");
            for (String s : resultList) {
                text += s;
            }
            purchaseResult.setText(text);
            total = (resultList.size()) * perPrice;
            totalPrice.setText("" + total);
            button.setSelected(false);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (commentTextField.getText().length() > 70) {
            tips.setText("The word count is too long!");
        } else if (commentTextField.getText().contains("'")) {
            tips.setText("There is a illegal character:(')");
        } else {
            tips.setText("");
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (commentTextField.getText().length() > 70) {
            tips.setText("The word count is too long!");
        } else if (commentTextField.getText().contains("'")) {
            tips.setText("There is a illegal character:(')");
        } else {
            tips.setText("");
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (commentTextField.getText().length() > 70) {
            tips.setText("The word count is too long!");
        } else if (commentTextField.getText().contains("'")) {
            tips.setText("There is a illegal character:(')");
        } else {
            tips.setText("");
        }
    }
}
