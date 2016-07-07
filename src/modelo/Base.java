package modelo;

/**
 * Created by martin on 4/07/16.
 */
public abstract class Base {
    private String nombre;
    private String url;

    private boolean estado;

    public Base(String nombre, String url) {
        this(nombre, url, false);
    }

    public Base(String nombre, String url, boolean estado) {
        this.nombre = nombre;
        this.url    = url;
        this.estado = estado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean getEstado() { return this.estado; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "{" +
                "nombre='" + nombre + '\'' +
                ", url='" + url + '\'' +
                ", estado=" + estado +
                '}';
    }
}
