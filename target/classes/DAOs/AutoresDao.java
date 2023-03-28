package DAOs;

import Models.Autor;
import Models.CategoriaLibros;
import Models.Conexion;

import java.sql.*;

public class AutoresDao {
    private Connection con;

    public AutoresDao() throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        this.con = cone.abrirConexion();
    }

    public String insertarAutor(Autor autor) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con = cone.abrirConexion();
        String resultado = "";

        int resultadoInsertar = 0;
        java.sql.Date fechaNacimiento = new java.sql.Date(autor.getFechaNacimientoAutor().getTime());
        System.out.println("fechaNacimiento: " + fechaNacimiento);
        try {
            String sql = "INSERT INTO tb_autor(codigoautor, nombreautor, apellidoautor, fechanacimientoautor, nacionalidad)values(?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, autor.getCodigoAutor());
            st.setString(2, autor.getNombreAutor());
            st.setString(3, autor.getApellidoAutor());
            st.setDate(4, fechaNacimiento);
            st.setString(5, autor.getNacionalidad());
            resultadoInsertar = st.executeUpdate();
            if (resultadoInsertar > 0){
                resultado = "exito";
            } else {
                resultado = "error insertando categoría";
            }
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al insertar: " + e);
            System.out.println("El código error al insertar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public ResultSet consultarAutorID( String codigoAutor){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM tb_autor WHERE codigoautor = '" + codigoAutor + "'";
            System.out.println("El SQL estados: " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("ps: " + resultSet);
        return resultSet;
    }

    public String modificarAutor(Autor autor) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con = cone.abrirConexion();
        String resultado = "";
        /** Formateo la fecha una tipo SQL*/
        java.sql.Date fechaNacimiento = new java.sql.Date(autor.getFechaNacimientoAutor().getTime());

        int resultadoModificar = 0;
        try {
            String sql = "UPDATE tb_autor SET nombreautor = ?,apellidoautor = ?, fechanacimientoautor = ?," +
                    "nacionalidad = ? WHERE codigoautor = '" + autor.getCodigoAutor() +"'";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, autor.getNombreAutor());
            st.setString(2, autor.getApellidoAutor());
            st.setDate(3, fechaNacimiento);
            st.setString(4, autor.getNacionalidad());
            resultadoModificar = st.executeUpdate();
            if (resultadoModificar > 0){
                resultado = "exito";
            } else {
                resultado = "error modificando categoría";
            }
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al modificar: " + e);
            System.out.println("El código error al modificar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public String eliminarAutor(Autor autor) throws SQLException, ClassNotFoundException{

        String resultado = "";
        int eliminado = 0;
        try {
            con.setAutoCommit(false);
            String sql = "DELETE FROM tb_autor WHERE codigoautor = '" + autor.getCodigoAutor() +"'";
            System.out.println("codigo de la categoria: " + autor.getCodigoAutor());
            PreparedStatement st;
            st = con.prepareStatement(sql);
            st.executeUpdate();
            resultado = "exito";
            con.commit();
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al modificar: " + e);
            System.out.println("El código error al modificar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public ResultSet consultarAutores(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM tb_autor";
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
