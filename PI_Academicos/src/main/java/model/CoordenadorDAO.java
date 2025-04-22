package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CoordenadorDAO extends GenericDAO{
    
    //Método para cadastrar coordenadores
	public Coordenador cadastrar(Coordenador usuario) throws SQLException {
            
            
            
            
		
		
		Connection con = conectarDAO();
                      con.setAutoCommit(false);

                PreparedStatement stmt, stmt2;
                if (con != null) {
                try {
                    String sql = "INSERT INTO Usuarios  cpf=?, nome=?, apelido=?, senha=?, nome=?, nome=?";
                        stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                        stmt.setLong(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
                        System.out.println(stmt);
			int affectedRows = stmt.executeUpdate();
                     if (affectedRows == 0) {
                        throw new SQLException("Creating user failed, no rows affected.");
                    }
                     try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                usuario.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
                
              String sql2 = "INSERT INTO coordenadores  idUsuario=?, siape=?, formacao=?";
              
               stmt2 = con.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
               
               
			//stmt2.setInteger(1, usuario.getId());
			//stmt2.setString(2, usuario.getSiape());
                        System.out.println(stmt);
			int affectedRows2 = stmt.executeUpdate();
               
                if (affectedRows2 == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
                        con.commit();

            } catch (Exception e) {
                              con.rollback();

            }
		
		       


			

        
        
			//stmt.close();
			conectarDAO().close();
			return usuario;
		} else {
			return null;
			
		}

	}
}
