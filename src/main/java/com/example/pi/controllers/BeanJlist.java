package com.example.pi.controllers;

import com.example.pi.models.*;
import com.example.pi.repositories.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Named("beanJlist")
@SessionScoped
public class BeanJlist implements Serializable {
    private List<Departamento> departamentos;
    private List<Ciudades> ciudades;
    private int selectedDepartamentoId = 0;
    private int selectedCiudadId = 0;
    private String nombre, descrip;
    private Boolean Estado;
    private List<CarritoCompra> carritoCompras = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        this.Estado = estado;
    }

    private List<Plan_turistico> planesTuristicos;
    private List<Tarifa> temporadas;
    private int id;

    public List<Tarifa> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Tarifa> temporadas) {
        this.temporadas = temporadas;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    private int costo;
    private int selectedPlanTuristicoId;

    public List<Punto_visita> getPuntoVisita() {
        return puntoVisita;
    }

    public void setPuntoVisita(List<Punto_visita> puntoVisita) {
        this.puntoVisita = puntoVisita;
    }

    public List<Punto_visita> getSelectedPuntoVisita() {
        return selectedPuntoVisita;
    }

    public void setSelectedPuntoVisita(List<Punto_visita> selectedPuntoVisita) {
        this.selectedPuntoVisita = selectedPuntoVisita;
    }

    private List<Punto_visita> selectedPuntoVisita;
    private List<Punto_visita> puntoVisita;

    public List<Punto_visita> cargarPuntovisita() {
        PlanTuristicoRepositoryImpl pt = new PlanTuristicoRepositoryImpl();
        mj = pt.listPunto(0);
        return mj;
    }

    private List<Punto_visita> mj;

    public List<Punto_visita> getMj() {
        return mj;
    }

    public void setMj(List<Punto_visita> mj) {
        this.mj = mj;
    }

    public BeanJlist() {
        cargarPuntovisita();
        departamentos = new DepartamentosRepositoryImp().list();
        cargarPlanesTuristicos();
        temporadas = new ArrayList<>();
        temporadas.add(new Tarifa());
        actualizarTemporadas();
        iniciaractualizarTemporadas();
    }

    public void cargarCiudades() {
        ciudades = new CiudadesRepositoryImp().listByDepartamento(selectedDepartamentoId);
    }


    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public List<Ciudades> getCiudades() {
        return ciudades;
    }

    public int getSelectedDepartamentoId() {
        return selectedDepartamentoId;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    private String temporada;
    public static String temporada_tarifa;

    public void enviarValoresPuntovisitas() {
        Insert_Update_Bean is = new Insert_Update_Bean();

        is.Insert_Update_punto(nombre, descrip, Estado, selectedCiudadId, getSelectedDepartamentoId(), id);
        resetValores();
    }
    /*
    public void cargarPuntosVisitaID(){
        PuntosVisitaRepositoryImp repo = new PuntosVisitaRepositoryImp();
        Punto_visita punto = repo.(this.selectedPlanTuristicoId);
        if (punto != null) {
            this.nombre = punto.getNom_actividad();

        }
    }
    */

    public void setSelectedDepartamentoId(int selectedDepartamentoId) {
        this.selectedDepartamentoId = selectedDepartamentoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Date Fecha_inicio;

    public Date getFecha_fin() {
        return Fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        Fecha_fin = fecha_fin;
    }

    private Date Fecha_fin;

    public Date getFecha_inicio() {
        return Fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        Fecha_inicio = fecha_inicio;
    }

    public void Ids(int ids) {
        id = ids;
        //cargarPuntosVisitaID();
    }

    public int getSelectedCiudadId() {
        return selectedCiudadId;
    }

    public void setSelectedCiudadId(int selectedCiudadId) {
        this.selectedCiudadId = selectedCiudadId;
    }

    public void cargarPlanesTuristicos() {
        planesTuristicos = new PlanTuristicoRepositoryImpl().list(0);
    }

    public List<Plan_turistico> getPlanesTuristicos() {
        return planesTuristicos;
    }

    public int getSelectedPlanTuristicoId() {
        return selectedPlanTuristicoId;
    }

    public void setSelectedPlanTuristicoId(int selectedPlanTuristicoId) {
        this.selectedPlanTuristicoId = selectedPlanTuristicoId;
    }




    public String getSelectedTemporadaId() {
        return selectedTemporadaId;
    }

    public void setSelectedTemporadaId(String selectedTemporadaId) {
        this.selectedTemporadaId = selectedTemporadaId;
    }

    private String selectedTemporadaId;


    public void actualizarTemporadas() {
        PlanTuristicoRepositoryImpl planRepository = new PlanTuristicoRepositoryImpl();
        Plan_turistico planSeleccionado = planRepository.obtenerPlanPorId(selectedPlanTuristicoId);
        TarifaRepositoryImp tarifaRepository = new TarifaRepositoryImp();
        List<Tarifa> tarifas = tarifaRepository.obtenerTarifasPorPlan(selectedPlanTuristicoId);
        setTemporadas(tarifas);
    }

    public void iniciaractualizarTemporadas() {
        PlanTuristicoRepositoryImpl planRepository = new PlanTuristicoRepositoryImpl();
        Plan_turistico planSeleccionado = planRepository.obtenerPlanPorId(2);
        TarifaRepositoryImp tarifaRepository = new TarifaRepositoryImp();
        List<Tarifa> tarifas = tarifaRepository.obtenerTarifasPorPlan(2);
        setTemporadas(tarifas);
    }

    public List<String> getNombresPuntosVisita() {
        return nombresPuntosVisita;
    }

    public void setNombresPuntosVisita(List<String> nombresPuntosVisita) {
        this.nombresPuntosVisita = nombresPuntosVisita;
    }

    private List<String> nombresPuntosVisita;

    public void actualizarPuntosVisita() {
        TarifaRepositoryImp tarifaRepositoryImp = new TarifaRepositoryImp();
        nombresPuntosVisita = tarifaRepositoryImp.obtenerNombresPuntosPorPlan(selectedPlanTuristicoId);
    }

    public List<String> obtenerNombresPuntosPorPlan() {
        return new TarifaRepositoryImp().obtenerNombresPuntosPorPlan(selectedPlanTuristicoId);
    }

    public static long costocompra;
    private int valor_otro, valor_alimentacion, cantidadPersonas;
    private String cedula_vendedor;
    private String cedula_Comprador;

    public String getCedula_Comprador() {
        return cedula_Comprador;
    }

    public void setCedula_Comprador(String cedula_Comprador) {
        this.cedula_Comprador = cedula_Comprador;
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

    private long total_compra = 0;

    public long getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(long total_compra) {
        this.total_compra = total_compra;
    }



    public void enviarValoresCompra() {
        if (alimentacion){
            valor_alimentacion=150000;
        }else{
            valor_alimentacion=0;
        }
        TarifaRepositoryImp tarifaRepositoryImp = new TarifaRepositoryImp();
        PlanTuristicoRepositoryImpl pt = new PlanTuristicoRepositoryImpl();
        Plan_turistico pl = pt.obtenerPlanPorId(selectedPlanTuristicoId);
        costocompra = tarifaRepositoryImp.costoPlan(selectedTemporadaId, selectedPlanTuristicoId);
        CarritoCompra carro = new CarritoCompra();
        carro.setCedula_Comprador(cedula_Comprador);
        carro.setCedula_vendedor(LoginBean.cedula_vendedor);
        carro.setValor_plan(costocompra);
        carro.setNom_plan(pl.getTitulo());
        carro.setCostocompra((costocompra + valor_alimentacion + valor_otro) * cantidadPersonas);
        carro.setValor_otro(valor_otro);
        carro.setCantidadPersonas(cantidadPersonas);
        carro.setPlan(selectedPlanTuristicoId);
        carro.setValor_alimentacion(valor_alimentacion);
        total_compra = total_compra + carro.getCostocompra();
        carro.setTotal(total_compra);
        carritoCompras.add(carro);
        resetValores();

    }

    public void setidModificar(int idplan) {
        selectedPlanTuristicoId=idplan;
        cargarPlanTuristico();
    }
    private void cargarPlanTuristico() {
        PlanTuristicoRepositoryImpl repo = new PlanTuristicoRepositoryImpl();
        Plan_turistico plan = repo.obtenerPlanPorId(this.selectedPlanTuristicoId);
        if (plan != null) {
            this.titulo = plan.getTitulo();
            this.descripcion = plan.getDescripcion();
            this.cant_dias = plan.getCant_dias();
            this.url = plan.getUrl();
            this.alimentacion = plan.isAlimentacion();
            this.plan_Activo = plan.isEstado();

        }
    }

    public void pagarCarrito() {
        CompraRepositoryImpl cm = new CompraRepositoryImpl();
        cm.NewCompra(carritoCompras, cedula_Comprador);
        resetVenta();
    }

    public void setcedula(String ced, String nom) {
        cedula_Comprador = ced;
        nombre = nom;
    }

    public List<CarritoCompra> getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(List<CarritoCompra> carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    private String titulo, descripcion, url;
    private boolean alimentacion, plan_Activo, tarAlta, tarMedia, tarBaja;
    private int costoAlta, costoMedia, costoBaja, cant_dias;
    private Date inicioAlta, inicioMedia, inicioBaja, finAlta, finMedia, finBaja;

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCant_dias() {
        return cant_dias;
    }

    public void setCant_dias(int cant_dias) {
        this.cant_dias = cant_dias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(boolean alimentacion) {
        this.alimentacion = alimentacion;
    }

    public boolean isPlan_Activo() {
        return plan_Activo;
    }

    public void setPlan_Activo(boolean plan_Activo) {
        this.plan_Activo = plan_Activo;
    }

    public boolean isTarAlta() {
        return tarAlta;
    }

    public void setTarAlta(boolean tarAlta) {
        this.tarAlta = tarAlta;
    }

    public boolean isTarMedia() {
        return tarMedia;
    }

    public void setTarMedia(boolean tarMedia) {
        this.tarMedia = tarMedia;
    }

    public boolean isTarBaja() {
        return tarBaja;
    }

    public void setTarBaja(boolean tarBaja) {
        this.tarBaja = tarBaja;
    }

    public int getCostoAlta() {
        return costoAlta;
    }

    public void setCostoAlta(int costoAlta) {
        this.costoAlta = costoAlta;
    }

    public int getCostoMedia() {
        return costoMedia;
    }

    public void setCostoMedia(int costoMedia) {
        this.costoMedia = costoMedia;
    }

    public int getCostoBaja() {
        return costoBaja;
    }

    public void setCostoBaja(int costoBaja) {
        this.costoBaja = costoBaja;
    }

    public Date getInicioAlta() {
        return inicioAlta;
    }

    public void setInicioAlta(Date inicioAlta) {
        this.inicioAlta = inicioAlta;
    }

    public Date getInicioMedia() {
        return inicioMedia;
    }

    public void setInicioMedia(Date inicioMedia) {
        this.inicioMedia = inicioMedia;
    }

    public Date getInicioBaja() {
        return inicioBaja;
    }

    public void setInicioBaja(Date inicioBaja) {
        this.inicioBaja = inicioBaja;
    }

    public Date getFinAlta() {
        return finAlta;
    }

    public void setFinAlta(Date finAlta) {
        this.finAlta = finAlta;
    }

    public Date getFinMedia() {
        return finMedia;
    }

    public void setFinMedia(Date finMedia) {
        this.finMedia = finMedia;
    }

    public Date getFinBaja() {
        return finBaja;
    }

    public void setFinBaja(Date finBaja) {
        this.finBaja = finBaja;
    }

    private List<Integer> mm;

    public List<Integer> getMm() {
        return mm;
    }

    public void setMm(List<Integer> mm) {
        this.mm = mm;
    }

    public void enviarValoresPlanturistico() {
        PlanTuristicoRepositoryImpl pt = new PlanTuristicoRepositoryImpl();
        pt.Insert_plan_turistico(titulo, descripcion, cant_dias, plan_Activo, alimentacion, url, mm, inicioAlta, inicioMedia, inicioBaja, finAlta, finMedia, finBaja, costoAlta, costoMedia, costoBaja, tarAlta, tarMedia, tarBaja);
        cargarPlanesTuristicos();
        cargarPlanTuristico();
        actualizarTemporadas();
        resetValores();
    }

    public void editar_plan(){
            PlanTuristicoRepositoryImpl pt = new PlanTuristicoRepositoryImpl();
            pt.Update_plan_turistico(selectedPlanTuristicoId,titulo, descripcion, cant_dias, plan_Activo, alimentacion, url, mm, inicioAlta, inicioMedia, inicioBaja, finAlta, finMedia, finBaja, costoAlta, costoMedia, costoBaja, tarAlta, tarMedia, tarBaja);
            cargarPlanesTuristicos();
            cargarPlanTuristico();
            actualizarTemporadas();
            resetValores();

    }



    public void ee(int x) {
        setSelectedPlanTuristicoId(x);
    }

    public void resetValores() {
        titulo = null;
        url = null;
        descripcion = null;
        cant_dias = 1;
        plan_Activo = alimentacion = false;
        inicioAlta = inicioMedia = inicioBaja = finAlta = finMedia = finBaja = null;
        costoAlta = costoMedia = costoBaja = 0;
        tarAlta = tarMedia = tarBaja = false;
        cedula_vendedor = null;
        costocompra = valor_otro = cantidadPersonas = valor_alimentacion = 0;
        temporada = null;
        Fecha_fin = Fecha_inicio = null;
        costo = 0;
        Estado = false;
        descrip = null;

    }

    public void resetVenta() {
        carritoCompras.clear();
        cedula_Comprador = null;
        nombre = null;
        total_compra = 0;
    }
}


