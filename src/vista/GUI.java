package vista;


import javax.swing.*;

/**
 * Created by martin on 4/07/16.
 */
public class GUI extends JFrame {

    public JTextField txtBusqueda;
    public JPanel panelMain;
    public JPanel panelCentro;
    public JButton btnBuscarBanda;
    public JButton btnDescargar;


    public GUI() {
        super("BSODownloader");

        this.setVisible(true);
        this.add(this.panelMain);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        btnDescargar.setEnabled(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



}
