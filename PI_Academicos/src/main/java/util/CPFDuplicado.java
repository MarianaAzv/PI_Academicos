
package util;

import java.sql.SQLException;
import model.UsuarioDAO;


public class CPFDuplicado {
      public static boolean cpfDuplicado(String cpf) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            return dao.cpfJaCadastrado(cpf);
        } catch (SQLException e) {
            System.err.println("Erro ao verificar duplicidade de CPF: " + e.getMessage());
            return true; // Por segurança, assume duplicado se erro ocorrer
        }
    }
}
