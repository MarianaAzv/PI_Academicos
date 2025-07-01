package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
    Projeto projeto;

    public CadastrarPostagemController() {
        postagemDAO = new PostagemDAO();
    }

    void setStage(Stage stagePostagem) {
        this.stagePostagem = stagePostagem;
        //carregarImagemPadrao();
        Image image = null;
        byte[] conteudoFoto = carregarImagemPadrao();
        if (conteudoFoto != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                image = new Image(bis); // Converte byte[] para Image AQUI
            } catch (Exception e) {
                System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                // precisa definir uma imagem padrao de erro
            }
        }
        
        imagem.setImage(image);
    }
    

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
        lblNomeProjeto.setText(projeto.getTitulo());
    }

    @FXML
    private ImageView btnMaisFotos;
    
    @FXML
    private ImageView imagem;

    @FXML
    private Button btnPostar;

    @FXML
    private Label lblImagem;

    @FXML
    private Label lblNomeProjeto;

    @FXML
    private TextArea txtLegenda;

    @FXML
    void onClickImagem(MouseEvent event) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de Imagem", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        arquivoSelecionado = fileChooser.showOpenDialog(imagem.getScene().getWindow());

        if (arquivoSelecionado != null) {
        String urlImagem = arquivoSelecionado.toURI().toURL().toString();
            Image fotoEscolhida = new Image(urlImagem);
            imagem.setImage(fotoEscolhida);
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
    
    public byte[] carregarImagemPadrao() {
        try (InputStream is = getClass().getResourceAsStream("/imagens/ImagemPost.png"); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            return baos.toByteArray();

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

}
