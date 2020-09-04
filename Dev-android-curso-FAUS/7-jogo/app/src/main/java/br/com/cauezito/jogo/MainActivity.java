package br.com.cauezito.jogo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pedra(View view){
        this.geraResultado("pedra");
    }
    public void papel(View view){
        this.geraResultado("papel");
    }
    public void tesoura(View view){
        this.geraResultado("tesoura");
    }
    public void geraResultado(String escolhaUsuario){
        ImageView imagemResultado = (ImageView) findViewById(R.id.imgResultado);
        TextView txtResultado = findViewById(R.id.txtResultado);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        int numero = new Random().nextInt(3);
        String escolhaApp = opcoes[numero];

        switch (escolhaApp){
            case "pedra":
                imagemResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imagemResultado.setImageResource(R.drawable.tesoura);
                break;
        }

      if(escolhaApp.equals("pedra") && escolhaUsuario.equals("tesoura")
      || escolhaApp.equals("papel") && escolhaUsuario.equals("pedra")
      || escolhaApp.equals("tesoura") && escolhaUsuario.equals("papel")){ //app ganhador
            txtResultado.setText("O app ganhou!");
      } else if(escolhaUsuario.equals("pedra") && escolhaApp.equals("tesoura")
              || escolhaUsuario.equals("papel") && escolhaApp.equals("pedra")
              || escolhaUsuario.equals("tesoura") && escolhaApp.equals("papel")){ //usuário ganhador
          txtResultado.setText("Você ganhou!");
      } else { // empate
          txtResultado.setText("Empate!");
      }
    }
}