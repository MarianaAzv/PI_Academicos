package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Projeto;
import model.ProjetoDAO;
import static util.AlertaUtil.mostrarErro;


public class BolsistaDesativarCoordenadorController {

    private Stage stageBolsistaDesativar;
    Projeto projeto;

    
    private List<CheckBox> checkboxes = new ArrayList<>();


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

    try{
        ProjetoDAO dao = new  ProjetoDAO();
        
  

    }catch(Exception e){
        mostrarErro("Erro","Erro ao carregar bolsista");
    } 
          
    }
    
    public void DesativarBolsista() {

 
 
 
        
        
        
        
    }
    
    public void Concluido(){
        
    }
}
