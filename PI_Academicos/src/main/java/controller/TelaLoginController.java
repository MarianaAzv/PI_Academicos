package controller;

import dal.ConexaoBD;
import java.io.File;
import java.io.IOException;
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
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;

    @FXML
    void onClickEntrar(ActionEvent event) throws IOException, SQLException {
        
        //processarLogin();
        String apelido = txtNome.getText();
        String senha = txtSenha.getText();
        Usuario usuario = autenticar(apelido, senha);
        if( usuario != null){
            
            URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stage = new Stage();
        
            TelaPrincipalCoordenadorController tpc = loader.getController();
            
        
            Scene cena = new Scene(root);
            stage.setTitle("Tela principal Coordenador");
            stage.setScene(cena);
            //deixa a tela maximizada
            stage.setMaximized(true);
            
            stage.show();
            stageLogin.close();
            
        }else {
            
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Nome ou senha incorretos");
        alerta.setHeaderText("Verifique as informações inseridas");
        alerta.setContentText("Digite novamente");
        alerta.showAndWait();
        
    }
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
    

    private Usuario autenticar(String apelido, String senha) throws SQLException {
   if(!dao.bancoOnline()){
            System.out.println("Banco desconectado");
        }
        else if(apelido !=null && !apelido.isEmpty() && senha !=null && !senha.isEmpty()){
            LoginDAO logindao = new LoginDAO();
            Usuario usuario = logindao.autenticar(apelido, senha);
            return usuario;
    }
        else{
            System.out.println("Verifique as informações");
        }
        return null;
    }
}
