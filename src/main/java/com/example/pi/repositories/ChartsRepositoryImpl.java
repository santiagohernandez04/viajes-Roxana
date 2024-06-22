package com.example.pi.repositories;

import com.example.pi.models.Plan_turistico;
import com.example.pi.models.Punto_visita;
import com.example.pi.utils.ConnectionDatabase;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChartsRepositoryImpl {
    public int Cantidad_clientes() {
        int cant = 0;
        String sql = "      SELECT COUNT(*) AS cantidad_clientes_activos" +
                " FROM cliente" +
                " WHERE Activo = true;";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cant = rs.getInt("cantidad_clientes_activos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cant;

    }

    public long total_ganancias() {
        long ganancias = 0;
        String sql = "   SELECT SUM(total) AS total_ganancias" +
                "  FROM compra" +
                " WHERE EXTRACT(YEAR FROM fech_creacion) = EXTRACT(YEAR FROM CURRENT_DATE)";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ganancias = rs.getInt("total_ganancias");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ganancias;

    }

    public List<Number> costo_x_temporada() {
        List<Number> costos = new ArrayList<>();
        String sql = "SELECT t.id_Temporada," +
                " AVG(t.Costo) AS costo_promedio" +
                " FROM tarifa t" +
                " JOIN plan_turistico pt ON t.Titulo_Plan = pt.Id_PlanTuristico" +
                " GROUP BY t.id_Temporada";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                costos.add(rs.getInt("costo_promedio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return costos;
    }

    public long costo_promedio_planes() {
        long costo = 0;
        String sql = "SELECT AVG(total_planes) AS costo_promedio_planes_turisticos" +
                " FROM compra";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                costo = rs.getInt("costo_promedio_planes_turisticos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return costo;
    }

        public List<Punto_visita> consulta1(int year, int month) {
        int i=0;
            List<Punto_visita> actividadesPopulares = new ArrayList<>();
            String sql = "SELECT PV.nom_Actividad, COUNT(*) as CantidadCompras " +
                    "FROM Compra C " +
                    "JOIN Detalle_Compra DC ON C.id_compra = DC.id_compra " +
                    "JOIN Plan_Turistico PT ON DC.id_plan = PT.Id_PlanTuristico " +
                    "JOIN Plan_Punto PP ON PT.Id_PlanTuristico = PP.id_Plan " +
                    "JOIN Punto_Visita PV ON PP.id_Actividad = PV.id_Actividad " +
                    "WHERE EXTRACT(YEAR FROM C.fech_creacion) = ? " +
                    "  AND EXTRACT(MONTH FROM C.fech_creacion) = ? " +
                    "GROUP BY PV.nom_Actividad " +
                    "ORDER BY CantidadCompras DESC";

            try (Connection conn = ConnectionDatabase.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, year);
                pstmt.setInt(2, month);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()&& i<5) {
                    Punto_visita pv = new Punto_visita();
                    pv.setNom_actividad(rs.getString("nom_actividad"));
                    pv.setCantidadcompras(rs.getInt("cantidadcompras"));
                    actividadesPopulares.add(pv);
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return actividadesPopulares;
        }

        public List<Plan_turistico> consulta2(int year, int month) {
        int i=0;
            List<Plan_turistico> planesvendidos = new ArrayList<>();
            String sql = "SELECT pt.Id_PlanTuristico, pt.titulo, COUNT(dc.id_detalleCompra) AS cantidad_vendida FROM Plan_Turistico pt JOIN Detalle_Compra dc ON pt.Id_PlanTuristico = dc.id_plan JOIN Compra c ON dc.id_compra = c.id_compra WHERE EXTRACT(MONTH FROM c.fech_creacion) = ? AND EXTRACT(YEAR FROM c.fech_creacion) = ? GROUP BY pt.Id_PlanTuristico, pt.titulo ORDER BY cantidad_vendida DESC;";

            try (Connection conn = ConnectionDatabase.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(2, year);
                pstmt.setInt(1, month);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()&&i<4) {
                    Plan_turistico pt = new Plan_turistico();
                    pt.setId_planturistico(rs.getInt("id_planturistico"));
                    pt.setTitulo(rs.getString("titulo"));
                    pt.setCant_planes(rs.getInt("cantidad_vendida"));
                    planesvendidos.add(pt);
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return planesvendidos;
        }



}
