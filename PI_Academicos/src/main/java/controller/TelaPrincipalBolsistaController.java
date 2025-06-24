package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bolsista;
import model.Projeto;
import model.Usuario;

public class TelaPrincipalBolsistaController implements INotificacaoAlert {

    private Stage stageTelaPrincipalBolsista;
    private Connection conexao;
    private final Usuario dao = new Usuario();
    private Bolsista bolsista;
    private Projeto projeto;//hj
    int resp = 1;

    public void setStage(Stage stage) {
        this.stageTelaPrincipalBolsista = stage;
    }

    public void setStagePrincipal(Stage telaPrincipalBolsista) {
        this.stageTelaPrincipalBolsista = telaPrincipalBolsista;
    }

    void setBolsista(Bolsista bol) {
        this.bolsista = bol;
    }

    void setProjeto(Projeto projeto) {
        this.projeto = projeto;
        txtResumo.setText(projeto.getResumo());
        txtCampus.setText(projeto.getCampus().getNomeCampus());
        textNomeProjeto.setText(projeto.getTitulo());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String DataInicio = projeto.getDataInicio().format(formatter);
        txtInicio.setText(DataInicio);

        String DataFim = projeto.getDataFim().format(formatter);
        txtFim.setText(DataFim);
        String prorroga = String.valueOf(projeto.getProrroacao());
        txtProrrogacao.setText(prorroga);
        txtNomeCoordenador.setText(projeto.getCocoordenadores());
        //txtNomeBolsitas.setText(projeto.get);
    }

    @FXML
    private Text TxtNomeProjetoBarra;

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
    private ImageView imgLogo;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgPerfilProjeto;

    @FXML
    private ImageView imgProjetoBarra;

    @FXML
    private Label lblNomeBolsista;

    @FXML
    private Label lblNomeCocoordenador;

    @FXML
    private Label lblNomeCoordenador;

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
    private Text txtNomeBolsista;
    @FXML
    private Text txtProrrogacao;

    @FXML
    private Label txtResumo;

    //******************* OnClicks ***************************************
    @FXML
    void onClickArtigo(ActionEvent event) throws IOException {
        abrirArtigo();
    }

