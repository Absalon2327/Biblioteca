package Conntrollers;

import DAOs.AlumnosDao;
import DAOs.AutoresDao;
import Models.Alumno;
import Models.Autor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AlumnoControlador", value = "/Alumnos")
public class AlumnoControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String filtro = request.getParameter("consultar_datos");
        if(filtro == null){
            return;
        }
        switch (filtro){
            case "insertarAlumnos":
                JSONArray arrayInsertAAlumno = new JSONArray();
                JSONObject objInsertAlumno = new JSONObject();
                String resultadoInsertar = "";
                String estadoInsert = null;
                String estadoVista = "Activo";
                String fechaNaci = request.getParameter("fechaNacimientoAlumno");
                String fechaIn= request.getParameter("fechaIngreso");
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = null;
                Date fechaIngreso = null;
                try {
                    fechaNacimiento = formato.parse(fechaNaci);
                    fechaIngreso = formato.parse(fechaIn);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if (request.getParameter("estadoAlumno") == estadoVista) {
                    estadoInsert = "t";
                } else{
                    estadoInsert = "f";
                }
                System.out.println("fecha: " + fechaNaci );

                System.out.println("estado Vista: " + estadoVista );
                System.out.println("estadoInsert del alumno: " + estadoInsert );
                Alumno alumno = new Alumno();
                try {
                    AlumnosDao rg = new AlumnosDao();
                    alumno.setCarnet(request.getParameter("carnetAlumno"));
                    alumno.setNombreAlumno(request.getParameter("nombreAlumno"));
                    alumno.setApellidoAlumno(request.getParameter("apellidoAlumno"));
                    alumno.setDireccion(request.getParameter("direccionAlumno"));
                    alumno.setFechaNacimientoAlumno(fechaNacimiento);
                    alumno.setFechaIngreso(fechaIngreso);
                    alumno.setGenero(request.getParameter("generoAlumno"));
                    alumno.setEstado(Boolean.valueOf(estadoInsert));
                    resultadoInsertar = rg.insertarAlumno(alumno);
                    if (resultadoInsertar == "exito"){
                        objInsertAlumno.put("resultado", "exito");
                    } else {
                        objInsertAlumno.put("resultado", "error");
                        objInsertAlumno.put("resultadoInsertar", resultadoInsertar);
                    }
                }catch (SQLException e){
                    objInsertAlumno.put("resultado", "error_sql");
                    objInsertAlumno.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado: " + e);
                    System.out.println("Error Code error: " + e.getErrorCode());
                    System.out.println("Error Exception: " + e);
                    throw  new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    objInsertAlumno.put("resultado", "error_class");
                    objInsertAlumno.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                arrayInsertAAlumno.put(objInsertAlumno);
                response.getWriter().write(arrayInsertAAlumno.toString());
                break;
            case "si_consultar_info":
                String codigoAlumno = request.getParameter("id");
                JSONArray ArrayAlumnosInfo = new JSONArray();
                JSONObject objAlumnosInfo = new JSONObject();

                try {
                    AlumnosDao alumnosDao = new AlumnosDao();
                    ResultSet resultSetAutores = alumnosDao.consultarAlumnoID(codigoAlumno);
                    while (resultSetAutores.next()){
                        objAlumnosInfo.put("resultado", "exito");
                        objAlumnosInfo.put("canetAl", resultSetAutores.getString("carnet"));
                        objAlumnosInfo.put("nombreAl", resultSetAutores.getString("nombre"));
                        objAlumnosInfo.put("apellidoAl", resultSetAutores.getString("apellido"));
                        objAlumnosInfo.put("direcAl", resultSetAutores.getString("direccion"));
                        objAlumnosInfo.put("fechaNaciAl", resultSetAutores.getString("fechanacimiento"));
                        objAlumnosInfo.put("fechaInAl", resultSetAutores.getString("fechaingreso"));
                        objAlumnosInfo.put("generoAl", resultSetAutores.getString("genero"));
                        objAlumnosInfo.put("estadoAl", resultSetAutores.getString("estado"));
                    }
                }catch (SQLException e){
                    objAlumnosInfo.put("resultado", "error_sql");
                    objAlumnosInfo.put("error", e.getMessage());
                    objAlumnosInfo.put("code error", e.getErrorCode());
                    throw  new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    objAlumnosInfo.put("resultado", "no se econtró la clase");
                    objAlumnosInfo.put("error", e.getMessage());
                    throw new RuntimeException(e);
                }
                ArrayAlumnosInfo.put(objAlumnosInfo);
                response.getWriter().write(ArrayAlumnosInfo.toString());
                break;
            case "modificarAlumno":
                JSONArray ArrayAutorModify = new JSONArray();
                JSONObject objAutorModify = new JSONObject();
                String resultadoModicado = "";
                Alumno alumnoModify = new Alumno();
                try {
                    String estadoModify = null;
                    String estadoV = request.getParameter("estadoAlumno");
                    String fechaNA = request.getParameter("fechaNacimientoAlumno");
                    String fechaInM= request.getParameter("fechaIngreso");
                    SimpleDateFormat formatoNA = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaNacimientoNA = null;
                    Date fechaIngresoM = null;
                    try {
                        fechaNacimientoNA = formatoNA.parse(fechaNA);
                        fechaIngresoM = formatoNA.parse(fechaInM);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    if (estadoV == "Activo") {
                        estadoModify = "t";
                    }else{
                        estadoModify = "f";
                    }
                    AlumnosDao alumnosDao = new AlumnosDao();
                    alumnoModify.setCarnet(request.getParameter("id"));
                    alumnoModify.setNombreAlumno(request.getParameter("nombreAlumno"));
                    alumnoModify.setApellidoAlumno(request.getParameter("apellidoAlumno"));
                    alumnoModify.setDireccion(request.getParameter("direccionAlumno"));
                    alumnoModify.setFechaNacimientoAlumno(fechaNacimientoNA);
                    alumnoModify.setFechaIngreso(fechaIngresoM);
                    alumnoModify.setGenero(request.getParameter("generoAlumno"));
                    alumnoModify.setEstado(Boolean.valueOf(estadoModify));
                    resultadoModicado = alumnosDao.modificarAlumno(alumnoModify);
                    if (resultadoModicado == "exito"){
                        objAutorModify.put("resultado", "exito");
                    }else {
                        objAutorModify.put("resultado", "error");
                        objAutorModify.put("resultado_nodificar", resultadoModicado);
                    }
                }catch (SQLException e){
                    objAutorModify.put("resultado", "error_sql");
                    objAutorModify.put("resultado", e.getMessage());
                    objAutorModify.put("error_code", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    objAutorModify.put("resultado", "no existe la clase");
                    objAutorModify.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                ArrayAutorModify.put(objAutorModify);
                response.getWriter().write(ArrayAutorModify.toString());
                break;
            case "eliminarAutor":
                JSONArray arrayAlumnoE = new JSONArray();
                JSONObject ojectAlumnoE = new JSONObject();
                String resultadoEliminado = "";
                Alumno alumnoE = new Alumno();
                try {
                    AlumnosDao alumnosDao = new AlumnosDao();
                    alumnoE.setCarnet(request.getParameter("id"));
                    resultadoEliminado = alumnosDao.eliminarAlumno(alumnoE);
                    if (resultadoEliminado == "exito"){
                        ojectAlumnoE.put("resultado", "exito");
                    }else {
                        ojectAlumnoE.put("resultado", "error");
                        ojectAlumnoE.put("resultado_eliminar", resultadoEliminado);
                    }
                }catch (SQLException e){
                    ojectAlumnoE.put("resultado", "no existe la clase");
                    ojectAlumnoE.put("error_code", e.getErrorCode());
                    ojectAlumnoE.put("error_mostrado", e.getMessage());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    ojectAlumnoE.put("resultado", "error_sql");
                    ojectAlumnoE.put("error_mostrado", e.getMessage());
                    throw new RuntimeException(e);
                }
                arrayAlumnoE.put(ojectAlumnoE);
                response.getWriter().write(arrayAlumnoE.toString());
                break;
            case "mostrarAlumnos":
                int con = 0;
                Alumno alumnos = new Alumno();
                JSONArray arrayAlumnosShow = new JSONArray();
                JSONObject objectAlumnosShow = new JSONObject();
                String html_alumnos = "";
                try {
                    AlumnosDao alumnosDao = new AlumnosDao();
                    ResultSet resultAlumnos = alumnosDao.consultarAlumnos();
                    html_alumnos += "<table id=\"tabla_alumnos\" " +
                            "class=\"table table-bordered dt-responsive nowrap\" " +
                            "cellSpacing=0 width=100%>\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th>Carnet</th>\n" +
                            "      <th>Nombre</th>\n" +
                            "      <th>Apllido</th>\n" +
                            "      <th>Dirección</th>\n" +
                            "      <th>Edad</th>\n" +
                            "      <th>Ingreso</th>\n" +
                            "      <th>Genero</th>\n" +
                            "      <th>Estado</th>\n" +
                            "      <th>Acciones</th>\n" +
                            "    </tr>\n" +
                            "  </thead>\n" +
                            "  <tbody>";
                    while (resultAlumnos.next()){
                        String edad = alumnos.calcularEdad(resultAlumnos.getString("fechanacimiento"));
                        String estado = alumnos.estadoAlumno(resultAlumnos.getBoolean("estado"));
                        html_alumnos += "<tr>";
                        html_alumnos += "<td>" + resultAlumnos.getString("carnet") +"</td>";
                        html_alumnos += "<td>" + resultAlumnos.getString("nombre") +"</td>";
                        html_alumnos += "<td>" + resultAlumnos.getString("apellido") +"</td>";
                        html_alumnos += "<td>" + resultAlumnos.getString("direccion") +"</td>";
                        html_alumnos += "<td>" + edad +"</td>";
                        html_alumnos += "<td>" + resultAlumnos.getString("fechaingreso") +"</td>";
                        html_alumnos += "<td>" + resultAlumnos.getString("genero") +"</td>";
                        html_alumnos += "<td>" + estado +"</td>";
                        html_alumnos += "<td>";
                        html_alumnos += "<div class='dropdown mb-b-10'>";
                        html_alumnos += "<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true'\n" +
                                "aria-expanded='false'>Seleccione</button>";
                        html_alumnos += "<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>";
                        html_alumnos += "<a class='dropdown-item btnModificar' data-idAlumno='"+ resultAlumnos.getString("carnet")+"' href='javascript:void(0)'>Modificar</a>";
                        html_alumnos += "<a class='dropdown-item btnEliminar' data-idAlumno='"+ resultAlumnos.getString("carnet")+"' href='javascript:void(0)'>Eliminar</a>";
                        html_alumnos += "</div>";
                        html_alumnos += "</div>";
                        html_alumnos += "</td>";
                        html_alumnos += "</tr>";
                        con++;
                    }
                    html_alumnos += "</tbody>\n" +
                            "\t\t</table>";
                    if (con >= 0){
                        objectAlumnosShow.put("resultado","exito");
                        objectAlumnosShow.put("tabla", html_alumnos);
                        objectAlumnosShow.put("cantidad", con);
                    }
                }catch (SQLException e){
                    objectAlumnosShow.put("resultado", "error_sql");
                    objectAlumnosShow.put("error",e.getMessage());
                    objectAlumnosShow.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    objectAlumnosShow.put("resultado", "class no found");
                    objectAlumnosShow.put("error",e.getMessage());
                    throw new RuntimeException(e);
                }
                arrayAlumnosShow.put(objectAlumnosShow);
                response.getWriter().write(arrayAlumnosShow.toString());
                break;
        }
    }
}
