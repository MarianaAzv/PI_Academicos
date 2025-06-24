package model;

import java.time.LocalDateTime;

public class Noticia {

    private int id;
    private int idAdministrador;
    private Foto foto;
    private String titulo;
    private String texto;
    private LocalDateTime data;

    //construtor com todos os atributos
    public Noticia(int id, int idAdministrador, Foto foto, String titulo, String texto, LocalDateTime data) {
        this.id = id;
        this.idAdministrador = idAdministrador;
        this.foto = foto;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
    }

    //construtor sem id
    public Noticia(int idAdministrador, Foto foto, String titulo, String texto, LocalDateTime data) {
        this.idAdministrador = idAdministrador;
        this.foto = foto;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
    }

    //construtor sem id e data
    public Noticia(int idAdministrador, Foto foto, String titulo, String texto) {
        this.idAdministrador = idAdministrador;
        this.foto = foto;
        this.titulo = titulo;
        this.texto = texto;
    }

    public Noticia(Foto foto) {
        this.foto = foto;
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

    public void setIdFoto(Foto foto) {
        this.foto = foto;
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

    public Foto getFoto() {
        return foto;
    }

    public String mensagem() {
        return "Foto{"
                + "id=" + id
                + ", idFoto='" + foto + '\''
                + ", data=" + data
                + '}';
    }

}
