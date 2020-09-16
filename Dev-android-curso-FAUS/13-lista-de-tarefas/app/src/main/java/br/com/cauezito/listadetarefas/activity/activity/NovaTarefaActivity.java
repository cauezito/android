package br.com.cauezito.listadetarefas.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import br.com.cauezito.listadetarefas.R;

public class NovaTarefaActivity extends AppCompatActivity {
    private TextInputEditText txtNovaTarefa;

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
}