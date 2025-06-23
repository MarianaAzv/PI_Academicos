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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Bolsista;
import model.Foto;
import model.Postagem;
import model.PostagemDAO;
import model.Projeto;

public class CadastrarPostagemController {

    Stage stagePostagem;
    private File arquivoSelecionado = null;
    PostagemDAO postagemDAO;

    public CadastrarPostagemController() {
        postagemDAO = new PostagemDAO();
    }

    void setStage(Stage stagePostagem) {
        this.stagePostagem = stagePostagem;
    }
    Projeto projeto;

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
        lblNomeProjeto.setText(projeto.getTitulo());
    }

    @FXML
    private ImageView btnMaisFotos;

    @FXML
    private Button btnPostar;

    @FXML
    private Label lblImagem;

    @FXML
    private Label lblNomeProjeto;

    @FXML
    private TextArea txtLegenda;

    @FXML
    void onClickImagem(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de Imagem", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        arquivoSelecionado = fileChooser.showOpenDialog(lblImagem.getScene().getWindow());

        if (arquivoSelecionado != null) {
            //lblCaminhoArquivo.setText(arquivoSelecionado.getAbsolutePath());
        } else {
            //lblCaminhoArquivo.setText("Nenhum arquivo foi selecionado");
        }
    }

    @FXML
    void onClickMaisFotos(MouseEvent event) {

    }

    @FXML
    void onClickPostar(ActionEvent event) throws IOException, SQLException {

        byte[] conteudoImagem = Files.readAllBytes(arquivoSelecionado.toPath());
        Foto foto = new Foto(conteudoImagem);
        Postagem postagem = new Postagem(projeto.getIdProjeto(), foto, txtLegenda.getText());

        try {
            postagemDAO.cadastrarPostagem(postagem, foto);
        } catch (IOException ex) {
            Logger.getLogger(TelaNoticiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stagePostagem.close();
    }

}
