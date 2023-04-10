package Conntrollers;

import DAOs.Consulta5Dao;
import DAOs.PrestamosDao;
import Models.Alumno;
import Models.Libro;
import Models.Prestamo;
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
}
