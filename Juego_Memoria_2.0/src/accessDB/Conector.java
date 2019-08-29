package accessDB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class Conector {

    //Tiene una varaible estatica
    private static AccessDB connectorBD = null;

    //Esta variable quiere decir que si hubiera mas de una instancia de conector (que no es lo ideal)
    //todos conmpartirian un unico AccessDB porque esta variable es estatica
    //es una variable de clase más no de instancia
    public static AccessDB getConnector(int factory) throws SQLException, Exception {
        //El factory representa el entero que le voy a representar a cada DB
        // Es decir si le paso un 1 debe conectarse a SQLSERVER

        if (connectorBD == null) {
            switch (factory) {
                case 1:
                    connectorBD = new SqlServerAccessDB("com.microsoft.sqlserver.jdbc.SQLServerDriver",
                            "jdbc:sqlserver://" + "ANGIE" + ";databaseName=" + "juegoMemoria" 
                                    + ";user=" + "KrauseKelsen" + ";password=" + "***********" + ";");

                    //(DRIVER, CONECTOR)
                    return connectorBD;
                default:
                    connectorBD = null;
            }
        }
        return connectorBD;
    }
}
