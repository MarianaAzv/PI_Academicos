package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Solicitacao;
import model.SolicitacaoDAO;
import static util.AlertaUtil.mostrarConfirmacao;

public class SolicitacaoTelaController {
    
    private Stage stageSol;
    private Solicitacao sol;
    //private Administrador adm;
    private Runnable onSolAceitacao;
    private Runnable onSolNegacao;
    
    public void setStage(Stage stageSol) {
        this.stageSol = stageSol;
    }
    
    File arquivoPDF = null;
    private final String DIRETORIO_PDFS = Paths.get(System.getProperty("user.home"), "pdfs_baixados").toString();
    
    public void initialize() {
        File diretorio = new File(DIRETORIO_PDFS);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
      }

    @FXML
    private Button btnAceitar;
    
    @FXML
    private Button btnNegar;

    @FXML
    private Label lblAceitacao;

    @FXML
    private Label lblIDSol;

    @FXML
    private Label lblIDUsu;

    @FXML
    private Label lblSol;
    
     @FXML
    private Label lblLink;
    
    public void setOnSolAceitacao(Runnable callback){
        this.onSolAceitacao = callback;
    }
    
    

    @FXML
    void onClickAceitar(ActionEvent event) throws SQLException {
        
        setSolicitacao(sol);
            new SolicitacaoDAO().ativarSolicitacao(sol);
            

        if(onSolAceitacao != null){
           onSolAceitacao.run();
        }
       
        stageSol.close();

    }
    
    @FXML
    void onClickNegar(ActionEvent event) throws SQLException {
        
            new SolicitacaoDAO().deletarSolicitacao(sol);
            
            if(onSolAceitacao != null){
           onSolAceitacao.run();
        }
       
       
        stageSol.close();

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
    
    public void setSolicitacao(Solicitacao sol) {
       this.sol = sol;
      
       String idSol = String.valueOf(sol.getIdSolicitacao());
       lblIDSol.setText(idSol);
       String idUsu = String.valueOf(sol.getIdUsuario());
       lblIDUsu.setText(idUsu);
       lblSol.setText(sol.getDescricao());
       arquivoPDF = sol.getAnexo();
       lblLink.setText(arquivoPDF.getName());
       String aceita = String.valueOf(sol.isAceitacao());
       lblAceitacao.setText(aceita);
  
       
    }
    
    @FXML
    void onEnterArquivo(MouseEvent event) {

        lblLink.setStyle("-fx-text-fill: blue" );
    }

    @FXML
    void onExitArquivo(MouseEvent event) {
        lblLink.setStyle("-fx-text-fill: black" );
    }
    
    

}
