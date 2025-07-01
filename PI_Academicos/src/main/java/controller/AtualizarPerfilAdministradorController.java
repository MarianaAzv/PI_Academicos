package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Administrador;
import model.AdministradorDAO;
import model.Foto;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;
import util.ApenasNumeros;
import util.Apenasletras;
import util.CPF;
import util.CPFDuplicado;
import util.Email;
import util.Senha;

public class AtualizarPerfilAdministradorController implements INotificacaoAlert {

    private Administrador adm;
    private Stage stageAtualizarADM;
    private File arquivoSelecionado = null;

    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnADM;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private GridPane btnAtualizarProjeto;

    @FXML
    private Button btnNotificacoes;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerPerfil;

    @FXML
    private Button btnDesativar;

    @FXML
    private ImageView imgFotoAdministrador;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNomeAdm;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    void onClickAtualizar(ActionEvent event) throws SQLException, IOException {
        if (!CPF.isValid(txtCPF.getText())) {
            alerta("CPF inválido", 2, "Data inválida");
            return;
        }
        if(!ApenasNumeros.isNumeros(txtCPF.getText())){
            alerta("Somente números no CPF",1,"ERRO");
            return;
        }

        if (!Apenasletras.isLetras(txtNome.getText())) {
            alerta("Nome inválido", 2, "ERRO");
            return;
        }
       
        if (!Email.isValidEmail(txtEmail.getText())) {
            alerta("Email inválido", 2, "ERRO");
            return;
        }
        if (!Senha.senhaForte(txtSenha.getText())) {
            alerta("A senha esta muito fraca, para uma senha forte é necessario ter 6 caracters,ter pelo menos 1 letra Maiuscula e 1 Letra minuscula, um numero e um simbulo especial", 2, "ERRO");
            return;
        }
        if(arquivoSelecionado != null){
            alerta("Por favor escolher uma foto",2,"Erro");
        }

        try {

            atualizarAdministrador(adm.getId(), txtCPF.getText(), txtNome.getText(), txtUsuario.getText(), txtEmail.getText(), txtSenha.getText(), adm.getAtiva());
        } catch (NumberFormatException n) {
            alerta("O valor inserido para CPF deve ser apenas números", 2, "CPF inválido");
        }
    }

    @FXML
    void onClickDesativar(ActionEvent event) throws IOException, SQLException {

        //mostrarConfirmacao("Usuário alterado","O usuário foi alterado com sucesso!");
        if (adm.getAtiva() == true) {
            Optional<ButtonType> result = mostrarConfirmacao("O seu perfil está prestes a ser DESATIVADO", "Têm certeza que deseja desativar o perfil?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("Usuário desativado.");
                new AdministradorDAO().desativarAdministrador(adm);
            } else {
                System.out.println("Usuário cancelou a ação.");
            }
        } else {
            Optional<ButtonType> result = mostrarConfirmacao("O seu perfil está prestes a ser ATIVADO", "Têm certeza que deseja ativar o perfil?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("Usuário ativado");
                new AdministradorDAO().ativarAdministrador(adm);
            } else {
                System.out.println("Usuário cancelou a ação.");
            }
        }

        URL url = new File("src/main/java/view/TelaPrincipalAdministrador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePrincipal = new Stage();

        TelaPrincipalAdministradorController tpc = loader.getController();
        tpc.setStage(stagePrincipal);

        stagePrincipal.setOnShown(evento -> {
            tpc.ajustarElementosJanela(adm);
        });

        Scene cena = new Scene(root);
        stagePrincipal.setTitle("Tela principal Administrador");
        stagePrincipal.setScene(cena);
        //deixa a tela maximizada
        stagePrincipal.setMaximized(true);

        stagePrincipal.show();
        stageAtualizarADM.close();

    }

    @FXML
    void onClickFotoPerfil(MouseEvent event) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de Imagem", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        arquivoSelecionado = fileChooser.showOpenDialog(imgFotoAdministrador.getScene().getWindow());

        if (arquivoSelecionado != null) {
            String urlImagem = arquivoSelecionado.toURI().toURL().toString();
            Image fotoEscolhida = new Image(urlImagem);
            imgFotoAdministrador.setImage(fotoEscolhida);
        } else {
            System.out.println("Nenhum arquivo foi selecionado");
        }

    }

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {

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
    void onClickVerPerfil(ActionEvent event) throws IOException {
        abrirTelaVerPerfil();
    }

    @FXML
    void OnDragEnterVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color:  D07979");
    }

