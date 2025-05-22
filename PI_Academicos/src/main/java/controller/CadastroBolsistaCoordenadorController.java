package controller;

import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;
import javafx.stage.Stage;
import model.Bolsista;
import model.BolsistaDAO;
import model.Projeto;
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
    }


    @FXML
    void OnClickPDF(ActionEvent event) {

    }
    
    

    //private void incluir(Long cpf, String text, String text0, String text1, String text2, Long matricula, String text3, LocalDate dataInicio, LocalDate dataFim) {
        
   // }
     void incluir(Long cpf, String nome, String apelido, String email, String senha, Long matricula, String curso, LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        Usuario usuario = new Usuario(cpf, nome, apelido, email, senha);
        Bolsista bolsista = new Bolsista(matricula, curso, dataInicio, dataFim);
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
    
   

}