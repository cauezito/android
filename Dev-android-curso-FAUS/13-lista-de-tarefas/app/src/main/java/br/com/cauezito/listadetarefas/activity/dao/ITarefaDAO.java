package br.com.cauezito.listadetarefas.activity.dao;

import java.util.List;

import br.com.cauezito.listadetarefas.activity.model.Tarefa;

public interface ITarefaDAO {
    boolean salvar(Tarefa tarefa);
    boolean atualizar(Tarefa tarefa);
    boolean deletar(Tarefa tarefa);
    List<Tarefa> todasAsTarefas();
}
