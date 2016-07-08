package modelo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.Utileria;
import vista.CheckNodeTree;
import vista.GUI;

import javax.swing.*;
import java.io.IOException;

/**
 * Clase que se encarga de la carga de la banda sonora.
 * Esta destinada a procesar una banda sonora de un juego/pelicula y descargar todas sus canciones desde :
 * (http://downloads.khinsider.com/{bandaSonora}).
 * Basicamente se interpreta la banda sonora indicada en la url, se leen todos los album/s asi como sus canciones y
 * se cargan en la logica/modelo de la App. (vease clases : BandaSonora, Album, Cancion).
 * El procesamiento se realiza por medio de un Thread.
 * Created by martin on 5/07/16.
 */
public class CargaBandaSonora implements Runnable {

    private BandaSonora banda;
    private GUI vista;

    // Constructor
    public CargaBandaSonora(BandaSonora b, GUI vista) {
        this.banda   = b;
        this.vista = vista;
    }

    /**
     * Metodo que carga el modelo de la banda sonora especificada.
     */
    @Override
    public void run() {

        System.out.println("INICIANDO HILO CARGA DE BANDA SONORA");
        try {
            String url = vista.txtBusqueda.getText();
            this.banda.setNombre(Utileria.parseNombre(url));
            this.banda.setUrl(url);

            Document docGeneral = Jsoup.connect(url).get();
            Elements hrefGral = docGeneral.select("a[href]");

            // por cada tag <a > en el documento
            for (Element linkGral : hrefGral) {
                String sGral = linkGral.toString();
                if (!sGral.contains("album") || sGral.contains("random")) continue;

                // obteniendo el album
                sGral       = Utileria.hrefToUrl(sGral);
                Album album = new Album(Utileria.parseNombre(sGral), sGral);
                this.banda.agregarAlbum(album);

                // procesando el documento html del album, el cual contiene la lista de todos los temas
                Document docAlbum = Jsoup.connect(sGral).get();
                Elements hrefEsp  = docAlbum.select("a[href]");

                int garca = 0;
                // por cada tag <a > en el documento
                for (Element linkEsp : hrefEsp) {
                    String sEsp = linkEsp.toString();
                    if (!sEsp.contains(".mp3") || sEsp.contains("Download")) continue;

                    garca++;
                    if (garca == 3) { break; }
                    sEsp = Utileria.hrefToUrl(sEsp);

                    // procesando el documento html que contiene el audio especifico de un tema
                    Document docEspecifico = Jsoup.connect(sEsp).get();
                    Elements eAudio  = docEspecifico.select("audio");
                    String urlRemoto = eAudio.first().toString();
                    String mp3Remoto = Utileria.urlToSourceMP3(urlRemoto);
                    String nombre    = Utileria.parseNombre(mp3Remoto);
                    // agregando cancion al album
                    album.agregarCancion( new Cancion(nombre, mp3Remoto) );
                }
            }
            cargarJTree();
            vista.tabbedPane1.setSelectedIndex(2);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error al cargar banda sonora.");
        }
        System.out.println("TERMINO HILO CARGA BANDA SONORA");
    }

    /*
     * Metodo que carga el JTree con la vista de la banda sonora
     */
    private void cargarJTree() {
        CheckNodeTree panelT = new CheckNodeTree(banda, vista);

        try {
            // borro el jtree del jpanel en caso de que existiera
            vista.panelTree.remove(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            //e.printStackTrace();
        }

        vista.panelTree.add(panelT);

        vista.validate();
        vista.repaint();

        vista.btnBuscarBanda.setEnabled(true);
    }
}
