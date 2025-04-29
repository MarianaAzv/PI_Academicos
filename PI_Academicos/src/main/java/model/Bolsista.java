
package model;

import java.time.LocalDate;


public class Bolsista extends Usuario {
    
    private long matricula;
    private String curso;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean acessoPostagens;
    private boolean acessoArtigos;
    
    
    // Método construtor com todos os parâmetros do bolsista/ sem data
    public Bolsista(int id, long cpf, String nome, String apelido, String email, String senha, Boolean ativa, long matricula, String curso ) {
        super(id, cpf, nome, apelido, email,senha,ativa);
        this.matricula = matricula;
        this.curso = curso;
    }
    
    //model coordenador p/ bolsista
    
    // Método  para incluir
    public Bolsista(long cpf, String nome, String apelido, String email, String senha, long matricula, String curso ) {
        super(cpf, nome, apelido, email, senha);
        this.matricula = matricula;
        this.curso = curso;
    }
    
    // Método para incluir apenas atributos usuário
    public Bolsista(long cpf, String nome, String apelido, String email, String senha) {
        super(cpf, nome, apelido, email, senha);
    }
    
    // Método atributos da classe
    public Bolsista(long matricula, String curso ) {
       this.matricula = matricula;
        this.curso = curso;
    }
    
      public long getMatricula() {
        return matricula;
    }

    public void getMatricula(long matricula) {
        this.matricula = matricula;
    }
    
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    
    
    
    
    
}
