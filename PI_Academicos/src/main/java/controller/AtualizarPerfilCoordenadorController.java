package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Coordenador;
import model.CoordenadorDAO;
import model.Projeto;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarPerfilCoordenadorController {

    private Stage stageAtualizarCoordenador;
    Coordenador coordenador;
    Projeto projeto;
     
    public void setStage(Stage stage){
    this.stageAtualizarCoordenador = stage;
    }
    
    private ArrayList<String> dados;
    
    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizar;

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
    private ImageView imgFotoCoordenador;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgProjeto;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFormacao;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblSIAPE;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFormacao;

    @FXML
    private TextField txtNome;

    @FXML
    private Text txtNomeProjeto;

    @FXML
    private TextField txtSIAPE;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    private Button btnDesativar;
    
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
        //AbrirTelaAtualizarPerfil();
    }
    @FXML
    void OnEnterAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color: D07979" );
    }
     @FXML
    void OnExitAtualizarPerfil(MouseEvent event) {
        btnAtualizarPerfil.setStyle("-fx-background-color:  D07979" );
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
        AbrirTelaPrincipal();
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
    //******************************************************************
    @FXML
    void onClickAtualizar(ActionEvent event) throws SQLException, IOException {
        
        try{
        Long cpf = Long.parseLong(txtCPF.getText());
        int siape = Integer.parseInt(txtSIAPE.getText()); 
        atualizarCoordenador(coordenador.getId(),cpf,txtNome.getText(),txtUsuario.getText(),txtEmail.getText(),txtSenha.getText(), siape, txtFormacao.getText());
        }catch(NumberFormatException n){
            mostrarAviso("CPF ou SIAPE inválidos","Os valores inseridos para CPF e SIAPE devem ser apenas números");
        }
        AbrirTelaPrincipal();
    }
     //******************************************************************
     @FXML
    void onClickDesativar(ActionEvent event) throws IOException, SQLException {
        desativar();
    }
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
            stageAtualizarCoordenador.close();
            
    }


    private void AbrirTelaPrincipal() throws IOException {

         URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageLogin = new Stage();

        TelaPrincipalCoordenadorController tpcc = loader.getController();
        tpcc.setStage(stageLogin); 
        tpcc.setCoordenador(coordenador);
        tpcc.setProjeto(projeto);

        Scene cena = new Scene(root);
        stageLogin.setTitle("Tela de principal");
        stageLogin.setScene(cena);
        //deixa a tela maximizada
        stageLogin.setMaximized(true);
        stageLogin.show();
        
        stageAtualizarCoordenador.close();
        

    }

    private void abrirTelaAtualizarProjeto() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/AtualizarProjeto.fxml").toURI().toURL();       
      FXMLLoader loader = new FXMLLoader(url);
       
      Parent root = loader.load();
        
     Stage stageAtualizarProjeto = new Stage();
        
       AtualizarProjetoController apc = loader.getController();
        
        apc.setStage(stageAtualizarProjeto);
        apc.setProjeto(projeto);
        
          stageAtualizarProjeto.setOnShown(evento -> {
        apc.ajustarElementosJanela();
      });
        
      Scene cena = new Scene(root);
       stageAtualizarProjeto.setTitle("Atualizar Projeto");
       stageAtualizarProjeto.setMaximized(true);
        stageAtualizarProjeto.setScene(cena);
       stageAtualizarProjeto.show();
        stageAtualizarCoordenador.close();

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
        
        stageAtualizarCoordenador.close();
        

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
        stageProjetos.show();
        stageProjetos.setOnShown(evento -> {
             try {
                 tpc.OnClickProjeto();
             } catch (SQLException ex) {
                 Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
      });
    }

    void ajustarElementosJanela(Coordenador coordenador, Projeto projeto) {
        this.coordenador=coordenador;
        this.projeto=projeto;
        setProjeto(projeto);
        
        System.out.println("Aqui chegam os parâmetros do login "
                + coordenador.getNome() + " - " + coordenador.getSiape() + "ATIVA: " + coordenador.getAtiva());
        
        String siape = String.valueOf(coordenador.getSiape());       
        if(coordenador.getAtiva()==false){       
        
        
        }
       
    }
    
    public void setProjeto(Projeto projeto) {
       this.projeto = projeto;
       txtNomeProjeto.setText(projeto.getTitulo());
    }
    
    
    public void desativar() throws IOException, SQLException {

            if(coordenador.getAtiva()==true){
            Optional<ButtonType> result = mostrarConfirmacao("O seu perfil está prestes a ser DESATIVADO", "Têm certeza que deseja desativar o perfil?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Usuário desativado.");
            new CoordenadorDAO().desativarCoordenador(coordenador);
        } else {
            System.out.println("Usuário cancelou a ação.");
        }
            }
            else{
                Optional<ButtonType> result = mostrarConfirmacao("O seu perfil está prestes a ser ATIVADO", "Têm certeza que deseja ativar o perfil?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Usuário ativado.");
            new CoordenadorDAO().ativarCoordenador(coordenador);
        } else {
            System.out.println("Usuário cancelou a ação.");
        }
            }
        abrirTelaVerPerfil();      
    }

    
    public void setCoordenador(Coordenador coord) {
       this.coordenador = coord;
       txtNome.setText(coordenador.getNome());
       txtUsuario.setText(coordenador.getApelido());
       String cpf = String.valueOf(coordenador.getCpf());
       txtCPF.setText(cpf);
       txtFormacao.setText(coordenador.getFormacao());
       txtSenha.setText(coordenador.getSenha());
       txtEmail.setText(coordenador.getEmail());
       String siape = String.valueOf(coordenador.getSiape());
       txtSIAPE.setText(siape);
       TxtNomeUsuario.setText(coordenador.getNome());
       
    }
    
    void atualizarCoordenador(int id,long cpf, String nome, String apelido,String email,String senha, int siape, String formacao) throws SQLException{
        
        
        Coordenador coordenador = new Coordenador(id, cpf, nome, apelido, email, senha, siape, formacao);
        int repetido = new CoordenadorDAO().validarApelido(apelido,id);
        if(repetido>0){
            mostrarAviso("Nome de usuário indisponível","Este nome de usuário já está sendo usado");
           
        }
        else{
        new CoordenadorDAO().atualizarCoordenador(coordenador);
        mostrarConfirmacao("Usuário alterado","O usuário foi alterado com sucesso!");
        }
    }    
    
   

}
