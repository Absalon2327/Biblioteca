package DAOs;

import Models.Conexion;
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
}
