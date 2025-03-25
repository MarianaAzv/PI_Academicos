package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void onClickCadastro(MouseEvent event) {

    }

    @FXML
    void onClickEntrar(ActionEvent event) {

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
