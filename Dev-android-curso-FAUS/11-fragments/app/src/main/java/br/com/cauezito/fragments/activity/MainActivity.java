package br.com.cauezito.fragments.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.cauezito.fragments.R;
import br.com.cauezito.fragments.fragment.ChamadaFragment;
import br.com.cauezito.fragments.fragment.ContatoFragment;

public class MainActivity extends AppCompatActivity {
    private Button btnChamadas, btnContatos;
    private ChamadaFragment chamadaFragment;
    private ContatoFragment contatoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChamadas = findViewById(R.id.btnChamadas);
        btnContatos = findViewById(R.id.btnContatos);

        //Remove sombra da actionBar
        getSupportActionBar().setElevation(0);

        chamadaFragment = new ChamadaFragment();



        //Configura o objeto para exibir o fragmento
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameConteudo, chamadaFragment);
        transaction.commit();

        btnContatos.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                contatoFragment = new ContatoFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, contatoFragment);
                transaction.commit();
            }
        });

        btnChamadas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                chamadaFragment = new ChamadaFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, chamadaFragment);
                transaction.commit();
            }
        });


    }
}