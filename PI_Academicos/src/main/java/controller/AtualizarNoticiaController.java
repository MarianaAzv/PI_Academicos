package controller;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Administrador;
import model.Noticia;
import model.NoticiaDAO;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarNoticiaController {

    private Stage stageAtualizarNoticia;
    private Administrador adm;
    private Noticia noticia;
    private Image image;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private ImageView imageViewFoto;

    @FXML
    private TextArea txtLegenda;

    @FXML
    private TextField txtTituloNoticia;

    @FXML
    void onClickAtualizar(ActionEvent event) {

    }

    @FXML
    void onClickExcluir(ActionEvent event) throws SQLException {

        NoticiaDAO noticiaDAO = new NoticiaDAO();
        noticiaDAO.deletarNoticia(noticia);
        mostrarConfirmacao("Notícia excluída", "A noticia foi excluída.");
        stageAtualizarNoticia.close();

    }

    @FXML
    void onClickFoto(MouseEvent event) {

    }

    public void setStage(Stage stageAtualizarNoticia) {
        this.stageAtualizarNoticia = stageAtualizarNoticia;
    }

    public void setAdministrador(Administrador adm) {
        this.adm = adm;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
        byte[] conteudoFoto = noticia.getFoto().getDadosImagem();
        if (conteudoFoto != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                image = new Image(bis); // Converte byte[] para Image AQUI
            } catch (Exception e) {
                System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                // precisa definir uma imagem padrao de erro
            }
        }
        imageViewFoto.setImage(image);
        txtTituloNoticia.setText(noticia.getTitulo());
        txtLegenda.setText(noticia.getTexto());

    }

}
