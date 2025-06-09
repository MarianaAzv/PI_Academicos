package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.AreasConhecimento;
import model.AreasConhecimentoDAO;
import model.Campus;
import model.CampusDAO;
import model.Coordenador;
import model.CoordenadorDAO;
import model.Projeto;
import model.ProjetoDAO;
import model.Solicitacao;
import model.SolicitacaoDAO;
import model.Usuario;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;
import util.Origem;

public class CriarProjetoController {

    private Stage stageCriarProjeto;
    Coordenador coordenador;
    Projeto projeto;
    AreasConhecimento areasconhecimento;
    File arquivoPDF = null;
    private final String DIRETORIO_PDFS = Paths.get(System.getProperty("user.home"), "pdfs_baixados").toString();

    public void initialize() {
        File diretorio = new File(DIRETORIO_PDFS);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    @FXML
    private ComboBox<Campus> CBcampus;

    @FXML
    private ComboBox<AreasConhecimento> CBcategoria;

    @FXML
    private Label LblDatadeinicio;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnNaococoordenador;

    @FXML
    private Button btnPDF;

    @FXML
    private Button btnSimBolsista;

    @FXML
    private Button btnSimCocoordenador;

    @FXML
    private Button btnnaoBolsista;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblAbrirArquivo;

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
    private TextArea txtResumo;
    
    //------------------*SETs*-----------------//

    public void setStage(Stage telaCriarProjeto) {
        this.stageCriarProjeto = telaCriarProjeto;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    //---------------*OnClicks*----------------//
    @FXML
    void OnClickEnviar(ActionEvent event) throws ParseException, IOException {

        try {

            if (txtNomedoProjeto.getText().isEmpty() || txtResumo.getText().isEmpty() || txtEdital.getText().isEmpty() || txtDatadeInicio.getText().isEmpty() || txtDatadeFim.getText().isEmpty() || CBcampus.getValue() == null || CBcategoria.getValue() == null) {
                mostrarAviso("Falta informacao", "Por favor inserir todos os dados corretamente");

                return;
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dI = LocalDate.parse(txtDatadeInicio.getText(), formatter);
                LocalDate dF = LocalDate.parse(txtDatadeFim.getText(), formatter);

                if (dI.isAfter(dF)) {
                    mostrarAviso("Datas", "A data do final do projeto esta menor que a data de inicio do projeto");
                    return;
                } else {
                    Campus campusnomeSelecionado = CBcampus.getValue();
                    AreasConhecimento areacnhecimentoselecionado = CBcategoria.getValue();
                    this.areasconhecimento = areacnhecimentoselecionado;

                    incluir(txtNomedoProjeto.getText(), txtResumo.getText(), campusnomeSelecionado, txtEdital.getText(), dI, dF, null, true, coordenador.getId());
                }
            }
        } catch (SQLException e) {
            mostrarAviso("Falha", "A falha em cadastrar esse projeto");
        } catch (DateTimeParseException e) {
            mostrarAviso("Falha", "O formato das datas nao esta como o esperado");
        }

        enviarSolicitacao();

        abrirJanelaMaisBolsista();
    }

    @FXML
    void OnClickPDF(ActionEvent event) throws IOException {

        //adicao de janela para escolher arquivo pdf 
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.pdf"));
        arquivoPDF = fileChooser.showOpenDialog(btnPDF.getScene().getWindow());
        lblAbrirArquivo.setText(arquivoPDF.getName());

    }

   

    @FXML
    void onClickAbrirArquivo(MouseEvent event) {

        if (arquivoPDF != null) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(arquivoPDF);
                } else {
                    System.err.println("A funcionalidade de desktop não é suportada.");
                }
            } catch (IOException e) {
                System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum arquivo selecionado para abrir.");
        }

    }
    
    //-----------------*OnMouse*--------------
    
     @FXML
    void onMouseEnterAbrirArquivo(MouseEvent event) {
        lblAbrirArquivo.setStyle("text-decoration: underline;");
    }

    @FXML
    void onMouseExitedAbrirArquivo(MouseEvent event) {

    }
    
    //------------*Metodos*-------------

    public void ajustarElementosJanela() {
        //ArrayList para set dos nomes dos campus no combo box de campus
        try {
            CampusDAO cdao = new CampusDAO();
            List<Campus> listCampus = cdao.buscarCampus();
            CBcampus.getItems().clear();
            CBcampus.getItems().addAll(listCampus);

            AreasConhecimentoDAO acdao = new AreasConhecimentoDAO();
            List<AreasConhecimento> listCategorias = acdao.buscarCategorias();
            CBcategoria.getItems().clear();
            CBcategoria.getItems().addAll(listCategorias);

        } catch (SQLException e) {

            mostrarAviso("Banco de Dados", "A falha de comunicação entre o sistema e o Banco");
        }
    }

    public void incluir(String titulo, String resumo, Campus campus, String edital, LocalDate dataInicio, LocalDate dataFim, LocalDate prorrogacao, boolean emAndamento, int id) throws SQLException {
        projeto = new Projeto(titulo, resumo, campus, edital, dataInicio, dataFim, prorrogacao, emAndamento);
        projeto.setAreaConhecimento(areasconhecimento);

        AreasConhecimento areacnhecimentoselecionado = CBcategoria.getValue();
        this.areasconhecimento = areacnhecimentoselecionado;
        projeto.setEmAndamento(true);

        ProjetoDAO pdao = new ProjetoDAO();
        pdao.cadastraprojeto(projeto, id);
        pdao.AreaProjeto(projeto, areasconhecimento);

        mostrarConfirmacao("Projeto cadastrado", "O projeto foi registrado no sistema com sucesso!");
        stageCriarProjeto.close();

    }

    public void enviarSolicitacao() {

        if (arquivoPDF != null) {
            try {

                Usuario usuario = new Usuario();
                usuario.setId(coordenador.getId());
               
                String descricao = "Solicitação para cadstro de projeto:"
                        + "\nNome do projeto: "+ txtNomedoProjeto.getText()
                        + "\nCoordenador: " + coordenador.getNome() 
                        + "\nSIAPE: " + coordenador.getSiape() 
                        + "\ne-mail: " + coordenador.getEmail();
                Solicitacao solicitacao = new Solicitacao(usuario, descricao, arquivoPDF);
                new SolicitacaoDAO().salvarPDF(solicitacao);
                System.out.println("Arquivo PDF salvo no banco de dados.");

            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo PDF: " + e.getMessage());
            }
        }
    }

    public void abrirJanelaMaisBolsista() throws IOException {
        URL url = new File("src/main/java/view/MaisBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageMaisBolsista = new Stage();

        MaisBolsistaController mbc = loader.getController();
    //    mbc.setCoordenador(coordenador);
        mbc.setProjeto(projeto);
        mbc.setStage(stageMaisBolsista);
        mbc.setOrigem(Origem.cadastro_projeto);

        Scene cena = new Scene(root);
        stageMaisBolsista.setTitle("Adicionar bolsistas");
        stageMaisBolsista.setScene(cena);
        //deixa a tela maximizada

        stageMaisBolsista.show();
        stageCriarProjeto.close();
    }

}
