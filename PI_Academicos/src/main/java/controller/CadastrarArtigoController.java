package controller;
 
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Projeto;

public class CadastrarArtigoController {
    
    Stage stageArtigo;

    void setStage(Stage stageArtigo) {
        this.stageArtigo = stageArtigo;
    }
    Projeto projeto;
    
     public void setProjeto(Projeto projeto) {
       this.projeto = projeto;
    }
     
     @FXML
    private Button btnPDF;

    @FXML
    private Button btnPostar;

    @FXML
    private DatePicker dataPublicacao;

    @FXML
    private TextArea txtAutores;

    @FXML
    private TextField txtNomeArtigo;

    @FXML
    private TextArea txtPalavrasChave;

    @FXML
    private TextArea txtResumo;

    
    
}
