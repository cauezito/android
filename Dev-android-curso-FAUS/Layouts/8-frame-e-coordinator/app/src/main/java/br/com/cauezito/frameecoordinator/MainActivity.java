package br.com.cauezito.frameecoordinator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameCarregando);
        //Faz com que o elemento não esteja visível
        // (removendo, ao invés de estar somente invisível)
        frameLayout.setVisibility(View.GONE);
    }

    public void abrir(View view){
        frameLayout.setVisibility(View.VISIBLE);
    }
}