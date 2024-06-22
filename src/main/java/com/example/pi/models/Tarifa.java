package com.example.pi.models;

import java.util.Date;

public class Tarifa {
    public int getTitulo_plan() {
        return titulo_plan;
    }

    public void setTitulo_plan(int titulo_plan) {
        this.titulo_plan = titulo_plan;
    }

    public String getId_temporada() {
        return id_temporada;
    }

    public void setId_temporada(String id_temporada) {
        this.id_temporada = id_temporada;
    }

    public String getFech_creacion() {
        return fech_creacion;
    }

    public void setFech_creacion(String fech_creacion) {
        this.fech_creacion = fech_creacion;
    }

    public String getFech_modificacion() {
        return fech_modificacion;
    }

    public void setFech_modificacion(String fech_modificacion) {
        this.fech_modificacion = fech_modificacion;
    }

    public Date getFech_inicio() {
        return fech_inicio;
    }

    public void setFech_inicio(Date fech_inicio) {
        this.fech_inicio = fech_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public long getCosto() {
        return costo;
    }

    public void setCosto(long costo) {
        this.costo = costo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    private int titulo_plan;
    private String id_temporada, fech_creacion, fech_modificacion;
    private Date fech_inicio, fecha_fin;
    private long costo;
    private boolean estado;
}
