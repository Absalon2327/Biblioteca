cargarDatos();
$(function () {

    console.log("esta funcionando prestamos");

    $(document).on("click", "#registrar_prestamos", function (e){
        e.preventDefault();

        $("#formulario_registro").trigger("reset");
        document.getElementById('codigoPrestamo').readOnly=false;
        $("#exampleModalLabel").empty().html("Nuevo | Prestamo");
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