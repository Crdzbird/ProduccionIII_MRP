/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

/**
 *
 * @author donaldo
 */
public class Validar {
    
    
    
    public static boolean isInt(String cadena){
        try{
            Integer.parseInt(cadena);
            return true;
        }catch(NumberFormatException nf){
            return false;
        }
    }
}
