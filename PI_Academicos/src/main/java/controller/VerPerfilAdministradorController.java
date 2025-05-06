package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Administrador;

public class VerPerfilAdministradorController  {
 
    Administrador adm;
    private Stage stageVerPerfil;
    
    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Text TxtNomeUsuario1;

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
    private ImageView imgFotoAdministrador;

    @FXML
    private ImageView imgInstituto;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblCPFR;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblEmailR;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNomeAdm;

    @FXML
    private Label lblNomeInstituto;

    @FXML
    private Label lblNomeR;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSenhaR;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblUsuarioR;  
    
    
    public void setStage(Stage stage){
        this.stageVerPerfil = stage;
    }
    
    @FXML
    void onClickAtualizarPerfil(ActionEvent event) {

    }

    @FXML
    void onClickCadastrarADM(ActionEvent event) {

    }

    @FXML
    void onClickPublicacao(ActionEvent event) {

    }

    @FXML
    void onClickSair(ActionEvent event) throws IOException {

        URL url = new File("src/main/java/view/TelaPrincipalAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalAdministradorController tpa = loader.getController();    
            tpa.setStage(stagePrincipal);
            
           stagePrincipal.setOnShown(evento -> {
            tpa.ajustarElementosJanela(adm);
       });
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal Administrador");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
            stageVerPerfil.close();
    }

    @FXML
    void onClickVerPerfil(ActionEvent event) {

    }
    
    public void setAdministrador(Administrador adm) {
       this.adm = adm;
       lblNomeR.setText(adm.getNome());
       lblUsuarioR.setText(adm.getApelido());
       String cpf = String.valueOf(adm.getCpf());
       lblCPFR.setText(cpf);
       lblSenhaR.setText(adm.getSenha());
       lblEmailR.setText(adm.getEmail());
       
    }
    
}
