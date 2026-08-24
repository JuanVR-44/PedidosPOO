package ec.edu.iti.pedidos.dao;

import ec.edu.iti.pedidos.model.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // INSERTAR PEDIDO
    public boolean insertar(Pedido pedido) {

        String sql = "INSERT INTO pedido "
                + "(ped_numero, pedi_cliente, ped_fecha, ped_total) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getPed_numero());
            ps.setInt(2, pedido.getPedi_cliente());
            ps.setDate(3, Date.valueOf(pedido.getPed_fecha()));
            ps.setDouble(4, pedido.getPed_total());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar pedido: " + e.getMessage());
            return false;
        }
    }

    // LISTAR PEDIDOS
    public List<Pedido> listar() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT ped_numero, pedi_cliente, ped_fecha, ped_total "
                + "FROM pedido ORDER BY ped_numero";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setPed_numero(rs.getInt("ped_numero"));
                pedido.setPedi_cliente(rs.getInt("pedi_cliente"));

                if (rs.getDate("ped_fecha") != null) {
                    pedido.setPed_fecha(
                            rs.getDate("ped_fecha").toLocalDate()
                    );
                }

                pedido.setPed_total(rs.getDouble("ped_total"));

                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // BUSCAR PEDIDO POR NÚMERO
    public Pedido buscarPorNumero(int numero) {

        String sql = "SELECT ped_numero, pedi_cliente, ped_fecha, ped_total "
                + "FROM pedido WHERE ped_numero = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numero);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Pedido pedido = new Pedido();

                    pedido.setPed_numero(rs.getInt("ped_numero"));
                    pedido.setPedi_cliente(rs.getInt("pedi_cliente"));

                    if (rs.getDate("ped_fecha") != null) {
                        pedido.setPed_fecha(
                                rs.getDate("ped_fecha").toLocalDate()
                        );
                    }

                    pedido.setPed_total(rs.getDouble("ped_total"));

                    return pedido;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar pedido: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR PEDIDO
    public boolean actualizar(Pedido pedido) {

        String sql = "UPDATE pedido SET "
                + "pedi_cliente = ?, "
                + "ped_fecha = ?, "
                + "ped_total = ? "
                + "WHERE ped_numero = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getPedi_cliente());
            ps.setDate(2, Date.valueOf(pedido.getPed_fecha()));
            ps.setDouble(3, pedido.getPed_total());
            ps.setInt(4, pedido.getPed_numero());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR PEDIDO
    public boolean eliminar(int numero) {

        String sql = "DELETE FROM pedido WHERE ped_numero = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numero);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }
}