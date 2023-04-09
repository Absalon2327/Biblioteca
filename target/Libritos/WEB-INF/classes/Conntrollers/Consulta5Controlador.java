package Conntrollers;

import DAOs.Consulta1Dao;
import DAOs.Consulta5Dao;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

@WebServlet(name = "Consulta5Controlador", value = "/Consulta5")
public class Consulta5Controlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filtro = request.getParameter("consultar_datos");

        if(filtro == null){
            return;
        }
        switch (filtro) {
            case "si_consulta_categoria":
                JSONArray array_categoria = new JSONArray();
                JSONObject json_categoria = new JSONObject();

                Consulta5Dao cl = null;
                String html_categorias = "";
                try {
                    cl = new Consulta5Dao();
                    ResultSet res_categorias = cl.obtenerCategorias();
                    html_categorias += "<option selected>Seleccione Opción</option>";
                    while (res_categorias.next()) {
                        html_categorias += "<option value=\""+res_categorias.getString("codigo")+"\">" + res_categorias.getString("nombre") +"</option>";
                    }
                    json_categoria.put("resultado", "exito");
                    json_categoria.put("opciones", html_categorias);
                } catch (SQLException e) {
                    json_categoria.put("resultado", "error_sql");
                    json_categoria.put("error", e.getMessage());
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                array_categoria.put(json_categoria);
                response.getWriter().write(array_categoria.toString());
                break;
            case "mostrarConsulta":
                int con = 0;
                JSONArray arrayConsulta = new JSONArray();
                JSONObject jsonConsulta  = new JSONObject();

                Locale locale = new Locale("en", "US");
                NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

                String html_Consulta  = "";
                String idcategoria = request.getParameter("id_categoria");
                try {
                    Consulta5Dao consultas = new Consulta5Dao();
                    ResultSet resultconsultas = consultas.consultar_librosEspecificos(idcategoria);
                    html_Consulta += "<table id=\"tabla_consulta\" " +
                            "class=\"table table-bordered dt-responsive nowrap\" " +
                            "cellSpacing=0 width=100%>\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th>código</th>\n" +
                            "      <th>título</th>\n" +
                            "      <th>precio</th>\n" +
                            "    </tr>\n" +
                            "  </thead>\n" +
                            "  <tbody>";
                    while (resultconsultas.next()){

                        double monedaPrecio = Double.parseDouble(resultconsultas.getString("precio"));
                        String formatoPrecio = currencyFormatter.format(monedaPrecio);

                        html_Consulta += "<tr>";
                        html_Consulta += "<td>" + resultconsultas.getString("codigolibro") +"</td>";
                        html_Consulta += "<td>" + resultconsultas.getString("titulolibro") +"</td>";
                        html_Consulta += "<td>" + formatoPrecio +"</td>";
                        html_Consulta += "</tr>";
                        con++;
                    }
                    html_Consulta += "</tbody>\n" +
                            "\t\t</table>";
                    if (con >= 0){
                        jsonConsulta.put("resultado","exito");
                        jsonConsulta.put("tabla", html_Consulta);
                        jsonConsulta.put("cantidad", con);
                        System.out.println("cantidad es: "+con);
                    }
                }catch (SQLException e){
                    jsonConsulta.put("resultado", "error_sql");
                    jsonConsulta.put("error",e.getMessage());
                    jsonConsulta.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonConsulta.put("resultado", "class no found");
                    jsonConsulta.put("error",e.getMessage());
                    throw new RuntimeException(e);
                }
                arrayConsulta.put(jsonConsulta);
                response.getWriter().write(arrayConsulta.toString());
                break;
        }
    }
}
