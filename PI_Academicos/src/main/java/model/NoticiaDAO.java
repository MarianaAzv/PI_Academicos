package model;

import java.io.IOException;
import java.nio.file.Files;
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
    
    public void cadastrarNoticia (Noticia noticia, Foto foto) throws IOException, SQLException{
        
        Connection con = conectarDAO();
        
        
        String sqlNoticia = "INSERT INTO noticiasgerais (idAdministrador, idFoto, titulo, texto) VALUES (?, ?, ?)";
        String sqlFoto = "INSERT INTO fotos (arquivoFoto) VALUES (?, ?)";

        try {
             PreparedStatement stmtNoticia = con.prepareStatement(sqlNoticia, PreparedStatement.RETURN_GENERATED_KEYS);
  
            stmtNoticia.setInt(1, noticia.getIdAdministrador());
            //stmtNoticia.setInt(2, noticia.getIdFoto());
            stmtNoticia.setString(2, noticia.getTitulo());
            stmtNoticia.setString(3, noticia.getTexto());
            stmtNoticia.executeUpdate();
            
            ResultSet keys = stmtNoticia.getGeneratedKeys();
            if (keys.next()) {
               int idGerado = keys.getInt(1);
               noticia.setId(idGerado);
               
            PreparedStatement stmtFoto = con.prepareStatement(sqlFoto, PreparedStatement.RETURN_GENERATED_KEYS);
            
            byte[] conteudoArquivo = Files.readAllBytes(foto.getArquivo().toPath());
            stmtFoto.setBytes(1, conteudoArquivo);
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
    
    public List<Noticia> listarNoticias() throws SQLException {
        List<Noticia> noticias = new ArrayList<>();
        String sql = "SELECT idNoticia, idAdministrador, idFoto titulo, texto, dataPublicacao FROM noticiasgerais;";
        
        Connection con = conectarDAO();
        ResultSet rs = null;
        
        if(con!=null){
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            int cont=0;
            while (rs.next()) {
                int idNoticia = rs.getInt("idNoticia");
                int idAdministrador = rs.getInt("idAdministrador");
                int idFoto = rs.getInt("idFoto");
                String titulo = rs.getString("titulo");
                String texto = rs.getString("texto");
                Timestamp timestamp = rs.getTimestamp("dataPublicacao"); 
                LocalDateTime data = timestamp.toLocalDateTime(); // convert timestamp pra localdatetime
                //noticias.add(new Noticia(idNoticia, idAdministrador, titulo, texto, imagem, data));
                Noticia noticia = new Noticia(idNoticia, idAdministrador, idFoto, titulo, texto, data);
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
