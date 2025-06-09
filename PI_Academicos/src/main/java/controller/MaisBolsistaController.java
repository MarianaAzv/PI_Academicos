package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Coordenador;
import model.Projeto;
import static util.AlertaUtil.mostrarAviso;
import util.Origem;

public class MaisBolsistaController {

    private Stage stageMaisBolsista;
    Projeto projeto;
    private Origem origem;
    Coordenador coordenador;
    
    @FXML
    private Button BtnNao;

    @FXML
    private Button btnSim;

    @FXML
    private Label lblProjeto;
    
    @FXML
    private Label lblVocêdesejaadicionarmaisbolsistas;
    

//-------------*Clicks*----------------//
    @FXML
    void OnClickNao(ActionEvent event) {
         try {
        switch (origem) {
            case atualizar_projeto:
                abrirTelaAtualizarProjeto();
                break;
            case  principal_coordenador:
                abrirTelaCoordenador();
                break;
          
        }
    } catch (IOException e) {
      
        mostrarAviso("Erro", "Erro no sistema");
    }
    }

    @FXML
    void OnClickSim(ActionEvent event) throws IOException {

        abrirJanelaCadastroBolsista();
        
    }
    
    //-------------------*SETs*-------------------//
    public void setProjeto(Projeto projeto){
        this.projeto = projeto;
    }
    
    public void setStage(Stage stageMaisBolsista){
        this.stageMaisBolsista = stageMaisBolsista;
    }
    
    public void setOrigem(Origem origem){
        this.origem=origem;
    }
  public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }
    //--------------------*METODOS*-----------------//
    public void abrirJanelaCadastroBolsista() throws IOException{
       URL url = new File("src/main/java/view/CadastroBolsistaCoodernador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastroBolsista = new Stage();
            
            CadastroBolsistaCoordenadorController cbcc = loader.getController();
            cbcc.setCoordenador(coordenador);
            cbcc.setProjeto(projeto);
            cbcc.setStage(stageCadastroBolsista);
            cbcc.setOrigem(origem);
        
            Scene cena = new Scene(root);
            stageCadastroBolsista.setTitle("Adicionar bolsistas");
            stageCadastroBolsista.setScene(cena);
            //deixa a tela maximizada
            
            stageCadastroBolsista.show();
            stageMaisBolsista.close();
           
   }
      public void abrirTelaAtualizarProjeto() throws IOException{
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
       stageMaisBolsista.close();
        System.out.print("O coordenador esta sendo set"+coordenador);
       }
      
      
      public void  abrirTelaCoordenador() throws IOException {
       URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalCoordenadorController tpc = loader.getController();    
            tpc.setStagePrincipal(stagePrincipal);
            tpc.setCoordenador(coordenador);
            tpc.setProjeto(projeto);
            
            stagePrincipal.setOnShown(evento -> {
            tpc.ajustarElementosJanela(coordenador,projeto);
        });
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal Coordenador");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
             System.out.print("O coordenador esta sendo set"+coordenador);
           stageMaisBolsista.close();

}

    
}
