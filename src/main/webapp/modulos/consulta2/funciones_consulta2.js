cargarDatos();
$(function (){
    console.log("esta funcionando consulta");

    $(document).on("change", "#fechaPrestam", function (e){
        cargarDatos($("#fechaPrestam").val());
    });
});
function cargarDatos(fechaPrestam){
    var datos={"consultar_datos": "mostrarconsulta", "fechaPrestam": fechaPrestam}
    console.log("datos enviados", datos);
    mostrarCargando("Procesando solicitud", "Espere mientras se cargan lo datos");
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "Consulta2",
        data: datos,
    }).done(function (json){
        console.log("El consultar", json);
        if(json[0].resultado == "exito"){
            $("#tbConsulta").empty().html(json[0].tabla);
            $("#tabla_consulta").DataTable();
            $("#consulta_registrados").empty().html(json[0].cantidad);
            console.log("Entra, cantidad es "+ json[0].cantidad);
        }else{
            Swal.fire(
                'Error',
                'No se pueden cargar los datos',
                'error'
            );
        }
    }).fail(function (){

    }).always(function (){
        Swal.close();

    });
    function mostrarCargando(titulo, mensaje=""){
        Swal.fire({
            title: titulo,
            html: mensaje,
            timer: 2000,
            timerProgressBar: true,
            didOpen: ()=>{
                Swal.showLoading()
            },
            willClose: ()=>{

            }
        }).then((result)=>{
            if(result.dismiss === Swal.DismissReason.timer){
                console.log('I was closed by the timer')
            }
        })
    }
}