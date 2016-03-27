/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import org.abego.treelayout.NodeExtentProvider;

/**
 *
 * @author donaldo
 */
public class TextInBoxNode implements NodeExtentProvider<TextInBox> {

    @Override
    public double getWidth(TextInBox treeNode) {
        return treeNode.width;
    }

    @Override
    public double getHeight(TextInBox treeNode) {
        return treeNode.height;
    }
}
