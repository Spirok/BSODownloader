package util;


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


}
