package com.example.pi.models;

public class compra {

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(String id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getFech_creacion() {
        return fech_creacion;
    }

    public void setFech_creacion(String fech_creacion) {
        this.fech_creacion = fech_creacion;
    }

    public long getTotal_planes() {
        return total_planes;
    }

    public void setTotal_planes(long total_planes) {
        this.total_planes = total_planes;
    }

    public long getTotal_otros_cargos() {
        return total_otros_cargos;
    }

    public void setTotal_otros_cargos(long total_otros_cargos) {
        this.total_otros_cargos = total_otros_cargos;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    private  String  id_cliente, id_vendedor, fech_creacion;
    private  int id_compra;
    private long total_planes, total_otros_cargos, total;
}
