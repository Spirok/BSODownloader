package modelo;

import vista.GUI;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * Clase que se encarga de la descarga de la banda sonora.
 * Se ejecuta por medio de un Thread.
 * Se le indica una ruta de destino (String rutaDestino), se crean carpetas correspondientes a los albums
 * si así lo requiere. Tambien se chequea si el archivo ya fue descargado.
 * Created by martin on 6/07/16.
 */
public class DescargaBandaSonora implements Runnable {

    private BandaSonora banda;
    private GUI vista;
    private String rutaDestino;
    private JProgressBar barra;
    private JLabel lblDescarga;

    private int descargasActual = 1;
    private int descargasTotal;

    // Constructor
    public DescargaBandaSonora(String rutaDestino, BandaSonora banda, GUI vista) {
        this.banda = banda;
        this.vista = vista;
        this.rutaDestino = rutaDestino;

        barra = vista.pbDescarga;
        lblDescarga = vista.infoDescarga;

        descargasTotal = banda.getCantidadTotalCanciones();
    }

    /*** !!!!!!!!!!!!! VALIDAR EL MKDIR !!!!!!!!!!!!!!!!!! **/
    @Override
    public void run() {
        String rutaBanda = rutaDestino + "/" + banda.getNombre();
        String rutaAlbum;
        String rutaCancion;
        File carpetaBase = new File(rutaBanda);

        if (!carpetaBase.exists()) carpetaBase.mkdir();

        System.out.println("\n\n\n");
        for (Album a : banda.getListaAlbum()) {
            rutaAlbum = rutaBanda + "/" + a.getNombre();
            if (a.getEstado()) {
                File carpetaAlbum = new File(rutaAlbum);
                if (!carpetaAlbum.exists()) carpetaAlbum.mkdir();
            }
            for (Cancion c : a.getListaCanciones()) {
                rutaCancion = rutaAlbum + "/" + c.getNombre();
                if (c.getEstado()) {
                    //System.out.println("Empezando descarga : " + c);
                    lblDescarga.setText("Descargando " + descargasActual + " / " + descargasTotal +"  [ " + c.getNombre() + " ]");
                    descargarMP3(c.getUrl(), rutaCancion);
                    descargasActual++;
                }
            }
        }
        System.out.println("Descarga completa");
        lblDescarga.setText("Descarga completada");
        System.out.println("\n\n\n");
    }

    /*
     * Metodo que descarga un mp3 remoto.
     * @param mp3Url String url del mp3
     * @param destino String lugar donde se almacena de forma local
     * @throws IOException
     */
    private void descargarMP3(String mp3Url, String destino) {
        try {
            int length;
            //URL url = new URL(mp3Url);
            URLConnection url = new URL(mp3Url).openConnection();
            File archivo = new File(destino);

            if (archivo.exists() && (url.getContentLength() == archivo.length()) ) {
                System.out.println("NO es necesario descargar " + mp3Url);
                return;
            }

            long fileSize = url.getContentLength();
            long totalBytesRead = 0;
            int percentCompleted;

            url.connect();
            InputStream is      = url.getInputStream();
            FileOutputStream os = new FileOutputStream(destino);
            byte[] b = new byte[2048];
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);

                totalBytesRead += length;
                percentCompleted = (int) (totalBytesRead * 100 / fileSize);
                //System.out.println("porcentaje : " + percentCompleted + "%");
                barra.setValue(percentCompleted);
            }
            is.close();
            os.close();

            System.out.println(mp3Url + " descargado ok");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
