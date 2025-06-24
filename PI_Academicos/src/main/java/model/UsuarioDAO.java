
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO extends GenericDAO  {
    


    public boolean cpfJaCadastrado(String cpf) throws SQLException {
        String query = "SELECT COUNT(*) FROM usuarios WHERE cpf = ?";
        try (Connection con = conectarDAO();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

}
