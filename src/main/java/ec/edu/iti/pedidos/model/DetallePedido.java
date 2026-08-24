package ec.edu.iti.pedidos.model;

public class DetallePedido {

    private int dep_codigo;
    private int dep_pedido;
    private int dep_producto;
    private int dep_cantidad;
    private double dep_subtotal;

    public DetallePedido() {
    }

    public DetallePedido(int dep_codigo, int dep_pedido, int dep_producto,
                         int dep_cantidad, double dep_subtotal) {
        this.dep_codigo = dep_codigo;
        this.dep_pedido = dep_pedido;
        this.dep_producto = dep_producto;
        this.dep_cantidad = dep_cantidad;
        this.dep_subtotal = dep_subtotal;
    }

    public int getDep_codigo() {
        return dep_codigo;
    }

    public void setDep_codigo(int dep_codigo) {
        this.dep_codigo = dep_codigo;
    }

    public int getDep_pedido() {
        return dep_pedido;
    }

    public void setDep_pedido(int dep_pedido) {
        this.dep_pedido = dep_pedido;
    }

    public int getDep_producto() {
        return dep_producto;
    }

    public void setDep_producto(int dep_producto) {
        this.dep_producto = dep_producto;
    }

    public int getDep_cantidad() {
        return dep_cantidad;
    }

    public void setDep_cantidad(int dep_cantidad) {
        this.dep_cantidad = dep_cantidad;
    }

    public double getDep_subtotal() {
        return dep_subtotal;
    }

    public void setDep_subtotal(double dep_subtotal) {
        this.dep_subtotal = dep_subtotal;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "dep_codigo=" + dep_codigo +
                ", dep_pedido=" + dep_pedido +
                ", dep_producto=" + dep_producto +
                ", dep_cantidad=" + dep_cantidad +
                ", dep_subtotal=" + dep_subtotal +
                '}';
    }
}