package com.example.pi.models;

public class Vendedor {
    private String nombreVendedor, correoVendedor,  username, password, fecha_NacimientoVendedor, fecha_modificacionVendedor, fecha_creacionVendedor,
    cedula, telefono_1, telefono_2, rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getCorreoVendedor() {
        return correoVendedor;
    }

    public void setCorreoVendedor(String correoVendedor) {
        this.correoVendedor = correoVendedor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFecha_NacimientoVendedor() {
        return fecha_NacimientoVendedor;
    }

    public void setFecha_NacimientoVendedor(String fecha_NacimientoVendedor) {
        this.fecha_NacimientoVendedor = fecha_NacimientoVendedor;
    }

    public String getFecha_modificacionVendedor() {
        return fecha_modificacionVendedor;
    }

    public void setFecha_modificacionVendedor(String fecha_modificacionVendedor) {
        this.fecha_modificacionVendedor = fecha_modificacionVendedor;
    }

    public String getFecha_creacionVendedor() {
        return fecha_creacionVendedor;
    }

    public void setFecha_creacionVendedor(String fecha_creacionVendedor) {
        this.fecha_creacionVendedor = fecha_creacionVendedor;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono_1() {
        return telefono_1;
    }

    public void setTelefono_1(String telefono_1) {
        this.telefono_1 = telefono_1;
    }

    public String getTelefono_2() {
        return telefono_2;
    }

    public void setTelefono_2(String telefono_2) {
        this.telefono_2 = telefono_2;
    }

    public Vendedor(){}

}
