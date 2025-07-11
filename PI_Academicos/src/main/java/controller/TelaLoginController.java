package controller;

import dal.ConexaoBD;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Administrador;
import model.Bolsista;
import model.Coordenador;
import model.LoginDAO;
import model.Usuario;
import util.AlertaUtil;

public class TelaLoginController implements INotificacaoAlert {

    private Stage stageLogin;
    private Connection conexao;
    private final LoginDAO dao = new LoginDAO();
    private Usuario user;
    TeladefundoController stageTelafundo;

    @FXML
    private Button btnEntrar;

    @FXML
    private Label lblCadastro;

    @FXML
    private Text lblRecuperarSenha;

    @FXML
    private TextField txtApelido;

    @FXML
    private PasswordField txtSenha;

    @FXML
    void onClickEntrar(ActionEvent event) throws IOException, SQLException {

        processarLogin();
    }

    public void setStage(Stage stage) {
        this.stageLogin = stage;
    }

    public void verificarBanco() throws IOException {

        this.conexao = ConexaoBD.conectar();
        if (this.conexao != null) {
            System.out.println("Conectou no banco de dados");
        } else {
            System.out.println("Não conectou o banco de dados");
        }

    }

    public void abrirJanela() throws IOException {
        verificarBanco();
    }

    public void processarLogin() throws IOException, SQLException {
        if (!dao.bancoOnline()) {
            alerta("Banco de dados não conectado", 2, "Erro de banco");
        } else if (txtApelido.getText() != null && !txtApelido.getText().isEmpty() && txtSenha.getText() != null && !txtSenha.getText().isEmpty()) {
            user = autenticar(txtApelido.getText(), txtSenha.getText());
            if (user != null) {
                System.out.println("Acesso liberado");

                if (stageLogin != null) {
                    stageLogin.close();
                }
                if (user instanceof Coordenador) {
                    Coordenador c = (Coordenador) user;
                    abrirTelaPrincipalCoordenador(c);
                } else if (user instanceof Bolsista) {//login bolsista
                    Bolsista b = (Bolsista) user;
                    System.out.println("Abrindo tela de Bolsista...");

                    abrirTelaPrincipalBolsista(b);
                } else if (user instanceof Administrador) {//login adm
                    Administrador a = (Administrador) user;
                    System.out.println("Abrindo tela de Administrador...");

                    abrirTelaPrincipalAdministradorTeste(a);
                    //abrirTelaPrincipalAdministrador(a);
                }

            } else {

                alerta("Este usuário ou senha é invalido!", 1, "Erro de validação");

            }
        } else {
            alerta("Verifique as informações", 2, "Erro de validação");
        }

    }

    @FXML
    void onClickCadastro(MouseEvent event) throws IOException {

        URL url = new File("src/main/java/view/CadastroCoordenador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);

        Parent root = loader.load();

        Stage stage = new Stage();

        CadastroCoordenadorController cc = loader.getController();
        cc.setStage(stage);

        Scene cena = new Scene(root);
        stage.setTitle("Cadastro Coordenador");
        stage.setMaximized(true);
        stage.setScene(cena);
        stage.show();

    }

    @FXML
    void onClickRecuperarSenha(MouseEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/RecuperarSenha.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);

        Parent root = loader.load();

        Stage stageRecuperar = new Stage();

        RecuperarSenhaController rsc = loader.getController();
        rsc.setStage(stageRecuperar);

        Scene cena = new Scene(root);
        stageRecuperar.setTitle("Recuperação de senha");
        stageRecuperar.setMaximized(true);
        stageRecuperar.setScene(cena);
        stageRecuperar.show();

    }

    private Usuario autenticar(String apelido, String senha) throws SQLException {
        user = dao.autenticar(apelido, senha);
        if (user != null) {

            return user;
        }
        return null;
    }

    public void abrirTelaPrincipalCoordenador(Coordenador coordenador) throws MalformedURLException, IOException {
        // Carrega a tela de fundo
        URL urlFundo = new File("src/main/java/view/Teladefundo.fxml").toURI().toURL();
        FXMLLoader loaderFundo = new FXMLLoader(urlFundo);
        Parent rootFundo = loaderFundo.load();

        Stage stageFundo = new Stage();

        stageFundo.setTitle("Tela de Fundo Escolher Projeto");
        stageFundo.setScene(new Scene(rootFundo));
        stageFundo.setMaximized(true);
        stageFundo.show();

        // Carrega a tela de escolher projeto
        URL urlProjeto = new File("src/main/java/view/EscolherProjeto.fxml").toURI().toURL();
        FXMLLoader loaderProjeto = new FXMLLoader(urlProjeto);
        Parent rootProjeto = loaderProjeto.load();

        Stage stageProjeto = new Stage();

        EscolherProjetoController tpc = loaderProjeto.getController();
        tpc.setCoordenador(coordenador);
        tpc.setStagefundo(stageFundo);
        tpc.setStage(stageProjeto);

        stageProjeto.setOnShown(evento -> {
            try {
                tpc.OnClickProjeto();
            } catch (SQLException ex) {
                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Scene cenaProjeto = new Scene(rootProjeto);
        stageProjeto.setTitle("Escolher Projeto");
        stageProjeto.setScene(cenaProjeto);
        stageProjeto.initOwner(stageFundo); // Define o fundo como tela base
        stageProjeto.show();

        // Fecha a tela de login
        if (stageLogin != null) {
            stageLogin.close();
        }

        System.out.println("Coordenador ao clicar em Criar Projeto: " + coordenador);

    }

    public void abrirTelaPrincipalBolsista(Bolsista bolsista) throws MalformedURLException, IOException {

        // Carrega a tela de fundo
        URL urlFundo = new File("src/main/java/view/Teladefundo.fxml").toURI().toURL();
        FXMLLoader loaderFundo = new FXMLLoader(urlFundo);
        Parent rootFundo = loaderFundo.load();

        Stage stageFundo = new Stage();

        stageFundo.setTitle("Tela de Fundo Escolher Projeto");
        stageFundo.setScene(new Scene(rootFundo));
        stageFundo.setMaximized(true);
        stageFundo.show();

        URL url = new File("src/main/java/view/EscolherProjeto.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePrincipal = new Stage();
        EscolherProjetoController epb = loader.getController();
        epb.setBolsista(bolsista);
        epb.setStagefundo(stageFundo);
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

        stagePrincipal.setScene(cena);
        stagePrincipal.show();
        stageLogin.close();

    }

    private void abrirTelaPrincipalAdministrador(Administrador adm) throws MalformedURLException, IOException {

        URL url = new File("src/main/java/view/TelaPrincipalAdministrador.fxml").toURI().toURL();
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
        stageLogin.close();

    }

    private void abrirTelaPrincipalAdministradorTeste(Administrador adm) throws MalformedURLException, IOException {

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
        stagePrincipal.setTitle("Tela principal Administrador Teste");
        stagePrincipal.setScene(cena);
        //deixa a tela maximizada
        stagePrincipal.setMaximized(true);

        stagePrincipal.show();

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

    }

}
