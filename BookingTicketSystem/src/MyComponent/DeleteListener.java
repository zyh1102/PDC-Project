/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class DeleteListener implements ActionListener {

    JFrame frame;
    int row = 0;
    private static String url = "jdbc:derby:MOVIESYSTEM; create=true";
    private static String name = "root";
    private static String password = "root";
    private Connection con;

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonInTable button = (ButtonInTable) e.getSource();
        row = button.getRow();
        frame = button.getFrame();
        try {
            con = DriverManager.getConnection(url, name, password);
            Statement statement = con.createStatement();
            String searchStatement = "SELECT * FROM ORDERINFO";
            ResultSet result = statement.executeQuery(searchStatement);
            int i = 0;
            boolean isloop = true;
            while (isloop && result.next()) {
                if (i == row) {
                    String movieName = result.getString("MOVIENAME");
                    int movieRow = Integer.valueOf(result.getString("ROW"));
                    int movieCol = Integer.valueOf(result.getString("COL"));
                    String deleteStatement = "DELETE FROM ORDERINFO WHERE MOVIENAME='" + movieName + "' AND ROW=" + movieRow + " AND COL=" + movieCol + "";
                    statement.executeUpdate(deleteStatement);

                    JOptionPane.showMessageDialog(null, "Delete successfully", "Tip", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    isloop = false;
                }
                i++;
            }

        } catch (Exception event) {
            event.printStackTrace();
        }
    }

}
