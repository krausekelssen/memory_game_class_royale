/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.dao.usuario;

import accessDB.Conector;
import bl.dao.DaoFactory;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class UsuarioDAO implements IUsuarioDAO{
    
    public void registrarUsuario(String nombre, Time tiempo){
        int posicion = 0;
        String sql = "INSERT INTO usuario VALUES ("
                + posicion + ","
                + "'" + nombre + "'" + ","
                + "'" + tiempo + "'" + ")";
        try {
            Conector.getConnector(DaoFactory.SQLSERVER).ejecutarSQL(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateUser(){
        String sql = "EXECUTE sp_updateUser";
        try {
            Conector.getConnector(DaoFactory.SQLSERVER).ejecutarSQL(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<Usuario> listarUsuarios(){
        ArrayList<Usuario> misRecords = new ArrayList();
        String sql = "SELECT posicion, nombreJugador, tiempo FROM usuario ORDER BY posicion ASC";
        ResultSet set;
        int posicion;
        String nombre;
        Time tiempo;

        try {
            set = Conector.getConnector(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);
            while (set.next()) {
                Usuario miRecord = new Usuario();

                posicion = set.getInt("posicion");
                nombre = set.getString("nombreJugador");
                tiempo = set.getTime("tiempo");
                
                miRecord.setPosicion(posicion);
                miRecord.setNombre(nombre);
                miRecord.setTiempo(tiempo);
                misRecords.add(miRecord);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return misRecords;
        
    }
    
    public void eliminarRecords(){
        String sql = "DELETE usuario";
        try {
            Conector.getConnector(DaoFactory.SQLSERVER).ejecutarSQL(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
