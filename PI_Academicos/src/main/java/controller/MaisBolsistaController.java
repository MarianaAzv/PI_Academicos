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
import model.Projeto;

public class MaisBolsistaController {

    private Stage stageMaisBolsista;
    Projeto projeto;
    
    @FXML
    private Button BtnNao;

    @FXML
    private Button btnSim;

    @FXML
    private Label lblProjeto;
    
    @FXML
    private Label lblVocêdesejaadicionarmaisbolsistas;
    


    @FXML
    void OnClickNao(ActionEvent event) {

    }

    @FXML
    void OnClickSim(ActionEvent event) throws IOException {

        abrirJanelaCadastroBolsista();
        
    }
    
    public void setProjeto(Projeto projeto){
        this.projeto = projeto;
    }
    
    public void setStage(Stage stageMaisBolsista){
        this.stageMaisBolsista = stageMaisBolsista;
    }
    
    public void abrirJanelaCadastroBolsista() throws IOException{
       URL url = new File("src/main/java/view/CadastroBolsistaCoodernador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastroBolsista = new Stage();
            
            CadastroBolsistaCoordenadorController cbcc = loader.getController();
            //mbc.setCoordenador(coordenador);
            cbcc.setProjeto(projeto);
            cbcc.setStage(stageCadastroBolsista);
        
            Scene cena = new Scene(root);
            stageCadastroBolsista.setTitle("Adicionar bolsistas");
            stageCadastroBolsista.setScene(cena);
            //deixa a tela maximizada
            
            stageCadastroBolsista.show();
            stageMaisBolsista.close();
           
   }

}
