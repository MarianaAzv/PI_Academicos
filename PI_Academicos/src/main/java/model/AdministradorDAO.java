
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO extends GenericDAO {
    
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
    
    public void cadastrarUsuarioAdministrador(Usuario usuario, Administrador administrador){
        
        Connection con = conectarDAO();
        
        String queryUsuario = "INSERT INTO USUARIOS(cpf, nome, apelido, senha, email,ativa) VALUES(?,?,?,?,?,1)";
        String queryCoordenador = "INSERT INTO ADMINISTRADORES(idUsuario) VALUES (?)";

        try (con) {
            // Inserir em Usuario
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
                administrador.setId(idGerado);

                // Inserir em Coordenador
                PreparedStatement stmtAdministrador = con.prepareStatement(queryCoordenador);
                stmtAdministrador.setInt(1, idGerado);
                stmtAdministrador.executeUpdate();

                System.out.println("Administrados cadastrado com ID: " + idGerado);
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
        
    }
    
        public void atualizarAdministrador(Administrador administrador) throws SQLException {
  
        Connection con = conectarDAO();
        
        String queryUsuario = "UPDATE USUARIOS SET cpf = ?, nome = ?, apelido = ?, senha = ?, email = ? WHERE idUsuario = ?";

        try (con) {
        // Inserir em Usuario
        PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
        stmtUsuario.setLong(1, administrador.getCpf());
        stmtUsuario.setString(2, administrador.getNome());
        stmtUsuario.setString(3, administrador.getApelido());
        stmtUsuario.setString(4, administrador.getSenha());
        stmtUsuario.setString(5, administrador.getEmail());  
        stmtUsuario.setInt(6, administrador.getId()); 
        stmtUsuario.executeUpdate();

        System.out.println("Administrador cadastrado com ID: " + administrador.getId());
    }
}
}
