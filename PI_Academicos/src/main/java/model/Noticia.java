package model;

import java.time.LocalDateTime;

public class Noticia {
    
    private int id;
    private int idAdministrador; 
    private String titulo;
    private String texto;
    private LocalDateTime data;
    private String linkImagem;

    //construtor com todos os atributos
    public Noticia(int id, int idAdministrador, String titulo, String texto, String linkImagem, LocalDateTime data) {
        this.id = id;
        this.idAdministrador = idAdministrador;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
        this.linkImagem = linkImagem;
    }

    //construtor sem id
    public Noticia(int idAdministrador, String titulo, String texto, String linkImagem, LocalDateTime data) {
        this.idAdministrador = idAdministrador;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
        this.linkImagem = linkImagem;
    }
    
    //construtor sem id e data
    public Noticia(int idAdministrador, String titulo, String texto, String linkImagem) {
        this.idAdministrador = idAdministrador;
        this.titulo = titulo;
        this.texto = texto;
        this.linkImagem = linkImagem;
    }
    public Noticia(String linkImagem) {
        this.linkImagem = linkImagem;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setLinkImagem(String linkImagem) {
        this.linkImagem = linkImagem;
    }

    public int getId() {
        return id;
    }
    

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getLinkImagem() {
        return linkImagem;
    }
    
    @Override
    public String toString() {
        return "Foto{" +
               "id=" + id +
               ", link='" + linkImagem + '\'' +
                ", data=" + data +
               '}';
    }

    
}
