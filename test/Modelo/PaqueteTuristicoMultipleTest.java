/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class PaqueteTuristicoMultipleTest {

    @Test
    public void testObtenerDestinosYValor() {
        System.out.println("Probando paquete Multiple");
        
        Destino d1 = new Destino(); d1.setDiasPermanencia(2);
        Destino d2 = new Destino(); d2.setDiasPermanencia(4);
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(d1);
        destinos.add(d2);

        PaqueteTuristicoMultiple paquete = new PaqueteTuristicoMultiple(
                "002", "Eurotrip123", "Cultural", "Desc", "Cali", destinos, 
                true, true, false, true, true, 200000, 1, "Cámara"
        );

        assertEquals(d1, paquete.obtenerDestinoInicial());
        assertEquals(d2, paquete.obtenerDestinoFinal());
        assertEquals(1200000, paquete.calcularValorUnidad());
    }
}
