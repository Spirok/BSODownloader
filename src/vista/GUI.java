package vista;


import javax.swing.*;
import java.awt.*;

/**
 * Created by martin on 4/07/16.
 */
public class GUI extends JFrame {

    public JTextField txtBusqueda;
    public JPanel panelMain;

    public JButton btnBuscarBanda;
    public JButton btnDescargar;
    public JTabbedPane tabbedPane1;

    public JProgressBar pbCargando;
    public JProgressBar pbDescarga;
    public JPanel panelTree;
    public JLabel infoDescarga;


    public GUI() {
        super("BSODownloader");

        this.setVisible(true);
        this.add(this.panelMain);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        final boolean showTabsHeader = false;


        tabbedPane1.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI(){
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
                if (showTabsHeader) {
                    return super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight);
                } else {
                    return 0;
                }
            }
            protected void paintTabArea(Graphics g,int tabPlacement,int selectedIndex){}
        });

        btnDescargar.setEnabled(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
