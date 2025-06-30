package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Bolsista;
import model.BolsistaDAO;
import model.Foto;
import model.Projeto;
import util.AlertaUtil;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;
import util.ApenasNumeros;
import util.Apenasletras;
import util.CPF;
import util.Email;
import util.Senha;

public class AtualizarPerfilBolsistaController implements INotificacaoAlert {

    private Stage stageAtualizarBolsista;
    private Bolsista bolsista;
    Projeto projeto;
    private BolsistaDAO bolsistaDAO = new BolsistaDAO();
    private boolean ativa = true;
    private File arquivoSelecionado = null;

    void setProjeto(Projeto projeto) {
        this.projeto = projeto;
        
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
    }

    @FXML
    private Text TxtNomeProjetoBarra;

    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizar;

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
    private Label lblCPFBols;

    @FXML
    private Label lblCurso;

    @FXML
    private Label lblData;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblEmailBols;

    @FXML
    private Label lblFormacaoBols;

    @FXML
    private Label lblInicioDaBolsa;

    @FXML
    private Label lblInicioDaBolsa1;

    @FXML
    private Label lblMatricula;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblSIAPEBols;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSenhaBols;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblUsuarioBols;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtCurso;

    @FXML
    private TextField txtDataFim;

    @FXML
    private TextField txtDataInicio;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;

    public void setStage(Stage stageAtualizarBolsista) {
        this.stageAtualizarBolsista = stageAtualizarBolsista;
    }

