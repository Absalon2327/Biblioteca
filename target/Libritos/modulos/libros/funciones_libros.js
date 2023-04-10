cargarDatos();
$(function () {
    console.log("esta funcionando libros");
    $(document).on("click", "#registrar_Libros", function (e){
        e.preventDefault();

        $("#formulario_registro").trigger("reset");
        document.getElementById('codigoLibro').readOnly=false;
        $("#exampleModalLabel").empty().html("Nuevo | Autor");
        $("#mdRegisLibros").modal("show");

    });
    const codigo= document.getElementById('codigoLibro');

    codigo.addEventListener('keyup',(e)=>{
        e.target.value=e.target.value.toUpperCase();
    })


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
})

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