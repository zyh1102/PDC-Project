/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrator
 */
public class InsertMovieWorkStation extends javax.swing.JFrame {

    /**
     * Creates new form AdminWorkStation
     */
    public InsertMovieWorkStation(String username) {
        this.username = username;
        initComponents();
        this.setLocation(100, 100);
        this.setTitle("Insert New Movie");
        this.setVisible(true);
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        movieName = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        year = new javax.swing.JLabel();
        yearBox = new javax.swing.JComboBox<>();
        month = new javax.swing.JLabel();
        monthBox = new javax.swing.JComboBox<>();
        day = new javax.swing.JLabel();
        dayBox = new javax.swing.JComboBox<>();
        hour = new javax.swing.JLabel();
        hourBox = new javax.swing.JComboBox<>();
        minute = new javax.swing.JLabel();
        minuteBox = new javax.swing.JComboBox<>();
        second = new javax.swing.JLabel();
        secondBox = new javax.swing.JComboBox<>();
        nameText = new javax.swing.JTextField();
        priceText = new javax.swing.JTextField();
        duration = new javax.swing.JLabel();
        durationText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        locationBox = new javax.swing.JComboBox<>();
        add = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        poster = new javax.swing.JLabel();
        posterLabel = new javax.swing.JLabel();
        posterButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        movieName.setText("Name:");
        movieName.setFont(textFont);

        price.setText("Price:");
        price.setFont(textFont);

        timeLabel.setText("Time:");
        timeLabel.setFont(textFont);

        year.setText("Year:");
        year.setFont(textFont);

        yearBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        yearBox.removeAllItems();
        Calendar c = Calendar.getInstance();

        for(int i = 0; i<100; i++){
            int stringYear = c.get(Calendar.YEAR)+i;
            yearBox.addItem(Integer.toString(stringYear));
        }

        month.setText("Month:");
        month.setFont(textFont);

        monthBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        monthBox.removeAllItems();
        for(int i = 1; i<13; i++){
            int stringMonth = i;
            if(i<10){
                monthBox.addItem("0"+Integer.toString(i));
            }
            else{
                monthBox.addItem(Integer.toString(i));
            }
        }

        day.setText("Day:");
        day.setFont(textFont);

        dayBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dayBox.removeAllItems();
        for(int i = 1; i<32; i++){
            if(i<10){
                dayBox.addItem("0"+Integer.toString(i));
            }
            else{
                dayBox.addItem(Integer.toString(i));
            }
        }

        hour.setText("Hour:");
        hour.setFont(textFont);

        hourBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        hourBox.removeAllItems();
        for(int i = 0; i<24; i++){
            if(i<10){
                hourBox.addItem("0"+Integer.toString(i));
            }
            else{
                hourBox.addItem(Integer.toString(i));
            }
        }

        minute.setText("Minute:");
        minute.setFont(textFont);

        minuteBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        minuteBox.removeAllItems();
        for(int i = 0; i<60; i++){
            if(i<10){
                minuteBox.addItem("0"+Integer.toString(i));
            }
            else{
                minuteBox.addItem(Integer.toString(i));
            }
        }

        second.setText("Second:");
        second.setFont(textFont);

        secondBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        secondBox.removeAllItems();
        for(int i = 0; i<60; i++){
            if(i<10){
                secondBox.addItem("0"+Integer.toString(i));
            }
            else{
                secondBox.addItem(Integer.toString(i));
            }
        }

        duration.setText("Duration:");
        duration.setFont(textFont);

        jLabel11.setText("minutes");

        locationLabel.setText("Location:");
        locationLabel.setFont(textFont);

        locationBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        locationBox.removeAllItems();
        locationBox.addItem("ROOM 1");
        locationBox.addItem("ROOM 2");
        locationBox.addItem("ROOM 3");

        add.setText("Add");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.GRAY);
        add.setFocusPainted(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        back.setText("Back");
        back.setBackground(Color.GRAY);
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel14.setText("$");

        poster.setText("Poster:");
        poster.setFont(textFont);

        posterLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        posterButton.setText("Set Poster");
        posterButton.setBackground(Color.GRAY);
        posterButton.setForeground(Color.WHITE);
        posterButton.setFocusPainted(false);
        posterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(movieName))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(price)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))
                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(duration)
                            .addComponent(timeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(year)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(month))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hour)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(minute)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dayBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(minuteBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(second)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(secondBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(posterButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(poster))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(locationLabel)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(durationText, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                    .addComponent(locationBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(posterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieName)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(price)
                    .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeLabel)
                    .addComponent(year)
                    .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month)
                    .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(day)
                    .addComponent(dayBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hour)
                    .addComponent(hourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minute)
                    .addComponent(minuteBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(second)
                    .addComponent(secondBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(duration)
                    .addComponent(durationText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(posterButton)
                            .addComponent(posterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add)
                            .addComponent(back)))
                    .addComponent(poster))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:

        try {

            con = DriverManager.getConnection(url, name, password);
            Statement statement = con.createStatement();
            
            if ((!nameText.getText().equals("")) && (!nameText.getText().contains(" ")) && (!priceText.getText().equals("")) && (!priceText.getText().contains(" ")) && (!durationText.getText().equals("")) && (!durationText.getText().contains(" "))) {
                String movieName = nameText.getText();
                float price = Float.parseFloat(priceText.getText());
                String year = (yearBox.getSelectedItem()).toString();
                String month = (monthBox.getSelectedItem()).toString();
                String day = (dayBox.getSelectedItem()).toString();
                String hour = (hourBox.getSelectedItem()).toString();
                String min = (minuteBox.getSelectedItem()).toString();
                String second = (secondBox.getSelectedItem()).toString();
                int duration = Integer.parseInt(durationText.getText());
                String location = (locationBox.getSelectedItem()).toString();
                String dateTimeStamp = "" + year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second;
                Timestamp time1 = Timestamp.valueOf(dateTimeStamp);
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                if (timeValidated(time1, location, durationText.getText())) {

                    if (time1.after(currentTime)) {
                        String insertStatement = "INSERT INTO MOVIEINFO(MOVIENAME,PRICE,BEGINTIME,DURATION,LOCATION,POSTER) VALUES('" + movieName + "'," + price + ",'" + year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second + "'," + duration + ",'" + location + "','" + this.imagePath + "')";
                        statement.executeUpdate(insertStatement);
                    } else {
                        JOptionPane.showMessageDialog(new JDialog(), "The begin time of movie is illegal!");

                    }
                } else {
                    
                    JOptionPane.showMessageDialog(new JDialog(), "There have been films shown in the room during this time");
                }
                JOptionPane.showMessageDialog(new JDialog(), "Success in uploading!");
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "The information of movie provided is incomplete");
            }

        } catch (SQLException sqlEvent) {
            JOptionPane.showMessageDialog(new JDialog(), "The format of the time is wrong!");

        } catch (NumberFormatException numberEvent) {
            JOptionPane.showMessageDialog(new JDialog(), "The format of the input is wrong!");
        }

    }//GEN-LAST:event_addActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new AdminWorkStation(this.username);
    }//GEN-LAST:event_backActionPerformed

    private void posterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posterButtonActionPerformed
        // TODO add your handling code here:
        upload(posterButton);
    }//GEN-LAST:event_posterButtonActionPerformed
    private void upload(javax.swing.JButton uploadButton) {
        JFileChooser choose = new JFileChooser();
        choose.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg/.png", "JPG", "PNG");
        choose.setFileFilter(filter);
        int value = choose.showOpenDialog(uploadButton);
        if (value == choose.APPROVE_OPTION) {
            File file = choose.getSelectedFile();
            String fileName = file.getName();
            String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!(prefix.equals("jpg") || prefix.equals("png"))) {
                JOptionPane.showMessageDialog(new JDialog(), "Please Select the correct format of the photo(.jpg/.png)");
                return;
            }
            String path = "./src/Poster/";
            File dir = new File(path);
            File[] fileList = dir.listFiles();
            HashSet<String> fileSet = new HashSet();
            for (File f : fileList) {
                fileSet.add(f.getName());
            }
            if (fileSet.contains(file.getName())) {
                String absolutePath = choose.getSelectedFile().getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(absolutePath);
                imageIcon = new ImageIcon(path + file.getName());
                this.imagePath = path + file.getName();
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(posterLabel.getWidth(), posterLabel.getHeight(), Image.SCALE_DEFAULT);
                imageIcon.setImage(image);
                posterLabel.setIcon(imageIcon);
                return;
            }
            FileInputStream input = null;
            FileOutputStream output = null;
            try {
                input = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                File outFile = new File(path, file.getName());
                output = new FileOutputStream(outFile);
                int length = 0;
                String absolutePath = choose.getSelectedFile().getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(absolutePath);
                while ((length = input.read(buffer)) != -1) {
                    output.write(buffer, 0, length);
                }
                output.flush();
                imageIcon = new ImageIcon(path + file.getName());
                this.imagePath = path + file.getName();
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(posterLabel.getWidth(), posterLabel.getHeight(), Image.SCALE_DEFAULT);
                imageIcon.setImage(image);
                posterLabel.setIcon(imageIcon);
                JOptionPane.showMessageDialog(null, "Upload successfully", "Tips", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException event) {
                JOptionPane.showMessageDialog(null, "Failure in Upload!", "Tips", JOptionPane.ERROR_MESSAGE);
                event.printStackTrace();
            } catch (IOException event) {
                JOptionPane.showMessageDialog(null, "Failure in Upload!", "Tips", JOptionPane.ERROR_MESSAGE);
                event.printStackTrace();
            } finally {
                try {
                    output.close();
                    input.close();
                } catch (IOException event) {
                    event.printStackTrace();
                }
            }
        }
    }

    public boolean timeValidated(Timestamp insertBeginTime, String location, String insertDuration) {
        Integer insertDurationNum = Integer.parseInt(insertDuration);
        Timestamp insertEndTime = calculateTime(insertBeginTime, insertDurationNum);
        boolean isValidate = false;
        try {
            con = DriverManager.getConnection(url, name, password);
            Statement statement = con.createStatement();
            String searchStatement = "SELECT * FROM MOVIEINFO WHERE LOCATION='" + location + "'";
            ResultSet result = statement.executeQuery(searchStatement);
            if(!result.next()){
                return true;
            }
            else{
                result = statement.executeQuery(searchStatement);
            while (result.next()) {
                Timestamp beginTime = Timestamp.valueOf(result.getString("BEGINTIME"));
                Integer duration = Integer.parseInt(result.getString("DURATION"));
                Timestamp endTime = calculateTime(beginTime, duration);
                if (insertEndTime.before(beginTime) || insertBeginTime.after(endTime)) {
                    isValidate = true;
                } else {
                    return false;
                }
            }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InsertMovieWorkStation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isValidate;
    }

    public Timestamp calculateTime(Timestamp beginTime, Integer duration) {
        long endTime = beginTime.getTime() + duration * 60 * 1000;
        Timestamp endtime = new Timestamp(new Date(endTime).getTime());
        return endtime;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    public static String url = "jdbc:derby:MOVIESYSTEM; create=true";
    public static String name = "root";
    public static String password = "root";
    Connection con;
    private String username;
    Font textFont = new Font("Serial",Font.BOLD,16);
    private javax.swing.JButton back;
    private javax.swing.JLabel day;
    private javax.swing.JComboBox<String> dayBox;
    private javax.swing.JLabel duration;
    private javax.swing.JTextField durationText;
    private javax.swing.JLabel hour;
    private javax.swing.JComboBox<String> hourBox;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JComboBox<String> locationBox;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel minute;
    private javax.swing.JComboBox<String> minuteBox;
    private javax.swing.JLabel month;
    private javax.swing.JComboBox<String> monthBox;
    private javax.swing.JLabel movieName;
    private javax.swing.JTextField nameText;
    private javax.swing.JLabel poster;
    private javax.swing.JButton posterButton;
    private String imagePath = null;
    private javax.swing.JLabel posterLabel;
    private javax.swing.JLabel price;
    private javax.swing.JTextField priceText;
    private javax.swing.JLabel second;
    private javax.swing.JComboBox<String> secondBox;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel year;
    private javax.swing.JComboBox<String> yearBox;
    // End of variables declaration//GEN-END:variables

}
