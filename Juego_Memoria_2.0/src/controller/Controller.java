/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bl.dao.DaoFactory;
import bl.dao.usuario.IUsuarioDAO;
import bl.dao.usuario.Usuario;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Controller {

    public void registrarUsuario(String nombre, Time tiempo) {
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDAO dao = factory.getUsuarioDAO();
            dao.registrarUsuario(nombre, tiempo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser() {
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDAO dao = factory.getUsuarioDAO();
            dao.updateUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Usuario> listarRecords() {
        ArrayList<Usuario> misRecords = new ArrayList();
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDAO dao = factory.getUsuarioDAO();
            misRecords = dao.listarUsuarios();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return misRecords;
    }
    
    public void eliminarRecords(){
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDAO dao = factory.getUsuarioDAO();
            dao.eliminarRecords();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
