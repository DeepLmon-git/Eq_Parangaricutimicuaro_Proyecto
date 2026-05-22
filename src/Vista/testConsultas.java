/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author d
 */
 
import Modelo.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
 
public class testConsultas {
 
    public static void main(String[] args) {
 
        System.out.println("=== TEST CONSULTAS ===");
 
        ArrayList<Venta> ventas = new ArrayList<>();
 
       
        LinkedList<String> atractivos1 = new LinkedList<>();
        atractivos1.add("Museo");
        Destino destino1 = new Destino("Bogota", 3, atractivos1, true);
        ArrayList<Destino> destinos1 = new ArrayList<>();
        destinos1.add(destino1);
        PaqueteTuristicoUnico p1 = new PaqueteTuristicoUnico(
            "P001", "Paquete Bogota City Tour", "Cultural",
            "Recorre la capital colombiana",
            "Cali", destinos1,
            true, true, false, false, true,
            150000, 2, "Hotel Bogota", "Americano"
        );
        Cliente c1 = new Cliente('C', "111111", false, "Ana Gomez",
            "ana@email.com", "300111", "Ana", 15.0);
        ArrayList<PaqueteTuristico> paquetes1 = new ArrayList<>();
        paquetes1.add(p1);
        Venta v1 = new Venta(1, LocalDateTime.now(), LocalDateTime.now(), c1, paquetes1, 'A');
        ventas.add(v1);
 
        
        LinkedList<String> atractivos2 = new LinkedList<>();
        atractivos2.add("Feria de las flores");
        Destino destino2 = new Destino("Medellin", 4, atractivos2, false);
        ArrayList<Destino> destinos2 = new ArrayList<>();
        destinos2.add(destino2);
        PaqueteTuristicoUnico p2 = new PaqueteTuristicoUnico(
            "P002", "Paquete Medellin Flores", "Cultural",
            "Vive la feria de las flores en Medellin",
            "Cali", destinos2,
            true, true, true, true, true,
            180000, 4, "Hotel Medellin", "Buffet"
        );
        Cliente c2 = new Cliente('N', "900123456", true, "Empresa XYZ",
            "xyz@email.com", "300222", "Carlos", 20.0);
        ArrayList<PaqueteTuristico> paquetes2 = new ArrayList<>();
        paquetes2.add(p2);
        Venta v2 = new Venta(2, LocalDateTime.now(), LocalDateTime.now(), c2, paquetes2, 'C');
        ventas.add(v2);
 
       
        System.out.println("\n-- Todas las ventas --");
        for (Venta v : ventas) {
            System.out.println("Venta #" + v.getNumero() + " - " + v.getSuCliente().getNombre());
        }
 
      
        System.out.println("\n-- Venta numero 1 --");
        for (Venta v : ventas) {
            if (v.getNumero() == 1) {
                System.out.println("Cliente: " + v.getSuCliente().getNombre());
                System.out.println("Total a pagar: $" + v.calcularValorTotalPagar());
            }
        }
 
      
        System.out.println("\n-- Primera venta --");
        System.out.println("Venta #" + ventas.get(0).getNumero());
 
        System.out.println("\n-- Ultima venta --");
        System.out.println("Venta #" + ventas.get(ventas.size() - 1).getNumero());
 
   
        System.out.println("\n-- Ventas activas --");
        for (Venta v : ventas) {
            if (v.getEstado() == 'A') {
                System.out.println("Venta #" + v.getNumero());
            }
        }
 
        System.out.println("\n-- Ventas canceladas --");
        for (Venta v : ventas) {
            if (v.getEstado() == 'C') {
                System.out.println("Venta #" + v.getNumero());
            }
        }
    }
}