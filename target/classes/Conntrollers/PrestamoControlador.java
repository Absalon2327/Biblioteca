package Conntrollers;

import DAOs.PrestamosDao;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
