package modelo;

import java.util.ArrayList;


/**
 * Clase modelo que representa una banda sonora, la cual tiene una lista de albums.
 * Cada uno de estos ademas tiene su respectiva lista de canciones.
 */
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

    public ArrayList<Album> getListaAlbum() { return this.listaAlbum; }


    /**
     * Metodo que agrega un album a la lista
     * @param a Album que será agregado.
     */
    public void agregarAlbum(Album a) {
        System.out.println("Agregando..." + a);
        this.listaAlbum.add(a);
    }

    /**
     * Metodo que retorna boolean indicando el estado para el boton descargar.
     * (Si no hay ningun album/cancion seleccionado retorna false, true caso contrario)
     * @return
     */
    public boolean estadoBandaSonora() {
        for (Album a : this.getListaAlbum()) {
            if (a.getEstado()) return true;
            for (Cancion c : a.getListaCanciones()) {
                if (c.getEstado()) return true;
            }
        }
        return false;
    }

    /**
     * Metodo que recorre todas las canciones de un album y determina el estado del mismo
     * (si no existen canciones con estado = true el estado de album cambia a false)
     * @param a
     */
    public void determinarEstadoAlbum(Album a) {
        boolean estado = false;
        for (Cancion c: a.getListaCanciones()) {
            if (c.getEstado()) {
                estado = true;
                break;
            }
        }
        a.setEstado(estado);
    }

    /**
     * Metodo que setea el estado de todos los albums de la banda sonora.
     * @param est
     */
    public void setearEstadoTodosAlbums(boolean est) {
        for (Album a : this.getListaAlbum()) {
            a.setEstado(est);
            for (Cancion c : a.getListaCanciones()) {
                c.setEstado(est);
            }
        }
    }

    /**
     * Metodo que retorna la cantidad total de albums y canciones de la banda sonora
     * @return int cantidad total.
     */
    public int getCantidadTotal() {
        int acc = 0;
        for (Album a : this.getListaAlbum()) { // por cada album
            ++acc;
            for (Cancion c : a.getListaCanciones()) { // por cada cancion del album
                ++acc;
            }
        }
        return acc;
    }

    /**
     * Metodo que retorna la cantidad total de canciones con estado true (que serán descargadas).
     * @return
     */
    public int getCantidadTotalCanciones() {
        int acc = 0;
        for (Album a : this.getListaAlbum()) {
            if (!a.getEstado()) continue;
            for (Cancion c : a.getListaCanciones()) {
                if (c.getEstado()) acc++;
            }
        }
        return  acc;
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

    /**
     * Metodo que setea el estado de un album (y todas sus canciones).
     * Es utilizado en la vista jtree, por eso mismo se utiliza el String nombreAlbum
     * @param nombreAlbum String nombre del album a editar su estado.
     * @param estado boolean estado del album y de todas sus canciones.
     */
    public void setearEstadoAlbumJTree(String nombreAlbum, boolean estado) {
        for (Album a : this.getListaAlbum()) {
            // busco el album seleccionado
            if (a.getNombre().equals(nombreAlbum)) {
                //System.out.println(isSelected + " album " + nombreAlbum);
                // seteo estado del album selecionado
                a.setEstado(estado);
                // seteo estado de todas las canciones del album
                for (Cancion c : a.getListaCanciones()) {
                    c.setEstado(estado);
                }
                break;
            }
        }
    }

    /**
     * Metodo que setea el estado de una cancion.
     * Es utilizado en la vista jree, por eso mismo se utiliza String nombreAlbum y nombreCancion.
     * Si toma en cuenta que puede seleccionar una cancion de un album cuyo estado sea false, en ese
     * caso estado de album y cancion serán true
     * @param nombreAlbum String nombre del album
     * @param nombreCancion String nombre de la cancion
     * @param estado boolean estado.
     */
    public void setearEstadoCancionJTree(String nombreAlbum, String nombreCancion, boolean estado) {
        for (Album a : this.getListaAlbum()) {
            // busco el album de la cancion
            if (a.getNombre().equals(nombreAlbum)) {
                // si selecciono una cancion entonces activo el estado de ese album
                if (estado && !a.getEstado()) a.setEstado(true);
                for (Cancion c : a.getListaCanciones()) {
                    // busco la cancion
                    if (c.getNombre().equals(nombreCancion)) {
                        c.setEstado(estado);
                        this.determinarEstadoAlbum(a);
                        //System.out.println(isSelected + " album " + nombreAlbum + " cancion " + c);
                        break;
                    }
                }
                break;
            }
        }
    }


    @Override
    public String toString() {
        return "BandaSonora{" + super.toString() + "listaAlbum=" + this.listaToString() + '}';
    }
}