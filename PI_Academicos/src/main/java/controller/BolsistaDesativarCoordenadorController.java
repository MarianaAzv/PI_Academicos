package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Projeto;

public class BolsistaDesativarCoordenadorController {

    private Stage stageBolsistaDesativar;
    Projeto projeto;

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
        
        
        
          
    }
    
    public void DesativarBolsista() {

 
 
 
        
        
        
        
    }
    
    public void Concluido(){
        
    }
}
