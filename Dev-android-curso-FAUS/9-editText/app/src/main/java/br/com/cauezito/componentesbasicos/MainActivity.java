package br.com.cauezito.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText produto;
    private CheckBox cbBranco, cbVermelho, cbRosa, cbAzul;
    List<String> check = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        produto = findViewById(R.id.editText);
        cbBranco = findViewById(R.id.cbBranco);
        cbVermelho = findViewById(R.id.cbVermelho);
        cbRosa = findViewById(R.id.cbRosa);
        cbAzul = findViewById(R.id.cbAzul);
    }

    public void verificaCheck(){
        check.clear();
        
        if(cbBranco.isChecked()){
            check.add(cbBranco.getText().toString());
        }
        if(cbVermelho.isChecked()){
            check.add(cbVermelho.getText().toString());
        }
        if(cbAzul.isChecked()){
            check.add(cbAzul.getText().toString());
        }
        if(cbRosa.isChecked()){
            check.add(cbRosa.getText().toString());
        }
    }

    public void resultado(View view){
        String nomeProduto = produto.getText().toString();
        verificaCheck();

    }
}