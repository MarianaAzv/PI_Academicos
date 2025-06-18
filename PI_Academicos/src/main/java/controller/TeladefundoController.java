package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Coordenador;
import model.Projeto;

public class TeladefundoController {

    private Stage stageTelafundo;
    Coordenador coordenador;
    Projeto projeto;

    //-----------------*SETs*--------------------//
    public void setStage(Stage stageTelafundo) {
        this.stageTelafundo = stageTelafundo;

    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public void Close() {
        stageTelafundo.close();
    }

}
