package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.AreasConhecimento;
import model.AreasConhecimentoDAO;
import model.Campus;
import model.CampusDAO;
import model.Coordenador;
import model.CoordenadorDAO;
import model.Projeto;
import model.ProjetoDAO;
import model.Solicitacao;
import model.SolicitacaoDAO;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class CriarProjetoController {
    
    private Stage stageCriarProjeto;
    File arquivoPDF=null;
    Coordenador coordenador;
    
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
        
       if(txtNomedoProjeto.getText().isEmpty() || txtResumo.getText().isEmpty() ||txtEdital.getText().isEmpty() || txtCoordenador.getText().isEmpty() || txtDatadeInicio.getText().isEmpty() || txtDatadeFim.getText().isEmpty() ||  CBcampus.getValue() == null || CBcategoria.getValue() == null  ){
           mostrarAviso("Falta informacao","Por favor inserir todos os dados corretamente");
           
           return;
       } else{
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dI = LocalDate.parse(txtDatadeInicio.getText(), formatter);
        LocalDate dF= LocalDate.parse(txtDatadeFim.getText(), formatter);
       
        if(dI.isAfter(dF)){
            mostrarAviso("Datas","A data do final do projeto esta menor que a data de inicio do projeto");
            return;
        } else{
            Campus campusnomeSelecionado = CBcampus.getValue();
            
            incluir(txtNomedoProjeto.getText(),txtResumo.getText(),campusnomeSelecionado,txtEdital.getText(),dI,dF,null,true,coordenador.getId());
        }
       }
     } catch(SQLException e){
          mostrarAviso("Falha","A falha em cadastrar esse projeto");
     } catch(DateTimeParseException e){
          mostrarAviso("Falha","O formato da data de inicio ou de Fim nao esta no padrao normal");
     }

    }
    
    @FXML
    void OnClickNaoBolsista(ActionEvent event) {
        // Mudar a cor do botao
 System.out.print("Nao ha Bolsista");
 // Se o usuario clicou no outro botao entao muda a cor para normal 
 btnnaoBolsista.setStyle("-fx-text-fill: gray; -fx-background-color: DBA5A5;");
    }

    @FXML
    void OnClickNaoCocoordenador(ActionEvent event) {
System.out.print("Nao ha cordenador");
 btnNaococoordenador.setStyle("-fx-text-fill: gray; -fx-background-color: DBA5A5;");

    }

    @FXML
    void OnClickPDF(ActionEvent event) throws IOException {

        //adicao de janela para escolher arquivo pdf 
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Text Files", "*.pdf"));
        //arquivoPDF = fileChooser.showOpenDialog(stageCriarProjeto); 
        arquivoPDF = fileChooser.showOpenDialog(btnPDF.getScene().getWindow());
        /*if (arquivoPDF != null) {
            Desktop.getDesktop().open(arquivoPDF);
                System.out.println(arquivoPDF.getPath());
                String caminhoArquivo = arquivoPDF.getPath();
                FileInputStream fileInputStream = new FileInputStream(caminhoArquivo);
                
                 byte[] conteudoPDF = fileInputStream.readAllBytes();
                 
                 //instrucao.setString(1, "nome_do_arquivo.pdf"); // Substitui por um nome de arquivo válido
                 //instrucao.setBytes(2, conteudoPDF);
            }*/
        
        if (arquivoPDF != null) {
            try {
               // byte[] conteudoPDF = Files.readAllBytes(arquivoPDF.toPath());
                //String nomeArquivo = arquivoPDF.getName();
                
                Usuario usuario = new Usuario();
                usuario.setId(coordenador.getId());
                Solicitacao solicitacao = new Solicitacao(usuario, arquivoPDF);
                new SolicitacaoDAO().salvarPDF(solicitacao);
                System.out.println("Arquivo PDF salvo no banco de dados.");
                // Adicione aqui feedback para o usuário (ex: um Label informando o sucesso)
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo PDF: " + e.getMessage());
                // Adicione aqui tratamento de erro para o usuário
            }
        }
        
        
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
           btnSimBolsista.setStyle("-fx-text-fill: gray; -fx-background-color: DBA5A5;");
           
    }

    @FXML
    void OnClickSimCocoordenador(ActionEvent event) throws MalformedURLException, IOException {
        
URL url = new File("src/main/java/view/CadastroCocoordenadorCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage TelaCadastroCocoordenadorCoordenador = new Stage();
            
            CadastroCocoordenadorCoordenadorController cccc = loader.getController();
            
           cccc.setStage(TelaCadastroCocoordenadorCoordenador); 
          
        
            Scene cena = new Scene(root);
            TelaCadastroCocoordenadorCoordenador.setTitle("Cadastro Cocoordenador");
            TelaCadastroCocoordenadorCoordenador.setScene(cena);
           
            
        TelaCadastroCocoordenadorCoordenador.show();
           
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
 void incluir(String titulo,String resumo, Campus campus, String edital,LocalDate dataInicio,LocalDate dataFim,LocalDate prorrogacao, boolean emAndamento,int id) throws SQLException {
       Projeto projeto = new Projeto(titulo,resumo,campus,edital,dataInicio,dataFim,prorrogacao,emAndamento);
      
    projeto.setEmAndamento(true);
      
        ProjetoDAO pdao =new ProjetoDAO();
        pdao.cadastraprojeto(projeto,id);
       
        mostrarConfirmacao("Projeto cadastrado","O projeto foi registrado no sistema com sucesso!");
        stageCriarProjeto.close();
       
    }
 
 void enviarSolicitacao(){
     
 }
   public  void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

}



