package ec.edu.iti.pedidos.service;

import ec.edu.iti.pedidos.dao.PedidoDAO;
import ec.edu.iti.pedidos.model.Pedido;
import java.util.List;

public class PedidoService {

    private final PedidoDAO pedidoDAO;

    public PedidoService() {
        pedidoDAO = new PedidoDAO();
    }

    public boolean registrar(Pedido pedido) {
        return pedidoDAO.insertar(pedido);
    }

    public List<Pedido> listar() {
        return pedidoDAO.listar();
    }

    public Pedido buscar(int numero) {
        return pedidoDAO.buscarPorNumero(numero);
    }

    public boolean actualizar(Pedido pedido) {
        return pedidoDAO.actualizar(pedido);
    }

    public boolean eliminar(int numero) {
        return pedidoDAO.eliminar(numero);
    }
}