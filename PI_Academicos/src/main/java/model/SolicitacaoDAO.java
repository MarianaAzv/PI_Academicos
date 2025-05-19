package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class SolicitacaoDAO extends GenericDAO{
    
    
    public void salvarPDF (Solicitacao solicitacao) throws IOException{
        
        Connection con = conectarDAO();
        
        String sql = "INSERT INTO solicitacoes (idUsuario, descricao, aceitacao, anexo) VALUES (?, ?, 0, ?)";

        try {
             PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  
            byte[] conteudoPDF = Files.readAllBytes(solicitacao.getAnexo().toPath());

            stmt.setInt(1, solicitacao.getUsuario().getId());
            stmt.setString(2, solicitacao.getDescricao());
            stmt.setBytes(3, conteudoPDF);
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
               int idGerado = keys.getInt(1);
               solicitacao.setIdSolicitacao(idGerado);
    }
            

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o PDF no banco de dados: " + e.getMessage());
            // Adicione aqui tratamento de erro mais robusto (ex: log, exceção customizada)
        }
    }
    
    public byte[] carregarConteudoPDF(int idSolicitacao) {
        
        Connection con = conectarDAO();
        
        String sql = "SELECT anexo FROM solicitacoes WHERE idSolicitacao = ?";
        byte[] conteudoPDF = null;
        
        
        try {
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idSolicitacao);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
                conteudoPDF = rs.getBytes("anexo");
            }
        
        } catch (SQLException e) {
            System.err.println("Erro ao salvar o PDF no banco de dados: " + e.getMessage());
        }
        return conteudoPDF;
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






