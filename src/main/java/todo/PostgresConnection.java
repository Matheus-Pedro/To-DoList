package todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class PostgresConnection {

    static String url = "jdbc:postgresql://localhost:5432/taskmanager?application_name=MyApp";
    static String user = "user";
    static String password = "passwd";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = getConnection();
            System.out.println("Conexão realizada!: ");
            connection.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

