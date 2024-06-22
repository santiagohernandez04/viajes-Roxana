package com.example.pi.controllers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
@Named("bean")
@SessionScoped
public class Bean implements Serializable {
    public String goToIndex(){
        if (LoginBean.tipolog) {
            return "Dashboard.faces";
        }else{
            return "Ventas.faces";
        }
    }

    public String goToClientes(){
        return "Clientes.faces";
    }
    public String goToReportes(){
        return "Reportes.faces";
    }
    public String goToVendedores(){
        return "Vendedor.faces";
    }
    public String goToPlanTuristico(){
        return "PlanesTuristicos.faces";
    }
    public String goToPuntoVisita(){
        return "PuntosVisita.faces";
    }

    public String goToCompras(){
        return "Compras.faces";
    }
    public String goToVenta(){return "Ventas.faces";
    }



}
