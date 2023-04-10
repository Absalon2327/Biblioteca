package DAOs;

import Models.Conexion;
import Models.Libro;

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
            sql = "SELECT * FROM vistalibros";
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

    public ResultSet llenarSelectCategoria(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "select * from llenarcomboCategoria";
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

    public ResultSet llenarSelectAutor(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "select * from llenarcomboAutor";
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

    // INICIA METODO PARA INSERTAR LIBROS   MDPJ
    public String insertarLibro(Libro libro) throws SQLException, ClassNotFoundException{
        Conexion cone= new Conexion();
        con= cone.abrirConexion();
        String resultado="";

        int resultadoInsertar=0;
        try {
            String sql = "exec insertarLibro ?, ?, ?, ?, ?, ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, libro.getCodigoLibro());
            st.setString(2, libro.getTituloLibro());
            st.setInt(3, libro.getExistencia());
            st.setString(4, libro.getCodigoCategoria().getCodigocategoria());
            st.setDouble(5, libro.getPrecio());
            st.setString(6, libro.getCodigoAutor().getCodigoAutor());
            resultadoInsertar = st.executeUpdate();
            if(resultadoInsertar>0){
                resultado="exito";
            }else{
                resultado="error insertando libros";
            }
        }catch (SQLException e){
            resultado="error_exceptioon";
            System.out.print("Error al insertar"+ e);
            System.out.println("El codigo error al insertar"+ e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }
    // TERMINA METODO PARA INSERTAR LIBROS   MDPJ

}
