package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Usuario;
import util.AlertaUtil;

public class TelaPrincipalBolsistaController {
    //carregar
     private Stage stageTelaPrincipalBolsista;
    private Connection conexao;
    private final Usuario dao = new Usuario();
    private ArrayList<String> listaDados;
    private Usuario user;    
    
    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizarPerfil;

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
    private ImageView imgLogo;

    @FXML
    private ImageView imgPerfilProjeto;

    @FXML
    private ImageView imgProjeto;

    @FXML
    private ImageView imgUsuarioBolsista;

    @FXML
    private Label lblNomeBolsista;

    @FXML
    private Label lblNomeCocoordenador;

    @FXML
    private Label lblNomeCoordenador;

    @FXML
    private Label lblResumo;

    @FXML
    private Text textNomeProjeto;

    @FXML
    private Text txtCampus;

    @FXML
    private Text txtFim;

    @FXML
    private Text txtInicio;

    @FXML
    private Text txtNomeBolsitas;

    @FXML
    private Text txtNomeCocoordenador;

    @FXML
    private Text txtNomeCoordenador;

    @FXML
    private Text txtNomeProjeto;

    @FXML
    private Text txtProrrogacao;
    private Stage stageLogin;
    
    public void setStage(Stage stage){ // dps ver como diferenciar o bolsista do coordenador a partir do login
     this.stageLogin = stage;
    }
  
    @FXML
     void onClickVerPerfil(ActionEvent event) throws IOException {
    
    Usuario usuario= null;
    if( usuario != null){
            
            URL url = new File("src/main/java/view/VerPerfilBolsista.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
            
            VerPerfilBolsistaController vpfb = loader.getController(); //lembrar de arrumar o vpf do coordenador para vpfc
            
        
            Scene cena = new Scene(root);
            stage.setTitle("Perfil bolsista");
            stage.setScene(cena);
            //deixa a tela maximizada
            stage.setMaximized(true);
            
            stage.show();
            stageTelaPrincipalBolsista.close();
            
                }else {
 
                       AlertaUtil.mostrarErro("Erro", "Usuário e senha inválidos!");// JAQUE adicionou um pacote Util para colocar os alertas
        
    }
    
    }
        @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        
        URL url = new File("src/main/java/view/AtualizaPerfilBolsista.fxml").toURI().toURL();       
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stage = new Stage();
        
        AtualizarPerfilBolsistaController apb = loader.getController();
        
        Scene cena = new Scene(root);
        stage.setTitle("Atualizar perfil bolsista");
        stage.setMaximized(true);
        stage.setScene(cena);
        stage.show();
         stage.show();
            stageTelaPrincipalBolsista.close();

    }
    @FXML
    void onClickSair (ActionEvent event) {
       stageTelaPrincipalBolsista.close();
            
    }
    @FXML
    void onClickAdicionarPublicacao(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/CadastrarPostagemController.fxml").toURI().toURL();
        
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stage = new Stage(); 
        
        CadastrarPostagemController cpb = loader.getController();
        
         Scene cena = new Scene(root);
        stage.setTitle("Cadastrar Postagem pelo bolsista");
        stage.setMaximized(true);
        stage.setScene(cena);
        stage.show();
         stage.show();
            stageTelaPrincipalBolsista.close();
    }
    
     @FXML
    void onClickAdicionarArtigo(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarArtigoController.fxml").toURI().toURL();
        
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stage = new Stage(); 
        
        CadastrarArtigoController cab = loader.getController();
        
         Scene cena = new Scene(root);
        stage.setTitle("Cadastrar artigo pelo bolsista");
        stage.setMaximized(true);
        stage.setScene(cena);
        stage.show();
         stage.show();
            stageTelaPrincipalBolsista.close();
    }
     @FXML
    void onClickVerProjeto(ActionEvent event) throws MalformedURLException, IOException  {
        URL url = new File("src/main/java/view/TelaPrincipalBosistaController.fxml").toURI().toURL();
        
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stage = new Stage(); 
        
        TelaPrincipalBolsistaController vpb = loader.getController();
        
         Scene cena = new Scene(root);
        stage.setTitle("Visualizar tela princiipal pelo bolsista");
        stage.setMaximized(true);
        stage.setScene(cena);
        stage.show();
         stage.show();
            stageTelaPrincipalBolsista.close();// mesmo que seja a mesma tela talvez se nao colocar elavai ser aberta 2 vezes
    }
     @FXML
    void onClickOutrosProjetos(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/EscolherProjeto.fxml").toURI().toURL();
        
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stage = new Stage(); 
        
        EscolherProjetoController epb = loader.getController();
        
         Scene cena = new Scene(root);
        stage.setTitle("Visualizar varios projetos pelo bolsista");
        stage.setMaximized(true);
        stage.setScene(cena);
        stage.show();
         stage.show();
            stageTelaPrincipalBolsista.close();
    }
}