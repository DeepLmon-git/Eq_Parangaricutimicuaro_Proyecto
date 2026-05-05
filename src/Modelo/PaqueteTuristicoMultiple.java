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
public final class PaqueteTuristicoMultiple extends PaqueteTuristico {
    private String obsequio;

    public PaqueteTuristicoMultiple(String codigo, String nombre, String tipoligiaTurismo, String descripcion, String origen, ArrayList<Destino> susDestinos, boolean hotel, boolean alimentacion, boolean alimentacionTodo, boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades, String obsequio) {
        super(codigo, nombre, tipoligiaTurismo, descripcion, origen, susDestinos, hotel, alimentacion, alimentacionTodo, vuelo, asistencia, tarifaDia, cantidadUnidades);
        this.obsequio = obsequio;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PaqueteTuristicoMultiple{" +
                "obsequio='" + obsequio + '\'' +
                '}';
    }
    @Override
    public int calcularValorUnidad() {
        return 0;
    }
    public Destino obtenerDestinoInicial() {
        return null;
    }
    public Destino obtenerDestinoFinal() {
        return null;
    }

}

