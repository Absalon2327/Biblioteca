package Models;

import java.util.Calendar;
import java.util.Date;

public class Alumno {
    private String carnet;
    private String nombreAlumno;
    private String apellidoAlumno;
    private String direccion;
    private Date fechaNacimientoAlumno;
    private Date fechaIngreso;
    private String genero;
    private Boolean estado;

    public Alumno() {
    }

    public Alumno(String carnet, String nombreAlumno, String apellidoAlumno, String direccion, Date fechaNacimientoAlumno,
                  Date fechaIngreso, String genero, Boolean estado) {
        this.carnet = carnet;
        this.nombreAlumno = nombreAlumno;
        this.apellidoAlumno = apellidoAlumno;
        this.direccion = direccion;
        this.fechaNacimientoAlumno = fechaNacimientoAlumno;
        this.fechaIngreso = fechaIngreso;
        this.genero = genero;
        this.estado = estado;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimientoAlumno() {
        return fechaNacimientoAlumno;
    }

    public void setFechaNacimientoAlumno(Date fechaNacimientoAlumno) {
        this.fechaNacimientoAlumno = fechaNacimientoAlumno;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    public String estadoAlumno(Boolean estadoA){
        String estadoB = "";
        if (String.valueOf(estadoA) == "t"){
            estadoB = "Activo";
        }else {
            estadoB = "Inactivo";
        }
        return estadoB;
    }
}
