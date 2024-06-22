package com.example.pi.repositories;

import com.example.pi.models.Ciudades;
import com.example.pi.models.Departamento;
import com.example.pi.utils.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CiudadesRepositoryImp {
    public List<Ciudades> listByDepartamento(int departamentoId) {
        List<Ciudades> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudades WHERE departamento_id = ?";

        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departamentoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Ciudades ciudad = new Ciudades();
                ciudad.setIdCiudad(rs.getInt("id"));
                ciudad.setCiudades(rs.getString("ciudad"));
                ciudad.setDepartamento_id(rs.getInt("departamento_id"));

                ciudades.add(ciudad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ciudades;
    }
}
