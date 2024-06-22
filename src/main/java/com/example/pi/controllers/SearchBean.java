package com.example.pi.controllers;

import com.example.pi.models.Cliente;
import com.example.pi.models.Detalle_compra;
import com.example.pi.models.Vendedor;
import com.example.pi.repositories.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Named("beanSearch")
@RequestScoped
public class SearchBean implements Serializable {
    ClientesRepositoryImp clientesRepositoryImp;

    CompraRepositoryImpl compraRepository;
    private List<Cliente> clienteList;
    private List<Detalle_compra> detalleList;

    public List<Detalle_compra> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle_compra> detalleList) {
        this.detalleList = detalleList;
    }

    private String cedula1;
    private  int id_compra;

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public String getCedula1() {
        return cedula1;
    }

    public void setCedula1(String cedula1) {
        this.cedula1 = cedula1;
    }



    public void search_cliente() {
        clientesRepositoryImp = new ClientesRepositoryImp();
        clienteList=clientesRepositoryImp.consulta_compra(cedula1);

    }
    public void search_detalleCompra() {
        compraRepository = new CompraRepositoryImpl();
        detalleList=compraRepository.detalle_compra(id_compra);

    }
}
