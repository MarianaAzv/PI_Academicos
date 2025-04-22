package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

public class VerPerfilBolsistaController {
    
     private Stage stageLogin;
    
        @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Text TxtNomeUsuario1;

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
    private ImageView imgFotoBolsista;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgProjeto;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblCPFBols;

    @FXML
    private Label lblCurso;

    @FXML
    private Label lblData;

    @FXML
    private Label lblDataInicioBols;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblEmailBols;

    @FXML
    private Label lblFormacaoBols;

    @FXML
    private Label lblInicioDaBolsa;

    @FXML
    private Label lblInicioDaBolsa1;

    @FXML
    private Label lblMatricula;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNomeBol;

    @FXML
    private Label lblNomeBolsista;

    @FXML
    private Label lblNomeProjeto;

    @FXML
    private Label lblSIAPEBols;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSenhaBols;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblUsuarioBols;

    @FXML
    void onClickArtigo(ActionEvent event) {

    }

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) {

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
    void onClickVerPerfil(ActionEvent event) throws IOException {
        
        
         URL url = new File("src/main/java/view/VerPerfilBolsista.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
        
            VerPerfilBolsistaController vpb = loader.getController();
            
        
            Scene cena = new Scene(root);
            stage.setTitle("Ver Perfil Bolsista");
            stage.setScene(cena);
            stage.setMaximized(true);
            
            stage.show();
            stageLogin.close();

    }

    @FXML
    void onClickVerProjeto(ActionEvent event) {

    }
}
