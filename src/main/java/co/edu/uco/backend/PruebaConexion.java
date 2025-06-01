package co.edu.uco.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/DOODB";
        String usuario = "postgres";
        String clave = "3104136215apa";

        try (Connection conexion = DriverManager.getConnection(url, usuario, clave)) {
            System.out.println("✅ Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a la base de datos");
            e.printStackTrace();
        }
    }
}
