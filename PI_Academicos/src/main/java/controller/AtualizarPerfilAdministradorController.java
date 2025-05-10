
package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import model.Administrador;
import model.AdministradorDAO;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarPerfilAdministradorController {
    
    private Administrador adm;
    private Stage stageAtualizarADM;
    
    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Text TxtNomeUsuario1;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDesativar;

    @FXML
    private GridPane btnAtualizarProjeto;

    @FXML
    private Button btnCadastrarADM;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerperfil;

    @FXML
    private ImageView imgFotoAdministrador;

    @FXML
    private ImageView imgInstituto;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNomeAdm;

    @FXML
    private Label lblNomeInstituto;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;


    @FXML
    void onClickAtualizar(ActionEvent event) throws SQLException {

        try{
        Long cpf = Long.parseLong(txtCPF.getText());
        atualizarAdministrador(adm.getId(),cpf,txtNome.getText(),txtUsuario.getText(),txtEmail.getText(),txtSenha.getText());
        }catch(NumberFormatException n){
            mostrarAviso("CPF inválido","O valor inserido para CPF deve ser apenas números");
        }
    }

    @FXML
    void onClickDesativar(ActionEvent event) throws IOException, SQLException {
        
        //mostrarConfirmacao("Usuário alterado","O usuário foi alterado com sucesso!");
        if(adm.getAtiva()==true){
            Optional<ButtonType> result = mostrarConfirmacao("O seu perfil está prestes a ser DESATIVADO", "Têm certeza que deseja desativar o perfil?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Usuário desativado.");
            new AdministradorDAO().desativarAdministrador(adm);
        } else {
            System.out.println("Usuário cancelou a ação.");
        }
        }
        else{
            Optional<ButtonType> result = mostrarConfirmacao("O seu perfil está prestes a ser ATIVADO", "Têm certeza que deseja ativar o perfil?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Usuário ativado");
            new AdministradorDAO().ativarAdministrador(adm);
        } else {
            System.out.println("Usuário cancelou a ação.");
        }
        }
        
        URL url = new File("src/main/java/view/TelaPrincipalAdministrador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePrincipal = new Stage();

        TelaPrincipalAdministradorController tpc = loader.getController();
        tpc.setStage(stagePrincipal);

        stagePrincipal.setOnShown(evento -> {
        tpc.ajustarElementosJanela(adm);
        });

        Scene cena = new Scene(root);
        stagePrincipal.setTitle("Tela principal Administrador");
        stagePrincipal.setScene(cena);
        //deixa a tela maximizada
        stagePrincipal.setMaximized(true);

        stagePrincipal.show();
        stageAtualizarADM.close();
        
    }

    @FXML
    void onClickCadastrarADM(ActionEvent event) throws IOException {
        
        abrirTelaCadastroADM();

    }

    @FXML
    void onClickPublicacao(ActionEvent event) {
        
        //abrirTelaNoticia();

    }

    @FXML
    void onClickSair(ActionEvent event) throws IOException {

        URL url = new File("src/main/java/view/TelaPrincipalAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalAdministradorController tpa = loader.getController();    
            tpa.setStage(stagePrincipal);
            
           stagePrincipal.setOnShown(evento -> {
            tpa.ajustarElementosJanela(adm);
       });
        
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal Administrador");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
            stageAtualizarADM.close();
    }

    @FXML
    void onClickVerPerfil(ActionEvent event) throws IOException {
        
        abrirTelaVerPerfilADM();

    }
    
    /////////////////METODOS///////////////////////////////////////////////////////////////////////
    
    private void abrirTelaCadastroADM() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/CadastrarAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageCadastroADM = new Stage();
        
            CadastrarAdministradorController cac = loader.getController();    
            //cac.setStage(stageCadastroADM);
        
            Scene cena = new Scene(root);
            stageCadastroADM.setTitle("Tela Cadastrar Administrador");
            stageCadastroADM.setScene(cena);
            
            stageCadastroADM.show();
            
    }
    
     private void abrirTelaVerPerfilADM() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/VerPerfilAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePerfil = new Stage();
        
            VerPerfilAdministradorController vpac = loader.getController();    
            vpac.setStage(stagePerfil);
        
            Scene cena = new Scene(root);
            stagePerfil.setTitle("Tela Perfil Administrador");
            stagePerfil.setScene(cena);
            //deixa a tela maximizada
            stagePerfil.setMaximized(true);
            
            stagePerfil.show();
            stageAtualizarADM.close();
    }
     
    private void abrirTelaAtualizar() throws MalformedURLException, IOException{
        
         URL url = new File("src/main/java/view/AtualizarPerfilAdministrador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stageAtualizar = new Stage();
        
            AtualizarPerfilAdministradorController apac = loader.getController();    
            //apac.setStage(stageAtualizar);
        
            Scene cena = new Scene(root);
            stageAtualizar.setTitle("Tela Atualizar Administrador");
            stageAtualizar.setScene(cena);
            //deixa a tela maximizada
            stageAtualizar.setMaximized(true);
            
            stageAtualizar.show();
            stageAtualizarADM.close();
    }
    
    

     public void setStage(Stage stage){
         this.stageAtualizarADM=stage;
     }
    public void setAdministrador(Administrador adm) {
       this.adm = adm;
       txtNome.setText(adm.getNome());
       txtUsuario.setText(adm.getApelido());
       String cpf = String.valueOf(adm.getCpf());
       txtCPF.setText(cpf);
       txtSenha.setText(adm.getSenha());
       txtEmail.setText(adm.getEmail());
       
    }
    
    void atualizarAdministrador(int id,long cpf, String nome, String apelido,String email,String senha) throws SQLException{
        
        
        Administrador adm = new Administrador(id, cpf, nome, apelido, email, senha);
        int repetido = new AdministradorDAO().validarApelido(apelido,id);
        if(repetido>0){
            mostrarAviso("Nome de usuário indisponível","Este nome de usuário já está sendo usado");
           
        }
        else{
        new AdministradorDAO().atualizarAdministrador(adm);
        mostrarConfirmacao("Usuário alterado","O usuário foi alterado com sucesso!");
        }
    }
    
     
    //    private void abrirTelaNoticia() throws MalformedURLException, IOException{
//        
//         URL url = new File("src/main/java/view/CadastroNoticia.fxml").toURI().toURL();
//            FXMLLoader loader = new FXMLLoader(url);
//            Parent root = loader.load();
//        
//            Stage stageCadastroNoticia = new Stage();
//        
//            CadastroNoticiaController cnc = loader.getController();    
//            //cac.setStage(stageCadastroADM);
//        
//            Scene cena = new Scene(root);
//            stageCadastroNoticia.setTitle("Tela Cadastrar Noticia");
//            stageCadastroNoticia.setScene(cena);
//            
//            stageCadastroNoticia.show();
//            
//    }
    
    
}
