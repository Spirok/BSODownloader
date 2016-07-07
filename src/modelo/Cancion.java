package modelo;




public class Cancion extends Base {
    public Cancion(String nombre, String url) {
        super(nombre, url);
    }

    @Override
    public String toString() {
        return "Cancion " + super.toString();
    }
}