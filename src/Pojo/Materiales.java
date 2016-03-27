/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author crdzbird
 */
public class Materiales {

    private int id;
    private String nombre_material;
    private int tiempo_espera;
    private int cantidad_lote;
    private int cantidad_material;
    private boolean estado;
    private String tipo_espera;
    public Materiales() {
    }

    public Materiales(int id, String nombre_material, int tiempo_espera, int cantidad_lote, int cantidad_material, boolean estado) {
        this.id = id;
        this.nombre_material = nombre_material;
        this.tiempo_espera = tiempo_espera;
        this.cantidad_lote = cantidad_lote;
        this.cantidad_material = cantidad_material;
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_material() {
        return nombre_material;
    }

    public void setNombre_material(String nombre_material) {
        this.nombre_material = nombre_material;
    }

    public int getTiempo_espera() {
        return tiempo_espera;
    }

    public void setTiempo_espera(int tiempo_espera) {
        this.tiempo_espera = tiempo_espera;
    }

    public int getCantidad_lote() {
        return cantidad_lote;
    }

    public void setCantidad_lote(int cantidad_lote) {
        this.cantidad_lote = cantidad_lote;
    }

    public int getCantidad_material() {
        return cantidad_material;
    }

    public void setCantidad_material(int cantidad_material) {
        this.cantidad_material = cantidad_material;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipo_espera() {
        return tipo_espera;
    }

    public void setTipo_espera(String tipo_espera) {
        this.tipo_espera = tipo_espera;
    }

    
}
