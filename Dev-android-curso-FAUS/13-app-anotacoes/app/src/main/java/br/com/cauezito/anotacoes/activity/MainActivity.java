package br.com.cauezito.anotacoes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.cauezito.anotacoes.R;
import br.com.cauezito.anotacoes.util.Anotacao;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnSalvar;
    private EditText txtAnotacao;
    private Anotacao anotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSalvar = findViewById(R.id.btnSalvar);
        txtAnotacao = findViewById(R.id.txtAnotacao);
        anotacao = new Anotacao(getApplicationContext());
        btnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String texto = txtAnotacao.getText().toString();
                if(!texto.equals("")) {

                    anotacao.salvarAnotacao(texto);
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha a anotação!", Toast.LENGTH_LONG);
                }
            }
        });

        String texto = anotacao.recuperarAnotacao();
        if(!texto.equals("")){
            txtAnotacao.setText(texto);
        }
    }
}