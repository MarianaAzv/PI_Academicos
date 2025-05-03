package controller;

import java.sql.SQLException;
//import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bolsista;
import model.BolsistaDAO;
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
    private TextField txtCPF, txtCurso, txtEmail, txtMatricula, txtNome, txtSenha, txtUsuario, txtDataInicio, txtDataFim;

    @FXML
    private CheckBox chkAcessoPostagens, chkAcessoArtigos;

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
            chkAcessoPostagens.setSelected(bolsista.getAcessoPostagens());
            chkAcessoArtigos.setSelected(bolsista.getAcessoArtigos());
        } else {
            mostrarAviso("Erro", "Bolsista não encontrado.");
        }
    }

    void atualizarBolsista(int id, long cpf, String nome, String apelido, String email, String senha, boolean ativa, long matricula, String curso, boolean acessoPostagens, boolean acessoArtigos) throws SQLException {// LocalDate dataInicio, LocalDate dataFim,
        Bolsista bolsista = new Bolsista(id, cpf, nome, apelido, email, senha, ativa, matricula, curso,  acessoPostagens, acessoArtigos);//dataInicio, dataFim,

        int repetido = bolsistaDAO.validarApelido(apelido, id);
        if (repetido > 0) {
            mostrarAviso("Nome de usuário indisponível", "Este nome de usuário já está sendo usado.");
        } else {
            try {
                bolsistaDAO.atualizarBolsista(bolsista);
                mostrarConfirmacao("Usuário alterado", "O usuário foi alterado com sucesso!");
            } catch (SQLException e) {
                mostrarAviso("Erro ao atualizar", "Ocorreu um erro ao atualizar os dados.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onClickAtualizar(ActionEvent event) {
        try {
           // if (txtCPF.getText().isEmpty() || txtMatricula.getText().isEmpty() || txtCurso.getText().isEmpty() {//|| txtDataInicio.getText().isEmpty() {//|| txtDataFim.getText().isEmpty()) {
              //  mostrarAviso("Campos obrigatórios", "Todos os campos devem estar preenchidos.");
             //   return;
         //   }

            Long cpf = Long.parseLong(txtCPF.getText());
            Long matricula = Long.parseLong(txtMatricula.getText());
        //    LocalDate dataInicio = LocalDate.parse(txtDataInicio.getText());
       //     LocalDate dataFim = LocalDate.parse(txtDataFim.getText());

            atualizarBolsista(
                bolsista.getId(),
                cpf,
                txtNome.getText(),
                txtUsuario.getText(),
                txtEmail.getText(),
                txtSenha.getText(),
                ativa,
                matricula,
                txtCurso.getText(),
               // dataInicio,
                //dataFim,
                chkAcessoPostagens.isSelected(),
                chkAcessoArtigos.isSelected()
            );

        } catch (NumberFormatException e) {
            mostrarAviso("Erro", "CPF, matrícula e datas devem estar em formatos válidos.");
        } catch (SQLException e) {
            mostrarAviso("Erro ao atualizar", "Erro ao executar a atualização.");
            e.printStackTrace();
        }
    }

    @FXML
    void onClickSair(ActionEvent event) {
        System.out.println("Fechando a tela...");
        if (stageAtualizarBolsista != null) {
            stageAtualizarBolsista.close();
        }
    }
}