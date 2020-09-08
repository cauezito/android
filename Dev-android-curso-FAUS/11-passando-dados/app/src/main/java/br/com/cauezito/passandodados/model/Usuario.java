package br.com.cauezito.passandodados.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nome;
    private String idade;

    public Usuario(String nome, String idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}
