/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Venta {
    private int numero;
    private LocalDateTime fechaHoraGeneracion;
    private LocalDateTime fechaHoraActualizacion;
    private Cliente suCliente;
    private ArrayList<PaqueteTuristico> susPaquetesTuristicos;
    private char estado;

    public Venta(int numero, LocalDateTime fechaHoraGeneracion, LocalDateTime fechaHoraActualizacion, Cliente suCliente, ArrayList<PaqueteTuristico> susPaquetesTuristicos, char estado) {
        this.numero = numero;
        this.fechaHoraGeneracion = fechaHoraGeneracion;
        this.fechaHoraActualizacion = fechaHoraActualizacion;
        this.suCliente = suCliente;
        this.susPaquetesTuristicos = susPaquetesTuristicos;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDateTime getFechaHoraGeneracion() {
        return fechaHoraGeneracion;
    }

    public void setFechaHoraGeneracion(LocalDateTime fechaHoraGeneracion) {
        this.fechaHoraGeneracion = fechaHoraGeneracion;
    }

    public LocalDateTime getFechaHoraActualizacion() {
        return fechaHoraActualizacion;
    }

    public void setFechaHoraActualizacion(LocalDateTime fechaHoraActualizacion) {
        this.fechaHoraActualizacion = fechaHoraActualizacion;
    }

    public Cliente getSuCliente() {
        return suCliente;
    }

    public void setSuCliente(Cliente suCliente) {
        this.suCliente = suCliente;
    }

    public ArrayList<PaqueteTuristico> getSusPaquetesTuristicos() {
        return susPaquetesTuristicos;
    }

    public void setSusPaquetesTuristicos(ArrayList<PaqueteTuristico> susPaquetesTuristicos) {
        this.susPaquetesTuristicos = susPaquetesTuristicos;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    /**
     * Calcula la cantidad total de unidades sumando las unidades 
     * de todos los paquetes turísticos incluidos en la venta.
     */
    public int calcularCantidadTotalUnidadesPaquetes() {
        int totalUnidades = 0;
        if (this.susPaquetesTuristicos != null) {
            for (PaqueteTuristico paquete : this.susPaquetesTuristicos) {
                totalUnidades += paquete.getCantidadUnidades();
            }
        }
        return totalUnidades;
    }
    
    public int calcularValorTotalPaquetes() {
        // Pendiente de implementar lógica
        return 0;
    }
    
    public int calcularValorDescuento() {
        // Pendiente de implementar lógica
        return 0;
    }
    
    public int calcularValorTotalPagar() {
        // Pendiente de implementar lógica
        return 0;
    }

    @java.lang.Override
    public java.lang.String toString() {
        String listaPaquetes = "Paquetes turisticos añadidos: \n";
        int i = 1;
        for(PaqueteTuristico  objP :susPaquetesTuristicos){
            listaPaquetes += "Paquete "+i+": \n"+objP.toString();
        }
        return """
               Venta:
               numero=""" + numero +
                "\nfechaHoraGeneracion=" + fechaHoraGeneracion +
                "\nfechaHoraActualizacion=" + fechaHoraActualizacion +               
                "\nestado=" + estado +
                "\nsuCliente=" + suCliente.toString() +
                "\ntotalUnidades=" + calcularCantidadTotalUnidadesPaquetes() +
                "\nsusPaquetesTuristicos=" + listaPaquetes 
                ;
    }
}