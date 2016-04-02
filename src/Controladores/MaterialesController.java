/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.Conexion;
import Pojo.Materiales;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author donaldo
 */
public class MaterialesController {

    public MaterialesController() {

    }

    public int RegistrarMaterial(Materiales materiales) {

        try {
            Connection con = new Conexion().Coneccion();
            CallableStatement comando = con.prepareCall("{call Guardar_Material(?,?,?,?,?,?,?)};");
            comando.setString("nombre_material", materiales.getNombre_material());
            comando.setInt("tiempo_espera", materiales.getTiempo_espera());
            comando.setString("tipo_espera", materiales.getTipo_espera());
            comando.setInt("cantidad_lote", materiales.getCantidad_lote());
            comando.setInt("cantidad_material", materiales.getCantidad_material());
            comando.setBoolean("estado_material", materiales.isEstado());

            comando.registerOutParameter(7, java.sql.Types.INTEGER);

            comando.execute();

            int id = comando.getInt(7);

            comando.close();
            con.close();
            return id;
        } catch (Exception ex) {
            return -1;
        }
    }

    public boolean ActualizarMaterial(Materiales materiales) {

        try {
            Connection con = new Conexion().Coneccion();
            CallableStatement comando = con.prepareCall("{call Actualizar_Material(?,?,?,?,?,?,?)};");
            comando.setString("nombre_material", materiales.getNombre_material());
            comando.setInt("tiempo_espera", materiales.getTiempo_espera());
            comando.setString("tipo_espera", materiales.getTipo_espera());
            comando.setInt("cantidad_lote", materiales.getCantidad_lote());
            comando.setInt("cantidad_material", materiales.getCantidad_material());
            comando.setBoolean("estado_material", materiales.isEstado());
            comando.setInt("id", materiales.getId());

            comando.execute();

            comando.close();
            con.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean ActualizarDependencia(int idprincipal, int iddependencia, int cantidad, boolean estado) {

        try {
            Connection con = new Conexion().Coneccion();
            CallableStatement comando = con.prepareCall("{call Actualizar_dependencia(?,?,?,?)};");
            comando.setInt("id_material_principal", idprincipal);
            comando.setInt("id_material_dependencia", iddependencia);
            comando.setInt("cantidad", cantidad);
            comando.setBoolean("estado", estado);

            comando.execute();

            comando.close();
            con.close();
            return true;
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());
            return false;
        }
    }

    public boolean RegistrarComposicion(List<Object[]> list, int IdPrincipal) {

        try {
            Connection con = new Conexion().Coneccion();

            for (Object[] o : list) {
                String sql = String.format("insert into Materiales_Dependencias "
                        + "(id_material_principal,id_material_dependencia,cantidad_material_dependencia, estado_uso) "
                        + "values (%s,%s,%s,%s)", IdPrincipal, o[0], o[1], true);

                System.out.println("" + sql);
                Statement comando = con.createStatement();
                int filas = comando.executeUpdate(sql);

                if (filas == 0) {
                    return false;
                }
                comando.close();

            }

            con.close();
            return true;
        } catch (Exception ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    public boolean RegistrarOrden(String fecha, int id, int cantidad) {

        try {
            Connection con = new Conexion().Coneccion();

            String sql = String.format("insert into OrdenProduccion "
                    + "(IdMaterial,CantidadSolicitud,Fecha) "
                    + "values (%s,%s,'%s')", id, cantidad, fecha);

            System.out.println("" + sql);
            Statement comando = con.createStatement();
            int filas = comando.executeUpdate(sql);

            con.close();
            if (filas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            System.out.println("" + ex);
            return false;
        }
    }
    
    public boolean RegistrarEntrada(int idorden, String fecha, int id, int cantidad) {

        try {
            Connection con = new Conexion().Coneccion();

            String sql = String.format("insert into Entradas_Programadas "
                    + "(id_material,fecha_entrega,cantidad_solicitada, IdOrden) "
                    + "values (%s,'%s' , %s,%s)", id,  fecha, cantidad, idorden);

            System.out.println("" + sql);
            Statement comando = con.createStatement();
            int filas = comando.executeUpdate(sql);

            con.close();
            if (filas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    public List<Materiales> getAll() {
        Connection con = new Conexion().Coneccion();
        List<Materiales> lista = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Materiales");

            while (rs.next()) {
                Materiales m = new Materiales();
                m.setId(rs.getInt(1));
                m.setNombre_material(rs.getString(2));
                m.setTiempo_espera(rs.getInt(3));
                m.setTipo_espera(rs.getString(4));
                m.setCantidad_lote(rs.getInt(5));
                m.setCantidad_material(rs.getInt(6));
                m.setEstado(rs.getBoolean(7));
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public Materiales getById(int Id) {
        Connection con = new Conexion().Coneccion();

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Materiales where Id_Material = " + Id);

            while (rs.next()) {
                Materiales m = new Materiales();
                m.setId(rs.getInt(1));
                m.setNombre_material(rs.getString(2));
                m.setTiempo_espera(rs.getInt(3));
                m.setTipo_espera(rs.getString(4));
                m.setCantidad_lote(rs.getInt(5));
                m.setCantidad_material(rs.getInt(6));
                m.setEstado(rs.getBoolean(7));
                return m;
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }
    
    
    public Object []  getOrdenById(int Id) {
        Connection con = new Conexion().Coneccion();

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from OrdenProduccion where IdOrden = " + Id);

            while (rs.next()) {
                Object [] obj = new Object[4];
                obj[0] = rs.getInt(1);
                obj[1] = rs.getInt(2);
                obj[2] = rs.getInt(3);
                obj[3] = rs.getString(4);
                return obj;
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    public List<Object[]> getDependencias(int id_principal) {
        Connection con = new Conexion().Coneccion();
        List<Object[]> lista = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from VistaComposicion where id_principal = " + id_principal);

            while (rs.next()) {
                Object[] obj = new Object[7];

                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
                obj[5] = rs.getBoolean(6);
                obj[6] = rs.getInt(7);
                lista.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<Object[]> getOrdenes() {
        Connection con = new Conexion().Coneccion();
        List<Object[]> lista = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select IdOrden, nombre_material, "
                    + "CantidadSolicitud,Fecha "
                    + "from OrdenProduccion o\n"
                    + "inner join Materiales m on m.id_material = o.IdMaterial "
                    + "order by Fecha desc");

            while (rs.next()) {
                Object[] obj = new Object[6];

                obj[0] = rs.getInt(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
                obj[3] = rs.getString(4);
                lista.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public List<Object[]> getEntradas(int idOrden) {
        Connection con = new Conexion().Coneccion();
        List<Object[]> lista = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Entradas_Programadas where IdOrden = " + idOrden);

            while (rs.next()) {
                Object [] obj = new Object[5];
                obj[0] = rs.getInt(1);
                obj[1] = rs.getInt(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
                lista.add(obj);
            }
            
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }

}
