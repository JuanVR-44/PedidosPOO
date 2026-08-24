package ec.edu.iti.pedidos.dao;

import ec.edu.iti.pedidos.model.DetallePedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {

    // INSERTAR DETALLE
    public boolean insertar(DetallePedido detalle) {

        String sql = "INSERT INTO detalle_pedido "
                + "(dep_codigo, dep_pedido, dep_producto, dep_cantidad, dep_subtotal) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getDep_codigo());
            ps.setInt(2, detalle.getDep_pedido());
            ps.setInt(3, detalle.getDep_producto());
            ps.setInt(4, detalle.getDep_cantidad());
            ps.setDouble(5, detalle.getDep_subtotal());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar detalle: " + e.getMessage());
            return false;
        }
    }

    // LISTAR DETALLES
    public List<DetallePedido> listar() {

        List<DetallePedido> detalles = new ArrayList<>();

        String sql = "SELECT dep_codigo, dep_pedido, dep_producto, "
                + "dep_cantidad, dep_subtotal "
                + "FROM detalle_pedido ORDER BY dep_codigo";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                DetallePedido detalle = new DetallePedido();

                detalle.setDep_codigo(rs.getInt("dep_codigo"));
                detalle.setDep_pedido(rs.getInt("dep_pedido"));
                detalle.setDep_producto(rs.getInt("dep_producto"));
                detalle.setDep_cantidad(rs.getInt("dep_cantidad"));
                detalle.setDep_subtotal(rs.getDouble("dep_subtotal"));

                detalles.add(detalle);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar detalles: " + e.getMessage());
        }

        return detalles;
    }

    // BUSCAR DETALLE POR CÓDIGO
    public DetallePedido buscarPorCodigo(int codigo) {

        String sql = "SELECT dep_codigo, dep_pedido, dep_producto, "
                + "dep_cantidad, dep_subtotal "
                + "FROM detalle_pedido WHERE dep_codigo = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    DetallePedido detalle = new DetallePedido();

                    detalle.setDep_codigo(rs.getInt("dep_codigo"));
                    detalle.setDep_pedido(rs.getInt("dep_pedido"));
                    detalle.setDep_producto(rs.getInt("dep_producto"));
                    detalle.setDep_cantidad(rs.getInt("dep_cantidad"));
                    detalle.setDep_subtotal(rs.getDouble("dep_subtotal"));

                    return detalle;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar detalle: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR DETALLE
    public boolean actualizar(DetallePedido detalle) {

        String sql = "UPDATE detalle_pedido SET "
                + "dep_pedido = ?, "
                + "dep_producto = ?, "
                + "dep_cantidad = ?, "
                + "dep_subtotal = ? "
                + "WHERE dep_codigo = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getDep_pedido());
            ps.setInt(2, detalle.getDep_producto());
            ps.setInt(3, detalle.getDep_cantidad());
            ps.setDouble(4, detalle.getDep_subtotal());
            ps.setInt(5, detalle.getDep_codigo());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR DETALLE
    public boolean eliminar(int codigo) {

        String sql = "DELETE FROM detalle_pedido WHERE dep_codigo = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle: " + e.getMessage());
            return false;
        }
    }

    // LISTAR DETALLES DE UN PEDIDO
    public List<DetallePedido> listarPorPedido(int numeroPedido) {

        List<DetallePedido> detalles = new ArrayList<>();

        String sql = "SELECT dep_codigo, dep_pedido, dep_producto, "
                + "dep_cantidad, dep_subtotal "
                + "FROM detalle_pedido "
                + "WHERE dep_pedido = ? "
                + "ORDER BY dep_codigo";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numeroPedido);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    DetallePedido detalle = new DetallePedido();

                    detalle.setDep_codigo(rs.getInt("dep_codigo"));
                    detalle.setDep_pedido(rs.getInt("dep_pedido"));
                    detalle.setDep_producto(rs.getInt("dep_producto"));
                    detalle.setDep_cantidad(rs.getInt("dep_cantidad"));
                    detalle.setDep_subtotal(rs.getDouble("dep_subtotal"));

                    detalles.add(detalle);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar detalles del pedido: "
                    + e.getMessage());
        }

        return detalles;
    }
}