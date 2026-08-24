package ec.edu.iti.pedidos.model;

public class Cliente {

    private int cli_codigo;
    private String cli_cedula;
    private String cli_nombre;
    private String cli_direccion;
    private String cli_telefono;

    public Cliente() {
    }

    public Cliente(int cli_codigo, String cli_cedula, String cli_nombre,
                   String cli_direccion, String cli_telefono) {
        this.cli_codigo = cli_codigo;
        this.cli_cedula = cli_cedula;
        this.cli_nombre = cli_nombre;
        this.cli_direccion = cli_direccion;
        this.cli_telefono = cli_telefono;
    }

    public int getCli_codigo() {
        return cli_codigo;
    }

    public void setCli_codigo(int cli_codigo) {
        this.cli_codigo = cli_codigo;
    }

    public String getCli_cedula() {
        return cli_cedula;
    }

    public void setCli_cedula(String cli_cedula) {
        this.cli_cedula = cli_cedula;
    }

    public String getCli_nombre() {
        return cli_nombre;
    }

    public void setCli_nombre(String cli_nombre) {
        this.cli_nombre = cli_nombre;
    }

    public String getCli_direccion() {
        return cli_direccion;
    }

    public void setCli_direccion(String cli_direccion) {
        this.cli_direccion = cli_direccion;
    }

    public String getCli_telefono() {
        return cli_telefono;
    }

    public void setCli_telefono(String cli_telefono) {
        this.cli_telefono = cli_telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cli_codigo=" + cli_codigo +
                ", cli_cedula='" + cli_cedula + '\'' +
                ", cli_nombre='" + cli_nombre + '\'' +
                ", cli_direccion='" + cli_direccion + '\'' +
                ", cli_telefono='" + cli_telefono + '\'' +
                '}';
    }
}