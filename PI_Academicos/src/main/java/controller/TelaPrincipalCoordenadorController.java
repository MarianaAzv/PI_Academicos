/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaPrincipalCoordenadorController {

    @FXML
    private Text TxtNomeUsuario;

    @FXML
    private Button btnArtigo;

    @FXML
    private Button btnAtualizarPerfil;

    @FXML
    private Button btnAtualizarProjeto;

    @FXML
    private Button btnCriarPerfil;

    @FXML
    private Button btnOutrosProjetos;

    @FXML
    private Button btnPublicacao;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerProjeto;

    @FXML
    private Button btnVerperfil;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgPerfilProjeto;

    @FXML
    private ImageView imgProjeto;

    @FXML
    private ImageView imgUsuarioCoordenador;

    @FXML
    private Label lblNomeBolsista;

    @FXML
    private Label lblNomeCocoordenador;

    @FXML
    private Label lblNomeCoordenador;

    @FXML
    private Label lblResumo;

    @FXML
    private Text textNomeProjeto;

    @FXML
    private Text txtCampus;

    @FXML
    private Text txtFim;

    @FXML
    private Text txtInicio;

    @FXML
    private Text txtNomeBolsitas;

    @FXML
    private Text txtNomeCocoordenador;

    @FXML
    private Text txtNomeCoordenador;

    @FXML
    private Text txtNomeProjeto;

    @FXML
    private Text txtProrrogacao;
    private Stage stageLogin;
    
public void setStage(Stage stage){
        this.stageLogin = stage;
    }

}