    public void setBolsista(Bolsista bol) throws IOException {
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
        imgFotoBolsista.setImage(image);
        imgPerfil.setImage(image);
        
        } else {
            alerta("Bolsista não encontrado.", 1, "Erro");

        }
    }

    @FXML
    void onClickAtualizar(ActionEvent event) throws SQLException, IOException {
        if (!CPF.isValid(txtCPF.getText())) {
            alerta("CPF inválido", 2, "ERRO");
            return;
        }
        if (!Email.isValidEmail(txtEmail.getText())) {
            alerta("Email inválido", 2, "ERRO");
            return;
        }
        if (!Apenasletras.isLetras(txtNome.getText())) {
            alerta("Nome inválido", 2, "ERRO");
            return;
        }
       
        if (!Senha.senhaForte(txtSenha.getText())) {
            alerta("A senha esta muito fraca, para uma senha forte é necessario ter 6 caracters,ter pelo menos 1 letra Maiuscula e 1 Letra minuscula, um numero e um simbulo especial", 2, "ERRO");
            return;
        }
        if (!Apenasletras.isLetras(txtCurso.getText())) {
            alerta("Curso inválido", 2, "ERRO");
            return;
        }
        if (!ApenasNumeros.isNumeros(txtMatricula.getText())) {
            alerta("Matricula inválida(Somente números", 2, "ERRO");
            return;
        }

        try {

            Long matricula = Long.parseLong(txtMatricula.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            //  Tratar valores NULL antes de converter para LocalDate
            LocalDate dataInicio = txtDataInicio.getText().isEmpty() ? null : LocalDate.parse(txtDataInicio.getText(), formatter);
            LocalDate dataFim = txtDataFim.getText().isEmpty() ? null : LocalDate.parse(txtDataFim.getText(), formatter);

            if (dataFim.isAfter(projeto.getDataFim())) {
                alerta("A data de fim do bolsista não pode ser maior que a do projeto.", 2, "Data inválida");
                return;
            }
            if (dataInicio.isAfter(dataFim)) {
                alerta("A data de inicio não pode ser posterior a data de fim", 2, "Data inválida");
                return;
            }
            if (dataInicio.isBefore(projeto.getDataInicio())) {
                alerta("A data de inicio do bolsista não pode seer menor que a data de inicio do projeto", 2, "Data inválida");
                return;
            }
            if (dataFim.isBefore(projeto.getDataFim())) {
                alerta("A data de fim do bolsista não pode ser menor que a data de inicio do projeto", 2, "Data inválida");
                return;
            }
            int idCampus = projeto.getCampus().getIdCampus();
            BolsistaDAO dao = new BolsistaDAO();
            if (dao.existeMatriculaNoCampus(matricula, idCampus)) {
                alerta("Já existe um bolsista com essa matrícula neste campus.", 2, "Matrícula duplicada");
                return;
            }

            atualizarBolsista(bolsista.getId(), txtCPF.getText(), txtNome.getText(), txtUsuario.getText(), txtEmail.getText(),
                    txtSenha.getText(), ativa, matricula, txtCurso.getText(), dataInicio, dataFim);

        } catch (NumberFormatException e) {
            alerta("CPF, matrícula e datas devem estar em formatos válidos.", 2, "Erro");
        }
    }

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
    void onClickFotoPerfil(MouseEvent event) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de Imagem", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        arquivoSelecionado = fileChooser.showOpenDialog(imgFotoBolsista.getScene().getWindow());

        if (arquivoSelecionado != null) {
            String urlImagem = arquivoSelecionado.toURI().toURL().toString();
            Image fotoEscolhida = new Image(urlImagem);
            imgFotoBolsista.setImage(fotoEscolhida);
        } else {
            System.out.println("Nenhum arquivo foi selecionado");
        }

    }
    @FXML
    void onClickAtualizarPerfil(ActionEvent event) throws IOException {
        //abrirAtualizarPerfil();
    }

    @FXML
    void onEnterAtualizarPerfil(MouseEvent event) {
        //btnAtualizarPerfil.setStyle("-fx-background-color: D07979" );
    }

    @FXML
    void onExitAtualizarPerfil(MouseEvent event) {
        //btnAtualizarPerfil.setStyle("-fx-background-color:  DBA5A5" );
    }

    //******************************************************************
    @FXML
    void onClickOutrosProjetos(ActionEvent event) throws MalformedURLException, IOException {
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
        abrirTelaPrincipal();
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

    //******************* MÉTODOS ***************************************
    void atualizarBolsista(int id, String cpf, String nome, String apelido, String email, String senha, boolean ativa,
            long matricula, String curso, LocalDate dataInicio, LocalDate dataFim) throws SQLException, IOException {
        
        Foto fotoPerfil = new Foto();
        if (arquivoSelecionado == null) {// caso o adm não queira alterar foto
            fotoPerfil.setDadosImagem(bolsista.getFotoPerfil().getDadosImagem());
        } else {
            byte[] conteudoImagem = Files.readAllBytes(arquivoSelecionado.toPath());
            fotoPerfil.setDadosImagem(conteudoImagem);
        }
        Bolsista bolsista = new Bolsista(id, cpf, nome, apelido, email, senha, ativa, matricula, curso, dataInicio, dataFim, fotoPerfil);

        int repetido = bolsistaDAO.validarApelido(apelido, id);

        if (repetido > 0) {
            alerta("Este nome de usuário já está sendo usado.", 2, "Nome de usuário indisponível");

        } else {
            bolsistaDAO.atualizarBolsista(bolsista, projeto);
            setBolsista(bolsista);
            alerta("O usuário foi alterado com sucesso!", 3, "Usuário alterado");
            abrirVerPerfil();
            stageAtualizarBolsista.close();

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

        Scene cena = new Scene(root);
        stageVerPerfil.setTitle("Perfil Bolsista");
        stageVerPerfil.setScene(cena);
        stageVerPerfil.setMaximized(true);

        stageVerPerfil.show();
        stageAtualizarBolsista.close();
    }

    public void abrirAtualizarPerfil() throws IOException {
        URL url = new File("src/main/java/view/AtualizarPerfilBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageAtualizar = new Stage();
        AtualizarPerfilBolsistaController apb = loader.getController();
        apb.setBolsista(bolsista);
        apb.setProjeto(projeto);

        Scene cena = new Scene(root);
        stageAtualizar.setTitle("Atualizar Perfil Bolsista");
        stageAtualizar.setMaximized(true);
        stageAtualizar.setScene(cena);
        stageAtualizar.show();
        stageAtualizarBolsista.close();
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

    public void outrosProjetos() throws MalformedURLException, IOException {

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
        stagePrincipal.setTitle("Tela Escolher Projeto");
        stagePrincipal.setMaximized(false);
        stagePrincipal.setScene(cena);
        stagePrincipal.show();
        stageAtualizarBolsista.close();
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
        stagePrincipalBolsista.setTitle("Tela Principal");
        stagePrincipalBolsista.setScene(cena);
        stagePrincipalBolsista.setMaximized(true);

        stagePrincipalBolsista.show();
        stageAtualizarBolsista.close();

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

    }

}
