package com.example.pi.models;

public class CarritoCompra {
    private  long costocompra, valor_plan, total;

    private int plan,valor_otro, valor_alimentacion, cantidadPersonas;
    private String cedula_vendedor, cedula_Comprador,nom_plan;

    public long getValor_plan() {
        return valor_plan;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getNom_plan() {
        return nom_plan;
    }

    public void setNom_plan(String nom_plan) {
        this.nom_plan = nom_plan;
    }

    public void setValor_plan(long valor_plan) {
        this.valor_plan = valor_plan;
    }

    public  long getCostocompra() {
        return costocompra;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public void setCostocompra(long costocompra) {
        this.costocompra = costocompra;
    }

    public int getValor_otro() {
        return valor_otro;
    }

    public void setValor_otro(int valor_otro) {
        this.valor_otro = valor_otro;
    }

    public int getValor_alimentacion() {
        return valor_alimentacion;
    }

    public void setValor_alimentacion(int valor_alimentacion) {
        this.valor_alimentacion = valor_alimentacion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getCedula_vendedor() {
        return cedula_vendedor;
    }

    public void setCedula_vendedor(String cedula_vendedor) {
        this.cedula_vendedor = cedula_vendedor;
    }

    public String getCedula_Comprador() {
        return cedula_Comprador;
    }

    public void setCedula_Comprador(String cedula_Comprador) {
        this.cedula_Comprador = cedula_Comprador;
    }
}
