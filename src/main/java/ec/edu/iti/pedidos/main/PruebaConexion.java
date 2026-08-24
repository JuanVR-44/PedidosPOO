package ec.edu.iti.pedidos.main;

import ec.edu.iti.pedidos.dao.Conexion;
import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {
            System.out.println("PROYECTO CONECTADO CORRECTAMENTE A LA BASE DE DATOS.");
        } else {
            System.out.println("NO SE PUDO CONECTAR.");
        }
    }
}