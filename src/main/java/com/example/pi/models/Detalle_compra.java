package com.example.pi.models;

public class Detalle_compra {
    public int getCant_personas() {
        return cant_personas;
    }

    public void setCant_personas(int cant_personas) {
        this.cant_personas = cant_personas;
    }

    public int getId_detallecompra() {
        return id_detallecompra;
    }

    public void setId_detallecompra(int id_detallecompra) {
        this.id_detallecompra = id_detallecompra;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public long getValor_plan() {
        return valor_plan;
    }

    public void setValor_plan(long valor_plan) {
        this.valor_plan = valor_plan;
    }

    public long getValor_alimentacionplan() {
        return valor_alimentacionplan;
    }

    public void setValor_alimentacionplan(long valor_alimentacionplan) {
        this.valor_alimentacionplan = valor_alimentacionplan;
    }

    public long getTotal_detalle() {
        return total_detalle;
    }

    public String getNombre_plan() {
        return nombre_plan;
    }

    public void setNombre_plan(String nombre_plan) {
        this.nombre_plan = nombre_plan;
    }

    public void setTotal_detalle(long total_detalle) {
        this.total_detalle = total_detalle;
    }
    private  String nombre_plan;
    private int id_detallecompra, id_compra, id_plan,cant_personas;
    private long valor_plan, valor_alimentacionplan, total_detalle;
}
