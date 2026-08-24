package ec.edu.iti.pedidos.main;

import ec.edu.iti.pedidos.model.Pedido;
import ec.edu.iti.pedidos.service.PedidoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PedidoFrame extends JFrame {

    private JTextField txtNumero;
    private JTextField txtCliente;
    private JTextField txtFecha;
    private JTextField txtTotal;

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;

    private PedidoService pedidoService;

    private final DateTimeFormatter formatoFecha =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public PedidoFrame() {

        pedidoService = new PedidoService();

        setTitle("Gestión de Pedidos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
        cargarPedidos();
    }

    private void crearInterfaz() {

        JPanel panelFormulario = new JPanel(
                new GridLayout(4, 2, 10, 10)
        );

        panelFormulario.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        panelFormulario.add(new JLabel("Número de pedido:"));
        txtNumero = new JTextField();
        panelFormulario.add(txtNumero);

        panelFormulario.add(new JLabel("Código del cliente:"));
        txtCliente = new JTextField();
        panelFormulario.add(txtCliente);

        panelFormulario.add(new JLabel("Fecha (yyyy-MM-dd):"));
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha);

        panelFormulario.add(new JLabel("Total:"));
        txtTotal = new JTextField();
        panelFormulario.add(txtTotal);

        JPanel panelBotones = new JPanel();

        JButton btnGuardar = new JButton("Guardar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        modeloTabla = new DefaultTableModel(
                new Object[]{
                    "Número",
                    "Cliente",
                    "Fecha",
                    "Total"
                }, 0
        );

        tablaPedidos = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaPedidos);

        JPanel panelSuperior = new JPanel(new BorderLayout());

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        setLayout(new BorderLayout());

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarPedido());

        btnBuscar.addActionListener(e -> buscarPedido());

        btnActualizar.addActionListener(e -> actualizarPedido());

        btnEliminar.addActionListener(e -> eliminarPedido());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        tablaPedidos.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && tablaPedidos.getSelectedRow() != -1) {

                int fila = tablaPedidos.getSelectedRow();

                txtNumero.setText(
                        modeloTabla.getValueAt(fila, 0).toString()
                );

                txtCliente.setText(
                        modeloTabla.getValueAt(fila, 1).toString()
                );

                txtFecha.setText(
                        modeloTabla.getValueAt(fila, 2).toString()
                );

                txtTotal.setText(
                        modeloTabla.getValueAt(fila, 3).toString()
                );
            }
        });
    }

    private void guardarPedido() {

        try {

            int numero = Integer.parseInt(txtNumero.getText());
            int cliente = Integer.parseInt(txtCliente.getText());
            LocalDate fecha = LocalDate.parse(
                    txtFecha.getText(),
                    formatoFecha
            );
            double total = Double.parseDouble(txtTotal.getText());

            Pedido pedido = new Pedido(
                    numero,
                    cliente,
                    fecha,
                    total
            );

            if (pedidoService.registrar(pedido)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pedido registrado correctamente."
                );

                cargarPedidos();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el pedido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Número, cliente y total deben ser valores numéricos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha debe tener el formato yyyy-MM-dd.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void buscarPedido() {

        try {

            int numero = Integer.parseInt(txtNumero.getText());

            Pedido pedido = pedidoService.buscar(numero);

            if (pedido != null) {

                txtCliente.setText(
                        String.valueOf(pedido.getPedi_cliente())
                );

                txtFecha.setText(
                        pedido.getPed_fecha().format(formatoFecha)
                );

                txtTotal.setText(
                        String.valueOf(pedido.getPed_total())
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Pedido no encontrado."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un número de pedido válido."
            );
        }
    }

    private void actualizarPedido() {

        try {

            int numero = Integer.parseInt(txtNumero.getText());
            int cliente = Integer.parseInt(txtCliente.getText());

            LocalDate fecha = LocalDate.parse(
                    txtFecha.getText(),
                    formatoFecha
            );

            double total = Double.parseDouble(txtTotal.getText());

            Pedido pedido = new Pedido(
                    numero,
                    cliente,
                    fecha,
                    total
            );

            if (pedidoService.actualizar(pedido)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pedido actualizado correctamente."
                );

                cargarPedidos();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el pedido."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Número, cliente y total deben ser valores numéricos."
            );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha debe tener el formato yyyy-MM-dd."
            );
        }
    }

    private void eliminarPedido() {

        try {

            int numero = Integer.parseInt(txtNumero.getText());

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de eliminar este pedido?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                if (pedidoService.eliminar(numero)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Pedido eliminado correctamente."
                    );

                    cargarPedidos();
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo eliminar el pedido."
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un número de pedido válido."
            );
        }
    }

    private void cargarPedidos() {

        modeloTabla.setRowCount(0);

        List<Pedido> pedidos = pedidoService.listar();

        for (Pedido pedido : pedidos) {

            modeloTabla.addRow(new Object[]{
                pedido.getPed_numero(),
                pedido.getPedi_cliente(),
                pedido.getPed_fecha(),
                pedido.getPed_total()
            });
        }
    }

    private void limpiarCampos() {

        txtNumero.setText("");
        txtCliente.setText("");
        txtFecha.setText("");
        txtTotal.setText("");

        tablaPedidos.clearSelection();
    }
}