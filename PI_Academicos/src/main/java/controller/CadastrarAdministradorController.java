package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Administrador;
import model.AdministradorDAO;
import model.Coordenador;
import model.CoordenadorDAO;
import model.Foto;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;
import util.Apenasletras;
import util.CPF;
import util.CPFDuplicado;
import util.Email;
import util.Senha;

public class CadastrarAdministradorController implements INotificacaoAlert {

    private Stage stage;
    private File arquivoSelecionado = null;
    private Runnable onADMCadastrado;// callback

    @FXML
    private GridPane btnAtualizarProjeto;

    @FXML
    private Button btnCadastrar;

    @FXML
    private ImageView imgFotoAdministrador;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

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
    void onClickCadastrar(ActionEvent event) throws SQLException, IOException {
        if (!CPF.isValid(txtCPF.getText())) {
            alerta("CPF inválido", 2, "ERRO");
            return;
        }
        if (!CPFDuplicado.cpfDuplicado(txtCPF.getText())==false) {
            alerta("Já existe um usuario com esse CPF cadastrado no sistema", 1, "ERRO");
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

        try {
            if (txtCPF.getText().isEmpty() || txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtEmail.getText().isEmpty() || txtSenha.getText().isEmpty()) {

                incluir(txtCPF.getText(), txtNome.getText(), txtUsuario.getText(), txtEmail.getText(), txtSenha.getText());

            } else {
                alerta("Por favor inserir todos os dados",2,"ERRO");
            }
        } catch (NumberFormatException n) {
            alerta("O valor inserido para CPF deve ser apenas números",2,"CPF inválido");
        }
        if (onADMCadastrado != null) {
            onADMCadastrado.run();
        }

    }

    public void setOnADMCadastrado(Runnable callback) {
        this.onADMCadastrado = callback;
    }

    @FXML
    void onClickAdicionarFoto(MouseEvent event) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de Imagem", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        arquivoSelecionado = fileChooser.showOpenDialog(imgFotoAdministrador.getScene().getWindow());

        if (arquivoSelecionado != null) {
            System.out.println("Arquivo escolhido: " + arquivoSelecionado.getAbsolutePath());
            String urlImagem = arquivoSelecionado.toURI().toURL().toString();
            Image fotoEscolhida = new Image(urlImagem);
            imgFotoAdministrador.setImage(fotoEscolhida);
        } else {
            System.out.println("Nenhum arquivo foi selecionado");
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    void incluir(String cpf, String nome, String apelido, String email, String senha) throws SQLException, IOException {
        Usuario usuario = new Usuario(cpf, nome, apelido, email, senha);
        Administrador administrador = new Administrador();

        if (arquivoSelecionado == null) {
            mostrarAviso("Arquivo para foto de perfil não escolhido", " É ne cessário escolher uma foto de perfil para o administrador");
        } else {
            byte[] conteudoImagem = Files.readAllBytes(arquivoSelecionado.toPath());
            Foto fotoPerfil = new Foto(conteudoImagem);

            int repetido = new AdministradorDAO().validarApelido(apelido, 0);
            if (repetido > 0) {
                alerta("Este nome de usuário já está sendo usado",1,"Nome de usuário indisponível");

            } else if (nome.isEmpty() || apelido.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                alerta("Todos os campos de cadastro devem ser preenchidos.",2,"Campos de preenchimento obrigatórios");
            } else {
                new AdministradorDAO().cadastrarUsuarioAdministrador(usuario, administrador, fotoPerfil);
               alerta("O usuário foi registrado no sistema com sucesso!",3,"Usuário cadastrado");
                stage.close();
            }
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
