package model;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtigoDAO extends GenericDAO{
    
    public void salvarArtigo(Artigo artigo) throws IOException, SQLException{
        
        Connection con = conectarDAO();
        
        String sql = "INSERT INTO artigos (titulo, resumo, autores, arquivo, dataPublicacao, idProjeto) VALUES (?, ?, ?, ?, ?, ?)";

        try {
             PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  
            byte[] conteudoPDF = Files.readAllBytes(artigo.getArquivo().toPath());

            stmt.setString(1, artigo.getTitulo());
            stmt.setString(2, artigo.getResumo());
            stmt.setString(3, artigo.getAutores());
            stmt.setBytes(4, conteudoPDF);
            stmt.setDate(5, Date.valueOf(artigo.getDataPublicacao()));
            stmt.setInt(6, artigo.getIdProjeto());
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
               int idGerado = keys.getInt(1);
               artigo.setId(idGerado);
    }
            

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o artigo no banco de dados: " + e.getMessage());
            // Adicione aqui tratamento de erro mais robusto (ex: log, exceção customizada)
        }
    }
    
    public List<Artigo> listarArtigos(Projeto projeto){
        List<Artigo> artigos = new ArrayList<>();
        String sql = "SELECT idArtigo, titulo, resumo, autores, arquivo, dataPublicacao, idProjeto FROM artigos WHERE idProjeto = ?;";
        
        Connection con = conectarDAO();
        ResultSet rs = null;
        
        if(con!=null){
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, projeto.getIdProjeto());
            rs = stmt.executeQuery();
            
            
            int cont=0;
            while (rs.next()) {
                int idArtigo = rs.getInt("idArtigo");
                String titulo = rs.getString("titulo");
                String resumo = rs.getString("resumo");
                String autores = rs.getString("autores");
               // Timestamp timestamp = rs.getTimestamp("dataPublicacao"); 
               // LocalDateTime data = timestamp.toLocalDateTime(); // convert timestamp pra localdatetime
               // int idFoto = rs.getInt("idFoto");
                byte[] dadosArtigo = rs.getBytes("arquivo");
                //FileUtils.writeByteArrayToFile(new File("pathname"), myByteArray);
              
               // Foto foto = new Foto(idFoto, dadosImagem);
               // Noticia noticia = new Noticia(idNoticia, idAdministrador, foto, titulo, texto, data);
               // noticias.add(noticia);
                //System.out.println("DAO: noticia carregada: " + noticia); // Imprime a foto carregada
                //cont++;
  
        }
            
        
        
    }catch(SQLException e){
        
    }
    
}
        return artigos;
    }
}
