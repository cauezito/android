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
import com.google.firebase.database.Query;
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

        //pesquisar usuário por ID
        /*DatabaseReference usuarioPesquisa = usuarios.child("-MIZ1NFkSib46WHACokw");

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Popula a classe com os dados retornados
                Usuario dadosRecebidos = dataSnapshot.getValue(Usuario.class);
                //dados do usuário pesquisado
                Log.i("Dados usuário string: ", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })*/

        //pesquisa utilizando queries
        //ordena os dados e então realiza a pesquisa com filtros
        Query usuarioPesquisado = usuarios.orderByChild("nome").equalTo("Cauê");

        //>=40 <=60
        Query usuarioIdade = usuarios.orderByChild("idade").startAt(40).endAt(60);
        //<= 20
        Query usuarioIdade2 = usuarios.orderByChild("idade").endAt(20);

        //palavras que iniciam com C - firebase interpreta o J realmente como a letra J \uf8ff
        Query usuarioNome = usuarios.orderByChild("nome").startAt("C").endAt("C" + "\uf8ff");

        //---VERIFICA USUÁRIO LOGADO---
        if(auth.getCurrentUser() != null){
            //logado
        }

        //---LOGAR USUÁRIO---
        /*auth.signInWithEmailAndPassword("cauesantos@teste.com", "teste145214")
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
        usuario.setNome("Ana");
        usuario.setEmail("ana.santos@teste.com.br");
        usuario.setIdade(78);

        //usuarios.setValue(usuario);

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