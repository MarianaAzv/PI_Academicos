
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
        //Método para autenticar usuários
	public Usuario autenticar(String apelido, String senha) throws SQLException {
		String sql = "SELECT * FROM USUARIOS WHERE apelido=? AND senha=?";
		Usuario usuario = null;
		Connection con = conectarDAO();
		if (con != null) {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, apelido);
			stmt.setString(2, senha);
                        System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
                        System.out.println(rs);

			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("idUsuario"));
                                usuario.setCpf(rs.getInt("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setApelido(rs.getString("apelido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtiva(rs.getBoolean("ativa"));
                                
			}

			rs.close();
			stmt.close();
			conectarDAO().close();
			return usuario;
		} else {
			return null;
			
		}

	}
}
