package Conntrollers;

import DAOs.LibroDao;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.NumberFormat;
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
