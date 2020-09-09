package br.com.cauezito.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Button btnAbrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAbrir = findViewById(R.id.btnAbrir);
        btnAbrir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Botão pressionado!", Snackbar.LENGTH_LONG)
                        .setAction("Desfazer", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                btnAbrir.setText("Desfeito! :)");
                            }
                        }).setActionTextColor(getResources().getColor(R.color.colorPrimaryDark))
                        .show();
            }
        });
    }
}