    @FXML
    void OnDragExitVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color:  DBA5A5");
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

    private void abrirTelaVerPerfil() throws MalformedURLException, IOException {

        URL url = new File("src/main/java/view/VerPerfilAdministrador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageVerPerfil = new Stage();

        VerPerfilAdministradorController vpac = loader.getController();
        vpac.setStage(stageVerPerfil);
        vpac.setAdministrador(adm);
        stageVerPerfil.setMaximized(true);

        Scene cena = new Scene(root);
        stageVerPerfil.setTitle("Perfil administrador");
        stageVerPerfil.setScene(cena);

        stageVerPerfil.show();
        stageAtualizarADM.close();

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
        stageAtualizarADM.close();
    }

    private void abrirTelaADMS() throws IOException {

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
        stageAtualizarADM.close();
    }

    private void abrirTelaNotificacoes() throws IOException {

        URL url = new File("src/main/java/view/Notificacoes.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageNotificacoes = new Stage();

        NotificacoesController nc = loader.getController();
        nc.setAdministrador(adm);
        nc.setStage(stageNotificacoes);

        Scene cena = new Scene(root);
        stageNotificacoes.setTitle("Tela notificações");
        stageNotificacoes.setScene(cena);
        //deixa a tela maximizada
        stageNotificacoes.setMaximized(true);

        stageNotificacoes.show();
        stageAtualizarADM.close();
    }

    private void abrirTelaLogin() throws IOException {

        URL url = new File("src/main/java/view/TelaLogin.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageLogin = new Stage();

        TelaLoginController tlc = loader.getController();
        tlc.setStage(stageLogin);

        Scene cena = new Scene(root);
        stageLogin.setTitle("Tela Login");
        stageLogin.setScene(cena);
        //deixa a tela maximizada
        stageLogin.setMaximized(true);

        stageLogin.show();
        stageAtualizarADM.close();
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
        stageAtualizarADM.close();
    }

    public void setStage(Stage stage) {
        this.stageAtualizarADM = stage;
    }

    public void setAdministrador(Administrador adm) {
        this.adm = adm;
        txtNome.setText(adm.getNome());
        lblNomeAdm.setText(adm.getNome());
        txtUsuario.setText(adm.getApelido());
        String cpf = String.valueOf(adm.getCpf());
        txtCPF.setText(cpf);
        txtSenha.setText(adm.getSenha());
        txtEmail.setText(adm.getEmail());

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

    void atualizarAdministrador(int id, String cpf, String nome, String apelido, String email, String senha, boolean ativa) throws SQLException, IOException {

        Foto fotoPerfil = new Foto();
        if (arquivoSelecionado == null) {// caso o adm não queira alterar foto
            fotoPerfil.setDadosImagem(adm.getFotoPerfil().getDadosImagem());
        } else {
            byte[] conteudoImagem = Files.readAllBytes(arquivoSelecionado.toPath());
            fotoPerfil.setDadosImagem(conteudoImagem);
        }

        Administrador adm = new Administrador(id, cpf, nome, apelido, email, senha, ativa, fotoPerfil);

        int repetido = new AdministradorDAO().validarApelido(apelido, id);
        if (repetido > 0) {
            alerta("Este nome de usuário já está sendo usado", 1, "Nome de usuário indisponível");

        } else {
            new AdministradorDAO().atualizarAdministrador(adm);
            alerta("O usuário foi alterado com sucesso!", 3, "Usuário alterado");
            setAdministrador(adm);
            abrirTelaPrincipal();
        }
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
