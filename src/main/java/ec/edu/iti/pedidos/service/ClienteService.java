package ec.edu.iti.pedidos.service;

import ec.edu.iti.pedidos.dao.ClienteDAO;
import ec.edu.iti.pedidos.model.Cliente;
import java.util.List;

public class ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteService() {
        clienteDAO = new ClienteDAO();
    }

    // REGISTRAR CLIENTE
    public boolean registrar(Cliente cliente) {
        return clienteDAO.insertar(cliente);
    }

    // LISTAR CLIENTES
    public List<Cliente> listar() {
        return clienteDAO.listar();
    }

    // BUSCAR POR CÓDIGO
    public Cliente buscarPorCodigo(int codigo) {
        return clienteDAO.buscarPorCodigo(codigo);
    }

    // BUSCAR POR CÉDULA
    public Cliente buscarPorCedula(String cedula) {
        return clienteDAO.buscarPorCedula(cedula);
    }

    // BUSCAR POR NOMBRE
    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteDAO.buscarPorNombre(nombre);
    }

    // ACTUALIZAR CLIENTE
    public boolean actualizar(Cliente cliente) {
        return clienteDAO.actualizar(cliente);
    }

    // ELIMINAR CLIENTE
    public boolean eliminar(int codigo) {
        return clienteDAO.eliminar(codigo);
    }
}