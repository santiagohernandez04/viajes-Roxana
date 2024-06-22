package com.example.pi.controllers;

import com.example.pi.repositories.ClientesRepositoryImp;
import com.example.pi.repositories.PlanTuristicoRepositoryImpl;
import com.example.pi.repositories.PuntosVisitaRepositoryImp;
import com.example.pi.repositories.VendedorRepositoryImp;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("beanDelete")
@RequestScoped
public class DeleteBean implements Serializable {
    VendedorRepositoryImp vendedorrepository;
    ClientesRepositoryImp clientesRepositoryImp;
    PlanTuristicoRepositoryImpl planTuristicoRepository;
    PuntosVisitaRepositoryImp puntosVisitaRepositoryImp;

    public void delete_vendedor(String cedula) {
        vendedorrepository = new VendedorRepositoryImp();
        vendedorrepository.deleteVendedor(cedula);
    }

    public void delete_plan(int id_plan) {
        planTuristicoRepository = new PlanTuristicoRepositoryImpl();
        planTuristicoRepository.delete_plan(id_plan);
    }
    public void delete_punto(int id_punto) {
        puntosVisitaRepositoryImp = new PuntosVisitaRepositoryImp();
        puntosVisitaRepositoryImp.delete_punto(id_punto);
    }
    public void delete_cliente(String cedula) {
        clientesRepositoryImp = new ClientesRepositoryImp();
        clientesRepositoryImp.deleteCliente(cedula);
    }
}
