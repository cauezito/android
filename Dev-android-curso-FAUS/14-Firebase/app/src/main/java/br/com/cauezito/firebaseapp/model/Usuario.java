package br.com.cauezito.firebaseapp.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nome;
    private String email;
    private int idade;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }
}
