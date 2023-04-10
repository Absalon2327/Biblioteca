package Conntrollers;

import DAOs.Consulta2Dao;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(name = "Consulta2", value = "/Consulta2")
public class Consulta2Controlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nombre = (String) session.getAttribute("nombre");
        System.out.println("Inicio de sesion"+nombre);
        request.getRequestDispatcher("/modulos/consulta2/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String filtro = request.getParameter("consultar_datos");

                if(filtro == null){
                    return;
                }

                switch (filtro){
                    case "mostrarconsulta":
                        int con = 0;
                        JSONArray arrayConsulta2 = new JSONArray();
                        JSONObject jsonConsulta2  = new JSONObject();


                        String html_Consulta2  = "";
                        String idfecha2 = request.getParameter("fechaPrestam");

                        try {
                            Consulta2Dao consultas = new Consulta2Dao();
                            ResultSet resultconsultas = consultas.consultar_datosPrestamos(idfecha2);
                            html_Consulta2 +="<table id=\"tabla_consulta\" " +
                                    "class=\"table table-bordered dt-responsive nowrap\" " +
                                    "cellSpacing=0 width=100%>\n" +
                                    "  <thead>\n" +
                                    "    <tr>\n" +
                                    "      <th>Alumno</th>\n" +
                                    "      <th>Codigo del libro</th>\n" +
                                    "      <th>Titulo</th>\n" +
                                    "      <th>Fecha prestamo</th>\n" +
                                    "      <th>Fecha devolucion</th>\n" +
                                    "    </tr>\n" +
                                    "  </thead>\n" +
                                    "  <tbody>";
                            while (resultconsultas.next()){
                                    html_Consulta2 += "<tr>";
                                    html_Consulta2 +="<td>" + resultconsultas.getString("datos")+"</td>";
                                    html_Consulta2 +="<td>" + resultconsultas.getString("codigolibro")+"</td>";
                                    html_Consulta2 +="<td>" + resultconsultas.getString("titulo")+"</td>";
                                    html_Consulta2 +="<td>" + resultconsultas.getDate("fechaprestamo")+"</td>";
                                    html_Consulta2 +="<td>" + resultconsultas.getDate("fechadevolucion")+"</td>";
                                    html_Consulta2 += "</tr>";
                                con++;
                            }
                            html_Consulta2 += "</tbody>\n" +
                                    "\t\t</table>";

                            if (con >= 0){
                                jsonConsulta2.put("resultado","exito");
                                jsonConsulta2.put("tabla", html_Consulta2);
                                jsonConsulta2.put("cantidad", con);
                                System.out.println("cantidad es: "+con);
                            }
                        }catch (SQLException e){
                            jsonConsulta2.put("resultado", "error_sql");
                            jsonConsulta2.put("error",e.getMessage());
                            jsonConsulta2.put("code error", e.getErrorCode());
                            throw new RuntimeException(e);

                        }catch (ClassNotFoundException e){
                            jsonConsulta2.put("resultado", "class no found");
                            jsonConsulta2.put("error",e.getMessage());
                            throw new RuntimeException(e);
                        }
                        arrayConsulta2.put(jsonConsulta2);
                        response.getWriter().write(arrayConsulta2.toString());
                        break;
                }
    }
}
