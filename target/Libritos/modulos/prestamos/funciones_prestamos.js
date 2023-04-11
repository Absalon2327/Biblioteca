cargarDatos();
$(function () {

    console.log("esta funcionando prestamos");

    $(document).on("click", "#registrar_prestamos", function (e){
        e.preventDefault();

        $("#formulario_registro").trigger("reset");
        document.getElementById('codigoPrestamo').readOnly=false;
        $("#exampleModalLabel").empty().html("Nuevo | Prestamo");
        $("#consultar_datos").val("insertarPrestamos");
        $("#mdRegisPrestamo").modal("show");
    });

    const codigo = document.getElementById('codigoPrestamo');

    codigo.addEventListener('keyup', (e)=>{
        e.target.value=e.target.value.toUpperCase();
    })


    $(document).on("submit", '#formulario_registro', function (e){
        e.preventDefault();
        mostrarCargando("Procesando solicitud", "Espere mientras se almacenan los datos")
        var datos = $("#formulario_registro").serialize();
        console.log("datos", datos);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "Prestamos",
            data: datos,
        }).done(function (json){
            console.log("EL GUARDAR", json);
            if(json[0].resultado=="exito"){

                Swal.fire({
                    icon: "success",
                    title: "Prestamos",
                    text: "Guardado con exito!",
                    allowOutsideClick: false,
                    confirmButtonText: "Ok",
                }).then((confirmacion)=>{
                    if(confirmacion){
                        $("#formulario_registro").trigger("reset");
                        $("#mdRegisPrestamo").modal("hide");
                        cargarDatos();
                    }else;
                });
            }else{
                Swal.fire({
                    icon: "error",
                    title: "Prestamos",
                    allowOutsideClick: false,
                    text: "Algo salio mal",
                });
            }
        });
    });

    $(document).on("click", ".btnModificar", function (e) {
        e.preventDefault();
        var id = $(this).attr("data-idPrestamo");
        console.log("El id es: ", id);
        var datos = { "id": id };

        $.ajax({
            dataType: "json",
            method: "GET",
            url: "Prestamos",
            data: datos,
        })
            .done(function (json) {
                console.log("EL consultar especifico", json);
                if (json[0].resultado == "exito") {

                    document.getElementById('codigoPrestamo').readOnly = true;
                    $("#consultar_datos").val("modificarPrestamo");
                    $("#codigoPrestamo").val(json[0].codigo);
                    $("#codigoLibro").val(json[0].libro);
                    $("#carnetAlumno").val(json[0].alumno);
                    $("#cantidadPrestamo").val(json[0].cantidad);
                    $("#fechaPrestamo").val(json[0].fechaPrestamo);
                    $("#fechaDevolucion").val(json[0].fechaDevo);
                    $("#exampleModalLabel").empty().html("Modificar | Prestamo");
                    $("#mdRegisPrestamo").modal("show");
                }
            })
            .fail(function () {})
            .always(function () {});
    });

    $(document).on("click", ".btnEliminar", function (e) {
        Swal.fire({
            icon: "warning",
            title: "¿Esta seguro de Eliminar?",
            text: "Si Elimina el prestamo ya no podrá usarlo",

            showCancelButton: true,
            confirmButtonColor: "#DC3545",
            confirmButtonText: "Sí, eliminar",
            cancelButtonText: "Cancelar",
        }).then((resutl) => {
            if (resutl.isConfirmed) {
                e.preventDefault();
                var id = $(this).attr("data-idPrestamo");
                console.log("El id es: ", id);
                var datos = {  "id": id };

                $.ajax({
                    dataType: "json",
                    method: "DELETE",
                    url: "Prestamos",
                    data: datos,
                })
                    .done(function (json) {
                        console.log("EL consultar especifico", json);
                        if (json[0].resultado == "exito") {
                            Swal.fire({
                                icon: "success",
                                title: "Prestamos",
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
                                title: "Prestamos",
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
                    title: "Prestamos",
                    allowOutsideClick: false,
                    text: "Algo salió mal !",
                });
            }
        });
    });
})


function cargarDatos() {
    var datos = {"consultar_datos": "mostrarPrestamos"}
    console.log("datos enviados", datos);
    mostrarCargando("Procesando Solicitud", "Espere mientras de cargan los datos");
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "Prestamos",
        data: datos,
    }).done(function (json) {
        console.log("EL consultar", json);
        if (json[0].resultado == "exito") {
            $("#tbPrestamos").empty().html(json[0].tabla);
            $("#tabla_prestamos").DataTable();
            $("#prestamos_registrados").empty().html(json[0].cantidad);
            console.log("Entra, cantidad es: "+ json[0].cantidad);

        } else {
            Swal.fire(
                'Error',
                'No se pueden cargar los datos',
                'error'
            );
        }
    }).fail(function () {
    }).always(function () {
        Swal.close();
    });
}

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