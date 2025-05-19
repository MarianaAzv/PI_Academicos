
package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Administrador;

public class TelaPrincipalAdministradorController {
    
    private Administrador adm;
    private Stage stageADM;
    
    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnADM;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnNotificacoes;

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
    private Label lblNomeAdm;
    

     @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {  
        abrirTelaAtualizar();
    }
    
      @FXML
    void OnDragEnterAtualizarPerfil(MouseEvent event) {
        
         btnAtualizarPerfil.setStyle("-fx-background-color: D07979" );
    }

    @FXML
    void OnDragExitAtualizarPerfil(MouseEvent event) {
        
         btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5" );

    }

     //**********************************
    @FXML
    void onClickADM(ActionEvent event) throws IOException {
        abrirTelaADMS();
    }
    
    @FXML
    void OnDragEnterADM(MouseEvent event) {
         btnADM.setStyle("-fx-background-color: D07979" );
    }
      @FXML
    void OnDragExitADM(MouseEvent event) {
         btnADM.setStyle("-fx-background-color:  DBA5A5" );
    }

     //**********************************
    @FXML
    void onClickPublicacao(ActionEvent event) throws IOException {
        abrirTelaNoticia();
    }
    @FXML
    void OnDragEnterPublicacao(MouseEvent event) {
         btnPublicacao.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnDragExitPublicacao(MouseEvent event) {
         btnPublicacao.setStyle("-fx-background-color:  DBA5A5" );
    }
    //**********************************

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
       abrirTelaLogin();
    }
    @FXML
    void OnDragEnterSair(MouseEvent event) {
         btnSair.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnDragExitSair(MouseEvent event) {
         btnSair.setStyle("-fx-background-color:  DBA5A5" );
    }
//**********************************

    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        abrirTelaVerPerfil();
    }

    @FXML
    void OnDragEnterVerPerfil(MouseEvent event) {
         btnVerPerfil.setStyle("-fx-background-color:  D07979" );
    }
     @FXML
    void OnDragExitVerPerfil(MouseEvent event) {
         btnVerPerfil.setStyle("-fx-background-color:  DBA5A5" );
    }
//**********************************
    
    @FXML
    void onClickBtnNotificacoes(ActionEvent event) throws IOException {
        abrirTelaNotificacoes();
    }
    
    @FXML
    void OnDragExitNotificacoes(MouseEvent event) {
        btnNotificacoes.setStyle("-fx-background-color:  DBA5A5" );
    }
    @FXML
    void OnDragEnterNotificacoes(MouseEvent event) {
        btnNotificacoes.setStyle("-fx-background-color:  D07979" );
    }
//**********************************    
    
     private void abrirTelaVerPerfil() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/VerPerfilAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageVerPerfil = new Stage();
        
            VerPerfilAdministradorController vpac = loader.getController();    
            vpac.setStage(stageVerPerfil);
            vpac.setAdministrador(adm);
            stageVerPerfil.setMaximized(true);
        
            Scene cena = new Scene(root);
            stageVerPerfil.setTitle("Perfil administrador");
            stageVerPerfil.setScene(cena);
            
            stageVerPerfil.show();
            stageADM.close();
            
    }
    
    private void abrirTelaNoticia() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/TelaNoticia.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastroNoticia = new Stage();
        
            TelaNoticiaController tnc = loader.getController();    
            //tnc.setStage(stageCadastroADM);
        
            Scene cena = new Scene(root);
            stageCadastroNoticia.setTitle("Tela Cadastrar Noticia");
            stageCadastroNoticia.setScene(cena);
            
            stageCadastroNoticia.show();
            stageADM.close();
    }
    
     private void abrirTelaAtualizar() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/AtualizarPerfilAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageAtualizar = new Stage();
        
            AtualizarPerfilAdministradorController apac = loader.getController();  
            apac.setAdministrador(adm);
            apac.setStage(stageAtualizar);
        
            Scene cena = new Scene(root);
            stageAtualizar.setTitle("Tela Atualizar Administrador");
            stageAtualizar.setScene(cena);
            //deixa a tela maximizada
            stageAtualizar.setMaximized(true);
            
            stageAtualizar.show();
            stageADM.close();
    }
     
    private void abrirTelaADMS() throws IOException{
        
         URL url = new File("src/main/java/view/Administradores.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageADMS = new Stage();
        
            AdministradoresController ac = loader.getController();  
            ac.setAdministrador(adm);
            ac.setStage(stageADMS);
        
            Scene cena = new Scene(root);
            stageADMS.setTitle("Tela Administradores");
            stageADMS.setScene(cena);
            //deixa a tela maximizada
            stageADMS.setMaximized(true);
            
            stageADMS.show();
            stageADM.close();
    }
    
    private void abrirTelaNotificacoes() throws IOException{
        
         URL url = new File("src/main/java/view/Notificacoes.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageNotificacoes = new Stage();
        
            NotificacoesController nc = loader.getController();  
            nc.setAdministrador(adm);
            nc.setStage(stageNotificacoes);
        
            Scene cena = new Scene(root);
            stageNotificacoes.setTitle("Tela notificações");
            stageNotificacoes.setScene(cena);
            //deixa a tela maximizada
            stageNotificacoes.setMaximized(true);
            
            stageNotificacoes.show();
            stageADM.close();
    }
    
    private void abrirTelaLogin() throws IOException{
        
         URL url = new File("src/main/java/view/TelaLogin.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageLogin = new Stage();
        
            TelaLoginController tlc = loader.getController();  
            tlc.setStage(stageLogin);
        
            Scene cena = new Scene(root);
            stageLogin.setTitle("Tela Login");
            stageLogin.setScene(cena);
            //deixa a tela maximizada
            stageLogin.setMaximized(true);
            
            stageLogin.show();
            stageADM.close();
    }
    
    public void setStage(Stage stage){
        this.stageADM = stage;
    }
    
    void setADM(Administrador adm) {
        this.adm = adm;
    }
    
    void ajustarElementosJanela(Administrador adm) {
        this.adm=adm;
        
        System.out.println("Aqui chegam os parâmetros do login " + adm.getNome() + " - " + "ATIVA: " + adm.getAtiva());
        //txtNomeUsuario.setText(adm.getNome());

        
        if(adm.getAtiva()==false){
        
        }
       
    }
    
}
