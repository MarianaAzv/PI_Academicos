
package model;


public class Administrador extends Usuario {

    public Administrador(int id, long cpf, String nome, String apelido, String email, String senha, Boolean ativa) {
        super(id, cpf, nome, apelido, email, senha, ativa);
    }
    
    //metodo construtor sem boolean ativa
    public Administrador(int id, long cpf, String nome, String apelido, String email, String senha) {
        super(id, cpf, nome, apelido, email, senha);
    }
    
    //metodo construtor com foto
    public Administrador(int id, long cpf, String nome, String apelido, String email, String senha, boolean ativa, Foto fotoPerfil) {
        super(id, cpf, nome, apelido, email, senha, ativa, fotoPerfil);
    }

    public Administrador(long cpf, String nome, String apelido, String email, String senha, Boolean ativa) {
        super(cpf, nome, apelido, email, senha, ativa);
    }

    public Administrador(String apelido, String senha) {
        super(apelido, senha);
    }

    public Administrador(long cpf, String nome, String apelido, String email, String senha) {
        super(cpf, nome, apelido, email, senha);
    }

    public Administrador() {
    }

    public Administrador(int id) {
        super(id);
    }

  
    
}
