package Modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Cliente.
 */
public class ClienteTest {
    
    public ClienteTest() {
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
     * Test de TipoIdentificacion
     */
    @Test
    public void testSetYGetTipoIdentificacion() {
        Cliente instance = new Cliente();
        char expResult = 'C'; // 'C' de Cédula, por ejemplo
        instance.setTipoIdentificacion(expResult);
        assertEquals(expResult, instance.getTipoIdentificacion());
    }

    /**
     * Test de NumeroIdentificacion
     */
    @Test
    public void testSetYGetNumeroIdentificacion() {
        Cliente instance = new Cliente();
        String expResult = "1144555666";
        instance.setNumeroIdentificacion(expResult);
        assertEquals(expResult, instance.getNumeroIdentificacion());
    }

    /**
     * Test de Empresa (booleano)
     */
    @Test
    public void testSetYIsEmpresa() {
        Cliente instance = new Cliente();
        boolean expResult = true; // Simulamos que sí es una empresa
        instance.setEmpresa(expResult);
        assertEquals(expResult, instance.isEmpresa());
    }

    /**
     * Test de Nombre
     */
    @Test
    public void testSetYGetNombre() {
        Cliente instance = new Cliente();
        String expResult = "Santiago Arboleda";
        instance.setNombre(expResult);
        assertEquals(expResult, instance.getNombre());
    }

    /**
     * Test de Email
     */
    @Test
    public void testSetYGetEmail() {
        Cliente instance = new Cliente();
        String expResult = "santiago@correo.com";
        instance.setEmail(expResult);
        assertEquals(expResult, instance.getEmail());
    }

    /**
     * Test de Telefono
     */
    @Test
    public void testSetYGetTelefono() {
        Cliente instance = new Cliente();
        String expResult = "3001234567";
        instance.setTelefono(expResult);
        assertEquals(expResult, instance.getTelefono());
    }

    /**
     * Test de NombreContacto
     */
    @Test
    public void testSetYGetNombreContacto() {
        Cliente instance = new Cliente();
        String expResult = "Contacto de Emergencia";
        instance.setNombreContacto(expResult);
        assertEquals(expResult, instance.getNombreContacto());
    }

    /**
     * Test de PorcentajeDescuento
     */
    @Test
    public void testSetYGetPorcentajeDescuento() {
        Cliente instance = new Cliente();
        double expResult = 15.5; // 15.5% de descuento
        instance.setPorcentajeDescuento(expResult);
        // El tercer parámetro '0' es el margen de error permitido al comparar decimales
        assertEquals(expResult, instance.getPorcentajeDescuento(), 0);
    }

    /**
     * Test del método toString
     */
    @Test
    public void testToString() {
        Cliente instance = new Cliente();
        instance.setNombre("Cliente Prueba");
        
        // Simplemente verificamos que el método devuelva un texto y no esté vacío/nulo
        String result = instance.toString();
        assertNotNull(result, "El método toString no debería devolver nulo");
        assertFalse(result.isEmpty(), "El método toString no debería devolver un texto vacío");
    }
}