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
//import model.Coordenador;
import model.Usuario;

public class TelaPrincipalCoordenadorController {
    
    private Stage stagePrincipalCoordenador;
    private Connection conexao;
    private final Usuario dao = new Usuario();
    private ArrayList<String> dados;
    private Usuario user;

    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnAtualizarProjeto;

    @FXML
    private Button btnCriarPerfil;

    @FXML
    private Button btnOutrosProjetos;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerProjeto;

   @FXML
    private Button btnVerPerfil;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgPerfilProjeto;

    @FXML
    private ImageView imgProjeto;

    @FXML
    private ImageView imgUsuarioCoordenador;

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
    
    //Text txtNomeCoordenador = " " + dados.get(0);;
    
public void setStage(Stage stage){
        this.stageLogin = stage;
    }

       @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {

        //carregando a tela principal do coordenador
        Usuario usuario = null; //talvez errado
         if( usuario != null){
            
            URL url = new File("src/main/java/view/VerPerfilCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
            
            VerPerfilCoordenadorController vpf = loader.getController();
            
        
            Scene cena = new Scene(root);
            stage.setTitle("Perfil Coordenador");
            stage.setScene(cena);
            //deixa a tela maximizada
            stage.setMaximized(true);
            
            stage.show();
            stagePrincipalCoordenador.close();
            
            }else {
            
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Nome ou senha incorretos");
        alerta.setHeaderText("Verifique as informações inseridas");
        alerta.setContentText("Digite novamente");
        alerta.showAndWait();
        
    }
}
    
    @FXML
    void onClickAtualizarPerfil(MouseEvent event) throws MalformedURLException, IOException {

        URL url = new File("src/main/java/view/AtualizarPerfilCoordenador.fxml").toURI().toURL();       
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stageAtualizar = new Stage();
        
        AtualizarPerfilCoordenadorController apcc = loader.getController();
        
        
        
        Scene cena = new Scene(root);
        stageAtualizar.setTitle("Atualizar Perfil Coordenador");
        stageAtualizar.setMaximized(true);
        stageAtualizar.setScene(cena);
        stageAtualizar.show();
    }     
            
     
     public void setStagePrincipal(Stage telaPrincipalCoordenador){
      this.stagePrincipalCoordenador = telaPrincipalCoordenador;
   }

        
    
   // void onClickAtualizarPerfil(){
        
   // }
    
  //  void onClickCriarProjeto(){
        
   // }
  //  void onClickSair(){
        
  //  }
   // void onClickAdicionarPublicação(){
        
  //  }
   // void onClickAdicionarArtigo(){
        
  //  }
  //  void onClickAtualizarProjeto(){
        
 //   }
   // void onClickOutrosProjetos(){
     
     private void abrirTelaAtualizarPerfil(ArrayList<String> listaDados) throws MalformedURLException, IOException{
         URL url = new File("src/main/java/view/AtualizarPerfilCoordenador.fxml").toURI().toURL();       
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stageAtualizar = new Stage();
        
        AtualizarPerfilCoordenadorController apcc = loader.getController();
        
        Scene cena = new Scene(root);
        stageAtualizar.setTitle("Atualizar Perfil Coordenador");
        stageAtualizar.setMaximized(true);
        stageAtualizar.setScene(cena);
        stageAtualizar.show();
     }
    
    void ajustarElementosJanela(ArrayList<String> dados) {
        System.out.println("Aqui chegam os parâmetros do login "
                + dados.get(0) + " - " + dados.get(1));
        txtNomeCoordenador.setText(dados.get(0));
        textNomeProjeto.setText(dados.get(0));
       
    }
        
   }
    

