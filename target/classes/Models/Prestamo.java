package Models;

import java.util.Date;

public class Prestamo {
    private Alumno carnetalumno;
    private Libro codigolibro;
    private Date fechaprestamo;
    private String codigoprestamo;
    private int cantidadprestamo;
    private Date fechadevolucion;

    public Prestamo() {
    }

    public Prestamo(Alumno carnetalumno, Libro codigolibro, Date fechaprestamo, String codigoprestamo, int cantidadprestamo, Date fechadevolucion) {
        this.carnetalumno = carnetalumno;
        this.codigolibro = codigolibro;
        this.fechaprestamo = fechaprestamo;
        this.codigoprestamo = codigoprestamo;
        this.cantidadprestamo = cantidadprestamo;
        this.fechadevolucion = fechadevolucion;
    }

    public Alumno getCarnetalumno() {
        return carnetalumno;
    }

    public void setCarnetalumno(Alumno carnetalumno) {
        this.carnetalumno = carnetalumno;
    }

    public Libro getCodigolibro() {
        return codigolibro;
    }

    public void setCodigolibro(Libro codigolibro) {
        this.codigolibro = codigolibro;
    }

    public Date getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public String getCodigoprestamo() {
        return codigoprestamo;
    }

    public void setCodigoprestamo(String codigoprestamo) {
        this.codigoprestamo = codigoprestamo;
    }

    public int getCantidadprestamo() {
        return cantidadprestamo;
    }

    public void setCantidadprestamo(int cantidadprestamo) {
        this.cantidadprestamo = cantidadprestamo;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }
}
