package vista;


import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.TreeCellRenderer;

public class CheckRenderer
        extends JPanel
        implements TreeCellRenderer {
    protected JCheckBox check;
    protected TreeLabel label;

    public CheckRenderer() {
        this.setLayout(null);
        this.check = new JCheckBox();
        this.add(this.check);
        this.label = new TreeLabel();
        this.add(this.label);
        this.check.setBackground(UIManager.getColor("Tree.textBackground"));
        this.label.setForeground(UIManager.getColor("Tree.textForeground"));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        String stringValue = tree.convertValueToText(value, isSelected, expanded, leaf, row, hasFocus);
        this.setEnabled(tree.isEnabled());
        this.check.setSelected(((CheckNode)value).isSelected());
        this.label.setFont(tree.getFont());
        this.label.setText(stringValue);
        this.label.setSelected(isSelected);
        this.label.setFocus(hasFocus);
        if (leaf) {
            this.label.setIcon(UIManager.getIcon("Tree.leafIcon"));
        } else if (expanded) {
            this.label.setIcon(UIManager.getIcon("Tree.openIcon"));
        } else {
            this.label.setIcon(UIManager.getIcon("Tree.closedIcon"));
        }
        return this;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d_check = this.check.getPreferredSize();
        Dimension d_label = this.label.getPreferredSize();
        return new Dimension(d_check.width + d_label.width, d_check.height < d_label.height ? d_label.height : d_check.height);
    }

    @Override
    public void doLayout() {
        Dimension d_check = this.check.getPreferredSize();
        Dimension d_label = this.label.getPreferredSize();
        int y_check = 0;
        int y_label = 0;
        if (d_check.height < d_label.height) {
            y_check = (d_label.height - d_check.height) / 2;
        } else {
            y_label = (d_check.height - d_label.height) / 2;
        }
        this.check.setLocation(0, y_check);
        this.check.setBounds(0, y_check, d_check.width, d_check.height);
        this.label.setLocation(d_check.width, y_label);
        this.label.setBounds(d_check.width, y_label, d_label.width, d_label.height);
    }

    @Override
    public void setBackground(Color color) {
        if (color instanceof ColorUIResource) {
            color = null;
        }
        super.setBackground(color);
    }


    public class TreeLabel
    extends JLabel {
        boolean isSelected;
        boolean hasFocus;

        @Override
        public void setBackground(Color color) {
            if (color instanceof ColorUIResource) {
                color = null;
            }
            super.setBackground(color);
        }

        @Override
        public void paint(Graphics g) {
            String str = this.getText();
            if (str != null && 0 < str.length()) {
                if (this.isSelected) {
                    g.setColor(UIManager.getColor("Tree.selectionBackground"));
                } else {
                    g.setColor(UIManager.getColor("Tree.textBackground"));
                }
                Dimension d = this.getPreferredSize();
                int imageOffset = 0;
                Icon currentI = this.getIcon();
                if (currentI != null) {
                    imageOffset = currentI.getIconWidth() + Math.max(0, this.getIconTextGap() - 1);
                }
                g.fillRect(imageOffset, 0, d.width - 1 - imageOffset, d.height);
                if (this.hasFocus) {
                    g.setColor(UIManager.getColor("Tree.selectionBorderColor"));
                    g.drawRect(imageOffset, 0, d.width - 1 - imageOffset, d.height - 1);
                }
            }
            super.paint(g);
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension retDimension = super.getPreferredSize();
            if (retDimension != null) {
                retDimension = new Dimension(retDimension.width + 3, retDimension.height);
            }
            return retDimension;
        }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
    }
}
}