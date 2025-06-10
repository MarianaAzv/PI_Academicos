package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class RecuperarSenhaController {

    @FXML
    private Button btnEnviar;

    @FXML
    private TextField txtEmailUsuario;

    
    public static void enviarEmail(String destinatario, String assunto, String mensagem) {
        final String remetente = "seuemail@gmail.com"; // Seu e-mail
        final String senha = "sua_senha"; // Sua senha ou App Password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);
            message.setText(mensagem);
            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        enviarEmail("destinatario@gmail.com", "Recuperação de Senha", "Aqui está o seu código de recuperação: 123456");
    }//ERRO

}
