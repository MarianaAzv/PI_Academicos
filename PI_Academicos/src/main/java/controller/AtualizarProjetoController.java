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
import java.time.format.DateTimeParseException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AreasConhecimento;
import model.AreasConhecimentoDAO;
import model.Campus;
import model.CampusDAO;
import model.Coordenador;
import model.Foto;
import model.Projeto;
import model.ProjetoDAO;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;
import util.Apenasletras;
import util.Origem;

public class AtualizarProjetoController implements INotificacaoAlert {

    private Stage stageAtualizarProjeto;
    Projeto projeto;
    Campus campus;
    Coordenador coordenador;
    AreasConhecimento areaconhecimento;
    private File arquivoSelecionado = null;

    @FXML
    private ComboBox<Campus> CBcampus;
    @FXML
    private ComboBox<AreasConhecimento> CBcategoria;

    @FXML
    private Label LblDatadeinicio;

    @FXML
    private Button btnAdicionarBolsista;

    @FXML
    private Button btnAdicionarCocoordenador;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDesativarBolsista;

    @FXML
    private Button btnDesativarCocoordendor;

    @FXML
    private ImageView imgFotoProjeto;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblBolsista;

    @FXML
    private Label lblCampus;

    @FXML
    private Label lblCategoria;

    @FXML
    private Label lblCocoordenador;

    @FXML
    private Label lblDatadeFim;

    @FXML
    private Label lblEdital;

    @FXML
    private Label lblNomedoprojeto;

    @FXML
    private Label lblProrrogacao;

    @FXML
    private Label lblResumo;

    @FXML
    private Label lbltitulocriarprojeto;

    @FXML
    private TextField txtDatadeFim;

    @FXML
    private TextField txtDatadeInicio;

    @FXML
    private TextField txtEdital;

    @FXML
    private TextField txtNomedoProjeto;

    @FXML
    private TextField txtProrrogacao;

    @FXML
    private TextArea txtResumo;

    //------------------------*Clicks*----------------------------------//
    @FXML
    void OnClickAdicionarBolsista(ActionEvent event) throws IOException {
        AdicionarBolsista();
    }

    @FXML
    void OnClickAdicionarCocoordenador(ActionEvent event) throws IOException {
        mostrarAviso("Erro", "Esse botão não abre!");
    }

    @FXML
    void OnClickAtualizar(ActionEvent event) throws IOException {
        if (arquivoSelecionado == null && projeto.getFotoPerfil() == null) {
            alerta("Por favor, selecione uma imagem para o projeto.", 2, "Imagem obrigatória");
            return;
        }
        if (!Apenasletras.isLetras(txtNomedoProjeto.getText())) {
            alerta("Nome do projeto inválido", 2, "Erro");
            return;
        }
        try {
            if (txtNomedoProjeto.getText().isEmpty() || txtResumo.getText().isEmpty() || CBcategoria.getValue() == null || txtDatadeInicio.getText().isEmpty() || txtDatadeFim.getText().isEmpty() || CBcampus.getValue() == null || txtEdital.getText().isEmpty()) {
                alerta("Por favor inserir todos os campus", 1, "Erro");
                return;
            }
//            if (arquivoPDF == null) {
//                alerta("Você deve selecionar um arquivo PDF antes de submeter.",2,"PDF obrigatório");
//                return;
//            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dI = LocalDate.parse(txtDatadeInicio.getText(), formatter);
            LocalDate dF = LocalDate.parse(txtDatadeFim.getText(), formatter);

            Campus campusnomeSelecionado = CBcampus.getValue();
            AreasConhecimento areacnhecimentoselecionado = CBcategoria.getValue();
            this.areaconhecimento = areacnhecimentoselecionado;

            if (projeto.getAreaConhecimento() != null) {
                CBcategoria.setValue(projeto.getAreaConhecimento());
            }
            LocalDate prorrogacao = null;
            if (!txtProrrogacao.getText().isEmpty()) {
                prorrogacao = LocalDate.parse(txtProrrogacao.getText(), formatter);
                if (!prorrogacao.isAfter(dF)) {
                    alerta("A data de prorrogação deve ser posterior à data de fim.", 2, "Data inválida");
                    return;
                }

            }

            if (!dF.isAfter(dI)) {
                alerta("A data de fim deve ser posterior á data de inicio", 2, "Data inválida");
                return;
            }
            if (!Apenasletras.isLetras(txtNomedoProjeto.getText())) {
                alerta("O nome do projeto deve conter somente letras", 2, "Nome inválido");
                return;
            }
           
//       
            System.out.print("Coordenador no atualizar" + coordenador);
            atualizarProjeto(projeto.getIdProjeto(), txtNomedoProjeto.getText(), txtResumo.getText(), campusnomeSelecionado, txtEdital.getText(), dI, dF, prorrogacao, areaconhecimento);
//            }
            voltarapaginainicial();
        } catch (SQLException e) {
            e.printStackTrace();
            alerta("A falha em atualizar esse projeto", 2, "Falha");
        } catch (DateTimeParseException e) {
            alerta("O formato das datas nao esta como o esperado", 2, "Falha");
        }
        //Depois que as coisas tiverem setads na tela do coordenador, testar se esta atualizando

    }

