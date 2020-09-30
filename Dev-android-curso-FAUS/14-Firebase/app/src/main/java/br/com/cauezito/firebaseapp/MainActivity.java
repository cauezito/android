package br.com.cauezito.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //Nó raiz do firebase, se não especificado
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // referencia.child("usuários").child("001").child("nome").setValue("Cauê");

        DatabaseReference usuarios = referencia.child("usuarios");

        //---VERIFICA USUÁRIO LOGADO---
        if(auth.getCurrentUser() != null){
            //logado
        }

        //---LOGAR USUÁRIO---
        auth.signInWithEmailAndPassword("cauesantos@teste.com", "teste145214")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });

        //---DESLOGAR USUÁRIO---
        auth.signOut();

        //---CADASTRANDO USUÁRIO NO FIREBASE---
        /*auth.createUserWithEmailAndPassword("cauesantos@teste.com", "teste145214").addOnCompleteListener(
                MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("Sucesso", "test");
                        } else {
                            Log.i("Falha", "test");
                        }
                    }
                }
        );*/


        //---SALVANDO DADOS NO FIREBASE---
        /*Usuario usuario = new Usuario();
        usuario.setNome("Cauê Santos");
        usuario.setEmail("caue.santos@teste.com.br");
        usuario.setIdade(23);

        usuarios.setValue(usuario);

        //push gera um identificador único
        usuarios.push().setValue(usuario);*/

        //--RECUPERANDO DADOS NO FIREBASE--
        /*usuarios.addValueEventListener(new ValueEventListener() {
            @Override //notifica sempre que um dado for alterado
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }
}