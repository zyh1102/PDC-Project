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
public class InsertMovieWorkStationTest {
    
    public InsertMovieWorkStationTest() {
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
     * Test of timeValidated method, of class InsertMovieWorkStation.
     */
    @Test
    public void testTimeValidated() {
        System.out.println("timeValidated");
        String BeginTime = "2020-10-20 14:00:00";
        Timestamp insertBeginTime = Timestamp.valueOf(BeginTime);
        String location = "ROOM 1";
        String insertDuration = "30";
        InsertMovieWorkStation instance = new InsertMovieWorkStation("admin");
        boolean expResult = true;
        boolean result = instance.timeValidated(insertBeginTime, location, insertDuration);
        assertEquals(expResult, result);
       
        BeginTime = "2019-01-30 13:30:00";
        insertBeginTime = Timestamp.valueOf(BeginTime);
        location = "ROOM 1";
        insertDuration = "120";
        expResult = false;
        result = instance.timeValidated(insertBeginTime, location, insertDuration);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateTime method, of class InsertMovieWorkStation.
     */
    @Test
    public void testCalculateTime() {
        System.out.println("calculateTime");
        String beginTimeText = "2019-01-11 17:12:00";
        Timestamp beginTime = Timestamp.valueOf(beginTimeText);
        Integer duration = 120;
        InsertMovieWorkStation instance = new InsertMovieWorkStation("admin");
        String expTime = "2019-01-11 19:12:00";
        Timestamp expResult = Timestamp.valueOf(expTime);
        Timestamp result = instance.calculateTime(beginTime, duration);
        assertEquals(expResult, result);
        
    }
    
}
