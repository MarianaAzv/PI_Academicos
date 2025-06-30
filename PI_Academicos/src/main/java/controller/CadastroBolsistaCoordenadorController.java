package controller;

import java.io.File;
import java.nio.file.Paths;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import model.*;
import util.*;

import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class CadastroBolsistaCoordenadorController implements INotificacaoAlert {

    private Stage stageCadastrarBolsistaCoordenador;
    private final String DIRETORIO_PDFS = Paths.get(System.getProperty("user.home"), "pdfs_baixados").toString();
    private File arquivoPDF = null;

    private Projeto projeto;
    private Bolsista bolsista;
    private Usuario usuario;
    private Origem origem;
    private Coordenador coordenador;
    private CriarProjetoController criarprojetocontrtoller;

    @FXML
    private DatePicker DataFimdaBolsa;
    @FXML
    private DatePicker DataInicioBolsa;
    @FXML
    private Button btnEnviar, btnPDF;
    @FXML
    private Label lblAbrirArquivo;
    @FXML
    private TextField txtCPF, txtCurso, txtEmail, txtMatricula, txtNomeCompleto, txtSenha, txtUsuario;

    //Verificar se aqule cpf ja esta vingulado deu o alerta
    @FXML
    void OnClickEnviar(ActionEvent event) throws SQLException, IOException {
        String cpfDigitado = txtCPF.getText().trim();
        BolsistaDAO dao = new BolsistaDAO();
        Bolsista bolsistaExistente = dao.buscarPorCPF(cpfDigitado, projeto.getIdProjeto());

        if (bolsistaExistente != null) {
            if (dao.vinculadoEmOutroProjeto(bolsistaExistente.getId(), projeto.getIdProjeto())) {
                alerta("Este bolsista já está vinculado a outro projeto.", 2, "Vínculo existente");

                return;
            }
            if (dao.jaVinculadoAoProjeto(bolsistaExistente.getId(), projeto.getIdProjeto())) {
                alerta("Este bolsista já está vinculado ao projeto.", 2, "Já vinculado");
                stageCadastrarBolsistaCoordenador.close();
                return;
            }

            if (arquivoPDF == null) {
                alerta("Você deve selecionar um arquivo PDF antes de submeter.", 2, "PDF obrigatório");
                return;
            }

            LocalDate dataInicio = DataInicioBolsa.getValue();
            LocalDate dataFim = DataFimdaBolsa.getValue();
            LocalDate dataFimProjeto = projeto.getDataFim();
            LocalDate dataInicioProjeto = projeto.getDataInicio();

            if (dataInicio == null || dataFim == null) {
                alerta("As datas de início e fim da bolsa devem ser preenchidas.", 2, "Erro");
                return;
            }

            if (dataFim.isAfter(dataFimProjeto)) {
                alerta("A data de fim do bolsista não pode ser maior que a do projeto.", 2, "Data inválida");
                return;
            }

            if (dataInicio.isAfter(dataFim)) {
                alerta("A data do início não pode ser maior que a de fim.", 2, "Data inválida");
                return;
            }
            if (dataInicio.isBefore(dataInicioProjeto)) {
                alerta("A data do inicio do bolsista não pode ser menor que a data de inicio do projeto", 2, "Data inválida");
            }
            if (dataFim.isBefore(dataInicioProjeto)) {
                alerta("A data do fim do bolsista não pode ser menor que a data de inicio do prjeto", 2, "Data inválida");
            }

            dao.atualizarVinculo(bolsistaExistente.getId(), projeto.getIdProjeto(), dataInicio, dataFim);

            usuario = new Usuario();
            usuario.setId(bolsistaExistente.getId());
            enviarSolicitacao();

            alerta("O bolsista já existia e foi vinculado ao projeto com sucesso.", 3, "Bolsista vinculado");
            return;
        }

        // Novo cadastro
        if (arquivoPDF == null) {
            alerta("Você deve selecionar um arquivo PDF antes de submeter.", 2, "PDF obrigatório");
            return;
        }

        if (txtCPF.getText().isEmpty() || txtMatricula.getText().isEmpty() || txtNomeCompleto.getText().isEmpty()
                || txtUsuario.getText().isEmpty() || txtEmail.getText().isEmpty()
                || txtSenha.getText().isEmpty() || txtCurso.getText().isEmpty()
                || DataInicioBolsa.getValue() == null || DataFimdaBolsa.getValue() == null) {
            alerta("Por favor, preencha todos os campos.", 2, "Erro");
            return;
        }

        if (!CPF.isValid(txtCPF.getText())) {
            alerta("CPF inválido.", 2, "Erro");
            return;
        }

        if (!Email.isValidEmail(txtEmail.getText())) {
            alerta("Email inválido.", 2, "Erro");
            return;
        }

        if (!Apenasletras.isLetras(txtNomeCompleto.getText())) {
            alerta("Nome inválido (apenas letras são permitidas).", 2, "Erro");
            return;
        }

        if (!Apenasletras.isLetras(txtCurso.getText())) {
            alerta("Curso inválido (apenas letras são permitidas).", 2, "Erro");
            return;
        }

        if (!Senha.senhaForte(txtSenha.getText())) {
            alerta("Senha inválido(A senha precisa conter 6 digitos, um numeros, uma letra maiuscula e uma letra minuscula e um caracter especial).", 2, "Erro");
            return;
        }
        if (!ApenasNumeros.isNumeros(txtMatricula.getText())) {
            alerta("Matricula inválida(Apenas números).", 2, "Erro");
            return;
        }

        try {
            Long matricula = Long.parseLong(txtMatricula.getText());
            LocalDate dataInicio = DataInicioBolsa.getValue();
            LocalDate dataFim = DataFimdaBolsa.getValue();
            LocalDate dataFimProjeto = projeto.getDataFim();
            LocalDate dataInicioProjeto = projeto.getDataInicio();

            if (dataFim.isAfter(dataFimProjeto)) {
                alerta("A data de fim do bolsista não pode ser maior que a do projeto.", 2, "Data inválida");
                return;
            }

            if (dataInicio.isAfter(dataFim)) {
                alerta("A data de início não pode ser posterior à data de fim.", 2, "Data inválida");
                return;
            }

            if (dataInicio.isBefore(dataInicioProjeto)) {
                alerta("A data de inicio do bolsista não pode seer menor que a data de inicio do projeto", 2, "Data inválida");
                return;
            }

            if (dataFim.isBefore(dataInicioProjeto)) {
                alerta("A data de fim do bolsista não pode ser menor que a data de inicio do projeto", 2, "Data inválida");
                return;
            }

            int idCampus = projeto.getCampus().getIdCampus();

            if (dao.existeMatriculaNoCampus(matricula, idCampus)) {
                alerta("Já existe um bolsista com essa matrícula neste campus.", 2, "Matrícula duplicada");
                return;
            }

            incluir(txtCPF.getText(), txtNomeCompleto.getText(), txtUsuario.getText(), txtEmail.getText(),
                    txtSenha.getText(), matricula, txtCurso.getText(), dataInicio, dataFim);
        } catch (NumberFormatException e) {
            alerta("Matrícula deve conter apenas números.", 2, "Erro");
        }
    }

    @FXML
    void OnClickPDF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar arquivo PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos PDF", "*.pdf"));
        arquivoPDF = fileChooser.showOpenDialog(btnPDF.getScene().getWindow());

        if (arquivoPDF != null) {
            lblAbrirArquivo.setText(arquivoPDF.getName());
        }
    }

    @FXML
    void onClickAbrirArquivo(MouseEvent event) {
        if (arquivoPDF != null && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(arquivoPDF);
            } catch (IOException e) {
                System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
            }
        }
    }

    @FXML
    void onCpfDigitado(KeyEvent event) throws IOException {
        String cpfDigitado = txtCPF.getText().trim();

        if (CPF.isValid(cpfDigitado)) {
            try {
                BolsistaDAO dao = new BolsistaDAO();
                Bolsista existente = dao.buscarPorCPF(cpfDigitado, projeto.getIdProjeto());

                if (existente != null) {
                    this.bolsista = existente;

                    txtNomeCompleto.setText(existente.getNome());
                    txtUsuario.setText(existente.getApelido());
                    txtEmail.setText(existente.getEmail());
                    txtCurso.setText(existente.getCurso());
                    txtMatricula.setText(String.valueOf(existente.getMatricula()));
                    txtSenha.setText("********");

                    DataInicioBolsa.setValue(existente.getDataInicio());
                    DataFimdaBolsa.setValue(existente.getDataFim());

                    txtNomeCompleto.setDisable(true);
                    txtUsuario.setDisable(true);
                    txtEmail.setDisable(true);
                    txtCurso.setDisable(true);
                    txtMatricula.setDisable(true);
                    txtSenha.setDisable(true);

                    DataInicioBolsa.setDisable(false);
                    DataFimdaBolsa.setDisable(false);

                    btnEnviar.setText("Vincular");
                } else {
                    txtNomeCompleto.clear();
                    txtUsuario.clear();
                    txtEmail.clear();
                    txtCurso.clear();
                    txtMatricula.clear();
                    txtSenha.clear();

                    txtNomeCompleto.setDisable(false);
                    txtUsuario.setDisable(false);
                    txtEmail.setDisable(false);
                    txtCurso.setDisable(false);
                    txtMatricula.setDisable(false);
                    txtSenha.setDisable(false);

                    DataInicioBolsa.setValue(null);
                    DataFimdaBolsa.setValue(null);

                    btnEnviar.setText("Cadastrar");
                }
            } catch (SQLException e) {
                alerta("Erro ao consultar CPF no banco de dados.", 2, "Erro");
            }
        }
    }

    // ---------- Setters ----------
    public void setStage(Stage stage) {
        this.stageCadastrarBolsistaCoordenador = stage;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public void setControllerCriar(CriarProjetoController controller) {
        this.criarprojetocontrtoller = controller;
    }

    // ---------- Métodos ----------
    void incluir(String cpf, String nome, String apelido, String email, String senha,
            Long matricula, String curso, LocalDate dataInicio, LocalDate dataFim) throws SQLException, IOException {
        
        Foto fotoPerfil = new Foto(carregarImagemPadrao());
        usuario = new Usuario(cpf, nome, apelido, email, senha);
        bolsista = new Bolsista(matricula, curso, dataInicio, dataFim);

        BolsistaDAO dao = new BolsistaDAO();
        if (dao.validarApelido(apelido, 0) > 0) {
            alerta("Este nome de usuário já está em uso.", 2, "Nome de usuário indisponível");
            return;
        }

        dao.cadastrarUsuarioBolsista(usuario, bolsista, projeto, fotoPerfil);
        alerta("O bolsista foi registrado com sucesso!", 3, "Cadastro realizado");

        if (origem == Origem.atualizar_projeto) {
            VoltarParaAtualizarprojeto();
        } else if (origem == Origem.cadastro_projeto) {
            Irtelacoordenador();
        } else {
            alerta("Origem da tela desconhecida.", 1, "Erro");
        }
    }

    public void enviarSolicitacao() {
        if (arquivoPDF != null) {
            try {
                usuario.setId(bolsista.getId());

                String descricao = "Solicitação para cadastro de bolsista:\n"
                        + "Nome: " + txtNomeCompleto.getText() + "\n"
                        + "Usuário: " + txtUsuario.getText() + "\n"
                        + "CPF: " + txtCPF.getText() + "\n"
                        + "Curso: " + txtCurso.getText() + "\n"
                        + "Matrícula: " + txtMatricula.getText() + "\n"
                        + "Email: " + txtEmail.getText();

                Solicitacao solicitacao = new Solicitacao(usuario, descricao, arquivoPDF);
                new SolicitacaoDAO().salvarPDF(solicitacao);
            } catch (IOException e) {
                System.err.println("Erro ao ler o PDF: " + e.getMessage());
            }
        }
    }

    public void VoltarParaAtualizarprojeto() throws IOException {
        stageCadastrarBolsistaCoordenador.close();
    }

    public void Irtelacoordenador() throws IOException {
        URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePrincipal = new Stage();
        TelaPrincipalCoordenadorController tpc = loader.getController();
        tpc.setStagePrincipal(stagePrincipal);
        tpc.setCoordenador(coordenador);
        tpc.setProjeto(projeto);

        stagePrincipal.setOnShown(evento -> tpc.ajustarElementosJanela(coordenador, projeto));
        stagePrincipal.setScene(new Scene(root));
        stagePrincipal.setTitle("Tela principal Coordenador");
        stagePrincipal.setMaximized(true);
        stagePrincipal.show();

        criarprojetocontrtoller.Close();
        stageCadastrarBolsistaCoordenador.close();
    }
    
    public byte[] carregarImagemPadrao() {
    try (InputStream is = getClass().getResourceAsStream("/imagens/FotoPerfilUsuarioDefault.png");
         ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }

        return baos.toByteArray();

    } catch (IOException | NullPointerException e) {
        e.printStackTrace();
        return null;
    }
    }

    public void alerta(String msg, int tipo, String titulo) throws IOException {
        URL url = new File("src/main/java/view/AlertGenerico.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageAlerta = new Stage();

        AlertGenericoController vpb = loader.getController();
        vpb.setMsg(msg);
        vpb.setTipo(tipo);
        vpb.setStage(stageAlerta);
        vpb.setControllerResposta(this);

        Scene cena = new Scene(root);
        stageAlerta.setTitle(titulo);
        stageAlerta.setScene(cena);

        stageAlerta.show();

    }

    @Override
    public void btnOk() {

    }
}

