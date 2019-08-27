/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.dao;

import bl.dao.usuario.IUsuarioDAO;
import bl.dao.usuario.UsuarioDAO;


/**
 *
 * @author Luis
 */
//ESTE FACTORY VA RETORNAR TODOS LOS MULTIS (TODOS LOS OBJ DE LA CONSULTA SQL) DE CADA CLASE
public class SqlServerDaoFactory extends DaoFactory {

    /**
     *
     * @autor Krause Kelsen
     */
    public SqlServerDaoFactory() {
    }

    /**
     *
     * @autor Krause Kelsen
     * @return UsuarioDAO();
     */
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO();
    }
}
