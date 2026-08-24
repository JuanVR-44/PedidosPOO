package ec.edu.iti.pedidos.dao;

import ec.edu.iti.pedidos.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // INSERTAR PRODUCTO
    public boolean insertar(Producto producto) {

        String sql = "INSERT INTO producto "
                + "(pro_codigo, pro_nombre, pro_precio) "
                + "VALUES (?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, producto.getPro_codigo());
            ps.setString(2, producto.getPro_nombre());
            ps.setDouble(3, producto.getPro_precio());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    // LISTAR PRODUCTOS
    public List<Producto> listar() {

        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT pro_codigo, pro_nombre, pro_precio "
                + "FROM producto ORDER BY pro_codigo";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto producto = new Producto();

                producto.setPro_codigo(rs.getInt("pro_codigo"));
                producto.setPro_nombre(rs.getString("pro_nombre"));
                producto.setPro_precio(rs.getDouble("pro_precio"));

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return productos;
    }

    // BUSCAR PRODUCTO POR CÓDIGO
    public Producto buscarPorCodigo(int codigo) {

        String sql = "SELECT pro_codigo, pro_nombre, pro_precio "
                + "FROM producto WHERE pro_codigo = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Producto producto = new Producto();

                    producto.setPro_codigo(rs.getInt("pro_codigo"));
                    producto.setPro_nombre(rs.getString("pro_nombre"));
                    producto.setPro_precio(rs.getDouble("pro_precio"));

                    return producto;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR PRODUCTO
    public boolean actualizar(Producto producto) {

        String sql = "UPDATE producto SET "
                + "pro_nombre = ?, "
                + "pro_precio = ? "
                + "WHERE pro_codigo = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getPro_nombre());
            ps.setDouble(2, producto.getPro_precio());
            ps.setInt(3, producto.getPro_codigo());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR PRODUCTO
    public boolean eliminar(int codigo) {

        String sql = "DELETE FROM producto WHERE pro_codigo = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}