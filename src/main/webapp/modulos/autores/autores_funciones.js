cargarDatos();
$(function () {
    var fecha = new Date();
    console.log("esta funcionando");


    $(document).on("click", "#registrar_autor", function (e) {
        e.preventDefault();

        $("#formulario_registro").trigger("reset");
        $("#exampleModalLabel").empty().html("Nuevo | Autor");
        $("#mdRegisAutor").modal("show");
    });

    $(document).on("submit", "#formulario_registro", function (e) {
        e.preventDefault();
        mostrarCargando("Procesando Solicitud","Espere mientras se almacenan los datos")
        var datos = $("#formulario_registro").serialize();
        console.log("datos: ", datos);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "autores",
            data: datos,
        }).done(function (json) {
            console.log("EL GUARDAR", json);
            if (json[0].resultado == "exito") {
                Swal.fire({
                    icon: "success",
                    title: "Autores",
                    text: "Guardado con éxtio!",
                    allowOutsideClick: false,
                    confirmButtonText: "Ok",
                }).then((confirmacion) => {
                    if (confirmacion) {
                        $("#formulario_registro").trigger("reset");
                        $("#mdRegisAutor").modal("hide");
                        cargarDatos();
                    } else;
                });
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Autores",
                    allowOutsideClick: false,
                    text: "Algo salió mal !",
                });
            }
        });
    });

    $(document).on("click", ".btnModificar", function (e) {
        e.preventDefault();
        var id = $(this).attr("data-idAutor");
        console.log("El id es: ", id);
        var datos = { "consultar_datos": "si_consultar_info", "id": id };

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "autores",
            data: datos,
        })
            .done(function (json) {
                console.log("EL consultar especifico", json);
                if (json[0].resultado == "exito") {
                    $("#consultar_datos").val("modificarAutor");
                    $("#codigoAutor").val(json[0].idAutor);
                    document.getElementById("codigoAutor").readOnly = true;
                    $("#nombreAutor").val(json[0].nombreA);
                    $("#apellidoAutor").val(json[0].apellidoA);
                    $("#fechaNacimiento").val(json[0].fechaNaci);
                    $("#nacionalidadAutor").val(json[0].nacional);
                    $("#exampleModalLabel").empty().html("Modificar | Autor");
                    $("#mdRegisAutor").modal("show");
                }
            })
            .fail(function () {})
            .always(function () {});
    });

    $(document).on("click", ".btnEliminar", function (e) {
        Swal.fire({
            icon: "warning",
            title: "¿Esta seguro de Eliminar?",
            text: "Si Elimina el Autor ya no podrá usarlo",

            showCancelButton: true,
            confirmButtonColor: "#DC3545",
            confirmButtonText: "Sí, eliminar",
            cancelButtonText: "Cancelar",
        }).then((resutl) => {
            if (resutl.isConfirmed) {
                e.preventDefault();
                var id = $(this).attr("data-idAutor");
                console.log("El id es: ", id);
                var datos = {  "consultar_datos": "eliminarAutor", "id": id };

                $.ajax({
                    dataType: "json",
                    method: "POST",
                    url: "autores",
                    data: datos,
                })
                    .done(function (json) {
                        console.log("EL consultar especifico", json);
                        if (json[0].resultado == "exito") {
                            Swal.fire({
                                icon: "success",
                                title: "Autores",
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
                                title: "Autores",
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
                    title: "Categorías",
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
    var datos = {"consultar_datos":"mostrarAutores"}
    console.log("datos enviados", datos);
    mostrarCargando("Procesando Solicitud","Espere mientras de cargan los datos")
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "autores",
        data: datos,
    }).done(function (json) {
        console.log("EL consultar", json);
        if (json[0].resultado == "exito"){
            console.log("Entra");
            $("#tbAutores").empty().html(json[0].tabla);
            $("#tabla_autores").DataTable();
            $("#autores_registrados").empty().html(json[0].cantidad);
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