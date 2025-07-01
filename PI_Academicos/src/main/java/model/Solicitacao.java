package model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Solicitacao {

    private int idSolicitacao;
    private Usuario usuario;
    private int idUsuario;
    private LocalDateTime data;
    private String descricao;
    private boolean aceitacao;
    private File anexo;

    
    public Solicitacao(int idSolicitacao, Usuario usuario, LocalDateTime data, String descricao, boolean aceitacao, File anexo) {
        this.idSolicitacao = idSolicitacao;
        this.usuario = usuario;
        this.anexo = anexo;

    }
    public Solicitacao(int idSolicitacao, Usuario usuario, File anexo) {
        this.idSolicitacao = idSolicitacao;
        this.usuario = usuario;
        this.anexo = anexo;

    }

    //id
    public Solicitacao(int idSolicitacao, int idUsuario, File anexo) {
        this.idSolicitacao = idSolicitacao;
        this.idUsuario = idUsuario;
        this.anexo = anexo;

    }

    public Solicitacao(Usuario usuario, File anexo) {
        this.usuario = usuario;
        this.anexo = anexo;

    }
    //id

    public Solicitacao(int idUsuario, File anexo) {
        this.idUsuario = idUsuario;
        this.anexo = anexo;

    }

    public Solicitacao(Usuario usuario, String descricao, File anexo) {
        this.usuario = usuario;
        this.descricao = descricao;
        this.anexo = anexo;

    }

    //id      
    public Solicitacao(int idUsuario, String descricao, File anexo) {
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.anexo = anexo;

    }

    public Solicitacao() {

    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAceitacao(boolean aceitacao) {
        this.aceitacao = aceitacao;
    }

    public void setAnexo(File anexo) {
        this.anexo = anexo;
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAceitacao() {
        return aceitacao;
    }

    public File getAnexo() {
        return anexo;
    }
    
    private transient IntegerProperty idPropertyS;

    public IntegerProperty idPropertyS() {
        if (idPropertyS == null) {
            idPropertyS = new SimpleIntegerProperty(idSolicitacao);
        }
        return idPropertyS;
    }
    
    public transient IntegerProperty idPropertyU;

    public IntegerProperty idPropertyU() {
        if (idPropertyU == null) {
            idPropertyU = new SimpleIntegerProperty(idUsuario);
        }
        return idPropertyU;
    }
    
     private transient StringProperty descricaoProperty;

    public StringProperty descricaoProperty() {
        if (descricaoProperty == null) {
            descricaoProperty = new SimpleStringProperty(descricao);
        }
        return descricaoProperty;
    }
    
    private transient BooleanProperty aceitacaoProperty;
    
    public BooleanProperty aceitacaoProperty() {
        if (aceitacaoProperty == null) {
            aceitacaoProperty = new SimpleBooleanProperty(aceitacao);
        }
        return aceitacaoProperty;
    }
    
    private transient StringProperty dataProperty;
    public StringProperty dataProperty() {
        if (dataProperty == null) {
            dataProperty = new SimpleStringProperty(data.toString());
        }
        return dataProperty;
    }
    
    

    private Connection conectarDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
