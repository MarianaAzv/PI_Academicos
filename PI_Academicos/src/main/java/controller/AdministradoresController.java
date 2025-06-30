
package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.stage.Stage;
import model.Administrador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.AdministradorDAO;
import model.Usuario;


public class AdministradoresController {
    
    private Stage stageADMS;
    private Administrador adm;
    ObservableList<Administrador> lista;
    

   public void setAdministrador(Administrador adm) {
        this.adm = adm;
        lblNomeAdm.setText(adm.getNome());
        
        Image image = null;
        byte[] conteudoFoto = adm.getFotoPerfil().getDadosImagem();
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

    public void setStage(Stage stageADMS) {
        this.stageADMS = stageADMS;
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
    private ImageView imgPerfil;

    @FXML
    private Label lblAdiministradoirs;
    
    @FXML
    private Button btnCadastrarADM;

    @FXML
    private Label lblNomeAdm;
    
    @FXML
    private TextField tfPesquisa;

    @FXML
    private TableView<Administrador> tabelaADMS;

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
       abrirTelaPrincipal();
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
    void onClickCadastrarADM(ActionEvent event) throws IOException {
        
        URL url = new File("src/main/java/view/CadastrarAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastrarADM = new Stage();
        
            CadastrarAdministradorController cac = loader.getController();    
            cac.setStage(stageCadastrarADM);

        
            Scene cena = new Scene(root);
            stageCadastrarADM.setTitle("Perfil administrador");
            stageCadastrarADM.setScene(cena);
            
            stageCadastrarADM.show();
            
            
            cac.setOnADMCadastrado(() -> {
            try {
                carregarTabelaADMS();
            } catch (SQLException ex) {
                Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
            
    }
    
    @FXML
    void onClickBtnNotificacoes(ActionEvent event) throws IOException {
       abrirTelaNotificacoes();
    }
    @FXML
    void OnDragEnterNotificacoes(MouseEvent event) {
         btnNotificacoes.setStyle("-fx-background-color:  D07979" );
    }
     @FXML
    void OnDragExitNotificacoes(MouseEvent event) {
         btnNotificacoes.setStyle("-fx-background-color:  DBA5A5" );
    }
    
    
//**********************************    
     private void carregarTabelaADMS() throws SQLException{
         lista = FXCollections.observableArrayList(listarADMS());
         if(!lista.isEmpty()){
             tabelaADMS.getColumns().clear();
             
            TableColumn<Administrador, Number> colunaID = new TableColumn<>("ID");
            colunaID.setCellValueFactory(u -> u.getValue().idProperty());
            colunaID.setStyle("-fx-alignment: CENTER;");
            colunaID.setPrefWidth(100);
            
            // para inserir um long precisa fazer essa transformação para object
            TableColumn<Administrador, String> colunaCPF = new TableColumn<>("CPF"); 
            colunaCPF.setCellValueFactory(u -> u.getValue().cpfProperty());
            colunaCPF.setStyle("-fx-alignment: CENTER;");
            colunaCPF.setPrefWidth(250);
            
            TableColumn<Administrador, String> colunaNome = new TableColumn<>("Nome");
            colunaNome.setCellValueFactory(u -> u.getValue().nomeProperty());
            colunaNome.setStyle("-fx-alignment: CENTER;");
            colunaNome.setPrefWidth(250);
            
            TableColumn<Administrador, String> colunaApelido = new TableColumn<>("Usuário");
            colunaApelido.setCellValueFactory(u -> u.getValue().apelidoProperty());
            colunaApelido.setStyle("-fx-alignment: CENTER;");
            colunaApelido.setPrefWidth(250);
            
            TableColumn<Administrador, String> colunaEmail = new TableColumn<>("Email");
            colunaEmail.setCellValueFactory(u -> u.getValue().emailProperty());
            colunaEmail.setStyle("-fx-alignment: CENTER;");
            colunaEmail.setPrefWidth(250);
           
            
            tabelaADMS.getColumns().addAll(colunaID, colunaCPF, colunaNome, colunaApelido, colunaEmail);
            
            
            FilteredList<Administrador> listaFiltrada = new FilteredList<>(lista, p -> true);
            tfPesquisa.textProperty().addListener((obs, oldVal, newVal) -> {
                listaFiltrada.setPredicate( adm -> {
                    if(newVal == null || newVal.isEmpty()){
                        return true;
                    }
                    String filtro = newVal.toLowerCase();
                    return adm.getNome().toLowerCase().contains(filtro)
                            //|| adm.getCpf().toLowerCase().contains(filtro)
                            || adm.getEmail().toLowerCase().contains(filtro);
                            
                });
            });
            SortedList<Administrador> listaOrdenada = new SortedList<>(listaFiltrada);
            listaOrdenada.comparatorProperty().bind(tabelaADMS.comparatorProperty());
                tabelaADMS.setItems(listaOrdenada);

            //tabelaADMS.setItems(lista);
         }
     }
     
     void ajustarElementosJanela() throws SQLException {
        carregarTabelaADMS();
    }
    
     private ObservableList<Administrador> listarADMS() throws SQLException {
        AdministradorDAO admDAO = new AdministradorDAO();
        return admDAO.listarAdministradores(adm);
    }
    
    
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
            stageADMS.close();
            
    }
    
    private void abrirTelaNoticia() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/TelaNoticia.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastroNoticia = new Stage();
        
            TelaNoticiaController tnc = loader.getController();    
            //tnc.setStage(stageCadastroADM);
        
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
            stageADMS.close();
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
            stageADMS.close();
    }
    
    private void abrirTelaNotificacoes() throws IOException{
        
         URL url = new File("src/main/java/view/Notificacoes.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageNotificacoes = new Stage();
        
            NotificacoesController nc = loader.getController();  
            nc.setAdministrador(adm);
            nc.setStage(stageNotificacoes);
            
            stageNotificacoes.setOnShown(evento -> {
             try {
                 nc.ajustarElementosJanela();
             } catch (SQLException ex) {
                 Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
             }
        });
        
            Scene cena = new Scene(root);
            stageNotificacoes.setTitle("Tela notificações");
            stageNotificacoes.setScene(cena);
            //deixa a tela maximizada
            stageNotificacoes.setMaximized(true);
            
            stageNotificacoes.show();
            stageADMS.close();
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
            stageADMS.close();
    }
    private void abrirTelaPrincipal() throws IOException{
     URL url = new File("src/main/java/view/TelaPrincipalAdministradorTeste.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalAdministradorController tpa = loader.getController();    
            tpa.setStage(stagePrincipal);
            tpa.setAdministrador(adm);
           stagePrincipal.setOnShown(evento -> {
            tpa.ajustarElementosJanela(adm);});
           
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal Administrador");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
            stageADMS.close();
    }
    
    @FXML
    void TableViewClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) {
            this.adm = tabelaADMS.getSelectionModel().getSelectedItem();
            if (this.adm != null) {
                URL url = new File("src/main/java/view/ADM.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();

                Stage stageADM = new Stage();

                ADMController ac = loader.getController();

                ac.setStage(stageADM);
                ac.setAdministrador(adm);

                stageADM.setOnShown(evento -> {
                    //ac.ajustarElementosJanela(this.adm);
                });
                
                

                Scene scene = new Scene(root);

                stageADM.setTitle("Administrador selecionado");
                stageADM.setScene(scene);
                stageADM.show();
                ac.setOnADMDesativado(() -> {
                    try{
                        carregarTabelaADMS();
                        
                    } catch (SQLException ex){
                        
                    }
                });
            }
        }
    }
     
    
    
}
