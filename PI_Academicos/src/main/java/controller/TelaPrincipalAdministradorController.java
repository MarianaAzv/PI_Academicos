
package controller;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Administrador;
import model.Noticia;
import model.NoticiaDAO;

public class TelaPrincipalAdministradorController {
    
    private Administrador adm;
    private NoticiaDAO noticiaDAO;
    private Stage stageADM;

    // Formatador para a data e hora
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public TelaPrincipalAdministradorController() {
        noticiaDAO = new NoticiaDAO();
    }
    
    @FXML
    public void initialize() {

       Platform.runLater(() -> {//esse método permite que a tela inicialize sem depender de uma operação mais demorada           
            carregarFotos();
        });

    }
    
    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnADM;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnNotificacoes;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerPerfil;
    
    @FXML
    private TilePane galeria;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Label lblNomeAdm;
    
    @FXML
    private TilePane tilePaneGaleria;
    
    @FXML
    public void carregarFotos() {
        tilePaneGaleria.getChildren().clear(); // Limpa a galeria antes de recarregar
        try {
            List<Noticia> noticias = noticiaDAO.listarNoticias();
            if (noticias.isEmpty()) {
                System.out.println("Nenhuma notícia encontrada no banco de dados.");
                

            }
            for (Noticia noticia : noticias) {
                if(noticia.getLinkImagem()!=null){
                adicionarNoticiaFeed(noticia);
                }
                else{ // caso o link da foto estiver com problema uma outra foto substitui ela
                    ImageView imageView = new ImageView();
                    imageView.setFitWidth(270);
                    imageView.setFitHeight(270);
                    imageView.setPreserveRatio(true);
                    tilePaneGaleria.getChildren().add(imageView);
                    Image image = new Image("https://developers.google.com/static/maps/documentation/streetview/images/error-image-generic.png", true);
                    imageView.setImage(image);
                }

            }
        } catch (SQLException e) {
            System.out.println("Deu ruim na hora de carregar noticias");
        }
    }
    
    private void adicionarNoticiaFeed(Noticia noticia) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(270);
        imageView.setFitHeight(270);
        imageView.setPreserveRatio(true);

        tilePaneGaleria.getChildren().add(imageView);
  
        
        try {
            Image image = new Image(noticia.getLinkImagem(), true);
           
            redimensionarFotos(image,imageView, noticia);//método para deixar fotos quadradas
                  
            System.out.println("Caminho da imagem: " + noticia.getLinkImagem());
            
            imageView.setImage(image);
            

            imageView.setOnMouseClicked(event -> {
                try {
                abrirTelaAtualizarNoticia(noticia); //tela teste, posteriormente será passada uma tela que mostre os detalhes da noticia
                } catch (IOException e) {
                System.err.println("Erro ao abrir tela de detalhes da notícia: " + e.getMessage());
                }
            });
            
            
        } catch (IllegalArgumentException e) {
            System.err.println("Link de imagem inválido ou inacessível: " + noticia.getLinkImagem());
            imageView.setImage(new Image(getClass().getResourceAsStream("/src/main/resources/Variant3.png")));
        }
        
    }
    

     @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {  
        abrirTelaAtualizar();
    }
    
      @FXML
    void OnDragEnterAtualizarPerfil(MouseEvent event) {
        
         btnAtualizarPerfil.setStyle("-fx-background-color: D07979" );
    }

    @FXML
    void OnDragExitAtualizarPerfil(MouseEvent event) {
        
         btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5" );

    }

     //**********************************
    @FXML
    void onClickADM(ActionEvent event) throws IOException {
        abrirTelaADMS();
    }
    
    @FXML
    void OnDragEnterADM(MouseEvent event) {
         btnADM.setStyle("-fx-background-color: D07979" );
    }
      @FXML
    void OnDragExitADM(MouseEvent event) {
         btnADM.setStyle("-fx-background-color:  DBA5A5" );
    }

     //**********************************
    @FXML
    void onClickPublicacao(ActionEvent event) throws IOException {
        abrirTelaNoticia();
    }
    @FXML
    void OnDragEnterPublicacao(MouseEvent event) {
         btnPublicacao.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnDragExitPublicacao(MouseEvent event) {
         btnPublicacao.setStyle("-fx-background-color:  DBA5A5" );
    }
    //**********************************

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
       abrirTelaLogin();
    }
    @FXML
    void OnDragEnterSair(MouseEvent event) {
         btnSair.setStyle("-fx-background-color: D07979" );
    }
    @FXML
    void OnDragExitSair(MouseEvent event) {
         btnSair.setStyle("-fx-background-color:  DBA5A5" );
    }
//**********************************

    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        abrirTelaVerPerfil();
    }

    @FXML
    void OnDragEnterVerPerfil(MouseEvent event) {
         btnVerPerfil.setStyle("-fx-background-color:  D07979" );
    }
     @FXML
    void OnDragExitVerPerfil(MouseEvent event) {
         btnVerPerfil.setStyle("-fx-background-color:  DBA5A5" );
    }
//**********************************
    
    @FXML
    void onClickBtnNotificacoes(ActionEvent event) throws IOException {
        abrirTelaNotificacoes();
    }
    
    @FXML
    void OnDragExitNotificacoes(MouseEvent event) {
        btnNotificacoes.setStyle("-fx-background-color:  DBA5A5" );
    }
    @FXML
    void OnDragEnterNotificacoes(MouseEvent event) {
        btnNotificacoes.setStyle("-fx-background-color:  D07979" );
    }
