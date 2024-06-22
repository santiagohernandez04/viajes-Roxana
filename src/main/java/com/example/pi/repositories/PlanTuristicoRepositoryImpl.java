package com.example.pi.repositories;

import com.example.pi.models.Plan_turistico;
import com.example.pi.models.Punto_visita;
import com.example.pi.utils.ConnectionDatabase;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlanTuristicoRepositoryImpl {
    public List<Plan_turistico> list(int id_plan) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechacreacionform = null;
        List<Plan_turistico> Plan = new ArrayList<>();
        if (id_plan == 0) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM plan_turistico where estado=true ORDER BY fech_modificacion DESC");
                while (rs.next()) {
                    Plan_turistico pt = new Plan_turistico();
                    pt.setId_planturistico(rs.getInt("id_planturistico"));
                    pt.setTitulo(rs.getString("titulo"));
                    pt.setDescripcion(rs.getString("descripcion"));
                    pt.setCant_dias(rs.getInt("cant_dias"));
                    try {
                        java.util.Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pt.setFech_creacion(fechacreacionform);
                    pt.setEstado(rs.getBoolean("estado"));
                    pt.setUrl(rs.getString("imagen"));
                    Plan.add(pt);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "SELECT * FROM plan_turistico where estado=true and id_planturistico=?";
            try (Connection conn = ConnectionDatabase.getConnection()) {
                PreparedStatement st = conn.prepareStatement(query);
                st.setInt(1, id_plan);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Plan_turistico pt = new Plan_turistico();
                    pt.setId_planturistico(rs.getInt("id_planturistico"));
                    pt.setTitulo(rs.getString("titulo"));
                    pt.setDescripcion(rs.getString("descripcion"));
                    pt.setCant_dias(rs.getInt("cant_dias"));
                    try {
                        java.util.Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pt.setFech_creacion(fechacreacionform);
                    pt.setEstado(rs.getBoolean("estado"));
                    pt.setUrl(rs.getString("imagen"));
                    Plan.add(pt);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Plan;
    }

    public List<Plan_turistico> listInactivos(int busqueda) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechacreacionform = null;
        List<Plan_turistico> Plan = new ArrayList<>();
        if (busqueda == 0) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM plan_turistico where estado=false");
                while (rs.next()) {
                    Plan_turistico pt = new Plan_turistico();
                    pt.setId_planturistico(rs.getInt("id_planturistico"));
                    pt.setTitulo(rs.getString("titulo"));
                    pt.setDescripcion(rs.getString("descripcion"));
                    pt.setCant_dias(rs.getInt("cant_dias"));
                    try {
                        java.util.Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pt.setFech_creacion(fechacreacionform);
                    pt.setEstado(rs.getBoolean("estado"));
                    pt.setUrl(rs.getString("imagen"));
                    Plan.add(pt);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "SELECT * FROM plan_turistico where estado=false and id_planturistico=?";
            try (Connection conn = ConnectionDatabase.getConnection()) {
                PreparedStatement st = conn.prepareStatement(query);
                st.setInt(1, busqueda);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Plan_turistico pt = new Plan_turistico();
                    pt.setId_planturistico(rs.getInt("id_planturistico"));
                    pt.setTitulo(rs.getString("titulo"));
                    pt.setDescripcion(rs.getString("descripcion"));
                    pt.setCant_dias(rs.getInt("cant_dias"));
                    try {
                        java.util.Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pt.setFech_creacion(fechacreacionform);
                    pt.setEstado(rs.getBoolean("estado"));
                    pt.setUrl(rs.getString("imagen"));
                    Plan.add(pt);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Plan;
    }

    public void ReactivarPlan(int id) {
        String query = "UPDATE plan_turistico SET  estado = true WHERE id_planturistico = ?";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Punto_visita> listPunto(int id_plan) {

        List<Punto_visita> Plan = new ArrayList<>();
        if (id_plan == 0) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM punto_visita where estado=true");
                while (rs.next()) {
                    Punto_visita pv = new Punto_visita();
                    pv.setNom_actividad(rs.getString("nom_actividad"));
                    pv.setId_actividad(rs.getInt("id_Actividad"));
                    Plan.add(pv);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Plan;
    }

    public void delete_plan(int id_plan) {
        String query = "UPDATE plan_turistico set estado= false WHERE id_planturistico = ? ";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_plan);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Plan_turistico obtenerPlanPorId(int id) {
        String query = "SELECT * FROM plan_turistico WHERE id_planturistico = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Plan_turistico plan = new Plan_turistico();
                plan.setId_planturistico(rs.getInt("id_planturistico"));
                plan.setTitulo(rs.getString("titulo"));
                plan.setDescripcion(rs.getString("descripcion"));
                plan.setCant_dias(rs.getInt("cant_dias"));
                plan.setUrl(rs.getString("imagen"));
                plan.setAlimentacion(rs.getBoolean("alimentación"));
                plan.setEstado(rs.getBoolean("estado"));



                return plan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Insert_plan_turistico(String titulo, String descripcion, int cant_dias, boolean estado, boolean Alimentacion, String url, List<Integer> ids, java.util.Date inicioAlta, java.util.Date inicioMedia, java.util.Date inicioBaja, java.util.Date finAlta, java.util.Date finMedia, java.util.Date finBaja, int costoAlta, int costoMedia, int costoBaja, boolean tarAlta, boolean tarMedia, boolean tarBaja) {
        int idPlangenerado = 0;
        String query = "INSERT INTO Plan_Turistico (titulo, Descripcion, Fech_Creacion, Fech_modificacion, Cant_dias, Estado, Alimentación,imagen) VALUES\n" +
                "(?, ?, current_timestamp, current_timestamp , ?, ?, ?,?) returning id_planturistico;\n";
        String query1 = "Insert into plan_punto (id_plan,id_actividad) Values (?,?)";
        String query2 = "INSERT INTO Tarifa (Titulo_Plan, id_Temporada, Fech_Creacion, Fech_inicio, Fech_Modificacion, Fecha_Fin, Costo, Estado)" +
                " VALUES (?, ?, current_timestamp, ?, current_timestamp, ?, ?, ?);\n";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, titulo);
            st.setString(2, descripcion);
            st.setInt(3, cant_dias);
            st.setBoolean(4, estado);
            st.setBoolean(5, Alimentacion);
            st.setString(6, url);
            try (ResultSet id = st.executeQuery()) {
                if (id.next()) {
                    idPlangenerado = id.getInt("id_planturistico");
                }
            }
            for (int i = 0; i < ids.size(); i++) {
                PreparedStatement st1 = conn.prepareStatement(query1);
                st1.setInt(1, idPlangenerado);
                st1.setInt(2, ids.get(i));
                st1.executeUpdate();
            }

            if (tarAlta) {
                java.sql.Date inicioalta = new java.sql.Date(inicioAlta.getTime());
                java.sql.Date finalta = new java.sql.Date(finAlta.getTime());
                PreparedStatement st2 = conn.prepareStatement(query2);
                st2.setInt(1, idPlangenerado);
                st2.setString(2, "ALTA");
                st2.setDate(3, inicioalta);
                st2.setDate(4, finalta);
                st2.setInt(5, costoAlta);
                st2.setBoolean(6, estado);
                st2.executeUpdate();
            }
            if (tarMedia) {
                java.sql.Date iniciomedia = new java.sql.Date(inicioMedia.getTime());
                java.sql.Date finmedia = new java.sql.Date(finMedia.getTime());
                PreparedStatement st3 = conn.prepareStatement(query2);
                st3.setInt(1, idPlangenerado);
                st3.setString(2, "MEDIA");
                st3.setDate(3, iniciomedia);
                st3.setDate(4, finmedia);
                st3.setInt(5, costoMedia);
                st3.setBoolean(6, estado);
                st3.executeUpdate();

            }
            if (tarBaja) {
                java.sql.Date inicibaja = new java.sql.Date(inicioBaja.getTime());
                java.sql.Date finbaja = new java.sql.Date(finBaja.getTime());
                PreparedStatement st4 = conn.prepareStatement(query2);
                st4.setInt(1, idPlangenerado);
                st4.setString(2, "BAJA");
                st4.setDate(3, inicibaja);
                st4.setDate(4, finbaja);
                st4.setInt(5, costoBaja);
                st4.setBoolean(6, estado);
                st4.executeUpdate();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void Update_plan_turistico(int idplan, String titulo, String descripcion, int cant_dias, boolean estado, boolean Alimentacion, String url, List<Integer> ids, java.util.Date inicioAlta, java.util.Date inicioMedia, java.util.Date inicioBaja, java.util.Date finAlta, java.util.Date finMedia, java.util.Date finBaja, int costoAlta, int costoMedia, int costoBaja, boolean tarAlta, boolean tarMedia, boolean tarBaja) {
        String query = "UPDATE  Plan_Turistico " +
                "SET " +
                "    titulo = ?, " +
                "    Descripcion = ?, " +
                "    Fech_modificacion = CURRENT_TIMESTAMP,  " +
                "    Cant_dias = ?, " +
                "    Estado = ?, " +
                "    Alimentación = ?, " +
                "    imagen = ? " +
                "WHERE id_planturistico = ?";
        String query1 = "Insert into plan_punto (id_plan,id_actividad) Values (?,?)";
        String query2 = "INSERT INTO Tarifa (Titulo_Plan, id_Temporada, Fech_Creacion, Fech_inicio, Fech_Modificacion, Fecha_Fin, Costo, Estado)" +
                " VALUES (?, ?, current_timestamp, ?, current_timestamp, ?, ?, ?);\n";
        String query3 = "Delete from plan_punto where id_plan=?";
        String query4 = "DELETE from tarifa where titulo_plan=?;";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, titulo);
            st.setString(2, descripcion);
            st.setInt(3, cant_dias);
            st.setBoolean(4, estado);
            st.setBoolean(5, Alimentacion);
            st.setString(6, url);
            st.setInt(7, idplan);
            st.executeUpdate();
            PreparedStatement st6 = conn.prepareStatement(query3);
            st6.setInt(1, idplan);
            st6.executeUpdate();
            PreparedStatement st7 = conn.prepareStatement(query4);
            st7.setInt(1, idplan);
            st7.executeUpdate();
            for (int i = 0; i < ids.size(); i++) {
                PreparedStatement st1 = conn.prepareStatement(query1);
                st1.setInt(1, idplan);
                st1.setInt(2, ids.get(i));
                st1.executeUpdate();
            }

            if (tarAlta) {
                java.sql.Date inicioalta = new java.sql.Date(inicioAlta.getTime());
                java.sql.Date finalta = new java.sql.Date(finAlta.getTime());
                PreparedStatement st2 = conn.prepareStatement(query2);
                st2.setInt(1, idplan);
                st2.setString(2, "ALTA");
                st2.setDate(3, inicioalta);
                st2.setDate(4, finalta);
                st2.setInt(5, costoAlta);
                st2.setBoolean(6, estado);
                st2.executeUpdate();
            }
            if (tarMedia) {
                java.sql.Date iniciomedia = new java.sql.Date(inicioMedia.getTime());
                java.sql.Date finmedia = new java.sql.Date(finMedia.getTime());
                PreparedStatement st3 = conn.prepareStatement(query2);
                st3.setInt(1, idplan);
                st3.setString(2, "MEDIA");
                st3.setDate(3, iniciomedia);
                st3.setDate(4, finmedia);
                st3.setInt(5, costoMedia);
                st3.setBoolean(6, estado);
                st3.executeUpdate();

            }
            if (tarBaja) {
                java.sql.Date inicibaja = new java.sql.Date(inicioBaja.getTime());
                java.sql.Date finbaja = new java.sql.Date(finBaja.getTime());
                PreparedStatement st4 = conn.prepareStatement(query2);
                st4.setInt(1, idplan);
                st4.setString(2, "BAJA");
                st4.setDate(3, inicibaja);
                st4.setDate(4, finbaja);
                st4.setInt(5, costoBaja);
                st4.setBoolean(6, estado);
                st4.executeUpdate();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
