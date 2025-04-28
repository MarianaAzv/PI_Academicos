package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CoordenadorDAO extends GenericDAO{
    
   
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

        // Inserir em Coordenador
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
    
    
    
    
    String queryUsuario = "INSERT INTO USUARIOS(cpf, nome, apelido, senha, email) VALUES(?,?,?,?,?)";
        String queryCoordenador = "INSERT INTO COORDENADORES(idUsuario, siape, formacao) VALUES (?,?,?)";
    
    // Método para atualizar usuarios
    public void atualizarCoordenador(Usuario usuario, Coordenador coordenador) throws SQLException {
        //String updateUsuario = "UPDATE USUARIOS SET cpf = ?, nome = ?, apelido = ?, senha = ?, email = ? WHERE idUsuario = ?";
       // String updateCoordenador = "UPDATE COORDENADORES SET siape = ?, formacao = ? WHERE idUsuario = ?";
	
       // update(updateUsuario, usuario.getCpf(), usuario.getNome(), usuario.getApelido(),usuario.getSenha(),usuario.getEmail(),usuario.getId());
        //update(updateCoordenador, coordenador.getSiape(), coordenador.getFormacao(), coordenador.getId());
        
        
        
        
        
        
        
        
        
        Connection con = conectarDAO();
        
        String queryUsuario = "UPDATE USUARIOS SET cpf = ?, nome = ?, apelido = ?, senha = ?, email = ? WHERE idUsuario = ?";
        String queryCoordenador = "UPDATE COORDENADORES SET siape = ?, formacao = ? WHERE idUsuario = ?";

    try (con) {
    // Inserir em Usuario
    PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
    stmtUsuario.setLong(1, usuario.getCpf());
    stmtUsuario.setString(2, usuario.getNome());
    stmtUsuario.setString(3, usuario.getApelido());
    stmtUsuario.setString(4, usuario.getSenha());
    stmtUsuario.setString(5, usuario.getEmail());  
    stmtUsuario.setInt(6, usuario.getId()); 
    stmtUsuario.executeUpdate();

    ResultSet keys = stmtUsuario.getGeneratedKeys();
    if (keys.next()) {
        int idGerado = keys.getInt(1);
        coordenador.setId(idGerado);

        // Inserir em Coordenador
        PreparedStatement stmtCoordenador = con.prepareStatement(queryCoordenador);
        stmtCoordenador.setInt(1, coordenador.getSiape());
        stmtCoordenador.setString(2, coordenador.getFormacao());
        stmtCoordenador.setInt(3, coordenador.getId());
        stmtCoordenador.executeUpdate();

        System.out.println("Coordenador cadastrado com ID: " + idGerado);
    }
} catch (Exception e) {
    e.printStackTrace();
}
        
        
        
        
        
        
        
        
        
        
    } 

}
