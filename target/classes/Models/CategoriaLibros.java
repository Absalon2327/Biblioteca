package Models;

public class CategoriaLibros {
    private String codigocategoria;
    private  String nombrecategoria;

    public CategoriaLibros() {
    }

    public CategoriaLibros(String codigocategoria, String nombrecategoria) {
        this.codigocategoria = codigocategoria;
        this.nombrecategoria = nombrecategoria;
    }

    public CategoriaLibros(String codigocategoria) {
        this.codigocategoria = codigocategoria;
    }

    public String getCodigocategoria() {
        return codigocategoria;
    }

    public void setCodigocategoria(String codigocategoria) {
        this.codigocategoria = codigocategoria;
    }

    public String getNombrecategoria() {
        return nombrecategoria;
    }

    public void setNombrecategoria(String nombrecategoria) {
        this.nombrecategoria = nombrecategoria;
    }
}
