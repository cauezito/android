package br.com.cauezito.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void executarAcao(View view){
        int numero = new Random().nextInt(10);
        TextView resultado = findViewById(R.id.resultado);
        resultado.setText("O número escolhido foi: " + numero);
    }
}