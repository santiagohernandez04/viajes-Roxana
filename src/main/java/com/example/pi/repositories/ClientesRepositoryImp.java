package com.example.pi.repositories;

import com.example.pi.models.Cliente;
import com.example.pi.models.Vendedor;
import com.example.pi.utils.ConnectionDatabase;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ClientesRepositoryImp {
    public List<Cliente> list(String busqueda) {
        List<Cliente> cliente = new ArrayList<>();
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
        String fechamodform=null;
        if (busqueda == "0") {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM cliente where activo = true ORDER BY fech_modificacion DESC");
                while (rs.next()) {
                    Cliente cl = new Cliente();
                    cl.setCedula(rs.getString("cedula"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setCorreo(rs.getString("correo"));
                    cl.setTelefono_1(rs.getString("telefono_1"));
                    try {
                        Date fechModificacion = formatoEntrada.parse(rs.getString("fech_modificacion"));
                        fechamodform = formatoSalida.format(fechModificacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    cl.setFech_nacimiento(rs.getDate("fech_nacimiento"));
                    cl.setFech_modificacion(fechamodform);
                    cliente.add(cl);

                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            String query = "SELECT * FROM cliente where activo = true and cedula=?";
            try (Connection conn = ConnectionDatabase.getConnection()) {
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, busqueda);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Cliente cl = new Cliente();
                    cl.setCedula(rs.getString("cedula"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setCorreo(rs.getString("correo"));
                    cl.setTelefono_1(rs.getString("telefono_1"));
                    cl.setFech_nacimiento(rs.getDate("fech_nacimiento"));
                    try {
                        Date fechModificacion = formatoEntrada.parse(rs.getString("fech_modificacion"));
                        fechamodform = formatoSalida.format(fechModificacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    cl.setFech_modificacion(fechamodform);
                    cliente.add(cl);
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }
    public List<Cliente> listInactivos(String busqueda) {
        List<Cliente> cliente = new ArrayList<>();
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
        String fechamodform = null;
        if (busqueda == "0") {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM cliente where activo = false");
                while (rs.next()) {
                    Cliente cl = new Cliente();
                    cl.setCedula(rs.getString("cedula"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setCorreo(rs.getString("correo"));
                    cl.setTelefono_1(rs.getString("telefono_1"));
                    try {
                        Date fechModificacion = formatoEntrada.parse(rs.getString("fech_modificacion"));
                        fechamodform = formatoSalida.format(fechModificacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    cl.setFech_nacimiento(rs.getDate("fech_nacimiento"));
                    cl.setFech_modificacion(fechamodform);
                    cliente.add(cl);

                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            try (Connection conn = ConnectionDatabase.getConnection()) {
                String query="SELECT * FROM cliente where activo = false and cedula=?";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, busqueda);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Cliente cl = new Cliente();
                    cl.setCedula(rs.getString("cedula"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setCorreo(rs.getString("correo"));
                    cl.setTelefono_1(rs.getString("telefono_1"));
                    try {
                        Date fechModificacion = formatoEntrada.parse(rs.getString("fech_modificacion"));
                        fechamodform = formatoSalida.format(fechModificacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    cl.setFech_nacimiento(rs.getDate("fech_nacimiento"));
                    cl.setFech_modificacion(fechamodform);
                    cliente.add(cl);

                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }

    public List<Cliente> consulta_compra(String cedula) {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT c.nombre AS nombre_cliente, p.titulo AS plan_comprado, co.fech_creacion AS fecha_compra\n" +
                "FROM cliente c\n" +
                "INNER JOIN Compra co ON c.cedula = co.id_cliente\n" +
                "INNER JOIN Detalle_Compra dc ON co.id_compra = dc.id_compra\n" +
                "INNER JOIN Plan_Turistico p ON dc.id_plan = p.Id_PlanTuristico\n" +
                "WHERE c.cedula = ?;";
        try (Connection conn = ConnectionDatabase.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCedula(cedula);
                cliente.setNombre(rs.getString("nombre_cliente"));
                cliente.setPlan_comprado(rs.getString("plan_comprado"));
                cliente.setFech_compra(rs.getString("fecha_compra"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public void deleteCliente(String cedula) {
        String query = "UPDATE cliente set activo= false WHERE cedula = ?";
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cedula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void NewCliente(String cedula, String nombre, String correo, java.util.Date fecha_nacimiento, String cedula_vendedor, String nom_pers_contacto, String tel_persona_contacto, String telefono_1, String telefono_2, Boolean activo) {
        String query = "INSERT INTO cliente (cedula, nombre, correo, tel_pers_contac, quien_creo, nom_pers_contact, fech_nacimiento, fech_modificacion, fech_creacion, cedula_usuario, Telefono_1, Telefono_2,activo)\n" +
                "VALUES (?, ?, ?,?, (SELECT nombre FROM usuario WHERE cedula = ?), ?, ?,current_timestamp, current_timestamp, ?, ?,?,?);";
        if (cedula != null) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                java.sql.Date sqlDate = new java.sql.Date(fecha_nacimiento.getTime());
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, cedula);
                st.setString(2, nombre);
                st.setString(3, correo);
                st.setString(4, tel_persona_contacto);
                st.setString(5, cedula_vendedor);
                st.setString(6, nom_pers_contacto);
                st.setDate(7, sqlDate);
                st.setString(8, cedula_vendedor);
                st.setString(9, telefono_1);
                st.setString(10, telefono_2);
                st.setBoolean(11, activo);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void UpdateCliente(String cedula, String nombre, String correo, String persona_contacto, String tel_persona_Contacto, java.util.Date fecha_nacimiento, String telefono_1, String telefono_2, Boolean activo) {
        String query = "UPDATE cliente " +
                "SET " +
                "    nombre = ?, " +
                "    correo = ?, " +
                "    tel_pers_contac = ?, " +
                "    nom_pers_contact = ?, " +
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
                st.setString(3, tel_persona_Contacto);
                st.setString(4, persona_contacto);
                st.setDate(5, sqlDate);
                st.setString(6, telefono_1);
                st.setString(7, telefono_2);
                st.setBoolean(8, activo);
                st.setString(9, cedula);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void ReactivarCliente(String cedula) {
            String query = "UPDATE cliente SET  Activo = true WHERE cedula = ?";
            try (Connection conn = ConnectionDatabase.getConnection()) {
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, cedula);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

    public int clientes_Activos() {
        List<Cliente> cliente = new ArrayList<>();
        try (Connection conn = ConnectionDatabase.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente where activo = true");
            while (rs.next()) {
                Cliente cl = new Cliente();
                cliente.add(cl);

            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente.size();
    }
}
