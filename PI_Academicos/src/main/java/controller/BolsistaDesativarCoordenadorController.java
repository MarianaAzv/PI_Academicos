package controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Bolsista;
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
    void OnClickConcluido(ActionEvent event) throws IOException {
      DesativarBolsista();
        
        Concluido();
    }

    //--------------*Metodos*-------------//
    
    public void SetarBolsistas(){

    try{
        ProjetoDAO dao = new  ProjetoDAO();
        
    List<Bolsista> bolsistas = dao.selecioBolPProj(projeto.getIdProjeto());

        for (Bolsista b : bolsistas) {
            CheckBox cb = new CheckBox(b.getNome() + " - Matrícula: " + b.getMatricula());
            cb.setUserData(b); 
            vbox.getChildren().add(cb);
            checkboxes.add(cb);
        }
           
        
    }catch(Exception e){
        mostrarErro("Erro","Erro ao carregar bolsista");
    } 
          
    }
    
    public void DesativarBolsista() {

 ProjetoDAO dao =  new ProjetoDAO();
 
   for (CheckBox cb : checkboxes) {
       if(cb.isSelected()){
           
       Bolsista b = (Bolsista) cb.getUserData();
            try {
                dao.Destivar(b.getId(), projeto.getIdProjeto());
            } catch (Exception e) {
                mostrarErro("Erro", "Erro ao remover o bolsista: " + b.getNome());
                e.printStackTrace();
            }

        
        }
        
        
    }
    }
    
    public void Concluido() throws IOException{
           URL url = new File("src/main/java/view/AtualizarProjeto.fxml").toURI().toURL();       
      FXMLLoader loader = new FXMLLoader(url);
       
      Parent root = loader.load();
        
     Stage stageAtualizarProjeto = new Stage();
        
       AtualizarProjetoController apc = loader.getController();
        
        apc.setStage(stageAtualizarProjeto);
        apc.setProjeto(projeto);
        
          stageAtualizarProjeto.setOnShown(evento -> {
        apc.ajustarElementosJanela();
      });
        
      Scene cena = new Scene(root);
       stageAtualizarProjeto.setTitle("Atualizar Projeto");
       stageAtualizarProjeto.setMaximized(true);
        stageAtualizarProjeto.setScene(cena);
       stageAtualizarProjeto.show();
        stageBolsistaDesativar.close();
    }
}
