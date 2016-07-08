package vista;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.*;

import modelo.Album;
import modelo.BandaSonora;
import modelo.Cancion;

/**
 * Clase UI que representa un JTree
 */
public class CheckNodeTree extends JPanel {

    private BandaSonora banda;
    private CheckNode[] nodes;
    private GUI vista;

    // Constructor
    public CheckNodeTree(BandaSonora b, GUI vista) {
        banda = b;
        this.vista = vista;

        this.crearNodos();
        this.establecerOrdenNodos();

        JTree tree = new JTree(nodes[0]);

        tree.setCellRenderer(new CheckRenderer());
        tree.getSelectionModel().setSelectionMode(1);
        tree.putClientProperty("JTree.lineStyle", "Angled");
        tree.addMouseListener(new NodeSelectionListener( tree));
        JScrollPane sp = new JScrollPane(tree);

        this.setLayout( new BorderLayout());

        this.add(sp, BorderLayout.CENTER);
    }

    /*
       Metodo que crea los nodos del jtree.
     */
    private void crearNodos() {
        this.nodes = new CheckNode[this.banda.getCantidadTotal() +1];
        nodes[0]   = new CheckNode(this.banda.getNombre());
        int i = 1;
        for (Album a : this.banda.getListaAlbum()) {
            nodes[i] = new CheckNode(a.getNombre());
            ++i;
            for (Cancion c : a.getListaCanciones()) {
                nodes[i] = new CheckNode(c.getNombre());
                ++i;
            }
        }
    }

    /*
        Metodo que establece el orden de los nodos.
        Album 1
          cancion 1
          cancion 2
          ...
        Album 2
          ...
        Etc..
     */
    private void establecerOrdenNodos() {
        int x = 1;
        int indiceAlbum = 1;
        for (Album a2 : this.banda.getListaAlbum()) {
            this.nodes[0].add(nodes[x]);
            ++x;
            for (Cancion c : a2.getListaCanciones()) {
                this.nodes[indiceAlbum].add(this.nodes[x]);
                ++x;
            }
            indiceAlbum = x;
        }
    }

    /*
        Metodo que muestra todos los nodos utilizados en el jtree
     */
    private void mostrarNodos() {
        for (int z = 0 ; z < this.nodes.length; z++) {
            System.out.println(nodes[z]);
        }
    }

    /**
     * Clase interna destinada al evento click del jtree
     */
    class NodeSelectionListener extends MouseAdapter {
        JTree tree;

        NodeSelectionListener(JTree tree) {
            this.tree = tree;
        }

        /*
            Click en algun nodo en el jtree
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            int y;
            int x = e.getX();
            int row = this.tree.getRowForLocation(x, y = e.getY());
            TreePath path = this.tree.getPathForRow(row);
            if (path != null) {
                CheckNode node = (CheckNode)path.getLastPathComponent();
                //System.out.println(node.toString());
                boolean isSelected = !node.isSelected();
                node.setSelected(isSelected);

                /* !!!gestionar click en raiz!!! */

                // seleccionó nodo de album
                if (path.getPath().length == 2) {
                    //System.out.println("SELECCIONO ALBUM!!!");
                    String nombreAlbum = path.getPath()[1].toString();
                    banda.setearEstadoAlbumJTree(nombreAlbum, isSelected);


                // seleccionó nodo de cancion
                } else if (path.getPath().length == 3) {
                    //System.out.println("SELECCIONO CANCION!!!");
                    String nombreAlbum   = path.getPath()[1].toString();
                    String nombreCancion = path.getPath()[2].toString();
                    banda.setearEstadoCancionJTree(nombreAlbum, nombreCancion, isSelected);
                }

                if (node.getSelectionMode() == 4) {
                    if (isSelected) {
                        this.tree.expandPath(path);
                    } else {
                        this.tree.collapsePath(path);
                    }
                }
                this.tree.revalidate();
                this.tree.repaint();
                ((DefaultTreeModel)this.tree.getModel()).nodeChanged(node);
                /*
                if (row == 0) {
                    this.tree.revalidate();
                    this.tree.repaint();
                }
                */
            }
            vista.btnDescargar.setEnabled(banda.estadoBandaSonora());
        }
    }


}