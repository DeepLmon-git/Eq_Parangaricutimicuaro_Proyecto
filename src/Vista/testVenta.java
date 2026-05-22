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
 
public class testVenta {
 
    public static void main(String[] args) {
 
        System.out.println("=== TEST VENTA ===");
 
        // Crear destino
        LinkedList<String> atractivos = new LinkedList<>();
        atractivos.add("Playa");
        Destino destino = new Destino("Cartagena", 5, atractivos, true);
 
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino);
 
        // Crear paquete
        PaqueteTuristicoUnico paquete = new PaqueteTuristicoUnico(
            "PKG001", "Paquete Cartagena Tour", "Sol y Playa",
            "Disfruta Cartagena con todo incluido",
            "Cali", destinos,
            true, true, false, true, true,
            200000, 3,
            "Hotel El Lagito", "Americano"
        );
 
        // Crear cliente
        Cliente cliente = new Cliente(
            'C', "123456789", false, "Juan Perez",
            "juan@email.com", "3001234567", "Juan", 10.0
        );
 
        // Crear venta
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
        paquetes.add(paquete);
        Venta venta = new Venta(
            1, LocalDateTime.now(), LocalDateTime.now(),
            cliente, paquetes, 'A'
        );
 
        // Mostrar resultados
        System.out.println("Numero venta: " + venta.getNumero());
        System.out.println("Cliente: " + venta.getSuCliente().getNombre());
        System.out.println("Estado: " + venta.getEstado());
        System.out.println("Cantidad unidades: " + venta.calcularCantidadTotalUnidadesPaquetes());
        System.out.println("Valor total paquetes: $" + venta.calcularValorTotalPaquetes());
        System.out.println("Valor descuento: $" + venta.calcularValorDescuento());
        System.out.println("Total a pagar: $" + venta.calcularValorTotalPagar());
    }
}