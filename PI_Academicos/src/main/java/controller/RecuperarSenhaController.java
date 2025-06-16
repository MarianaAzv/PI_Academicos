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

    String assunto = "Recuperação de Senha";
    String mensagem = "Aqui está seu código de recuperação: 123456"; // substitua por geração dinâmica se quiser

    enviarEmail(destinatario, assunto, mensagem); // Aqui está a chamada direta!
    mostrarAlerta(AlertType.INFORMATION, "Sucesso", "E-mail enviado para: " + destinatario);
}


    public static void enviarEmail(String destinatario, String assunto, String mensagem) {
          // Configuração do servidor SMTP
        String host = "smtp.gmail.com";  // Exemplo: SMTP do Gmail
        final String user = "jaquelinebrandao49@gmail.com";  // Seu e-mail
        final String password = "08678506555";  // Sua senha ou senha de app do Gmail

          // Definir as propriedades do servidor SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Ativar TLS
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");  // Porta SMTP para TLS

         Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
              // Criar o objeto de mensagem de e-mail
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user)); // De quem é o e-mail
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("destinatario@dominio.com")); // Para quem é o e-mail
            message.setSubject("Assunto do E-mail");  // Assunto
            message.setText("Este é o corpo do e-mail");  // Corpo do e-mail

            // Enviar o e-mail
            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        enviarEmail("jaquelinemira@gmail.com", "Recuperação de Senha", "Aqui está oseu código de recuperação: 123456");
    }

    void setStage(Stage stageRecuperar) {
        this.stageRecuperar= stageRecuperar;
    }

    
}
