package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AlertGenericoController {
    
    
   

    @FXML
    private Button btnOK;

    @FXML
    private ImageView imgAlerta;

    @FXML
    private Label lblMsg;

    private Stage stageAlerta;
    private boolean r;

    Image imgErro = new Image(getClass().getResource("/imagens/Erro.png").toExternalForm());
    Image imgAtencao = new Image(getClass().getResource("/imagens/Atencao.png").toExternalForm());
    Image imgCerto = new Image(getClass().getResource("/imagens/Certo.png").toExternalForm());
    private INotificacaoAlert contResp;

    public void setStage(Stage stage) {
        this.stageAlerta = stage;
    }

    private String msg;
    private int tipo;

    void setMsg(String msg) {
        this.msg = msg;
        lblMsg.setText(msg);
    }

    void setTipo(int tipo) {
        this.tipo = tipo;
        if (tipo == 1) {
            imgAlerta.setImage(imgErro);
        } else if (tipo == 2) {
            imgAlerta.setImage(imgAtencao);
        } else if (tipo == 3) {
            imgAlerta.setImage(imgCerto);
        }

    }

    @FXML
    void onClickOK(ActionEvent event) {
        contResp.btnOk();
        stageAlerta.close();
    }

    void setControllerResposta(INotificacaoAlert cont) {
        this.contResp = cont;
    }

}
