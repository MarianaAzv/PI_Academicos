
package model;

public class Coordenador extends Usuario{
    
    private int siape;
    private String formacao;
    
    // Método construtor com todos os parâmetros
    public Coordenador(int id, long cpf, String nome, String apelido, String email, String senha, Boolean ativa, int siape, String formacao ) {
        super(id, cpf, nome, apelido, email,senha,ativa);
        this.siape = siape;
        this.formacao = formacao;
    }
    
     // Método construtor para incluir
    public Coordenador(long cpf, String nome, String apelido, String email, String senha, int siape, String formacao ) {
        super(cpf, nome, apelido, email, senha);
        this.siape = siape;
        this.formacao = formacao;
    }
    
    // Método construtor para incluir apenas atributos usuário
    public Coordenador(long cpf, String nome, String apelido, String email, String senha) {
        super(cpf, nome, apelido, email, senha);
    }
    
    // Método construtor atributos da classe
    public Coordenador(int siape, String formacao ) {
        this.siape = siape;
        this.formacao = formacao;
    }
    

    
     public int getSiape() {
        return siape;
    }

    public void setSiape(int siape) {
        this.siape = siape;
    }
    
    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
    
}
