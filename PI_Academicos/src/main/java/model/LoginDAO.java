
package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends GenericDAO {
    
    // Método para verificar se o banco esta online
	public Boolean bancoOnline() {
		Connection con = conectarDAO();
		if (con != null) {
			try {
				conectarDAO().close();
			} catch (SQLException e) {
			}
			return true;
		} else
			return false;
	}
}
