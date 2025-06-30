package controller;

import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Solicitacao;
import model.SolicitacaoDAO;
import static util.AlertaUtil.mostrarConfirmacao;

public class SolicitacaoTelaController {
    
    private Stage stageSol;
    private Solicitacao sol;
    //private Administrador adm;
    private Runnable onSolAceitacao;
    
    public void setStage(Stage stageSol) {
        this.stageSol = stageSol;
    }

    @FXML
    private Button btnDesativar;

    @FXML
    private Label lblAceitacao;

    @FXML
    private Label lblIDSol;

    @FXML
    private Label lblIDUsu;

    @FXML
    private Label lblSol;
    
    public void setOnSolAceitacao(Runnable callback){
        this.onSolAceitacao = callback;
    }

    @FXML
    void onClickDesativar(ActionEvent event) throws SQLException {
        
            new SolicitacaoDAO().ativarSolicitacao(sol);

        if(onSolAceitacao != null){
           onSolAceitacao.run();
        }
       
        stageSol.close();

    }
    
    public void setSolicitacao(Solicitacao sol) {
       this.sol = sol;
      
       String idSol = String.valueOf(sol.getIdSolicitacao());
       lblIDSol.setText(idSol);
       String idUsu = String.valueOf(sol.getIdUsuario());
       lblIDUsu.setText(idUsu);
       lblSol.setText(sol.getDescricao());
  
       
    }
    
    

}
