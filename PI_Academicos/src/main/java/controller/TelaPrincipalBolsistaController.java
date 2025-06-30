package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Artigo;
import model.ArtigoDAO;
import model.Bolsista;
import model.Postagem;
import model.PostagemDAO;
import model.Projeto;
import model.Usuario;

public class TelaPrincipalBolsistaController implements INotificacaoAlert {

    private Stage stageTelaPrincipalBolsista;
    private Connection conexao;
    private final Usuario dao = new Usuario();
    private PostagemDAO postagemDAO;
    private ArtigoDAO artigoDAO;
    private Bolsista bolsista;
    private Projeto projeto;//hj
    int resp = 1;
    
    public TelaPrincipalBolsistaController() {
        postagemDAO = new PostagemDAO();
        artigoDAO = new ArtigoDAO();
    }
    
     @FXML
    public void initialize() {

        Platform.runLater(() -> {
            //esse método permite que a tela inicialize sem depender de uma operação mais demorada
            try {
                carregarFotos();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

    }

    public void setStage(Stage stage) {
        this.stageTelaPrincipalBolsista = stage;
    }

    public void setStagePrincipal(Stage telaPrincipalBolsista) {
        this.stageTelaPrincipalBolsista = telaPrincipalBolsista;
    }

    void setBolsista(Bolsista bol) {
        this.bolsista = bol;
        Image image = null;
        byte[] conteudoFoto = bolsista.getFotoPerfil().getDadosImagem();
        if (conteudoFoto != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                image = new Image(bis); // Converte byte[] para Image AQUI
            } catch (Exception e) {
                System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                // precisa definir uma imagem padrao de erro
            }
        }
        imgPerfil.setImage(image);
    }

    void setProjeto(Projeto projeto) {
        this.projeto = projeto;
        txtResumo.setText(projeto.getResumo());
        txtCampus.setText(projeto.getCampus().getNomeCampus());
        textNomeProjeto.setText(projeto.getTitulo());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String DataInicio = projeto.getDataInicio().format(formatter);
        txtInicio.setText(DataInicio);

        String DataFim = projeto.getDataFim().format(formatter);
        txtFim.setText(DataFim);
        String prorroga = String.valueOf(projeto.getProrroacao());
        txtProrrogacao.setText(prorroga);
        txtNomeCoordenador.setText(projeto.getCocoordenadores());
        //txtNomeBolsitas.setText(projeto.get);
        
        Image image = null;
        byte[] conteudoFoto = projeto.getFotoPerfil().getDadosImagem();
        if (conteudoFoto != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                image = new Image(bis); // Converte byte[] para Image AQUI
            } catch (Exception e) {
                System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                // precisa definir uma imagem padrao de erro
            }
        }
        imgProjetoBarra.setImage(image);
        imgPerfilProjeto.setImage(image);
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
    private ImageView imgLogo;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgPerfilProjeto;

    @FXML
    private ImageView imgProjetoBarra;

    @FXML
    private Label lblNomeBolsista;

    @FXML
    private Label lblNomeCoordenador;

    @FXML
    private Text textNomeProjeto;

    @FXML
    private Text txtCampus;

    @FXML
    private Text txtFim;

    @FXML
    private Text txtInicio;

    @FXML
    private Text txtNomeBolsita;

    @FXML
    private Text txtNomeCoordenador;

    @FXML
    private Text txtNomeBolsista;
    @FXML
    private Text txtProrrogacao;

    @FXML
    private Label txtResumo;
    
    @FXML
    private Label lblArtigos;
    
    @FXML
    private Label lblPublicacoes;
    
    @FXML
    private TilePane tilePaneGaleria;

    //******************* OnClicks ***************************************
    @FXML
    void onClickArtigo(ActionEvent event) throws IOException {
        abrirArtigo();
    }

    @FXML
    void onEnterArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitArtigo(MouseEvent event) {
        btnArtigo.setStyle("-fx-background-color:  DBA5A5");
    }
    //******************************************************************

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        abrirAtualizarPerfil();
    }

    @FXML
    void onEnterAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5");
    }
    //******************************************************************

    @FXML
    void onClickOutrosProjetos(ActionEvent event) throws IOException {
        outrosProjetos();
    }

    @FXML
    void onEnterOutrosProjeto(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitOutrosProjeto(MouseEvent event) {
        btnOutrosProjetos.setStyle("-fx-background-color:  DBA5A5");
    }
    //******************************************************************

    @FXML
    void onClickPublicacao(ActionEvent event) throws IOException {
        abrirPublicacao();
    }

    @FXML
    void onEnterPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitPublicacao(MouseEvent event) {
        btnPublicacao.setStyle("-fx-background-color:  DBA5A5");
    }
    //******************************************************************

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
        alerta("Você tem certeza mana", 2, "Verificação de saida");
    }

    @FXML
    void onEnterSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitSair(MouseEvent event) {
        btnSair.setStyle("-fx-background-color:  DBA5A5");
    }

