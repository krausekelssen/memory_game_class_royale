/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.dao.usuario;

import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public interface IUsuarioDAO {
    
    public void registrarUsuario(String nombre, Time time);
    public void updateUser();
    public ArrayList<Usuario> listarUsuarios();
    public void eliminarRecords();
}
