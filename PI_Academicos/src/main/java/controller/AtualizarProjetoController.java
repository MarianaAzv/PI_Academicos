package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.AreasConhecimento;
import model.Campus;
import model.Coordenador;
import model.Projeto;
import model.ProjetoDAO;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarProjetoController {

     private Stage stageAtualizarProjeto;
     Projeto projeto;
        @FXML
    private ComboBox<Campus> CBcampus;
    @FXML
    private ComboBox<AreasConhecimento> CBcategoria;

    @FXML
    private Label LblDatadeinicio;

    @FXML
    private Button btnAdicionarBolsista;

    @FXML
    private Button btnAdicionarCocoordenador;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDesativarBolsista;

    @FXML
    private Button btnDesativarCocoordendor;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblBolsista;

    @FXML
    private Label lblCampus;

    @FXML
    private Label lblCategoria;

    @FXML
    private Label lblCocoordenador;

    @FXML
    private Label lblCoordenador;

    @FXML
    private Label lblDatadeFim;

    @FXML
    private Label lblEdital;

    @FXML
    private Label lblNomedoprojeto;

    @FXML
    private Label lblProrrogacao;

    @FXML
    private Label lblResumo;

    @FXML
    private Label lbltitulocriarprojeto;

 
    @FXML
    private TextField txtCoordenador;

    @FXML
    private TextField txtDatadeFim;

    @FXML
    private TextField txtDatadeInicio;

    @FXML
    private TextField txtEdital;

    @FXML
    private TextField txtNomedoProjeto;

    @FXML
    private TextField txtProrrogacao;

    @FXML
    private TextArea txtResumo;

    @FXML
    void OnClickAdicionarBolsista(ActionEvent event) {

    }

    @FXML
    void OnClickAdicionarCocoordenador(ActionEvent event) {

    }

    @FXML
    void OnClickAtualizar(ActionEvent event) {
        
       try{
      
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       LocalDate dI = LocalDate.parse(txtDatadeInicio.getText(), formatter);
       LocalDate dF= LocalDate.parse(txtDatadeFim.getText(), formatter);
         LocalDate PR= LocalDate.parse(txtProrrogacao.getText(), formatter);
         
           Campus campusnomeSelecionado = CBcampus.getValue();
          
          
        
     atualizarProjeto(projeto.getIdProjeto(),txtNomedoProjeto.getText(),txtResumo.getText(),campusnomeSelecionado,txtEdital.getText(),dI,dF,PR);
     
     
      } catch(SQLException e){
         mostrarAviso("Falha","A falha em atuaizar esse projeto");
     } 
       catch(DateTimeParseException e){
         mostrarAviso("Falha","O formato da data de inicio ou de Fim nao esta no padrao normal");
    }
    }
    

    @FXML
    void OnClickDesativarBolsista(ActionEvent event) {

    }

    @FXML
    void OnClickDesativarCocoordenador(ActionEvent event) {

    }
    
     public void setStage(Stage telaAtualizarProjeto){
        this.stageAtualizarProjeto = telaAtualizarProjeto;
        
    }
     
     public void setProjeto(Projeto pro) {
       this.projeto = pro;
       txtNomedoProjeto.setText(projeto.getTitulo());
       txtResumo.setText(projeto.getResumo());
       txtEdital.setText(projeto.getEdital());
      String DataInicio = String.valueOf(projeto.getDataInicio());
      txtDatadeInicio.setText(DataInicio);
       String DataFim = String.valueOf(projeto.getDataFim());
      txtDatadeInicio.setText(DataFim);
      String prorroga = String.valueOf(projeto.getProrroacao());
      txtProrrogacao.setText(prorroga);
      txtCoordenador.setText(projeto.getCocoordenadores());
      
    }
  void atualizarProjeto(int idProjeto,String titulo,String resumo, Campus campus, String edital,LocalDate dataInicio,LocalDate dataFim,LocalDate prorrogacao ) throws SQLException{
        
         ProjetoDAO pdao =new ProjetoDAO();
        pdao.atualizarProjeto(projeto);
       mostrarConfirmacao("Projeto alterado","O projeto foi alterado com sucesso!");
        
  }
}
