package Modelo;

import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

public class DestinoTest {

    public DestinoTest() {
    }

    /**
     * Método auxiliar que crea un destino de prueba con los 4 parámetros
     * reales y exactos de tu clase original.
     */
    private Destino crearDestinoPrueba() {
        // 1. Creamos la lista de atractivos que pide tu clase
        LinkedList<String> listaAtractivos = new LinkedList<>();
        listaAtractivos.add("Playa");
        listaAtractivos.add("Ruinas");

        // 2. Retornamos el objeto Destino con los datos exactos
        return new Destino(
            "Cancún", 
            7, 
            listaAtractivos, 
            true
        );
    }

    @Test
    public void testGetNombreLugar() {
        Destino instance = crearDestinoPrueba();
        String expResult = "Cancún";
        String result = instance.getNombreLugar();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDiasPermanencia() {
        Destino instance = crearDestinoPrueba();
        int expResult = 7;
        int result = instance.getDiasPermanencia();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAtractivos() {
        Destino instance = crearDestinoPrueba();
        
        // Creamos la lista esperada para compararla
        LinkedList<String> expResult = new LinkedList<>();
        expResult.add("Playa");
        expResult.add("Ruinas");
        
        LinkedList<String> result = instance.getAtractivos();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsAtractivosIncluidos() {
        Destino instance = crearDestinoPrueba();
        boolean expResult = true;
        boolean result = instance.isAtractivosIncluidos();
        assertEquals(expResult, result);
    }
}