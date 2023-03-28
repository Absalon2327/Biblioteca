package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Obtener_CategoriaLibros {

    private Connection con;

    public Obtener_CategoriaLibros() throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        this.con = cone.abrirConexion();
    }

    public String insertarCategoria(CategoriaLibros catLibr) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con = cone.abrirConexion();
        String resultado = "";

        int resultadoInsertar = 0;
        int resultadoInsertarCategoria = 0;
        try {
            String sql = "INSERT INTO tb_categoria(codigocategoria, nombrecategoria)values(?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, catLibr.getCodigocategoria());
            st.setString(2, catLibr.getNombrecategoria());
            resultadoInsertar = st.executeUpdate();
            if (resultadoInsertar > 0){
                resultado = "Exito";
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
    public String modificarCategoria(CategoriaLibros catLibr) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con = cone.abrirConexion();
        String resultado = "";

        int resultadoModificar = 0;
        try {
            String sql = "UPDATE tb_categoria SET nombrecategoria = ? WHERE codigocategoria = '" + catLibr.getCodigocategoria() +"'";
            System.out.println("codigo de la categoria: " + catLibr.getCodigocategoria());
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, catLibr.getNombrecategoria());
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

    public String eliminarCategoria(CategoriaLibros catLibr) throws SQLException, ClassNotFoundException{

        String resultado = "";
        int eliminado = 0;
        try {
            con.setAutoCommit(false);
            String sql = "DELETE FROM tb_categoria WHERE codigocategoria = '" + catLibr.getCodigocategoria() +"'";
            System.out.println("codigo de la categoria: " + catLibr.getCodigocategoria());
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

    public ResultSet consultarCategorias(Integer estado, String quien){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM tb_categoria";
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
    public ResultSet consultarCategoriasID( String codigocategoria){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM tb_categoria WHERE codigocategoria = '" + codigocategoria + "'";
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

}
