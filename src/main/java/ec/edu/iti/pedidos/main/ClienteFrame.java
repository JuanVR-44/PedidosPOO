package ec.edu.iti.pedidos.main;

import ec.edu.iti.pedidos.model.Cliente;
import ec.edu.iti.pedidos.service.ClienteService;
import ec.edu.iti.pedidos.util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteFrame extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;

    private JComboBox<String> cmbBuscarPor;
    private JTextField txtBuscar;

    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    private ClienteService clienteService;

    public ClienteFrame() {

        clienteService = new ClienteService();

        setTitle("Gestión de Clientes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
        cargarClientes();
    }

    private void crearInterfaz() {

        // ==========================================
        // PANEL DEL FORMULARIO
        // ==========================================

        JPanel panelFormulario = new JPanel(
                new GridLayout(5, 2, 10, 10)
        );

        panelFormulario.setBorder(
                BorderFactory.createTitledBorder("Datos del Cliente")
        );

        panelFormulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        panelFormulario.add(txtCedula);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        // ==========================================
        // PANEL DE BÚSQUEDA
        // ==========================================

        JPanel panelBusqueda = new JPanel();

        panelBusqueda.setBorder(
                BorderFactory.createTitledBorder("Buscar Cliente")
        );

        JLabel lblBuscarPor = new JLabel("Buscar por:");

        cmbBuscarPor = new JComboBox<>(
                new String[]{"Cédula", "Nombre"}
        );

        txtBuscar = new JTextField(20);

        JButton btnBuscar = new JButton("Buscar");

        panelBusqueda.add(lblBuscarPor);
        panelBusqueda.add(cmbBuscarPor);
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        // ==========================================
        // PANEL DE BOTONES
        // ==========================================

        JPanel panelBotones = new JPanel();

        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // ==========================================
        // TABLA
        // ==========================================

        modeloTabla = new DefaultTableModel(
                new Object[]{
                    "Código",
                    "Cédula",
                    "Nombre",
                    "Dirección",
                    "Teléfono"
                }, 0
        );

        tablaClientes = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaClientes);

        // ==========================================
        // PANEL SUPERIOR
        // ==========================================

        JPanel panelSuperior = new JPanel(
                new BorderLayout(10, 10)
        );

        panelSuperior.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBusqueda, BorderLayout.SOUTH);

        // ==========================================
        // VENTANA
        // ==========================================

        setLayout(new BorderLayout(10, 10));

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // ==========================================
        // EVENTOS
        // ==========================================

        btnGuardar.addActionListener(e -> guardarCliente());

        btnBuscar.addActionListener(e -> buscarCliente());

        btnActualizar.addActionListener(e -> actualizarCliente());

        btnEliminar.addActionListener(e -> eliminarCliente());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        // ==========================================
        // SELECCIONAR CLIENTE DE LA TABLA
        // ==========================================

        tablaClientes.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && tablaClientes.getSelectedRow() != -1) {

                int fila = tablaClientes.getSelectedRow();

                txtCodigo.setText(
                        modeloTabla.getValueAt(fila, 0).toString()
                );

                txtCedula.setText(
                        modeloTabla.getValueAt(fila, 1).toString()
                );

                txtNombre.setText(
                        modeloTabla.getValueAt(fila, 2).toString()
                );

                txtDireccion.setText(
                        modeloTabla.getValueAt(fila, 3).toString()
                );

                txtTelefono.setText(
                        modeloTabla.getValueAt(fila, 4).toString()
                );
            }
        });
    }

    // ==========================================
    // GUARDAR
    // ==========================================

    private void guardarCliente() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            String cedula = txtCedula.getText().trim();

            // VALIDAR CÉDULA
            if (!Util.validarCedula(cedula)) {

                JOptionPane.showMessageDialog(
                        this,
                        "La cédula ingresada no es válida.",
                        "Cédula inválida",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Cliente cliente = new Cliente(
                    codigo,
                    cedula,
                    txtNombre.getText().trim(),
                    txtDireccion.getText().trim(),
                    txtTelefono.getText().trim()
            );

            if (clienteService.registrar(cliente)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cliente registrado correctamente."
                );

                cargarClientes();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el cliente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El código debe ser un número.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================
    // BUSCAR
    // ==========================================

    private void buscarCliente() {

        String criterio = txtBuscar.getText().trim();

        if (criterio.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un valor para buscar."
            );

            return;
        }

        // BUSCAR POR CÉDULA
        if (cmbBuscarPor.getSelectedItem().equals("Cédula")) {

            if (!Util.validarCedula(criterio)) {

                JOptionPane.showMessageDialog(
                        this,
                        "La cédula ingresada no es válida.",
                        "Cédula inválida",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Cliente cliente =
                    clienteService.buscarPorCedula(criterio);

            if (cliente != null) {

                mostrarCliente(cliente);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró un cliente con esa cédula."
                );
            }

        // BUSCAR POR NOMBRE
        } else {

            List<Cliente> clientes =
                    clienteService.buscarPorNombre(criterio);

            if (clientes.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontraron clientes con ese nombre."
                );

            } else {

                mostrarClientesEnTabla(clientes);
            }
        }
    }

    // ==========================================
    // ACTUALIZAR
    // ==========================================

    private void actualizarCliente() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            String cedula = txtCedula.getText().trim();

            // VALIDAR CÉDULA
            if (!Util.validarCedula(cedula)) {

                JOptionPane.showMessageDialog(
                        this,
                        "La cédula ingresada no es válida.",
                        "Cédula inválida",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Cliente cliente = new Cliente(
                    codigo,
                    cedula,
                    txtNombre.getText().trim(),
                    txtDireccion.getText().trim(),
                    txtTelefono.getText().trim()
            );

            if (clienteService.actualizar(cliente)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cliente actualizado correctamente."
                );

                cargarClientes();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el cliente."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El código debe ser un número."
            );
        }
    }

    // ==========================================
    // ELIMINAR
    // ==========================================

    private void eliminarCliente() {

        try {

            int codigo =
                    Integer.parseInt(txtCodigo.getText());

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de eliminar este cliente?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                if (clienteService.eliminar(codigo)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Cliente eliminado correctamente."
                    );

                    cargarClientes();
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo eliminar el cliente."
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un código válido."
            );
        }
    }

    // ==========================================
    // CARGAR TODOS LOS CLIENTES
    // ==========================================

    private void cargarClientes() {

        modeloTabla.setRowCount(0);

        List<Cliente> clientes =
                clienteService.listar();

        mostrarClientesEnTabla(clientes);
    }

    // ==========================================
    // MOSTRAR CLIENTES EN TABLA
    // ==========================================

    private void mostrarClientesEnTabla(List<Cliente> clientes) {

        modeloTabla.setRowCount(0);

        for (Cliente cliente : clientes) {

            modeloTabla.addRow(new Object[]{
                cliente.getCli_codigo(),
                cliente.getCli_cedula(),
                cliente.getCli_nombre(),
                cliente.getCli_direccion(),
                cliente.getCli_telefono()
            });
        }
    }

    // ==========================================
    // MOSTRAR UN CLIENTE
    // ==========================================

    private void mostrarCliente(Cliente cliente) {

        txtCodigo.setText(
                String.valueOf(cliente.getCli_codigo())
        );

        txtCedula.setText(
                cliente.getCli_cedula()
        );

        txtNombre.setText(
                cliente.getCli_nombre()
        );

        txtDireccion.setText(
                cliente.getCli_direccion()
        );

        txtTelefono.setText(
                cliente.getCli_telefono()
        );

        modeloTabla.setRowCount(0);

        modeloTabla.addRow(new Object[]{
            cliente.getCli_codigo(),
            cliente.getCli_cedula(),
            cliente.getCli_nombre(),
            cliente.getCli_direccion(),
            cliente.getCli_telefono()
        });
    }

    // ==========================================
    // LIMPIAR
    // ==========================================

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtCedula.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");

        txtBuscar.setText("");

        tablaClientes.clearSelection();

        cargarClientes();
    }
}