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
    private boolean r;
    
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
        r = false;
        getResultado(r);
        stageAlerta.close();
    }

    @FXML
    void onClickOK(ActionEvent event) {
        r = true;
        getResultado(r);
        stageAlerta.close();
    }
    
    //setar img
    
    public boolean getResultado(boolean r){
        return(r);
    }
    
   

}

