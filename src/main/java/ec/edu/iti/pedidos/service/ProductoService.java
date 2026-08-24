package ec.edu.iti.pedidos.service;

import ec.edu.iti.pedidos.dao.ProductoDAO;
import ec.edu.iti.pedidos.model.Producto;
import java.util.List;

public class ProductoService {

    private final ProductoDAO productoDAO;

    public ProductoService() {
        productoDAO = new ProductoDAO();
    }

    public boolean registrar(Producto producto) {
        return productoDAO.insertar(producto);
    }

    public List<Producto> listar() {
        return productoDAO.listar();
    }

    public Producto buscar(int codigo) {
        return productoDAO.buscarPorCodigo(codigo);
    }

    public boolean actualizar(Producto producto) {
        return productoDAO.actualizar(producto);
    }

    public boolean eliminar(int codigo) {
        return productoDAO.eliminar(codigo);
    }
}