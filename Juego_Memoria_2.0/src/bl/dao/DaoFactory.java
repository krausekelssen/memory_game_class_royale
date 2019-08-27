/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.dao;

import bl.dao.usuario.IUsuarioDAO;


/**
 *
 * @author Luis
 */
//DEFINE QUE MOTOR DE BASE DE DATOS VAMOS A USAR

public abstract class DaoFactory {

    //Va tener dos variables estaticas, una por motor de DB
    public static final int SQLSERVER = 1;

    //El factory es un PATRÓN (curso de patrones) un patrón como viene en el lab
    //es una solución ya probada para aun problema especifico
    //El patron Factory es una fabrica de objetos
    
    /**
     * @autor Krause Kelsen
     * @param factory
     * @return SqlServerDaoFacotry();
     * @version 1.0
     */
    public static DaoFactory getDaoFactory(int factory) {
        //Retorna el factory especifico que recibe
        switch (factory) {
            case SQLSERVER:
                return new SqlServerDaoFactory();
            default:
                return null;
        }
    }

    public abstract IUsuarioDAO getUsuarioDAO();
}
