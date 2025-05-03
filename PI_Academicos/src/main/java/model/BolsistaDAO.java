package model;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class BolsistaDAO extends GenericDAO{
    public void cadastrarUsuarioBolsista(Usuario usuario, Bolsista bolsista){
Connection con = conectarDAO();
        String queryUsuario = "INSERT INTO USUARIOS(cpf, nome, apelido, senha, email) VALUES(?,?,?,?,?)";
        String queryBolsista = "INSERT INTO BOLSISTAS(idUsuario, matricula, curso, acessoPostagens, acessoArtigos) VALUES (?,?,?,?,?)";
        
//        try (con) {
//    // Inserir em Usuario
//    PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
//    stmtUsuario.setLong(1, usuario.getCpf());
//    stmtUsuario.setString(2, usuario.getNome());
//    stmtUsuario.setString(3, usuario.getApelido());
//
//
//    }
    }
    
}
