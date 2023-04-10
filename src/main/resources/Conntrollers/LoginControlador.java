package Conntrollers;

import DAOs.UsuariosDao;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "LoginControlador", value = "/Login")
public class LoginControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        request.getRequestDispatcher("/modulos/login/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("llegó sin la llave");
        HttpSession sesion = request.getSession();
        JSONArray array_login = new JSONArray();
        JSONObject objeto_login = new JSONObject();


        response.setContentType("application/json;charset=utf-8");
        try {
            String usuario = request.getParameter("usuarioLogin");
            UsuariosDao procesar = new UsuariosDao();
            String nombre = "";
            String contrabd = "";
            String nivel = "";
            ResultSet resultSet = procesar.obtenerCredenciales(usuario);

            if(resultSet == null){
                objeto_login.put("resultado","Errodatosnoexisten");
            }else{
                while(resultSet.next()){
                    objeto_login.put("id",resultSet.getString("id"));
                    objeto_login.put("first_name",resultSet.getString("nombre"));
                    objeto_login.put("last_name",resultSet.getString("apellido"));
                    objeto_login.put("level",resultSet.getString("nivel"));
                    nombre = resultSet.getString("nombre") + ' ' + resultSet.getString("apellido");
                    nivel = resultSet.getString("nivel");
                    contrabd = resultSet.getString("contra");
                    usuario = resultSet.getString("usuario");
                }
                System.out.println("usuario " +  usuario);
                if(BCrypt.checkpw(request.getParameter("contraLogin"),contrabd)){
                    sesion.setAttribute("usuario", usuario);
                    sesion.setAttribute("nombre", nombre);
                    sesion.setAttribute("nivel", nivel);
                    objeto_login.put("resultado","Exito");
                }else{
                    objeto_login.put("resultado","ErrorContrasenia");
                }
            }

        } catch (SQLException e){
            objeto_login.put("resultado", "error_sql_login");
            objeto_login.put("error", e.getMessage());
            objeto_login.put("code error", e.getErrorCode());
            throw  new RuntimeException(e);
        }catch (ClassNotFoundException e){
            objeto_login.put("resultado", "no se econtró la clase");
            objeto_login.put("error", e.getMessage());
            throw new RuntimeException(e);
        }
        array_login.put(objeto_login);
        response.getWriter().write(array_login.toString());
    }
}
