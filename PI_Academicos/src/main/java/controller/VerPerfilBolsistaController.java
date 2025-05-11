package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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
import model.Bolsista;

public class VerPerfilBolsistaController {
    
    private Stage stage;
    Bolsista bolsista;
    
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
    private Button btnVerPerfil;

    @FXML
    private Button btnVerProjeto;

    @FXML
    private ImageView imgFotoBolsista;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgProjeto;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblCPFBolsista;

    @FXML
    private Label lblCurso;

    @FXML
    private Label lblCursoBolsista;

    @FXML
    private Label lblData;

    @FXML
    private Label lblDataFimBols;
    
    @FXML
    private Label lblDataInicioBols;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblEmailBolsista;

    @FXML
    private Label lblFimDaBolsa;

    @FXML
    private Label lblInicioDaBolsa;

    @FXML
    private Label lblMatricula;

    @FXML
    private Label lblMatriculaBols;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNomeBol;

    @FXML
    private Label lblNomeBolsista;

    @FXML
    private Label lblNomeProjeto;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSenhaBolsista;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblUsuarioBolsista;
     public void setBolsista(Bolsista bol) {
    this.bolsista = bol;
    lblNomeBol.setText(bolsista.getNome());
    lblUsuarioBolsista.setText(bolsista.getApelido());
    lblCPFBolsista.setText(String.valueOf(bolsista.getCpf()));
    lblCursoBolsista.setText(bolsista.getCurso());
    lblSenhaBolsista.setText(bolsista.getSenha());
    lblEmailBolsista.setText(bolsista.getEmail());
    lblMatriculaBols.setText(String.valueOf(bolsista.getMatricula()));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Verificando se as datas não estão nulas antes de formatar
    if (bolsista.getDataInicio() != null) {
        lblDataInicioBols.setText(bolsista.getDataInicio().format(formatter));
    } else {
        lblDataInicioBols.setText("Data não disponível");
    }

    if (bolsista.getDataFim() != null) {
        lblDataFimBols.setText(bolsista.getDataFim().format(formatter));
    } else {
        lblDataFimBols.setText("Data não disponível");
    }

       //lblDataInicioBols.setText(bolsista.getDataInicio().toString());
      // lblDataFimBols.setText(bolsista.getDataFim().toString());
    }

    @FXML
    void onClickArtigo(ActionEvent event) {

    }

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws MalformedURLException, IOException {
        
        URL url = new File("src/main/java/view/AtualizarPerfilBolsista.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
        
            AtualizarPerfilBolsistaController apb = loader.getController();
            
        
            Scene cena = new Scene(root);
            stage.setTitle("Atualizar Perfil Bolsista");
            stage.setScene(cena);
            //deixa a tela maximizada
            stage.setMaximized(true);
            
            stage.show();
            stage.close();

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
    void onClickVerPerfil(ActionEvent event) throws MalformedURLException, IOException {
        
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
            stage.close();

    }

    @FXML
    void onClickVerProjeto(ActionEvent event) throws MalformedURLException, IOException {
        
          URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
        
            TelaPrincipalBolsistaController tpb = loader.getController();
            
        
            Scene cena = new Scene(root);
            stage.setTitle("Tela principal Bolsista");
            stage.setScene(cena);
            stage.setMaximized(true);
            
            stage.show();
            stage.close();

    }

   

}
