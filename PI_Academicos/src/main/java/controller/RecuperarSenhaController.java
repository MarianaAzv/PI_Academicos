
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.CodigoSenha;
import static util.AlertaUtil.mostrarAlerta;

public class RecuperarSenhaController {

    private Stage stageRecuperar;

    @FXML
    private Button btnEnviar;

    @FXML
    private TextField txtEmailUsuario;

    @FXML
    void onClickEnviar(ActionEvent event) {
        String destinatario = txtEmailUsuario.getText();

        if (destinatario == null || destinatario.isEmpty()) {
            mostrarAlerta(AlertType.ERROR, "Erro", "O campo de e-mail está vazio.");
            return;
        }

        String codigo = CodigoSenha.gerarCodigo(destinatario);
        if (codigo == null) {
            mostrarAlerta(AlertType.ERROR, "Erro", "E-mail não encontrado no sistema.");
            return;
        }

        String assunto = "Recuperação de Senha";
        String link = "http://localhost:8080/resetar-senha?codigo=" + codigo;
        String mensagem = """
            Olá!

            Recebemos uma solicitação de recuperação de senha.

            Clique no link abaixo para redefinir sua senha:

            %s

            Esse link é válido por 15 minutos.

            Caso você não tenha solicitado isso, apenas ignore este e-mail.
            """.formatted(link);

        enviarEmail(destinatario, assunto, mensagem);
        mostrarAlerta(AlertType.INFORMATION, "Sucesso", "Link de recuperação enviado para: " + destinatario);
    }

    public static void enviarEmail(String destinatario, String assunto, String mensagem) {
        String host = "smtp.gmail.com";
        final String user = "pipocaazeda64@gmail.com";
        final String password = "momj yhit mvin rtyc";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(assunto);
            message.setText(mensagem);
            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    void setStage(Stage stageRecuperar) {
        this.stageRecuperar = stageRecuperar;
    }
}