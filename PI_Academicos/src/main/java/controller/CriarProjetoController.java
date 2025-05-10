package controller;



import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import model.CoordenadorDAO;
import model.Projeto;
import model.ProjetoDAO;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class CriarProjetoController {
    
    private Stage stageCriarProjeto;
    
     @FXML
    private ComboBox<Campus> CBcampus;

    @FXML
    private ComboBox<AreasConhecimento> CBcategoria;

    @FXML
    private Label LblDatadeinicio;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnNaococoordenador;

    @FXML
    private Button btnPDF;

    @FXML
    private Button btnSimBolsista;

    @FXML
    private Button btnSimCocoordenador;

    @FXML
    private Button btnnaoBolsista;

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
    private TextArea txtResumo;

    
    public void setStage(Stage telaCriarProjeto){
        this.stageCriarProjeto = telaCriarProjeto;
    }
    
     @FXML
    void OnClickEnviar(ActionEvent event) throws ParseException {
        //Para o combo box é pelo dao na inicializacao
        
        //Pois o metodo é so para cadastrar
      // Localdate para data
        //LocalDate;
       try{ 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dI = LocalDate.parse(txtDatadeInicio.getText(), formatter);
        LocalDate dF= LocalDate.parse(txtDatadeFim.getText(), formatter);
       
       Campus campusnomeSelecionado = CBcampus.getValue();
       
         
     incluir(txtNomedoProjeto.getText(),txtResumo.getText(),campusnomeSelecionado,txtEdital.getText(),dI,dF);
      
   
     } catch(SQLException e){
          mostrarAviso("Falha","A falha em cadastrar esse projeto");
     } catch(DateTimeParseException e){
          mostrarAviso("Falha","O formato da data de inicio ou de Fim nao esta no padrao normal");
     }

    }
    
    @FXML
    void OnClickNaoBolsista(ActionEvent event) {
 
    }

    @FXML
    void OnClickNaoCocoordenador(ActionEvent event) {

    }

    @FXML
    void OnClickPDF(ActionEvent event) {

    }

    @FXML
    void OnClickSimBolsista(ActionEvent event) throws MalformedURLException, IOException {
        
        
            URL url = new File("src/main/java/view/CadastroBolsistaCoodernador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage TelaCadastroBolsistaCoordenador = new Stage();
            
            CadastroBolsistaCoordenadorController cbcc = loader.getController();
            
           cbcc.setStage(TelaCadastroBolsistaCoordenador); 
          
        
            Scene cena = new Scene(root);
            TelaCadastroBolsistaCoordenador.setTitle("Cadastro Bolsista");
            TelaCadastroBolsistaCoordenador.setScene(cena);
           
            
           TelaCadastroBolsistaCoordenador.show();
            stageCriarProjeto.close();
    }

    @FXML
    void OnClickSimCocoordenador(ActionEvent event) {

    }
    
 public void ajustarElementosJanela(){
  //ArrayList para set dos nomes dos campus no combo box de campus
  try{
      CampusDAO cdao = new CampusDAO();
     List<Campus> listCampus = cdao.buscarCampus();
     CBcampus.getItems().clear();
     CBcampus.getItems().addAll(listCampus);
     
       AreasConhecimentoDAO acdao = new AreasConhecimentoDAO();
     List<AreasConhecimento> listCategorias = acdao.buscarCategorias();
     CBcategoria.getItems().clear();
    CBcategoria.getItems().addAll(listCategorias);
    
  } catch(SQLException e){
    
        mostrarAviso("Banco de Dados","A falha de comunicação entre o sistema e o Banco");
  }
   }
 void incluir(String titulo,String resumo, Campus campus, String edital,LocalDate dataInicio,LocalDate dataFim) throws SQLException {
       Projeto projeto = new Projeto(titulo,resumo,campus,edital,dataInicio,dataFim);
     
    projeto.setEmAndamento(true);
      
        ProjetoDAO pdao =new ProjetoDAO();
        pdao.cadastraprojeto(projeto);
       
        mostrarConfirmacao("Projeto cadastrado","O projeto foi registrado no sistema com sucesso!");
        stageCriarProjeto.close();
       
    }
    

}



