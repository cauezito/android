package br.com.cauezito.passandodados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.cauezito.passandodados.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //seta a activity que você deseja ir
                Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                /*passar dados (intent = intenção. O que você deseja fazer)
                * No caso, poderia ser para abrir a câmera, abrir arquivo pdf,
                * utilizar o bluetooth, abrir algum app etc
                * */
                intent.putExtra("nome", "Cauê");
                intent.putExtra("idade", "23 anos");

                Usuario usuario = new Usuario("Amanda", "21 anos");
                intent.putExtra("usuario", usuario);

                //inicia uma nova activity
                startActivity(intent);
            }
        });
    }
}