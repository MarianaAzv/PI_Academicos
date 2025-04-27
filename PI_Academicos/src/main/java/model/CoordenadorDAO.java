package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CoordenadorDAO extends GenericDAO{
    
    
     public void cadastrarUsuario(Usuario usuario, Coordenador coordenador) throws SQLException {
        String insert = "INSERT INTO USUARIOS(cpf, nome, apelido, senha, email) VALUES(?,?,?,?,?)";
        save(insert, usuario.getCpf(), usuario.getNome(), usuario.getApelido(), usuario.getSenha(), usuario.getEmail());

        
        
        
        
        
        
        
        String query = "SELECT idUsuario FROM usuarios WHERE apelido=?"; 
       
           try(Connection con = conectarDAO()){
           con.setAutoCommit(false);
           PreparedStatement stmt = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
           stmt.setString(1,usuario.getApelido());
           
           int linhasAfetadas = stmt.executeUpdate();
           
           if(linhasAfetadas>0){
               try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        int idGerado = keys.getInt(1); // Pega o primeiro valor da chave gerada
                        System.out.println("ID gerado: " + idGerado);
                    }
                }
  
           } else {
                System.out.println("Nenhuma linha foi inserida.");
            }
           
       }catch (Exception e){
               System.out.println("DEU RUIM");
           e.printStackTrace();
       }
        
        
        
      

        
        
      
        
        
       String insert2 = "INSERT INTO COORDENADORES(idUsuario, siape, formacao) VALUES (?,?,?)";
         System.out.println("id do usuario: " + usuario.getId());
         System.out.println("nome do usuario: " + usuario.getNome());
         save(insert2, usuario.getId(), coordenador.getSiape(), coordenador.getFormacao());
         
    }
     
     
     
    public void cadastrarUsuarioCoordenador(Usuario usuario, Coordenador coordenador){
        
        Connection con = conectarDAO();
        
        String queryUsuario = "INSERT INTO USUARIOS(cpf, nome, apelido, senha, email) VALUES(?,?,?,?,?)";
        String queryCoordenador = "INSERT INTO COORDENADORES(idUsuario, siape, formacao) VALUES (?,?,?)";

    try (con) {
    // Inserir em Usuario
    PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
    stmtUsuario.setLong(1, usuario.getCpf());
    stmtUsuario.setString(2, usuario.getNome());
    stmtUsuario.setString(3, usuario.getApelido());
    stmtUsuario.setString(4, usuario.getSenha());
    stmtUsuario.setString(5, usuario.getEmail());  
    stmtUsuario.executeUpdate();

    ResultSet keys = stmtUsuario.getGeneratedKeys();
    if (keys.next()) {
        int idGerado = keys.getInt(1);
        coordenador.setId(idGerado);

        // Inserir em Aluno
        PreparedStatement stmtCoordenador = con.prepareStatement(queryCoordenador);
        stmtCoordenador.setInt(1, idGerado);
        stmtCoordenador.setInt(2, coordenador.getSiape());
        stmtCoordenador.setString(3, coordenador.getFormacao());
        stmtCoordenador.executeUpdate();

        System.out.println("Coordenador cadastrado com ID: " + idGerado);
    }
} catch (Exception e) {
    e.printStackTrace();
}
        
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    /*public cadastrarCoordenador(Coordenador coordenador) throws SQLException {
       // String insert = "INSERT INTO COORDENADOR(idUsuario, siape, formacao) VALUES(?,?,?)";
          //save(insert, coordenador.getId(), coordenador.getSiape(), coordenador.getFormacao());   
    }/*
    
    //Método para cadastrar coordenadores
	/*public Coordenador cadastrar(Coordenador usuario) throws SQLException {
		
		Connection con = conectarDAO();
                      con.setAutoCommit(false);

                      //PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                //PreparedStatement stmt, stmt2;
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                if (con != null) {
                try {
                    //String sql = "INSERT INTO Usuarios cpf=?, nome=?, apelido=?, senha=?, email=?, ativa=?";
                    String sql = "INSERT INTO USUARIOS(cpf,nome,apelido,senha,email) VALUES(?,?,?,?,?,?)";
                        stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                        stmt.setLong(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
                        stmt.setString(3, usuario.getApelido());
                        stmt.setString(4, usuario.getSenha());
                        stmt.setString(5, usuario.getEmail());

                         save(sql, usuario.getCpf(), usuario.getNome(), usuario.getApelido(), usuario.getSenha(), usuario.getEmail());
                        System.out.println(stmt);
			int affectedRows = stmt.executeUpdate();
                     if (affectedRows == 0) {
                         System.out.println("nao criou user");
                        throw new SQLException("Creating user failed, no rows affected.");
                         
                    }
                     
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                usuario.setId(generatedKeys.getInt(1));
                System.out.println("id do coordenador: " + usuario.getId());
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
         }
                
              String sql2 = "INSERT INTO coordenadores  idUsuario=?, siape=?, formacao=?";
              
               stmt2 = con.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
               
               
			stmt2.setInt(1, usuario.getId());
			stmt2.setInt(2, usuario.getSiape());
                        stmt2.setString(3, usuario.getFormacao());
                        System.out.println(stmt2);
			int affectedRows2 = stmt2.executeUpdate();
               
                if (affectedRows2 == 0) {
                    System.out.println("nao criou coord");
                    throw new SQLException("Creating user failed, no rows affected.");
                }
                        con.commit();

            } catch (Exception e) {
                    System.out.println(e.getCause());
                              con.rollback();
                              

            }
                
                //if(stmt!=null){
                    stmt.close();
                //}
               // if(stmt2!=null){
                    stmt2.close();
                //}
                                
                
        
        
			
			conectarDAO().close();
			return usuario;
		} else {
			return null;
			
		}

	}*/
}
