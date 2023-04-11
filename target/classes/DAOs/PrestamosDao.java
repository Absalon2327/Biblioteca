package DAOs;

import Models.Conexion;
import Models.Libro;
import Models.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrestamosDao {
    private Connection con;

    public PrestamosDao() throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        this.con = cone.abrirConexion();
    }

    public ResultSet consultarprestamos(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM vistaprestamos";
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

    ///CODIGO PARA LLENAR EL COMBO DE LIBRO
    public ResultSet listaprestamoSelect(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "select* from llenarcomboLibro";
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

    public ResultSet listaprestamoSelectAlumno(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "select * from llenarcomboAlumno";
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

    public String insertarPrestamo(Prestamo prestamo) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con= cone.abrirConexion();
        String resultado="";

        int resultadoInsertar=0;
        java.sql.Date fechaprestamo = new java.sql.Date(prestamo.getFechaprestamo().getTime());
        java.sql.Date fechadevolucion = new java.sql.Date(prestamo.getFechadevolucion().getTime());

        try {
            String sql = "exec insertarPrestamoalumno ?, ?, ?, ?, ?, ?";
            PreparedStatement st= con.prepareStatement(sql);
            st.setString(1, prestamo.getCarnetalumno().getCarnet());
            st.setString(2, prestamo.getCodigolibro().getCodigoLibro());
            st.setDate(3, fechaprestamo);
            st.setString(4, prestamo.getCodigoprestamo());
            st.setInt(5, prestamo.getCantidadprestamo());
            st.setDate(6, fechadevolucion);
            resultadoInsertar=st.executeUpdate();
            if(resultadoInsertar>0){
                resultado="exito";
            }else{
                resultado="error registrando prestamo";
            }
        }catch (SQLException e){
            resultado="error_exception";
            System.out.println("error al insertar"+ e);
            System.out.println("El codigo da error al insertar"+ e.getErrorCode());
            e.printStackTrace();

        }
        return resultado;
    }

    public ResultSet traerPrestamo(String id){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "EXEC traerPrestamo '"+ id +"'";
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
    public String modificarPrestamo(Prestamo prestamo) throws SQLException, ClassNotFoundException{
        Conexion cone= new Conexion();
        con= cone.abrirConexion();
        String resultado="";

        int resultadoModificar=0;
        try {
            java.sql.Date fechaPrestamo = new java.sql.Date(prestamo.getFechaprestamo().getTime());
            java.sql.Date fechaDevolucion = new java.sql.Date(prestamo.getFechadevolucion().getTime());
            String sql = "exec actualizartablaPrestamoalumno ?, ?, ?, ?, ?, ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, prestamo.getCarnetalumno().getCarnet());
            st.setString(2, prestamo.getCodigolibro().getCodigoLibro());
            st.setDate(3, fechaPrestamo);
            st.setInt(4, prestamo.getCantidadprestamo());
            st.setDate(5, fechaDevolucion);
            st.setString(6, prestamo.getCodigoprestamo());
            resultadoModificar = st.executeUpdate();
            if(resultadoModificar>0){
                resultado="exito";
            }else{
                resultado="error modificando libros";
            }
        }catch (SQLException e){
            resultado="error_exceptioon";
            System.out.print("Error al modificar "+ e);
            System.out.println("El codigo error al modificar"+ e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public String eliminarPrestamo(String id) throws SQLException, ClassNotFoundException{

        String resultado = "";
        int eliminado = 0;
        try {
            con.setAutoCommit(false);
            String sql = "EXEC eliminarPrestamoalumno '" + id +"'";
            PreparedStatement st;
            st = con.prepareStatement(sql);
            st.executeUpdate();
            resultado = "exito";
            con.commit();
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al Eliminar el libro: " + e);
            System.out.println("El código error al Eliminar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }
}
