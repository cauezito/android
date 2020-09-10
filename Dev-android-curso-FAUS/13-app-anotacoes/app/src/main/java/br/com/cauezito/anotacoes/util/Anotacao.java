package br.com.cauezito.anotacoes.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Anotacao {
    private Context context;
    private SharedPreferences preferences;
    private static final String NOME_ARQUIVO = "anotacoes";
    private SharedPreferences.Editor editor;
    private final String CHAVE_NOME = "nome";

    public Anotacao(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, 0);
        editor = preferences.edit();
    }

    public void salvarAnotacao(String anotacao){
        editor.putString(CHAVE_NOME, anotacao);
        editor.commit();
    }
    public String recuperarAnotacao(){
        return preferences.getString(NOME_ARQUIVO,"");
    }
}