    //******************************************************************
    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        abrirVerPerfil();
    }

    @FXML
    void onEnterVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color: D07979");
    }

    @FXML
    void onExitVerPerfil(MouseEvent event) {
        btnVerPerfil.setStyle("-fx-background-color:  DBA5A5");
    }
    //******************************************************************
    @FXML
    void onClickLabelArtigos(MouseEvent event) throws IOException, SQLException {
        try{
        carregarArtigos();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }    
     @FXML
    void onEnterLabelArtigos(MouseEvent event) {
        lblArtigos.setStyle("-fx-text-fill: #840d0b" );
    }    
    @FXML
    void onExitLabelArtigos(MouseEvent event) {
        lblArtigos.setStyle("-fx-text-fill: black" );
    }
    //******************************************************************
    @FXML
    void onClickLabelPublicacoes(MouseEvent event) {
        try{
           carregarFotos();
           } catch (IOException ex) {
               ex.printStackTrace();
           }
    }    
    @FXML
    void onEnterLabelPublicacoes(MouseEvent event) {
        lblPublicacoes.setStyle("-fx-text-fill: #840d0b" );
    }
    @FXML
    void onExitLabelPublicacoes(MouseEvent event) {
        lblPublicacoes.setStyle("-fx-text-fill: black" );
    }
    //******************* MÉTODOS ***************************************
    
    @FXML
    public void carregarFotos() throws IOException {
        tilePaneGaleria.getChildren().clear(); // Limpa a galeria antes de recarregar
        try {
            List<Postagem> postagens = postagemDAO.listarPostagens(projeto);
            if (postagens.isEmpty()) {
                System.out.println("Nenhuma postagem encontrada no banco de dados.");

            }
            for (Postagem postagem : postagens) {
                if (postagem.getFoto().getDadosImagem() != null) {
                    adicionarPostagemFeed(postagem);
                } else { // caso o link da foto estiver com problema uma outra foto substitui ela
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
    
    @FXML
    public void carregarArtigos() throws IOException, SQLException {
        tilePaneGaleria.getChildren().clear(); // Limpa a galeria antes de recarregar
        
            List<Artigo> artigos = artigoDAO.listarArtigos(projeto);
            if (artigos.isEmpty()) {
                System.out.println("Nenhum artigo encontrado no banco de dados.");
                

            }
            for (Artigo artigo : artigos) {
                if(artigo.getArquivo()!=null){
                adicionarArtigoFeed(artigo);
                }
                else{ // caso o link da foto estiver com problema uma outra foto substitui ela
                    System.out.println("Não carregou o artigo");
                }

            } 
        
    }
    
    public void abrirVerPerfil() throws IOException {
        URL url = new File("src/main/java/view/VerPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageVerPerfil = new Stage();

        VerPerfilBolsistaController vpb = loader.getController();
        vpb.setBolsista(bolsista);
        vpb.setProjeto(projeto);
        vpb.setStage(stageVerPerfil);

        stageVerPerfil.setOnShown(evento -> {//hj
            vpb.ajustarElementosJanela(bolsista, projeto);
        });

        Scene cena = new Scene(root);
        stageVerPerfil.setTitle("Perfil Bolsista");
        stageVerPerfil.setScene(cena);
        stageVerPerfil.setMaximized(true);

        stageVerPerfil.show();
        stageTelaPrincipalBolsista.close(); // Correção: Fechar a tela principal ao abrir a nova
    }

    public void abrirAtualizarPerfil() throws IOException {
        URL url = new File("src/main/java/view/AtualizarPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageAtualizar = new Stage();
        AtualizarPerfilBolsistaController apb = loader.getController();
        apb.setBolsista(bolsista);
        apb.setProjeto(projeto);

        stageAtualizar.setOnShown(evento -> {//hj
            //apb.ajustarElementosJanela(bolsista,projeto);
        });

        Scene cena = new Scene(root);
        stageAtualizar.setTitle("Atualizar Perfil Bolsista");
        stageAtualizar.setMaximized(true);
        stageAtualizar.setScene(cena);
        stageAtualizar.show();
        stageTelaPrincipalBolsista.close();
    }

    public void abrirPublicacao() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarPostagem.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePostagem = new Stage();
        CadastrarPostagemController cpb = loader.getController();
        cpb.setProjeto(projeto);
        // cpb.setBolsista(bolsista);
        cpb.setStage(stagePostagem);

        Scene cena = new Scene(root);
        stagePostagem.setTitle("Bolsista Cadastro Postagem");
        stagePostagem.setMaximized(false);
        stagePostagem.setScene(cena);
        stagePostagem.show();
    }
    
    public void abrirTelaAtualizarPublicacao(Postagem postagem) throws IOException {
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

    public void abrirArtigo() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarArtigo.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageArtigo = new Stage();
        CadastrarArtigoController cab = loader.getController();
        cab.setProjeto(projeto);
        //  cab.setBolsista(bolsista);
        cab.setStage(stageArtigo);
        

        Scene cena = new Scene(root);
        stageArtigo.setTitle("Bolsista Cadastro Artigo");
        stageArtigo.setMaximized(false);
        stageArtigo.setScene(cena);
        stageArtigo.show();
    }
    
    private void abrirTelaAtualizarArtigo(Artigo artigo) throws IOException{
 
            URL url = new File("src/main/java/view/AtualizarArtigo.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageAtualizarArtigo = new Stage();
            
            AtualizarArtigoController aac = loader.getController();
            aac.setStage(stageAtualizarArtigo);
            aac.setProjeto(projeto);
            aac.setArtigo(artigo);
                  
            Scene cena = new Scene(root);
            stageAtualizarArtigo.setTitle("Mostrar Artigo");
            stageAtualizarArtigo.setScene(cena);
            
            stageAtualizarArtigo.show();
            
    }

    @FXML
    public void outrosProjetos() throws MalformedURLException, IOException {
        System.out.println("Outros Projetos clicado!");
        URL url = new File("src/main/java/view/EscolherProjeto.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePrincipal = new Stage();
        EscolherProjetoController epb = loader.getController();
        epb.setBolsista(bolsista);
        epb.setStage(stagePrincipal);

        stagePrincipal.setOnShown(evento -> {
            try {
                epb.OnClickProjeto();
            } catch (SQLException ex) {
                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // tpb.ajustarElementosJanela(bolsista);
        });

        Scene cena = new Scene(root);
        stagePrincipal.setTitle("Tela Escolher Projeto Bolsista");
        stagePrincipal.setMaximized(false);
        stagePrincipal.setScene(cena);
        stagePrincipal.show();
        stageTelaPrincipalBolsista.close();

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

        stageTelaPrincipalBolsista.close();

    }

    public void ajustarElementosJanela(Bolsista bolsista, Projeto projeto) {
        this.bolsista = bolsista;
        this.projeto = projeto;

        System.out.println("Aqui chegam os parâmetros do login " + bolsista.getNome() + " - " + bolsista.getMatricula() + " ATIVA: " + bolsista.getAtiva());

        TxtNomeUsuario.setText(bolsista.getNome());
        // String matricula = String.valueOf(bolsista.getMatricula());
        textNomeProjeto.setText(projeto.getTitulo());
        TxtNomeProjetoBarra.setText(projeto.getTitulo());

    }
    
    private void adicionarArtigoFeed(Artigo artigo){
       
       Label lblTituloArtigo = new Label();
       lblTituloArtigo.setPrefHeight(30);
       lblTituloArtigo.setPrefWidth(920);
       lblTituloArtigo.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 18px; -fx-underline: true;");
       lblTituloArtigo.setText("Artigo");
       
       tilePaneGaleria.getChildren().add(lblTituloArtigo);
       
       File arquivoArtigo = artigo.getArquivo();
       if(arquivoArtigo!=null){
           lblTituloArtigo.setText(artigo.getTitulo());
           
           System.out.println("Lbl artigo: " + lblTituloArtigo.toString());
           lblTituloArtigo.setOnMouseClicked(event -> {
                try {
                    System.out.println("Artigo id: " + artigo.getId());
                    //AbrirTelaLogin();
                abrirTelaAtualizarArtigo(artigo); 
                } catch (Exception e) {
                System.err.println("Erro ao abrir tela de detalhes do artigo: " + e.getMessage());
                e.printStackTrace();
                }
            });
           
           lblTituloArtigo.setOnMouseEntered(event -> {
               lblTituloArtigo.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 18px; -fx-underline: true; -fx-text-fill: #840d0b" );
           });
           lblTituloArtigo.setOnMouseExited(event -> {
               lblTituloArtigo.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 18px; -fx-underline: true; -fx-text-fill: black" );
           });
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
        if (conteudoFoto != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(conteudoFoto)) {
                image = new Image(bis); // Converte byte[] para Image AQUI
            } catch (Exception e) {
                System.err.println("Erro ao converter bytes para Image: " + e.getMessage());
                // precisa definir uma imagem padrao de erro
            }
        }

        if (image != null) {
            imageView.setImage(image);
            carregarFotosAjustadas(image, imageView, postagem);//método para deixar fotos quadradas

            System.out.println("Id da imagem: " + postagem.getFoto().getId());

            imageView.setOnMouseClicked(event -> {
                try {
                    abrirTelaAtualizarPublicacao(postagem); //tela teste, posteriormente será passada uma tela que mostre os detalhes da noticia
                } catch (IOException e) {
                    System.err.println("Erro ao abrir tela de detalhes da postagem: " + e.getMessage());
                }
            });

        } else {
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

    public void alerta(String msg, int tipo, String titulo) throws IOException {
        URL url = new File("src/main/java/view/AlertGenerico.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageAlerta = new Stage();

        AlertGenericoController vpb = loader.getController();
        vpb.setMsg(msg);
        vpb.setTipo(tipo);
        vpb.setStage(stageAlerta);
        vpb.setControllerResposta(this);

        Scene cena = new Scene(root);
        stageAlerta.setTitle(titulo);
        stageAlerta.setScene(cena);

        stageAlerta.show();

    }

    @Override
    public void btnOk() {
    
            resp = 2;   
    }
}