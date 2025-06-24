package util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertaUtil {

    public static void mostrarAlerta(Alert.AlertType tipo,
            String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public static void mostrarErro(String titulo, String mensagem) {
        mostrarAlerta(Alert.AlertType.ERROR, titulo, mensagem);
    }

    public static void mostrarInformacao(String titulo, String mensagem) {
        mostrarAlerta(Alert.AlertType.INFORMATION, titulo, mensagem);
    }

    public static void mostrarAviso(String titulo, String mensagem) {
        mostrarAlerta(Alert.AlertType.WARNING, titulo, mensagem);
    }

    public static Optional<ButtonType> mostrarConfirmacao(String titulo,
            String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        return alerta.showAndWait();
    }

}
