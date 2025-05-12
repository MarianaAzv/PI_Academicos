


   package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Coordenador;
import model.Projeto;
import static util.AlertaUtil.mostrarAviso;

   //Toda vez que clicar num botao ele abre a tela principal do cordenador que esta logado com o 
public class EscolherProjetoController {

    private Stage stageEscolherProjeto; 
         Projeto projeto;
         Coordenador coordenador;
         
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
    void OnClickCriarProjeto(ActionEvent event) throws MalformedURLException, MalformedURLException, IOException {
        try{
if(coordenador.getAtiva()==true){
                 
         URL url = new File("src/main/java/view/CriarProjeto.fxml").toURI().toURL();
        
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        //Comunicar com o combo box com o DAO
        
        
        Stage telaCriarProjeto = new Stage();
        
         CriarProjetoController tpc = loader.getController();
       
        tpc.setStage(telaCriarProjeto);
        
         telaCriarProjeto.setOnShown(evento -> {
        tpc.ajustarElementosJanela();
      });
        
          Scene cena = new Scene(root);
           telaCriarProjeto.setTitle("Tela Criar Projeto");
            telaCriarProjeto.setMaximized(true);
        telaCriarProjeto.setScene(cena);
        telaCriarProjeto.show();
        
         }
    }catch (IOException e) {
        mostrarAviso("Erro","Falha ao abrir a tela de criação de projeto");
    }
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


    

