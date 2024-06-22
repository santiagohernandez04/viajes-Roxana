package com.example.pi.repositories;

import com.example.pi.controllers.BeanJlist;
import com.example.pi.controllers.Insert_Update_Bean;
import com.example.pi.models.Plan_turistico;
import com.example.pi.models.Punto_visita;
import com.example.pi.models.Vendedor;
import com.example.pi.utils.ConnectionDatabase;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PuntosVisitaRepositoryImp {
    BeanJlist is=new BeanJlist();
    Insert_Update_Bean os=new Insert_Update_Bean();
    public List<Punto_visita> list(int id_Actividad) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechacreacionform=null;
        String fechaModificacionform=null;
        List<Punto_visita> puntoVisita = new ArrayList<>();
        if (id_Actividad==0) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM punto_visita where estado=true ORDER BY fecha_modificacion DESC");
                while (rs.next()) {
                    Punto_visita pv = new Punto_visita();
                    pv.setId_actividad(rs.getInt("id_actividad"));
                    pv.setNom_actividad(rs.getString("nom_actividad"));
                    pv.setDescripcion(rs.getString("descripcion"));
                    pv.setEstado(rs.getBoolean("estado"));
                    try {
                        java.util.Date fechcreacion = formatoEntrada.parse(rs.getString("fecha_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                        java.util.Date fechModificacion = formatoEntrada.parse(rs.getString("fecha_modificacion"));
                        fechaModificacionform = formatoSalida.format(fechModificacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pv.setFecha_creacion(fechacreacionform);
                    pv.setFecha_modificacion(fechaModificacionform);

                    puntoVisita.add(pv);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            String query="SELECT * FROM punto_visita where estado=true and id_actividad=?";
            try (Connection conn = ConnectionDatabase.getConnection()) {
                PreparedStatement st = conn.prepareStatement(query);
                st.setInt(1,id_Actividad);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Punto_visita pv = new Punto_visita();
                    pv.setId_actividad(rs.getInt("id_actividad"));
                    pv.setNom_actividad(rs.getString("nom_actividad"));
                    pv.setDescripcion(rs.getString("descripcion"));
                    pv.setEstado(rs.getBoolean("estado"));
                    try {
                        java.util.Date fechcreacion = formatoEntrada.parse(rs.getString("fecha_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                        java.util.Date fechModificacion = formatoEntrada.parse(rs.getString("fecha_modificacion"));
                        fechaModificacionform = formatoSalida.format(fechModificacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pv.setFecha_creacion(fechacreacionform);
                    pv.setFecha_modificacion(fechaModificacionform);
                    puntoVisita.add(pv);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return puntoVisita;
    }

    public List<Punto_visita> listInactivos(int busqueda) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechacreacionform=null;
        String fechaModificacionform=null;
        List<Punto_visita> puntoVisita = new ArrayList<>();
        if (busqueda==0) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM punto_visita where estado=false");
                while (rs.next()) {
                    Punto_visita pv = new Punto_visita();
                    pv.setId_actividad(rs.getInt("id_actividad"));
                    pv.setNom_actividad(rs.getString("nom_actividad"));
                    pv.setDescripcion(rs.getString("descripcion"));
                    pv.setEstado(rs.getBoolean("estado"));
                    try {
                        java.util.Date fechcreacion = formatoEntrada.parse(rs.getString("fecha_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                        java.util.Date fechModificacion = formatoEntrada.parse(rs.getString("fecha_modificacion"));
                        fechaModificacionform = formatoSalida.format(fechModificacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pv.setFecha_creacion(fechacreacionform);
                    pv.setFecha_modificacion(fechaModificacionform);

                    puntoVisita.add(pv);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
                String query="SELECT * FROM punto_visita where estado=false and id_actividad=?";
                try (Connection conn = ConnectionDatabase.getConnection()) {
                    PreparedStatement st = conn.prepareStatement(query);
                    st.setInt(1,busqueda);
                    ResultSet rs = st.executeQuery();
                    while (rs.next()) {
                        Punto_visita pv = new Punto_visita();
                        pv.setId_actividad(rs.getInt("id_actividad"));
                        pv.setNom_actividad(rs.getString("nom_actividad"));
                        pv.setDescripcion(rs.getString("descripcion"));
                        pv.setEstado(rs.getBoolean("estado"));
                        try {
                            java.util.Date fechcreacion = formatoEntrada.parse(rs.getString("fecha_creacion"));
                            fechacreacionform = formatoSalida.format(fechcreacion);
                            java.util.Date fechModificacion = formatoEntrada.parse(rs.getString("fecha_modificacion"));
                            fechaModificacionform = formatoSalida.format(fechModificacion);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        pv.setFecha_creacion(fechacreacionform);
                        pv.setFecha_modificacion(fechaModificacionform);
                        puntoVisita.add(pv);
                    }
                    st.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
            return puntoVisita;
        }
    public void ReactivarPunto(int id) {
        String query = "UPDATE punto_visita SET  estado = true WHERE id_actividad = ?";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void insertPuntoVisita(String nombre,String Descripcion,Boolean Estado,int departamento,int ciudad){
        String query = "INSERT INTO Punto_Visita (nom_Actividad, Descripcion, Estado, Fecha_Modificacion, Fecha_Creacion, id_Departamento, id_Ciudad) " +
                "VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?)";
            try (Connection conn = ConnectionDatabase.getConnection()) {
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, nombre);
                st.setString(2, Descripcion);
                st.setBoolean(3, Estado);
                st.setInt(4, departamento);
                st.setInt(5, ciudad);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    public void UpdatePuntoVisita(String nombre,String Descripcion,Boolean Estado,int departamento,int ciudad, int id){
        String query = "UPDATE punto_visita " +
                "SET nom_Actividad=?, " +
                "descripcion=?, " +
                "estado=?, " +
                "fecha_modificacion=CURRENT_TIMESTAMP, " +
                "id_departamento=?, " +
                "id_ciudad=? " +
                "WHERE id_actividad=?";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, nombre);
            st.setString(2, Descripcion);
            st.setBoolean(3, Estado);
            st.setInt(4, departamento);
            st.setInt(5, ciudad);
            st.setInt(6,id);
            st.executeUpdate();
            os.limpiar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete_punto(int id_punto) {
        String query = "UPDATE punto_visita set estado= false WHERE id_actividad = ? ";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_punto);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
