
package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
		String sql =   "select * from usuarios left join coordenadores  ON coordenadores.idUsuario"
                    + "= usuarios.idUsuario left join bolsistas ON bolsistas.idUsuario = usuarios.idUsuario"
                    + "  left join administradores on administradores.idUsuario=usuarios.idUsuario "
                    + "left join bolsistas_projetos bp on usuarios.idUsuario=bp.idUsuario "
                    + "left join projetos p on bp.idProjeto= p.idProjeto"
                    + " WHERE apelido= ? AND senha=?";
                
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
                            
                                if(rs.getString("formacao") != null){
                                    usuario = new Coordenador(rs.getInt("siape"), rs.getString("formacao"));

                                }
                                else if(rs.getString("curso") != null){
                                     LocalDate dataInicio = rs.getDate("dataInicio") != null ? rs.getDate("dataInicio").toLocalDate() : null;
                                    LocalDate dataFim = rs.getDate("dataFim") != null ? rs.getDate("dataFim").toLocalDate() : null;
                                    Bolsista bolsista = new Bolsista(rs.getLong("matricula"), rs.getString("curso"),
                                      
                                 //   rs.getInt("idUsuario"),  // ID do bolsista
                                //    rs.getLong("cpf"), 
                                //    rs.getString("nome"), 
                                //    rs.getString("apelido"), 
                               //     rs.getString("email"), 
                                //    rs.getString("senha"), 
                                 //   rs.getBoolean("ativa"), 
                                //    rs.getLong("matricula"), 
                                 //   rs.getString("curso"), 
                                  dataInicio, 
                                    dataFim,
                                    rs.getInt("idProjeto")
);

    usuario = bolsista;

                                    
                                    

                                } else {
                                   usuario = new Administrador(); 
                                }
				usuario.setId(rs.getInt("idUsuario"));
                                usuario.setCpf(rs.getLong("cpf"));
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