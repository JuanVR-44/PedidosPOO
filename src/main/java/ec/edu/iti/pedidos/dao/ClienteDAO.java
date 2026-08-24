package ec.edu.iti.pedidos.dao;

import ec.edu.iti.pedidos.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // INSERTAR CLIENTE
    public boolean insertar(Cliente cliente) {

        String sql = "INSERT INTO cliente "
                + "(cli_codigo, cli_cedula, cli_nombre, cli_direccion, cli_telefono) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cliente.getCli_codigo());
            ps.setString(2, cliente.getCli_cedula());
            ps.setString(3, cliente.getCli_nombre());
            ps.setString(4, cliente.getCli_direccion());
            ps.setString(5, cliente.getCli_telefono());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    // LISTAR CLIENTES
    public List<Cliente> listar() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT cli_codigo, cli_cedula, cli_nombre, "
                + "cli_direccion, cli_telefono "
                + "FROM cliente ORDER BY cli_codigo";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setCli_codigo(rs.getInt("cli_codigo"));
                cliente.setCli_cedula(rs.getString("cli_cedula"));
                cliente.setCli_nombre(rs.getString("cli_nombre"));
                cliente.setCli_direccion(rs.getString("cli_direccion"));
                cliente.setCli_telefono(rs.getString("cli_telefono"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return clientes;
    }

    // BUSCAR CLIENTE POR CÓDIGO
    public Cliente buscarPorCodigo(int codigo) {

        String sql = "SELECT cli_codigo, cli_cedula, cli_nombre, "
                + "cli_direccion, cli_telefono "
                + "FROM cliente WHERE cli_codigo = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Cliente cliente = new Cliente();

                    cliente.setCli_codigo(rs.getInt("cli_codigo"));
                    cliente.setCli_cedula(rs.getString("cli_cedula"));
                    cliente.setCli_nombre(rs.getString("cli_nombre"));
                    cliente.setCli_direccion(rs.getString("cli_direccion"));
                    cliente.setCli_telefono(rs.getString("cli_telefono"));

                    return cliente;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }

        return null;
    }

    // BUSCAR CLIENTE POR CÉDULA
    public Cliente buscarPorCedula(String cedula) {

        String sql = "SELECT cli_codigo, cli_cedula, cli_nombre, "
                + "cli_direccion, cli_telefono "
                + "FROM cliente WHERE cli_cedula = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Cliente cliente = new Cliente();

                    cliente.setCli_codigo(rs.getInt("cli_codigo"));
                    cliente.setCli_cedula(rs.getString("cli_cedula"));
                    cliente.setCli_nombre(rs.getString("cli_nombre"));
                    cliente.setCli_direccion(rs.getString("cli_direccion"));
                    cliente.setCli_telefono(rs.getString("cli_telefono"));

                    return cliente;
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al buscar cliente por cédula: "
                    + e.getMessage()
            );
        }

        return null;
    }

    // BUSCAR CLIENTES POR NOMBRE
    public List<Cliente> buscarPorNombre(String nombre) {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT cli_codigo, cli_cedula, cli_nombre, "
                + "cli_direccion, cli_telefono "
                + "FROM cliente "
                + "WHERE LOWER(cli_nombre) LIKE LOWER(?) "
                + "ORDER BY cli_nombre";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Cliente cliente = new Cliente();

                    cliente.setCli_codigo(rs.getInt("cli_codigo"));
                    cliente.setCli_cedula(rs.getString("cli_cedula"));
                    cliente.setCli_nombre(rs.getString("cli_nombre"));
                    cliente.setCli_direccion(rs.getString("cli_direccion"));
                    cliente.setCli_telefono(rs.getString("cli_telefono"));

                    clientes.add(cliente);
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al buscar cliente por nombre: "
                    + e.getMessage()
            );
        }

        return clientes;
    }

    // ACTUALIZAR CLIENTE
    public boolean actualizar(Cliente cliente) {

        String sql = "UPDATE cliente SET "
                + "cli_cedula = ?, "
                + "cli_nombre = ?, "
                + "cli_direccion = ?, "
                + "cli_telefono = ? "
                + "WHERE cli_codigo = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getCli_cedula());
            ps.setString(2, cliente.getCli_nombre());
            ps.setString(3, cliente.getCli_direccion());
            ps.setString(4, cliente.getCli_telefono());
            ps.setInt(5, cliente.getCli_codigo());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR CLIENTE
    public boolean eliminar(int codigo) {

        String sql = "DELETE FROM cliente WHERE cli_codigo = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}