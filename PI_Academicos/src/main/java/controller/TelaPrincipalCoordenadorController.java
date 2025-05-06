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
//import model.Coordenador;
import model.Usuario;

public class TelaPrincipalCoordenadorController {
    
    private Stage stagePrincipalCoordenador;
    private Connection conexao;
    private final Usuario dao = new Usuario();
 
    private Coordenador coordenador;
    

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
    
    
    public void setStage(Stage stage){
        this.stageLogin = stage;
    }

    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {

            
            URL url = new File("src/main/java/view/VerPerfilCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageVerPerfil = new Stage();
            
            VerPerfilCoordenadorController vpf = loader.getController();
            vpf.setCoordenador(coordenador); 
            vpf.setStage(stageVerPerfil);
        
            Scene cena = new Scene(root);
            stageVerPerfil.setTitle("Perfil Coordenador");
            stageVerPerfil.setScene(cena);
            //deixa a tela maximizada
            stageVerPerfil.setMaximized(true);
            
            stageVerPerfil.show();
            stagePrincipalCoordenador.close();
            
            
}
   
    
    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {

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

    @FXML
    void onClickAdicionarArtigo(ActionEvent event) {

    }

    @FXML
    void onClickAdicionarPublicacao(ActionEvent event) {

    }

    @FXML
    void onClickAtualizarProjeto(ActionEvent event) {

    }

    
    @FXML
    void onClickCriarProjeto(ActionEvent event) throws IOException, MalformedURLException {

         
                 
         URL url = new File("src/main/java/view/CriarProjeto.fxml").toURI().toURL();
        
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        //Comunicar com o combo box com o DAO
        
        
        Stage telaCriarProjeto = new Stage();
        
         CriarProjetoController tpc = loader.getController();
       
        tpc.setStage(telaCriarProjeto);
        
         telaCriarProjeto.setOnShown(evento -> {
        tpc.ajustarElementosJanela();
      });
        
          Scene cena = new Scene(root);
           telaCriarProjeto.setTitle("Tela Criar Projeto");
            telaCriarProjeto.setMaximized(true);
        telaCriarProjeto.setScene(cena);
        telaCriarProjeto.show();
        
        
        
       
    }

    @FXML
    void onClickOutrosProjetos(ActionEvent event) {

    }

    @FXML
    void onClickSair(ActionEvent event) {

        stagePrincipalCoordenador.close();

    }


    @FXML
    void onClickVerProjeto(ActionEvent event) {

    }    
            
     
     public void setStagePrincipal(Stage stagePrincipalCoordenador){
      this.stagePrincipalCoordenador = stagePrincipalCoordenador;
   }

       
    
    void ajustarElementosJanela(Coordenador coordenador) {
        this.coordenador=coordenador;
        
        System.out.println("Aqui chegam os parâmetros do login "
                + coordenador.getNome() + " - " + coordenador.getSiape() + "ATIVA: " + coordenador.getAtiva());
        txtNomeCoordenador.setText(coordenador.getNome());
        String siape = String.valueOf(coordenador.getSiape());
        textNomeProjeto.setText(siape);
       
    }
    
   
        
   }
    

