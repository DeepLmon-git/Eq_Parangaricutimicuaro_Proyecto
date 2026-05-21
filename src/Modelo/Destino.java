/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.LinkedList;

/**
 *
 * @author admin
 */
public class Destino {
    private String nombreLugar;
    private int diasPermanencia;
    private LinkedList<String> atractivos;
    private boolean atractivosIncluidos;

    public Destino(String nombreLugar, int diasPermanencia, LinkedList<String> atractivos, boolean atractivosIncluidos) {
        this.nombreLugar = nombreLugar;
        this.diasPermanencia = diasPermanencia;
        this.atractivos = atractivos;
        this.atractivosIncluidos = atractivosIncluidos;
    }

    Destino() {
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public int getDiasPermanencia() {
        return diasPermanencia;
    }

    public void setDiasPermanencia(int diasPermanencia) {
        this.diasPermanencia = diasPermanencia;
    }

    public LinkedList<String> getAtractivos() {
        return atractivos;
    }

    public void setAtractivos(LinkedList<String> atractivos) {
        this.atractivos = atractivos;
    }

    public boolean isAtractivosIncluidos() {
        return atractivosIncluidos;
    }

    public void setAtractivosIncluidos(boolean atractivosIncluidos) {
        this.atractivosIncluidos = atractivosIncluidos;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Destino{" +
                "nombreLugar='" + nombreLugar + '\'' +
                ", diasPermanencia=" + diasPermanencia +
                ", atractivos=" + atractivos +
                ", atractivosIncluidos=" + atractivosIncluidos +
                '}';
    }
}

