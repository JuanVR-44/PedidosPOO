package ec.edu.iti.pedidos.model;

import java.time.LocalDate;

public class Pedido {

    private int ped_numero;
    private int pedi_cliente;
    private LocalDate ped_fecha;
    private double ped_total;

    public Pedido() {
    }

    public Pedido(int ped_numero, int pedi_cliente,
                  LocalDate ped_fecha, double ped_total) {
        this.ped_numero = ped_numero;
        this.pedi_cliente = pedi_cliente;
        this.ped_fecha = ped_fecha;
        this.ped_total = ped_total;
    }

    public int getPed_numero() {
        return ped_numero;
    }

    public void setPed_numero(int ped_numero) {
        this.ped_numero = ped_numero;
    }

    public int getPedi_cliente() {
        return pedi_cliente;
    }

    public void setPedi_cliente(int pedi_cliente) {
        this.pedi_cliente = pedi_cliente;
    }

    public LocalDate getPed_fecha() {
        return ped_fecha;
    }

    public void setPed_fecha(LocalDate ped_fecha) {
        this.ped_fecha = ped_fecha;
    }

    public double getPed_total() {
        return ped_total;
    }

    public void setPed_total(double ped_total) {
        this.ped_total = ped_total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "ped_numero=" + ped_numero +
                ", pedi_cliente=" + pedi_cliente +
                ", ped_fecha=" + ped_fecha +
                ", ped_total=" + ped_total +
                '}';
    }
}