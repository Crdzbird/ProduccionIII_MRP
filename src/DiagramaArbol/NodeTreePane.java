/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagramaArbol;

import static Interfaces.Interfaz_GestionMateriales.returnFontMetrics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javax.swing.JComponent;
import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.TreeLayout;

/**
 *
 * @author crdzbird
 */
public class NodeTreePane extends JComponent {

    private final TreeLayout<Nodos> treeLayout;

    private TreeForTreeLayout<Nodos> getTree() {
        return treeLayout.getTree();
    }

    private Iterable<Nodos> getChildren(Nodos parent) {
        return getTree().getChildren(parent);
    }

    private Rectangle2D.Double getBoundsOfNode(Nodos node) {
        return treeLayout.getNodeBounds().get(node);
    }

    public NodeTreePane(TreeLayout<Nodos> treeLayout) {
        this.treeLayout = treeLayout;

        Dimension size = treeLayout.getBounds().getBounds().getSize();
        setPreferredSize(size);
    }

    public NodeTreePane(TreeLayout<Nodos> treeLayout, Color BOX_COLOR, Color BORDER_COLOR, Color TEXT_COLOR) {
        this.treeLayout = treeLayout;

        Dimension size = treeLayout.getBounds().getBounds().getSize();
        setPreferredSize(size);
    }

    private final static int ARC_SIZE = 10;

    private void paintEdges(Graphics g, Nodos parent) {
        if (!getTree().isLeaf(parent)) {
            Rectangle2D.Double b1 = getBoundsOfNode(parent);
            double x1 = b1.getCenterX();
            double y1 = b1.getCenterY();
            for (Nodos child : getChildren(parent)) {
                Rectangle2D.Double b2 = getBoundsOfNode(child);
                g.drawLine((int) x1, (int) y1, (int) b2.getCenterX(),
                        (int) b2.getCenterY());

                paintEdges(g, child);
            }
        }
    }

    private void paintBox(Graphics g, Nodos theNode) {
        if (Colores.isShadowing() == true) {
            if (theNode.isShadowingEnabled() == true) {
                g.setColor(Colores.getBOX_COLOR());
            } else {
                g.setColor(Colores.getSHADOW_COLOR());
            }
        } else {
            g.setColor(Colores.getBOX_COLOR());
        }

        Rectangle2D.Double box = getBoundsOfNode(theNode);
        g.fillRoundRect((int) box.x, (int) box.y, (int) box.width - 1,
                (int) box.height - 1, ARC_SIZE, ARC_SIZE);
        g.setColor(Colores.getBORDER_COLOR());
        g.drawRoundRect((int) box.x, (int) box.y, (int) box.width - 1,
                (int) box.height - 1, ARC_SIZE, ARC_SIZE);

        g.setColor(Colores.getTEXT_COLOR());

        RastrearArbol t = new RastrearArbol();
        String timestamp = theNode.getTimestamp();
        int id = theNode.getId();
        String key = "#" + Integer.toString(id) + ", " + timestamp;

        List<String> lines = theNode.getFieldTokens();
        FontMetrics m = returnFontMetrics();

        int x = (int) box.x + ARC_SIZE / 2;
        int y = (int) box.y + m.getAscent() + m.getLeading() + 1;
        for (String line : lines) {
            String temp = line.replaceAll("\\s", "");
            if (temp.isEmpty() == true) {
                continue;
            }

            g.drawString(line, x, y);
            y += m.getHeight();
        }
    }

    @Override
    public void paint(Graphics g
    ) {
        super.paint(g);

        paintEdges(g, getTree().getRoot());

        // paint the boxes
        for (Nodos theNode : treeLayout.getNodeBounds().keySet()) {
            paintBox(g, theNode);
        }
    }
}
