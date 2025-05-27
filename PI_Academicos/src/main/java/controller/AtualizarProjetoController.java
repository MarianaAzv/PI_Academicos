package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
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
import model.AreasConhecimentoDAO;
import model.Campus;
import model.CampusDAO;
import model.Coordenador;
import model.Projeto;
import model.ProjetoDAO;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarProjetoController {

     private Stage stageAtualizarProjeto;
     Projeto projeto;
     Campus campus;
     AreasConhecimento areaconhecimento;
     
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
      
           //IF da prorrogacao e atualizar a area 
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       LocalDate dI = LocalDate.parse(txtDatadeInicio.getText(), formatter);
       LocalDate dF= LocalDate.parse(txtDatadeFim.getText(), formatter);
       
         
           Campus campusnomeSelecionado = CBcampus.getValue();
           AreasConhecimento areacnhecimentoselecionado = CBcategoria.getValue();
            this.areaconhecimento= areacnhecimentoselecionado;
          
          
     
          //Rever o null e o set em prorrogacao
         LocalDate prorrogacao = null;
if (!txtProrrogacao.getText().isEmpty()) {
    prorrogacao = LocalDate.parse(txtProrrogacao.getText(), formatter);
}

atualizarProjeto(projeto.getIdProjeto(), txtNomedoProjeto.getText(), txtResumo.getText(),
                 campusnomeSelecionado, txtEdital.getText(), dI, dF, prorrogacao, areaconhecimento);
        //else {
//            atualizarProjetoSEmProrrogacao(projeto.getIdProjeto(),txtNomedoProjeto.getText(),txtResumo.getText(),campusnomeSelecionado,txtEdital.getText(),dI,dF,areaconhecimento);
//        }


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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String DataInicio = projeto.getDataInicio().format(formatter);
      txtDatadeInicio.setText(DataInicio);
     

       String DataFim = projeto.getDataFim().format(formatter);
      txtDatadeFim.setText(DataFim);
      //IF se a prorrogacao for null le nao set
      
      
  //  String p = String.valueOf(projeto.getProrroacao());
      
      if(projeto.getProrroacao() != null){
              String prorroga = projeto.getProrroacao().format(formatter);  
      txtProrrogacao.setText(prorroga);
      } else {
          txtProrrogacao.clear();
      }
      
      txtCoordenador.setText(projeto.getCocoordenadores());
      //Falta combo box
      
        if (projeto.getCampus() != null) {
        CBcampus.setValue(projeto.getCampus());
        System.out.print("Set campus atualizar");
    }
        if(projeto.getAreaConhecimento() !=null){
              CBcategoria.setValue(areaconhecimento);
        }
      
      
    }
  void atualizarProjeto(int idProjeto,String titulo,String resumo, Campus campus, String edital,LocalDate dataInicio,LocalDate dataFim,LocalDate prorrogacao,AreasConhecimento areaconhecimento  ) throws SQLException{
        
         ProjetoDAO pdao =new ProjetoDAO();
         //Falta SET AQUI para enviar para o banco de dados 
         projeto.setTitulo(titulo);
         projeto.setResumo(resumo);
         projeto.setCampus(campus);
         projeto.setEdital(edital);
         projeto.setDataInicio(dataInicio);
         projeto.setDataFim(dataFim);
         projeto.setProrroacao(prorrogacao);
         projeto.setAreaConhecimento(areaconhecimento);
         
        pdao.atualizarProjeto(projeto);
        
      // pdao.AtualizarAreaProjeto(projeto);
       
       mostrarConfirmacao("Projeto alterado","O projeto foi alterado com sucesso!");
        
  }
  
//  void atualizarProjetoSEmProrrogacao(int idProjeto,String titulo,String resumo, Campus campus, String edital,LocalDate dataInicio,LocalDate dataFim,AreasConhecimento areaconhecimento) throws SQLException{
//   
//      ProjetoDAO pdao =new ProjetoDAO();
//      
//      projeto.setTitulo(titulo);
//      projeto.setResumo(resumo);
//      projeto.setCampus(campus);
//      projeto.setEdital(edital);
//      projeto.setDataInicio(dataInicio);
//      projeto.setDataFim(dataFim);
//      projeto.setAreaConhecimento(areaconhecimento);
//        pdao.atualizarProjetoSEmProrrogacao(projeto);
//        
//     //  pdao.AtualizarAreaProjeto(projeto);
//       
//       mostrarConfirmacao("Projeto alterado","O projeto foi alterado com sucesso!");
//      
//  }
  
  public void ajustarElementosJanela(){
  //ArrayList para set dos nomes dos campus no combo box de campus
  try{
      CampusDAO cdao = new CampusDAO();
     List<Campus> listCampus = cdao.buscarCampus();
    
     CBcampus.getItems().addAll(listCampus);
     
      if (projeto != null && projeto.getCampus() != null) {
            CBcampus.setValue(projeto.getCampus());
            System.out.print("Set atualizar");
        }
      
     
       AreasConhecimentoDAO acdao = new AreasConhecimentoDAO();
     List<AreasConhecimento> listCategorias = acdao.buscarCategorias();
    
    CBcategoria.getItems().addAll(listCategorias);
    
     if (projeto != null && projeto.getAreaConhecimento()!= null) {
           CBcategoria.setValue(projeto.getAreaConhecimento());
            System.out.print("Set atualizar area de conhecimento");
        }
    
  } catch(SQLException e){
    
        mostrarAviso("Banco de Dados","A falha de comunicação entre o sistema e o Banco");
  }
   }
}
