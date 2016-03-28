/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagramaArbol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.abego.treelayout.util.DefaultTreeForTreeLayout;

/**
 *
 * @author crdzbird
 */
public class RastrearArbol {
    
     private final int INIT_ID = -1;
    private int id = INIT_ID;
    private final ArrayList<String> categories = new ArrayList<>();
    private final ArrayList<ImplementacionNodos> serNodes = new ArrayList<>();
    private int indxOfDelNode=-1;

    public int createId() {
        id++;
        return getId();
    }

    public void createSerNode(DefaultTreeForTreeLayout<Nodos> tree, Nodos node, int id) {
        List<Nodos> children = tree.getChildrenList(node);
        ImplementacionNodos newNode = new ImplementacionNodos(node, children);
        serNodes.add(newNode);

        if (node.getId() == id) {
            indxOfDelNode = serNodes.indexOf(newNode);
            System.out.println("Node found with index: " + indxOfDelNode);
        }

        System.out.println("added node with id: " + node.getId());
        for (Nodos aNode : children) {
            createSerNode(tree, aNode, id);
        }
    }

    public DefaultTreeForTreeLayout<Nodos> deleteNode(DefaultTreeForTreeLayout<Nodos> tree, Nodos node, int id) {
        createSerNode(tree, node, id);

        if (indxOfDelNode == -1) {
            serNodes.clear();
            return null;
        } else {
            //a new tree
            DefaultTreeForTreeLayout<Nodos> newTree;
            Nodos delNode = serNodes.get(indxOfDelNode).getNode();
            Nodos parent = tree.getParent(delNode);
            ImplementacionNodos serParent = null;

            for (ImplementacionNodos checkNode : serNodes) {
                if (checkNode.getNode().getId() == parent.getId()) {
                    serParent = checkNode;
                    break;
                }
            }

            serParent.getChildren().remove(delNode);
            for (Nodos child : serNodes.get(indxOfDelNode).getChildren()) {
                serParent.getChildren().add(child);
            }
            
            serNodes.remove(serNodes.get(indxOfDelNode));

            newTree = buidTree();
            serNodes.clear();
            indxOfDelNode = -1;
            
            return newTree;
        }
    }


    public DefaultTreeForTreeLayout<Nodos> buidTree() {
        DefaultTreeForTreeLayout<Nodos> tree = null;

        for (ImplementacionNodos serNode : serNodes) {
            Nodos newNode = serNode.getNode();
            List<Nodos> children = serNode.getChildren();
            Nodos[] childNodes = new Nodos[children.size()];
            childNodes = children.toArray(childNodes);
            if (serNode.getNode().getId() == 0) {
                tree = new DefaultTreeForTreeLayout<>(newNode);
                tree.addChildren(newNode, childNodes);
            } else {
                if (tree != null) {
                    tree.addChildren(newNode, childNodes);
                }
            }
        }

        return tree;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String createTimestamp() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void clearCategories() {
        categories.clear();
    }

    public void resetId() {
        id = INIT_ID;
    }
    
}
