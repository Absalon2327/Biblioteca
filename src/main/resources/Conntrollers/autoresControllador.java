package Conntrollers;

import DAOs.AutoresDao;
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

@WebServlet(name = "autoresControllador", value = "/autores")
public class autoresControllador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filtro = request.getParameter("consultar_datos");
        if(filtro == null){
            return;
        }
        switch (filtro){
            case "insertarAutores":
                JSONArray arrayInsertAutor = new JSONArray();
                JSONObject jsonInsertAutor = new JSONObject();
                String resultadoInsertar = "";
                String fecha = request.getParameter("fechaNacimiento");
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = null;
                try {
                    fechaNacimiento = formato.parse(fecha);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("fecha: " + fecha );
                Autor autor = new Autor();
                try {
                    AutoresDao rg = new AutoresDao();
                    autor.setCodigoAutor(request.getParameter("codigoAutor"));
                    autor.setNombreAutor(request.getParameter("nombreAutor"));
                    autor.setApellidoAutor(request.getParameter("apellidoAutor"));
                    autor.setFechaNacimientoAutor(fechaNacimiento);
                    autor.setNacionalidad(request.getParameter("nacionalidadAutor"));
                    resultadoInsertar = rg.insertarAutor(autor);
                    if (resultadoInsertar == "exito"){
                        jsonInsertAutor.put("resultado", "exito");
                    } else {
                        jsonInsertAutor.put("resultado", "error");
                        jsonInsertAutor.put("resultadoInsertar", resultadoInsertar);
                    }
                }catch (SQLException e){
                    jsonInsertAutor.put("resultado", "error_sql");
                    jsonInsertAutor.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado: " + e);
                    System.out.println("Error Code error: " + e.getErrorCode());
                    System.out.println("Error Exception: " + e);
                    throw  new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonInsertAutor.put("resultado", "error_class");
                    jsonInsertAutor.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                arrayInsertAutor.put(jsonInsertAutor);
                response.getWriter().write(arrayInsertAutor.toString());
                break;
            case "si_consultar_info":
                String codigoAutor = request.getParameter("id");
                JSONArray jsonArrayAutores = new JSONArray();
                JSONObject jsonObjectAutores = new JSONObject();

                try {
                    AutoresDao autoresDao = new AutoresDao();
                    ResultSet resultSetAutores = autoresDao.consultarAutorID(codigoAutor);
                    while (resultSetAutores.next()){
                        jsonObjectAutores.put("resultado", "exito");
                        jsonObjectAutores.put("idAutor", resultSetAutores.getString("codigoautor"));
                        jsonObjectAutores.put("nombreA", resultSetAutores.getString("nombreautor"));
                        jsonObjectAutores.put("apellidoA", resultSetAutores.getString("apellidoautor"));
                        jsonObjectAutores.put("fechaNaci", resultSetAutores.getString("fechanacimientoautor"));
                        jsonObjectAutores.put("nacional", resultSetAutores.getString("nacionalidad"));
                    }
                }catch (SQLException e){
                    jsonObjectAutores.put("resultado", "error_sql");
                    jsonObjectAutores.put("error", e.getMessage());
                    jsonObjectAutores.put("code error", e.getErrorCode());
                    throw  new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonObjectAutores.put("resultado", "no se econtró la clase");
                    jsonObjectAutores.put("error", e.getMessage());
                    throw new RuntimeException(e);
                }
                jsonArrayAutores.put(jsonObjectAutores);
                response.getWriter().write(jsonArrayAutores.toString());
                break;
            case "modificarAutor":
                JSONArray jsonArrayAutorM = new JSONArray();
                JSONObject jsonObjectAutorM = new JSONObject();
                String resultadoModicado = "";
                Autor autor2 = new Autor();
                try {
                    String fechaNA = request.getParameter("fechaNacimiento");
                    SimpleDateFormat formatoNA = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaNacimientoNA = null;
                    try {
                        fechaNacimientoNA = formatoNA.parse(fechaNA);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    AutoresDao autoresDao = new AutoresDao();
                    autor2.setCodigoAutor(request.getParameter("codigoAutor"));
                    autor2.setNombreAutor(request.getParameter("nombreAutor"));
                    autor2.setApellidoAutor(request.getParameter("apellidoAutor"));
                    autor2.setFechaNacimientoAutor(fechaNacimientoNA);
                    autor2.setNacionalidad(request.getParameter("nacionalidadAutor"));
                    resultadoModicado = autoresDao.modificarAutor(autor2);
                    if (resultadoModicado == "exito"){
                        jsonObjectAutorM.put("resultado", "exito");
                    }else {
                        jsonObjectAutorM.put("resultado", "error");
                        jsonObjectAutorM.put("resultado_nodificar", resultadoModicado);
                    }
                }catch (SQLException e){
                    jsonObjectAutorM.put("resultado", "error_sql");
                    jsonObjectAutorM.put("resultado", e.getMessage());
                    jsonObjectAutorM.put("error_code", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonObjectAutorM.put("resultado", "no existe la clase");
                    jsonObjectAutorM.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                jsonArrayAutorM.put(jsonObjectAutorM);
                response.getWriter().write(jsonArrayAutorM.toString());
                break;
            case "eliminarAutor":
                JSONArray jsonArrayAutorE = new JSONArray();
                JSONObject jsonObjectAutorE = new JSONObject();
                String resultadoEliminado = "";
                Autor autor3 = new Autor();
                try {
                    AutoresDao autoresDao = new AutoresDao();
                    autor3.setCodigoAutor(request.getParameter("id"));
                    resultadoEliminado = autoresDao.eliminarAutor(autor3);
                    if (resultadoEliminado == "exito"){
                        jsonObjectAutorE.put("resultado", "exito");
                    }else {
                        jsonObjectAutorE.put("resultado", "error");
                        jsonObjectAutorE.put("resultado_eliminar", resultadoEliminado);
                    }
                }catch (SQLException e){
                    jsonObjectAutorE.put("resultado", "no existe la clase");
                    jsonObjectAutorE.put("error_code", e.getErrorCode());
                    jsonObjectAutorE.put("error_mostrado", e.getMessage());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonObjectAutorE.put("resultado", "error_sql");
                    jsonObjectAutorE.put("error_mostrado", e.getMessage());
                    throw new RuntimeException(e);
                }
                jsonArrayAutorE.put(jsonObjectAutorE);
                response.getWriter().write(jsonArrayAutorE.toString());
                break;
            case "mostrarAutores":
                int con = 0;
                Autor autorEdad = new Autor();
                JSONArray arrayAutores = new JSONArray();
                JSONObject jsonAutores = new JSONObject();
                String html_categorias = "";
                try {
                    AutoresDao autores = new AutoresDao();
                    ResultSet resultAutores = autores.consultarAutores();
                    html_categorias += "<table id=\"tabla_autores\" " +
                            "class=\"table table-bordered dt-responsive nowrap\" " +
                            "cellSpacing=0 width=100%>\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th>Código</th>\n" +
                            "      <th>Nombre</th>\n" +
                            "      <th>Apllido</th>\n" +
                            "      <th>Edad</th>\n" +
                            "      <th>Nacionalidad</th>\n" +
                            "      <th>Acciones</th>\n" +
                            "    </tr>\n" +
                            "  </thead>\n" +
                            "  <tbody>";
                    while (resultAutores.next()){
                        String edad = autorEdad.calcularEdad(resultAutores.getString("fechanacimientoautor"));
                        html_categorias += "<tr>";
                        html_categorias += "<td>" + resultAutores.getString("codigoautor") +"</td>";
                        html_categorias += "<td>" + resultAutores.getString("nombreautor") +"</td>";
                        html_categorias += "<td>" + resultAutores.getString("apellidoautor") +"</td>";
                        html_categorias += "<td>" + edad +"</td>";
                        html_categorias += "<td>" + resultAutores.getString("nacionalidad") +"</td>";
                        html_categorias += "<td>";
                        html_categorias += "<div class='dropdown mb-b-10'>";
                        html_categorias += "<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true'\n" +
                                "aria-expanded='false'>Seleccione</button>";
                        html_categorias += "<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>";
                        html_categorias += "<a class='dropdown-item btnModificar' data-idAutor='"+ resultAutores.getString("codigoautor")+"' href='javascript:void(0)'>Modificar</a>";
                        html_categorias += "<a class='dropdown-item btnEliminar' data-idAutor='"+ resultAutores.getString("codigoautor")+"' href='javascript:void(0)'>Eliminar</a>";
                        html_categorias += "</div>";
                        html_categorias += "</div>";
                        html_categorias += "</td>";
                        html_categorias += "</tr>";
                        con++;
                    }
                    html_categorias += "</tbody>\n" +
                            "\t\t</table>";
                    if (con >= 0){
                        jsonAutores.put("resultado","exito");
                        jsonAutores.put("tabla", html_categorias);
                        jsonAutores.put("cantidad", con);
                    }
                }catch (SQLException e){
                    jsonAutores.put("resultado", "error_sql");
                    jsonAutores.put("error",e.getMessage());
                    jsonAutores.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonAutores.put("resultado", "class no found");
                    jsonAutores.put("error",e.getMessage());
                    throw new RuntimeException(e);
                }
                arrayAutores.put(jsonAutores);
                response.getWriter().write(arrayAutores.toString());
                break;
        }
    }
}
