package br.com.cauezito.listadetarefas.activity.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cauezito.listadetarefas.activity.helper.DbHelper;
import br.com.cauezito.listadetarefas.activity.model.Tarefa;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase inserir;
    private SQLiteDatabase consultar;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        inserir = db.getWritableDatabase();
        consultar = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNome());

        try{
            inserir.insert(DbHelper.TABELA_TAREFAS, null, cv);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> todasAsTarefas() {
        return null;
    }
}
