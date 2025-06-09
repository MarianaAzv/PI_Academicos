package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bolsista;
import model.BolsistaDAO;
import model.Projeto;
import util.AlertaUtil;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarPerfilBolsistaController {

    private Stage stageAtualizarBolsista;
    private Bolsista bolsista;
    Projeto projeto;
    private BolsistaDAO bolsistaDAO = new BolsistaDAO();
    private boolean ativa = true;

    @FXML
    private Text txtNomeUsuario;
    
    @FXML
    private Text  txtNomeProjeto;

    @FXML
    private Button btnAtualizarPerfil, btnSair;

    @FXML
    private Label lblCPF, lblCurso, lblEmail, lblMatricula, lblNome, lblDataInicio, lblDataFim;

    @FXML
    private TextField txtCPF, txtCurso, txtEmail, txtMatricula, txtNome, txtSenha, txtUsuario, txtDataInicio, txtDataFim;

    public void setStage(Stage stageAtualizarBolsista) {
        this.stageAtualizarBolsista = stageAtualizarBolsista;
    }

    public void setBolsista(Bolsista bol) {
        if (bol != null) {
            this.bolsista = bol;
            txtNome.setText(bolsista.getNome());
            txtUsuario.setText(bolsista.getApelido());
            txtCPF.setText(String.valueOf(bolsista.getCpf()));
            txtCurso.setText(bolsista.getCurso());
            txtSenha.setText(bolsista.getSenha());
            txtEmail.setText(bolsista.getEmail());
            txtMatricula.setText(String.valueOf(bolsista.getMatricula()));

            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

              System.out.println(bolsista.getDataInicio());
            txtDataInicio.setText(bolsista.getDataInicio() != null ? bolsista.getDataInicio().format(formatter) : "Data não disponível");
             System.out.println(bolsista.getDataInicio().format(formatter));
           txtDataFim.setText(bolsista.getDataFim() != null ? bolsista.getDataFim().format(formatter) : "Data não disponível");
            
            //  Evitar erro ao acessar valores NULL
          //  txtDataInicio.setText(bolsista.getDataInicio() != null ? bolsista.getDataInicio().toString() : "Data não cadastrada");
          //  txtDataFim.setText(bolsista.getDataFim() != null ? bolsista.getDataFim().toString() : "Data não cadastrada");

           // System.out.println("Data Início carregada: " + bolsista.getDataInicio());
          //  System.out.println("Data Fim carregada: " + bolsista.getDataFim());
        } else {
            mostrarAviso("Erro", "Bolsista não encontrado.");
        }
    }

   

    @FXML
    void onClickAtualizar(ActionEvent event) throws SQLException {
        try {
            Long cpf = Long.parseLong(txtCPF.getText());
            Long matricula = Long.parseLong(txtMatricula.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            //  Tratar valores NULL antes de converter para LocalDate
            LocalDate dataInicio = txtDataInicio.getText().isEmpty() ? null : LocalDate.parse(txtDataInicio.getText(), formatter);
            LocalDate dataFim = txtDataFim.getText().isEmpty() ? null : LocalDate.parse(txtDataFim.getText(), formatter);

            atualizarBolsista(bolsista.getId(), cpf, txtNome.getText(), txtUsuario.getText(), txtEmail.getText(),
                              txtSenha.getText(), ativa, matricula, txtCurso.getText(), dataInicio, dataFim);
        } catch (NumberFormatException e) {
            mostrarAviso("Erro", "CPF, matrícula e datas devem estar em formatos válidos.");
        }
    }

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
        if (bolsista != null) {
            URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage stage = new Stage();
            TelaPrincipalBolsistaController tpb = loader.getController();

            Scene cena = new Scene(root);
            stage.setTitle("Perfil Bolsista");
            stage.setScene(cena);
            stage.setMaximized(true);
            stage.show();
            stageAtualizarBolsista.close();
        } else {
            AlertaUtil.mostrarErro("Erro", "Usuário não encontrado ou inválido.");
        }
    }

     @FXML
void onClickVerPerfil(ActionEvent event) throws IOException {
    URL url = new File("src/main/java/view/VerPerfilBolsista.fxml").toURI().toURL();
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();

    Stage stageVerPerfil = new Stage();

    VerPerfilBolsistaController vpb = loader.getController();
    vpb.setBolsista(bolsista);
    vpb.setProjeto(projeto);
    vpb.setStage(stageVerPerfil); // Correção: Garantir que a nova tela tenha controle sobre seu próprio Stage
    
    stageVerPerfil.setOnShown(evento -> {//hj
            vpb.ajustarElementosJanela(bolsista,projeto);
        });


    Scene cena = new Scene(root);
    stageVerPerfil.setTitle("Perfil Bolsista");
    stageVerPerfil.setScene(cena);
    stageVerPerfil.setMaximized(true);

    stageVerPerfil.show();
    stageAtualizarBolsista.close(); // Correção: Fechar a tela principal ao abrir a nova
}

    @FXML
    void onClickAtualizarPerfil(ActionEvent event) {
    }

     @FXML
    void onClickArtigo(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarArtigo.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageArtigo = new Stage();
        CadastrarArtigoController cab = loader.getController();
      //  cab.setBolsista(bolsista);
        cab.setStage(stageArtigo);

        Scene cena = new Scene(root);
        stageArtigo.setTitle("Bolsista Cadastro Artigo");
        stageArtigo.setMaximized(true);
        stageArtigo.setScene(cena);
        stageArtigo.show();
    }
     @FXML
    void onClickOutrosProjetos(ActionEvent event) throws MalformedURLException, IOException {
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
        stagePrincipal.setMaximized(true);
        stagePrincipal.setScene(cena);
        stagePrincipal.show();
        stageAtualizarBolsista.close();

    }

   @FXML
    void onClickPublicacao(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/CadastrarPostagem.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePostagem = new Stage();
        CadastrarPostagemController cpb = loader.getController();
       // cpb.setBolsista(bolsista);
        cpb.setStage(stagePostagem);

        Scene cena = new Scene(root);
        stagePostagem.setTitle("Bolsista Cadastro Postagem");
        stagePostagem.setMaximized(true);
        stagePostagem.setScene(cena);
        stagePostagem.show();
    }

     @FXML
    void onClickVerProjeto(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/TelaPrincipalBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stage = new Stage();
        TelaPrincipalBolsistaController tpb = loader.getController();
        tpb.setBolsista(bolsista);
        tpb.setProjeto(projeto);//hj
        tpb.setStagePrincipal(stage);
          stage.setOnShown(evento -> {
            tpb.ajustarElementosJanela(bolsista,projeto);
        });

        Scene cena = new Scene(root);
        stage.setTitle("Tela Principal Bolsista");
        stage.setScene(cena);
        stage.setMaximized(true);

        stage.show();
        stageAtualizarBolsista.close();
    }
    void setProjeto(Projeto projeto) {
       this.projeto = projeto;
       //PRECISA FAZER LISTA DE BOLSISTAS E COORDENADORES
     //  txtNomeBolsista.setText(projeto.getBolistas)
     txtNomeProjeto.setText(projeto.getTitulo());//falta foto
     
    }
    
     void atualizarBolsista(int id, long cpf, String nome, String apelido, String email, String senha, boolean ativa,
                           long matricula, String curso, LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        Bolsista bolsista = new Bolsista(id, cpf, nome, apelido, email, senha, ativa, matricula, curso, dataInicio, dataFim);

        int repetido = bolsistaDAO.validarApelido(apelido, id);

        if (repetido > 0) {
            mostrarAviso("Nome de usuário indisponível", "Este nome de usuário já está sendo usado.");
        } else {
            bolsistaDAO.atualizarBolsista(bolsista, projeto);
            mostrarConfirmacao("Usuário alterado", "O usuário foi alterado com sucesso!");
        }
    }
      void ajustarElementosJanela(Bolsista bolsista, Projeto projeto) {
        this.bolsista = bolsista;
        this.projeto = projeto;
                 txtNomeUsuario.setText(bolsista.getNome());//falta foto
      }
     
}