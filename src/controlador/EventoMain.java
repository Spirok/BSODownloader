package controlador;

import modelo.*;
import vista.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Clase encargada de los eventos de la vista principal de la App.
 * Created by martin on 5/07/16.
 */
public class EventoMain implements ActionListener {

    private GUI vista;
    private BandaSonora banda;

    // Constructor
    public EventoMain(BandaSonora b, GUI vista) {
        this.vista = vista;
        this.banda = b;

        vista.btnBuscarBanda.addActionListener(this);
        vista.btnDescargar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == vista.btnBuscarBanda) { /** click en boton buscar */
            vista.btnBuscarBanda.setEnabled(false);
            vista.btnDescargar.setEnabled(false);

            // limpio alumns y canciones de la banda si existian previamente
            banda.limpiarTodo();

            try {
                // borro el jtree del jpanel en caso de que existiera
                this.vista.panelCentro.remove(0);
            } catch (ArrayIndexOutOfBoundsException e) {
                //e.printStackTrace();
            }

            // creando barra de progreso
            JProgressBar barra = new JProgressBar();
            barra.setStringPainted(true);
            barra.setString("Analizando link...");
            barra.setIndeterminate(true);
            // añadiendo barra a la gui
            this.vista.panelCentro.add(barra);
            //actualizando gui
            vista.invalidate();
            vista.validate();
            vista.repaint();

            // hilo encargando del proceso de carga de la banda sonora
            new Thread(new ManejadorBandaSonora(banda, vista)).start();
        }

        if (actionEvent.getSource() == vista.btnDescargar) { /** click en boton descargar */

            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File yourFolder = fc.getSelectedFile();
                System.out.println(yourFolder);
            }
            /*
            FileDialog dialogoDirectorio = new java.awt.FileDialog((java.awt.Frame) null, "Elegir ruta", FileDialog.LOAD);
            dialogoDirectorio.setVisible(true);
            String directorio = dialogoDirectorio.getDirectory();

            if (directorio != null) {
                System.out.println("Elijio la ruta : " + directorio);
                //new Thread(new DescargaBandaSonora(directorio, banda, vista)).start();
            }
            */
        }
    }
}
