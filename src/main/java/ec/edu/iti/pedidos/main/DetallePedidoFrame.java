package ec.edu.iti.pedidos.main;

import ec.edu.iti.pedidos.model.DetallePedido;
import ec.edu.iti.pedidos.service.DetallePedidoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DetallePedidoFrame extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtPedido;
    private JTextField txtProducto;
    private JTextField txtCantidad;
    private JTextField txtSubtotal;

    private JTable tablaDetalles;
    private DefaultTableModel modeloTabla;

    private DetallePedidoService detalleService;

    public DetallePedidoFrame() {

        detalleService = new DetallePedidoService();

        setTitle("Gestión de Detalles de Pedido");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
        cargarDetalles();
    }

    private void crearInterfaz() {

        JPanel panelFormulario = new JPanel(
                new GridLayout(5, 2, 10, 10)
        );

        panelFormulario.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        panelFormulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Pedido:"));
        txtPedido = new JTextField();
        panelFormulario.add(txtPedido);

        panelFormulario.add(new JLabel("Producto:"));
        txtProducto = new JTextField();
        panelFormulario.add(txtProducto);

        panelFormulario.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelFormulario.add(txtCantidad);

        panelFormulario.add(new JLabel("Subtotal:"));
        txtSubtotal = new JTextField();
        panelFormulario.add(txtSubtotal);

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
                    "Código",
                    "Pedido",
                    "Producto",
                    "Cantidad",
                    "Subtotal"
                }, 0
        );

        tablaDetalles = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaDetalles);

        JPanel panelSuperior = new JPanel(new BorderLayout());

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        setLayout(new BorderLayout());

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarDetalle());

        btnBuscar.addActionListener(e -> buscarDetalle());

        btnActualizar.addActionListener(e -> actualizarDetalle());

        btnEliminar.addActionListener(e -> eliminarDetalle());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        tablaDetalles.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && tablaDetalles.getSelectedRow() != -1) {

                int fila = tablaDetalles.getSelectedRow();

                txtCodigo.setText(
                        modeloTabla.getValueAt(fila, 0).toString()
                );

                txtPedido.setText(
                        modeloTabla.getValueAt(fila, 1).toString()
                );

                txtProducto.setText(
                        modeloTabla.getValueAt(fila, 2).toString()
                );

                txtCantidad.setText(
                        modeloTabla.getValueAt(fila, 3).toString()
                );

                txtSubtotal.setText(
                        modeloTabla.getValueAt(fila, 4).toString()
                );
            }
        });
    }

    private void guardarDetalle() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());
            int pedido = Integer.parseInt(txtPedido.getText());
            int producto = Integer.parseInt(txtProducto.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double subtotal = Double.parseDouble(txtSubtotal.getText());

            DetallePedido detalle = new DetallePedido(
                    codigo,
                    pedido,
                    producto,
                    cantidad,
                    subtotal
            );

            if (detalleService.registrar(detalle)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Detalle registrado correctamente."
                );

                cargarDetalles();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el detalle.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos numéricos deben contener números.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void buscarDetalle() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            DetallePedido detalle = detalleService.buscar(codigo);

            if (detalle != null) {

                txtPedido.setText(
                        String.valueOf(detalle.getDep_pedido())
                );

                txtProducto.setText(
                        String.valueOf(detalle.getDep_producto())
                );

                txtCantidad.setText(
                        String.valueOf(detalle.getDep_cantidad())
                );

                txtSubtotal.setText(
                        String.valueOf(detalle.getDep_subtotal())
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Detalle no encontrado."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un código válido."
            );
        }
    }

    private void actualizarDetalle() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());
            int pedido = Integer.parseInt(txtPedido.getText());
            int producto = Integer.parseInt(txtProducto.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double subtotal = Double.parseDouble(txtSubtotal.getText());

            DetallePedido detalle = new DetallePedido(
                    codigo,
                    pedido,
                    producto,
                    cantidad,
                    subtotal
            );

            if (detalleService.actualizar(detalle)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Detalle actualizado correctamente."
                );

                cargarDetalles();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el detalle."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos numéricos deben contener números."
            );
        }
    }

    private void eliminarDetalle() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de eliminar este detalle?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                if (detalleService.eliminar(codigo)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Detalle eliminado correctamente."
                    );

                    cargarDetalles();
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo eliminar el detalle."
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

    private void cargarDetalles() {

        modeloTabla.setRowCount(0);

        List<DetallePedido> detalles = detalleService.listar();

        for (DetallePedido detalle : detalles) {

            modeloTabla.addRow(new Object[]{
                detalle.getDep_codigo(),
                detalle.getDep_pedido(),
                detalle.getDep_producto(),
                detalle.getDep_cantidad(),
                detalle.getDep_subtotal()
            });
        }
    }

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtPedido.setText("");
        txtProducto.setText("");
        txtCantidad.setText("");
        txtSubtotal.setText("");

        tablaDetalles.clearSelection();
    }
}