package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Bolsista;
import model.Projeto;

public class CadastrarPostagemController {
    
    Stage stagePostagem;

    void setStage(Stage stagePostagem) {
        this.stagePostagem = stagePostagem;
    }
    
    Projeto projeto;
     
     public void setProjeto(Projeto projeto) {
       this.projeto = projeto;
       lblNomeProjeto.setText(projeto.getTitulo());
    }
      
    @FXML
    private ImageView btnCortarFoto;

    @FXML
    private ImageView btnMaisFotos;

    @FXML
    private Button btnPostar;

    @FXML
    private Label lblImagem;

    @FXML
    private Label lblNomeProjeto;

    @FXML
    private TextArea txtLegenda;

    @FXML
    void onClickCortarFotos(MouseEvent event) {

    }

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

