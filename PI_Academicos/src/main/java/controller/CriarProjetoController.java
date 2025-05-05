package controller;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Campus;
import model.Coordenador;
import model.CoordenadorDAO;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class CriarProjetoController {
    
    private Stage stageCriarProjeto;
    
     @FXML
    private ComboBox<String> CBcampus;

    @FXML
    private ComboBox<?> CBcategoria;

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
        
      
         // SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
       // Date dtf = formato.parse(txtDatadeFim.getText());
       // Date dti = formato.parse(txtDatadeInicio.getText());
         
    // incluir(txtNomedoProjeto.getText());
       
       
        // Localdate para data
        //LocalDate;
        
        //Para o combo box é pelo dao na inicializacao
        
        //Pois o metodo é so para cadastrar
   
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
    void OnClickSimBolsista(ActionEvent event) {
  
    }

    @FXML
    void OnClickSimCocoordenador(ActionEvent event) {

    }
    
 void ajustarElementosJanela(Campus campus){
     //Analisar se é campus ou se é projetos
     //ArrayList para set dos nomes dos campus no combo box de campus
     //  ArrayList[] campusnomes = new ArrayList[];
       
       //for(int i = 0;i> lenft;i++){
      //campusnomes.setText(campus.getNomeCampus());
    //  CBcampus= campusnome;
    //   }
   }
    

}



