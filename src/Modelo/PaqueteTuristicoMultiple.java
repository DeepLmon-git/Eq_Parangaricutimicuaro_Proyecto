/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

public final class PaqueteTuristicoMultiple extends PaqueteTuristico {
    private String obsequio;

    public PaqueteTuristicoMultiple(String codigo, String nombre, String tipoligiaTurismo, String descripcion, String origen, ArrayList<Destino> susDestinos, boolean hotel, boolean alimentacion, boolean alimentacionTodo, boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades, String obsequio) {
        super(codigo, nombre, tipoligiaTurismo, descripcion, origen, susDestinos, hotel, alimentacion, alimentacionTodo, vuelo, asistencia, tarifaDia, cantidadUnidades);
        this.obsequio = obsequio;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return super.toString() + " PaqueteTuristicoMultiple{" +
                "obsequio='" + obsequio + '\'' +
                '}';
    }

    @Override
    public int calcularValorUnidad() {
        int totalDias = 0;
       
        if (this.susDestinos != null) {
            for (Destino destino : this.susDestinos) {
                totalDias += destino.getDiasPermanencia();
            }
        }
    
        return this.tarifaDia * totalDias;
    }

    public Destino obtenerDestinoInicial() {
        
        if (this.susDestinos != null && !this.susDestinos.isEmpty()) {
           
            return this.susDestinos.get(0);
        }
        return null;
    }

    public Destino obtenerDestinoFinal() {
        
        if (this.susDestinos != null && !this.susDestinos.isEmpty()) {
            // El destino final está en la última posición (tamaño de la lista - 1)
            return this.susDestinos.get(this.susDestinos.size() - 1);
        }
        return null;
    }

}