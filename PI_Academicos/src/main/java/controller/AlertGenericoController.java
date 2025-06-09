package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AlertGenericoController {
    
    private Stage stageAlerta;
    
    public void setStage(Stage stage) {
        this.stageAlerta = stage;
    }
    
    private String msg;
    private int tipo;
    
    void setMsg(String msg) {
        this.msg = msg;
        lblMsg.setText(msg);
    }

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnOK;
    
    @FXML
    private ImageView imgAlerta;
    
    @FXML
    private Label lblMsg;

    @FXML
    void onClickCancelar(ActionEvent event) {

    }

    @FXML
    void onClickOK(ActionEvent event) {

    }
    
    //setar img
    
    public boolean ok(){
        
        return true;
        
    }
   

}

