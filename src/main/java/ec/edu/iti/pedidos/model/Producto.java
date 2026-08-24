package ec.edu.iti.pedidos.model;

public class Producto {

    private int pro_codigo;
    private String pro_nombre;
    private double pro_precio;

    public Producto() {
    }

    public Producto(int pro_codigo, String pro_nombre, double pro_precio) {
        this.pro_codigo = pro_codigo;
        this.pro_nombre = pro_nombre;
        this.pro_precio = pro_precio;
    }

    public int getPro_codigo() {
        return pro_codigo;
    }

    public void setPro_codigo(int pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    public String getPro_nombre() {
        return pro_nombre;
    }

    public void setPro_nombre(String pro_nombre) {
        this.pro_nombre = pro_nombre;
    }

    public double getPro_precio() {
        return pro_precio;
    }

    public void setPro_precio(double pro_precio) {
        this.pro_precio = pro_precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "pro_codigo=" + pro_codigo +
                ", pro_nombre='" + pro_nombre + '\'' +
                ", pro_precio=" + pro_precio +
                '}';
    }
}