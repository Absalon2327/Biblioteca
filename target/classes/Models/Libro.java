package Models;

public class Libro {

    private String codigoLibro;
    private String tituloLibro;
    private Integer existencia;
    private CategoriaLibros codigoCategoria;
    private Double precio;
    private Autor codigoAutor;

    public Libro() {
    }

    public Libro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public Libro(String codigoLibro, String tituloLibro, Integer existencia, CategoriaLibros codigoCategoria, Double precio, Autor codigoAutor) {
        this.codigoLibro = codigoLibro;
        this.tituloLibro = tituloLibro;
        this.existencia = existencia;
        this.codigoCategoria = codigoCategoria;
        this.precio = precio;
        this.codigoAutor = codigoAutor;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public CategoriaLibros getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(CategoriaLibros codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Autor getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(Autor codigoAutor) {
        this.codigoAutor = codigoAutor;
    }
}
