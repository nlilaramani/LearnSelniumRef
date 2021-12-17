/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.sel.learnselenium.Calculator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author itexps
 */

public class CalculatorTest {
    
    public CalculatorTest() {
    }
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Executed setUpClass() method");
        c=new Calculator();
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Executed tearDownClass() method");
    }
    //instance variable
    static Calculator c;
    @Before
    public void setUp() {
        System.out.println("Before method executed.");
        c=new Calculator();
    }
    
    @After
    public void tearDown() {
        System.out.println("After method executed.");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    // Annotation for declaring method as a unit test method
    @Test
    public void testAdd(){
        // ADD -> 10 + 20 =30
        System.out.println("testAdd()");
        //Calculator c=new Calculator();
        //assertNotNull(c);
        double result=c.add(10, 20);
        assertTrue(result>0);
        assertEquals(30, result,0.0);
    }

    @Test
    public void testSub(){
        // Subtract -> 20 - 10=10
        System.out.println("testSub()");
        //Calculator c=new Calculator();
        //assertNotNull(c);
        double result=c.sub(20, 10);
        assertTrue(result>0);
        assertEquals(10, result,0.0);
    }
    
    @Ignore
    @Test
    public void testAdd1(){
        // ADD -> 10 + 20 =30
        Calculator c=new Calculator();
        double result=c.add(100, 200);
       
        assertEquals(400, result,0.0);
    }
    
    @Test
    public void testSomeDoubleResult(){
        System.out.println("testSomeDoubleResult()");
        // ADD -> 10 + 20 =30
        int result=10+20;
       
        assertEquals(30, result);
    }
    
    //@Ignore("Need to fix this later")
    @Test(expected=NullPointerException.class)
    public void testException(){
        Calculator c;
        c= new Calculator();
        c=null;
        c.add(10,10);
        
    }
    
}