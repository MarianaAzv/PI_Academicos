
package model;

public class Usuario {
    
        private int id;
        private long cpf;
	private String nome;
	private String apelido;
	private String email;
	private String senha;
	private Boolean ativa;

	// Método construtor com todos os parâmetros

    public Usuario(int id, long cpf, String nome, String apelido, String email, String senha, Boolean ativa) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.ativa = ativa;
    }

    
   

    // metodo sem o parametro de id
     public Usuario(long cpf, String nome, String apelido, String email, String senha, Boolean ativa) {   
        this.cpf = cpf;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.ativa = ativa;
    }

    //metodo construtor para login
    public Usuario(String apelido, String senha) {
        this.apelido = apelido;
        this.senha = senha;
    }
     
    // metodo sem parametros
    public Usuario() {
    }

    //Geters e Setters
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
    

   
    
	

	
    
}
