package com.example.pi.repositories;

import com.example.pi.models.Departamento;
import com.example.pi.utils.ConnectionDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartamentosRepositoryImp {
    public List<Departamento> list() {
        List<Departamento> departamentos = new ArrayList<>();
        try (Connection conn = ConnectionDatabase.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM departamentos");
            while (rs.next()) {
                Departamento dpto = new Departamento();
                dpto.setId(rs.getInt("id"));
                dpto.setDepartamento(rs.getString("departamento"));

                departamentos.add(dpto);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departamentos;
    }
}
