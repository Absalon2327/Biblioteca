package DAOs;

import Models.Alumno;
import Models.Conexion;
import Models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDao {
    private Connection con;

    public UsuariosDao() throws SQLException, ClassNotFoundException{
        Conexion conexion = new Conexion();
        this.con = conexion.abrirConexion();
    }
    public ResultSet consultarUsuarios(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM vistausuarios";
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
    public ResultSet consultarUsuariosID(String usuario){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "EXEC usuraioEspecifico '" + usuario + "'";
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

    public ResultSet obtenerCredenciales(String usuario){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "EXEC obtenerCredenciales '" + usuario + "'";
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
    public String insertarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con = cone.abrirConexion();
        String resultado = "";

        int resultadoInsertar = 0;
        try {
            String sql = "EXEC insertarUsuarios ?,?,?,?,?,?,?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, usuario.getIdUsuario());
            st.setString(2, usuario.getNombreUsuario());
            st.setString(3, usuario.getApellidoUsuario());
            st.setString(4, usuario.getUsuario());
            st.setString(5, usuario.getContra());
            st.setString(6, usuario.getNivel());
            st.setString(7, usuario.getEstadoUsuario());
            resultadoInsertar = st.executeUpdate();
            if (resultadoInsertar > 0){
                resultado = "exito";
            } else {
                resultado = "error insertando usuarios";
            }
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al insertar: " + e);
            System.out.println("El código error al insertar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public String modificarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con = cone.abrirConexion();
        String resultado = "";
        int resultadoModificar = 0;
        try {

            String sql = "EXEC editarUsuarios ?,?,?,?,?,?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, usuario.getIdUsuario());
            st.setString(2, usuario.getNombreUsuario());
            st.setString(3, usuario.getApellidoUsuario());
            st.setString(4, usuario.getUsuario());
            st.setString(5, usuario.getNivel());
            st.setString(6, usuario.getEstadoUsuario());
            resultadoModificar = st.executeUpdate();
            if (resultadoModificar > 0){
                resultado = "exito";
            } else {
                resultado = "error modificando el Usuario" + resultadoModificar;
            }
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al modificar: " + e);
            System.out.println("El código error al modificar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }
    public String modificarContrasenia(Usuario usuario) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con = cone.abrirConexion();
        String resultado = "";
        int resultadoModificar = 0;
        try {

            String sql = "EXEC editContraUser ?,?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, usuario.getIdUsuario());
            st.setString(2, usuario.getContra());
            resultadoModificar = st.executeUpdate();
            if (resultadoModificar > 0){
                resultado = "exito";
            } else {
                resultado = "error modificando la contraseña del Usuario" + resultadoModificar;
            }
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al modificar contraseña: " + e);
            System.out.println("El código error al modificar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public String eliminarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{

        String resultado = "";
        int eliminado = 0;
        try {
            con.setAutoCommit(false);
            String sql = "EXEC eliminarUsuarios '" + usuario.getIdUsuario() +"'";
            PreparedStatement st;
            st = con.prepareStatement(sql);
            st.executeUpdate();
            resultado = "exito";
            con.commit();
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al Eliminar el usuario: " + e);
            System.out.println("El código error al Eliminar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }
}
