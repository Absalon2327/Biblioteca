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
            sql = "SELECT l.codigolibro, l.titulolibro, l.existencia, c.nombrecategoria, l.precio, a.nombreautor FROM tb_libro as l \n" +
                    "inner join tb_autor as a on a.codigoautor = l.codigoautor \n" +
                    "inner join tb_categoria as c on c.codigocategoria = l.codigocategoria";
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
