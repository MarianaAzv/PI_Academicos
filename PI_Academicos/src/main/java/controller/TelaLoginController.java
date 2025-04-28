package controller;

import dal.ConexaoBD;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.LoginDAO;
import model.Usuario;
import util.AlertaUtil;

public class TelaLoginController {
    
    private Stage stageLogin;
    private Connection conexao;
    private final LoginDAO dao = new LoginDAO();
    private ArrayList<String> listaDados;
    private Usuario user;
                     
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
    
    public void setStage(Stage stage){
        this.stageLogin = stage;
    }
    
    public void verificarBanco() {
        
        this.conexao = ConexaoBD.conectar();
        if (this.conexao != null) {
            System.out.println("Conectou no banco de dados");
        } else {
            System.out.println("Problemas na conexão com o banco de dados");
        }

    }
        
    public void abrirJanela(){
        verificarBanco();
    }
        
    public void processarLogin() throws IOException, SQLException {
        if (!dao.bancoOnline()) {
            System.out.println("Banco de dados desconectado!");
        } else if (txtApelido.getText() != null && !txtApelido.getText().isEmpty() && txtSenha.getText() != null && !txtSenha.getText().isEmpty()) {
            listaDados = autenticar(txtApelido.getText(),txtSenha.getText());
            if (listaDados != null) {
                System.out.println("Bem vindo " + listaDados.get(0) + " acesso liberado!");
                       
                if (stageLogin != null) { 
                    stageLogin.close();
                }
                abrirTelaPrincipal(listaDados);
            } else {
               System.out.println("Usuário e senha invalidos!");

            }
        } else {
            System.out.println("Verifique as informações!");
        }

    }
    @FXML
    void onClickCadastro(MouseEvent event) throws IOException {
        
        URL url = new File("src/main/java/view/CadastroCoordenador.fxml").toURI().toURL();       
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stage = new Stage();
        
        CadastroCoordenadorController cc = loader.getController();
        
        Scene cena = new Scene(root);
        stage.setTitle("Cadastro Coordenador");
        stage.setMaximized(true);
        stage.setScene(cena);
        stage.show();

    }

    @FXML
    void onClickRecuperarSenha(MouseEvent event) {

    }
    

    private ArrayList<String> autenticar(String apelido, String senha) throws SQLException {
    user = dao.autenticar(apelido, senha);
        if (user != null) {
            ArrayList<String> listaDados = new ArrayList<>();
            listaDados.add(user.getNome());
            listaDados.add(user.getApelido());
            return listaDados;
        }
        return null;
    }
    
    private void abrirTelaPrincipal(ArrayList<String> listaDados) throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalCoordenadorController tpc = loader.getController();    
            tpc.setStage(stagePrincipal);
            
            stagePrincipal.setOnShown(evento -> {
            tpc.ajustarElementosJanela(listaDados);
        });
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal Coordenador");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
            stageLogin.close();
    }
}
