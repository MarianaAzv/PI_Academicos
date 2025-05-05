package controller;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
         
      //incluir(txtNomedoProjeto.getText(),CBcampus.getCampus().getIdCampus);
       
       
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
    
   // void incluir(String Titulo, String Resumo,int campus,LocalDate dataInicio,LocalDate DataFim throws SQLException {
     //   Usuario usuario = new Usuario(cpf, nome, apelido, email, senha);
       // Coordenador coordenador = new Coordenador(siape, formacao);
       // int repetido = new CoordenadorDAO().validarApelido(apelido,0);
       // if(repetido>0){
         //   mostrarAviso("Nome de usuário indisponível","Este nome de usuário já está sendo usado");
           
       // }
       // else{
       // new CoordenadorDAO().cadastrarUsuarioCoordenador(usuario,coordenador);
       // mostrarConfirmacao("Usuário cadastrado","O usuário foi registrado no sistema com sucesso!");
       // stageCadastroCoordenador.close();
        //}
    //}
    

}



