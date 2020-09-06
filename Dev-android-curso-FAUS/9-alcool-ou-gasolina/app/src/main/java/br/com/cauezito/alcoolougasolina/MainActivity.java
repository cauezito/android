package br.com.cauezito.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

   private EditText editAlcool;
   private EditText editGasolina;
   private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResultado = findViewById(R.id.txtResultado);
        editAlcool = findViewById(R.id.precoAlcool);
        editGasolina = findViewById(R.id.precoGasolina);
    }

    public void calcular(View view){
        String precoAlcool = editAlcool.getText().toString();
        String precoGasolina = editGasolina.getText().toString();

        if(this.validaCampos(precoAlcool, precoGasolina)){
            this.calcularMelhorPreco(precoAlcool, precoGasolina);
        } else {
            txtResultado.setText("Preencha os preços primeiros!");
        }
    }

    private void calcularMelhorPreco(String precoAlcool, String precoGasolina){
      Double alcool = Double.parseDouble(precoAlcool);
      Double gasolina = Double.parseDouble(precoGasolina);

        /*Faz cálculo ( precoAlcool / precoGasolina )
         * Se resultado >= 0.7 melhor utilizar gasolina
         * senão melhor utilizar Álcool
         * */
        Double resultado = alcool / gasolina;

        txtResultado.setBackgroundColor(Color.parseColor("#ebebeb"));
        if(resultado >= 0.7){
            txtResultado.setText("É melhor abastecer com gasolina!");
        } else {
            txtResultado.setText("É melhor abastecer com alcool!");
        }
    }

    private boolean validaCampos(String precoAlcool, String precoGasolina){
        boolean camposValidados = true;

        if(precoAlcool == null || precoAlcool.equals("")){
            camposValidados = false;
        } else if (precoGasolina == null || precoGasolina.equals("")){
            camposValidados = false;
        }
        return camposValidados;
    }
}