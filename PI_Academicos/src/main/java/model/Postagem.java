package model;

import java.time.LocalDateTime;

public class Postagem {

    private int id;
    private int idProjeto;
    private Foto foto;
    private String legenda;
    private LocalDateTime data;

    //construtor com todos os atributos
    public Postagem(int id, int idProjeto, Foto foto, String legenda, LocalDateTime data) {
        this.id = id;
        this.idProjeto = idProjeto;
        this.foto = foto;
        this.legenda = legenda;
        this.data = data;
    }

    //construtor sem id
    public Postagem(int idProjeto, Foto foto, String legenda, LocalDateTime data) {
        this.idProjeto = idProjeto;
        this.foto = foto;
        this.legenda = legenda;
        this.data = data;
    }

    //construtor sem data
    public Postagem(int id, int idProjeto, Foto foto, String legenda) {
        this.id = id;
        this.idProjeto = idProjeto;
        this.foto = foto;
        this.legenda = legenda;
    }

    //construtor sem id e data
    public Postagem(int idProjeto, Foto foto, String legenda) {
        this.idProjeto = idProjeto;
        this.foto = foto;
        this.legenda = legenda;
    }

    public Postagem(Foto foto) {
        this.foto = foto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public Foto getFoto() {
        return foto;
    }

    public String getLegenda() {
        return legenda;
    }

    public LocalDateTime getData() {
        return data;
    }

}
