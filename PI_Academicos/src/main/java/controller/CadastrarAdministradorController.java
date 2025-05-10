package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Administrador;
import model.AdministradorDAO;
import model.Coordenador;
import model.CoordenadorDAO;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class CadastrarAdministradorController {

    private Stage stage;
    @FXML
    private GridPane btnAtualizarProjeto;

    @FXML
    private Button btnCadastrar;

    @FXML
    private ImageView imgFotoAdministrador;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    void AtualizarProjetoOnClick(MouseEvent event) {

    }

    @FXML
    void onClickCadastrar(ActionEvent event) throws SQLException {

         try{
        Long cpf = Long.parseLong(txtCPF.getText());
        incluir(cpf,txtNome.getText(),txtUsuario.getText(),txtEmail.getText(),txtSenha.getText());
        }catch(NumberFormatException n){
            mostrarAviso("CPF inválido","O valor inserido para CPF deve ser apenas números");
        }
        
    }
    
    public void setStage(Stage stage){
        this.stage=stage;
    }
    
    void incluir(Long cpf, String nome, String apelido, String email, String senha) throws SQLException {
        Usuario usuario = new Usuario(cpf, nome, apelido, email, senha);
        Administrador administrador = new Administrador();
        int repetido = new AdministradorDAO().validarApelido(apelido,0);
        if(repetido>0){
            mostrarAviso("Nome de usuário indisponível","Este nome de usuário já está sendo usado");
           
        }
        else if(nome.isEmpty() || apelido.isEmpty() || email.isEmpty() || senha.isEmpty()){
         mostrarAviso("Campos de preenchimento obrigatórios","Todos os campos de cadastro devem ser preenchidos.");
    }
        else{
        new AdministradorDAO().cadastrarUsuarioAdministrador(usuario,administrador);
        mostrarConfirmacao("Usuário cadastrado","O usuário foi registrado no sistema com sucesso!");
        stage.close();
        }
    }
    
    

}
