package Conntrollers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;



@WebServlet(name = "RutasControlador", value = "/Rutas")
public class RutasControlador extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String modulos = request.getParameter("modulos");
        String moduloPrincipal = "prestamos";
        HttpSession session = request.getSession();
        if (session.getAttribute("nombre") == null && !modulos.equals("login")){
            response.sendRedirect("Login");
        }else if ( session.getAttribute("nombre") != null && modulos != null && modulos.equals("login")){
            response.sendRedirect("Login");
        }else{
            request.getRequestDispatcher("/modulos/"+ modulos +"/index.jsp").forward(request, response);
        }
        if (session.getAttribute("nombre") != null && modulos.equals("login")) {
            session.invalidate();
            response.sendRedirect("Login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
