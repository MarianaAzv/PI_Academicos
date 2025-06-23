package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Coordenador;
import model.CoordenadorDAO;
import model.Solicitacao;
import model.SolicitacaoDAO;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;
import util.Apenasletras;
import util.CPF;
import util.Email;
import util.Senha;

public class CadastroCoordenadorController {

    private Stage stageCadastroCoordenador;
    File arquivoPDF = null;
    private final String DIRETORIO_PDFS = Paths.get(System.getProperty("user.home"), "pdfs_baixados").toString();
    Coordenador coordenador;
    Usuario usuario;

    public void initialize() {
        File diretorio = new File(DIRETORIO_PDFS);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    @FXML
    private Button btnPDF;

    @FXML
    private Button btnSubmeter;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblAbrirArquivo;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblCadastro;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFormacao;

    @FXML
    private Label lblMensagemsdeSubmissaoparte1;

    @FXML
    private Label lblMensagemsdeSubmissaoparte2;

    @FXML
    private Label lblNomeCompleto;

    @FXML
    private Label lblSIAPE;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFormacao;

    @FXML
    private TextField txtNomeCompleto;

    @FXML
    private TextField txtSIAPE;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    void OnClickSubmeter(ActionEvent event) throws SQLException {

        try {
            if (arquivoPDF == null) {
                mostrarAviso("PDF obrigatório", "Você deve selecionar um arquivo PDF antes de submeter.");
                return;
            }

            if (txtCPF.getText().isEmpty() || txtSIAPE.getText().isEmpty() || txtNomeCompleto.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtEmail.getText().isEmpty() || txtSenha.getText().isEmpty() || txtFormacao.getText().isEmpty()) {

                mostrarAviso("Falta informação", "Por favor inserir todos os dados");
                return;
            } else {
                if (Senha.senhaForte(txtSenha.getText())) {
                    if (CPF.isValid(txtCPF.getText())) {
                        System.out.print("CPF valido");
                        if (Email.isValidEmail(txtEmail.getText())) {
                            System.out.print("O Email esta valido");
                            if (Apenasletras.isLetras(txtNomeCompleto.getText())) {
                                if (Apenasletras.isLetras(txtFormacao.getText())) {

                                    int siape = Integer.parseInt(txtSIAPE.getText());

                                    incluir(txtCPF.getText(), txtNomeCompleto.getText(), txtUsuario.getText(), txtEmail.getText(), txtSenha.getText(), siape, txtFormacao.getText());

                                } else {
                                    mostrarAviso("ERRO", "Por favor inserir a sua formação corretamente");
                                }
                            } else {
                                mostrarAviso("ERRO", "Por favor inserir o nome corretamente");
                            }
                        } else {
                            System.out.print("O Email invalido");
                            mostrarAviso("ERRO", "O Email não esta no formato esperado");
                        }
                    } else {
                        System.out.print("CPF invalido");
                        mostrarAviso("ERRO", "O CPF nao esta correto");
                    }
                } else {
                    mostrarAviso("ERRO", "A senha esta muito fraca, para uma senha forte é necessario ter 6 caracters,ter pelo menos 1 letra Maiuscula e 1 Letra minuscula, um numero e um simbulo especial");
                }
            }
        } catch (NumberFormatException n) {
            mostrarAviso("CPF ou SIAPE inválidos", "Os valores inseridos para CPF e SIAPE devem ser apenas números");
        }

        enviarSolicitacao();

    }

    @FXML
    void onClickPDF(ActionEvent event) {

        //adicao de janela para escolher arquivo pdf 
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.pdf"));
        arquivoPDF = fileChooser.showOpenDialog(btnPDF.getScene().getWindow());
        lblAbrirArquivo.setText(arquivoPDF.getName());

    }

    @FXML
    void onClickAbrirArquivo(MouseEvent event) {

        if (arquivoPDF != null) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(arquivoPDF);
                } else {
                    System.err.println("A funcionalidade de desktop não é suportada.");
                }
            } catch (IOException e) {
                System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum arquivo selecionado para abrir.");
        }
    }

    public void setStage(Stage telaCadastroCoordenador) {
        this.stageCadastroCoordenador = telaCadastroCoordenador;
    }

    void incluir(String cpf, String nome, String apelido, String email, String senha, int siape, String formacao) throws SQLException {
        usuario = new Usuario(cpf, nome, apelido, email, senha);
        coordenador = new Coordenador(siape, formacao);
        int repetido = new CoordenadorDAO().validarApelido(apelido, 0);
        if (repetido > 0) {
            mostrarAviso("Nome de usuário indisponível", "Este nome de usuário já está sendo usado");

        } else if (nome.isEmpty() || apelido.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            mostrarAviso("Campos de preenchimento obrigatórios", "Todos os campos de cadastro devem ser preenchidos.");
        } else {
            new CoordenadorDAO().cadastrarUsuarioCoordenador(usuario, coordenador);
            mostrarConfirmacao("Usuário cadastrado", "O usuário foi registrado no sistema com sucesso!");
            stageCadastroCoordenador.close();
        }
    }

    public void enviarSolicitacao() {

        if (arquivoPDF != null) {
            try {

                usuario.setId(coordenador.getId());

                String descricao = "Solicitação para cadastro de coordenador de projeto: "
                        + "\nNome completo: " + txtNomeCompleto.getText()
                        + "\nNome de usuário: " + txtUsuario.getText()
                        + "\nCPF: " + txtCPF.getText()
                        + "\nFormação: " + txtFormacao.getText()
                        + "\nSIAPE: " + txtSIAPE.getText()
                        + "\nE-mail: " + txtEmail.getText();
                Solicitacao solicitacao = new Solicitacao(usuario, descricao, arquivoPDF);
                new SolicitacaoDAO().salvarPDF(solicitacao);
                System.out.println("Arquivo PDF salvo no banco de dados.");

            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo PDF: " + e.getMessage());
            }
        }

    }

}
