package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.AreasConhecimento;
import model.AreasConhecimentoDAO;
import model.Campus;
import model.CampusDAO;
import model.Coordenador;
import model.Projeto;
import model.ProjetoDAO;
import static util.AlertaUtil.mostrarAviso;
import static util.AlertaUtil.mostrarConfirmacao;

public class AtualizarProjetoController {

    private Stage stageAtualizarProjeto;
    Projeto projeto;
    Campus campus;
    Coordenador coordenador;
    AreasConhecimento areaconhecimento;

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
    void OnClickAdicionarBolsista(ActionEvent event) {

    }

    @FXML
    void OnClickAdicionarCocoordenador(ActionEvent event) throws IOException {
   AdicionarBolsista();
    }

    @FXML
    void OnClickAtualizar(ActionEvent event) throws IOException {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dI = LocalDate.parse(txtDatadeInicio.getText(), formatter);
            LocalDate dF = LocalDate.parse(txtDatadeFim.getText(), formatter);

            Campus campusnomeSelecionado = CBcampus.getValue();
            AreasConhecimento areacnhecimentoselecionado = CBcategoria.getValue();
            this.areaconhecimento = areacnhecimentoselecionado;

            LocalDate prorrogacao = null;
            if (!txtProrrogacao.getText().isEmpty()) {
                prorrogacao = LocalDate.parse(txtProrrogacao.getText(), formatter);
                 if (!prorrogacao.isAfter(dF)) {
                mostrarAviso("Data inválida", "A data de prorrogação deve ser posterior à data de fim.");
                return;
            }
            }
//       

            atualizarProjeto(projeto.getIdProjeto(), txtNomedoProjeto.getText(), txtResumo.getText(), campusnomeSelecionado, txtEdital.getText(), dI, dF, prorrogacao, areaconhecimento);
//            } 
        } catch (SQLException e) {
            mostrarAviso("Falha", "A falha em atuaizar esse projeto");
        } catch (DateTimeParseException e) {
            mostrarAviso("Falha", "O formato das datas nao esta como o esperado");
        }
        //Depois que as coisas tiverem setads na tela do coordenador, testar se esta atualizando
        voltarapaginainicial();
    }

    @FXML
    void OnClickDesativarBolsista(ActionEvent event) throws IOException {
DesativarBolsista();
    }

    @FXML
    void OnClickDesativarCocoordenador(ActionEvent event) {

    }

    
    //----------------------------*Sets*----------------------------------//
    public void setStage(Stage telaAtualizarProjeto) {
        this.stageAtualizarProjeto = telaAtualizarProjeto;

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

    }
    
    //---------------------------*Metodos*------------------------------------//

    void atualizarProjeto(int idProjeto, String titulo, String resumo, Campus campus, String edital, LocalDate dataInicio, LocalDate dataFim, LocalDate prorrogacao, AreasConhecimento areaconhecimento) throws SQLException {

        ProjetoDAO pdao = new ProjetoDAO();

        projeto.setTitulo(titulo);
        projeto.setResumo(resumo);
        projeto.setCampus(campus);
        projeto.setEdital(edital);
        projeto.setDataInicio(dataInicio);
        projeto.setDataFim(dataFim);
        projeto.setProrroacao(prorrogacao);
        projeto.setAreaConhecimento(areaconhecimento);

        pdao.atualizarProjeto(projeto);

        mostrarConfirmacao("Projeto alterado", "O projeto foi alterado com sucesso!");

    }

    public void ajustarElementosJanela() {
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

            mostrarAviso("Banco de Dados", "A falha de comunicação entre o sistema e o Banco");
        }
    }
    
    public void voltarapaginainicial() throws MalformedURLException, IOException {
          URL url = new File("src/main/java/view/TelaPrincipalCoordenador.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
        
            Stage stagePrincipal = new Stage();
        
            TelaPrincipalCoordenadorController tpc = loader.getController();    
            tpc.setStagePrincipal(stagePrincipal);
            
            Scene cena = new Scene(root);
            stagePrincipal.setTitle("Tela principal Coordenador");
            stagePrincipal.setScene(cena);
            //deixa a tela maximizada
            stagePrincipal.setMaximized(true);
            
            stagePrincipal.show();
           stageAtualizarProjeto.close();
    }
    
    public void AdicionarBolsista() throws IOException{
         URL url = new File("src/main/java/view/MaisBolsista.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage stageMaisBolsista = new Stage();

        MaisBolsistaController mbc = loader.getController();
        //mbc.setCoordenador(coordenador);
        mbc.setProjeto(projeto);
        mbc.setStage(stageMaisBolsista);

        Scene cena = new Scene(root);
        stageMaisBolsista.setTitle("Adicionar bolsistas");
        stageMaisBolsista.setScene(cena);
        //deixa a tela maximizada

        stageMaisBolsista.show();
       stageAtualizarProjeto.close();
    }
    
   public void DesativarBolsista() throws IOException{
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
       stageAtualizarProjeto.close();
   }
}
