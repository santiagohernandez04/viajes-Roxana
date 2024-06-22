package com.example.pi.models;

import java.util.Date;

public class Cliente {
    private String cedula, nombre, correo, tel_pers_contac, quien_creo, nom_pers_contact,
            cedula_vendedor, telefono_1, telefono_2, fech_modificacion, fech_creacion, plan_comprado,fech_compra;

    public String getPlan_comprado() {
        return plan_comprado;
    }

    public void setPlan_comprado(String plan_comprado) {
        this.plan_comprado = plan_comprado;
    }

    public String getFech_compra() {
        return fech_compra;
    }

    public void setFech_compra(String fech_compra) {
        this.fech_compra = fech_compra;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTel_pers_contac() {
        return tel_pers_contac;
    }

    public void setTel_pers_contac(String tel_pers_contac) {
        this.tel_pers_contac = tel_pers_contac;
    }

    public String getQuien_creo() {
        return quien_creo;
    }

    public void setQuien_creo(String quien_creo) {
        this.quien_creo = quien_creo;
    }

    public String getNom_pers_contact() {
        return nom_pers_contact;
    }

    public void setNom_pers_contact(String nom_pers_contact) {
        this.nom_pers_contact = nom_pers_contact;
    }

    public String getCedula_vendedor() {
        return cedula_vendedor;
    }

    public void setCedula_vendedor(String cedula_vendedor) {
        this.cedula_vendedor = cedula_vendedor;
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

    public String getFech_modificacion() {
        return fech_modificacion;
    }

    public void setFech_modificacion(String fech_modificacion) {
        this.fech_modificacion = fech_modificacion;
    }

    public String getFech_creacion() {
        return fech_creacion;
    }

    public void setFech_creacion(String fech_creacion) {
        this.fech_creacion = fech_creacion;
    }

    public Date getFech_nacimiento() {
        return fech_nacimiento;
    }

    public void setFech_nacimiento(Date fech_nacimiento) {
        this.fech_nacimiento = fech_nacimiento;
    }

    private Date fech_nacimiento;

}
