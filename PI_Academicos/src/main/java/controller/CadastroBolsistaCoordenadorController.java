package controller;

import java.io.File;
import java.nio.file.Paths;
import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;
import javafx.stage.Stage;
import model.Bolsista;
import model.BolsistaDAO;
import model.Projeto;
import model.Solicitacao;
import model.SolicitacaoDAO;
import model.Usuario;


public class CadastroBolsistaCoordenadorController {

     private Stage stageCadastrarBolsistaCoordenador;
     File arquivoPDF = null;
     private final String DIRETORIO_PDFS = Paths.get(System.getProperty("user.home"), "pdfs_baixados").toString();
     Projeto projeto;
     Bolsista bolsista;
     Usuario usuario;
     
    @FXML
    private DatePicker DataFimdaBolsa;

    @FXML
    private DatePicker DataInicioBolsa;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnPDF;
    
    @FXML
    private Label lblAbrirArquivo;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblCadastrarBolsista;

    @FXML
    private Label lblCurso;

    @FXML
    private Label lblData;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFimdaBolsa;

    @FXML
    private Label lblIniciodaBolsa;

    @FXML
    private Label lblMatricula;

    @FXML
    private Label lblNomeCompleto;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtCurso;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtNomeCompleto;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;


    @FXML
    void OnClickEnviar(ActionEvent event) throws SQLException {
        try {
            Long cpf = Long.parseLong(txtCPF.getText());
            Long matricula = Long.parseLong(txtMatricula.getText());
            LocalDate dataInicio = DataInicioBolsa.getValue();
            LocalDate dataFim = DataFimdaBolsa.getValue();

            if (dataInicio == null || dataFim == null) {
                mostrarAviso("Erro", "As datas de início e fim da bolsa devem ser preenchidas.");
                return;
            }

            incluir(cpf, txtNomeCompleto.getText(), txtUsuario.getText(), txtEmail.getText(), txtSenha.getText(),
                    matricula, txtCurso.getText(), dataInicio, dataFim);

        } catch (NumberFormatException n) {
            mostrarAviso("CPF ou Matrícula inválidos", "Os valores inseridos para CPF e Matrícula devem ser apenas números.");
        }
        
        enviarSolicitacao();
    }


    @FXML
    void OnClickPDF(ActionEvent event) {

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
    
    
    void incluir(Long cpf, String nome, String apelido, String email, String senha, Long matricula, String curso, LocalDate dataInicio, LocalDate dataFim) throws SQLException {
         usuario = new Usuario(cpf, nome, apelido, email, senha);
         bolsista = new Bolsista(matricula, curso, dataInicio, dataFim);
        int repetido = new BolsistaDAO().validarApelido(apelido, 0);

        if (repetido > 0) {
            mostrarAviso("Nome de usuário indisponível", "Este nome de usuário JÁ está sendo UTILIZADO.");
        } else if (nome.isEmpty() || apelido.isEmpty() || email.isEmpty() || senha.isEmpty() || curso.isEmpty()) {
            mostrarAviso("Campos obrigatórios", "TODOS os campos devem ser preenchidos.");
        } else {
            new BolsistaDAO().cadastrarUsuarioBolsista(usuario, bolsista, projeto);
            mostrarConfirmacao("Cadastro realizado", "O bolsista foi registrado com sucesso!");
            stageCadastrarBolsistaCoordenador.close();
        }
    }

     public void setStage(Stage TelaCadastroBolsistaCoordenador){
        this.stageCadastrarBolsistaCoordenador = TelaCadastroBolsistaCoordenador;
    }

     public void setProjeto(Projeto projeto){
        this.projeto = projeto;
    }
     
     public void enviarSolicitacao(){
     
     if (arquivoPDF != null) {
            try {
          
                usuario.setId(bolsista.getId());
                
                String descricao = "Solicitação para cadastro de bolsista de projeto: "
                        + "\nNome completo: " + txtNomeCompleto.getText() 
                        + "\nNome de usuário: " + txtUsuario.getText() 
                        + "\nCPF: " + txtCPF.getText()
                        + "\nCurso: " + txtCurso.getText()
                        + "\nMatrícula: " + txtMatricula.getText()
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