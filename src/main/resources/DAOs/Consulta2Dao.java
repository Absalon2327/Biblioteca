package DAOs;

import Models.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consulta2Dao {
    private Connection con;

    public Consulta2Dao() throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        this.con=cone.abrirConexion();
    }

    public ResultSet obtenerFechas(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con= cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM vistafechasprestamo";
            System.out.println("El SQL estados" + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();

        }
        return resultSet;
    }

    public ResultSet consultar_datosPrestamos(String quien){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            String el_where_id = quien;
            sql = "SELECT * FROM funcionconsulta2(" + el_where_id +")";
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
