package com.example.pi.controllers;

import com.example.pi.models.Vendedor;
import com.example.pi.repositories.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Named("beanInsert_Update")
@SessionScoped
public class Insert_Update_Bean implements Serializable {
    VendedorRepositoryImp vendedorrepository;
    ClientesRepositoryImp clientesRepositoryImp;
    PuntosVisitaRepositoryImp puntosVisitaRepositoryImp;
    String cedula;
    String nombre;
    String correo;
    String username;
    String password;
    String telefono_1;
    String telefono_2;

    String cedula_vendedor, persona_contacto, tel_persona_contacto;

    public String getCedula_vendedor() {
        return cedula_vendedor;
    }

    public void setCedula_vendedor(String cedula_vendedor) {
        this.cedula_vendedor = cedula_vendedor;
    }

    public String getPersona_contacto() {
        return persona_contacto;
    }

    public void setPersona_contacto(String persona_contacto) {
        this.persona_contacto = persona_contacto;
    }

    public String getTel_persona_contacto() {
        return tel_persona_contacto;
    }

    public void setTel_persona_contacto(String tel_persona_contacto) {
        this.tel_persona_contacto = tel_persona_contacto;
    }
    public void reactivarplan(int id){
        PlanTuristicoRepositoryImpl pt=new PlanTuristicoRepositoryImpl();
        pt.ReactivarPlan(id);
    }
    public void reactivarpunto(int id){
        PuntosVisitaRepositoryImp pv=new PuntosVisitaRepositoryImp();
        pv.ReactivarPunto(id);
    }
    public void reactivarCedulaCliente(String cedula){
        ClientesRepositoryImp cr=new ClientesRepositoryImp();
        cr.ReactivarCliente(cedula);
    }
    public void reactivarCedulaVendedor(String cedula){
        VendedorRepositoryImp vr= new VendedorRepositoryImp();
        vr.ReactivarVendedor(cedula);
    }
    Date fecha_nacimiento;
    Boolean activo;
    public static String insert_or_update = "Registrar";
    public static String nuevo;
    private Boolean editable = true;


    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void insert_o_updat(String id) {
        insert_or_update = id;
        cargarVendedor(id);

    }
    public void cargarVendedor(String cedula) {
        VendedorRepositoryImp vendedorrepository = new VendedorRepositoryImp();
        Optional<Vendedor> vendedorOpt = vendedorrepository.buscar(cedula);

        if (vendedorOpt.isPresent()) {
            Vendedor vendedor = vendedorOpt.get();
            this.cedula = vendedor.getCedula();
            this.nombre = vendedor.getNombreVendedor();
            this.correo = vendedor.getCorreoVendedor();
            this.username = vendedor.getUsername();
            this.password = vendedor.getPassword();
            this.telefono_1 = vendedor.getTelefono_1();
            this.telefono_2 = vendedor.getTelefono_2();
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.fecha_nacimiento = formatoEntrada.parse(vendedor.getFecha_NacimientoVendedor());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.activo = true;  // Assuming the vendedor is active based on your logic.
        }
    }

    public void nuev(String accion) {
        nuevo = accion;
    }

    public void Insert_Update_vendedor() {
        vendedorrepository = new VendedorRepositoryImp();
        if (Objects.equals(nuevo, "Registrar")) {
            vendedorrepository.NewVendedor(cedula, nombre, correo, username, password, fecha_nacimiento, telefono_1, telefono_2, activo);
        } else {
            vendedorrepository.UpdateVendedor(insert_or_update, nombre, correo, username, password, fecha_nacimiento, telefono_1, telefono_2, activo);
        }
        limpiar();
    }


    public void limpiar() {
        cedula = null;
        nombre = null;
        correo = null;
        telefono_1 = null;
        telefono_2 = null;
        fecha_nacimiento = null; // O la fecha que desees inicializar
        username = null;
        password = null;
        activo = false; // Según sea el valor por defecto
        persona_contacto=null;
        tel_persona_contacto=null;
    }

    public void Insert_Update_punto(String nombrePunto, String descripcionPunto, Boolean Estado, int ciudad, int departamento, int id) {
        puntosVisitaRepositoryImp = new PuntosVisitaRepositoryImp();
        if (Objects.equals(nuevo, "Registrar")) {
            puntosVisitaRepositoryImp.insertPuntoVisita(nombrePunto, descripcionPunto, Estado, departamento, ciudad);
        } else {
            puntosVisitaRepositoryImp.UpdatePuntoVisita(nombrePunto, descripcionPunto, Estado, departamento, ciudad, id);

        }
        limpiar();
    }

    public void Insert_Update_cliente() {
        Tablas bf=new Tablas();
        clientesRepositoryImp = new ClientesRepositoryImp();
        cedula_vendedor = LoginBean.cedula_vendedor;
        if (Objects.equals(nuevo, "Registrar")) {
            clientesRepositoryImp.NewCliente(cedula, nombre, correo, fecha_nacimiento, cedula_vendedor, persona_contacto, tel_persona_contacto, telefono_1, telefono_2, activo);
        } else {
            clientesRepositoryImp.UpdateCliente(insert_or_update, nombre, correo, persona_contacto, tel_persona_contacto, fecha_nacimiento, telefono_1, telefono_2, activo);
        }
        limpiar();
        bf.Cliente();

    }
}
