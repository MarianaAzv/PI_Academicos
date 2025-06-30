package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Administrador;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import model.AdministradorDAO;
import model.Solicitacao;
import model.SolicitacaoDAO;

public class NotificacoesController {
    
    private Stage stageNotificacoes;
    private Administrador adm;
    private Solicitacao sol;
    ObservableList<Solicitacao> lista;
    
     
    public void setAdministrador(Administrador adm) {
        this.adm = adm;
        lblNomeAdm.setText(adm.getNome());
    }

    public void setStage(Stage stageNotificacoes) {
        this.stageNotificacoes = stageNotificacoes;
        }

  
    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnADM;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnNaoValidados;

    @FXML
    private Button btnNotificacoes;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnValidados;

    @FXML
    private Button btnVerPerfil;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Label lblNomeAdm;

    @FXML
    private Label lblNotificacoes;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private TableView<Solicitacao> tvNotificacoes;

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
    void onClickBtnNotificacoes(ActionEvent event) throws IOException {
      
    }
   
    
//********************************** 
    
    
    private ObservableList<Solicitacao> listarSol() throws SQLException, IOException {
        SolicitacaoDAO solDAO = new SolicitacaoDAO();
        return solDAO.listarSolicitacoes();
    }
    
     private void carregarTabelaSol() throws SQLException, IOException{
         lista = FXCollections.observableArrayList(listarSol());
         if(!lista.isEmpty()){
             tvNotificacoes.getColumns().clear();
             
            TableColumn<Solicitacao, Number> colunaIDSol = new TableColumn<>("ID solicitação");
            colunaIDSol.setCellValueFactory(u -> u.getValue().idPropertyS());
            colunaIDSol.setStyle("-fx-alignment: CENTER;");
            colunaIDSol.setPrefWidth(200);
            
            
            TableColumn<Solicitacao, String> colunaDescricao = new TableColumn<>("Descrição");
            colunaDescricao.setCellValueFactory(u -> u.getValue().descricaoProperty());
            colunaDescricao.setStyle("-fx-alignment: CENTER;");
            colunaDescricao.setPrefWidth(750);
            
            TableColumn<Solicitacao, Boolean> colunaAceitacao = new TableColumn<>("Aceitação");
            colunaAceitacao.setCellValueFactory(u-> u.getValue().aceitacaoProperty());
            colunaAceitacao.setStyle("-fx-alignment: CENTER;");
            colunaAceitacao.setPrefWidth(250);
            
            tvNotificacoes.getColumns().addAll(colunaIDSol, colunaDescricao, colunaAceitacao);
            
            
//            FilteredList<Solicitacao> listaFiltrada = new FilteredList<>(lista, p -> true);
//            tfPesquisa.textProperty().addListener((obs, oldVal, newVal) -> {
//                listaFiltrada.setPredicate( adm -> {
//                    if(newVal == null || newVal.isEmpty()){
//                        return true;
//                    }
//                    String filtro = newVal.toLowerCase();
//                    return adm.getIdSolicitacao().toLowerCase().contains(filtro);
//                           
//                            
//                });
//            });
//            SortedList<Solicitacao> listaOrdenada = new SortedList<>(listaFiltrada);
//            listaOrdenada.comparatorProperty().bind(tvNotificacoes.comparatorProperty());
//                tvNotificacoes.setItems(listaOrdenada);

            tvNotificacoes.setItems(lista);
         }
     }
     
    void ajustarElementosJanela() throws SQLException, IOException {
        carregarTabelaSol();
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
            stageNotificacoes.close();
            
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
            stageNotificacoes.close();
    }
     
    private void abrirTelaADMS() throws IOException{
        
         URL url = new File("src/main/java/view/Administradores.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageADMS = new Stage();
        
            AdministradoresController ac = loader.getController();  
            ac.setAdministrador(adm);
            ac.setStage(stageADMS);
            
            stageADMS.setOnShown(evento -> {
            try {
                ac.ajustarElementosJanela();
            } catch (SQLException ex) {
                Logger.getLogger(TelaPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
            Scene cena = new Scene(root);
            stageADMS.setTitle("Tela Administradores");
            stageADMS.setScene(cena);
            //deixa a tela maximizada
            stageADMS.setMaximized(true);
            
            stageADMS.show();
            stageNotificacoes.close();
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
            stageNotificacoes.close();
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
            stageNotificacoes.close();
    }
    private void abrirTelaPrincipal() throws IOException{
     URL url = new File("src/main/java/view/TelaPrincipalAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalAdministradorController tpa = loader.getController();    
            tpa.setStage(stagePrincipal);
            tpa.setAdministrador(adm);
           stagePrincipal.setOnShown(evento -> {
            tpa.ajustarElementosJanela(adm);});
           
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
            stageNotificacoes.close();
    }
    
    @FXML
    void TableViewClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) {
            sol = tvNotificacoes.getSelectionModel().getSelectedItem();
            if (this.sol != null) {
                URL url = new File("src/main/java/view/SolicitacaoTela.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();

                Stage stageSol = new Stage();

                SolicitacaoTelaController stc = loader.getController();

                stc.setStage(stageSol);
                stc.setSolicitacao(sol);

//                stageSol.setOnShown(evento -> {
//                    stc.ajustarElementosJanela(this.adm);
//                });
                
                

                Scene scene = new Scene(root);

                stageSol.setTitle("Solicitaçao selecionada");
                stageSol.setScene(scene);
                stageSol.show();
                stc.setOnSolAceitacao(() -> {
                    try {
                        carregarTabelaSol();
                    } catch (SQLException ex) {
                        Logger.getLogger(NotificacoesController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(NotificacoesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                    
                });
            }
        }
    }

    

}
