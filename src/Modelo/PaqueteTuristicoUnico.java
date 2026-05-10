/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public final class PaqueteTuristicoUnico extends PaqueteTuristico {
    private String nombreHotel;
    private String tipoDesayuno;

    public PaqueteTuristicoUnico(String codigo, String nombre, String tipoligiaTurismo, String descripcion, String origen, ArrayList<Destino> susDestinos, boolean hotel, boolean alimentacion, boolean alimentacionTodo, boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades, String nombreHotel, String tipoDesayuno) {
        super(codigo, nombre, tipoligiaTurismo, descripcion, origen, susDestinos, hotel, alimentacion, alimentacionTodo, vuelo, asistencia, tarifaDia, cantidadUnidades);
        this.nombreHotel = nombreHotel;
        this.tipoDesayuno = tipoDesayuno;
    }
    public PaqueteTuristicoUnico(String codigo, String nombre, String tipoligiaTurismo, String descripcion, String origen, ArrayList<Destino> susDestinos, boolean hotel, boolean alimentacion, boolean alimentacionTodo, boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades, String nombreHotel) {
        super(codigo, nombre, tipoligiaTurismo, descripcion, origen, susDestinos, hotel, alimentacion, alimentacionTodo, vuelo, asistencia, tarifaDia, cantidadUnidades);
        this.nombreHotel = nombreHotel;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public String getTipoDesayuno() {
        return tipoDesayuno;
    }

    public void setTipoDesayuno(String tipoDesayuno) {
        this.tipoDesayuno = tipoDesayuno;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PaqueteTuristicoUnico{" +
                "nombreHotel='" + nombreHotel + '\'' +
                ", tipoDesayuno='" + tipoDesayuno + '\'' +
                '}';
    }
    @Override
    public int calcularValorUnidad() {
        int valorBase = this.getTarifaDia() * this.calcularDuracionTotalDias();
        int adicionalDesayuno = 0;

        // Ejemplo de lógica adicional: Si es Buffet, se suma un costo extra [cite: 119, 120]
        if (this.tipoDesayuno != null && this.tipoDesayuno.equalsIgnoreCase("Buffet")) {
            adicionalDesayuno = 50000; // Valor de ejemplo para el adicional
        } else if (this.tipoDesayuno != null && this.tipoDesayuno.equalsIgnoreCase("Americano")) {
            adicionalDesayuno = 25000;
        }

        return valorBase + adicionalDesayuno;
    }
    
}