//**********************************    
    
     private void abrirTelaVerPerfil() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/VerPerfilAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageVerPerfil = new Stage();
        
            VerPerfilAdministradorController vpac = loader.getController();    
            vpac.setStage(stageVerPerfil);
            vpac.setAdministrador(adm);
            stageVerPerfil.setMaximized(true);
        
            Scene cena = new Scene(root);
            stageVerPerfil.setTitle("Perfil administrador");
            stageVerPerfil.setScene(cena);
            
            stageVerPerfil.show();
            stageADM.close();
            
    }
    
    private void abrirTelaNoticia() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/TelaNoticia.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastroNoticia = new Stage();
        
            TelaNoticiaController tnc = loader.getController();    
            tnc.setStage(stageCadastroNoticia);
            tnc.setAdministrador(adm);
        
            Scene cena = new Scene(root);
            stageCadastroNoticia.setTitle("Tela Cadastrar Noticia");
            stageCadastroNoticia.setScene(cena);
            
            stageCadastroNoticia.show();
            
    }
    
     private void abrirTelaAtualizar() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/AtualizarPerfilAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageAtualizar = new Stage();
        
            AtualizarPerfilAdministradorController apac = loader.getController();  
            apac.setAdministrador(adm);
            apac.setStage(stageAtualizar);
        
            Scene cena = new Scene(root);
            stageAtualizar.setTitle("Tela Atualizar Administrador");
            stageAtualizar.setScene(cena);
            //deixa a tela maximizada
            stageAtualizar.setMaximized(true);
            
            stageAtualizar.show();
            stageADM.close();
    }
     
    private void abrirTelaADMS() throws IOException{
        
         URL url = new File("src/main/java/view/Administradores.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageADMS = new Stage();
        
            AdministradoresController ac = loader.getController();  
            ac.setAdministrador(adm);
            ac.setStage(stageADMS);
        
            Scene cena = new Scene(root);
            stageADMS.setTitle("Tela Administradores");
            stageADMS.setScene(cena);
            //deixa a tela maximizada
            stageADMS.setMaximized(true);
            
            stageADMS.show();
            stageADM.close();
    }
    
    private void abrirTelaNotificacoes() throws IOException{
        
         URL url = new File("src/main/java/view/Notificacoes.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageNotificacoes = new Stage();
        
            NotificacoesController nc = loader.getController();  
            nc.setAdministrador(adm);
            nc.setStage(stageNotificacoes);
        
            Scene cena = new Scene(root);
            stageNotificacoes.setTitle("Tela notificações");
            stageNotificacoes.setScene(cena);
            //deixa a tela maximizada
            stageNotificacoes.setMaximized(true);
            
            stageNotificacoes.show();
            stageADM.close();
    }
    
    private void abrirTelaAtualizarNoticia(Noticia noticia) throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/AtualizarNoticia.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageAtualizarNoticia = new Stage();
        
            AtualizarNoticiaController anc = loader.getController();    
            anc.setStage(stageAtualizarNoticia);
            anc.setAdministrador(adm);
            anc.setNoticia(noticia);
        
            Scene cena = new Scene(root);
            stageAtualizarNoticia.setTitle("Tela Atualizar Noticia");
            stageAtualizarNoticia.setScene(cena);
            
            stageAtualizarNoticia.show();
            
    }
    
    private void abrirTelaLogin() throws IOException{
        
         URL url = new File("src/main/java/view/TelaLogin.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageLogin = new Stage();
        
            TelaLoginController tlc = loader.getController();  
            tlc.setStage(stageLogin);
        
            Scene cena = new Scene(root);
            stageLogin.setTitle("Tela Login");
            stageLogin.setScene(cena);
            //deixa a tela maximizada
            stageLogin.setMaximized(true);
            
            stageLogin.show();
            stageADM.close();
    }
    
    
    public void setStage(Stage stage){
        this.stageADM = stage;
    }
    
    public void setAdministrador(Administrador adm) {
        this.adm = adm;
        lblNomeAdm.setText(adm.getNome());
    }
    
    
    
    void ajustarElementosJanela(Administrador adm) {
        this.adm=adm;
        
        System.out.println("Aqui chegam os parâmetros do login " + adm.getNome() + " - " + "ATIVA: " + adm.getAtiva());
        //txtNomeUsuario.setText(adm.getNome());

        
        if(adm.getAtiva()==false){
        
        }
       
    }
    
    private void redimensionarFotos(Image image, ImageView imageView, Noticia noticia){
        
        image.progressProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() == 1.0) { // Imagem totalmente carregada
                Platform.runLater(() -> { // Garantir que a atualização da UI ocorra no thread da aplicação
                    double imageWidth = image.getWidth();
                    double imageHeight = image.getHeight();
                    double imageViewWidth = imageView.getFitWidth();
                    double imageViewHeight = imageView.getFitHeight();

                    double ratioImage = imageWidth / imageHeight;
                    double ratioView = imageViewWidth / imageViewHeight;

                    // Calcular o viewport para preencher o espaço sem distorção
                    Rectangle2D viewport;
                    if (ratioImage > ratioView) {
                        // Imagem é mais larga que o ImageView. Cortar laterais.
                        double newImageWidth = imageHeight * ratioView;
                        double xOffset = (imageWidth - newImageWidth) / 2;
                        viewport = new Rectangle2D(xOffset, 0, newImageWidth, imageHeight);
                    } else {
                        // Imagem é mais alta que o ImageView. Cortar superior/inferior.
                        double newImageHeight = imageWidth / ratioView;
                        double yOffset = (imageHeight - newImageHeight) / 2;
                        viewport = new Rectangle2D(0, yOffset, imageWidth, newImageHeight);
                    }
                    imageView.setViewport(viewport);
                    imageView.setImage(image); // Definir a imagem após configurar o viewport
                    System.out.println("Noticia " + noticia.getLinkImagem());
                });
            }
        });
    }
    
}
