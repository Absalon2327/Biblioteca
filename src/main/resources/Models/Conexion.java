package Models;

import java.sql.*;

public class Conexion {
    public PreparedStatement ps;
    public ResultSet rs;
    public String SQL;
    public Connection con = null;

    public Connection abrirConexion() throws  ClassNotFoundException, SQLException{
        String url = "jdbc:postgresql://localhost:5432/db_libros";
        String user = "postgres";
        String password = "root";
        try {
            System.out.println("Conexion exitosa");
            return con = DriverManager.getConnection(url, user, password);
        }catch (SQLException e){
            System.out.println("Error al conectarse a la BD "+e.getMessage() + " Error code: " + e.getErrorCode());
            throw new SQLException(String.valueOf(e.getMessage()));
        }
    }
}
