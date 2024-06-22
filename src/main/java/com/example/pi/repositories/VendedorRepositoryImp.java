package com.example.pi.repositories;

import com.example.pi.controllers.Insert_Update_Bean;
import com.example.pi.models.Cliente;
import com.example.pi.models.Vendedor;
import com.example.pi.utils.ConnectionDatabase;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class VendedorRepositoryImp {
    Insert_Update_Bean is = new Insert_Update_Bean();

    public VendedorRepositoryImp() {

    }

    public void deleteVendedor(String cedula) {
        String query = "UPDATE usuario set activo= false WHERE cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cedula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateVendedor(String cedula, String nombre, String correo, String username, String password, Date fecha_nacimiento, String telefono_1, String telefono_2, Boolean activo) {
        String query = "UPDATE usuario " +
                "SET " +
                "    nombre = ?, " +
                "    correo = ?, " +
                "    username = ?, " +
                "    password = ?, " +
                "    fech_nacimiento = ?, " +
                "    Telefono_1 = ?, " +
                "    Telefono_2 = ?, " +
                "    Activo = ?, " +
                "    fech_modificacion = CURRENT_TIMESTAMP " +
                "WHERE cedula = ?";
        if (cedula != null) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                java.sql.Date sqlDate = new java.sql.Date(fecha_nacimiento.getTime());
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, nombre);
                st.setString(2, correo);
                st.setString(3, username);
                st.setString(4, password);
                st.setDate(5, sqlDate);
                st.setString(6, telefono_1);
                st.setString(7, telefono_2);
                st.setBoolean(8, activo);
                st.setString(9, cedula);
                st.executeUpdate();
                is.limpiar();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Vendedor> list(String cedula) {
        List<Vendedor> vendedor = new ArrayList<>();
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
        String fechacreacionform = null;
        if (cedula == "0") {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM usuario where activo = true and rol='vendedor' ORDER BY fech_modificacion DESC");
                while (rs.next()) {
                    Vendedor vd = new Vendedor();
                    vd.setCedula(rs.getString("cedula"));
                    vd.setNombreVendedor(rs.getString("nombre"));
                    vd.setUsername(rs.getString("username"));
                    vd.setCorreoVendedor(rs.getString("correo"));
                    vd.setTelefono_1(rs.getString("telefono_1"));
                    vd.setFecha_NacimientoVendedor(rs.getString("fech_nacimiento"));
                    try {
                        Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    vd.setFecha_creacionVendedor(fechacreacionform);
                    vendedor.add(vd);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "SELECT * FROM usuario where activo = true and rol='vendedor' and cedula=?";
            try (Connection conn = ConnectionDatabase.getConnection()) {
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, cedula);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Vendedor vd = new Vendedor();
                    vd.setCedula(rs.getString("cedula"));
                    vd.setNombreVendedor(rs.getString("nombre"));
                    vd.setUsername(rs.getString("username"));
                    vd.setCorreoVendedor(rs.getString("correo"));
                    vd.setTelefono_1(rs.getString("telefono_1"));
                    vd.setFecha_NacimientoVendedor(rs.getString("fech_nacimiento"));
                    try {
                        Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    vd.setFecha_creacionVendedor(fechacreacionform);
                    vendedor.add(vd);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return vendedor;
    }

    public List<Vendedor> listInactivos(String busqueda) {
        List<Vendedor> vendedor = new ArrayList<>();
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
        String fechacreacionform = null;
        if (busqueda == "0") {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM usuario where activo = false and rol='vendedor'");
                while (rs.next()) {
                    Vendedor vd = new Vendedor();
                    vd.setCedula(rs.getString("cedula"));
                    vd.setNombreVendedor(rs.getString("nombre"));
                    vd.setUsername(rs.getString("username"));
                    vd.setCorreoVendedor(rs.getString("correo"));
                    vd.setTelefono_1(rs.getString("telefono_1"));
                    vd.setFecha_NacimientoVendedor(rs.getString("fech_nacimiento"));
                    try {
                        Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    vd.setFecha_creacionVendedor(fechacreacionform);
                    vendedor.add(vd);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                String query = "SELECT * FROM usuario where activo = false and rol='vendedor' and cedula=?";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, busqueda);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Vendedor vd = new Vendedor();
                    vd.setCedula(rs.getString("cedula"));
                    vd.setNombreVendedor(rs.getString("nombre"));
                    vd.setUsername(rs.getString("username"));
                    vd.setCorreoVendedor(rs.getString("correo"));
                    vd.setTelefono_1(rs.getString("telefono_1"));
                    vd.setFecha_NacimientoVendedor(rs.getString("fech_nacimiento"));
                    try {
                        Date fechcreacion = formatoEntrada.parse(rs.getString("fech_creacion"));
                        fechacreacionform = formatoSalida.format(fechcreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    vd.setFecha_creacionVendedor(fechacreacionform);
                    vendedor.add(vd);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vendedor;
    }

    public void ReactivarVendedor(String cedula) {
        String query = "UPDATE usuario SET  Activo = true WHERE cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, cedula);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Vendedor> login(String username, String password) {
        Vendedor vendedor = null;
        String query = "SELECT u.* FROM usuario as u WHERE u.username = ? AND u.password = ?";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vendedor = new Vendedor();
                vendedor.setUsername(rs.getString("username"));
                vendedor.setPassword(rs.getString("password"));
                vendedor.setRol(rs.getString("rol"));
                vendedor.setCedula(rs.getString("cedula"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(vendedor);
    }

    public void NewVendedor(String cedula, String nombre, String correo, String username, String password, Date fecha_nacimiento, String telefono_1, String telefono_2, Boolean activo) {
        String query = "INSERT INTO usuario (cedula, nombre, correo, username, password, fech_nacimiento, Telefono_1,Telefono_2,activo,fech_creacion,fech_modificacion)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,current_timestamp,current_timestamp)";
        if (cedula != null) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                java.sql.Date sqlDate = new java.sql.Date(fecha_nacimiento.getTime());
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, cedula);
                st.setString(2, nombre);
                st.setString(3, correo);
                st.setString(4, username);
                st.setString(5, password);
                st.setDate(6, sqlDate);
                st.setString(7, telefono_1);
                st.setString(8, telefono_2);
                st.setBoolean(9, activo);
                st.executeUpdate();
                is.limpiar();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Optional<Vendedor> buscar(String cedula) {
        Vendedor vendedor = null;
        String query = "SELECT u.* FROM usuario as u WHERE u.cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vendedor = new Vendedor();
                vendedor.setUsername(rs.getString("username"));
                vendedor.setCedula(rs.getString("cedula"));
                vendedor.setNombreVendedor(rs.getString("nombre"));
                vendedor.setUsername(rs.getString("username"));
                vendedor.setCorreoVendedor(rs.getString("correo"));
                vendedor.setTelefono_1(rs.getString("telefono_1"));
                vendedor.setTelefono_2(rs.getString("telefono_2"));
                vendedor.setPassword(rs.getString("password"));
                vendedor.setFecha_NacimientoVendedor(rs.getString("fech_nacimiento"));
                vendedor.setFecha_creacionVendedor(rs.getString("fech_creacion"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(vendedor);
    }
}