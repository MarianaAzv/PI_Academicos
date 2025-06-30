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
import javafx.scene.input.MouseEvent;
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
    private Button btnArtigo;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnAtualizarProjeto;

    @FXML
    private Button btnOutrosProjetos;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerPerfil;

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
    private Label lblNomeCoord;

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

    @FXML
    private Text txtNomeProjeto;

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
        TxtNomeUsuario.setText(coord.getNome());
        
        Image image = null;
        byte[] conteudoFoto = coordenador.getFotoPerfil().getDadosImagem();
        if (conteudoFoto != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                image = new Image(bis); // Converte byte[] para Image AQUI
            } catch (Exception e) {
                System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                // precisa definir uma imagem padrao de erro
            }
        }
        imgPerfil.setImage(image);
        imgFotoCoordenador.setImage(image);

    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
        txtNomeProjeto.setText(projeto.getTitulo());
        
        Image image = null;
        byte[] conteudoFoto = projeto.getFotoPerfil().getDadosImagem();
        if (conteudoFoto != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                image = new Image(bis); // Converte byte[] para Image AQUI
            } catch (Exception e) {
                System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                // precisa definir uma imagem padrao de erro
            }
        }
        imgProjeto.setImage(image);
    }

    //******************* OnClicks ***************************************
    @FXML
    void onClickAdicionarArtigo(ActionEvent event) throws IOException {
        abrirTelaArtigos();
    }

    @FXML
    void OnEnterArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnExitArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color:  DBA5A5");
    }

    //******************************************************************
    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        AbrirTelaAtualizarPerfil();
    }

    @FXML
    void OnEnterAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnExitAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5");
    }

    //******************************************************************
    @FXML
    void onClickAtualizarProjeto(ActionEvent event) throws IOException {
        abrirTelaAtualizarProjeto();
    }

    @FXML
    void OnEnterAtualizarProjeto(MouseEvent event) {
        btnAtualizarProjeto.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnExitAtualizarProjeto(MouseEvent event) {
        btnAtualizarProjeto.setStyle("-fx-background-color:  DBA5A5");
    }

    //******************************************************************
    @FXML
    void onClickOutrosProjetos(ActionEvent event) throws IOException {
        abrirTelaOutrosProjetos();
    }

    @FXML
    void OnEnterOutrosProjetos(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnExitOutrosProjetos(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color:  DBA5A5");
    }

    //******************************************************************
    @FXML
    void onClickAdicionarPublicacao(ActionEvent event) throws IOException {
        abrirTelaPublicacao();
    }

    @FXML
    void OnEnterPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnExitPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color:  DBA5A5");
    }

    //******************************************************************
    @FXML
    void onClickSair(ActionEvent event) throws IOException {
        AbrirTelaPrincipal();
    }

    @FXML
    void OnEnterSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnExitSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color:  DBA5A5");
    }

    //******************************************************************
    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        abrirTelaVerPerfil();
    }

    @FXML
    void OnEnterVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void OnExitVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color:  D07979");
    }

//******************* MÉTODOS ***************************************
    private void abrirTelaVerPerfil() throws IOException {

        URL url = new File("src/main/java/view/VerPerfilCoordenador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageVerPerfil = new Stage();

        VerPerfilCoordenadorController vpf = loader.getController();
        vpf.setCoordenador(coordenador);
        vpf.setProjeto(projeto);
        vpf.setStage(stageVerPerfil);

        Scene cena = new Scene(root);
        stageVerPerfil.setTitle("Perfil Coordenador");
        stageVerPerfil.setScene(cena);
        //deixa a tela maximizada
        stageVerPerfil.setMaximized(true);

        stageVerPerfil.show();
        stageVerPerfil.close();

    }

    private void AbrirTelaAtualizarPerfil() throws IOException {

        URL url = new File("src/main/java/view/AtualizarPerfilCoordenador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);

        Parent root = loader.load();

        Stage stageAtualizar = new Stage();

        AtualizarPerfilCoordenadorController apcc = loader.getController();
        apcc.setCoordenador(coordenador);
        apcc.setProjeto(projeto);
        apcc.setStage(stageAtualizar);

        Scene cena = new Scene(root);
        stageAtualizar.setTitle("Atualizar Perfil Coordenador");
        stageAtualizar.setMaximized(true);
        stageAtualizar.setScene(cena);
        stageAtualizar.show();
        stageVerPerfil.close();

    }

    private void abrirTelaAtualizarProjeto() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/AtualizarProjeto.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);

        Parent root = loader.load();

        Stage stageAtualizarProjeto = new Stage();

        AtualizarProjetoController apc = loader.getController();

        apc.setStage(stageAtualizarProjeto);
        apc.setProjeto(projeto);

        stageAtualizarProjeto.setOnShown(evento -> {
            try {
                apc.ajustarElementosJanela();
            } catch (IOException ex) {
                Logger.getLogger(VerPerfilCoordenadorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Scene cena = new Scene(root);
        stageAtualizarProjeto.setTitle("Atualizar Projeto");
        stageAtualizarProjeto.setMaximized(true);
        stageAtualizarProjeto.setScene(cena);
        stageAtualizarProjeto.show();
        stageVerPerfil.close();

    }

    private void AbrirTelaPrincipal() throws IOException {

        URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageLogin = new Stage();

        TelaPrincipalCoordenadorController tpcc = loader.getController();
        tpcc.setStage(stageLogin);
        tpcc.setCoordenador(coordenador);
        tpcc.setProjeto(projeto);

        Scene cena = new Scene(root);
        stageLogin.setTitle("Tela de principal");
        stageLogin.setScene(cena);
        //deixa a tela maximizada
        stageLogin.setMaximized(true);
        stageLogin.show();

        stageVerPerfil.close();

    }

    public void abrirTelaArtigos() throws IOException {
        URL url = new File("src/main/java/view/CadastrarArtigo.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageArtigo = new Stage();
        CadastrarArtigoController cab = loader.getController();
        cab.setStage(stageArtigo);
        cab.setProjeto(projeto);

        Scene cena = new Scene(root);
        stageArtigo.setTitle("Cadastro Artigo");
        stageArtigo.setMaximized(false);
        stageArtigo.setScene(cena);
        stageArtigo.show();
    }

    public void abrirTelaPublicacao() throws IOException {
        URL url = new File("src/main/java/view/CadastrarPostagem.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePostagem = new Stage();
        CadastrarPostagemController cpb = loader.getController();
        cpb.setStage(stagePostagem);
        cpb.setProjeto(projeto);

        Scene cena = new Scene(root);
        stagePostagem.setTitle("Cadastro Postagem");
        stagePostagem.setMaximized(false);
        stagePostagem.setScene(cena);
        stagePostagem.show();
    }

    public void abrirTelaOutrosProjetos() throws IOException {
        URL url = new File("src/main/java/view/EscolherProjeto.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageProjetos = new Stage();

        EscolherProjetoController tpc = loader.getController();
        tpc.setCoordenador(coordenador);
        tpc.setStage(stageProjetos);

        Scene cena = new Scene(root);
        stageProjetos.setTitle("Outros projetos");
        stageProjetos.setMaximized(false);
        stageProjetos.setScene(cena);
        stageProjetos.show();
        stageProjetos.setOnShown(evento -> {
            try {
                tpc.OnClickProjeto();
            } catch (SQLException ex) {
                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setStage(Stage stage) {
        this.stageVerPerfil = stage;

    }

}
