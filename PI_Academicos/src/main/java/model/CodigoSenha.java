package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CodigoSenha {

    public static String gerarCodigo(String email) {
        String codigo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        try (Connection conn = ConexaoBD.conectar()) {
            if (conn == null) {
                System.out.println("não conectou ao banco");
                return null;
            }

            String sqlBusca = "SELECT idUsuario FROM usuarios WHERE email = ?";
            PreparedStatement stmtBusca = conn.prepareStatement(sqlBusca);
            stmtBusca.setString(1, email);
            ResultSet rs = stmtBusca.executeQuery();

            if (!rs.next()) {
                return null;
            }

            int idUsuario = rs.getInt("idUsuario");

            String sqlInsert = "INSERT INTO recuperacao_senhas (idUsuario, codigo_recuperacao, expiracao_codigo) " +
                               "VALUES (?, ?, DATE_ADD(NOW(), INTERVAL 15 MINUTE))";
            PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
            stmtInsert.setInt(1, idUsuario);
            stmtInsert.setString(2, codigo);
            stmtInsert.executeUpdate();

            return codigo;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}