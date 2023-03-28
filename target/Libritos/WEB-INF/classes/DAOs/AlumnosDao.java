package DAOs;

import Models.Alumno;
import Models.Autor;
import Models.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnosDao {
    private Connection con;

    public AlumnosDao() throws SQLException, ClassNotFoundException {
        Conexion conexion = new Conexion();
        this.con = conexion.abrirConexion();
    }

    public ResultSet consultarAlumnos(){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM tb_alumno";
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

    public String insertarAlumno(Alumno alumno) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con = cone.abrirConexion();
        String resultado = "";

        int resultadoInsertar = 0;
        java.sql.Date fechaNacimiento = new java.sql.Date(alumno.getFechaNacimientoAlumno().getTime());
        java.sql.Date fechaIngreso = new java.sql.Date(alumno.getFechaIngreso().getTime());
        System.out.println("fechaNacimiento: " + fechaNacimiento);
        try {
            String sql = "INSERT INTO tb_alumno(carnet, nombre, apellido, direccion, fechanacimiento, fechaingreso, genero, estado)" +
                    "values(?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, alumno.getCarnet());
            st.setString(2, alumno.getNombreAlumno());
            st.setString(3, alumno.getApellidoAlumno());
            st.setString(4, alumno.getDireccion());
            st.setDate(5, fechaNacimiento);
            st.setDate(6, fechaIngreso);
            st.setString(7, alumno.getGenero());
            st.setBoolean(8, alumno.getEstado());
            resultadoInsertar = st.executeUpdate();
            if (resultadoInsertar > 0){
                resultado = "exito";
            } else {
                resultado = "error insertando alumnos";
            }
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al insertar: " + e);
            System.out.println("El código error al insertar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public ResultSet consultarAlumnoID( String carnet){
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            con = cone.abrirConexion();
            String sql = "";
            sql = "SELECT * FROM tb_alumno WHERE carnet = '" + carnet + "'";
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
    public String modificarAlumno(Alumno alumno) throws SQLException, ClassNotFoundException{
        Conexion cone = new Conexion();
        con = cone.abrirConexion();
        String resultado = "";
        /** Formateo la fecha una tipo SQL*/
        java.sql.Date fechaNacimiento = new java.sql.Date(alumno.getFechaNacimientoAlumno().getTime());

        java.sql.Date fechaIngreso = new java.sql.Date(alumno.getFechaIngreso().getTime());

        int resultadoModificar = 0;
        try {
            String sql = "UPDATE tb_alumno SET nombre = ?, apellido = ?, direccion = ?, fechanacimiento = ?," +
                    "fechaingreso = ?, genero = ?, estado = ? WHERE carnet = '" + alumno.getCarnet() +"'";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, alumno.getNombreAlumno());
            st.setString(2, alumno.getApellidoAlumno());
            st.setString(3, alumno.getDireccion());
            st.setDate(4, fechaNacimiento);
            st.setDate(5, fechaIngreso);
            st.setString(6, alumno.getGenero());
            st.setBoolean(7, alumno.getEstado());
            resultadoModificar = st.executeUpdate();
            if (resultadoModificar > 0){
                resultado = "exito";
            } else {
                resultado = "error modificando el Alumno" + resultadoModificar;
            }
        }catch (SQLException e){
            resultado = "error_exception";
            System.out.println("Error al modificar: " + e);
            System.out.println("El código error al modificar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }
    public String eliminarAlumno(Alumno alumno) throws SQLException, ClassNotFoundException{

        String resultado = "";
        int eliminado = 0;
        try {
            con.setAutoCommit(false);
            String sql = "DELETE FROM tb_alumno WHERE carnet = '" + alumno.getCarnet() +"'";
            System.out.println("codigo de la categoria: " + alumno.getCarnet());
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
}
