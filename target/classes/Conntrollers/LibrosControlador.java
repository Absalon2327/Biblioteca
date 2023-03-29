package Conntrollers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LibrosControlador", value = "/Libros")
public class LibrosControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nombre = (String) session.getAttribute("nombre");
        System.out.println("Inicio sesión: " + nombre);
        request.getRequestDispatcher("/modulos/libros/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
