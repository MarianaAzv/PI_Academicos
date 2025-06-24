package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BolsistaDAO extends GenericDAO {

    // Método para cadastrar um novo bolsista no banco de dados
    public void cadastrarUsuarioBolsista(Usuario usuario, Bolsista bolsista, Projeto projeto) {
        Connection con = conectarDAO();

        String queryUsuario = "INSERT INTO USUARIOS(cpf, nome, apelido, senha, email, ativa) VALUES(?,?,?,?,?,1)";
        String queryBolsista = "INSERT INTO BOLSISTAS(idUsuario, matricula, curso) VALUES (?, ?, ?)";
        String queryBolsistaProjetos = "INSERT INTO BOLSISTAS_PROJETOS(idUsuario, idProjeto, dataInicio, dataFim) VALUES (?, ?, ?, ?)";

        try (con) {
            // Inserir usuário
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
                bolsista.setId(idGerado);

                // Inserir bolsista
                PreparedStatement stmtBolsista = con.prepareStatement(queryBolsista);
                stmtBolsista.setInt(1, idGerado);
                stmtBolsista.setLong(2, bolsista.getMatricula());
                stmtBolsista.setString(3, bolsista.getCurso());
                stmtBolsista.executeUpdate();

                // Inserir bolsista no projeto
                PreparedStatement stmtBolsistaProjeto = con.prepareStatement(queryBolsistaProjetos);
                stmtBolsistaProjeto.setInt(1, idGerado);
                stmtBolsistaProjeto.setInt(2, projeto.getIdProjeto()); // Mantendo funcionalidade do bolsista
                stmtBolsistaProjeto.setDate(3, bolsista.getDataInicio() != null ? Date.valueOf(bolsista.getDataInicio()) : null);
                stmtBolsistaProjeto.setDate(4, bolsista.getDataFim() != null ? Date.valueOf(bolsista.getDataFim()) : null);
                stmtBolsistaProjeto.executeUpdate();

                System.out.println("Bolsista cadastrado com ID: " + idGerado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar bolsista no banco
    public void atualizarBolsista(Bolsista bolsista, Projeto projeto) throws SQLException {
        Connection con = conectarDAO();

        String queryUsuario = "UPDATE USUARIOS SET cpf = ?, nome = ?, apelido = ?, senha = ?, email = ? WHERE idUsuario = ?";
        String queryBolsista = "UPDATE BOLSISTAS SET matricula = ?, curso = ? WHERE idUsuario = ?";
        String queryBolsistaProjetos = "UPDATE BOLSISTAS_PROJETOS SET  dataInicio = ?, dataFim = ? WHERE idUsuario = ? and idProjeto = ?";

        try (con) {
            // Atualiza USUARIOS
            con.setAutoCommit(false);
            PreparedStatement stmtUsuario = con.prepareStatement(queryUsuario);

            stmtUsuario.setString(1, bolsista.getCpf());
            stmtUsuario.setString(2, bolsista.getNome());
            stmtUsuario.setString(3, bolsista.getApelido());
            stmtUsuario.setString(4, bolsista.getSenha());
            stmtUsuario.setString(5, bolsista.getEmail());
            stmtUsuario.setInt(6, bolsista.getId());
            int linhasUsuario = stmtUsuario.executeUpdate();
            System.out.println("Linhas afetadas em USUARIOS: " + linhasUsuario);

            // Atualiza BOLSISTAS
            PreparedStatement stmtBolsista = con.prepareStatement(queryBolsista);
            stmtBolsista.setLong(1, bolsista.getMatricula());
            stmtBolsista.setString(2, bolsista.getCurso());
            stmtBolsista.setInt(3, bolsista.getId());
            int linhasBolsista = stmtBolsista.executeUpdate();
            System.out.println("Linhas afetadas em BOLSISTAS: " + linhasBolsista);

            // Atualiza BOLSISTAS_PROJETOS (mantendo funcionalidade exclusiva do bolsista)
            PreparedStatement stmtProjeto = con.prepareStatement(queryBolsistaProjetos);
            stmtProjeto.setDate(1, bolsista.getDataInicio() != null ? Date.valueOf(bolsista.getDataInicio()) : null);
            stmtProjeto.setDate(2, bolsista.getDataFim() != null ? Date.valueOf(bolsista.getDataFim()) : null);
            stmtProjeto.setInt(3, bolsista.getId());
            stmtProjeto.setInt(4, projeto.getIdProjeto()); // Mantendo a lógica do projeto do bolsista

            int linhasProjeto = stmtProjeto.executeUpdate();
            System.out.println("Linhas afetadas em BOLSISTAS_PROJETOS: " + linhasProjeto);
            con.commit();
        }
    }

    // Método para validar apelido
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

    public void buscarDados(Bolsista bolsista, Projeto projeto) {

    }

    public List<Bolsista> selecionarBolsistasPorProjeto(Projeto projeto) throws SQLException {
        List<Bolsista> bolsistas = new ArrayList<>();
        String sql = "Select bp.*,b.*,u.* from bolsistas_projetos bp inner join bolsistas b on b.idUsuario=bp.idUsuario inner join usuarios u  on b.idUsuario=u.idUsuario where bp.idProjeto = ?";

        Connection con = conectarDAO();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, projeto.getIdProjeto());

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Usuario u = new Usuario();
            u.setId(rs.getInt("idUsuario"));
            u.setNome(rs.getString("nome"));
            u.setCpf(rs.getString("cpf"));
            u.setApelido(rs.getString("apelido"));
            u.setEmail(rs.getString("email"));
            u.setAtiva(rs.getBoolean("ativa"));

            Bolsista b = new Bolsista();
            b.setId(rs.getInt("idUsuario"));
            b.setMatricula(rs.getInt("matricula"));
            b.setCurso(rs.getString("curso"));

            bolsistas.add(b);
        }

        return bolsistas;
    }

    public Bolsista buscarPorCPF(String cpf, int idProjeto) throws SQLException {
        String sql = "SELECT b.*, u.*, bp.dataInicio, bp.dataFim FROM bolsistas b INNER JOIN usuarios u ON b.idUsuario = u.idUsuario LEFT JOIN bolsistas_projetos bp ON bp.idUsuario = u.idUsuario AND bp.idProjeto = ? WHERE u.cpf = ? ";

        Connection con = conectarDAO();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idProjeto);
        stmt.setString(2, cpf);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Bolsista b = new Bolsista(
                    rs.getLong("matricula"),
                    rs.getString("curso"),
                    rs.getDate("dataInicio") != null ? rs.getDate("dataInicio").toLocalDate() : null,
                    rs.getDate("dataFim") != null ? rs.getDate("dataFim").toLocalDate() : null
            );

            b.setId(rs.getInt("idUsuario"));
            b.setCpf(rs.getString("cpf"));
            b.setNome(rs.getString("nome"));
            b.setApelido(rs.getString("apelido"));
            b.setEmail(rs.getString("email"));
            b.setSenha(rs.getString("senha"));
            return b;
        }
        return null;
    }

    public boolean jaVinculadoAoProjeto(int idUsuario, int idProjeto) throws SQLException {
        String sql = "SELECT * FROM bolsistas_projetos WHERE idUsuario = ? AND idProjeto = ?";
        Connection con = conectarDAO();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idProjeto);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public void vincularBolsistaAoProjeto(int idUsuario, int idProjeto) throws SQLException {
        String sql = "INSERT INTO bolsistas_projetos (idUsuario, idProjeto) VALUES (?, ?)";
        Connection con = conectarDAO();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idProjeto);
        stmt.executeUpdate();
    }

    public void atualizarVinculo(int idUsuario, int idProjeto, LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        String sql = "UPDATE bolsistas_projetos SET dataInicio = ?, dataFim = ? WHERE idUsuario = ? AND idProjeto = ?";
        try (Connection conn = conectarDAO(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(dataInicio));
            stmt.setDate(2, java.sql.Date.valueOf(dataFim));
            stmt.setInt(3, idUsuario);
            stmt.setInt(4, idProjeto);
            stmt.executeUpdate();
        }
    }

    public boolean vinculadoEmOutroProjeto(int idBolsista, int idProjetoAtual) throws SQLException {
        String sql = "SELECT COUNT(*) FROM projetos_bolsistas WHERE idBolsista = ? AND idProjeto <> ?";

        try (Connection con = conectarDAO(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idBolsista);
            stmt.setInt(2, idProjetoAtual);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
