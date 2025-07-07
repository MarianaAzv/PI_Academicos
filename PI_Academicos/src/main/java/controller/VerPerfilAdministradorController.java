package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import model.Administrador;

public class VerPerfilAdministradorController {

    Administrador adm;
    private Stage stageVerPerfil;

    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnADM;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnNotificacoes;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerperfil;

    @FXML
    private ImageView imgFotoAdministrador;

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
    private Label lblNomeR;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSenhaR;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblUsuarioR;

    public void setStage(Stage stage) {
        this.stageVerPerfil = stage;
    }

    //**********************************
    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        abrirTelaAtualizar();
    }

    @FXML
    void OnDragEnterAtualizarPerfil(MouseEvent event) {

        btnAtualizarPerfil.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnDragExitAtualizarPerfil(MouseEvent event) {

        btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5");

    }

    //**********************************
    @FXML
    void onClickADM(ActionEvent event) throws IOException {
        abrirTelaADMS();
    }

    @FXML
    void OnDragEnterADM(MouseEvent event) {
        btnADM.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnDragExitADM(MouseEvent event) {
        btnADM.setStyle("-fx-background-color:  DBA5A5");
    }

    //**********************************
    @FXML
    void onClickPublicacao(ActionEvent event) throws IOException {
        abrirTelaNoticia();
    }

    @FXML
    void OnDragEnterPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnDragExitPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color:  DBA5A5");
    }
    //**********************************

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
        abrirTelaPrincipal();
    }

    @FXML
    void OnDragEnterSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnDragExitSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color:  DBA5A5");
    }
//**********************************

    @FXML
    void onClickVerPerfil(ActionEvent event) {

    }

//**********************************
    @FXML
    void onClickBtnNotificacoes(ActionEvent event) throws IOException {
        abrirTelaNotificacoes();
    }

    @FXML
    void OnDragExitNotificacoes(MouseEvent event) {
        btnNotificacoes.setStyle("-fx-background-color:  DBA5A5");
    }

    @FXML
    void OnDragEnterNotificacoes(MouseEvent event) {
        btnNotificacoes.setStyle("-fx-background-color:  D07979");
    }
//**********************************    

    public void setAdministrador(Administrador adm) {
        this.adm = adm;
        lblNomeR.setText(adm.getNome());
        lblNomeAdm.setText(adm.getNome());
        lblUsuarioR.setText(adm.getApelido());
        String cpf = String.valueOf(adm.getCpf());
        lblCPFR.setText(cpf);
        lblSenhaR.setText(adm.getSenha());
        lblEmailR.setText(adm.getEmail());
        
        Image image = null;
        byte[] conteudoFoto = adm.getFotoPerfil().getDadosImagem();
        if (conteudoFoto != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                image = new Image(bis); // Converte byte[] para Image AQUI
            } catch (Exception e) {
                System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                // precisa definir uma imagem padrao de erro
            }
        }
        imgFotoAdministrador.setImage(image);
        imgPerfil.setImage(image);

    }

    private void abrirTelaNoticia() throws MalformedURLException, IOException {

        URL url = new File("src/main/java/view/TelaNoticia.fxml").toURI().toURL();
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

    private void abrirTelaAtualizar() throws MalformedURLException, IOException {

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
        stageVerPerfil.close();
    }
     
    private void abrirTelaADMS() throws IOException{
        URL url = new File("src/main/java/view/Administradores.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageADMS = new Stage();
        
            AdministradoresController ac = loader.getController();  
            ac.setAdministrador(adm);
            ac.setStage(stageADMS);
            
            stageADMS.setOnShown(evento -> {
            try {
                ac.ajustarElementosJanela();
            } catch (SQLException ex) {
                Logger.getLogger(TelaPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
            Scene cena = new Scene(root);
            stageADMS.setTitle("Tela Administradores");
            stageADMS.setScene(cena);
            //deixa a tela maximizada
            stageADMS.setMaximized(true);
            
            stageADMS.show();
            stageVerPerfil.close();
    }

    private void abrirTelaNotificacoes() throws IOException {

        URL url = new File("src/main/java/view/Notificacoes.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageNotificacoes = new Stage();

        NotificacoesController nc = loader.getController();
        nc.setAdministrador(adm);
        nc.setStage(stageNotificacoes);
        
        stageNotificacoes.setOnShown(evento -> {
             try {
                 nc.ajustarElementosJanela();
             } catch (SQLException ex) {
                 Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
             }
        });

        Scene cena = new Scene(root);
        stageNotificacoes.setTitle("Tela notificações");
        stageNotificacoes.setScene(cena);
        //deixa a tela maximizada
        stageNotificacoes.setMaximized(true);

        stageNotificacoes.show();
        stageVerPerfil.close();
    }

    private void abrirTelaPrincipal() throws IOException {
        URL url = new File("src/main/java/view/TelaPrincipalAdministradorTeste.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePrincipal = new Stage();

        TelaPrincipalAdministradorController tpa = loader.getController();
        tpa.setStage(stagePrincipal);
        tpa.setAdministrador(adm);
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

}
