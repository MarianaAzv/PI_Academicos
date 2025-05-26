
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
		String sql = "select * from usuarios left join coordenadores  ON coordenadores.idUsuario "
                        + "= usuarios.idUsuario left join bolsistas ON bolsistas.idUsuario = usuarios.idUsuario"
                        + " left join administradores on administradores.idUsuario=usuarios.idUsuario WHERE apelido=? AND senha=?";
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
                                    Bolsista b = new Bolsista(rs.getLong("matricula"), rs.getString("curso"));
                                    b.setCurso(rs.getString("curso"));
                                 //   b.setCPF(rs.getLong("cpf"));
                                    b.setApelido(rs.getString("apelido"));
                                    b.setNome(rs.getString("nome"));
                                    b.setEmail(rs.getString("email"));
                                    b.setMatricula(rs.getLong("matricula"));
                             //       b.setDataInicio(rs.getLocalDate("dataInicio"));
                            //        b.setDataFim(rs.getLocalDate("dataFim"));
//        public Bolsista(int id, long cpf, String nome, String apelido, String email, String senha, boolean ativa, long matricula, String curso, LocalDate dataInicio, LocalDate dataFim){

                                            //bolsista.setDataInicio(rs.getDate);
                                    //b.set
                                   // usuario = b;
                                    
                                    

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
