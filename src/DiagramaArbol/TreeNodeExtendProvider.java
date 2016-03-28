/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagramaArbol;

import org.abego.treelayout.NodeExtentProvider;

/**
 *
 * @author crdzbird
 */
public class TreeNodeExtendProvider implements
        NodeExtentProvider<Nodos> {

    @Override
    public double getWidth(Nodos treeNode) {
        return treeNode.getWidth();
    }

    @Override
    public double getHeight(Nodos treeNode) {
        return treeNode.getHeight();
    }
}
