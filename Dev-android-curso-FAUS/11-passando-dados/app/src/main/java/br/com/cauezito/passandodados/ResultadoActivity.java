package br.com.cauezito.passandodados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.com.cauezito.passandodados.model.Usuario;

public class ResultadoActivity extends AppCompatActivity {
    private TextView txtNome;
    private TextView txtIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        txtNome = findViewById(R.id.txtNome);
        txtIdade = findViewById(R.id.txtIdade);
        //recupera dados enviados
        Bundle dados = getIntent().getExtras();
        Usuario usuario = (Usuario) dados.getSerializable("usuario");
      /*  txtNome.setText(dados.getString("nome"));
        txtIdade.setText(dados.getString("idade"));
       */

      txtNome.setText(usuario.getNome());
      txtIdade.setText(usuario.getIdade());


    }
}