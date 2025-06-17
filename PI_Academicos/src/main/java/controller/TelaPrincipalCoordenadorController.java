package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bolsista;
import model.BolsistaDAO;
import model.Coordenador;
import model.Noticia;
import model.Postagem;
import model.PostagemDAO;
import model.Projeto;
//import model.Coordenador;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;

public class TelaPrincipalCoordenadorController {
    
    private Stage stagePrincipalCoordenador;
    private Connection conexao;
    private final Usuario dao = new Usuario();
 
    private Coordenador coordenador;
    private PostagemDAO postagemDAO;
    Projeto projeto;
    Bolsista bolsista;
    

    
    @FXML
    public void initialize() {

       Platform.runLater(() -> {
           //esse método permite que a tela inicialize sem depender de uma operação mais demorada
           try{
           carregarFotos();
           } catch (IOException ex) {
               ex.printStackTrace();
           }
            
        });

    }


    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnAtualizarProjeto;

    @FXML
    private Button btnOutrosProjetos;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerPerfil;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgPerfilProjeto;

    @FXML
    private ImageView imgProjeto;

  @FXML
   private Label lblNomeBolsista;

  @FXML
    private Label lblNomeCocoordenador;

    @FXML
   private Label lblNomeCoordenador;

    @FXML
    private Label lblResumo;

    @FXML
    private Text textNomeProjeto;
    
    @FXML
    private TilePane tilePaneGaleria;

    @FXML
    private Text txtCampus;

    @FXML
    private Text txtFim;

    @FXML
    private Text txtInicio;

    @FXML
    private Text txtNomeBolsitas;

    @FXML
    private Text txtNomeCocoordenador;

    @FXML
    private Text txtNomeCoordenador;

    @FXML
    private Text txtNomeProjeto;

    @FXML
    private Text txtProrrogacao;
    
        @FXML
    private VBox vbox;
    
    //******************* OnClicks ***************************************
    @FXML
    void onClickAdicionarArtigo(ActionEvent event) throws IOException {
        abrirTelaArtigos();
    }
     @FXML
    void OnEnterArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color: D07979" );
    }
     @FXML
    void OnExitArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        AbrirTelaAtualizarPerfil();
    }
    @FXML
    void OnEnterAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color: D07979" );
    }
     @FXML
    void OnExitAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
     @FXML
    void onClickAtualizarProjeto(ActionEvent event) throws IOException {
        abrirTelaAtualizarProjeto();
    }
    @FXML
    void OnEnterAtualizarProjeto(MouseEvent event) {
        btnAtualizarProjeto.setStyle("-fx-background-color: D07979" );
    }
     @FXML
    void OnExitAtualizarProjeto(MouseEvent event) {
        btnAtualizarProjeto.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickOutrosProjetos(ActionEvent event) throws IOException {
        abrirTelaOutrosProjetos();
    }
    @FXML
    void OnEnterOutrosProjetos(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnExitOutrosProjetos(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickAdicionarPublicacao(ActionEvent event) throws IOException {
        abrirTelaPublicacao();
    }
    @FXML
    void OnEnterPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnExitPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickSair(ActionEvent event) throws IOException {
        AbrirTelaLogin();
    }
    @FXML
    void OnEnterSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color: D07979" );
    }
     @FXML
    void OnExitSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color:  DBA5A5" );
    }
    //******************************************************************
    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        abrirTelaVerPerfil();
    }
    @FXML
    void OnEnterVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnExitVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color:  DBA5A5" );
    }
    
    //----------------------*CarregarFotos*----------------------//
        public TelaPrincipalCoordenadorController() {
        postagemDAO = new PostagemDAO();
    }
        
          @FXML
    public void carregarFotos() throws IOException {
        tilePaneGaleria.getChildren().clear(); // Limpa a galeria antes de recarregar
        try {
            List<Postagem> postagens = postagemDAO.listarPostagens(projeto);
            if (postagens.isEmpty()) {
                System.out.println("Nenhuma postagem encontrada no banco de dados.");
                

            }
            for (Postagem postagem : postagens) {
                if(postagem.getFoto().getDadosImagem()!=null){
                adicionarPostagemFeed(postagem);
                }
                else{ // caso o link da foto estiver com problema uma outra foto substitui ela
                    ImageView imageView = new ImageView();
                    imageView.setFitWidth(320);
                    imageView.setFitHeight(320);
                    imageView.setPreserveRatio(true);
                    tilePaneGaleria.getChildren().add(imageView);
                    Image image = new Image("https://developers.google.com/static/maps/documentation/streetview/images/error-image-generic.png", true);
                    imageView.setImage(image);
                }

            }
        } catch (SQLException e) {
            System.out.println("Não é possível carregar postagens");
        }
    }
        
    //------------------*SETs*----------------------//
    
    
        
      public void setStage(Stage stage){
    this.stagePrincipalCoordenador = stage;
    }
    
    public void setStagePrincipal(Stage stagePrincipalCoordenador){
    this.stagePrincipalCoordenador = stagePrincipalCoordenador;
   }
    
    public void setCoordenador(Coordenador coord) {
       this.coordenador = coord;
       TxtNomeUsuario.setText(coord.getNome());
    }
    public void setProjeto(Projeto projeto) {
       this.projeto = projeto;
       //lblNomeCoordenador.setText(projeto.getCoordenador());
       lblResumo.setText(projeto.getResumo());
       txtCampus.setText(projeto.getCampus().getNomeCampus());
       txtNomeProjeto.setText(projeto.getTitulo());
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       String DataInicio = projeto.getDataInicio().format(formatter);
       txtInicio.setText(DataInicio);

       String DataFim = projeto.getDataFim().format(formatter);
       txtFim.setText(DataFim);
       String prorroga = String.valueOf(projeto.getProrroacao());
       txtProrrogacao.setText(prorroga);
       txtNomeCoordenador.setText(projeto.getCocoordenadores());
        carregarBolsista(projeto);
 
    }
    
    //Setar os nomes dos bolsistas e deixar null o coordenador
      
        
  
    
