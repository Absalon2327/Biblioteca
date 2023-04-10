$(function(){
    console.log("El jquery en login funcionando");


    $(document).on("submit","#formLogin",function(e){

        e.preventDefault();
        var datos = $("#formLogin").serialize();
        $.ajax({
            dataType: "json",
            method: "POST",
            url:'Login',
            data : datos,
        }).done(function(json) {
            console.log("EL TRAER al logueo",json);
            if (json[0].resultado == "Exito") {
                Swal.fire({
                    icon: "success",
                    title: "Login",
                    text: "Bienvenido al Sistema" + json[0].first_name + ' ' + json[0].last_name,
                    allowOutsideClick: false,
                    confirmButtonText: "Ok",
                }).then((confirmacion) => {
                    if (confirmacion) {
                        var timer = setInterval(function(){
                            $(location).attr("href","Rutas?modulos=prestamos");
                            clearTimeout(timer);
                        },1000)
                    } else;
                });
            }else if (json[0].resultado == "Errodatosnoexisten") {
                Swal.fire({
                    icon: "info",
                    title: "Login",
                    allowOutsideClick: false,
                    text: "El usuario no existe",
                });
            }


        }).fail(function(){

        }).always(function(){
            Swal.close();
        });

    });
})