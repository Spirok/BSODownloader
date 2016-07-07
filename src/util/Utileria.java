package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Clase de utiliria de la App.
 * Created by martin on 5/07/16.
 */
public class Utileria {

    /**
     * Metodo que retorna el nombre de la banda sonora segun la url especificada.
     * Ej :
     *  http://downloads.khinsider.com/diablo
     * Retorna :
     *  diablo
     * @param url de la banda sonora
     * @return String nombre de la banda
     */
    public static String parseNombre(String url) {
        return url.substring(url.lastIndexOf("/") + 1, url.length());
    }

    /**
     * Metodo que retorna la url de un href especificado.
     * Ej :
     *  <a href="http://downloads.khinsider.com/game-soundtracks/album/diablo-hellfire">Diablo - Hellfire</a>
     * Retorna :
     *   http://downloads.khinsider.com/game-soundtracks/album/diablo-hellfire
     * @param href
     * @return
     */
    public static String hrefToUrl(String href) {
        return href.substring(href.indexOf('"') + 1, href.lastIndexOf('"'));
    }

    /**
     * Metodo que retorna la url remota del mp3, el cual esta contenida en un tag <audio></audio>
     * Ej :
     *  <audio id="audio1" src="http://66.90.91.26/ost/diablo-the-music-of-1996-2011-diablo-15-year-anniversary/vahbhjivuf/01-diablo-intro.mp3" controls="" preload="auto" autobuffer="">
       </audio>
        Retorna :
     http://66.90.91.26/ost/diablo-the-music-of-1996-2011-diablo-15-year-anniversary/vahbhjivuf/01-diablo-intro.mp3
     * @param url String tag audio
     * @return String mp3 src remoto
     */
    public static String urlToSourceMP3(String url) {
        return url.substring(url.indexOf("src=") + 5, url.indexOf("mp3") + 3);
    }

    /**
     * Metodo que descarga un mp3 remoto.
     * @param mp3Url String url del mp3
     * @param destino String lugar donde se almacena de forma local
     * @throws IOException
     */
    public static void descargarMP3(String mp3Url, String destino) {
        try {
            int length;
            URL url = new URL(mp3Url);
            InputStream is = url.openStream();
            FileOutputStream os = new FileOutputStream(destino);
            byte[] b = new byte[2048];
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();
            System.out.println(mp3Url + " descargado ok");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
