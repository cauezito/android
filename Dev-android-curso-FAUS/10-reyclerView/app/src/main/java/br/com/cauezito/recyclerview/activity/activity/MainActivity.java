package br.com.cauezito.recyclerview.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import br.com.cauezito.recyclerview.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        //Configurar adapter

        //Configurar RecyclerView
        RecyclerView.LayoutManager recycler = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter();
    }
}