package ec.edu.iti.pedidos.main;

import ec.edu.iti.pedidos.model.Producto;
import ec.edu.iti.pedidos.service.ProductoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductoFrame extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    private ProductoService productoService;

    public ProductoFrame() {

        productoService = new ProductoService();

        setTitle("Gestión de Productos");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
        cargarProductos();
    }

    private void crearInterfaz() {

        JPanel panelFormulario = new JPanel(
                new GridLayout(3, 2, 10, 10)
        );

        panelFormulario.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        panelFormulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

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
                    "Nombre",
                    "Precio"
                }, 0
        );

        tablaProductos = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaProductos);

        JPanel panelSuperior = new JPanel(new BorderLayout());

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        setLayout(new BorderLayout());

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarProducto());

        btnBuscar.addActionListener(e -> buscarProducto());

        btnActualizar.addActionListener(e -> actualizarProducto());

        btnEliminar.addActionListener(e -> eliminarProducto());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        tablaProductos.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && tablaProductos.getSelectedRow() != -1) {

                int fila = tablaProductos.getSelectedRow();

                txtCodigo.setText(
                        modeloTabla.getValueAt(fila, 0).toString()
                );

                txtNombre.setText(
                        modeloTabla.getValueAt(fila, 1).toString()
                );

                txtPrecio.setText(
                        modeloTabla.getValueAt(fila, 2).toString()
                );
            }
        });
    }

    private void guardarProducto() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            double precio = Double.parseDouble(txtPrecio.getText());

            Producto producto = new Producto(
                    codigo,
                    txtNombre.getText(),
                    precio
            );

            if (productoService.registrar(producto)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Producto registrado correctamente."
                );

                cargarProductos();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el producto.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El código y el precio deben ser números.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void buscarProducto() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            Producto producto = productoService.buscar(codigo);

            if (producto != null) {

                txtNombre.setText(producto.getPro_nombre());
                txtPrecio.setText(
                        String.valueOf(producto.getPro_precio())
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Producto no encontrado."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un código válido."
            );
        }
    }

    private void actualizarProducto() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            double precio = Double.parseDouble(txtPrecio.getText());

            Producto producto = new Producto(
                    codigo,
                    txtNombre.getText(),
                    precio
            );

            if (productoService.actualizar(producto)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Producto actualizado correctamente."
                );

                cargarProductos();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el producto."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El código y el precio deben ser números."
            );
        }
    }

    private void eliminarProducto() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de eliminar este producto?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                if (productoService.eliminar(codigo)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto eliminado correctamente."
                    );

                    cargarProductos();
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo eliminar el producto."
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

    private void cargarProductos() {

        modeloTabla.setRowCount(0);

        List<Producto> productos = productoService.listar();

        for (Producto producto : productos) {

            modeloTabla.addRow(new Object[]{
                producto.getPro_codigo(),
                producto.getPro_nombre(),
                producto.getPro_precio()
            });
        }
    }

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");

        tablaProductos.clearSelection();
    }
}