
package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    
        private int id;
        private long cpf;
	private String nome;
	private String apelido;
	private String email;
	private String senha;
	private Boolean ativa;
        private Foto fotoPerfil;

    

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
    
    // Método construtor com todos os parâmetros e foto
    public Usuario(int id, long cpf, String nome, String apelido, String email, String senha, Boolean ativa, Foto fotoPerfil) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.ativa = ativa;
        this.fotoPerfil = fotoPerfil;
    }

    // Método construtor sem boolean ativa

    public Usuario(int id, long cpf, String nome, String apelido, String email, String senha) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
    }
    
    // Método construtor sem boolean ativa com foto

    public Usuario(int id, long cpf, String nome, String apelido, String email, String senha, Foto fotoPerfil) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
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
    
    // metodo construtor para cadastrar
     public Usuario(long cpf, String nome, String apelido, String email, String senha) {   
        this.cpf = cpf;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
    }
     
    // metodo sem parametros
    public Usuario() {
    }
    
    // metodo construtor apenas id
     public Usuario(int id) {   
        this.id = id;
    }

    //Geters e Setters
    public int getId() {
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
    
    public void setFotoPerfil(Foto fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Foto getFotoPerfil() {
        return fotoPerfil;
    }
    
    private transient IntegerProperty idProperty;

    public IntegerProperty idProperty() {
        if (idProperty == null) {
            idProperty = new SimpleIntegerProperty(id);
        }
        return idProperty;
    }

    private transient LongProperty cpfProperty;

    public LongProperty cpfProperty() {
        if (cpfProperty == null) {
            cpfProperty = new SimpleLongProperty(cpf);
        }
        return cpfProperty;
    }
    
    private transient StringProperty nomeProperty;

    public StringProperty nomeProperty() {
        if (nomeProperty == null) {
            nomeProperty = new SimpleStringProperty(nome);
        }
        return nomeProperty;
    }
    
    private transient StringProperty apelidoProperty;
    
    public StringProperty apelidoProperty() {
        if (apelidoProperty == null) {
            apelidoProperty = new SimpleStringProperty(apelido);
        }
        return apelidoProperty;
    }
    
    private transient StringProperty emailProperty;
    
    public StringProperty emailProperty() {
        if (emailProperty == null) {
            emailProperty = new SimpleStringProperty(email);
        }
        return emailProperty;
    }
   
    private transient BooleanProperty ativaProperty;
    
    public BooleanProperty ativaProperty() {
        if (ativaProperty == null) {
            ativaProperty = new SimpleBooleanProperty(ativa);
        }
        return ativaProperty;
    }
	   
}
