package Conntrollers;

import DAOs.LibroDao;
import Models.Autor;
import Models.CategoriaLibros;
import Models.Libro;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@WebServlet(name = "LibrosControlador", value = "/Libros")
public class LibrosControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String filtro = request.getParameter("consultar_datos");

        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        if(filtro == null){
            return;
        }
        switch (filtro) {
            case "insertarLibros":
                JSONArray arrayInsertLibro = new JSONArray();
                JSONObject jsonInsertlibro = new JSONObject();
                String resultadoInsert = "";
                Libro libro = new Libro();
                try {
                    LibroDao ld = new LibroDao();

                    libro.setCodigoLibro(request.getParameter("codigoLibro"));
                    libro.setTituloLibro(request.getParameter("tituloLibro"));
                    libro.setExistencia(Integer.parseInt(request.getParameter("Existencia")));
                    libro.setCodigoCategoria(new CategoriaLibros(request.getParameter("codigoCategoria")));
                    libro.setPrecio(Double.parseDouble(request.getParameter("Precio")));
                    libro.setCodigoAutor(new Autor(request.getParameter("codigoAutor")));
                    resultadoInsert = ld.insertarLibro(libro);
                    if(resultadoInsert=="exito"){
                        jsonInsertlibro.put("resultado", "exito");
                    }else {
                        jsonInsertlibro.put("resultado", "error");
                        jsonInsertlibro.put("resultadoInsertar", resultadoInsert);

                    }

                }catch (SQLException e){
                    jsonInsertlibro.put("resultado", "error_sql");
                    jsonInsertlibro.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado: " + e);
                    System.out.println("Error Code error: " + e.getErrorCode());
                    System.out.println("Error Exception: " + e);
                    throw  new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonInsertlibro.put("resultado", "error_class");
                    jsonInsertlibro.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                arrayInsertLibro.put(jsonInsertlibro);
                response.getWriter().write(arrayInsertLibro.toString());
                break;
            case "mostrarLibros":
                int con = 0;
                JSONArray arrayLibros = new JSONArray();
                JSONObject jsonLibros  = new JSONObject();
                String html_Libros  = "";
                try {
                    LibroDao libros = new LibroDao();
                    ResultSet resultLibros = libros.consultarLibros();
                    html_Libros += "<table id=\"tabla_libros\" " +
                            "class=\"table table-bordered dt-responsive nowrap\" " +
                            "cellSpacing=0 width=100%>\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th>Código</th>\n" +
                            "      <th>Nombre Libro</th>\n" +
                            "      <th>Existencias</th>\n" +
                            "      <th>Categoria</th>\n" +
                            "      <th>Precio</th>\n" +
                            "      <th>Autor</th>\n" +
                            "      <th>Acciones</th>\n" +
                            "    </tr>\n" +
                            "  </thead>\n" +
                            "  <tbody>";
                    while (resultLibros.next()){
                        double monedaPrecio = Double.parseDouble(resultLibros.getString("precio"));
                        String formatoPrecio = currencyFormatter.format(monedaPrecio);

                        html_Libros += "<tr>";
                        html_Libros += "<td>" + resultLibros.getString("codigolibro") +"</td>";
                        html_Libros += "<td>" + resultLibros.getString("titulolibro") +"</td>";
                        html_Libros += "<td>" + resultLibros.getString("existencia") +"</td>";
                        html_Libros += "<td>" + resultLibros.getString("nombrecategoria") +"</td>";
                        html_Libros += "<td>" +  formatoPrecio +"</td>";
                        html_Libros += "<td>" + resultLibros.getString("nombreautor") +"</td>";
                        html_Libros += "<td>";
                        html_Libros += "<div class='dropdown mb-b-10'>";
                        html_Libros += "<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true'\n" +
                                "aria-expanded='false'>Seleccione</button>";
                        html_Libros += "<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>";
                        html_Libros += "<a class='dropdown-item btnModificar' data-idLibro='"+ resultLibros.getString("codigolibro")+"' href='javascript:void(0)'>Modificar</a>";
                        html_Libros += "<a class='dropdown-item btnEliminar' data-idLibro='"+ resultLibros.getString("codigolibro")+"' href='javascript:void(0)'>Eliminar</a>";
                        html_Libros += "</div>";
                        html_Libros += "</div>";
                        html_Libros += "</td>";
                        html_Libros += "</tr>";
                        con++;
                    }
                    html_Libros += "</tbody>\n" +
                            "\t\t</table>";
                    if (con >= 0){
                        jsonLibros.put("resultado","exito");
                        jsonLibros.put("tabla", html_Libros);
                        jsonLibros.put("cantidad", con);
                        System.out.println("cantidad es: "+con);
                    }
                }catch (SQLException e){
                    jsonLibros.put("resultado", "error_sql");
                    jsonLibros.put("error",e.getMessage());
                    jsonLibros.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonLibros.put("resultado", "class no found");
                    jsonLibros.put("error",e.getMessage());
                    throw new RuntimeException(e);
                }
                arrayLibros.put(jsonLibros);
                response.getWriter().write(arrayLibros.toString());
                break;
        }
    }
}
