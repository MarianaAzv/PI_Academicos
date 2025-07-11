package principal;

import controller.TelaLoginController;
import java.io.File;
import javafx.application.Application;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
    
        stage.setTitle("Tela inicial de login");
        
        URL url = new File("src/main/java/view/TelaLogin.fxml").toURI().toURL();
        
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage telaLogin = new Stage();
        telaLogin.setMaximized(true);
        
        TelaLoginController tlc = loader.getController();
        tlc.setStage(telaLogin);
        
        telaLogin.setOnShown(event -> {
            try {
                tlc.abrirJanela();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        Scene cena = new Scene(root);
        telaLogin.setScene(cena);
        telaLogin.show();
    }

    public static void main(String[] args) {
        launch();
    }

}