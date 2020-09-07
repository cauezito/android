package br.com.cauezito.gorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText valorBruto;
    private TextView porcentagem;
    private SeekBar seekBar;
    private TextView totalGorjeta;
    private TextView valorTotal;
    private int progresso = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valorBruto = findViewById(R.id.valorBruto);
        porcentagem = findViewById(R.id.porcentagem);
        seekBar = findViewById(R.id.seekBar);
        totalGorjeta = findViewById(R.id.totalGorjeta);
        valorTotal = findViewById(R.id.valorTotal);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar.setProgress(i);
                porcentagem.setText(i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(!valorBruto.getText().toString().equals("")){
                    calculaTotal();
                }

            }
        });
    }

    private void calculaTotal(){
        Double totalAPagarGorjeta = (Double.parseDouble(this.valorBruto.getText().toString()) *
                Double.parseDouble(porcentagem.getText().toString().replace("%",""))) / 100;
        totalGorjeta.setText("R$"+totalAPagarGorjeta);
        valorTotal.setText("R$"+(totalAPagarGorjeta + Double.parseDouble(valorBruto.getText().toString())));
    }


}