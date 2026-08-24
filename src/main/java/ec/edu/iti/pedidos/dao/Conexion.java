package ec.edu.iti.pedidos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CONTRASENA
            );

            System.out.println("Conexión exitosa a PostgreSQL.");

        } catch (SQLException e) {
            System.out.println("Error al conectar con PostgreSQL.");
            System.out.println(e.getMessage());
        }

        return conexion;
    }
}