package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
       
    
    public ObservableList<Solicitacao> listarSolicitacoes() throws SQLException, FileNotFoundException, IOException{
        
        Connection con = conectarDAO();
        
        ObservableList<Solicitacao> lista = FXCollections.observableArrayList();
        String sql = "SELECT * FROM solicitacoes";
        
        try {
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();
             
             while(rs.next()){
                 Solicitacao solicitacao = new Solicitacao();
                 solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
                 solicitacao.setIdUsuario(rs.getInt("idUsuario"));
                 solicitacao.setDescricao(rs.getString("descricao"));
                 solicitacao.setAceitacao(rs.getBoolean("aceitacao"));
                 byte[] anexoByte = rs.getBytes("anexo");
                 
                 String filePath = "C:/Users/Aluno/Downloads/arquivo.pdf";
                 File file = new File(filePath);
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(anexoByte);
                        solicitacao.setAnexo(file);
                        fos.close();
                        
                    }catch(IOException e) {
                        System.out.println("arquivo não foi criado");
                         e.printStackTrace();
                    }
                    
                    lista.add(solicitacao);
                    
                 }
                 
            rs.close();
            stmt.close();
            conectarDAO().close();
        
             }catch(SQLException s){
                 System.out.println("tabela sql não acessada (solicitacao)");
                  s.printStackTrace();
        }
        
        
        return lista;
}
    
     
       
    
}






