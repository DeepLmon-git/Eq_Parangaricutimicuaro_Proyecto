/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import java.util.LinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author melio
 */
public class DestinoTest {
    
    public DestinoTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getNombreLugar method, of class Destino.
     */
    @org.junit.jupiter.api.Test
    public void testGetNombreLugar() {
        System.out.println("getNombreLugar");
        Destino instance = null;
        String expResult = "";
        String result = instance.getNombreLugar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreLugar method, of class Destino.
     */
    @org.junit.jupiter.api.Test
    public void testSetNombreLugar() {
        System.out.println("setNombreLugar");
        String nombreLugar = "";
        Destino instance = null;
        instance.setNombreLugar(nombreLugar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiasPermanencia method, of class Destino.
     */
    @org.junit.jupiter.api.Test
    public void testGetDiasPermanencia() {
        System.out.println("getDiasPermanencia");
        Destino instance = null;
        int expResult = 0;
        int result = instance.getDiasPermanencia();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDiasPermanencia method, of class Destino.
     */
    @org.junit.jupiter.api.Test
    public void testSetDiasPermanencia() {
        System.out.println("setDiasPermanencia");
        int diasPermanencia = 0;
        Destino instance = null;
        instance.setDiasPermanencia(diasPermanencia);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAtractivos method, of class Destino.
     */
    @org.junit.jupiter.api.Test
    public void testGetAtractivos() {
        System.out.println("getAtractivos");
        Destino instance = null;
        LinkedList<String> expResult = null;
        LinkedList<String> result = instance.getAtractivos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAtractivos method, of class Destino.
     */
    @org.junit.jupiter.api.Test
    public void testSetAtractivos() {
        System.out.println("setAtractivos");
        LinkedList<String> atractivos = null;
        Destino instance = null;
        instance.setAtractivos(atractivos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAtractivosIncluidos method, of class Destino.
     */
    @org.junit.jupiter.api.Test
    public void testIsAtractivosIncluidos() {
        System.out.println("isAtractivosIncluidos");
        Destino instance = null;
        boolean expResult = false;
        boolean result = instance.isAtractivosIncluidos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAtractivosIncluidos method, of class Destino.
     */
    @org.junit.jupiter.api.Test
    public void testSetAtractivosIncluidos() {
        System.out.println("setAtractivosIncluidos");
        boolean atractivosIncluidos = false;
        Destino instance = null;
        instance.setAtractivosIncluidos(atractivosIncluidos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Destino.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        System.out.println("toString");
        Destino instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
