package br.com.cauezito.recyclerview.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.cauezito.recyclerview.R;
import br.com.cauezito.recyclerview.activity.adapter.Adapter;
import br.com.cauezito.recyclerview.activity.model.Filme;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Filme> filmes = new ArrayList<Filme>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        //Cria a listagem de filmes
        geraFilmes();

        //Configurar adapter (pega os dados e monta uma visualização)
        Adapter adapter = new Adapter(filmes);

        //Configurar RecyclerView
        RecyclerView.LayoutManager recycler = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public void geraFilmes(){
        Filme filme = new Filme("O mundo sombrio de Kolo", "Horror", "1998");
        this.filmes.add(filme);

        filme = new Filme("Indo embora para casa", "Comédia", "2020");
        this.filmes.add(filme);

        filme = new Filme("A múmia 5", "Terror", "2020");
        this.filmes.add(filme);

        filme = new Filme("Pocoyo", "Animação", "2018");
        this.filmes.add(filme);

        filme = new Filme("Super heróis", "Fantasia", "2020");
        this.filmes.add(filme);
    }
}