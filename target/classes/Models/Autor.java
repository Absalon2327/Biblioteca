package Models;

import java.util.Calendar;
import java.util.Date;

public class Autor {
    private String codigoAutor;
    private String nombreAutor;
    private String apellidoAutor;
    private Date fechaNacimientoAutor;
    private String nacionalidad;

    public Autor() {
    }

    public Autor(String codigoAutor, String nombreAutor, String apellidoAutor, Date fechaNacimientoAutor, String nacionalidad) {
        this.codigoAutor = codigoAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.fechaNacimientoAutor = fechaNacimientoAutor;
        this.nacionalidad = nacionalidad;
    }

    public Autor(String codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public String getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(String codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }

    public Date getFechaNacimientoAutor() {
        return fechaNacimientoAutor;
    }

    public void setFechaNacimientoAutor(Date fechaNacimientoAutor) {
        this.fechaNacimientoAutor = fechaNacimientoAutor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String calcularEdad(String fechaNacimiento){
        String edad;
        String separador = "-";
        Calendar cal= Calendar.getInstance();
        int añoActual= cal.get(Calendar.YEAR);
        String[] separacion = fechaNacimiento.split(separador);
        edad =  String.valueOf(añoActual - Integer.parseInt(separacion[0]));
        return edad;
    }
}
