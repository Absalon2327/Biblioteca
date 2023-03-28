package Conntrollers;

import Models.CategoriaLibros;
import Models.Obtener_CategoriaLibros;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "RegCatLib", value = "/RegCatLib")
public class RegCatLib extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nombre = (String) session.getAttribute("nombre");
        System.out.println("Inicio sesión: " + nombre);
        req.getRequestDispatcher("/modulos/catLibros/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String filtro = req.getParameter("consultar_datos");
        System.out.println("El filtro es: "+filtro);
        HttpSession session = req.getSession();
        if ( filtro == null ){
            return;
        }
        switch ( filtro ){
            case "si_registro":
                JSONArray array_catLib = new JSONArray();
                JSONObject json_catLib = new JSONObject();
                String resultado_insertar = "";
                CategoriaLibros catLibritos = new CategoriaLibros();
                try {
                    Obtener_CategoriaLibros rg = new Obtener_CategoriaLibros();
                    catLibritos.setCodigocategoria(req.getParameter("codigoCategoria"));
                    catLibritos.setNombrecategoria(req.getParameter("nombreCategoria"));
                    resultado_insertar = rg.insertarCategoria(catLibritos);
                    if (resultado_insertar == "Exito"){
                        json_catLib.put("resultado", "exito");
                        json_catLib.put("codigoCategoria", catLibritos.getCodigocategoria());
                        json_catLib.put("codigoCategoria", catLibritos.getNombrecategoria());

                    } else {
                        json_catLib.put("resultado", "error");
                        json_catLib.put("resultado_insertar", resultado_insertar);
                    }
                }catch (SQLException e){
                    json_catLib.put("resultado", "error_sql");
                    json_catLib.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado: " + e);
                    System.out.println("Error Code error: " + e.getErrorCode());
                    System.out.println("Error Exception: " + e);
                    throw  new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_catLib.put("resultado", "error_class");
                    json_catLib.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                array_catLib.put(json_catLib);
                resp.getWriter().write(array_catLib.toString());
                break;

            case "si_consultar_info":
                String codigoCat = req.getParameter("id");
                JSONArray array_catConsul = new JSONArray();
                JSONObject json_catConsul = new JSONObject();
                CategoriaLibros catLibConsul = new CategoriaLibros();
                try {
                    Obtener_CategoriaLibros cl = new Obtener_CategoriaLibros();
                    ResultSet res_categorias = cl.consultarCategoriasID(codigoCat);

                    while(res_categorias.next()){
                        json_catConsul.put("resultado","exito");
                        json_catConsul.put("idCat",res_categorias.getString("codigocategoria"));
                        json_catConsul.put("idNombreCat",res_categorias.getString("nombrecategoria"));
                    }
                }catch (SQLException e){
                    json_catConsul.put("resultado", "error_sql");
                    json_catConsul.put("error",e.getMessage());
                    json_catConsul.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_catConsul.put("resultado", "class no found");
                    json_catConsul.put("error",e.getMessage());
                    throw new RuntimeException(e);
                }
                array_catConsul.put(json_catConsul);
                resp.getWriter().write(array_catConsul.toString());
                break;

            case "modificarCategoria":
                JSONArray array_catMod = new JSONArray();
                JSONObject json_catMod = new JSONObject();
                String resultadoModificar = "";
                CategoriaLibros catLibritosM = new CategoriaLibros();
                try {
                    Obtener_CategoriaLibros rg = new Obtener_CategoriaLibros();
                    catLibritosM.setCodigocategoria(req.getParameter("codigoCategoria"));
                    catLibritosM.setNombrecategoria(req.getParameter("nombreCategoria"));
                    resultadoModificar = rg.modificarCategoria(catLibritosM);
                    if (resultadoModificar == "exito"){
                        json_catMod.put("resultado", "exito");
                        json_catMod.put("codigoCategoria", catLibritosM.getCodigocategoria());
                        json_catMod.put("nombreCategoria", catLibritosM.getNombrecategoria());

                    } else {
                        json_catMod.put("resultado", "error");
                        json_catMod.put("resultado_nodificar", resultadoModificar);
                    }
                }catch (SQLException e){
                    json_catMod.put("resultado", "error_sql");
                    json_catMod.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado: " + e);
                    System.out.println("Error Code error: " + e.getErrorCode());
                    System.out.println("Error Exception: " + e);
                    throw  new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_catMod.put("resultado", "error_class");
                    json_catMod.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                array_catMod.put(json_catMod);
                resp.getWriter().write(array_catMod.toString());
                break;
            case "eliminarCategoria":
                JSONArray array_catEliminar = new JSONArray();
                JSONObject json_catEliminar = new JSONObject();
                String resultadoEliminar = "";
                CategoriaLibros catLibritosEliminar = new CategoriaLibros();
                try {
                    Obtener_CategoriaLibros rg = new Obtener_CategoriaLibros();
                    catLibritosEliminar.setCodigocategoria(req.getParameter("id"));
                    resultadoEliminar = rg.eliminarCategoria(catLibritosEliminar);
                    if (resultadoEliminar == "exito"){
                        json_catEliminar.put("resultado", "exito");
                    } else {
                        json_catEliminar.put("resultado", "error");
                        json_catEliminar.put("resultado_eliminar", resultadoEliminar);
                    }
                }catch (SQLException e){
                    json_catEliminar.put("resultado", "error_sql");
                    json_catEliminar.put("error_mostrado", e.getMessage());
                    throw  new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_catEliminar.put("resultado", "error_class");
                    json_catEliminar.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                array_catEliminar.put(json_catEliminar);
                resp.getWriter().write(array_catEliminar.toString());
                break;

            case "si_consultar_categorias":
                JSONArray array_catLibrito = new JSONArray();
                JSONObject json_catLibrito = new JSONObject();
                String html_categorias = "";
                String el_estado = req.getParameter("estado");
                try {
                    Obtener_CategoriaLibros cl = new Obtener_CategoriaLibros();
                    ResultSet res_categorias = cl.consultarCategorias(Integer.valueOf(el_estado), "todos");
                    html_categorias += "<table id=\"tabla_categorias\" " +
                            "class=\"table table-bordered dt-responsive nowrap\" " +
                            "cellSpacing=0 width=100%>\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th>Código Categoría</th>\n" +
                            "      <th>Nombre Categoría</th>\n" +
                            "      <th>Acciones</th>\n" +
                            "    </tr>\n" +
                            "  </thead>\n" +
                            "  <tbody>";
                    while (res_categorias.next()){
                        html_categorias += "<tr>";
                        html_categorias += "<td>" + res_categorias.getString("codigocategoria")+"</td>";
                        html_categorias += "<td>" + res_categorias.getString("nombrecategoria")+"</td>";
                        html_categorias += "<td>";
                        html_categorias += "<div class='dropdown mb-b-10'>";
                        html_categorias += "<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true'\n" +
                                "aria-expanded='false'>Seleccione</button>";
                        html_categorias += "<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>";
                        html_categorias += "<a class='dropdown-item btnModificar' data-idCategoria='"+ res_categorias.getString("codigocategoria")+"' href='javascript:void(0)'>Modificar</a>";
                        html_categorias += "<a class='dropdown-item btnEliminar' data-idCategoria='"+ res_categorias.getString("codigocategoria")+"' href='javascript:void(0)'>Eliminar</a>";
                        html_categorias += "</div>";
                        html_categorias += "</div>";
                        html_categorias += "</td>";
                        html_categorias += "</tr>";
                    }
                    html_categorias += "</tbody>\n" +
                            "\t\t</table>";
                    json_catLibrito.put("resultado","exito");
                    json_catLibrito.put("tabla", html_categorias);
                }catch (SQLException e){
                    json_catLibrito.put("resultado", "error_sql");
                    json_catLibrito.put("error",e.getMessage());
                    json_catLibrito.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_catLibrito.put("resultado", "class no found");
                    json_catLibrito.put("error",e.getMessage());
                    throw new RuntimeException(e);
                }
                array_catLibrito.put(json_catLibrito);
                resp.getWriter().write(array_catLibrito.toString());
                break;
        }
    }
}
