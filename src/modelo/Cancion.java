package modelo;


/**
 * Clase modelo que representa una cancion, la cual esta asociada a un album.
 * Created by Spirok on 6/07/16.
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