/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingticketsystem;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
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
public class InsertAdminWorkStationTest {
    
    public InsertAdminWorkStationTest() {
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
     * Test of isValidate method, of class InsertAdminWorkStation.
     */
    @Test
    public void testIsValidate() {
        System.out.println("isValidate");
        String adminName = "admin";
        InsertAdminWorkStation instance = new InsertAdminWorkStation("admin");
        boolean expResult = false;
        boolean result = instance.isValidate(adminName);
        assertEquals(expResult, result);
        
        adminName = "admin121";
        expResult = true;
        result = instance.isValidate(adminName);
        assertEquals(expResult,result);
    }
    
    
}
