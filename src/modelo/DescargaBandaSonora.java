package modelo;

import vista.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static util.Utileria.descargarMP3;

/**
 * Created by martin on 6/07/16.
 */
public class DescargaBandaSonora implements Runnable {

    private BandaSonora banda;
    private GUI vista;
    private String rutaDestino;

    public DescargaBandaSonora(String rutaDestino, BandaSonora banda, GUI vista) {
        this.banda = banda;
        this.vista = vista;
        this.rutaDestino = rutaDestino;
    }

    @Override
    public void run() {
        String rutaBanda = rutaDestino + "/" + banda.getNombre();
        String rutaAlbum;
        String rutaCancion;
        File carpetaBase = new File(rutaBanda);
        if (!carpetaBase.exists()) carpetaBase.mkdir();



        System.out.println("\n\n\n");
        for (Album a : banda.getListaAlbum()) {
            //System.out.println(a);
            rutaAlbum = rutaBanda + "/" + a.getNombre();
            if (a.getEstado()) {
                System.out.println("deberia crear la carpeta para el album : " + a);
                File carpetaAlbum = new File(rutaAlbum);
                if (!carpetaAlbum.exists()) carpetaAlbum.mkdir();
            }
            for (Cancion c : a.getListaCanciones()) {
                rutaCancion = rutaAlbum + "/" + c.getNombre();
                if (c.getEstado()) {

                    System.out.println("Empezando descarga : " + c);
                    descargarMP3(c.getUrl(), rutaCancion);
                    //System.out.println("debo descargar : " + c);
                }
            }
        }
        System.out.println("\n\n\n");

    }
}
