package DAOs;

import Models.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibroDao {
    private Connection con;

    public LibroDao() throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        this.con = cone.abrirConexion();
    }

    public ResultSet consultarLibros(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT l.[codi libro] as codigolibro, l.titulolibro , l.existencia , c.[nombrecate ria] as nombrecategoria, l.precio, a.nombreautor FROM db_libros.dbo.tb_libro as l inner join db_libros.dbo.tb_autor as a on a.[codi autor] = l.[codi autor] inner join db_libros.dbo.[tb_cate ria] as c on c.[codi cate ria] = l.[codi cate ria]";
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
