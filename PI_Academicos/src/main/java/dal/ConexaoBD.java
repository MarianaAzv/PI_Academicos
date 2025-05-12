
package dal;
import java.sql.*;

public class ConexaoBD {
     // São criadas constantes pois as informações são estáticas

    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/academicos";

    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    private static final String USER = "root";

    private static final String PASSWORD = "Estudoprogra123@";

    // Método para estabelecer conexão com o BD
    public static Connection conectar() {

        try {
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
        }
        return null;
    }

    //Método para desconectar do banco e dados
    public void desconectar(Connection conexao) {
        try {
            conexao.close();
        } catch (SQLException e) {
        }
    }
    
}
