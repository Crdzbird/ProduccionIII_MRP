/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagramaArbol;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author crdzbird
 */
public class ImplementacionNodos implements Serializable {

    private Nodos node;
    private List<Nodos> children;

    public ImplementacionNodos(Nodos node, List<Nodos> children) {
        this.node = node;
        this.children = children;
    }

    public Nodos getNode() {
        return node;
    }

    public List<Nodos> getChildren() {
        return children;
    }
}
