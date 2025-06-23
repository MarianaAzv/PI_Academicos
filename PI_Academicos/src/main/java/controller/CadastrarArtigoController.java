package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Artigo;
import model.ArtigoDAO;
import model.Projeto;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class CadastrarArtigoController {

    Stage stageArtigo;
    File arquivoPDF = null;

    void setStage(Stage stageArtigo) {
        this.stageArtigo = stageArtigo;
    }
    Projeto projeto;

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @FXML
    private Button btnPDF;

    @FXML
    private Button btnPostar;

    @FXML
    private DatePicker dataPublicacao;

    @FXML
    private TextArea txtAutores;

    @FXML
    private TextField txtNOmeArtigo;

    @FXML
    private TextArea txtPalavrasChave;

    @FXML
    private TextArea txtResumo;

    @FXML
    void onClickPDF(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.pdf"));
        arquivoPDF = fileChooser.showOpenDialog(btnPDF.getScene().getWindow());
        //lblAbrirArquivo.setText(arquivoPDF.getName());
    }

    @FXML
    void onClickPostar(ActionEvent event) throws IOException, SQLException {

        int idProjeto = projeto.getIdProjeto();
        String titulo = txtNOmeArtigo.getText();
        String resumo = txtResumo.getText();
        String autores = txtAutores.getText();

        if (arquivoPDF != null) {
            Artigo artigo = new Artigo(idProjeto, titulo, resumo, autores, arquivoPDF, dataPublicacao.getValue());
            new ArtigoDAO().salvarArtigo(artigo);
            mostrarConfirmacao("Artigo cadastrado", "O artigo foi cadastrado no sistema.");
            stageArtigo.close();
        } else {
            mostrarAviso("Arquivo PDF não escolhido", "É necessário selecionar um arquivo PDF para completar a ação.");
        }

    }

}
