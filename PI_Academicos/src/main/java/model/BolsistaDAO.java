package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BolsistaDAO extends GenericDAO {

    public void cadastrarUsuarioBolsista(Usuario usuario, Bolsista bolsista) {
        Connection con = conectarDAO();

        String queryUsuario = "INSERT INTO USUARIOS(cpf, nome, apelido, senha, email, ativa) VALUES(?,?,?,?,?,1)";
        String queryBolsista = "INSERT INTO BOLSISTAS(idUsuario, matricula, curso, acessoPostagens, acessoArtigos) VALUES (?,?,?,?,?,?,?)";

        try (con) {
            // inserir usuario
            PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUsuario.setLong(1, usuario.getCpf());
            stmtUsuario.setString(2, usuario.getNome());
            stmtUsuario.setString(3, usuario.getApelido());
            stmtUsuario.setString(4, usuario.getSenha());
            stmtUsuario.setString(5, usuario.getEmail());
            stmtUsuario.executeUpdate();

            ResultSet keys = stmtUsuario.getGeneratedKeys();
            if (keys.next()) {
                int idGerado = keys.getInt(1);
                bolsista.setId(idGerado);

                // inserir bolsista
                PreparedStatement stmtBolsista = con.prepareStatement(queryBolsista);
                stmtBolsista.setInt(1, idGerado);
                stmtBolsista.setLong(2, bolsista.getMatricula());
                stmtBolsista.setString(3, bolsista.getCurso());
                stmtBolsista.setBoolean(4, bolsista.getAcessoPostagens());
                stmtBolsista.setBoolean(5, bolsista.getAcessoArtigos());
                stmtBolsista.executeUpdate();

                System.out.println("Bolsista cadastrado com ID: " + idGerado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//atualia
    public void atualizarBolsista(Bolsista bolsista) throws SQLException {
        Connection con = conectarDAO();

        String queryUsuario = "UPDATE USUARIOS SET cpf = ?, nome = ?, apelido = ?, senha = ?, email = ? WHERE idUsuario = ?";
        String queryBolsista = "UPDATE BOLSISTAS SET matricula = ?, curso = ?, acessoPostagens = ?, acessoArtigos = ? WHERE idUsuario = ?";

        try (con) {
            // inser usuario
            PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUsuario.setLong(1, bolsista.getCpf());
            stmtUsuario.setString(2, bolsista.getNome());
            stmtUsuario.setString(3, bolsista.getApelido());
            stmtUsuario.setString(4, bolsista.getSenha());
            stmtUsuario.setString(5, bolsista.getEmail());
            stmtUsuario.setInt(6, bolsista.getId());
            stmtUsuario.executeUpdate();

            // inser bolsista
            PreparedStatement stmtBolsista = con.prepareStatement(queryBolsista);
            stmtBolsista.setLong(1, bolsista.getMatricula());
            stmtBolsista.setString(2, bolsista.getCurso());
            stmtBolsista.setBoolean(3, bolsista.getAcessoPostagens());
            stmtBolsista.setBoolean(4, bolsista.getAcessoArtigos());
            stmtBolsista.setInt(5, bolsista.getId());
            stmtBolsista.executeUpdate();

            System.out.println("Bolsista atualizado com ID: " + bolsista.getId());
        }
    }
//valida
    public int validarApelido(String apelido, int id) throws SQLException {
        String buscarApelido = "SELECT * FROM USUARIOS WHERE apelido = ? AND idUsuario != ?";
        int rowCount = 0;

        Connection con = conectarDAO();
        if (con != null) {
            PreparedStatement stmtUsuario = con.prepareStatement(buscarApelido);
            stmtUsuario.setString(1, apelido);
            stmtUsuario.setInt(2, id);
            ResultSet rs = stmtUsuario.executeQuery();
            while (rs.next()) {
                rowCount++;
            }
            System.out.println("Total de linhas retornadas: " + rowCount);
        }
        return rowCount;
    }
}