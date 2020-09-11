package br.com.cauezito.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //cria banco
            SQLiteDatabase banco = openOrCreateDatabase("TesteDB", MODE_PRIVATE, null);
            //cria tabela
            banco.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade INT(3)) ");
            //inserir dados
            banco.execSQL("INSERT INTO pessoas VALUES ('Cauê Santos', 23)");
            //recuperar registros
            Cursor cursor = banco.rawQuery("SELECT * FROM pessoas", null);
            //recuperar índices da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            cursor.moveToFirst();
            while(cursor != null){
                Log.i("RESULTADO - nome: ", cursor.getString(indiceNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}