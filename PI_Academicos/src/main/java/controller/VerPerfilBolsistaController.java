package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bolsista;
import model.Projeto;

public class VerPerfilBolsistaController {
    
    private Stage stageVerPerfil;
    private Bolsista bolsista;

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
    private Button btnVerProjeto;

    @FXML
    private ImageView imgFotoBolsista, imgPerfil, imgProjeto;

    @FXML
    private Label lblCPF, lblCPFBolsista, lblCurso, lblDataFimBols, 
                  lblDataInicioBols, lblEmail, lblEmailBolsista, lblMatricula, lblMatriculaBols, 
                  lblNomeBol, lblUsuarioBolsista;
      @FXML
    private Label lblSenhaBolsista;
        @FXML
    private Label lblCursoBolsista;
    private Projeto projeto;

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

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/AtualizarPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stage = new Stage();

        AtualizarPerfilBolsistaController apb = loader.getController();
        apb.setBolsista(bolsista); // Passando o bolsista corretamente para a tela de atualização
        apb.setStage(stage);

        Scene cena = new Scene(root);
        stage.setTitle("Atualizar Perfil Bolsista");
        stage.setScene(cena);
        stage.setMaximized(true);

        stage.show();
        stageVerPerfil.close();
    }

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
       // URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
      //  FXMLLoader loader = new FXMLLoader(url);
      //  Parent root = loader.load();

       // Stage stagePrincipalBolsista = new Stage();

       // TelaPrincipalBolsistaController tpb = loader.getController();
       // tpb.setStagePrincipal(stagePrincipalBolsista);

       // Scene cena = new Scene(root);
       // stagePrincipalBolsista.setTitle("Tela Principal Bolsista");
       // stagePrincipalBolsista.setScene(cena);
       // stagePrincipalBolsista.setMaximized(true);

       // stagePrincipalBolsista.show();
       // stageVerPerfil.close();
   // }
   
   
    URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
         FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalBolsistaController tpb = loader.getController();    
            tpb.setStagePrincipal(stagePrincipal);
            tpb.setBolsista(bolsista);
            tpb.setProjeto(projeto);
            
            stagePrincipal.setOnShown(evento -> {
            tpb.ajustarElementosJanela(bolsista,projeto);
        });
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal bolsista");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
            stageVerPerfil.close();
    }

    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/VerPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stage = new Stage();
        VerPerfilBolsistaController vpb = loader.getController();
        vpb.setBolsista(bolsista); // Garantindo que os dados sejam passados corretamente
        vpb.setStage(stage);

        Scene cena = new Scene(root);
        stage.setTitle("Ver Perfil Bolsista");
        stage.setScene(cena);
        stage.setMaximized(true);

        stage.show();
        stageVerPerfil.close();
    }

    @FXML
    void onClickVerProjeto(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stage = new Stage();
        TelaPrincipalBolsistaController tpb = loader.getController();
        tpb.setStagePrincipal(stage);

        Scene cena = new Scene(root);
        stage.setTitle("Tela Principal Bolsista");
        stage.setScene(cena);
        stage.setMaximized(true);

        stage.show();
        stageVerPerfil.close();
    }
    @FXML
void onClickOutrosProjetos(ActionEvent event) {
    System.out.println("Outros Projetos click");
}


   @FXML
void onClickPublicacao(ActionEvent event) throws IOException {
    URL url = new File("src/main/java/view/CadastrarPostagem.fxml").toURI().toURL();
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();

    Stage stagePublicacao = new Stage();
    CadastrarPostagemController cpb = loader.getController();
    cpb.setStage(stagePublicacao);

    Scene cena = new Scene(root);
    stagePublicacao.setTitle("Cadastro de Publicação");
    stagePublicacao.setMaximized(true);
    stagePublicacao.setScene(cena);
    stagePublicacao.show();
}
@FXML
void onClickArtigo(ActionEvent event) throws IOException {
    URL url = new File("src/main/java/view/CadastrarArtigo.fxml").toURI().toURL();
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();

    Stage stageArtigo = new Stage();
    CadastrarArtigoController cab = loader.getController();
    cab.setStage(stageArtigo);

    Scene cena = new Scene(root);
    stageArtigo.setTitle("Cadastro de Artigo");
    stageArtigo.setMaximized(true);
    stageArtigo.setScene(cena);
    stageArtigo.show();
}



    void setProjeto(Projeto projeto) {
       this.projeto = projeto;
    }


}