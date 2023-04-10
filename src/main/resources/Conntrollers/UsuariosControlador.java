package Conntrollers;

import DAOs.AlumnosDao;
import DAOs.UsuariosDao;
import Models.Alumno;
import Models.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UsuariosControlador", value = "/Usuarios")
public class UsuariosControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String filtro = request.getParameter("consultar_datos");
        System.out.println(filtro);

        if (filtro.equals("si_coneste_id")) {
            String idUsuario = request.getParameter("id");
            JSONArray ArrayUsuarios = new JSONArray();
            JSONObject objUsuarios = new JSONObject();

            try {
                UsuariosDao usuariosDao = new UsuariosDao();
                ResultSet resultSetUsuarios = usuariosDao.consultarUsuariosID(idUsuario);
                while (resultSetUsuarios.next()){
                    objUsuarios.put("resultado", "exito");
                    objUsuarios.put("idU", resultSetUsuarios.getString("id"));
                    objUsuarios.put("nombreU", resultSetUsuarios.getString("nombre"));
                    objUsuarios.put("apellidoU", resultSetUsuarios.getString("apellido"));
                    objUsuarios.put("usuario", resultSetUsuarios.getString("usuario"));
                    objUsuarios.put("nivel", resultSetUsuarios.getString("nivel"));
                    objUsuarios.put("estadoU", resultSetUsuarios.getString("estado"));
                }
            }catch (SQLException e){
                objUsuarios.put("resultado", "error_sql");
                objUsuarios.put("error", e.getMessage());
                objUsuarios.put("code error", e.getErrorCode());
                throw  new RuntimeException(e);
            }catch (ClassNotFoundException e){
                objUsuarios.put("resultado", "no se econtró la clase");
                objUsuarios.put("error", e.getMessage());
                throw new RuntimeException(e);
            }
            ArrayUsuarios.put(objUsuarios);
            response.getWriter().write(ArrayUsuarios.toString());
        } else if (filtro.equals("mostrarUsuarios")) {
            System.out.println("entró");
            int con = 0;
            JSONArray arrayUsuarios = new JSONArray();
            JSONObject objectUsuarios = new JSONObject();
            String html_users = "";
            try {
                UsuariosDao usuariosDao = new UsuariosDao();
                ResultSet resultUsers = usuariosDao.consultarUsuarios();
                html_users += "<table id=\"tabla_usuarios\" " +
                        "class=\"table table-bordered dt-responsive nowrap\" " +
                        "cellSpacing=0 width=100%>\n" +
                        "  <thead>\n" +
                        "    <tr>\n" +
                        "      <th>Nombre</th>\n" +
                        "      <th>Usuario</th>\n" +
                        "      <th>Nivel</th>\n" +
                        "      <th>Acciones</th>\n" +
                        "    </tr>\n" +
                        "  </thead>\n" +
                        "  <tbody>";
                while (resultUsers.next()) {
                    html_users += "<tr>";
                    html_users += "<td>" + resultUsers.getString("nombre") + ' ' + resultUsers.getString("apellido") + "</td>";
                    html_users += "<td>" + resultUsers.getString("usuario") + "</td>";
                    html_users += "<td>" + resultUsers.getString("nivel") + "</td>";
                    html_users += "<td>";
                    html_users += "<div class='dropdown mb-b-10'>";
                    html_users += "<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true'\n" +
                            "aria-expanded='false'>Seleccione</button>";
                    html_users += "<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>";
                    html_users += "<a class='dropdown-item btnModificar' data-idUsuario='" + resultUsers.getString("id") + "' href='javascript:void(0)'>Modificar Datos</a>";
                    html_users += "<a class='dropdown-item btnModificarContra' data-idUsuario='" + resultUsers.getString("id") + "' href='javascript:void(0)'>Modificar Contraseña</a>";
                    html_users += "<a class='dropdown-item btnEliminar' data-idUsuario='" + resultUsers.getString("id") + "' href='javascript:void(0)'>Eliminar</a>";
                    html_users += "</div>";
                    html_users += "</div>";
                    html_users += "</td>";
                    html_users += "</tr>";
                    con++;
                }
                html_users += "</tbody>\n" +
                        "\t\t</table>";
                if (con >= 0) {
                    objectUsuarios.put("resultado", "exito");
                    objectUsuarios.put("tabla", html_users);
                    objectUsuarios.put("cantidad", con);
                }
            } catch (SQLException e) {
                objectUsuarios.put("resultado", "error_sql");
                objectUsuarios.put("error", e.getMessage());
                objectUsuarios.put("code error", e.getErrorCode());
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                objectUsuarios.put("resultado", "class no found");
                objectUsuarios.put("error", e.getMessage());
                throw new RuntimeException(e);
            }
            arrayUsuarios.put(objectUsuarios);
            response.getWriter().write(arrayUsuarios.toString());
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String insertar = "";
        String modifcar = "";
        String contra = "";
        if (request.getParameter("consultar_datos") != null) {
            insertar = request.getParameter("consultar_datos");
        }
        if (request.getParameter("modificar_datos") != null) {
            modifcar = request.getParameter("modificar_datos");
        }
        if (request.getParameter("consultar_contra") != null) {
            contra = request.getParameter("consultar_contra");
        }

        System.out.println("modificar " + modifcar);
        System.out.println("el insertar " + insertar);
        System.out.println("llego aquí");
        if (modifcar.equals("modificarUsuario")) {
            System.out.println("entró al editar");
            JSONArray ArrayModify = new JSONArray();
            JSONObject objModify = new JSONObject();
            String resultadoModicado = "";
            Usuario usuarioModify = new Usuario();
            try {
                UsuariosDao usuariosDao = new UsuariosDao();
                usuarioModify.setIdUsuario(request.getParameter("idUsersM"));
                usuarioModify.setNombreUsuario(request.getParameter("nombrePersonaM"));
                usuarioModify.setApellidoUsuario(request.getParameter("apellidoPersonaM"));
                usuarioModify.setUsuario(request.getParameter("nombreUsuarioM"));
                usuarioModify.setNivel(request.getParameter("nivelUsuarioM"));
                usuarioModify.setEstadoUsuario(request.getParameter("estado"));
                resultadoModicado = usuariosDao.modificarUsuario(usuarioModify);
                if (resultadoModicado == "exito"){
                    objModify.put("resultado", "exito");
                }else {
                    objModify.put("resultado", "error");
                    objModify.put("resultado_nodificar", resultadoModicado);
                }
            }catch (SQLException e){
                objModify.put("resultado", "error_sql");
                objModify.put("resultado", e.getMessage());
                objModify.put("error_code", e.getErrorCode());
                throw new RuntimeException(e);
            }catch (ClassNotFoundException e){
                objModify.put("resultado", "no existe la clase");
                objModify.put("error_mostrado", e);
                throw new RuntimeException(e);
            }
            ArrayModify.put(objModify);
            response.getWriter().write(ArrayModify.toString());

        }

        else if ( insertar.equals("insertarUsuarios")) {
            System.out.println("entró al insertar");
            JSONArray arrayUsuarios = new JSONArray();
            JSONObject objUsuarios = new JSONObject();
            String resultadoInsertar = "";

            Usuario usuario = new Usuario();
            try {
                String contraEncrip = BCrypt.hashpw(request.getParameter("reContrasena"), BCrypt.gensalt());

                UsuariosDao rg = new UsuariosDao();
                usuario.setIdUsuario(request.getParameter("idUsers"));
                usuario.setNombreUsuario(request.getParameter("nombrePersona"));
                usuario.setApellidoUsuario(request.getParameter("apellidoPersona"));
                usuario.setUsuario(request.getParameter("nombreUsuario"));
                usuario.setContra(contraEncrip);
                usuario.setNivel(request.getParameter("nivelUsuario"));
                usuario.setEstadoUsuario("activo");
                resultadoInsertar = rg.insertarUsuario(usuario);
                if (resultadoInsertar == "exito"){
                    objUsuarios.put("resultado", "exito");
                } else {
                    objUsuarios.put("resultado", "error");
                    objUsuarios.put("resultadoInsertar", resultadoInsertar);
                }
            }catch (SQLException e){
                objUsuarios.put("resultado", "error_sql");
                objUsuarios.put("error_mostrado", e.getMessage());
                System.out.println("Error mostrado: " + e);
                System.out.println("Error Code error: " + e.getErrorCode());
                System.out.println("Error Exception: " + e);
                throw  new RuntimeException(e);
            }catch (ClassNotFoundException e){
                objUsuarios.put("resultado", "error_class");
                objUsuarios.put("error_mostrado", e);
                throw new RuntimeException(e);
            }
            arrayUsuarios.put(objUsuarios);
            response.getWriter().write(arrayUsuarios.toString());
        }

        else if (contra.equals("modContraUsuarios")) {
            System.out.println("entró al editar contra");
            JSONArray ArrayModifyContra = new JSONArray();
            JSONObject objModifyContra = new JSONObject();
            String resultadoModicado = "";
            Usuario usuarioModify = new Usuario();
            try {
                String contraEncrip = BCrypt.hashpw(request.getParameter("reContrasenaM"), BCrypt.gensalt());
                UsuariosDao usuariosDao = new UsuariosDao();
                usuarioModify.setIdUsuario(request.getParameter("id"));
                usuarioModify.setContra(contraEncrip);

                resultadoModicado = usuariosDao.modificarContrasenia(usuarioModify);

                if (resultadoModicado == "exito"){
                    objModifyContra.put("resultado", "exito");
                }else {
                    objModifyContra.put("resultado", "error");
                    objModifyContra.put("resultado_nodificar", resultadoModicado);
                }
            }catch (SQLException e){
                objModifyContra.put("resultado", "error_sql");
                objModifyContra.put("resultado", e.getMessage());
                objModifyContra.put("error_code", e.getErrorCode());
                throw new RuntimeException(e);
            }catch (ClassNotFoundException e){
                objModifyContra.put("resultado", "no existe la clase");
                objModifyContra.put("error_mostrado", e);
                throw new RuntimeException(e);
            }
            ArrayModifyContra.put(objModifyContra);
            response.getWriter().write(ArrayModifyContra.toString());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String filtro = request.getParameter("consultar_datos");
        System.out.println(filtro);
        System.out.println("llego aquí");
        if (request.getParameter("consultar_datos") == filtro) {
            JSONArray arrayUsuario = new JSONArray();
            JSONObject ojectUsuario = new JSONObject();
            String resultadoEliminado = "";
            Usuario usuario = new Usuario();
            try {
                UsuariosDao usuariosDao = new UsuariosDao();
                usuario.setIdUsuario(request.getParameter("id"));
                resultadoEliminado = usuariosDao.eliminarUsuario(usuario);
                if (resultadoEliminado == "exito"){
                    ojectUsuario.put("resultado", "exito");
                }else {
                    ojectUsuario.put("resultado", "error");
                    ojectUsuario.put("resultado_eliminar", resultadoEliminado);
                }
            }catch (SQLException e){
                ojectUsuario.put("resultado", "no existe la clase");
                ojectUsuario.put("error_code", e.getErrorCode());
                ojectUsuario.put("error_mostrado", e.getMessage());
                throw new RuntimeException(e);
            }catch (ClassNotFoundException e){
                ojectUsuario.put("resultado", "error_sql");
                ojectUsuario.put("error_mostrado", e.getMessage());
                throw new RuntimeException(e);
            }
            arrayUsuario.put(ojectUsuario);
            response.getWriter().write(arrayUsuario.toString());
        }

    }


}

