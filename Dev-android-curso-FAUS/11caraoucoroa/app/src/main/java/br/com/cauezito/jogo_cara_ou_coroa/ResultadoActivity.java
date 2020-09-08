package br.com.cauezito.jogo_cara_ou_coroa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class ResultadoActivity extends AppCompatActivity {
    private ImageView imgResultado;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        imgResultado = findViewById(R.id.imgResultado);
        btnVoltar = findViewById(R.id.btnVoltar);
    }
}