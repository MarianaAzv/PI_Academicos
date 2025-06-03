package controller;

<<<<<<< Updated upstream
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
=======
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
>>>>>>> Stashed changes

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Projeto;
<<<<<<< Updated upstream
=======
import model.ProjetoDAO;
>>>>>>> Stashed changes

public class BolsistaDesativarCoordenadorController {

    private Stage stageBolsistaDesativar;
    Projeto projeto;
<<<<<<< Updated upstream
=======
    
    private List<CheckBox> checkboxes = new ArrayList<>();
>>>>>>> Stashed changes

    @FXML
    private Button btnConcluido;

    @FXML
    private Label lblBolsistas;

    @FXML
    private VBox vbox;

    //------------*SETs*---------//
    public void setStage(Stage telaBolsistaDesativar) {
        this.stageBolsistaDesativar = telaBolsistaDesativar;
    }
    
    
    public void setProjeto(Projeto projeto){
        this.projeto = projeto;
        SetarBolsistas();
    }

    //---------------*OnClick*---------------//
    @FXML
    void OnClickConcluido(ActionEvent event) {
      DesativarBolsista();
        
        Concluido();
    }

    //--------------*Metodos*-------------//
    
    public void SetarBolsistas(){
<<<<<<< Updated upstream
        
        
=======
    
        ProjetoDAO dao = new  ProjetoDAO();
        
  
>>>>>>> Stashed changes
        
          
    }
    
    public void DesativarBolsista() {

 
 
 
        
        
        
        
    }
    
    public void Concluido(){
        
    }
}
