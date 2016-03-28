/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagramaArbol;

import java.awt.Color;

/**
 *
 * @author crdzbird
 */
public class Colores {
    
    private static Color BOX_COLOR = Color.orange;
    private static Color BORDER_COLOR = Color.darkGray;
    private static Color TEXT_COLOR = Color.black;

    private static Color LAST_BOX_COLOR = Color.orange;
    private static Color LAST_BORDER_COLOR = Color.darkGray;
    private static Color LAST_TEXT_COLOR = Color.black;
    private final static Color SHADOW_COLOR = Color.lightGray;
    private static Color[] profile = null;
    private static boolean shadowing = false;

    public final static Colores INSTANCE = new Colores();

    public static Color[] getProfile() {
        return profile;
    }

    public static Color getSHADOW_COLOR() {
        return SHADOW_COLOR;
    }

    public static boolean isShadowing() {
        return shadowing;
    }

    public static void setShadowing(boolean aShadowing) {
        shadowing = aShadowing;
    }

    private Colores() {
        
    }

    public static void saveProfile() {
        profile = new Color[3];
        profile[0] = BOX_COLOR;
        profile[1] = BORDER_COLOR;
        profile[2] = TEXT_COLOR;
    }

    public static void loadProfile() {
        BOX_COLOR = getProfile()[0];
        BORDER_COLOR = getProfile()[1];
        TEXT_COLOR = getProfile()[2];
    }

    public static Color getBOX_COLOR() {
        return BOX_COLOR;
    }

    public static void setBOX_COLOR(Color aBOX_COLOR) {
        BOX_COLOR = aBOX_COLOR;
    }

    public static Color getBORDER_COLOR() {
        return BORDER_COLOR;
    }

    public static void setBORDER_COLOR(Color aBORDER_COLOR) {
        BORDER_COLOR = aBORDER_COLOR;
    }

    public static Color getTEXT_COLOR() {
        return TEXT_COLOR;
    }

    public static void setTEXT_COLOR(Color aTEXT_COLOR) {
        TEXT_COLOR = aTEXT_COLOR;
    }

    public static void setdefaultColors() {
        BOX_COLOR = Color.orange;
        BORDER_COLOR = Color.darkGray;
        TEXT_COLOR = Color.black;
    }

    public static void setCurColorsAsLast() {
        LAST_BOX_COLOR = BOX_COLOR;
        LAST_BORDER_COLOR = BORDER_COLOR;
        LAST_TEXT_COLOR = TEXT_COLOR;
    }

    public static void getLastColors() {
        BOX_COLOR = LAST_BOX_COLOR;
        BORDER_COLOR = LAST_BORDER_COLOR;
        TEXT_COLOR = LAST_TEXT_COLOR;
    }
}
