/**
 * Created by martin on 4/07/16.
 */

import javax.swing.*;

import controlador.EventoMain;
import modelo.BandaSonora;
import vista.GUI;

/**
 * Aplicacion destinada a la facil descarga de soundtracks de peliculas/juegos de la pagina web :
 * http://downloads.khinsider.com/
 * Created by martin on 5/07/16.
 */
public class Main {

    public static void main(String[] args) {
        // cambiando el aspecto de swing
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // creando modelo banda sonora
        BandaSonora banda = new BandaSonora();

        // creando gui
        GUI gui = new GUI();

        // creando controlador
        EventoMain ev = new EventoMain(banda, gui);

    }
}