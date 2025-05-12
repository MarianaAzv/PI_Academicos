


   package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Projeto;

   //Toda vez que clicar num botao ele abre a tela principal do cordenador que esta logado com o 
public class EscolherProjetoController {

    private Stage stageEscolherProjeto; 
         Projeto projeto;
         
        @FXML
    private Button btnCriarProjetos;
        
    @FXML
    private Button btnNomeProjeto;

    @FXML
    private Button btnNomeProjeto1;

    @FXML
    private Button btnNomeProjeto2;

    @FXML
    private ImageView imgFotoDoProjeto;

    
    //Abre essa tela antes da tela da tela principal, esta precisa criar projeto e 
    //mostrar quais projeto ja existem no sistema relacionados ao id da pessoa ou em quais ela ja esta cadastrada
    

              @FXML
    void OnClickCriarProjeto(ActionEvent event) {

    }
         
         public void setStage(Stage stageEscolherProjeto){
        this.stageEscolherProjeto= stageEscolherProjeto;
            
}
 public void setProjeto(Projeto pro) {
       this.projeto = pro;
 //     btnNomeProjeto; 
      
    }
 
 public void OnClickProjeto(Projeto escoproje ){
 //Esse botao abre o projeto escolhido    
 }

}


    

