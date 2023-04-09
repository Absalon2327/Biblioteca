cargarDatos();
$(function () {
    //var fecha = new Date();
    console.log("esta funcionando");


    $(document).on("click", "#registrar_usuarios", function (e) {
        e.preventDefault();

        $("#formulario_registro").trigger("reset");
        //document.getElementById('carnetAlumno').readOnly = false;
        $("#exampleModalLabel").empty().html("Nuevo | Usuario");
        $("#mdRegisUsuarios").modal("show");
    });

    $(document).on("submit", "#formulario_registro", function (e) {
        e.preventDefault();

        var datos = $("#formulario_registro").serialize();
        console.log("datos: ", datos);
        if ($("#contrasena").val() != $("#reContrasena").val()) {
            Swal.fire(
                'Usuarios',
                'Las Constraseñas no coinciden',
                'warning'
            )
        }else{
            mostrarCargando("Procesando Solicitud","Espere mientras se almacenan los datos")
            $.ajax({
                dataType: "json",
                method: "PUT",
                url: "Usuarios",
                data: datos,
            }).done(function (json) {
                console.log("EL GUARDAR", json);
                if (json[0].resultado == "exito") {
                    Swal.fire({
                        icon: "success",
                        title: "Usuarios",
                        text: "Guardado con éxtio!",
                        allowOutsideClick: false,
                        confirmButtonText: "Ok",
                    }).then((confirmacion) => {
                        if (confirmacion) {
                            $("#formulario_registro").trigger("reset");
                            $("#mdRegisUsuarios").modal("hide");
                            cargarDatos();
                        } else;
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Alumnos",
                        allowOutsideClick: false,
                        text: "Algo salió mal !",
                    });
                }
            });
        }

    });

    $(document).on("submit", "#formularioModifiUser", function (e) {
        e.preventDefault();

        var datos = $("#formularioModifiUser").serialize();
        console.log("datos: ", datos);
        mostrarCargando("Procesando Solicitud","Espere mientras se almacenan los datos")
        $.ajax({
            dataType: "json",
            method: "PUT",
            url: "Usuarios",
            data: datos,
        }).done(function (json) {
            console.log("EL GUARDAR", json);
            if (json[0].resultado == "exito") {
                Swal.fire({
                    icon: "success",
                    title: "Usuarios",
                    text: "Modificado con éxtio!",
                    allowOutsideClick: false,
                    confirmButtonText: "Ok",
                }).then((confirmacion) => {
                    if (confirmacion) {
                        $("#formularioModifiUser").trigger("reset");
                        $("#mdModiUsuarios").modal("hide");
                        cargarDatos();
                    } else;
                });
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Usuarios",
                    allowOutsideClick: false,
                    text: "Algo salió mal !",
                });
            }
        });

    });

    $(document).on("submit", "#formularioModContra", function (e) {
        e.preventDefault();

        var datos = $("#formularioModContra").serialize();
        console.log("datos: ", datos);
        if ($("#contrasenaM").val() != $("#reContrasenaM").val()) {
            Swal.fire(
                'Usuarios',
                'Las Constraseñas no coinciden',
                'warning'
            )
        }else{
            mostrarCargando("Procesando Solicitud","Espere mientras se almacenan los datos")
            $.ajax({
                dataType: "json",
                method: "PUT",
                url: "Usuarios",
                data: datos,
            }).done(function (json) {
                console.log("EL GUARDAR", json);
                if (json[0].resultado == "exito") {
                    Swal.fire({
                        icon: "success",
                        title: "Usuarios",
                        text: "Contraseña Modificada con éxtio!",
                        allowOutsideClick: false,
                        confirmButtonText: "Ok",
                    }).then((confirmacion) => {
                        if (confirmacion) {
                            $("#formularioModContra").trigger("reset");
                            $("#mdModiContrasenas").modal("hide");
                            cargarDatos();
                        } else;
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Usuarios",
                        allowOutsideClick: false,
                        text: "Algo salió mal !",
                    });
                }
            });
        }

    });


    $(document).on("click", ".btnModificar", function (e) {
        e.preventDefault();
        var id = $(this).attr("data-idUsuario");
        console.log("El id es: ", id);
        var datos = { "consultar_datos": "si_coneste_id", "id": id };

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "Usuarios",
            data: datos,
        })
            .done(function (json) {
                console.log("EL consultar especifico", json);
                if (json[0].resultado == "exito") {

                    document.getElementById('idUsersM').readOnly = true;
                    $("#modificar_datos").val("modificarUsuario");
                    $("#idUsersM").val(json[0].idU);
                    $("#nombrePersonaM").val(json[0].nombreU);
                    $("#apellidoPersonaM").val(json[0].apellidoU);
                    $("#nombreUsuarioM").val(json[0].usuario);
                    $("#nivelUsuarioM").val(json[0].nivel);
                    $("#estado").val(json[0].estadoU);
                    $("#mdModiUsuarios").modal("show");
                }
            })
            .fail(function () {})
            .always(function () {});
    });

    $(document).on("click", ".btnModificarContra", function (e) {
        e.preventDefault();
        var id = $(this).attr("data-idUsuario");
        console.log("El id es: ", id);
        var datos = { "consultar_datos": "si_coneste_id", "id": id };

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "Usuarios",
            data: datos,
        })
            .done(function (json) {
                console.log("EL consultar especifico", json);
                if (json[0].resultado == "exito") {
                    $("#modificar_datos").val("modificarUsuario");
                    $("#id").val(json[0].idU);
                    $("#titleModal").empty().html("Modificar Contraseñas | Usuario | " + json[0].nombreU + ' ' + json[0].apellidoU);
                    $("#mdModiContrasenas").modal("show");
                }
            })
            .fail(function () {})
            .always(function () {});
    });

    $(document).on("click", ".btnEliminar", function (e) {
        Swal.fire({
            icon: "warning",
            title: "¿Esta seguro de Eliminar?",
            text: "Si Elimina el Usuario ya no podrá ingresar al sistema",

            showCancelButton: true,
            confirmButtonColor: "#DC3545",
            confirmButtonText: "Sí, eliminar",
            cancelButtonText: "Cancelar",
        }).then((resutl) => {
            if (resutl.isConfirmed) {
                e.preventDefault();
                var id = $(this).attr("data-idUsuario");
                console.log("El id es: ", id);
                var datos = {  "consultar_datos": "eliminarUsuario", "id": id };

                $.ajax({
                    dataType: "json",
                    method: "DELETE",
                    url: "Usuarios",
                    data: datos,
                })
                    .done(function (json) {
                        console.log("EL consultar especifico", json);
                        if (json[0].resultado == "exito") {
                            Swal.fire({
                                icon: "success",
                                title: "Usuarios",
                                text: "Eliminado con éxtio!",
                                allowOutsideClick: false,
                                confirmButtonText: "Ok",
                            }).then((confirmacion) => {
                                if (confirmacion) {
                                    cargarDatos();
                                } else;
                            });
                        } else if (json[0].resultado == "error") {
                            Swal.fire({
                                icon: "error",
                                title: "Usuarios",
                                allowOutsideClick: false,
                                text: "Algo salió mal !",
                            });
                        }
                    })
                    .fail(function () {})
                    .always(function () {});
            } else if (resutl.isDenied) {
                Swal.fire({
                    icon: "error",
                    title: "Alumnos",
                    allowOutsideClick: false,
                    text: "Algo salió mal !",
                });
            }
        });
    });


})

function mostrarCargando(titulo,mensaje=""){
    Swal.fire({
        title: titulo,
        html: mensaje,
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading()
        },
        willClose: () => {

        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer){
            console.log('I was closed by the timer')
        }
    })
}

function cargarDatos(){
    var datos = {"consultar_datos":"mostrarUsuarios"}
    console.log("datos enviados", datos);
    mostrarCargando("Procesando Solicitud","Espere mientras de cargan los datos")
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "Usuarios",
        data: datos,
    }).done(function (json) {
        console.log("EL consultar", json);
        if (json[0].resultado == "exito"){
            console.log("Entra");
            $("#tbUsuarios").empty().html(json[0].tabla);
            $("#tabla_usuarios").DataTable();
            $("#usuarios_registrados").empty().html(json[0].cantidad);
        }else{
            Swal.fire(
                'Error',
                'No se pueden cargar los datos',
                'error'
            );
        }


    })
        .fail(function () {})
        .always(function () {
            Swal.close();
        });
}