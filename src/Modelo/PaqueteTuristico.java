/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author CELY
 */
public abstract class PaqueteTuristico {
    protected String codigo;
    protected String nombre;
    protected String tipologiaTurismo;
    protected String descripcion;
    protected String origen;
    protected ArrayList<Destino> susDestinos;
    protected boolean hotel;
    protected boolean alimentacion;
    protected boolean alimentacionTodo;
    protected boolean vuelo;
    protected boolean asistencia;
    protected int tarifaDia;
    protected int cantidadUnidades;

    public PaqueteTuristico(String codigo, String nombre, String tipoligiaTurismo, String descripcion, String origen, ArrayList<Destino> susDestinos, boolean hotel, boolean alimentacion, boolean alimentacionTodo, boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipologiaTurismo = tipoligiaTurismo;
        this.descripcion = descripcion;
        this.origen = origen;
        this.susDestinos = susDestinos;
        this.hotel = hotel;
        this.alimentacion = alimentacion;
        this.alimentacionTodo = alimentacionTodo;
        this.vuelo = vuelo;
        this.asistencia = asistencia;
        this.tarifaDia = tarifaDia;
        this.cantidadUnidades = cantidadUnidades;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && nombre.length() >= 10) { // Mínimo 10 caracteres [cite: 91]
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre debe tener al menos 10 caracteres.");
        }
    }

    public String getTipologiaTurismo() {
        return tipologiaTurismo;
    }

    public void setTipologiaTurismo(String tipologiaTurismo) {
        this.tipologiaTurismo = tipologiaTurismo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() <= 500) { // Máximo 500 caracteres [cite: 95]
            this.descripcion = descripcion;
        } else {
            throw new IllegalArgumentException("La descripción no puede exceder los 500 caracteres.");
        }
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = (origen == null || origen.isEmpty()) ? "CALI" : origen;
    }

    public ArrayList<Destino> getSusDestinos() {
        return susDestinos;
    }

    public void setSusDestinos(ArrayList<Destino> susDestinos) {
        this.susDestinos = susDestinos;
    }

    public boolean isHotel() {
        return hotel;
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

    public boolean isAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(boolean alimentacion) {
        this.alimentacion = alimentacion;
    }

    public boolean isAlimentacionTodo() {
        return alimentacionTodo;
    }

    public void setAlimentacionTodo(boolean alimentacionTodo) {
        this.alimentacionTodo = alimentacionTodo;
    }

    public boolean isVuelo() {
        return vuelo;
    }

    public void setVuelo(boolean vuelo) {
        this.vuelo = vuelo;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public int getTarifaDia() {
        return tarifaDia;
    }

    public void setTarifaDia(int tarifaDia) {
        this.tarifaDia = tarifaDia;
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }
    
    public int calcularDuracionTotalDias() {
        int totalDias = 0;
        if (susDestinos != null) {
            for (Destino d : susDestinos) {
                totalDias += d.getDiasPermanencia(); 
            }
        }
        return totalDias;
    }
    
    public int calcularValorTotal() {
        return this.cantidadUnidades * this.calcularValorUnidad();
    }
    
    public abstract int calcularValorUnidad();
    
    

    @java.lang.Override
    public java.lang.String toString() {
        return "PaqueteTuristico{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoligiaTurismo='" + tipologiaTurismo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", origen='" + origen + '\'' +
                ", susDestinos=" + susDestinos +
                ", hotel=" + hotel +
                ", alimentacion=" + alimentacion +
                ", alimentacionTodo=" + alimentacionTodo +
                ", vuelo=" + vuelo +
                ", asistencia=" + asistencia +
                ", tarifaDia=" + tarifaDia +
                ", cantidadUnidades=" + cantidadUnidades +
                '}';
    }
    
}
