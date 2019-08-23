/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class MyInfoTest {
    private static String url = "jdbc:derby:MOVIESYSTEM; create=true";
    private static String name = "root";
    private static String password = "root";
    private Connection con;
    public MyInfoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            con = DriverManager.getConnection(url,name,password);
        } catch (SQLException ex) {
            Logger.getLogger(MyInfoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of updateProfilePhoto method, of class MyInfo.
     */
    @Test
    public void testUpdateProfilePhoto() {
        try {
            System.out.println("updateProfilePhoto");
            String path = new String("./src/ProfilePhoto/1.jpg");
            MyInfo instance = new MyInfo("admin");
            instance.updateProfilePhoto(path);
            boolean expected = true;
            boolean actual = false;
            Statement statement = con.createStatement();
            String searchStatement = "SELECT * FROM USERINFO WHERE USERNAME='admin'"; 
            ResultSet result = statement.executeQuery(searchStatement);
            String pathInDatabase = new String();
            while(result.next()){
                pathInDatabase = result.getString("PROFILEPHOTO");
                
            }
            if(pathInDatabase.equals(path)){
                actual = true;
            }
            else{
                actual = false;
            }
            Assert.assertSame(expected, actual);
        } catch (SQLException ex) {
            Logger.getLogger(MyInfoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
