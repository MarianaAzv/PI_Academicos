package model;

import java.io.File;
import java.time.LocalDate;

public class Artigo {
    
    private int id;
    private int idProjeto;   
    private String titulo;
    private String resumo;
    private String autores;
    private File arquivo;
    private LocalDate dataPublicacao;
    

    public Artigo(){
        
    }
    
    public Artigo(int id, int idProjeto, String titulo, String resumo, String autores, File arquivo, LocalDate dataPublicacao){
       this.id=id;
       this.idProjeto=idProjeto;
       this.titulo=titulo;
       this.resumo=resumo;
       this.autores=autores;
       this.arquivo=arquivo;
       this.dataPublicacao=dataPublicacao;
    }
    
    public Artigo(int idProjeto, String titulo, String resumo, String autores, File arquivo, LocalDate dataPublicacao){
       this.idProjeto=idProjeto;
       this.titulo=titulo;
       this.resumo=resumo;
       this.autores=autores;
       this.arquivo=arquivo;
       this.dataPublicacao=dataPublicacao;
    }
    
    public Artigo(int idProjeto, String titulo, String resumo, String autores, File arquivo){
       this.idProjeto=idProjeto;
       this.titulo=titulo;
       this.resumo=resumo;
       this.autores=autores;
       this.arquivo=arquivo;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }
    
    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getAutores() {
        return autores;
    }
       
    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public File getArquivo() {
        return arquivo;
    }
    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdProjeto() {
        return idProjeto;
    }
    
}
