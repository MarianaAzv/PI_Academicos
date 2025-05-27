package model;

import java.io.File;

public class Foto {
    
    private int id;
    private File arquivo;
    private int idPostagem;
    
    public Foto(int id, File arquivo, int idPostagem){
        this.id = id;
        this.arquivo = arquivo;
        this.idPostagem = idPostagem;    
    }
    
    public Foto( File arquivo, int idPostagem){
        this.arquivo = arquivo;
        this.idPostagem = idPostagem;    
    }

    public int getId() {
        return id;
    }

    public File getArquivo() {
        return arquivo;
    }

    public int getIdPostagem() {
        return idPostagem;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public void setIdPostagem(int idPostagem) {
        this.idPostagem = idPostagem;
    }
    
    
    
    
}
