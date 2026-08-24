package ec.edu.iti.pedidos.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {

        setTitle("Sistema de Pedidos");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel(
                "SISTEMA DE PEDIDOS",
                JLabel.CENTER
        );

        JPanel panelBotones = new JPanel(
                new GridLayout(5, 1, 10, 10)
        );

        JButton btnClientes = new JButton("Clientes");
        JButton btnProductos = new JButton("Productos");
        JButton btnPedidos = new JButton("Pedidos");
        JButton btnDetalles = new JButton("Detalles de Pedido");
        JButton btnSalir = new JButton("Salir");
        btnClientes.addActionListener(e -> {
            new ClienteFrame().setVisible(true);
        });
        btnProductos.addActionListener(e -> {
            new ProductoFrame().setVisible(true);
        });
        btnPedidos.addActionListener(e -> {
            new PedidoFrame().setVisible(true);
        });
        btnDetalles.addActionListener(e -> {
            new DetallePedidoFrame().setVisible(true);
        });
        panelBotones.add(btnClientes);
        panelBotones.add(btnProductos);
        panelBotones.add(btnPedidos);
        panelBotones.add(btnDetalles);
        panelBotones.add(btnSalir);

        add(titulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}
