package Conntrollers;

import DAOs.Consulta1Dao;
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

@WebServlet(name = "Consulta1Controlador", value = "/Consulta1")
public class Consulta1Controlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nombre = (String) session.getAttribute("nombre");
        System.out.println("Inicio sesión: " + nombre);
        request.getRequestDispatcher("/modulos/consulta1/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filtro = request.getParameter("consultar_datos");

        if(filtro == null){
            return;
        }
        switch (filtro) {
            case "mostrarConsulta":
                int con = 0;
                JSONArray arrayConsulta = new JSONArray();
                JSONObject jsonConsulta  = new JSONObject();
                String html_Consulta  = "";
                try {
                    Consulta1Dao consultas = new Consulta1Dao();
                    ResultSet resultconsultas = consultas.consultarConsulta();
                    html_Consulta += "<table id=\"tabla_consulta\" " +
                            "class=\"table table-bordered dt-responsive nowrap\" " +
                            "cellSpacing=0 width=100%>\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th>Carnet</th>\n" +
                            "      <th>Nombre</th>\n" +
                            "      <th>Apellido</th>\n" +
                            "      <th>Edad (año)</th>\n" +
                            "      <th>Edad Exacta</th>\n" +
                            "      <th>Edad Real</th>\n" +
                            "    </tr>\n" +
                            "  </thead>\n" +
                            "  <tbody>";
                    while (resultconsultas.next()){

                        html_Consulta += "<tr>";
                        html_Consulta += "<td>" + resultconsultas.getString("carnet") +"</td>";
                        html_Consulta += "<td>" + resultconsultas.getString("nombre") +"</td>";
                        html_Consulta += "<td>" + resultconsultas.getString("apellido") +"</td>";
                        html_Consulta += "<td>" + resultconsultas.getString("edad") +"</td>";
                        html_Consulta += "<td>" + resultconsultas.getString("edad_exacta") +"</td>";
                        html_Consulta += "<td>" + resultconsultas.getString("edad_real") +"</td>";
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
