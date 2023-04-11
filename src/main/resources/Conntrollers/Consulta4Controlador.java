package Conntrollers;

import DAOs.Consulta3Dao;
import DAOs.Consulta4Dao;
import Models.Autor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Consulta4", value = "/Consulta4")
public class Consulta4Controlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        int con = 0;
        JSONArray arrayConsulta = new JSONArray();
        JSONObject jsonConsulta  = new JSONObject();
        String html_Consulta  = "";
        try {
            Consulta4Dao consultas = new Consulta4Dao();
            ResultSet resultconsultas = consultas.obtenerConsulta4();
            html_Consulta += "<table id=\"tabla_consulta4\" " +
                    "class=\"table table-bordered dt-responsive nowrap\" " +
                    "cellSpacing=0 width=100%>\n" +
                    "  <thead>\n" +
                    "    <tr>\n" +
                    "      <th>Libro</th>\n" +
                    "      <th>Categoría</th>\n" +
                    "      <th>Precio</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>";
            while (resultconsultas.next()){
                html_Consulta += "<tr>";
                html_Consulta += "<td>" + resultconsultas.getString("titulolibro") + "</td>";
                html_Consulta += "<td>" + resultconsultas.getString("nombrecategoria") +"</td>";
                html_Consulta += "<td> $ " + resultconsultas.getString("precio") +"</td>";
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
