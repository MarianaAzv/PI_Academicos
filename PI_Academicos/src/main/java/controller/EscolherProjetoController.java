


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
import model.Bolsista;
import model.Coordenador;
import model.Projeto;
import model.ProjetoDAO;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;

   //Toda vez que clicar num botao ele abre a tela principal do cordenador que esta logado com o 
public class EscolherProjetoController {

    private Stage stageEscolherProjeto; 
         Coordenador coordenador;
         Bolsista bolsista;
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
       @FXML
    private VBox vboxbutton;

    @FXML
    private VBox vboximg;
    private Usuario user;

    
          
  //------------------------*OnClick*-------------------------//
  
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
        tpc.setCoordenador(coordenador);
        System.out.print("O coordenador é teste"+coordenador);
         telaCriarProjeto.setOnShown(evento -> {
        tpc.ajustarElementosJanela();
      });
        
          Scene cena = new Scene(root);
           telaCriarProjeto.setTitle("Tela Criar Projeto");
            telaCriarProjeto.setMaximized(true);
        telaCriarProjeto.setScene(cena);
        telaCriarProjeto.show();
        stageEscolherProjeto.close();
        
         }
    }catch (IOException e) {
        mostrarAviso("Erro","Falha ao abrir a tela de criação de projeto");
    }
        System.out.println("Coordenador ao clicar em Criar Projeto: " + coordenador);
    }
         
  
    //--------------------------*SETs*-------------------------------//
     public void setStage(Stage stageEscolherProjeto){
            this.stageEscolherProjeto= stageEscolherProjeto;
        
        
           }
    
    
    
  public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
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
  
   public void setBolsista(Bolsista bolsista) {
        this.bolsista = bolsista;
    }
   
   
   //-----------------------------*Metodos*-------------------------------//
 
 public void OnClickProjeto() throws SQLException { 
    vboxbutton.getChildren().clear(); // Limpa os botões antes de adicionar novos

    try {
        ProjetoDAO dao = new ProjetoDAO();
        List<Projeto> projetos;

        if (coordenador != null) {
            projetos = dao.selecionarProjeto(coordenador);
            System.out.println("Projetos do Coordenador encontrados: " + projetos.size());
        } else if (bolsista != null) {
            projetos = dao.selecionarProjetoB(bolsista);
            System.out.println("Projetos do Bolsista encontrados: " + projetos.size());
        } else {
            System.out.println("Erro: Usuário não é Coordenador nem Bolsista.");
            return;
        }

        for (Projeto projeto : projetos) {
            Button btn = new Button(projeto.getTitulo());
            btn.setOnAction(event -> {
                try {
                    if (coordenador != null) {
                        abriProjeto(projeto);
                    } else if (bolsista != null) {
                        abriProjetoB(projeto);
                    }
                } catch (IOException e) {
                    mostrarAviso("Erro", "Erro ao carregar projeto");
                }
            });
            vboxbutton.getChildren().add(btn);
        }
    } catch (SQLException e) {
        mostrarAviso("Erro", "Erro ao carregar os projetos");
        e.printStackTrace();
    }
}

 private void abriProjeto(Projeto projeto) throws MalformedURLException, IOException{
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
            stageEscolherProjeto.close();
 }

    

    private void abriProjetoB(Projeto projeto) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
         FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalBolsistaController tpb = loader.getController();    
            tpb.setStagePrincipal(stagePrincipal);
            tpb.setBolsista(bolsista);
            tpb.setProjeto(projeto);
            
            stagePrincipal.setOnShown(evento -> {
            tpb.ajustarElementosJanela(bolsista,projeto);
        });
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal bolsista");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
            stageEscolherProjeto.close();
    }
  
   
 
}
    

