/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import java.sql.Timestamp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class MyOrderTest {
    
    public MyOrderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findPath method, of class MyOrder.
     */
    @Test
    public void testFindPath() {
        System.out.println("findPath");
        String movieName = "Venom";
        MyOrder instance = new MyOrder("admin",6);
        String expResult = "./src/Poster/Venom.jpg";
        String result = instance.findPath(movieName);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidated method, of class MyOrder.
     */
    @Test
    public void testIsValidated() {
        System.out.println("isValidated");
        String movieTimeText = "2020-09-10 14:00:00";
        Timestamp movieTime = Timestamp.valueOf(movieTimeText);
        MyOrder instance = new MyOrder("admin",6);
        boolean expResult = true;
        boolean result = instance.isValidated(movieTime);
        assertEquals(expResult, result);
        
    }
    
}
