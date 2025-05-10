package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static util.AlertaUtil.mostrarAviso;

import static util.AlertaUtil.mostrarAviso;

import javafx.stage.Stage;


public class CadastroBolsistaCoordenadorController {

     private Stage stageCadastrarBolsistaCoordenador;  
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
    void OnClickEnviar(ActionEvent event) {
         try{
        Long cpf = Long.parseLong(txtCPF.getText());
        Long matricula = Long.parseLong(txtMatricula.getText());
        incluir(cpf,txtNomeCompleto.getText(),txtUsuario.getText(),txtEmail.getText(),txtSenha.getText(), matricula, txtCurso.getText());
        }catch(NumberFormatException n){
            mostrarAviso("CPF ou Matricula inválidos","Os valores inseridos para CPF e Matricula do bolsista devem ser apenas números");
        }

    }

    @FXML
    void OnClickPDF(ActionEvent event) {

    }
    
    

    private void incluir(Long cpf, String text, String text0, String text1, String text2, Long matricula, String text3) {
        
    }
     public void setStage(Stage TelaCadastroBolsistaCoordenador){
        this.stageCadastrarBolsistaCoordenador = TelaCadastroBolsistaCoordenador;
    }
   

}