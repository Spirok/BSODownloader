package modelo;

import java.util.ArrayList;


public class BandaSonora extends Base {

    private ArrayList<Album> listaAlbum;

    public BandaSonora() {
        this(null, null);
    }

    public BandaSonora(String nombre, String url) {
        this(nombre, url, new ArrayList<>());
    }

    public BandaSonora(String nombre, String url, ArrayList<Album> lA) {
        super(nombre, url);
        this.listaAlbum = lA;
    }

    /**
     * Metodo que agrega un album a la lista
     * @param a Album que será agregado.
     */
    public void agregarAlbum(Album a) {
        System.out.println("Agregando..." + a);
        this.listaAlbum.add(a);
    }

    private String listaToString() {
        if (this.listaAlbum.isEmpty()) {
            return "Albums vacio";
        }
        StringBuffer s = new StringBuffer();
        for (Album a : this.listaAlbum) {
            s.append(a);
        }
        return s.toString();
    }

    public ArrayList<Album> getListaAlbum() {
        return this.listaAlbum;
    }

    @Override
    public String toString() {
        return "BandaSonora{" + super.toString() + "listaAlbum=" + this.listaToString() + '}';
    }

    /**
     * Metodo que retorna la cantidad total de albums y canciones de la banda sonora
     * @return int cantidad total.
     */
    public int getCantidadTotal() {
        int acc = 0;
        for (Album a : this.listaAlbum) { // por cada album
            ++acc;
            for (Cancion c : a.getListaCanciones()) { // por cada cancion del album
                ++acc;
            }
        }
        return acc;
    }

    /**
     * Metodo que borra las listas de albums y canciones de la banda sonora.
     */
    public void limpiarTodo() {
        // borrando canciones de cada album
        for (Album a : getListaAlbum()) {
            a.getListaCanciones().clear();
        }
        // borrando albums
        getListaAlbum().clear();

    }
}