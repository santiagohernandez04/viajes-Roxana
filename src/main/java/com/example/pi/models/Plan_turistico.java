package com.example.pi.models;

import javax.swing.*;

public class Plan_turistico {

    public String[] getPuntosVisita() {
        return puntosVisita;
    }

    public void setPuntosVisita(String[] puntosVisita) {
        this.puntosVisita = puntosVisita;
    }

    public String[] puntosVisita;

    public long getValor_plan() {
        return valor_plan;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public void setValor_plan(long valor_plan) {
        this.valor_plan = valor_plan;
    }

    private long valor_plan;
    public int getCant_planes() {
        return cant_planes;
    }

    public void setCant_planes(int cant_planes) {
        this.cant_planes = cant_planes;
    }

    private int cant_planes;
    private String titulo, descripcion, fech_creacion, fech_modificacio;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFech_creacion() {
        return fech_creacion;
    }

    public void setFech_creacion(String fech_creacion) {
        this.fech_creacion = fech_creacion;
    }

    public String getFech_modificacio() {
        return fech_modificacio;
    }

    public void setFech_modificacio(String fech_modificacio) {
        this.fech_modificacio = fech_modificacio;
    }

    public int getId_planturistico() {
        return id_planturistico;
    }

    public void setId_planturistico(int id_planturistico) {
        this.id_planturistico = id_planturistico;
    }

    public int getCant_dias() {
        return cant_dias;
    }

    public void setCant_dias(int cant_dias) {
        this.cant_dias = cant_dias;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(boolean alimentacion) {
        this.alimentacion = alimentacion;
    }

    private int id_planturistico, cant_dias;
    private boolean estado, alimentacion;
}