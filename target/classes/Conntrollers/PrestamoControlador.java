package Conntrollers;

import DAOs.Consulta5Dao;
import DAOs.LibroDao;
import DAOs.PrestamosDao;
import Models.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.ObjectInput;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@WebServlet(name = "PrestamoControllador", value = "/Prestamos")
public class PrestamoControlador extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");

        String id = request.getParameter("id");
        JSONArray ArrayPrestamos = new JSONArray();
        JSONObject objPrestamos= new JSONObject();
        try {
            PrestamosDao prestamosDao = new PrestamosDao();
            ResultSet resultSet = prestamosDao.traerPrestamo(id);
            while (resultSet.next()){
                objPrestamos.put("resultado", "exito");
                objPrestamos.put("alumno", resultSet.getString("alumno"));
                objPrestamos.put("lirbo", resultSet.getString("idLibro"));
                objPrestamos.put("fechaPrestamo", resultSet.getString("fechaPrestamo"));
                objPrestamos.put("codigo", resultSet.getString("idPrestamo"));
                objPrestamos.put("cantidad", resultSet.getString("cantidad"));
                objPrestamos.put("fechaDevo", resultSet.getString("fechaDevolucion"));
            }
        }catch (SQLException e){
            objPrestamos.put("resultado", "error_sql_prestamos");
            objPrestamos.put("error", e.getMessage());
            objPrestamos.put("code error", e.getErrorCode());
            throw  new RuntimeException(e);
        }catch (ClassNotFoundException e){
            objPrestamos.put("resultado", "no se econtró la clase");
            objPrestamos.put("error", e.getMessage());
            throw new RuntimeException(e);
        }
        ArrayPrestamos.put(objPrestamos);
        response.getWriter().write(ArrayPrestamos.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String filtro = request.getParameter("consultar_datos");

        if(filtro == null){
            return;
        }
        switch (filtro) {

            case "insertarPrestamos":
                JSONArray arrayInsertPrestamo = new JSONArray();
                JSONObject objInsertPrestamo = new JSONObject();
                String resultadoInsertar="";

                String fechaprestamo = request.getParameter("fechaPrestamo");
                String fechadevolucion = request.getParameter("fechaDevolucion");
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechapres = null;
                Date fechadevo = null;
                try {
                    fechapres= formato.parse(fechaprestamo);
                    fechadevo= formato.parse(fechadevolucion);
                }catch (ParseException e){
                    throw new RuntimeException(e);
                }
                System.out.println("fechaprestamo: "+ fechaprestamo);
                System.out.println("fechadevolucion: "+ fechadevolucion);
                Prestamo prestamo = new Prestamo();

                try {
                    PrestamosDao pdao= new PrestamosDao();
                    prestamo.setCodigoprestamo(request.getParameter("codigoPrestamo"));
                    prestamo.setCodigolibro(new Libro(request.getParameter("codigoLibro")));
                    prestamo.setCarnetalumno(new Alumno(request.getParameter("carnetAlumno")));
                    prestamo.setCantidadprestamo(Integer.parseInt(request.getParameter("cantidadPrestamo")));
                    prestamo.setFechaprestamo(fechapres);
                    prestamo.setFechadevolucion(fechadevo);
                    resultadoInsertar= pdao.insertarPrestamo(prestamo);
                    if (resultadoInsertar == "exito"){
                        objInsertPrestamo.put("resultado", "exito");
                    }else{
                        objInsertPrestamo.put("resultado", "error");
                        objInsertPrestamo.put("resultadoInsertar", resultadoInsertar);
                    }

                }catch (SQLException e){
                    objInsertPrestamo.put("resultado", "error_sql");
                    objInsertPrestamo.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado: " + e);
                    System.out.println("Error Code error: " + e.getErrorCode());
                    System.out.println("Error Exception: " + e);
                    throw  new RuntimeException(e);

                }catch (ClassNotFoundException e){
                    objInsertPrestamo.put("resultado", "error_class");
                    objInsertPrestamo.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                arrayInsertPrestamo.put(objInsertPrestamo);
                response.getWriter().write(arrayInsertPrestamo.toString());
                break;
            case "modificarPrestamo":
                JSONArray ArrayModify = new JSONArray();
                JSONObject objModify = new JSONObject();
                String resultadoModicado = "";
                Prestamo prestamoModify = new Prestamo();
                Libro libro = new Libro();
                Alumno alumno = new Alumno();
                CategoriaLibros categoriaLibros = new CategoriaLibros();
                try {
                    String fechaprestamoM = request.getParameter("fechaPrestamo");
                    String fechadevolucionM = request.getParameter("fechaDevolucion");
                    SimpleDateFormat formatoM = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechapresM = null;
                    Date fechadevoM = null;
                    try {
                        fechapresM = formatoM.parse(fechaprestamoM);
                        fechadevoM = formatoM.parse(fechadevolucionM);
                    }catch (ParseException e){
                        throw new RuntimeException(e);
                    }
                    PrestamosDao presDao = new PrestamosDao();
                    categoriaLibros.setCodigocategoria(request.getParameter("codigoCategoria"));
                    alumno.setCarnet(request.getParameter("carnetAlumno"));
                    libro.setCodigoLibro(request.getParameter("codigoLibro"));
                    prestamoModify.setCodigoprestamo(request.getParameter("codigoPrestamo"));
                    prestamoModify.setCodigolibro(libro);
                    prestamoModify.setFechaprestamo(fechapresM);
                    prestamoModify.setCarnetalumno(alumno);
                    prestamoModify.setCantidadprestamo(Integer.parseInt(request.getParameter("cantidadPrestamo")));
                    prestamoModify.setFechadevolucion(fechadevoM);
                    resultadoModicado = presDao.modificarPrestamo(prestamoModify);
                    if (resultadoModicado == "exito"){
                        objModify.put("resultado", "exito");
                    }else {
                        objModify.put("resultado", "error");
                        objModify.put("resultado_nodificar_prestamo", resultadoModicado);
                    }
                }catch (SQLException e){
                    objModify.put("resultado", "error_sql_prestamo");
                    objModify.put("resultado", e.getMessage());
                    objModify.put("error_code", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    objModify.put("resultado", "no existe la clase");
                    objModify.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                ArrayModify.put(objModify);
                response.getWriter().write(ArrayModify.toString());
                break;
            case "mostrarPrestamos":
                int con = 0;
                JSONArray arrayPrestamos = new JSONArray();
                JSONObject jsonPrestamos  = new JSONObject();
                String html_Prestamos  = "";
                try {
                    PrestamosDao prestamos = new PrestamosDao();
                    ResultSet resultprestamos = prestamos.consultarprestamos();
                    html_Prestamos += "<table id=\"tabla_prestamos\" " +
                            "class=\"table table-bordered dt-responsive nowrap\" " +
                            "cellSpacing=0 width=100%>\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th>Código Prestamo</th>\n" +
                            "      <th>Carnet Alumno</th>\n" +
                            "      <th>Apellido</th>\n" +
                            "      <th>Nombre</th>\n" +
                            "      <th>Codigo Libro</th>\n" +
                            "      <th>Libro</th>\n" +
                            "      <th>Fecha Prestamo</th>\n" +
                            "      <th>Cantidad</th>\n" +
                            "      <th>Fecha Devolución</th>\n" +
                            "      <th>Acciones</th>\n" +
                            "    </tr>\n" +
                            "  </thead>\n" +
                            "  <tbody>";
                    while (resultprestamos.next()){

                        html_Prestamos += "<tr>";
                        html_Prestamos += "<td>" + resultprestamos.getString("codiprestamo") +"</td>";
                        html_Prestamos += "<td>" + resultprestamos.getString("carnet_alumno") +"</td>";
                        html_Prestamos += "<td>" + resultprestamos.getString("apellido") +"</td>";
                        html_Prestamos += "<td>" + resultprestamos.getString("nombre") +"</td>";
                        html_Prestamos += "<td>" + resultprestamos.getString("codilibro") +"</td>";
                        html_Prestamos += "<td>" + resultprestamos.getString("titulolibro") +"</td>";
                        html_Prestamos += "<td>" + resultprestamos.getString("fche_prestamo") +"</td>";
                        html_Prestamos += "<td>" + resultprestamos.getString("cantidadprestamo") +"</td>";
                        html_Prestamos += "<td>" + resultprestamos.getString("fecha_devolucion") +"</td>";
                        html_Prestamos += "<td>";
                        html_Prestamos += "<div class='dropdown mb-b-10'>";
                        html_Prestamos += "<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true'\n" +
                                "aria-expanded='false'>Seleccione</button>";
                        html_Prestamos += "<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>";
                        html_Prestamos += "<a class='dropdown-item btnModificar' data-idPrestamo='"+ resultprestamos.getString("codiprestamo")+"' href='javascript:void(0)'>Modificar</a>";
                        html_Prestamos += "<a class='dropdown-item btnEliminar' data-idPrestamo='"+ resultprestamos.getString("codiprestamo")+"' href='javascript:void(0)'>Eliminar</a>";
                        html_Prestamos += "</div>";
                        html_Prestamos += "</div>";
                        html_Prestamos += "</td>";
                        html_Prestamos += "</tr>";
                        con++;
                    }
                    html_Prestamos += "</tbody>\n" +
                            "\t\t</table>";
                    if (con >= 0){
                        jsonPrestamos.put("resultado","exito");
                        jsonPrestamos.put("tabla", html_Prestamos);
                        jsonPrestamos.put("cantidad", con);
                        System.out.println("cantidad es: "+con);
                    }
                }catch (SQLException e){
                    jsonPrestamos.put("resultado", "error_sql");
                    jsonPrestamos.put("error",e.getMessage());
                    jsonPrestamos.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonPrestamos.put("resultado", "class no found");
                    jsonPrestamos.put("error",e.getMessage());
                    throw new RuntimeException(e);
                }
                arrayPrestamos.put(jsonPrestamos);
                response.getWriter().write(arrayPrestamos.toString());
                break;
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        System.out.println("llego aquí");
        String id = request.getParameter("id");
        JSONArray arrayPrestamo = new JSONArray();
        JSONObject ojectPrestamo = new JSONObject();
        String resultadoEliminado = "";
        try {
            PrestamosDao prestamoDao = new PrestamosDao();
            resultadoEliminado = prestamoDao.eliminarPrestamo(id);
            if (resultadoEliminado == "exito"){
                ojectPrestamo.put("resultado", "exito");
            }else {
                ojectPrestamo.put("resultado", "error");
                ojectPrestamo.put("resultado_eliminar", resultadoEliminado);
            }
        }catch (SQLException e){
            ojectPrestamo.put("resultado", "no existe la clase");
            ojectPrestamo.put("error_code", e.getErrorCode());
            ojectPrestamo.put("error_mostrado", e.getMessage());
            throw new RuntimeException(e);
        }catch (ClassNotFoundException e){
            ojectPrestamo.put("resultado", "error_sql_prestamo");
            ojectPrestamo.put("error_mostrado", e.getMessage());
            throw new RuntimeException(e);
        }
        arrayPrestamo.put(ojectPrestamo);
        response.getWriter().write(arrayPrestamo.toString());
    }
}
