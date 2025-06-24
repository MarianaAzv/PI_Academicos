package model;

import controller.AtualizarPerfilCoordenadorController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CoordenadorDAO extends GenericDAO {

    public void cadastrarUsuarioCoordenador(Usuario usuario, Coordenador coordenador) {

        Connection con = conectarDAO();

        String queryUsuario = "INSERT INTO USUARIOS(cpf, nome, apelido, senha, email,ativa) VALUES(?,?,?,?,?,1)";
        String queryCoordenador = "INSERT INTO COORDENADORES(idUsuario, siape, formacao) VALUES (?,?,?)";

        try (con) {
            // Inserir em Usuario
            PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUsuario.setString(1, usuario.getCpf());
            stmtUsuario.setString(2, usuario.getNome());
            stmtUsuario.setString(3, usuario.getApelido());
            stmtUsuario.setString(4, usuario.getSenha());
            stmtUsuario.setString(5, usuario.getEmail());
            stmtUsuario.executeUpdate();

            ResultSet keys = stmtUsuario.getGeneratedKeys();
            if (keys.next()) {
                int idGerado = keys.getInt(1);
                coordenador.setId(idGerado);

                // Inserir em Coordenador
                PreparedStatement stmtCoordenador = con.prepareStatement(queryCoordenador);
                stmtCoordenador.setInt(1, idGerado);
                stmtCoordenador.setInt(2, coordenador.getSiape());
                stmtCoordenador.setString(3, coordenador.getFormacao());
                stmtCoordenador.executeUpdate();

                System.out.println("Coordenador cadastrado com ID: " + idGerado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Método para atualizar usuarios
    public void atualizarCoordenador(Coordenador coordenador) throws SQLException {

        Connection con = conectarDAO();

        String queryUsuario = "UPDATE USUARIOS SET cpf = ?, nome = ?, apelido = ?, senha = ?, email = ? WHERE idUsuario = ?";
        String queryCoordenador = "UPDATE COORDENADORES SET siape = ?, formacao = ? WHERE idUsuario = ?";

        try (con) {
            // Inserir em Usuario
            PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUsuario.setString(1, coordenador.getCpf());
            stmtUsuario.setString(2, coordenador.getNome());
            stmtUsuario.setString(3, coordenador.getApelido());
            stmtUsuario.setString(4, coordenador.getSenha());
            stmtUsuario.setString(5, coordenador.getEmail());
            stmtUsuario.setInt(6, coordenador.getId());
            stmtUsuario.executeUpdate();

            // Inserir em Coordenador
            PreparedStatement stmtCoordenador = con.prepareStatement(queryCoordenador);
            stmtCoordenador.setInt(1, coordenador.getSiape());
            stmtCoordenador.setString(2, coordenador.getFormacao());
            stmtCoordenador.setInt(3, coordenador.getId());
            stmtCoordenador.executeUpdate();

            System.out.println("Coordenador cadastrado com ID: " + coordenador.getId());
        }
    }

    //Método para desativar usuário
    public void desativarCoordenador(Coordenador coordenador) throws SQLException {

        Connection con = conectarDAO();

        String queryUsuario = "UPDATE USUARIOS SET ativa = 0 WHERE idUsuario = ?";

        try (con) {
            // Inserir em Usuario
            PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUsuario.setInt(1, coordenador.getId());
            stmtUsuario.executeUpdate();
        }
    }

    //Método para desativar usuário
    public void ativarCoordenador(Coordenador coordenador) throws SQLException {

        Connection con = conectarDAO();

        String queryUsuario = "UPDATE USUARIOS SET ativa = 1 WHERE idUsuario = ?";

        try (con) {
            // Inserir em Usuario
            PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUsuario.setInt(1, coordenador.getId());
            stmtUsuario.executeUpdate();
        }
    }

    //Método para validar nome de usuário
    public int validarApelido(String apelido, int id) throws SQLException {

        String buscarApelido = "select * from usuarios where apelido = ? AND idUsuario != ?";
        int rowCount = 0;
        Usuario usuario = null;
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
