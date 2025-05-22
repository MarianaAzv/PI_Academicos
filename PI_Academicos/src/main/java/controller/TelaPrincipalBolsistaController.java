package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
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
import model.Projeto;
import model.Usuario;
import util.AlertaUtil;

public class TelaPrincipalBolsistaController {

    private Stage stageTelaPrincipalBolsista;
    private Connection conexao;
    private final Usuario dao = new Usuario();
    private Bolsista bolsista;
        private Projeto projeto;//hj


    @FXML
    private Text TxtNomeUsuario, TxtNomeProjetoBarra;
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
    private ImageView imgLogo, imgPerfil, imgPerfilProjeto, imgProjetoBarra, imgUsuarioBolsista;
    @FXML
    private Text textNomeProjeto; 
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
    @FXML
    private Label lblNomeBolsista, lblNomeCocoordenador, lblNomeCoordenador, lblResumo;
    
    @FXML
    private Text txtResumo;

    public void setStage(Stage stage) {
        this.stageTelaPrincipalBolsista = stage;
    }

   @FXML
void onClickVerPerfil(ActionEvent event) throws IOException {
    URL url = new File("src/main/java/view/VerPerfilBolsista.fxml").toURI().toURL();
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();

    Stage stageVerPerfil = new Stage();

    VerPerfilBolsistaController vpb = loader.getController();
    vpb.setBolsista(bolsista);
    vpb.setStage(stageVerPerfil); // Correção: Garantir que a nova tela tenha controle sobre seu próprio Stage

    Scene cena = new Scene(root);
    stageVerPerfil.setTitle("Perfil Bolsista");
    stageVerPerfil.setScene(cena);
    stageVerPerfil.setMaximized(true);

    stageVerPerfil.show();
    stageTelaPrincipalBolsista.close(); // Correção: Fechar a tela principal ao abrir a nova
}

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/AtualizarPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageAtualizar = new Stage();
        AtualizarPerfilBolsistaController apb = loader.getController();
        apb.setBolsista(bolsista);

        Scene cena = new Scene(root);
        stageAtualizar.setTitle("Atualizar Perfil Bolsista");
        stageAtualizar.setMaximized(true);
        stageAtualizar.setScene(cena);
        stageAtualizar.show();
    }

    @FXML
    void onClickPublicacao(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarPostagem.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePostagem = new Stage();
        CadastrarPostagemController cpb = loader.getController();
        cpb.setStage(stagePostagem);

        Scene cena = new Scene(root);
        stagePostagem.setTitle("Bolsista Cadastro Postagem");
        stagePostagem.setMaximized(true);
        stagePostagem.setScene(cena);
        stagePostagem.show();
    }

    @FXML
    void onClickArtigo(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarArtigo.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageArtigo = new Stage();
        CadastrarArtigoController cab = loader.getController();
        cab.setStage(stageArtigo);

        Scene cena = new Scene(root);
        stageArtigo.setTitle("Bolsista Cadastro Artigo");
        stageArtigo.setMaximized(true);
        stageArtigo.setScene(cena);
        stageArtigo.show();
    }

    @FXML
    void onClickOutrosProjetos(ActionEvent event) {
        System.out.println("Outros Projetos clicado!");
    }

    @FXML
    void onClickSair(ActionEvent event) {
        stageTelaPrincipalBolsista.close();
    }

    @FXML
    void onClickVerProjeto(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stage = new Stage();
        TelaPrincipalBolsistaController tpb = loader.getController();
        tpb.setStagePrincipal(stage);

        Scene cena = new Scene(root);
        stage.setTitle("Tela Principal Bolsista");
        stage.setScene(cena);
        stage.setMaximized(true);

        stage.show();
        stageTelaPrincipalBolsista.close();
    }

    public void setStagePrincipal(Stage telaPrincipalBolsista) {
        this.stageTelaPrincipalBolsista = telaPrincipalBolsista;
    }

    void ajustarElementosJanela(Bolsista bolsista, Projeto projeto) {
        this.bolsista = bolsista;
        this.projeto = projeto;
        System.out.println("Aqui chegam os parâmetros do login " + bolsista.getNome() + " - " + bolsista.getMatricula() + " ATIVA: " + bolsista.getAtiva());
        TxtNomeUsuario.setText(bolsista.getNome());
       // String matricula = String.valueOf(bolsista.getMatricula());
        textNomeProjeto.setText(projeto.getTitulo());
        TxtNomeProjetoBarra.setText(projeto.getTitulo());
      //  txtNomeCoordenador.setText();
      //txtNomeBolsita.setText();
      //txtNomeCocoordenador.setText();
      //txtInicio
      //txtFim
      //txtProrrogacao
      //txtResumo
      //txtCampus
      
    }

    
}