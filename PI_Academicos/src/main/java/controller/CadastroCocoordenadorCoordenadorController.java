package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Coordenador;
import model.CoordenadorDAO;

public class CadastroCocoordenadorCoordenadorController {

    Stage stageCadastroCocoordenador;
    
    @FXML
    private Button btnEEnviar;

    @FXML
    private Button btnPDF;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblCadastrarCoCoordenador;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFormacao;

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
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txttSIAPE;

    @FXML
    void OnClickEnviar(ActionEvent event) throws SQLException {
        
        Long cpf = Long.parseLong(txtCPF.getText());
        int siape = Integer.parseInt(txttSIAPE.getText());
        cadastrar(cpf,txtNomeCompleto.getText(),txtUsuario.getText(),txtEmail.getText(),
                txtSenha.getText(), siape, txtFormacao.getText());
    }

    @FXML
    void OnClickPDF(ActionEvent event) {

    }
    
    void setStage(Stage telaCadastroCoordenador){
        this.stageCadastroCocoordenador = telaCadastroCoordenador;     
    }
    
     void cadastrar(Long cpf, String nome, String apelido, String email, String senha, int siape, String formacao) throws SQLException {
        Coordenador coordenador = new Coordenador(cpf, nome, apelido, email, senha, siape, formacao);
        //new CoordenadorDAO().cadastrar(coordenador);
        System.out.println("Registro inserido com sucesso!");
        stageCadastroCocoordenador.close();
    }

}
