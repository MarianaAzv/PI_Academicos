package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Artigo;
import model.Projeto;

public class AtualizarArtigoController {

    private Projeto projeto;
    private Artigo artigo;
    private Stage stageAtualizarArtigo;
    
    File arquivoPDF = artigo.getArquivo();
    private final String DIRETORIO_PDFS = Paths.get(System.getProperty("user.home"), "pdfs_baixados").toString();
    
    public void initialize() {
        File diretorio = new File(DIRETORIO_PDFS);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
      }
    
    
    @FXML
    private Button btnExcluir;

    @FXML
    private Label lblAbrirArquivo;

    @FXML
    private Label lblAutores;

    @FXML
    private Label lblData;

    @FXML
    private Label lblResumo;

    @FXML
    private Label lblTitulo;

    @FXML
    void onClickAbrirArquivo(MouseEvent event) {

        if (arquivoPDF != null) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(arquivoPDF);
                } else {
                    System.err.println("A funcionalidade de desktop não é suportada.");
                }
            } catch (IOException e) {
                System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum arquivo selecionado para abrir.");
        }
    }

    @FXML
    void onClickExcluir(ActionEvent event) {

    }

    @FXML
    void onEnterArquivo(MouseEvent event) {

         lblAbrirArquivo.setStyle("-fx-text-fill: blue" );
    }

    @FXML  
     void onExitArquivo(MouseEvent event) {
         
         lblAbrirArquivo.setStyle("-fx-text-fill: black" );
         
     }
    
    public void setStage(Stage stageAtualizarArtigo){
        this.stageAtualizarArtigo = stageAtualizarArtigo;
    }
    
    public void setProjeto(Projeto projeto){
        this.projeto = projeto;
    }
    public void setArtigo(Artigo artigo){
        this.artigo = artigo;
        lblTitulo.setText(artigo.getTitulo());
        lblAutores.setText(artigo.getAutores());
        lblData.setText(artigo.getDataPublicacao().toString());
        lblResumo.setText(artigo.getResumo());
        lblAbrirArquivo.setText(artigo.getArquivo().getName());
    }

}

