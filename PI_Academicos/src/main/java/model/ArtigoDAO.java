package model;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
