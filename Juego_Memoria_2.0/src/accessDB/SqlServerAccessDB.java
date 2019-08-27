/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessDB;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Luis
 */
public class SqlServerAccessDB extends AccessDB{
    
    public SqlServerAccessDB(String driver, String conexion)throws SQLException, Exception{
        
        super(driver, conexion);
    }
    
    public SqlServerAccessDB(String driver, String url, String user, String password) 
            throws SQLException, Exception{
        super(driver, url, user, password);
    }
    
    //Se definen estos constructores de la clase ACCESSDB
    //Sobrecarga de metodos: Metodos con los mismos nombres pero con diferentes parametros
    //Sobreescritura: Esta clase implementa los metodos de ACCESSDB
    @Override
    //Ejecuta las sentencias
    public void ejecutarSQL(String query)throws SQLException, Exception{
        st.execute(query);
    }
    
    @Override
    //Ejecuta las sentencias y retorna la consulta
    public ResultSet ejecutarSQL(String query, boolean retorno)throws SQLException, Exception{
        ResultSet rs;
        rs = st.executeQuery(query);
        return rs;
    }
}
