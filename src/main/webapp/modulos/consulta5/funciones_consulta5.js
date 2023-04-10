cargarDatos();
$(function () {
    console.log("esta funcionando consulta");

    cargarSelect();
    $(document).on("change","#id_categoria",function (e) {
        cargarDatos($("#id_categoria").val());
    });

});

function cargarSelect() {
    var datos = {"consultar_datos": "si_consulta_categoria"};
    console.log("datos enviados: ", datos);
    $.ajax({
        dataType: "json",
        type: "POST",
        url: "Consulta5",
        data: datos
    }).done(function (json) {
        Swal.close();
        console.log("datos del select: ", json);
        if (json[0].resultado === "exito") {
            $("#id_categoria").empty().html(json[0].opciones);
        } else {
            Swal.fire('Error', 'No se puede cargar la tabla', 'error');
        }
    }).fail(function () {
    }).always(function () {
    })
}

function cargarDatos(id_categoria) {
    var datos = {"consultar_datos": "mostrarConsulta", "id_categoria": id_categoria}
    console.log("datos enviados", datos);
    mostrarCargando("Procesando Solicitud", "Espere mientras de cargan los datos");
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "Consulta5",
        data: datos,
    }).done(function (json) {
        console.log("EL consultar", json);
        if (json[0].resultado == "exito") {
            $("#tbConsulta").empty().html(json[0].tabla);
            $("#tabla_consulta").DataTable();
            $("#consulta_registrados").empty().html(json[0].cantidad);
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