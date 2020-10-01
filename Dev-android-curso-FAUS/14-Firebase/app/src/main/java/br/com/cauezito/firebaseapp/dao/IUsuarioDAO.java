package br.com.cauezito.firebaseapp.dao;

import br.com.cauezito.firebaseapp.model.Usuario;

public interface IUsuarioDAO {
    void insereUsuario(Usuario usuario);
    boolean existeUsuario(String email);
}
