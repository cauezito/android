package br.com.cauezito.listadetarefas.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import br.com.cauezito.listadetarefas.R;
import br.com.cauezito.listadetarefas.activity.dao.TarefaDAO;
import br.com.cauezito.listadetarefas.activity.helper.DbHelper;
import br.com.cauezito.listadetarefas.activity.model.Tarefa;

public class NovaTarefaActivity extends AppCompatActivity {
    private TextInputEditText txtNovaTarefa;
    private TarefaDAO tarefaDAO;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);
        txtNovaTarefa = findViewById(R.id.txtNovaTarefa);
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

                tarefa = new Tarefa();
                tarefa.setNome(txtNovaTarefa.getText().toString());

                tarefaDAO.salvar(tarefa);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}