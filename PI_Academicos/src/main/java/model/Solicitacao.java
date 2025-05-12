package model;

import java.io.File;
import java.time.LocalDateTime;

public class Solicitacao {
    
    private int idSolicitacao;
    private Usuario usuario;
    private LocalDateTime data;
    private String descricao;
    private boolean aceitacao;
    private File anexo;

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    
    
}
