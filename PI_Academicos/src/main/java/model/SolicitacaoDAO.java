package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class SolicitacaoDAO extends GenericDAO{
    
    public void cadastrarSolicitacao(Solicitacao solicitacao) throws SQLException, FileNotFoundException{
    
        Connection con = conectarDAO();
        
        String sql = "INSERT INTO solicitacoes (idUsuario, data, descricao, aceitacao, anexo) VALUES (?, ?, ?, ?, ?)";
        
        /*try{
             PreparedStatement stmt = con.prepareStatement(sql);
             FileInputStream fis = new FileInputStream(solicitacao.getAnexo()) {
        
           
            stmt.setInt(1, solicitacao.getUsuario().getId());
            stmt.setBinaryStream(5, fis, (int) selectedFile.length());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("PDF saved successfully. Rows affected: " + rowsAffected);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    
    
    
    
    
    
    
    public void insertSolicitacao(Solicitacao solicitacao) throws SQLException, FileNotFoundException, IOException{
        

        Connection con = conectarDAO();
        
        String sql = "INSERT INTO solicitacao (idUsuario, data, descricao, aceitacao, anexo) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (con){
             PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, solicitacao.getIdSolicitacao());
            stmt.setInt(2, solicitacao.getUsuario().getId());
            
            // Formatar data para string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setString(3, solicitacao.getData().format(formatter));

            stmt.setString(4, solicitacao.getDescricao());
            stmt.setBoolean(5, solicitacao.isAceitacao());
            
            ResultSet keys = stmt.getGeneratedKeys();
    if (keys.next()) {
        int idGerado = keys.getInt(1);
        solicitacao.setIdSolicitacao(idGerado);
            
            // 
            if (solicitacao.getAnexo() != null) {
                try (FileInputStream fis = new FileInputStream(solicitacao.getAnexo())) {
                    stmt.setBinaryStream(6, fis, (int) solicitacao.getAnexo().length());
                }
            } else {
                stmt.setNull(6, java.sql.Types.BLOB); // No file attached
            }

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Solicitação salva com sucesso! Linhas afetadas: " + rowsAffected);
    }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        }
    
}






