package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarPerfilCoordenadorController {

    private Stage stageAtualizarCoordenador;
    Coordenador coordenador;
    boolean ativa=true;
    
    
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
        
        try{
        Long cpf = Long.parseLong(txtCPF.getText());
        int siape = Integer.parseInt(txtSIAPE.getText()); 
        atualizarCoordenador(coordenador.getId(),cpf,txtNome.getText(),txtUsuario.getText(),txtEmail.getText(),txtSenha.getText(), ativa, siape, txtFormacao.getText());
        }catch(NumberFormatException n){
            mostrarAviso("CPF ou SIAPE inválidos","Os valores inseridos para CPF e SIAPE devem ser apenas números");
        }
    }

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws SQLException {
        
   
    }
    
    @FXML
    void onClickCadastrarBolsista(ActionEvent event) {

    }

    @FXML
    void onClickOutrosProjetos(ActionEvent event) {

    }

    @FXML
    void onClickPublicacao(ActionEvent event) {

    }

    @FXML
    void onClickSair(ActionEvent event) throws IOException {

         URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalCoordenadorController tpc = loader.getController();    
            tpc.setStagePrincipal(stagePrincipal);
            
            stagePrincipal.setOnShown(evento -> {
            tpc.ajustarElementosJanela(coordenador);
        });
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal Coordenador");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
            stageAtualizarCoordenador.close();
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
    
    public void setCoordenador(Coordenador coord) {
       this.coordenador = coord;
       txtNome.setText(coordenador.getNome());
       txtUsuario.setText(coordenador.getApelido());
       String cpf = String.valueOf(coordenador.getCpf());
       txtCPF.setText(cpf);
       txtFormacao.setText(coordenador.getFormacao());
       txtSenha.setText(coordenador.getSenha());
       txtEmail.setText(coordenador.getEmail());
       String siape = String.valueOf(coordenador.getSiape());
       txtSIAPE.setText(siape);
    }
    
    void atualizarCoordenador(int id,long cpf, String nome, String apelido,String email,String senha,boolean ativa, int siape, String formacao) throws SQLException{
        
        
        Coordenador coordenador = new Coordenador(id, cpf, nome, apelido, email, senha, ativa, siape, formacao);
        int repetido = new CoordenadorDAO().validarApelido(apelido,id);
        if(repetido>0){
            mostrarAviso("Nome de usuário indisponível","Este nome de usuário já está sendo usado");
           
        }
        else{
        new CoordenadorDAO().atualizarCoordenador(coordenador);
        mostrarConfirmacao("Usuário alterado","O usuário foi alterado com sucesso!");
        }
    }

    
    
   

}
