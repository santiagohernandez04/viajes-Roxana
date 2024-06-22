package com.example.pi.models;

public class Punto_visita {
    public int getCantidadcompras() {
        return cantidadcompras;
    }

    public void setCantidadcompras(int cantidadcompras) {
        this.cantidadcompras = cantidadcompras;
    }

    public int cantidadcompras;
    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNom_actividad() {
        return nom_actividad;
    }

    public void setNom_actividad(String nom_actividad) {
        this.nom_actividad = nom_actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    private int id_actividad, id_departamento, id_ciudad;
    private String nom_actividad, descripcion, fecha_modificacion,fecha_creacion;

    private boolean estado;
}
