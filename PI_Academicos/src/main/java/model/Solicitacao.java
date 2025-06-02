package model;

import java.io.File;
import java.time.LocalDateTime;

public class Solicitacao {
    
    private int idSolicitacao;
    private Usuario usuario;
    private int idUsuario;

    private LocalDateTime data;
    private String descricao;
    private boolean aceitacao;
    private File anexo;

    public Solicitacao(int idSolicitacao, Usuario usuario, File anexo){
        this.idSolicitacao=idSolicitacao;
        this.usuario=usuario;
        this.anexo=anexo;
        
    }
    //id
    public Solicitacao(int idSolicitacao, int idUsuario, File anexo){
        this.idSolicitacao=idSolicitacao;
        this.idUsuario=idUsuario;
        this.anexo=anexo;
        
    }
    
     public Solicitacao( Usuario usuario, File anexo){
        this.usuario=usuario;
        this.anexo=anexo;
        
    }
     //id
     public Solicitacao( int idUsuario, File anexo){
        this.idUsuario=idUsuario;
        this.anexo=anexo;
        
    }
     
          public Solicitacao( Usuario usuario, String descricao, File anexo){
              this.usuario=usuario;
              this.descricao=descricao;
              this.anexo=anexo;
        
    }
    //id      
    public Solicitacao(int idUsuario, String descricao, File anexo){
              this.idUsuario=idUsuario;
              this.descricao=descricao;
              this.anexo=anexo;
        
    }
          
     public Solicitacao(){
        
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
    
    
}
