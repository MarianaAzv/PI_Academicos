package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import model.Coordenador;
import model.Projeto;
//import model.Coordenador;
import model.Usuario;

public class TelaPrincipalCoordenadorController {
    
    private Stage stagePrincipalCoordenador;
    private Connection conexao;
    private final Usuario dao = new Usuario();
 
    private Coordenador coordenador;
    Projeto projeto;
    
    public void setStage(Stage stage){
    this.stagePrincipalCoordenador = stage;
    }
    
    public void setStagePrincipal(Stage stagePrincipalCoordenador){
    this.stagePrincipalCoordenador = stagePrincipalCoordenador;
   }
    

    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnAtualizarProjeto;

    @FXML
    private Button btnOutrosProjetos;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerPerfil;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgPerfilProjeto;

    @FXML
    private ImageView imgProjeto;

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
    
    //******************* OnClicks ***************************************
    @FXML
    void onClickAdicionarArtigo(ActionEvent event) {

    }
     @FXML
    void OnEnterArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color: D07979" );
    }
     @FXML
    void OnExitArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        AbrirTelaAtualizarPerfil();
    }
    @FXML
    void OnEnterAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color: D07979" );
    }
     @FXML
    void OnExitAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
     @FXML
    void onClickAtualizarProjeto(ActionEvent event) throws IOException {
        abrirTelaAtualizarProjeto();
    }
    @FXML
    void OnEnterAtualizarProjeto(MouseEvent event) {
        btnAtualizarProjeto.setStyle("-fx-background-color: D07979" );
    }
     @FXML
    void OnExitAtualizarProjeto(MouseEvent event) {
        btnAtualizarProjeto.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickOutrosProjetos(ActionEvent event) {
        
    }
    @FXML
    void OnEnterOutrosProjetos(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnExitOutrosProjetos(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickAdicionarPublicacao(ActionEvent event) {

    }
    @FXML
    void OnEnterPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnExitPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickSair(ActionEvent event) throws IOException {
        AbrirTelaLogin();
    }
    @FXML
    void OnEnterSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color: D07979" );
    }
     @FXML
    void OnExitSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        abrirTelaVerPerfil();
    }
    @FXML
    void OnEnterVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnExitVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color:  DBA5A5" );
    }
    
//******************* MÉTODOS ***************************************
    
    
    private void abrirTelaVerPerfil() throws IOException{
 
            URL url = new File("src/main/java/view/VerPerfilCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageVerPerfil = new Stage();
            
            VerPerfilCoordenadorController vpf = loader.getController();
            vpf.setCoordenador(coordenador);
            vpf.setProjeto(projeto);
            vpf.setStage(stageVerPerfil);
        
            Scene cena = new Scene(root);
            stageVerPerfil.setTitle("Perfil Coordenador");
            stageVerPerfil.setScene(cena);
            //deixa a tela maximizada
            stageVerPerfil.setMaximized(true);
            
            stageVerPerfil.show();
            stagePrincipalCoordenador.close();
            
    }


    private void AbrirTelaAtualizarPerfil() throws IOException {

        URL url = new File("src/main/java/view/AtualizarPerfilCoordenador.fxml").toURI().toURL();       
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stageAtualizar = new Stage();
        
        AtualizarPerfilCoordenadorController apcc = loader.getController();
        apcc.setCoordenador(coordenador); 
        apcc.setStage(stageAtualizar);
        
        Scene cena = new Scene(root);
        stageAtualizar.setTitle("Atualizar Perfil Coordenador");
        stageAtualizar.setMaximized(true);
        stageAtualizar.setScene(cena);
        stageAtualizar.show();
        stagePrincipalCoordenador.close();
               
    } 

    private void abrirTelaAtualizarProjeto() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/AtualizarProjeto.fxml").toURI().toURL();       
      FXMLLoader loader = new FXMLLoader(url);
       
      Parent root = loader.load();
        
     Stage stageAtualizarProjeto = new Stage();
        
       AtualizarProjetoController apc = loader.getController();
        
        apc.setStage(stageAtualizarProjeto);
        apc.setProjeto(projeto);
        
          stageAtualizarProjeto.setOnShown(evento -> {
        apc.ajustarElementosJanela();
      });
        
      Scene cena = new Scene(root);
       stageAtualizarProjeto.setTitle("Atualizar Projeto");
       stageAtualizarProjeto.setMaximized(true);
        stageAtualizarProjeto.setScene(cena);
       stageAtualizarProjeto.show();
        stagePrincipalCoordenador.close();

    }

    private void AbrirTelaLogin() throws IOException {

         URL url = new File("src/main/java/view/TelaLogin.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageLogin = new Stage();

        TelaLoginController tpc = loader.getController();
        tpc.setStage(stageLogin);     

        Scene cena = new Scene(root);
        stageLogin.setTitle("Tela de Login");
        stageLogin.setScene(cena);
        //deixa a tela maximizada
        stageLogin.setMaximized(true);
        stageLogin.show();
        
        stagePrincipalCoordenador.close();
        

    }

    void ajustarElementosJanela(Coordenador coordenador, Projeto projeto) {
        this.coordenador=coordenador;
        this.projeto=projeto;
        
        System.out.println("Aqui chegam os parâmetros do login "
                + coordenador.getNome() + " - " + coordenador.getSiape() + "ATIVA: " + coordenador.getAtiva());
        txtNomeCoordenador.setText(coordenador.getNome());
        String siape = String.valueOf(coordenador.getSiape());
        textNomeProjeto.setText(projeto.getTitulo());
        
        if(coordenador.getAtiva()==false){
        
        
        }
       
    }
   
    public void setCoordenador(Coordenador coord) {
       this.coordenador = coord;
    }
    public void setProjeto(Projeto projeto) {
       this.projeto = projeto;
    }
    
    
    
   
        
   }
    

