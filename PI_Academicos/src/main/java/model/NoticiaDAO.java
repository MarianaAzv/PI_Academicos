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
    
    public void cadastrarNoticia (Noticia noticia) throws IOException{
        
        Connection con = conectarDAO();
        
        String sql = "INSERT INTO noticiasgerais (idAdministrador, titulo, texto, imagem) VALUES (?, ?, ?, ?)";

        try {
             PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  
            stmt.setInt(1, noticia.getIdAdministrador());
            stmt.setString(2, noticia.getTitulo());
            stmt.setString(3, noticia.getTexto());
            stmt.setString(4, noticia.getLinkImagem());
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
               int idGerado = keys.getInt(1);
               noticia.setId(idGerado);
    }
            

        } catch (SQLException e) {
            System.err.println("Erro ao salvar a imagem no banco de dados: " + e.getMessage());
            // Adicione aqui tratamento de erro mais robusto (ex: log, exceção customizada)
        }
    }
    
    public List<Noticia> listarNoticias() throws SQLException {
        List<Noticia> noticias = new ArrayList<>();
        String sql = "SELECT idNoticia, idAdministrador, titulo, texto, imagem, dataPublicacao FROM noticiasgerais;";
        
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
                String titulo = rs.getString("titulo");
                String texto = rs.getString("texto");
                String imagem = rs.getString("imagem");
                Timestamp timestamp = rs.getTimestamp("dataPublicacao"); 
                LocalDateTime data = timestamp.toLocalDateTime(); // convert timestamp pra localdatetime
                //noticias.add(new Noticia(idNoticia, idAdministrador, titulo, texto, imagem, data));
                Noticia noticia = new Noticia(idNoticia, idAdministrador, titulo, texto, imagem, data);
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
