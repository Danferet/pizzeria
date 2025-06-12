package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    //Creo un metodo que me devolverá la conexión en cualquier clase en la que lo implemente.
    public static Connection conectar() throws SQLException{

        return DriverManager.getConnection("jdbc:sqlite:pizzeria.db");

    }
}
