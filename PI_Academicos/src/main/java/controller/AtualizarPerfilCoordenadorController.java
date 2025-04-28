package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Coordenador;
import model.CoordenadorDAO;
import model.Usuario;

public class AtualizarPerfilCoordenadorController {

    private Stage stageAtualizarCoordenador;
    Coordenador coordenador;
    private ArrayList<String> dados;
    
    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Text TxtNomeUsuario1;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnAtualizarPerfil1;

    @FXML
    private GridPane btnAtualizarProjeto;

    @FXML
    private Button btnOutrosProjetos;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerProjeto;

    @FXML
    private Button btnVerperfil;

    @FXML
    private ImageView imgFotoCoordenador;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgProjeto;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFormacao;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNomeBolsista;

    @FXML
    private Label lblNomeProjeto;

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
    private TextField txtNome;

    @FXML
    private TextField txtSIAPE;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    void AtualizarProjetoOnClick(MouseEvent event) {

    }

    @FXML
    void onClickArtigo(ActionEvent event) {

    }

    @FXML
    void onClickAtualizar(ActionEvent event) throws SQLException {
        Long cpf = Long.parseLong(txtCPF.getText());
        int siape = Integer.parseInt(txtSIAPE.getText());
        
        atualizarCoordenador(cpf,txtNome.getText(),txtUsuario.getText(),txtEmail.getText(),txtSenha.getText(), siape, txtFormacao.getText());
    }

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws SQLException {
        
   
    }

    @FXML
    void onClickOutrosProjetos(ActionEvent event) {

    }

    @FXML
    void onClickPublicacao(ActionEvent event) {

    }

    @FXML
    void onClickSair(ActionEvent event) {

    }

    @FXML
    void onClickVerPerfil(ActionEvent event) {

    }

    @FXML
    void onClickVerProjeto(ActionEvent event) {

    }
    
    public void setStage(Stage telaAtualizarCoordenador){
        this.stageAtualizarCoordenador = telaAtualizarCoordenador;
    }
    
    
    
    void atualizarCoordenador(Long cpf, String nome, String apelido, String email, String senha, int siape, String formacao) throws SQLException{
        Usuario usuario = new Usuario(cpf, nome, apelido, email, senha);
        Coordenador coordenador = new Coordenador(siape, formacao);
        new CoordenadorDAO().atualizarCoordenador(usuario,coordenador);
        System.out.println("Coordenador atualizado com sucesso!");
    }
    
   

}
