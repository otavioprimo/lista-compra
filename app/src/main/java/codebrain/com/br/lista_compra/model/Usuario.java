package codebrain.com.br.lista_compra.model;

/**
 * Created by otavio on 30/10/2017.
 */

public class Usuario {
    private int id;
    private String email;
    private String senha;

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id;}

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
}
