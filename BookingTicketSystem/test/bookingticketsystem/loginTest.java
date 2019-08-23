/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class loginTest {
    public Connection con;
    public static String url="jdbc:derby:MOVIESYSTEM; create=true";;
    public static String name="root";
    public static String password="root";
    public loginTest() {
        
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
            con = DriverManager.getConnection(url, name, password);
        } catch (SQLException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @After
    public void tearDown() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    /**
     * Test of insertUserTable method, of class login.
     */
    @Test
    public void testInsertUserTable() {
        System.out.println("insertUserTable");
        login instance = new login();
        instance.insertUserTable();
        boolean expected = true;
        boolean actual = this.checkTableExisting("USERINFO");
        Assert.assertSame(expected,actual);
   }

    /**
     * Test of insertAdminTable method, of class login.
     */
    @Test
    public void testInsertAdminTable() {
        System.out.println("insertAdminTable");
        login instance = new login();
        instance.insertAdminTable();
        boolean expected = true;
        boolean actual = this.checkTableExisting("ADMININFO");
        Assert.assertSame(expected,actual);
    }
//
    /**
     * Test of insertMovieTable method, of class login.
     */
    @Test
    public void testInsertMovieTable() {
        System.out.println("insertMovieTable");
        login instance = new login();
        instance.insertMovieTable();
        boolean expected = true;
        boolean actual = this.checkTableExisting("MOVIEINFO");
        Assert.assertSame(expected,actual);
    }
//
    /**
     * Test of insertOrderTable method, of class login.
     */
    @Test
    public void testInsertOrderTable() {
        System.out.println("insertOrderTable");
        login instance = new login();
        instance.insertOrderTable();
        boolean expected = true;
        boolean actual = this.checkTableExisting("ORDERINFO");
        Assert.assertSame(expected,actual);
    }

    /**
     * Test of insertCommentTable method, of class login.
     */
    @Test
    public void testInsertCommentTable() {
        System.out.println("insertCommentTable");
        login instance = new login();
        instance.insertCommentTable();
        boolean expected = true;
        boolean actual = this.checkTableExisting("COMMENTINFO");
        Assert.assertSame(expected,actual);
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
    
}
