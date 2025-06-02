package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Administrador;
import model.Foto;
import model.Noticia;
import model.NoticiaDAO;

public class TelaNoticiaController {
    
    private Stage stageNoticia;
    private Administrador adm;
    NoticiaDAO noticiaDAO;
    private File arquivoSelecionado=null;
    
    public TelaNoticiaController() {
        noticiaDAO = new NoticiaDAO();
   }

    @FXML
    private Button btnPostar;

    @FXML
    private Label lblCaminhoArquivo;
    
    @FXML
    private Label lblImagem;

    @FXML
    private TextField txtTituloNoticia;

    @FXML
    private TextArea txtLegenda;

    @FXML
    void onClickImagem(MouseEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de Imagem", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        arquivoSelecionado = fileChooser.showOpenDialog(lblImagem.getScene().getWindow());
        
        if(arquivoSelecionado!=null){
             lblCaminhoArquivo.setText(arquivoSelecionado.getAbsolutePath());
        }
        else{
            lblCaminhoArquivo.setText("Nenhum arquivo foi selecionado");
        }
        
        
        
    }

    @FXML
    void onClickPostar(ActionEvent event) throws SQLException, IOException {
        
        //String link = arquivoSelecionado.toURI().toString();
        byte[] conteudoImagem = Files.readAllBytes(arquivoSelecionado.toPath());
        Foto foto = new Foto(conteudoImagem);
        Noticia noticia = new Noticia(adm.getId(), foto, txtTituloNoticia.getText(), txtLegenda.getText());
        
        try{
            noticiaDAO.cadastrarNoticia(noticia, foto);
        } catch (IOException ex) {
            Logger.getLogger(TelaNoticiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stageNoticia.close();
    }
    
    public void setStage (Stage stageNoticia){
        this.stageNoticia=stageNoticia;
    }
    
    public void setAdministrador(Administrador adm) {
        this.adm = adm;
    }

}
