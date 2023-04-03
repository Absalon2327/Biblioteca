cargarDatos();
$(function () {
    var fecha = new Date();
    console.log("esta funcionando");

    $(document).on("click", "#registrar_categoria", function (e) {
        e.preventDefault();
        $("#formulario_registro").trigger("reset");
        $("#md_registrar_categoria").modal("show");
    });

    $(document).on("submit", "#formulario_registro", function (e) {
        e.preventDefault();
        mostrarCargando("Procesando Solicitud","Espere mientras se almacenan los datos")
        var datos = $("#formulario_registro").serialize();
        console.log("datos: ", datos);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "RegCatLib",
            data: datos,
        }).done(function (json) {
            console.log("EL GUARDAR", json);
            if (json[0].resultado == "exito") {
                Swal.fire({
                    icon: "success",
                    title: "Categorías",
                    text: "Guardada con éxtio!",
                    allowOutsideClick: false,
                    confirmButtonText: "Ok",
                }).then((confirmacion) => {
                    if (confirmacion) {
                        $("#md_registrar_categoria").modal("hide");
                        cargarDatos();
                    } else;
                });
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Categorías",
                    allowOutsideClick: false,
                    text: "Algo salió mal !",
                });
            }
        });
    });

    $(document).on("click", ".btnModificar", function (e) {
        e.preventDefault();
        var id = $(this).attr("data-idcategoria");
        console.log("El id es: ", id);
        var datos = { "consultar_datos": "si_consultar_info", "id": id };

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "RegCatLib",
            data: datos,
        })
            .done(function (json) {
                console.log("EL consultar especifico", json);
                if (json[0].resultado == "exito") {
                    $("#idmarca").val(json[0].idCat);
                    $("#consultar_datos").val("modificarCategoria");
                    $("#codigoCategoria").val(json[0].idCat);
                    document.getElementById("codigoCategoria").readOnly = true;
                    $("#nombreCategoria").val(json[0].idNombreCat);
                    $("#exampleModalLabel").empty().html("Modificar Categoría");
                    $("#md_registrar_categoria").modal("show");
                }
            })
            .fail(function () {})
            .always(function () {});
    });

    $(document).on("click", ".btnEliminar", function (e) {
        Swal.fire({
            icon: "warning",
            title: "¿Esta seguro de Eliminar?",
            text: "Si Elimina la Categoría ya no podrá usarla",

            showCancelButton: true,
            confirmButtonColor: "#DC3545",
            confirmButtonText: "Sí, eliminar",
            cancelButtonText: "Cancelar",
        }).then((resutl) => {
            if (resutl.isConfirmed) {
                e.preventDefault();
                var id = $(this).attr("data-idCategoria");
                console.log("El id es: ", id);
                var datos = {  "consultar_datos": "eliminarCategoria", "id": id };

                $.ajax({
                    dataType: "json",
                    method: "POST",
                    url: "RegCatLib",
                    data: datos,
                })
                    .done(function (json) {
                        console.log("EL consultar especifico", json);
                        if (json[0].resultado == "exito") {
                            Swal.fire({
                                icon: "success",
                                title: "Categorías",
                                text: "Eliminada con éxtio!",
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
                                title: "Categorías",
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

function cargarDatos(estado = 1){
    var datos = {"consultar_datos":"si_consultar_categorias","estado":estado}
    console.log("datos enviados", datos);
    mostrarCargando("Procesando Solicitud","Espere mientras de cargan los datos")
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "RegCatLib",
        data: datos,
    }).done(function (json) {
            console.log("EL consultar", json);
            if (json[0].resultado == "exito"){
                console.log("Entra");
                $("#tablita").empty().html(json[0].tabla);
                $("#tabla_categorias").DataTable();
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