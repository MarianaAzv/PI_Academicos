package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoticiaDAO extends GenericDAO{
    
    private File outputFile;
    
    public void cadastrarNoticia (Noticia noticia, Foto foto) throws IOException, SQLException{
        
        Connection con = conectarDAO();
        
        
        String sqlNoticia = "INSERT INTO noticiasgerais (idAdministrador, titulo, texto) VALUES (?, ?, ?)";
        String sqlFoto = "INSERT INTO fotos_noticias (arquivoFoto, idNoticia) VALUES (?, ?)";

        try {
             PreparedStatement stmtNoticia = con.prepareStatement(sqlNoticia, PreparedStatement.RETURN_GENERATED_KEYS);
  
            stmtNoticia.setInt(1, noticia.getIdAdministrador());
            stmtNoticia.setString(2, noticia.getTitulo());
            stmtNoticia.setString(3, noticia.getTexto());
            stmtNoticia.executeUpdate();
            
            ResultSet keys = stmtNoticia.getGeneratedKeys();
            if (keys.next()) {
               int idGerado = keys.getInt(1);
               noticia.setId(idGerado);
               
            PreparedStatement stmtFoto = con.prepareStatement(sqlFoto, PreparedStatement.RETURN_GENERATED_KEYS);
            
            
            stmtFoto.setBytes(1, foto.getDadosImagem());
            stmtFoto.setInt(2, idGerado);
            stmtFoto.executeUpdate();
            
            ResultSet keys2 = stmtFoto.getGeneratedKeys();
            if (keys2.next()) {
               int idGerado2 = keys2.getInt(1);
               foto.setId(idGerado2);
    }
            }
            

        } catch (SQLException e) {
            System.err.println("Erro ao salvar a imagem no banco de dados: " + e.getMessage());
            // Adicione aqui tratamento de erro mais robusto (ex: log, exceção customizada)
        }
    }
    
    
    public void deletarNoticia (Noticia noticia) throws SQLException{
        
        Connection con = conectarDAO();
        
        
        String sqlNoticia = "DELETE FROM noticiasgerais WHERE idNoticia = ?;";
        String sqlFoto = "DELETE FROM fotos_noticias WHERE idNoticia = ?;";
        
        try {
            PreparedStatement stmtNoticia = con.prepareStatement(sqlNoticia);
  
            stmtNoticia.setInt(1, noticia.getId());
            stmtNoticia.executeUpdate();
            
            PreparedStatement stmtFoto = con.prepareStatement(sqlFoto);
            
            stmtFoto.setInt(1, noticia.getId());
            stmtFoto.executeUpdate();
            
            System.out.println("A notícia foi excluída");
            
        }catch (SQLException e) {
            System.err.println("Erro ao excluir notícia do banco de dados: " + e.getMessage());
            // Adicione aqui tratamento de erro mais robusto (ex: log, exceção customizada)
        }
        
    }
    
    public List<Noticia> listarNoticias(Administrador adm) throws SQLException, IOException {
        List<Noticia> noticias = new ArrayList<>();
        String sql = "select noticiasgerais.idNoticia, idAdministrador, titulo, texto, dataPublicacao, idFoto," +
                     "arquivoFoto, fotos_noticias.idNoticia from noticiasgerais left join fotos_noticias " +
                     "on noticiasgerais.idNoticia = fotos_noticias.idNoticia WHERE idAdministrador = ?;";
        
        Connection con = conectarDAO();
        ResultSet rs = null;
        
        if(con!=null){
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, adm.getId());
            rs = stmt.executeQuery();

            int cont=0;
            while (rs.next()) {
                int idNoticia = rs.getInt("idNoticia");
                int idAdministrador = rs.getInt("idAdministrador");
                String titulo = rs.getString("titulo");
                String texto = rs.getString("texto");
                Timestamp timestamp = rs.getTimestamp("dataPublicacao"); 
                LocalDateTime data = timestamp.toLocalDateTime(); // convert timestamp pra localdatetime
                int idFoto = rs.getInt("idFoto");
                byte[] dadosImagem = rs.getBytes("arquivoFoto");
                //noticias.add(new Noticia(idNoticia, idAdministrador, titulo, texto, imagem, data));
                Foto foto = new Foto(idFoto, dadosImagem);
                Noticia noticia = new Noticia(idNoticia, idAdministrador, foto, titulo, texto, data);
                noticias.add(noticia);
                System.out.println("DAO: noticia carregada: " + noticia); // Imprime a foto carregada
                cont++;
  
        }
        
            
            System.out.println("DAO: Total de noticias carregadas do BD: " + cont);
            
        } finally {
            
                rs.close();
            
        }
        }
    
        return noticias;
        
    
    }
    
    
    
}
