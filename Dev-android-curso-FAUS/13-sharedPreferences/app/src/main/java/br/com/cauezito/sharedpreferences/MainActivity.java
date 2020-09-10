package br.com.cauezito.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextView txtResultado;
    private Button btnSalvar;
    private TextInputEditText txtNome;
    private static final String ARQUIVO_PREFERENCIA = "preferencia";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResultado = findViewById(R.id.txtResultado);
        txtNome = findViewById(R.id.txtNome);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                /*Salva dados direto no aparelho do usuário criando um arquivo xml para armazenar os dados
                Exemplo: cor para o app, configs do app, informações básicas do usuário etc
                Modo 0: privado (só o próprio aplicativo poderá salvar e ler o arquivo)
                */
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if(txtNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Preencha o nome!", Toast.LENGTH_LONG).show();
                } else {
                    String nome = txtNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    txtResultado.setText("Olá, " + nome + "! Como vai?");
                }
            }
        });

        //recuperar dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        if(preferences.contains("nome")){
            String nome = preferences.getString("nome", "");
            txtResultado.setText("Olá, " + nome + "! Como vai?");
        } else {
            txtResultado.setText("Olá! =)");
        }

    }
}