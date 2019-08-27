package accessDB;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public abstract class AccessDB {
    //Esta clase es abstracta para que cada proveedor de BD pueda implementar
    //los metodos 
    protected Connection conn = null;
    protected Statement st = null;
    
    //Este metodo hace el registro del driver y de paso inicializa la conexión
    public AccessDB(String driver, String conexion) throws SQLException, Exception{
        Class.forName(driver);
        conn = DriverManager.getConnection(conexion);
        st = conn.createStatement();
    }
    public AccessDB (String driver, String url, String user, String password) 
            throws SQLException, Exception{
        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, password);
        st = conn.createStatement();
    }
    public abstract void ejecutarSQL(String query)throws SQLException, Exception;
    public abstract ResultSet ejecutarSQL(String query, boolean retorno)
            throws SQLException, Exception;
}
