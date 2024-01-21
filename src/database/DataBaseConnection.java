package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private final String serverName = "localhost";
    private final String dbName = "MerceariaDB";
    private final String portNumber = "3306";
    private final String username = "sweyd-abdul";
    private final String password = "SweydAbdul@12345";

    public Connection getConnection(){
        String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão à base de dados: " + e.getMessage(), "Base de Dados", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }
}
