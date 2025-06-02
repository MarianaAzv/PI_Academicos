package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Projeto;

public class TelaNoticiaController {
    
    Stage stageNoticia;

    void setStage(Stage stageNoticia) {
        this.stageNoticia = stageNoticia;
    }
    Projeto projeto;
    
     public void setProjeto(Projeto projeto) {
       this.projeto = projeto;
    }

      @FXML
    private ImageView btnMaisFotos;

    @FXML
    private Button btnPostar;

    @FXML
    private Label lblImagem;

    @FXML
    private TextField tfTituloNoticia;

    @FXML
    private TextArea txtLegenda;

    @FXML
    void onClickImagem(MouseEvent event) {

    }

    @FXML
    void onClickMaisFotos(MouseEvent event) {

    }

    @FXML
    void onClickPostar(ActionEvent event) {

    }

}
