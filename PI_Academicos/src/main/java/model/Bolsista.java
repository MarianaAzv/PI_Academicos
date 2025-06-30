package model;

import java.time.LocalDate;

public class Bolsista extends Usuario {

    private long matricula;
    private String curso;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int idProjeto;

    //Todos os atributos
    public Bolsista(int id, String cpf, String nome, String apelido, String email, String senha, boolean ativa,
            long matricula, String curso, LocalDate dataInicio, LocalDate dataFim) {
        super(id, cpf, nome, apelido, email, senha, ativa);
        this.matricula = matricula;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

    }
    
    //todos atributos + foto
     public Bolsista(int id, String cpf, String nome, String apelido, String email, String senha, boolean ativa,
            long matricula, String curso, LocalDate dataInicio, 
            LocalDate dataFim, Foto fotoPerfil) {
        super(id, cpf, nome, apelido, email, senha, ativa, fotoPerfil);
        this.matricula = matricula;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

    }

    // Construtor sem ID para INSERIR novo bolsista)
    public Bolsista(String cpf, String nome, String apelido, String email, String senha,
            long matricula, String curso, LocalDate dataInicio, LocalDate dataFim) {
        super(cpf, nome, apelido, email, senha);
        this.matricula = matricula;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

    }

    // Construtor de atributos básicos
    public Bolsista(long matricula, String curso, LocalDate dataInicio, LocalDate dataFim) {
        this.matricula = matricula;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }


    Bolsista(long aLong, String string) {
        this.matricula = matricula;
        this.curso = curso;
    }

    Bolsista(long matricula, String curso, LocalDate dataInicio, LocalDate dataFim, int idProjeto) {
        this.matricula = matricula;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

    }

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

    public Bolsista() {
        super();
    }
}
