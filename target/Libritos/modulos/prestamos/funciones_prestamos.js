$(function () {
    console.log("esta funcionando prestamos");
    cargarDatos();
});

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
}