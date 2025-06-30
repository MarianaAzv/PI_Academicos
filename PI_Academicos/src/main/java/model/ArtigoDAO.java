package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArtigoDAO extends GenericDAO {

    public void salvarArtigo(Artigo artigo) throws IOException, SQLException {

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
    
    public void deletarArtigo (Artigo artigo) throws SQLException{
        
        Connection con = conectarDAO();
        
        
        String sqlArtigo = "DELETE FROM artigos WHERE idArtigo = ?;";

        try {
            PreparedStatement stmtPostagem = con.prepareStatement(sqlArtigo);

            stmtPostagem.setInt(1, artigo.getId());
            stmtPostagem.executeUpdate();

            System.out.println("O artigo foi excluído");

        } catch (SQLException e) {
            System.err.println("Erro ao excluir artigo do banco de dados: " + e.getMessage());
            // Adicione aqui tratamento de erro mais robusto (ex: log, exceção customizada)
        }
        
    }
    
    public List<Artigo> listarArtigos(Projeto projeto) throws FileNotFoundException, SQLException{
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
                byte[] dadosArtigo = rs.getBytes("arquivo");               
                    String filePath = titulo + ".pdf";
                    
                    File file = new File(filePath); 
                    try{
                    FileOutputStream fos = new FileOutputStream(file); 
                    fos.write(dadosArtigo);
                    fos.close();
                    System.out.println("byte convertido para file");
                    } catch (IOException e) {
                    System.err.println("Ocorreu um erro ao converter o array de bytes para arquivo: " + e.getMessage());
                    e.printStackTrace();
                    }
                LocalDate dataPublicacao = rs.getDate("dataPublicacao").toLocalDate();
                int idProjeto = rs.getInt("idProjeto");
                Artigo artigo = new Artigo(idArtigo, idProjeto, titulo, resumo, autores, file, dataPublicacao);
                artigos.add(artigo);
                System.out.println("DAO: artigo carregado: " + artigo); // Imprime a foto carregada
                cont++;
  
        }
            
        
        
    }catch(SQLException e){
        e.printStackTrace();
    }
        
        finally{
            rs.close();
        }
    
}
        return artigos;
    }
}