    @FXML
    void onEnterArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color:  DBA5A5");
    }
    //******************************************************************

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        abrirAtualizarPerfil();
    }

    @FXML
    void onEnterAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5");
    }
    //******************************************************************

    @FXML
    void onClickOutrosProjetos(ActionEvent event) throws IOException {
        outrosProjetos();
    }

    @FXML
    void onEnterOutrosProjeto(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitOutrosProjeto(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color:  DBA5A5");
    }
    //******************************************************************

    @FXML
    void onClickPublicacao(ActionEvent event) throws IOException {
        abrirPublicacao();
    }

    @FXML
    void onEnterPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color:  DBA5A5");
    }
    //******************************************************************

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
        alerta("Você tem certeza mana", 2, "Verificação de saida");
    }

    @FXML
    void onEnterSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color:  DBA5A5");
    }

    //******************************************************************
    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        abrirVerPerfil();
    }

    @FXML
    void onEnterVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color:  DBA5A5");
    }

    //******************* MÉTODOS ***************************************
    public void abrirVerPerfil() throws IOException {
        URL url = new File("src/main/java/view/VerPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageVerPerfil = new Stage();

        VerPerfilBolsistaController vpb = loader.getController();
        vpb.setBolsista(bolsista);
        vpb.setProjeto(projeto);
        vpb.setStage(stageVerPerfil);

        stageVerPerfil.setOnShown(evento -> {//hj
            vpb.ajustarElementosJanela(bolsista, projeto);
        });

        Scene cena = new Scene(root);
        stageVerPerfil.setTitle("Perfil Bolsista");
        stageVerPerfil.setScene(cena);
        stageVerPerfil.setMaximized(true);

        stageVerPerfil.show();
        stageTelaPrincipalBolsista.close(); // Correção: Fechar a tela principal ao abrir a nova
    }

    public void abrirAtualizarPerfil() throws IOException {
        URL url = new File("src/main/java/view/AtualizarPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageAtualizar = new Stage();
        AtualizarPerfilBolsistaController apb = loader.getController();
        apb.setBolsista(bolsista);
        apb.setProjeto(projeto);

        stageAtualizar.setOnShown(evento -> {//hj
            //apb.ajustarElementosJanela(bolsista,projeto);
        });

        Scene cena = new Scene(root);
        stageAtualizar.setTitle("Atualizar Perfil Bolsista");
        stageAtualizar.setMaximized(true);
        stageAtualizar.setScene(cena);
        stageAtualizar.show();
        stageTelaPrincipalBolsista.close();
    }

    public void abrirPublicacao() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarPostagem.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePostagem = new Stage();
        CadastrarPostagemController cpb = loader.getController();
        // cpb.setBolsista(bolsista);
        cpb.setStage(stagePostagem);

        Scene cena = new Scene(root);
        stagePostagem.setTitle("Bolsista Cadastro Postagem");
        stagePostagem.setMaximized(false);
        stagePostagem.setScene(cena);
        stagePostagem.show();
    }

    public void abrirArtigo() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarArtigo.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageArtigo = new Stage();
        CadastrarArtigoController cab = loader.getController();
        //  cab.setBolsista(bolsista);
        cab.setStage(stageArtigo);

        Scene cena = new Scene(root);
        stageArtigo.setTitle("Bolsista Cadastro Artigo");
        stageArtigo.setMaximized(false);
        stageArtigo.setScene(cena);
        stageArtigo.show();
    }

    @FXML
    public void outrosProjetos() throws MalformedURLException, IOException {
        System.out.println("Outros Projetos clicado!");
        URL url = new File("src/main/java/view/EscolherProjeto.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePrincipal = new Stage();
        EscolherProjetoController epb = loader.getController();
        epb.setBolsista(bolsista);
        epb.setStage(stagePrincipal);

        stagePrincipal.setOnShown(evento -> {
            try {
                epb.OnClickProjeto();
            } catch (SQLException ex) {
                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // tpb.ajustarElementosJanela(bolsista);
        });

        Scene cena = new Scene(root);
        stagePrincipal.setTitle("Tela Escolher Projeto Bolsista");
        stagePrincipal.setMaximized(false);
        stagePrincipal.setScene(cena);
        stagePrincipal.show();
        stageTelaPrincipalBolsista.close();

    }

    private void AbrirTelaLogin() throws IOException {

        URL url = new File("src/main/java/view/TelaLogin.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageLogin = new Stage();

        TelaLoginController tpc = loader.getController();
        tpc.setStage(stageLogin);

        Scene cena = new Scene(root);
        stageLogin.setTitle("Tela de Login");
        stageLogin.setScene(cena);
        //deixa a tela maximizada
        stageLogin.setMaximized(true);
        stageLogin.show();

        stageTelaPrincipalBolsista.close();

    }

    public void ajustarElementosJanela(Bolsista bolsista, Projeto projeto) {
        this.bolsista = bolsista;
        this.projeto = projeto;

        System.out.println("Aqui chegam os parâmetros do login " + bolsista.getNome() + " - " + bolsista.getMatricula() + " ATIVA: " + bolsista.getAtiva());

        TxtNomeUsuario.setText(bolsista.getNome());
        // String matricula = String.valueOf(bolsista.getMatricula());
        textNomeProjeto.setText(projeto.getTitulo());
        TxtNomeProjetoBarra.setText(projeto.getTitulo());

    }

    public void alerta(String msg, int tipo, String titulo) throws IOException {
        URL url = new File("src/main/java/view/AlertGenerico.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageAlerta = new Stage();

        AlertGenericoController vpb = loader.getController();
        vpb.setMsg(msg);
        vpb.setTipo(tipo);
        vpb.setStage(stageAlerta);
        vpb.setControllerResposta(this);

        Scene cena = new Scene(root);
        stageAlerta.setTitle(titulo);
        stageAlerta.setScene(cena);

        stageAlerta.show();

    }

    @Override
    public void btnOk() {
    
            resp = 2;   
    }
}