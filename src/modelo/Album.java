package modelo;


import java.util.ArrayList;


/**
 * Clase modelo que representa un album, la cual contiene una lista de canciones.
 * Created by Spirok on 6/07/16.
 */
public class Album extends Base {

    private ArrayList<Cancion> listaCanciones;

    public Album(String nombre, String url) {
        this(nombre, url, new ArrayList<>());
    }

    public Album(String nombre, String url, ArrayList<Cancion> lC) {
        super(nombre, url);
        this.listaCanciones = lC;
    }

    /**
     * Metodo que agrega una cancion a la lista
     * @param c Cancion que será agregada
     */
    public void agregarCancion(Cancion c) {
        System.out.println("\t\tagregando " + c);
        this.listaCanciones.add(c);
    }

    private String listaToString() {
        if (this.listaCanciones.isEmpty()) {
            return "Albums vacio";
        }
        StringBuffer s = new StringBuffer();
        for (Cancion c : this.listaCanciones) {
            s.append(c);
        }
        return s.toString();
    }


    public ArrayList<Cancion> getListaCanciones() {
        return this.listaCanciones;
    }

    @Override
    public String toString() {
        return "Album{" + super.toString() + '}'; //  " " + "listaCanciones=" + this.listaToString() +
    }
}