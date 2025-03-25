package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaLoginController {
    
    private Stage stageLogin;
                     
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
    void onClickCadastro(MouseEvent event) throws IOException {
        
        URL url = new File("src/main/java/view/CadastroCoordenador.fxml").toURI().toURL();       
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stage = new Stage();
        
        CadastroCoordenadorController cc = loader.getController();
        
        Scene cena = new Scene(root);
        stage.setTitle("Cadastro Coordenador");
        stage.setScene(cena);
        stage.show();

    }

    @FXML
    void onClickEntrar(ActionEvent event) throws IOException {

    }

    @FXML
    void onClickRecuperarSenha(MouseEvent event) {

    }
    
    public void abrirJanela(){
        System.out.println("controller.TelaLoginController.abrirJanela()");
    }
    public void setStage(Stage stage){
        this.stageLogin = stage;
    }
}
