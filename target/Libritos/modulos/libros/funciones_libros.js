cargarDatos();
$(function () {
    console.log("esta funcionando libros");

    $(document).on("click", ".regis_libros", function (e){
        e.preventDefault();

        $("#formulario_registro").trigger("reset");
        document.getElementById('codigoLibro').readOnly=false;
        $("#exampleModalLabel").empty().html("Nuevo | Libro");
        $("#mdRegisLib").modal("show");
    });

    $(document).on('click', '#registrarLibro', function (e){
        e.preventDefault();
        $("#formulario_registro").trigger("reset");
        document.getElementById('codigoLibro').readOnly=false;
        $("#exampleModalLabel").empty().html("Nuevo | Libro");
        $("#mdRegistroLibros").modal('show');
    });
    const codigo= document.getElementById('codigoLibro');

    codigo.addEventListener('keyup',(e)=>{
        e.target.value=e.target.value.toUpperCase();
    });



    //formulario de Libro
    $(document).on("submit", '#formulario_registro', function (e){
        e.preventDefault();
        mostrarCargando("Procesando solicitud", "Espere mientras se almacenan los datos")
        var datos= $("#formulario_registro").serialize();
        console.log("datos", datos);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "Libros",
            data: datos,
        }).done(function (json){
            console.log("EL GUARDAR", json);
            if(json[0].resultado=="exito"){
                Swal.fire({
                    icon: "success",
                    title: "Libros",
                    text: "Guardado con exito!",
                    allowOutsideClick: false,
                    confirmButtonText: "Ok",
                }).then((confirmacion)=>{
                    if (confirmacion){
                        $("#formulario_registro").trigger("reset");
                        $("#mdRegisLibros").modal("hide");
                        cargarDatos();
                    }else;
                });
            }else{
                Swal.fire({
                    icon: "error",
                    title: "Libros",
                    allowOutsideClick: false,
                    text: "Algo salio mal!",

                });
            }
        });
    });

    $(document).on("click", ".btnModificar", function (e) {
        e.preventDefault();
        var id = $(this).attr("data-idLibro");
        console.log("El id es: ", id);
        var datos = { "consultar_datos": "si_consultar_info", "id": id };

        $.ajax({
            dataType: "json",
            method: "GET",
            url: "Libros",
            data: datos,
        })
            .done(function (json) {
                console.log("EL consultar especifico", json);
                if (json[0].resultado == "exito") {
                    console.log("el estado: ", json[0].estadoAl );
                    let estado = "";
                    if (json[0].estadoAl == "t"){
                        estado = "Activo";
                    }else{
                        estado = "Inactivo";
                    }
                    document.getElementById('carnetAlumno').readOnly = true;
                    $("#consultar_datos").val("modificarAlumno");
                    $("#carnetAlumno").val(json[0].canetAl);
                    $("#nombreAlumno").val(json[0].nombreAl);
                    $("#apellidoAlumno").val(json[0].apellidoAl);
                    $("#direccionAlumno").val(json[0].direcAl);
                    $("#fechaNacimientoAlumno").val(json[0].fechaNaciAl);
                    $("#fechaIngreso").val(json[0].fechaInAl);
                    $("#generoAlumno").val(json[0].generoAl);
                    $("#apellidoAutor").val(json[0].estadoAl);
                    $("#estadoAlumno").val(estado);
                    $("#exampleModalLabel").empty().html("Modificar | Alumno");
                    $("#mdRegisAlumnos").modal("show");
                }
            })
            .fail(function () {})
            .always(function () {});
    });

    $(document).on("click", ".btnEliminar", function (e) {
        Swal.fire({
            icon: "warning",
            title: "¿Esta seguro de Eliminar?",
            text: "Si Elimina el Alumno ya no podrá usarlo",

            showCancelButton: true,
            confirmButtonColor: "#DC3545",
            confirmButtonText: "Sí, eliminar",
            cancelButtonText: "Cancelar",
        }).then((resutl) => {
            if (resutl.isConfirmed) {
                e.preventDefault();
                var id = $(this).attr("data-idAlumno");
                console.log("El id es: ", id);
                var datos = {"consultar_datos": "eliminarAutor", "id": id};

                $.ajax({
                    dataType: "json",
                    method: "POST",
                    url: "Alumnos",
                    data: datos,
                })
                    .done(function (json) {
                        console.log("EL consultar especifico", json);
                        if (json[0].resultado == "exito") {
                            Swal.fire({
                                icon: "success",
                                title: "Alumnos",
                                text: "Eliminado con éxtio!",
                                allowOutsideClick: false,
                                confirmButtonText: "Ok",
                            }).then((confirmacion) => {
                                if (confirmacion) {
                                    cargarDatos();
                                } else ;
                            });
                        } else if (json[0].resultado == "error") {
                            Swal.fire({
                                icon: "error",
                                title: "Alumnos",
                                allowOutsideClick: false,
                                text: "Algo salió mal !",
                            });
                        }
                    })
                    .fail(function () {
                    })
                    .always(function () {
                    });
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
});




function cargarDatos() {
    var datos = {"consultar_datos": "mostrarLibros"}
    console.log("datos enviados", datos);
    mostrarCargando("Procesando Solicitud", "Espere mientras de cargan los datos");
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "Libros",
        data: datos,
    }).done(function (json) {
        console.log("EL consultar", json);
        if (json[0].resultado == "exito") {
            $("#tbLibros").empty().html(json[0].tabla);
            $("#tabla_libros").DataTable();
            $("#libros_registrados").empty().html(json[0].cantidad);
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