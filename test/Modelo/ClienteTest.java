package Modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {

    public ClienteTest() {
    }

    /**
     * Método auxiliar que crea un cliente de prueba con los 8 parámetros
     * reales y exactos de tu clase original.
     */
    private Cliente crearClientePrueba() {
        return new Cliente(
            'C', 
            "1234567890", 
            true, 
            "Viajes S.A.", 
            "contacto@viajes.com", 
            "555-1234", 
            "Juan Pérez", 
            15.0
        );
    }

    @Test
    public void testGetTipoIdentificacion() {
        Cliente instance = crearClientePrueba();
        char expResult = 'C';
        char result = instance.getTipoIdentificacion();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNumeroIdentificacion() {
        Cliente instance = crearClientePrueba();
        String expResult = "1234567890";
        String result = instance.getNumeroIdentificacion();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsEmpresa() {
        Cliente instance = crearClientePrueba();
        boolean expResult = true;
        boolean result = instance.isEmpresa();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNombre() {
        Cliente instance = crearClientePrueba();
        String expResult = "Viajes S.A.";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetEmail() {
        Cliente instance = crearClientePrueba();
        String expResult = "contacto@viajes.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTelefono() {
        Cliente instance = crearClientePrueba();
        String expResult = "555-1234";
        String result = instance.getTelefono();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNombreContacto() {
        Cliente instance = crearClientePrueba();
        String expResult = "Juan Pérez";
        String result = instance.getNombreContacto();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPorcentajeDescuento() {
        Cliente instance = crearClientePrueba();
        double expResult = 15.0;
        double result = instance.getPorcentajeDescuento();
        assertEquals(expResult, result, 0.0);
    }
}