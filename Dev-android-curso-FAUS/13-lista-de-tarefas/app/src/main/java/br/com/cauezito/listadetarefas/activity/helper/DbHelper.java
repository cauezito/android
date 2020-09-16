package br.com.cauezito.listadetarefas.activity.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";


    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    //geralmente utilizado para criar o banco pela primeira vez (ao INSTALAR o app)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "  nome TEXT NOT NULL); ";

        try{
            db.execSQL(sql);
            Log.i("info db", "sucesso ao criar a tabela");
        }catch (Exception e){
            Log.i("info db", "erro ao criar a tabela");
        }
    }

    //geralmente utilizado quando há uma nova versão do app e é necessário modificar tabelas após
    //a atualização
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
