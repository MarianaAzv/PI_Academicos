package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import util.CPF;
import util.Email;

public class CadastrarAdministradorController {

    private Stage stage;
    private File arquivoSelecionado=null;
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

        try {
            if (txtCPF.getText().isEmpty() || txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtEmail.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                if (CPF.isValid(txtCPF.getText())) {
                    System.out.print("CPF válido");
                    if (Email.isValidEmail(txtEmail.getText())) {

                        incluir(txtCPF.getText(), txtNome.getText(), txtUsuario.getText(), txtEmail.getText(), txtSenha.getText());

                    } else {
                        mostrarAviso("ERRO", "Email inválido");
                    }
                } else {
                    mostrarAviso("ERRO", "CPF inválido");
                }
            } else {
                mostrarAviso("ERRO", "Por favor inserir todos os dados");
            }
        } catch (NumberFormatException n) {
            mostrarAviso("CPF inválido", "O valor inserido para CPF deve ser apenas números");
        }
        if(onADMCadastrado != null){
            onADMCadastrado.run();
        }
        
    }
    public void setOnADMCadastrado(Runnable callback){
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
                mostrarAviso("Nome de usuário indisponível", "Este nome de usuário já está sendo usado");

            } else if (nome.isEmpty() || apelido.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                mostrarAviso("Campos de preenchimento obrigatórios", "Todos os campos de cadastro devem ser preenchidos.");
            } else {
                new AdministradorDAO().cadastrarUsuarioAdministrador(usuario, administrador, fotoPerfil);
                mostrarConfirmacao("Usuário cadastrado", "O usuário foi registrado no sistema com sucesso!");
                stage.close();
            }
        }

    }
        else{
        new AdministradorDAO().cadastrarUsuarioAdministrador(usuario,administrador, fotoPerfil);
        mostrarConfirmacao("Usuário cadastrado","O usuário foi registrado no sistema com sucesso!");
        stage.close();
        }
        }
        
        
    }
    
    
    

}
