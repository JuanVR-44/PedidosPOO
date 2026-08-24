package ec.edu.iti.pedidos.service;

import ec.edu.iti.pedidos.dao.DetallePedidoDAO;
import ec.edu.iti.pedidos.model.DetallePedido;
import java.util.List;

public class DetallePedidoService {

    private final DetallePedidoDAO detallePedidoDAO;

    public DetallePedidoService() {
        detallePedidoDAO = new DetallePedidoDAO();
    }

    public boolean registrar(DetallePedido detalle) {
        return detallePedidoDAO.insertar(detalle);
    }

    public List<DetallePedido> listar() {
        return detallePedidoDAO.listar();
    }

    public DetallePedido buscar(int codigo) {
        return detallePedidoDAO.buscarPorCodigo(codigo);
    }

    public boolean actualizar(DetallePedido detalle) {
        return detallePedidoDAO.actualizar(detalle);
    }

    public boolean eliminar(int codigo) {
        return detallePedidoDAO.eliminar(codigo);
    }

    public List<DetallePedido> listarPorPedido(int numeroPedido) {
        return detallePedidoDAO.listarPorPedido(numeroPedido);
    }
}