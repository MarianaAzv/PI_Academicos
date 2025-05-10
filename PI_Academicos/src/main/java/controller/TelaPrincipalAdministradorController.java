
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Administrador;

public class TelaPrincipalAdministradorController {
    
    private Administrador adm;
    private Stage stageADM;
    
    @FXML
    private Text txtNomeUsuario;

    @FXML
    private Text txtNomeUsuario1;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnCadastrarADM;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerperfil;

    @FXML
    private ImageView imgInstituto;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Label lblNomeAdm;

    @FXML
    private Label lblNomeInstituto;

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        
        abrirTelaAtualizar();

    }

    @FXML
    void onClickCadastrarADM(ActionEvent event) throws IOException {
        
        if(adm.getAtiva()==true){
        abrirTelaCadastroADM();
        }

    }

    @FXML
    void onClickPublicacao(ActionEvent event) throws IOException {
        
       abrirTelaNoticia();

    }

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
    // metodo para sair do perfil e ir para a tela de login
    
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
        
        stageADM.close();
    }

    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        
        abrirTelaVerPerfilADM();

    }
    
    ///////////////////////////////////////////METODOS/////////////////////////////////////////////////
    
    public void setStage(Stage stage){
        this.stageADM = stage;
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
     
    private void abrirTelaCadastroADM() throws IOException{
        
         URL url = new File("src/main/java/view/CadastrarAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastroADM = new Stage();
        
            CadastrarAdministradorController cac = loader.getController();    
            cac.setStage(stageCadastroADM);
        
            Scene cena = new Scene(root);
            stageCadastroADM.setTitle("Tela Cadastrar Administrador");
            stageCadastroADM.setScene(cena);
            
            stageCadastroADM.show();
            
    }
    
    private void abrirTelaVerPerfilADM() throws IOException{
        
         URL url = new File("src/main/java/view/VerPerfilAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePerfil = new Stage();
        
            VerPerfilAdministradorController vpac = loader.getController();    
            vpac.setStage(stagePerfil);
            vpac.setAdministrador(adm);
        
            Scene cena = new Scene(root);
            stagePerfil.setTitle("Tela Perfil Administrador");
            stagePerfil.setScene(cena);
            //deixa a tela maximizada
            stagePerfil.setMaximized(true);
            
            stagePerfil.show();
            stageADM.close();
    }
    
    private void abrirTelaNoticia() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/CadastroNoticia.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastroNoticia = new Stage();
        
            TelaNoticiaController tnc = loader.getController();    
            //tnc.setStage(stageCadastroADM);
        
            Scene cena = new Scene(root);
            stageCadastroNoticia.setTitle("Tela Cadastrar Noticia");
            stageCadastroNoticia.setScene(cena);
            
            stageCadastroNoticia.show();
            
    }
    
    void ajustarElementosJanela(Administrador adm) {
        this.adm=adm;
        
        System.out.println("Aqui chegam os parâmetros do login "
                + adm.getNome() + " - " + "ATIVA: " + adm.getAtiva());
        //txtNomeUsuario.setText(adm.getNome());

        
        if(adm.getAtiva()==false){
        
        btnCadastrarADM.setStyle("-fx-text-fill: gray; -fx-background-color: DBA5A5;");
        btnPublicacao.setStyle("-fx-text-fill: gray; -fx-background-color: DBA5A5;");
        }
       
    }
    
}
