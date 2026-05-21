/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class PaqueteTuristicoUnicoTest {

    @Test
    public void testCalcularValorUnidad() {
        System.out.println("Probando calcularValorUnidad para paquete Unico");
        
        Destino d1 = new Destino();
        d1.setDiasPermanencia(3); 
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(d1);

        PaqueteTuristicoUnico instance = new PaqueteTuristicoUnico(
                "001", "Vacaciones123", "Aventura", "Desc", "Cali", destinos, 
                true, true, true, true, true, 100000, 1, "Hotel Sol", "Buffet"
        );

        int expResult = 350000; 
        int result = instance.calcularValorUnidad();

        assertEquals(expResult, result);
    }
}
