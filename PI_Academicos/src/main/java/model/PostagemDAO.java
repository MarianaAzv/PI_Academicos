package model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostagemDAO extends GenericDAO {

    private File outputFile;

    public void cadastrarPostagem(Postagem postagem, Foto foto) throws IOException, SQLException {

        Connection con = conectarDAO();

        String sqlPostagem = "INSERT INTO postagens (idProjeto, legenda) VALUES (?, ?)";
        String sqlFoto = "INSERT INTO fotos_postagens (arquivoFoto, idPostagem) VALUES (?, ?)";

        try {
            PreparedStatement stmtPostagem = con.prepareStatement(sqlPostagem, PreparedStatement.RETURN_GENERATED_KEYS);

            stmtPostagem.setInt(1, postagem.getIdProjeto());
            stmtPostagem.setString(2, postagem.getLegenda());
            stmtPostagem.executeUpdate();

            ResultSet keys = stmtPostagem.getGeneratedKeys();
            if (keys.next()) {
                int idGerado = keys.getInt(1);
                postagem.setId(idGerado);

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

    public void deletarPostagem(Postagem postagem) throws SQLException {

        Connection con = conectarDAO();

        String sqlPostagem = "DELETE FROM postagens WHERE idPostagem = ?;";
        String sqlFoto = "DELETE FROM fotos_postagens WHERE idPostagem = ?;";

        try {
            PreparedStatement stmtPostagem = con.prepareStatement(sqlPostagem);

            stmtPostagem.setInt(1, postagem.getId());
            stmtPostagem.executeUpdate();

            PreparedStatement stmtFoto = con.prepareStatement(sqlFoto);

            stmtFoto.setInt(1, postagem.getId());
            stmtFoto.executeUpdate();

            System.out.println("A postagem foi excluída");

        } catch (SQLException e) {
            System.err.println("Erro ao excluir postagem do banco de dados: " + e.getMessage());
            // Adicione aqui tratamento de erro mais robusto (ex: log, exceção customizada)
        }

    }

    public List<Postagem> listarPostagens(Projeto projeto) throws SQLException, IOException {
        List<Postagem> postagens = new ArrayList<>();
        String sql = "select postagens.idPostagem, idProjeto, legenda, data, idFoto, "
                + "arquivoFoto, fotos_postagens.idPostagem from postagens left join fotos_postagens "
                + "on postagens.idPostagem = fotos_postagens.idPostagem WHERE idProjeto = ?;";

        Connection con = conectarDAO();
        ResultSet rs = null;

        if (con != null) {
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, projeto.getIdProjeto());
                rs = stmt.executeQuery();

                int cont = 0;
                while (rs.next()) {
                    int idPostagem = rs.getInt("idPostagem");
                    int idProjeto = rs.getInt("idProjeto");
                    String legenda = rs.getString("legenda");
                    //Timestamp timestamp = rs.getTimestamp("data"); 
                    //LocalDateTime data = timestamp.toLocalDateTime(); // convert timestamp pra localdatetime
                    int idFoto = rs.getInt("idFoto");
                    byte[] dadosImagem = rs.getBytes("arquivoFoto");
                    Foto foto = new Foto(idFoto, dadosImagem);
                    Postagem postagem = new Postagem(idPostagem, idProjeto, foto, legenda);
                    postagens.add(postagem);
                    System.out.println("DAO: postagem carregada: " + postagem); // Imprime a foto carregada
                    cont++;

                }

                System.out.println("DAO: Total postagens carregadas do BD: " + cont);

            } finally {

                rs.close();

            }
        }

        return postagens;

    }

}
