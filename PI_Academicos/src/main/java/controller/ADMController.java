package controller;

import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Administrador;
import model.AdministradorDAO;
import static util.AlertaUtil.mostrarConfirmacao;

public class ADMController {
    
    private Stage stageADM;
    private Administrador adm;
    private Runnable onADMDesativado;
    
    public void setStage(Stage stageADM) {
        this.stageADM = stageADM;
    }

    @FXML
    private Button btnDesativar;

    @FXML
    private Label lblCPFR;

    @FXML
    private Label lblEmailR;

    @FXML
    private Label lblNomeR;

    @FXML
    private Label lblUsuarioR;
    
    public void setOnADMDesativado(Runnable callback){
        this.onADMDesativado = callback;
    }
    
  

    @FXML
    void onClickDesativar(ActionEvent event) throws SQLException {
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

        if(onADMDesativado != null){
            onADMDesativado.run();
        }
       
        stageADM.close();
    }
    
    public void setAdministrador(Administrador adm) {
       this.adm = adm;
       if(adm.getAtiva()==false){
        btnDesativar.setText("Ativar");
       }
       lblNomeR.setText(adm.getNome());
       lblUsuarioR.setText(adm.getApelido());
       String cpf = String.valueOf(adm.getCpf());
       lblCPFR.setText(cpf);
       lblEmailR.setText(adm.getEmail());
       
    }

}

