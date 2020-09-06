package br.com.cauezito.componentesbasicos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText produto;
    private CheckBox cbBranco, cbVermelho;
    private RadioGroup rgEstoque;
    private Switch swEndereco;
    private ToggleButton toggleBtn;
    private CheckBox checkSenha;
    private ProgressBar progressBar;
    private ProgressBar progressBarCarregamento;
    private int progresso = 0;

    List<String> check = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        produto = findViewById(R.id.editText);
        cbBranco = findViewById(R.id.cbBranco);
        cbVermelho = findViewById(R.id.cbVermelho);
        rgEstoque = findViewById(R.id.rgEstoque);
        toggleBtn = findViewById(R.id.toggleBtn);
        checkSenha = findViewById(R.id.checkSenha);
        swEndereco = findViewById(R.id.swEndereco);
        progressBar = findViewById(R.id.progressBar);
        progressBarCarregamento = findViewById(R.id.pbCarregando);
        progressBarCarregamento.setVisibility(View.GONE);
        verificaRadioButton();
    }

    public void verificaCheck(){
        check.clear();

        if(cbBranco.isChecked()){
            check.add(cbBranco.getText().toString());
        }
        if(cbVermelho.isChecked()){
            check.add(cbVermelho.getText().toString());
        }
       if(swEndereco.isChecked()){
           check.add(swEndereco.getText().toString());
       }
       if(toggleBtn.isChecked()){
           check.add(toggleBtn.getText().toString());
       }
    }

    public void verificaRadioButton(){
        //verifica se um dos itens dentro do radio group foi marcado (e qual foi)
        rgEstoque.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rbSim){
                    
                } else {

                }
            }
        });
    }

    public void resultado(View view){
        String nomeProduto = produto.getText().toString();
        verificaCheck();
    }

    public void abrirToast(View view){
        //Toast.makeText(this, "Deu certo!", Toast.LENGTH_LONG).show();

        //customizado
        ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(android.R.drawable.star_big_on);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(img);
        toast.show();
    }

    public void abrirDialog(View view){
        /*
        * Criar alertDialog
        * */
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        //Configurar título e mensagem
        dialog.setTitle("Atenção!");
        dialog.setMessage("É necessário que você tenha atenção. Concorda?");
        //Configurar cancelamento
        dialog.setCancelable(false);
        //Configurar ações para os botões sim e não
        dialog.setPositiveButton("Concordo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Você concordou!", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setNegativeButton("Não concordo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Você não concordou!", Toast.LENGTH_SHORT).show();
            }
        });

        //criar e exibir a dialog
        dialog.create();
        dialog.show();
    }

    public void carregarProgressBar(View view){
        progressBarCarregamento.setVisibility(View.VISIBLE);
       /* this.progresso = this.progresso + 10;
        progressBar.setProgress(this.progresso);*/

       new Thread(new Runnable() {
           @Override
           public void run() {
               for(int i=0; i<=100; i++){
                   final int progresso = i;
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           progressBar.setProgress(progresso);
                           if(progresso == 100){
                               progressBarCarregamento.setVisibility(View.GONE);
                           }
                       }
                   });
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       });
    }
}