package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Coordenador;
import model.CoordenadorDAO;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class CadastroCoordenadorController {
    
    private Stage stageCadastroCoordenador;
    
    @FXML
    private Button btnSubmeter;

    @FXML
    private ImageView imgLogo;

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
        
        try{
        Long cpf = Long.parseLong(txtCPF.getText());
        int siape = Integer.parseInt(txtSIAPE.getText());
        incluir(cpf,txtNomeCompleto.getText(),txtUsuario.getText(),txtEmail.getText(),txtSenha.getText(), siape, txtFormacao.getText());
        }catch(NumberFormatException n){
            mostrarAviso("CPF ou SIAPE inválidos","Os valores inseridos para CPF e SIAPE devem ser apenas números");
        }
        
        
    }
    
    public void setStage(Stage telaCadastroCoordenador){
        this.stageCadastroCoordenador = telaCadastroCoordenador;
    }
    
    void incluir(Long cpf, String nome, String apelido, String email, String senha, int siape, String formacao) throws SQLException {
        Usuario usuario = new Usuario(cpf, nome, apelido, email, senha);
        Coordenador coordenador = new Coordenador(siape, formacao);
        int repetido = new CoordenadorDAO().validarApelido(apelido,0);
        if(repetido>0){
            mostrarAviso("Nome de usuário indisponível","Este nome de usuário já está sendo usado");
           
        }
        else{
        new CoordenadorDAO().cadastrarUsuarioCoordenador(usuario,coordenador);
        mostrarConfirmacao("Usuário cadastrado","O usuário foi registrado no sistema com sucesso!");
        stageCadastroCoordenador.close();
        }
    }
    
}
