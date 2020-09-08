package br.com.cauezito.jogo_cara_ou_coroa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ResultadoActivity extends AppCompatActivity {
    private ImageView imgResultado;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        imgResultado = findViewById(R.id.imgResultado);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //destrói a activity atual
                finish();
            }
        });
        Bundle resultado = getIntent().getExtras();
        geraResultado(resultado.getInt("resultado"));

    }

    private void geraResultado(int numero){
        if(numero == 0) {
            imgResultado.setImageResource(R.drawable.moeda_coroa);
        }
    }
}