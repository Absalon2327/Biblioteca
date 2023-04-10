<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta http-equiv="Content-Type" content="text/html"/>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>

</head>

<body>
<div class="vh-100 d-flex justify-content-center align-items-center">
    <a href="Rutas?modulos=login"></a>
    <div class="col-md-4 p-5 shadow-sm border rounded-5 border-primary">
        <div class="mb-3 text-center">
            <img src="public/assets/images/icon_libros.png" height="90" alt="logo">
        </div>
        <h2 class="text-center mb-4 text-primary">Inicio de Sesión</h2>
        <form id="formLogin" name="formLogin">
            <div class="mb-3">
                <label for="usuarioLogin" class="form-label">Usuario</label>
                <input type="text" class="form-control bg-info bg-opacity-10 border border-primary" id="usuarioLogin"
                       name="usuarioLogin" required>
            </div>
            <div class="mb-3">
                <label for="contraLogin" class="form-label">Contraseña</label>
                <input type="password" class="form-control bg-info bg-opacity-10 border border-primary" id="contraLogin"
                       name="contraLogin" required>
            </div>
            <div class="d-grid">
                <button class="btn btn-primary" type="submit">Entrar</button>
            </div>
        </form>
    </div>
</div>
<%@ include file="../../layouts/footerScript.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="./modulos/login/login_funciones.js"></script>
</body>

</html>