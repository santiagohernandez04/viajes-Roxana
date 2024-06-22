package com.example.pi.controllers;

import com.example.pi.models.*;
import com.example.pi.repositories.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("beanLista")
@RequestScoped
public class Tablas implements Serializable {
    private List<Vendedor> vendedores;
    private List<Cliente> cliente;
    private List<Plan_turistico> plan;
    private List<Punto_visita> puntoVisita;
    private List<Vendedor> vendedoresInactivos;
    private List<Cliente> clienteInactivos;
    private List<Plan_turistico> planInactivos;
    private List<Punto_visita> puntoVisitaInactivos;
    private List<Tarifa> tarifa;
    private List<compra> compra;
    private int listar = 0;
    String lista ;

    public List<Vendedor> getVendedoresInactivos() {
        return vendedoresInactivos;
    }

    public void setVendedoresInactivos(List<Vendedor> vendedoresInactivos) {
        this.vendedoresInactivos = vendedoresInactivos;
    }

    public List<Cliente> getClienteInactivos() {
        return clienteInactivos;
    }

    public void setClienteInactivos(List<Cliente> clienteInactivos) {
        this.clienteInactivos = clienteInactivos;
    }

    public List<Plan_turistico> getPlanInactivos() {
        return planInactivos;
    }

    public void setPlanInactivos(List<Plan_turistico> planInactivos) {
        this.planInactivos = planInactivos;
    }

    public List<Punto_visita> getPuntoVisitaInactivos() {
        return puntoVisitaInactivos;
    }

    public void setPuntoVisitaInactivos(List<Punto_visita> puntoVisitaInactivos) {
        this.puntoVisitaInactivos = puntoVisitaInactivos;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }
    public int getListar() {
        return listar;
    }

    public void setListar(int listar) {
        this.listar = listar;
    }

    public void setPlan(List<Plan_turistico> plan) {
        this.plan = plan;
    }

    public List<Punto_visita> getPuntoVisita() {
        return puntoVisita;
    }

    public void setPuntoVisita(List<Punto_visita> puntoVisita) {
        this.puntoVisita = puntoVisita;
    }

    public void setTarifa(List<Tarifa> tarifa) {
        this.tarifa = tarifa;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public List<com.example.pi.models.compra> getCompra() {
        return compra;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }
    public List<Plan_turistico> getPlan() {
        return plan;
    }

    public List<Tarifa> getTarifa() {
        return tarifa;
    }

    public void setCompra(List<com.example.pi.models.compra> compra) {
        this.compra = compra;
    }

    public Tablas() {
        Vendedores();
        Compras();
        Plan();
        Cliente();
        PuntoV();
        VendedoresInactivos();
        PlanInactivos();
        ClientesInactivos();
        PuntoVInactivos();
    }
    public void Vendedores() {
        if(lista==null){
            lista="0";
        }
        VendedorRepositoryImp repository = new VendedorRepositoryImp();
        vendedores = repository.list(lista);
    }



    public void Compras() {
        if (lista == null) {
            lista = "0";
        }
        listar = Integer.parseInt(lista);
        CompraRepositoryImpl repository = new CompraRepositoryImpl();
        compra = repository.list(listar);
    }

    public void Cliente() {
        if (lista == null) {
            lista = "0";
        }
        ClientesRepositoryImp repository = new ClientesRepositoryImp();
        cliente = repository.list(lista);
    }

    public void Plan() {
        if (lista == null) {
            lista = "0";
        }
        listar = Integer.parseInt(lista);
        PlanTuristicoRepositoryImpl repository = new PlanTuristicoRepositoryImpl();
        plan = repository.list(listar);

    }

    public void PuntoV() {
        if (lista == null) {
            lista = "0";
        }
        listar = Integer.parseInt(lista);
        PuntosVisitaRepositoryImp repository = new PuntosVisitaRepositoryImp();
        puntoVisita = repository.list(listar);

    }
    public void VendedoresInactivos() {
        if(lista==null){
            lista="0";
        }
        VendedorRepositoryImp repository = new VendedorRepositoryImp();
        vendedoresInactivos = repository.listInactivos(lista);
    }
    public void ClientesInactivos() {
        if(lista==null){
            lista="0";
        }
        ClientesRepositoryImp repository = new ClientesRepositoryImp();
        clienteInactivos = repository.listInactivos(lista);
    }

    public void PlanInactivos() {
        if (lista == null) {
            lista = "0";
        }
        listar = Integer.parseInt(lista);
        PlanTuristicoRepositoryImpl repository = new PlanTuristicoRepositoryImpl();
        planInactivos = repository.listInactivos(listar);

    }

    public void PuntoVInactivos() {
        if (lista == null) {
            lista = "0";
        }
        listar = Integer.parseInt(lista);
        PuntosVisitaRepositoryImp repository = new PuntosVisitaRepositoryImp();
        puntoVisitaInactivos = repository.listInactivos(listar);

    }




}
