package br.com.cauezito.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listaLocais;
    private String[] itens = {
      "Bahia", "São Paulo", "Rio de Janeiro", "Porto Seguro", "Macapá", "Acre",
      "João Pessoa", "Amazonas", "Roraima", "Paraná", "Rio Grande do Sul",
            "Santa Catarina"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaLocais = findViewById(R.id.listLocais);
        //Criar adaptador para a lista (como se fosse um benjamin de tomada), pegando
        //os itens a ser exibidos e criando um layout
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        //Adiciona adaptador para a lista
        listaLocais.setAdapter(adaptador);

        /*Adiciona evento de click no item da lista*/
        listaLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String valorSelecionado = listaLocais.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), valorSelecionado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}