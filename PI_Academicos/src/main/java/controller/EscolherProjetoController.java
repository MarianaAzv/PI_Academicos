


   package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Coordenador;
import model.Projeto;
import model.ProjetoDAO;
import static util.AlertaUtil.mostrarAviso;

   //Toda vez que clicar num botao ele abre a tela principal do cordenador que esta logado com o 
public class EscolherProjetoController {

    private Stage stageEscolherProjeto; 
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
       @FXML
    private VBox vboxbutton;

    @FXML
    private VBox vboximg;

    
           public void setStage(Stage stageEscolherProjeto){
            this.stageEscolherProjeto= stageEscolherProjeto;
        
        
           }
    
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
         
  
    
 public void setProjeto() {
     ProjetoDAO dao = new ProjetoDAO();
    try {
     //   List<Projeto> projetos = dao.selecionarProjeto();
        OnClickProjeto();
    } catch (SQLException e) {
       mostrarAviso("Erro","Falha com o Banco");
    }
    }
  
   
 
 public void OnClickProjeto() throws SQLException{
 //Esse botao abre o projeto escolhido  
 
  vboxbutton.getChildren().clear();

try{
  
  ProjetoDAO dao = new ProjetoDAO();
        List<Projeto> projetos = dao.selecionarProjeto(coordenador);
        
    for (Projeto projeto : projetos) {
            Button btn = new Button(projeto.getTitulo());
            btn.setOnAction(event -> {
                try {
                    abriProjeto(projeto);
                } catch (IOException e) {
                    mostrarAviso("Erro","Erro ao carregar projeto");
                }
            });
            vboxbutton.getChildren().add(btn);
        }
}catch(SQLException e){
    mostrarAviso("Erro","Erro ao carregar os projeto");
}
 }

 private void abriProjeto(Projeto projeto) throws MalformedURLException, IOException{
    URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalCoordenadorController tpc = loader.getController();    
            tpc.setStagePrincipal(stagePrincipal);
            //tpc.setCoordenador(coordenador);
            
            stagePrincipal.setOnShown(evento -> {
            tpc.ajustarElementosJanela(coordenador,projeto);
        });
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal Coordenador");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
            stageEscolherProjeto.close();
 }

    void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

}
    

