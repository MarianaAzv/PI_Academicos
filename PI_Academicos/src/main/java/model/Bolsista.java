package model;

import java.time.LocalDate;

public class Bolsista extends Usuario {

    private long matricula;
    private String curso;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean acessoPostagens;
    private boolean acessoArtigos;
    private int idProjeto;

    //Todos os atributos
    public Bolsista(int id, long cpf, String nome, String apelido, String email, String senha, boolean ativa, 
                    long matricula, String curso, boolean acessoPostagens, boolean acessoArtigos, LocalDate dataInicio, LocalDate dataFim){
        super(id, cpf, nome, apelido, email, senha, ativa);
        this.matricula = matricula;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        //this.acessoPostagens = acessoPostagens;
        //this.acessoArtigos = acessoArtigos;
    }

    // Construtor sem ID para INSERIR novo bolsista)
    public Bolsista(long cpf, String nome, String apelido, String email, String senha, 
                    long matricula, String curso,  boolean acessoPostagens, boolean acessoArtigos, LocalDate dataInicio, LocalDate dataFim){
        super(cpf, nome, apelido, email, senha);
        this.matricula = matricula;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.acessoPostagens = acessoPostagens;
        this.acessoArtigos = acessoArtigos;
    }

    // Construtor de atributos básicos
    public Bolsista(long matricula, String curso, LocalDate dataInicio, LocalDate dataFim){
        this.matricula = matricula;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    //CONSTRUTOR PARA ATUALIZAR com datas  E SEM ACESSOS
        public Bolsista(int id, long cpf, String nome, String apelido, String email, String senha, boolean ativa, long matricula, String curso, LocalDate dataInicio, LocalDate dataFim){
        super(id, cpf, nome, apelido, email, senha, ativa);
        this.matricula = matricula;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

    }
//11-05
    Bolsista(long aLong, String string) {
        this.matricula = matricula;
        this.curso = curso;
    }

    // GetSet ok
    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public boolean getAcessoPostagens() {
        return acessoPostagens;
    }

    public void setAcessoPostagens(boolean acessoPostagens) {
        this.acessoPostagens = acessoPostagens;
    }

    public boolean getAcessoArtigos() {
        return acessoArtigos;
    }

    public void setAcessoArtigos(boolean acessoArtigos) {
        this.acessoArtigos = acessoArtigos;
    }

}