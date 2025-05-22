package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import model.Coordenador;
import model.Projeto;

public class VerPerfilCoordenadorController {
    
    private Stage stageVerPerfil;
    Coordenador coordenador;
    Projeto projeto;
        
    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Text TxtNomeUsuario1;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnCadastrarBolsista;

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
    private ImageView imgFotoCoordenador;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgProjeto;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblCPFCoord;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblEmailCoord;

    @FXML
    private Label lblFormacao;

    @FXML
    private Label lblFormacaoCoord;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNomeBolsista;

    @FXML
    private Label lblNomeCoord;

    @FXML
    private Label lblNomeProjeto;

    @FXML
    private Label lblSIAPE;

    @FXML
    private Label lblSIAPECoord;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSenhaCoord;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblUsuarioCoord;
    
    public void setCoordenador(Coordenador coord) {
       this.coordenador = coord;
       lblNomeCoord.setText(coordenador.getNome());
       lblUsuarioCoord.setText(coordenador.getApelido());
       String cpf = String.valueOf(coordenador.getCpf());
       lblCPFCoord.setText(cpf);
       lblFormacaoCoord.setText(coordenador.getFormacao());
       lblSenhaCoord.setText(coordenador.getSenha());
       lblEmailCoord.setText(coordenador.getEmail());
       String siape = String.valueOf(coordenador.getSiape());
       lblSIAPECoord.setText(siape);
    }
    
    public void setProjeto(Projeto projeto){
        this.projeto=projeto;
    }

    @FXML
    void onClickArtigo(ActionEvent event) {

    }

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws MalformedURLException, IOException {
        
         URL url = new File("src/main/java/view/AtualizarPerfilCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
        
            AtualizarPerfilCoordenadorController apc = loader.getController();
            apc.setCoordenador(coordenador); 
            apc.setStage(stage);
        
            Scene cena = new Scene(root);
            stage.setTitle("Atualizar Perfil Coordenador");
            stage.setScene(cena);
            //deixa a tela maximizada
            stage.setMaximized(true);
            
            stage.show();
            stageVerPerfil.close();

    }

    @FXML
    void onClickOutrosProjetos(ActionEvent event) {

    }

    @FXML
    void onClickPublicacao(ActionEvent event) {

    }

    @FXML
    void onClickSair(ActionEvent event) throws IOException {

         URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipalCoordenador = new Stage();
            
            TelaPrincipalCoordenadorController tcc = loader.getController();
           tcc.ajustarElementosJanela(coordenador, projeto); 
            tcc.setStage(stagePrincipalCoordenador);
        
            Scene cena = new Scene(root);
            stagePrincipalCoordenador.setTitle("Tela Principal Coordenador");
            stagePrincipalCoordenador.setScene(cena);
            //deixa a tela maximizada
           stagePrincipalCoordenador.setMaximized(true);
            
            stagePrincipalCoordenador.show();
            stageVerPerfil.close();
    }

    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        
         URL url = new File("src/main/java/view/VerPerfilCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
        
            VerPerfilCoordenadorController vpc = loader.getController();
            
        
            Scene cena = new Scene(root);
            stage.setTitle("Ver Perfil Coordenador");
            stage.setScene(cena);
            stage.setMaximized(true);
            
            stage.show();
            stageVerPerfil.close();

    }

    @FXML
    void onClickVerProjeto(ActionEvent event) throws IOException {
        
        URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
        
            TelaPrincipalCoordenadorController tpc = loader.getController();
            tpc.setStagePrincipal(stage);
        
            Scene cena = new Scene(root);
            stage.setTitle("Tela principal Coordenador");
            stage.setScene(cena);
            stage.setMaximized(true);
            
            stage.show();
            stageVerPerfil.close();

    }
    
     @FXML
    void onClickCadastrarBolsista(ActionEvent event) throws IOException {
        
         URL url = new File("src/main/java/view/CadastroBolsistaCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
        
            CadastroBolsistaCoordenadorController cbc = loader.getController();
            
        
            Scene cena = new Scene(root);
            stage.setTitle("Cadastrar Bolsista");
            stage.setScene(cena);
            stage.setMaximized(true);
            
            stage.show();
            stageVerPerfil.close();

    }
    
     @FXML
    void onClickAtualizarProjeto(ActionEvent event) throws IOException {
        
         URL url = new File("src/main/java/view/AtualizarProjeto.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
        
            AtualizarProjetoController apc = loader.getController();
            
        
            Scene cena = new Scene(root);
            stage.setTitle("Atualizar Projeto");
            stage.setScene(cena);
            
            stage.show();
            stageVerPerfil.close();

    }
    
public void setStage(Stage stage){
        this.stageVerPerfil= stage;
            
}

}