    @FXML
    void OnClickDesativarBolsista(ActionEvent event) throws IOException {
        DesativarBolsista();
    }

    @FXML
    void OnClickDesativarCocoordenador(ActionEvent event) throws IOException {
        alerta("Esse botão não abre!", 1, "Erro");
    }

    @FXML
    void onClickFotoProjeto(MouseEvent event) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de Imagem", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        arquivoSelecionado = fileChooser.showOpenDialog(imgFotoProjeto.getScene().getWindow());

        if (arquivoSelecionado != null) {
            String urlImagem = arquivoSelecionado.toURI().toURL().toString();
            Image fotoEscolhida = new Image(urlImagem);
            imgFotoProjeto.setImage(fotoEscolhida);
        } else {
            System.out.println("Nenhum arquivo foi selecionado");
        }
    }

    //----------------------------*Sets*----------------------------------//
    public void setStage(Stage telaAtualizarProjeto) {
        this.stageAtualizarProjeto = telaAtualizarProjeto;

    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
        System.out.print("O coordenador esta sendo set" + coordenador);
    }

    public void setProjeto(Projeto pro) {
        this.projeto = pro;
        txtNomedoProjeto.setText(projeto.getTitulo());
        txtResumo.setText(projeto.getResumo());
        txtEdital.setText(projeto.getEdital());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String DataInicio = projeto.getDataInicio().format(formatter);
        txtDatadeInicio.setText(DataInicio);
        String DataFim = projeto.getDataFim().format(formatter);
        txtDatadeFim.setText(DataFim);
        if (projeto.getProrroacao() != null) {
            String prorroga = projeto.getProrroacao().format(formatter);
            txtProrrogacao.setText(prorroga);
        } else {
            txtProrrogacao.clear();
        }
        if (projeto.getCampus() != null) {
            CBcampus.setValue(projeto.getCampus());
            System.out.print("Set campus atualizar");
        }
        if (projeto.getAreaConhecimento() != null) {
            CBcategoria.setValue(areaconhecimento);
        }
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
        imgFotoProjeto.setImage(image);

    }

    //---------------------------*Metodos*------------------------------------//
    void atualizarProjeto(int idProjeto, String titulo, String resumo, Campus campus, String edital, LocalDate dataInicio, LocalDate dataFim, LocalDate prorrogacao, AreasConhecimento areaconhecimento) throws SQLException, IOException {

        Foto fotoPerfil = new Foto();
        if (arquivoSelecionado == null) {// caso o adm não queira alterar foto
            fotoPerfil.setDadosImagem(projeto.getFotoPerfil().getDadosImagem());
        } else {
            byte[] conteudoImagem = Files.readAllBytes(arquivoSelecionado.toPath());
            fotoPerfil.setDadosImagem(conteudoImagem);
        }

        ProjetoDAO pdao = new ProjetoDAO();

        projeto.setTitulo(titulo);
        projeto.setResumo(resumo);
        projeto.setCampus(campus);
        projeto.setEdital(edital);
        projeto.setDataInicio(dataInicio);
        projeto.setDataFim(dataFim);
        projeto.setProrroacao(prorrogacao);
        projeto.setAreaConhecimento(areaconhecimento);
        projeto.setFotoPerfil(fotoPerfil);

        pdao.atualizarProjeto(projeto);

        alerta("O projeto foi alterado com sucesso!", 3, "Projeto alterado");
        System.out.print("O projeto de atualizando");

    }

    public void ajustarElementosJanela() throws IOException {
        //ArrayList para set dos nomes dos campus no combo box de campus
        try {
            CampusDAO cdao = new CampusDAO();
            List<Campus> listCampus = cdao.buscarCampus();

            CBcampus.getItems().addAll(listCampus);

            if (projeto != null && projeto.getCampus() != null) {
                CBcampus.setValue(projeto.getCampus());
                System.out.print("Set atualizar");
            }

            AreasConhecimentoDAO acdao = new AreasConhecimentoDAO();
            List<AreasConhecimento> listCategorias = acdao.buscarCategorias();

            CBcategoria.getItems().addAll(listCategorias);

            if (projeto != null && projeto.getAreaConhecimento() != null) {
                CBcategoria.setValue(projeto.getAreaConhecimento());
                System.out.print("Set atualizar area de conhecimento");
            }

        } catch (SQLException e) {
 e.printStackTrace(); 
            alerta("A falha de comunicação entre o sistema e o Banco", 1, "Banco de Dados");
        }
    }

    public void voltarapaginainicial() throws MalformedURLException, IOException {
        URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stagePrincipal = new Stage();

        TelaPrincipalCoordenadorController tpc = loader.getController();
        tpc.setStagePrincipal(stagePrincipal);
        tpc.setCoordenador(coordenador);
        System.out.print("Coordenador VoltarPagiana" + coordenador);
        tpc.setProjeto(projeto);

        stagePrincipal.setOnShown(evento -> {
            tpc.ajustarElementosJanela(coordenador, projeto);
        });

        Scene cena = new Scene(root);
        stagePrincipal.setTitle("Tela principal Coordenador");
        stagePrincipal.setScene(cena);
        //deixa a tela maximizada
        stagePrincipal.setMaximized(true);
        System.out.print("Estamos indo para a pagina do coordenador");
        stagePrincipal.show();
        stageAtualizarProjeto.close();
    }

    public void AdicionarBolsista() throws IOException {
        URL url = new File("src/main/java/view/MaisBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageMaisBolsista = new Stage();

        MaisBolsistaController mbc = loader.getController();
        mbc.setCoordenador(coordenador);
        mbc.setProjeto(projeto);
        mbc.setStage(stageMaisBolsista);
        mbc.setOrigem(Origem.atualizar_projeto);

        Scene cena = new Scene(root);
        stageMaisBolsista.setTitle("Adicionar bolsistas");
        stageMaisBolsista.setScene(cena);
        //deixa a tela maximizada

        stageMaisBolsista.show();

    }

    public void DesativarBolsista() throws IOException {
        URL url = new File("src/main/java/view/BolsistaDesativarCoordenador.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageBolsistaDesativar = new Stage();

        BolsistaDesativarCoordenadorController bdcc = loader.getController();
        //mbc.setCoordenador(coordenador);
        bdcc.setProjeto(projeto);
        bdcc.setStage(stageBolsistaDesativar);

        Scene cena = new Scene(root);
        stageBolsistaDesativar.setTitle("Desativar bolsistas");
        stageBolsistaDesativar.setScene(cena);

        stageBolsistaDesativar.show();

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
