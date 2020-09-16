package br.com.cauezito.listadetarefas.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.cauezito.listadetarefas.R;
import br.com.cauezito.listadetarefas.activity.adapter.TarefaAdapter;
import br.com.cauezito.listadetarefas.activity.helper.RecyclerItemClickListener;
import br.com.cauezito.listadetarefas.activity.model.Tarefa;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnNovaTarefa;
    private RecyclerView listaTarefas;
    private TarefaAdapter adapter;
    private List<br.com.cauezito.listadetarefas.activity.model.Tarefa> listaDeTarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNovaTarefa = findViewById(R.id.btnNovaTarefa);
        listaTarefas = findViewById(R.id.listaTarefas);

        listaTarefas.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(), listaTarefas, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
                )
        );

        btnNovaTarefa.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NovaTarefaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void carregaTarefas(){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setNome("Ir ao mercado");
        listaDeTarefas.add(tarefa);

        adapter = new TarefaAdapter(listaDeTarefas);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaTarefas.setHasFixedSize(true);
        listaTarefas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        listaTarefas.setAdapter(adapter);
        listaTarefas.setLayoutManager(layoutManager);

    }


    @Override
    //OnCreate é chamado apenas uma vez, para atualizar a lista de tarefas ao retornar para a activity é melhor utilizar
    //o onStart
    protected void onStart() {
        carregaTarefas();
        super.onStart();
    }
}