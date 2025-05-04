package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bolsista;
import model.Usuario;
import util.AlertaUtil;

public class TelaPrincipalBolsistaController {

    private Stage stageTelaPrincipalBolsista;
    private Connection conexao;
    private final Usuario dao = new Usuario();
    private Bolsista bolsista;

    @FXML
    private Text TxtNomeUsuario;
     @FXML
    private Text TxtNomeProjetoBarra;

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
    private Button btnVerPerfil;
    
    @FXML
    private ImageView imgLogo;
    
     @FXML
    private ImageView imgPerfil;
    
    @FXML
    private ImageView imgPerfilProjeto;
    
      @FXML
    private ImageView imgProjetoBarra;
    
    @FXML
    private ImageView imgUsuarioBolsista;
    
    @FXML
    private Text textNomeProjeto;
     @FXML
    private Label lblNomeBolsista;

    @FXML
    private Label lblNomeCocoordenador;

    @FXML
    private Label lblNomeCoordenador;

    @FXML
    private Label lblResumo;

    @FXML
    private Text txtCampus;

    @FXML
    private Text txtFim;

    @FXML
    private Text txtInicio;

    @FXML
    private Text txtNomeBolsita;

    @FXML
    private Text txtNomeCocoordenador;

    @FXML
    private Text txtNomeCoordenador;

    @FXML
    private Text txtProrrogacao;


    private Stage stageLogin;

    public void setStage(Stage stage) {
        this.stageLogin = stage;
    }

    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        if (bolsista != null) {
            URL url = new File("src/main/java/view/VerPerfilBolsista.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage stage = new Stage();
            VerPerfilBolsistaController vpb = loader.getController();

            Scene cena = new Scene(root);
            stage.setTitle("Perfil Bolsista");
            stage.setScene(cena);
            stage.setMaximized(true);
            stage.show();
            stageTelaPrincipalBolsista.close();

        } else {
            AlertaUtil.mostrarErro("Erro", "Usuário não encontrado ou inválido.");
        }
    }

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/AtualizarPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stage = new Stage();
        AtualizarPerfilBolsistaController apb = loader.getController();
        apb.setBolsista(bolsista);

        Scene cena = new Scene(root);
        stage.setTitle("Atualizar Perfil Bolsista");
        stage.setMaximized(true);
        stage.setScene(cena);
        stage.show();
        stageTelaPrincipalBolsista.close();
    }
    
    @FXML
    void onClickPublicacao(ActionEvent event) {
       System.out.println("Botão Publicação clicado!");
    }
    @FXML
    void onClickArtigo(ActionEvent event) {
       System.out.println("Botão Artigo clicado!");
    }
    @FXML
    void onClickOutrosProjetos(ActionEvent event) {
       System.out.println("Botão Outros Projetos clicado!");
    }

    @FXML
    void onClickSair(ActionEvent event) {
        stageTelaPrincipalBolsista.close();
    }
    @FXML
    void onClickVerProjeto(ActionEvent event) {
       System.out.println("Botão Ver Projeto clicado!");
    }

    public void setStagePrincipal(Stage telaPrincipalBolsista) {
        this.stageTelaPrincipalBolsista = telaPrincipalBolsista;
    }

    void ajustarElementosJanela(Bolsista bolsista) {
        this.bolsista = bolsista;
        
        System.out.println("Aqui chegam os parâmetros do login " +
                bolsista.getNome() + " - " + bolsista.getMatricula() + " ATIVA: " + bolsista.getAtiva());
        TxtNomeUsuario.setText(bolsista.getNome());
        String matricula = String.valueOf(bolsista.getMatricula());
        textNomeProjeto.setText(matricula);
        TxtNomeProjetoBarra.setText(matricula);
        
    }
}