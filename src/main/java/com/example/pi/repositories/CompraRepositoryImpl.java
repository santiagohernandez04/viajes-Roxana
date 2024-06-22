package com.example.pi.repositories;

import com.example.pi.controllers.BeanJlist;
import com.example.pi.controllers.LoginBean;
import com.example.pi.models.CarritoCompra;
import com.example.pi.models.Cliente;
import com.example.pi.models.Detalle_compra;
import com.example.pi.models.compra;
import com.example.pi.utils.ConnectionDatabase;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class CompraRepositoryImpl {
    public List<compra> list(int id_compra) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechacreacionform = null;
        List<compra> compras = new ArrayList<>();
        if (id_compra == 0) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM compra ORDER BY fech_creacion DESC");
                while (rs.next()) {
                    compra cl = new compra();
                    cl.setId_compra(rs.getInt("id_compra"));
                    cl.setId_cliente(rs.getString("id_cliente"));
                    cl.setId_vendedor(rs.getString("id_usuario"));
                    try {
                        Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    cl.setFech_creacion(fechacreacionform);
                    cl.setTotal_otros_cargos(rs.getInt("total_otros_cargos"));
                    cl.setTotal_planes(rs.getInt("total_planes"));
                    cl.setTotal(rs.getLong("total"));
                    compras.add(cl);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "SELECT * FROM compra where id_compra=?";
            try (Connection conn = ConnectionDatabase.getConnection()) {
                PreparedStatement st = conn.prepareStatement(query);
                st.setInt(1, id_compra);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    compra cl = new compra();
                    cl.setId_compra(rs.getInt("id_compra"));
                    cl.setId_cliente(rs.getString("id_cliente"));
                    cl.setId_vendedor(rs.getString("id_usuario"));
                    try {
                        Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    cl.setFech_creacion(fechacreacionform);
                    cl.setTotal_otros_cargos(rs.getInt("total_otros_cargos"));
                    cl.setTotal_planes(rs.getInt("total_planes"));
                    cl.setTotal(rs.getLong("total"));
                    compras.add(cl);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return compras;
    }

    public List<Detalle_compra> detalle_compra(int id_compra) {
        List<Detalle_compra> Detalle_compra = new ArrayList<>();
        String query = "select * from detalle_compra d join plan_turistico P  on d.id_plan=p.id_planturistico where d.id_compra=?";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_compra);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Detalle_compra DC = new Detalle_compra();
                DC.setId_detallecompra(rs.getInt("id_detallecompra"));
                DC.setNombre_plan(rs.getString("titulo"));
                DC.setValor_plan(rs.getInt("valor_plan"));
                DC.setValor_alimentacionplan(rs.getInt("valor_alimentacionplan"));
                DC.setTotal_detalle(rs.getLong("total_Detalle"));
                DC.setCant_personas(rs.getInt("cantidad_personas"));
                Detalle_compra.add(DC);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Detalle_compra;
    }

    public void NewCompra(List<CarritoCompra> carritoCompras, String cedula_comprador) {
        if (cedula_comprador != null) {
            String query = "Insert into compra (id_cliente,id_usuario,fech_creacion, total_planes," +
                    "total_otros_cargos,total)" +
                    "Values (?,?,current_timestamp,?,?,?) returning id_compra";
            String query1 = "Insert into detalle_compra (id_compra,id_plan,valor_plan,valor_alimentacionplan,total_detalle,cantidad_personas)" +
                    "values (?,?,?,?,?,?)";
            int idgenerado = 0;
            long total_planes = 0;
            for (int i = 0; i < carritoCompras.size(); i++) {
                total_planes = total_planes + carritoCompras.get(i).getValor_plan();
            }
            int total_otros_cargos = 0;
            for (int i = 0; i < carritoCompras.size(); i++) {
                total_otros_cargos = total_otros_cargos + carritoCompras.get(i).getValor_otro();
            }
            int cant_personas = 0;
            for (int i = 0; i < carritoCompras.size(); i++) {
                cant_personas = cant_personas + carritoCompras.get(i).getCantidadPersonas();
            }
            try (Connection conn = ConnectionDatabase.getConnection()) {
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, cedula_comprador);
                st.setString(2, LoginBean.cedula_vendedor);
                st.setLong(3, total_planes);
                st.setInt(4, total_otros_cargos);
                st.setInt(5, (int) carritoCompras.get(carritoCompras.size() - 1).getTotal());
                try (ResultSet id = st.executeQuery()) {
                    if (id.next()) {
                        idgenerado = id.getInt("id_compra");
                    }
                }
                for (int i = 0; i < carritoCompras.size(); i++) {
                    PreparedStatement stmt = conn.prepareStatement(query1);
                    stmt.setInt(1, idgenerado);
                    stmt.setInt(2, carritoCompras.get(i).getPlan());
                    stmt.setLong(3, carritoCompras.get(i).getValor_plan());
                    stmt.setInt(4, carritoCompras.get(i).getValor_alimentacion());
                    stmt.setInt(5, (int) ((carritoCompras.get(i).getValor_plan() + carritoCompras.get(i).getValor_alimentacion()) * cant_personas));
                    stmt.setInt(6, cant_personas);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Seleccione un cliente antes de realizar el pago"));
        }
    }
}
