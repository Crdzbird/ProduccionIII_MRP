/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagramaArbol;

import java.io.Serializable;
import static Interfaces.Interfaz_GestionMateriales.returnFontMetrics;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author crdzbird
 */
public class Nodos implements Serializable{
    
    private String title;
    private String category;
    private String link;
    private String description;
    private final String timestamp;
    private final String key;
    private int width;
    private int height;
    private final int id;
    private final int PADDING;
    private List<String> fieldTokens;
    private final int MAX_LENGTH_OF_TEXT_FIELD;
    private boolean shadowed = false;
    
    public Nodos(String key, String title, String category, String link, String description, int id, String timestamp) {
        this.title = title;
        this.category = category;
        this.link = link;
        this.description = description;
        this.id = id;
        this.timestamp = timestamp;
        this.key = key;
        PADDING = 10;
        MAX_LENGTH_OF_TEXT_FIELD = 50;
        fieldTokens = new ArrayList<>();
        shadowed = false;

        calcNodeDims();
    }
    
    private String checkLength(String str) {

        if (str.length() > MAX_LENGTH_OF_TEXT_FIELD) {
            StringBuilder sb = new StringBuilder(str);

            int i = 0;
            while ((i = sb.indexOf(" ", i + MAX_LENGTH_OF_TEXT_FIELD)) != -1) {
                sb.replace(i, i + 1, "\n");
            }
            return sb.toString();
        } else {
            return str;
        }

    }

    public void calcNodeDims() {
        getFieldTokens().clear();

        String[] theStrings = {checkLength(getKey()), checkLength(getTitle()), checkLength(getCategory()),
            checkLength(getDescription())};

        for (String str : theStrings) {
            if (str.length() > MAX_LENGTH_OF_TEXT_FIELD) {
                String[] temp = str.split("[\n]");

                getFieldTokens().addAll(Arrays.asList(temp));
            } else {
                getFieldTokens().add(str);
            }
        }

        FontMetrics fm = returnFontMetrics();

        int maxLength = 0; 
        int longestStringWidth = 0; 
        int numOfLines = 0;
        for (String str : getFieldTokens()) {
            if (fm.stringWidth(str) > maxLength) {
                // maxLength = str.length();
                maxLength = fm.stringWidth(str);
                longestStringWidth = fm.stringWidth(str);
            }
            if (str.replaceAll("\\s", "").isEmpty() == false) {
                numOfLines++;
            }
        }

        setWidth(longestStringWidth + PADDING);
        setHeight((fm.getHeight()) * numOfLines + PADDING / 2);
        System.out.println("width: " + getWidth() + ", heigth: " + getHeight());
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getFieldTokens() {
        return fieldTokens;
    }

    public String getKey() {
        return key;
    }

    public boolean isShadowingEnabled() {
        return shadowed;
    }

    public void setShadowed(boolean shadowed) {
        this.shadowed = shadowed;
    }
    
}
