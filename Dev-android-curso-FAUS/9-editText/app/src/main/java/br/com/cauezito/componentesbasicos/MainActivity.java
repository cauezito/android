package br.com.cauezito.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText produto;
    private TextView resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        produto = findViewById(R.id.editText);
        resultado = findViewById(R.id.txtResultado);
    }

    public void resultado(View view){
        String nomeProduto = produto.getText().toString();
        resultado.setText("O nome digitado foi: " + nomeProduto);
    }
}