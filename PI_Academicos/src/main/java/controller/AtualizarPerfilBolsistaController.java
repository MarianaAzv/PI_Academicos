package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
//import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bolsista;
import model.BolsistaDAO;
import util.AlertaUtil;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarPerfilBolsistaController {

    private Stage stageAtualizarBolsista;
    private Bolsista bolsista;
   private BolsistaDAO bolsistaDAO = new BolsistaDAO();
    private boolean ativa = true;

    @FXML
    private Text txtNomeUsuario;

    @FXML
    private Button btnAtualizarPerfil, btnSair;

    @FXML
    private Label lblCPF, lblCurso, lblEmail, lblMatricula, lblNome; // lblDataInicio, lblDataFim;

    @FXML
    private TextField txtCPF, txtCurso, txtEmail, txtMatricula, txtNome, txtSenha, txtUsuario; //txtDataInicio, txtDataFim;


    public void setStage(Stage stageAtualizarBolsista) {
        this.stageAtualizarBolsista = stageAtualizarBolsista;
    }

    public void setBolsista(Bolsista bol) {
        if (bol != null) {
            this.bolsista = bol;
            txtNome.setText(bolsista.getNome());
            txtUsuario.setText(bolsista.getApelido());
            txtCPF.setText(String.valueOf(bolsista.getCpf()));
            txtCurso.setText(bolsista.getCurso());
            txtSenha.setText(bolsista.getSenha());
            txtEmail.setText(bolsista.getEmail());
            txtMatricula.setText(String.valueOf(bolsista.getMatricula()));
      //      txtDataInicio.setText(bolsista.getDataInicio().toString());
         //   txtDataFim.setText(bolsista.getDataFim().toString());
        } else {
            mostrarAviso("Erro", "Bolsista não encontrado.");
        }
    }

    void atualizarBolsista(int id, long cpf, String nome, String apelido, String email, String senha, boolean ativa, long matricula, String curso) throws SQLException {// LocalDate dataInicio, LocalDate dataFim, boolean acessoPostagens, boolean acessoArtigos
        Bolsista bolsista = new Bolsista(id, cpf, nome, apelido, email, senha, ativa, matricula, curso);//dataInicio, dataFim, acessoPostagens, acessoArtigos

        
        int repetido = bolsistaDAO.validarApelido(apelido, id);

        if (repetido > 0) {
            mostrarAviso("Nome de usuário indisponível", "Este nome de usuário já está sendo usado.");
        } else {
            
         bolsistaDAO.atualizarBolsista(bolsista);
        mostrarConfirmacao("Usuário alterado","O usuário foi alterado com sucesso!");
        }
        
    }

    @FXML
    void onClickAtualizar(ActionEvent event) throws SQLException {
        try {
               // if (txtCPF.getText().isEmpty() || txtMatricula.getText().isEmpty() || txtCurso.getText().isEmpty() {//|| txtDataInicio.getText().isEmpty() {//|| txtDataFim.getText().isEmpty()) {
              //  mostrarAviso("Campos obrigatórios", "Todos os campos devem estar preenchidos.");
             //   return;
            //   }

            Long cpf = Long.parseLong(txtCPF.getText());
            Long matricula = Long.parseLong(txtMatricula.getText());
      
            atualizarBolsista(
                bolsista.getId(),cpf,txtNome.getText(),txtUsuario.getText(),txtEmail.getText(),txtSenha.getText(),ativa,matricula,txtCurso.getText());
               //    LocalDate dataInicio = LocalDate.parse(txtDataInicio.getText());
              //     LocalDate dataFim = LocalDate.parse(txtDataFim.getText());
             // dataInicio,
            //dataFim,                      
        } catch (NumberFormatException e) {
            mostrarAviso("Erro", "CPF, matrícula e datas devem estar em formatos válidos.");
        }
    }

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
      //  System.out.println("Fechando a tela...");
       // if (stageAtualizarBolsista != null) {
          //  stageAtualizarBolsista.close();
        //   Bolsista bolsista = null;
        if (bolsista != null) {
            URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage stage = new Stage();
            TelaPrincipalBolsistaController tpb = loader.getController();

            Scene cena = new Scene(root);
            stage.setTitle("Perfil Bolsista");
            stage.setScene(cena);
            stage.setMaximized(true);
            stage.show();
            stageAtualizarBolsista.close();

        } else {
            AlertaUtil.mostrarErro("Erro", "Usuário não encontrado ou inválido.");
        }
   // }
     //   }
    }
    
    

    @FXML
    void onClickVerPerfil(ActionEvent event) {

    }
    
    

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) {

    }
    
      @FXML
    void onClickArtigo(ActionEvent event) {

    }


    @FXML
    void onClickOutrosProjetos(ActionEvent event) {

    }

    @FXML
    void onClickPublicacao(ActionEvent event) {

    }


    @FXML
    void onClickVerProjeto(ActionEvent event) {

    }

   
    

}