package br.com.cauezito.listadetarefas.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.com.cauezito.listadetarefas.R;
import br.com.cauezito.listadetarefas.activity.dao.TarefaDAO;
import br.com.cauezito.listadetarefas.activity.helper.DbHelper;
import br.com.cauezito.listadetarefas.activity.model.Tarefa;

public class NovaTarefaActivity extends AppCompatActivity {
    private TextInputEditText txtNovaTarefa;
    private TarefaDAO tarefaDAO;
    private Tarefa tarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);
        txtNovaTarefa = findViewById(R.id.txtNovaTarefa);
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if(tarefaAtual != null){
            txtNovaTarefa.setText(tarefaAtual.getNome());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.salvar:
                tarefaDAO = new TarefaDAO(getApplicationContext());
                String nomeTarefa = txtNovaTarefa.getText().toString();

                //edição
                if(tarefaAtual != null){
                    if(validaTarefa(nomeTarefa)){
                        tarefa = new Tarefa();
                        tarefa.setNome(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        tarefaDAO.atualizar(tarefa);
                        finish();
                    }
                } else { //salvar

                    if (validaTarefa(nomeTarefa)) {
                        tarefa = new Tarefa();
                        tarefa.setNome(nomeTarefa);
                        tarefaDAO.salvar(tarefa);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Digite uma tarefa", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validaTarefa(String nome){
        if(nome.isEmpty()){
           return false;
        }
        return true;
    }
}