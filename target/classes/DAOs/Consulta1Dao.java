package DAOs;

import Models.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consulta1Dao {
    private Connection con;

    public Consulta1Dao() throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        this.con = cone.abrirConexion();
    }

    public ResultSet consultarConsulta(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM vistaconsulta1";
            System.out.println("El SQL estados: " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
