package controller;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Postagem;
import model.PostagemDAO;
import model.Projeto;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarPostagemController {
    
    private Stage stageAtualizarPostagem;
    private Projeto projeto;
    private Postagem postagem;
    private Image image;
    

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private ImageView imageViewFoto;

    @FXML
    private Label lblNomeProjeto;

    @FXML
    private TextArea txtLegenda;

    @FXML
    void onClickAtualizar(ActionEvent event) {

    }

    @FXML
    void onClickExcluir(ActionEvent event) throws SQLException {
        
        PostagemDAO postagemDAO = new PostagemDAO();
        postagemDAO.deletarPostagem(postagem);
        mostrarConfirmacao("Postagem excluída","A postagem foi excluída.");
        stageAtualizarPostagem.close();

    }

    @FXML
    void onClickFoto(MouseEvent event) {

    }
    
    public void setStage(Stage stageAtualizarPostagem){
        this.stageAtualizarPostagem = stageAtualizarPostagem;
    }
    
    public void setProjeto(Projeto projeto){
        this.projeto = projeto;
    }
    
    public void setPostagem(Postagem postagem){
        this.postagem = postagem;
        byte[] conteudoFoto = postagem.getFoto().getDadosImagem();
                if(conteudoFoto!=null){
                   try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                            image = new Image(bis); // Converte byte[] para Image AQUI
                        } catch (Exception e) {
                            System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                            // precisa definir uma imagem padrao de erro
                        }
                }
        imageViewFoto.setImage(image);
        txtLegenda.setText(postagem.getLegenda());
        
    }

}

