/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.util.Date;

/**
 *
 * @author crdzbird
 */
public class Envio_Materiales {

    private int id_envio;
    private int id_material;
    private Date fecha_entrega;
    private Date fecha_solicitud;
    private int cantidad_solicitada;

    public Envio_Materiales() {
    }

    public Envio_Materiales(int id_envio, int id_material, Date fecha_entrega, Date fecha_solicitud, int cantidad_solicitada) {
        this.id_envio = id_envio;
        this.id_material = id_material;
        this.fecha_entrega = fecha_entrega;
        this.fecha_solicitud = fecha_solicitud;
        this.cantidad_solicitada = cantidad_solicitada;
    }

    public int getId_envio() {
        return id_envio;
    }

    public void setId_envio(int id_envio) {
        this.id_envio = id_envio;
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public int getCantidad_solicitada() {
        return cantidad_solicitada;
    }

    public void setCantidad_solicitada(int cantidad_solicitada) {
        this.cantidad_solicitada = cantidad_solicitada;
    }

}