//******************* MÉTODOS ***************************************
   
    
    private void abrirTelaVerPerfil() throws IOException{
 
            URL url = new File("src/main/java/view/VerPerfilCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageVerPerfil = new Stage();
            
            VerPerfilCoordenadorController vpf = loader.getController();
            vpf.setCoordenador(coordenador);
            vpf.setProjeto(projeto);
            vpf.setStage(stageVerPerfil);
        
            Scene cena = new Scene(root);
            stageVerPerfil.setTitle("Perfil Coordenador");
            stageVerPerfil.setScene(cena);
            //deixa a tela maximizada
            stageVerPerfil.setMaximized(true);
            
            stageVerPerfil.show();
            stagePrincipalCoordenador.close();
            
    }


    private void AbrirTelaAtualizarPerfil() throws IOException {

        URL url = new File("src/main/java/view/AtualizarPerfilCoordenador.fxml").toURI().toURL();       
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        
        Stage stageAtualizarCoordenador = new Stage();
        
        AtualizarPerfilCoordenadorController apcc = loader.getController();
        apcc.setCoordenador(coordenador); 
        apcc.setProjeto(projeto);
        apcc.setStage(stageAtualizarCoordenador);
        
        Scene cena = new Scene(root);
        stageAtualizarCoordenador.setTitle("Atualizar Perfil Coordenador");
        stageAtualizarCoordenador.setMaximized(true);
        stageAtualizarCoordenador.setScene(cena);
        stageAtualizarCoordenador.show();
        stagePrincipalCoordenador.close();
               
    } 

    private void abrirTelaAtualizarProjeto() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/AtualizarProjeto.fxml").toURI().toURL();       
      FXMLLoader loader = new FXMLLoader(url);
       
      Parent root = loader.load();
        
     Stage stageAtualizarProjeto = new Stage();
        
       AtualizarProjetoController apc = loader.getController();
        
        apc.setStage(stageAtualizarProjeto);
        apc.setCoordenador(coordenador);
        System.out.print("Coordenador em abrir atualizar"+coordenador);
        apc.setProjeto(projeto);
        
        
          stageAtualizarProjeto.setOnShown(evento -> {
        apc.ajustarElementosJanela();
      });
        
      Scene cena = new Scene(root);
       stageAtualizarProjeto.setTitle("Atualizar Projeto");
       stageAtualizarProjeto.setMaximized(true);
        stageAtualizarProjeto.setScene(cena);
       stageAtualizarProjeto.show();
        stagePrincipalCoordenador.close();

    }

    private void AbrirTelaLogin() throws IOException {

         URL url = new File("src/main/java/view/TelaLogin.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageLogin = new Stage();

        TelaLoginController tpc = loader.getController();
        tpc.setStage(stageLogin);     

        Scene cena = new Scene(root);
        stageLogin.setTitle("Tela de Login");
        stageLogin.setScene(cena);
        //deixa a tela maximizada
        stageLogin.setMaximized(true);
        stageLogin.show();
        
        stagePrincipalCoordenador.close();
        

    }
    
    public void abrirTelaArtigos() throws IOException{
        URL url = new File("src/main/java/view/CadastrarArtigo.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageArtigo = new Stage();
        CadastrarArtigoController cab = loader.getController();
        cab.setStage(stageArtigo);
        cab.setProjeto(projeto);

        Scene cena = new Scene(root);
        stageArtigo.setTitle("Cadastro Artigo");
        stageArtigo.setMaximized(false);
        stageArtigo.setScene(cena);
        stageArtigo.show();
    }
    
    public void abrirTelaPublicacao() throws IOException{
        URL url = new File("src/main/java/view/CadastrarPostagem.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePostagem = new Stage();
        CadastrarPostagemController cpb = loader.getController();
        cpb.setStage(stagePostagem);
        cpb.setProjeto(projeto);

        Scene cena = new Scene(root);
        stagePostagem.setTitle("Cadastro Postagem");
        stagePostagem.setMaximized(false);
        stagePostagem.setScene(cena);
        stagePostagem.show();
    }
    
    public void abrirTelaAtualizarPublicacao(Postagem postagem) throws IOException{
        URL url = new File("src/main/java/view/AtualizarPostagem.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageAtualizarPostagem = new Stage();
        AtualizarPostagemController apc = loader.getController();
        apc.setStage(stageAtualizarPostagem);
        apc.setProjeto(projeto);
        apc.setPostagem(postagem);

        Scene cena = new Scene(root);
        stageAtualizarPostagem.setTitle("Atualizar Postagem");
        stageAtualizarPostagem.setMaximized(false);
        stageAtualizarPostagem.setScene(cena);
        stageAtualizarPostagem.show();
    }
    
    public void abrirTelaOutrosProjetos() throws IOException{
        URL url = new File("src/main/java/view/EscolherProjeto.fxml").toURI().toURL();
         FXMLLoader loader = new FXMLLoader(url);
          Parent root = loader.load();
        
           Stage stageProjetos = new Stage();
        
          EscolherProjetoController tpc = loader.getController();  
          tpc.setCoordenador(coordenador);
          tpc.setStage(stageProjetos);
          
         Scene cena = new Scene(root);
        stageProjetos.setTitle("Outros projetos");
        stageProjetos.setMaximized(false);
        stageProjetos.setScene(cena);
        stageProjetos.setOnShown(evento -> {
        try {
            tpc.OnClickProjeto();
        } catch (SQLException ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });


    stageProjetos.show();
    }

   public void ajustarElementosJanela(Coordenador coordenador, Projeto projeto) {
        this.coordenador=coordenador;
        this.projeto=projeto;
        carregarBolsista(projeto);
                
        System.out.println("Aqui chegam os parâmetros do login "
                + coordenador.getNome() + " - " + coordenador.getSiape() + "ATIVA: " + coordenador.getAtiva());
        txtNomeCoordenador.setText(coordenador.getNome());
        String siape = String.valueOf(coordenador.getSiape());
        textNomeProjeto.setText(projeto.getTitulo());
        
        if(coordenador.getAtiva()==false){
        
          System.out.println("Coordenador esta falso");
        }

       
    }
   
    
    private void adicionarPostagemFeed(Postagem postagem) {
        
        ImageView imageView = new ImageView();
        imageView.setFitWidth(320);
        imageView.setFitHeight(320);
        imageView.setPreserveRatio(true);

        tilePaneGaleria.getChildren().add(imageView);
        
        Image image = null;
                
                byte[] conteudoFoto = postagem.getFoto().getDadosImagem();
                if(conteudoFoto!=null){
                   try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                            image = new Image(bis); // Converte byte[] para Image AQUI
                        } catch (Exception e) {
                            System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                            // precisa definir uma imagem padrao de erro
                        }
                }
        
        
        
        if(image!=null){
        imageView.setImage(image);
        carregarFotosAjustadas(image,imageView, postagem);//método para deixar fotos quadradas
  
                  
            System.out.println("Id da imagem: " + postagem.getFoto().getId());
            

            imageView.setOnMouseClicked(event -> {
                try {
                abrirTelaAtualizarPublicacao(postagem); //tela teste, posteriormente será passada uma tela que mostre os detalhes da noticia
                } catch (IOException e) {
                System.err.println("Erro ao abrir tela de detalhes da postagem: " + e.getMessage());
                }
            });
            
            
        
    }
        else{
            imageView.setImage(new Image(getClass().getResourceAsStream("/src/main/resources/Variant3.png")));
        }
    }
    
    
    private void carregarFotosAjustadas(Image image, ImageView imageView, Postagem postagem) {
    // verifica se a imagem foi carregada
    if (image.isError()) {
        System.err.println("Error loading image for ID: " + postagem.getFoto().getId());
        imageView.setImage(new Image(getClass().getResourceAsStream("/src/main/resources/Variant3.png")));
        return; 
    }

    if (image.isBackgroundLoading() || image.getProgress() < 1.0) {
        // se a imagem está carregando ele adiciona um listener
        image.progressProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() == 1.0) { // Image fully loaded
                Platform.runLater(() -> { // Ensure UI update on FX Application Thread
                    redimensionarFotos(image, imageView, postagem);
                });
            }
        });
    } else {
        // se a imagem está carregada ele redimensiona
        redimensionarFotos(image, imageView, postagem);
    }
}
  
    
    private void redimensionarFotos(Image image, ImageView imageView, Postagem postagem) {
    double imageWidth = image.getWidth();
    double imageHeight = image.getHeight();
    double imageViewWidth = imageView.getFitWidth();
    double imageViewHeight = imageView.getFitHeight();

    double ratioImage = imageWidth / imageHeight;
    double ratioView = imageViewWidth / imageViewHeight;

    Rectangle2D viewport;
    if (ratioImage > ratioView) {
        // Image is wider than ImageView. Crop sides.
        double newImageWidth = imageHeight * ratioView;
        double xOffset = (imageWidth - newImageWidth) / 2;
        viewport = new Rectangle2D(xOffset, 0, newImageWidth, imageHeight);
    } else {
        // Image is taller than ImageView. Crop top/bottom.
        double newImageHeight = imageWidth / ratioView;
        double yOffset = (imageHeight - newImageHeight) / 2;
        viewport = new Rectangle2D(0, yOffset, imageWidth, newImageHeight);
    }
    imageView.setViewport(viewport);
    System.out.println("Postagem arrumada com id:" + postagem.getFoto().getId());
}

    public void carregarBolsista(Projeto projeto) {
       try {
        BolsistaDAO dao = new BolsistaDAO();
        List<Bolsista> bolsistas = dao.selecionarBolsistasPorProjeto(projeto);

        vbox.getChildren().clear(); 

         if (bolsistas.isEmpty()) {
            System.out.println("Nenhum bolsista encontrado para o projeto: " + projeto.getTitulo());
        }
         
        for (Bolsista b : bolsistas) {
            Label lbl = new Label(b.getNome()); 
            vbox.getChildren().add(lbl);
        }
    } catch (SQLException e) {
        mostrarAviso("Erro", "Erro ao carregar bolsistas do projeto");
       
    }
    }
   
        
   }
    

