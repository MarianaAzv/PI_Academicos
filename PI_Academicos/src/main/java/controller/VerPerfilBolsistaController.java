package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bolsista;
import model.Projeto;

public class VerPerfilBolsistaController {
    
    private Stage stageVerPerfil;
    private Bolsista bolsista;
    
    Projeto projeto;
    void setProjeto(Projeto projeto) {
       this.projeto = projeto;
    }

    @FXML
    private Text TxtNomeProjetoBarra;

    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnOutrosProjetos;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerPerfil;

    @FXML
    private ImageView imgFotoBolsista;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgProjetoBarra;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblCPFBolsista;

    @FXML
    private Label lblCurso;

    @FXML
    private Label lblCursoBolsista;

    @FXML
    private Label lblData;

    @FXML
    private Label lblDataFimBols;

    @FXML
    private Label lblDataInicioBols;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblEmailBolsista;

    @FXML
    private Label lblFimDaBolsa;

    @FXML
    private Label lblInicioDaBolsa;

    @FXML
    private Label lblMatricula;

    @FXML
    private Label lblMatriculaBols;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNomeBol;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSenhaBolsista;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblUsuarioBolsista;

    public void setBolsista(Bolsista bol) {
        

        
        this.bolsista = bol;
          if (bolsista != null) { // Garantindo que bolsista não seja null antes de atribuir os valores
            lblNomeBol.setText(bolsista.getNome());
            lblUsuarioBolsista.setText(bolsista.getApelido());
            lblCPFBolsista.setText(String.valueOf(bolsista.getCpf()));
            lblCursoBolsista.setText(bolsista.getCurso());
            lblSenhaBolsista.setText(bolsista.getSenha());
            lblEmailBolsista.setText(bolsista.getEmail());
            lblMatriculaBols.setText(String.valueOf(bolsista.getMatricula()));
            
           //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // String DataInicio = bolsista.getDataInicio().format(formatter);
    // lblDataInicioBols.setText(DataInicio);
     

    //   String DataFim = bolsista.getDataFim().format(formatter);
    //  lblDataFimBols.setText(DataFim);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

              System.out.println(bolsista.getDataInicio());
            lblDataInicioBols.setText(bolsista.getDataInicio() != null ? bolsista.getDataInicio().format(formatter) : "Data não disponível");
             System.out.println(bolsista.getDataInicio().format(formatter));
           lblDataFimBols.setText(bolsista.getDataFim() != null ? bolsista.getDataFim().format(formatter) : "Data não disponível");
            
        }
    }

    public void setStage(Stage stage) {
        this.stageVerPerfil = stage;
    }
    
    //******************* OnClicks ***************************************

    @FXML
    void onClickArtigo(ActionEvent event) throws IOException {
        abrirArtigo();
    }
    @FXML
    void onEnterArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void onExitArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color:  DBA5A5" );
    }
     //******************************************************************

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        abrirAtualizarPerfil();
    }
    @FXML
    void onEnterAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void onExitAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************

    @FXML
    void onClickOutrosProjetos(ActionEvent event) throws IOException, IOException {
        outrosProjetos();
    }
    @FXML
    void onEnterOutrosProjeto(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void onExitOutrosProjeto(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************

    @FXML
    void onClickPublicacao(ActionEvent event) throws IOException {
        abrirPublicacao();
    }
     @FXML
    void onEnterPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void onExitPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
        abrirTelaPrincipal();
    }
    @FXML
    void onEnterSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void onExitSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        //abrirVerPerfil();
    }
    @FXML
    void onEnterVerPerfil(MouseEvent event) {
        //btnVerPerfil.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void onExitVerPerfil(MouseEvent event) {
        //btnVerPerfil.setStyle("-fx-background-color:  DBA5A5" );
    }
   

    //******************* MÉTODOS ***************************************
    
    public void abrirVerPerfil() throws IOException {
        URL url = new File("src/main/java/view/VerPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageVerPerfil = new Stage();

        VerPerfilBolsistaController vpb = loader.getController();
        vpb.setBolsista(bolsista);
        vpb.setProjeto(projeto);
        vpb.setStage(stageVerPerfil); 

        Scene cena = new Scene(root);
        stageVerPerfil.setTitle("Perfil Bolsista");
        stageVerPerfil.setScene(cena);
        stageVerPerfil.setMaximized(true);

        stageVerPerfil.show();
        stageVerPerfil.close(); 
    }

    
    public void abrirAtualizarPerfil() throws IOException {
        URL url = new File("src/main/java/view/AtualizarPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageAtualizar = new Stage();
        AtualizarPerfilBolsistaController apb = loader.getController();
        apb.setBolsista(bolsista);
        apb.setProjeto(projeto);

        vpb.setBolsista(bolsista); // Garantindo que os dados sejam passados corretamente
        vpb.setProjeto(projeto);
        stage.setOnShown(evento -> {
            vpb.ajustarElementosJanela(bolsista,projeto);
        });

        Scene cena = new Scene(root);
        stageAtualizar.setTitle("Atualizar Perfil Bolsista");
        stageAtualizar.setMaximized(true);
        stageAtualizar.setScene(cena);
        stageAtualizar.show();
        stageVerPerfil.close();
    }

    
    public void abrirPublicacao() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarPostagem.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePostagem = new Stage();
        CadastrarPostagemController cpb = loader.getController();
        cpb.setStage(stagePostagem);

        Scene cena = new Scene(root);
        stagePostagem.setTitle("Bolsista Cadastro Postagem");
        stagePostagem.setMaximized(false);
        stagePostagem.setScene(cena);
        stagePostagem.show();
    }

   
    public void abrirArtigo() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarArtigo.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageArtigo = new Stage();
        CadastrarArtigoController cab = loader.getController();
        cab.setStage(stageArtigo);

        Scene cena = new Scene(root);
        stageArtigo.setTitle("Bolsista Cadastro Artigo");
        stageArtigo.setMaximized(false);
        stageArtigo.setScene(cena);
        stageArtigo.show();
    }
    @FXML
void onClickOutrosProjetos(ActionEvent event) {
    System.out.println("Outros Projetos click");
}

    
    public void outrosProjetos() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/EscolherProjeto.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        
        Stage stageProjetos = new Stage();
        
        EscolherProjetoController tpc = loader.getController();  
        tpc.setBolsista(bolsista);
        tpc.setStage(stageProjetos);
          
        Scene cena = new Scene(root);
        stageProjetos.setTitle("Outros projetos");
        stageProjetos.setMaximized(false);
        stageProjetos.setScene(cena);
        stageProjetos.show();
        stageProjetos.setOnShown(evento -> {
             try {
                 tpc.OnClickProjeto();
             } catch (SQLException ex) {
                 Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
      });
    }

    
    private void abrirTelaPrincipal() throws IOException {

        URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePrincipalBolsista = new Stage();

        TelaPrincipalBolsistaController tpb = loader.getController();
        tpb.setStagePrincipal(stagePrincipalBolsista);
        tpb.setProjeto(projeto);
        tpb.setBolsista(bolsista);

        Scene cena = new Scene(root);
        stagePrincipalBolsista.setTitle("Tela Principal Bolsista");
        stagePrincipalBolsista.setScene(cena);
        stagePrincipalBolsista.setMaximized(true);

        stagePrincipalBolsista.show();
        stageVerPerfil.close();

    }
      void ajustarElementosJanela(Bolsista bolsista, Projeto projeto) {
        this.bolsista = bolsista;
        this.projeto = projeto;
                 txtNomeUsuario.setText(bolsista.getNome());//falta foto
      }

}