package com.example.pi.repositories;

import com.example.pi.controllers.BeanJlist;
import com.example.pi.controllers.Insert_Update_Bean;
import com.example.pi.models.Cliente;
import com.example.pi.models.Tarifa;
import com.example.pi.utils.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarifaRepositoryImp {

    public List<Tarifa> obtenerTarifasPorPlan(int id_plan) {
        List<Tarifa> tarifas = new ArrayList<>();
        String query = "SELECT * FROM tarifa WHERE titulo_plan = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_plan);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tarifa tarifa = new Tarifa();
                tarifa.setTitulo_plan(rs.getInt("titulo_plan"));
                tarifa.setId_temporada(rs.getString("id_temporada"));
                tarifa.setCosto(rs.getLong("costo"));
                tarifas.add(tarifa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarifas;
    }

    public long costoPlan(String temporada,int id_plan){
        long costo=0;
        String query = "SELECT costo FROM tarifa WHERE titulo_plan = ? and id_temporada=?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_plan);
            stmt.setString(2,temporada);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               costo=rs.getLong("costo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return costo;
    }
    public List<String> obtenerNombresPuntosPorPlan(int id_plan) {
        List<String> nombresPuntos = new ArrayList<>();
        String query = "SELECT p.nom_actividad FROM punto_visita p " +
                "JOIN plan_punto pp ON p.id_actividad = pp.id_actividad " +
                "WHERE pp.id_plan = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_plan);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombrePunto = rs.getString("nom_actividad");
                nombresPuntos.add(nombrePunto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombresPuntos;
    }
}
