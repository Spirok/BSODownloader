package modelo;


/**
 * Clase modelo que representa una cancion, la cual esta asociada a un album.
 */
public class Cancion extends Base {
    public Cancion(String nombre, String url) {
        super(nombre, url);
    }

    @Override
    public String toString() {
        return "Cancion " + super.toString();
    }
}