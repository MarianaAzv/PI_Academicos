
package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Coordenador;
import model.Projeto;


public class TeladefundoController  {
    private Stage stageTelafundo; 
    Coordenador coordenador;
    Projeto projeto;
    
    //Primeiro abrir TElafundo para partir dela abrir a tela de escolher projeto
    

//    AbrirEscolherProjeto();
    
    //-----------------*SETs*--------------------//
    public void setStage(Stage stageTelafundo){
            this.stageTelafundo= stageTelafundo;
        
    }
    
  public void setCoordenador(Coordenador coordenador) {
      this.coordenador = coordenador;
    }
  
    
    //----------------------------*METODOS*---------------------------------//
     public void AbrirEscolherProjeto(Coordenador coordenador) throws MalformedURLException, IOException{
       
     
       
        URL url = new File("src/main/java/view/EscolherProjeto.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
         Parent root = loader.load();
       
         Stage stagePrincipal = new Stage();
       
       EscolherProjetoController tpc = loader.getController();  
         tpc.setCoordenador(coordenador);
         tpc.setStage(stagePrincipal);
         
          
            stagePrincipal.setOnShown(evento -> {
            try {
                tpc.OnClickProjeto();
          } catch (SQLException ex) {
             Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
      });
       
         Scene cena = new Scene(root);
         stagePrincipal.setTitle("Tela Escolher Projeto");
           stagePrincipal.setScene(cena);
          stagePrincipal.show();
        
          System.out.println("Coordenador ao clicar em Criar Projeto: " + coordenador);
    }

    public void ajustarElementosJanela(Coordenador coordenador) {
         this.coordenador=coordenador;
    }

  

   

  

   
    
}
