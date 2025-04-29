
package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

public class AtualizarPerfilAdministradorController {
    
    private Stage stagePerfilADM;
    
        @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Text TxtNomeUsuario1;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private GridPane btnAtualizarProjeto;

    @FXML
    private Button btnCadastrarADM;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerperfil;

    @FXML
    private ImageView imgFotoAdministrador;

    @FXML
    private ImageView imgInstituto;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNomeAdm;

    @FXML
    private Label lblNomeInstituto;

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
    void onClickAtualizar(ActionEvent event) {

    }

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) {

    }

    @FXML
    void onClickCadastrarADM(ActionEvent event) throws IOException {
        
        abrirTelaCadastroADM();

    }

    @FXML
    void onClickPublicacao(ActionEvent event) {
        
        //abrirTelaNoticia();

    }

    @FXML
    void onClickSair(ActionEvent event) {

    }

    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        
        abrirTelaVerPerfilADM();

    }
    
    /////////////////METODOS///////////////////////////////////////////////////////////////////////
    
    private void abrirTelaCadastroADM() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/CadastrarAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastroADM = new Stage();
        
            CadastrarAdministradorController cac = loader.getController();    
            //cac.setStage(stageCadastroADM);
        
            Scene cena = new Scene(root);
            stageCadastroADM.setTitle("Tela Cadastrar Administrador");
            stageCadastroADM.setScene(cena);
            
            stageCadastroADM.show();
            
    }
    
     private void abrirTelaVerPerfilADM() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/VerPerfilAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePerfil = new Stage();
        
            VerPerfilAdministradorController vpac = loader.getController();    
            //apac.setStage(stageAtualizar);
        
            Scene cena = new Scene(root);
            stagePerfil.setTitle("Tela Perfil Administrador");
            stagePerfil.setScene(cena);
            //deixa a tela maximizada
            stagePerfil.setMaximized(true);
            
            stagePerfil.show();
            stagePerfilADM.close();
    }
     
    private void abrirTelaAtualizar() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/AtualizarPerfilAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageAtualizar = new Stage();
        
            AtualizarPerfilAdministradorController apac = loader.getController();    
            //apac.setStage(stageAtualizar);
        
            Scene cena = new Scene(root);
            stageAtualizar.setTitle("Tela Atualizar Administrador");
            stageAtualizar.setScene(cena);
            //deixa a tela maximizada
            stageAtualizar.setMaximized(true);
            
            stageAtualizar.show();
            stagePerfilADM.close();
    }
     
    //    private void abrirTelaNoticia() throws MalformedURLException, IOException{
//        
//         URL url = new File("src/main/java/view/CadastroNoticia.fxml").toURI().toURL();
//            FXMLLoader loader = new FXMLLoader(url);
//            Parent root = loader.load();
//        
//            Stage stageCadastroNoticia = new Stage();
//        
//            CadastroNoticiaController cnc = loader.getController();    
//            //cac.setStage(stageCadastroADM);
//        
//            Scene cena = new Scene(root);
//            stageCadastroNoticia.setTitle("Tela Cadastrar Noticia");
//            stageCadastroNoticia.setScene(cena);
//            
//            stageCadastroNoticia.show();
//            
//    }
    
    
}
