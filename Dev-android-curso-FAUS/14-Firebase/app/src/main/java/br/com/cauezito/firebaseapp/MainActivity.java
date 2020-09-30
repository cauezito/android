package br.com.cauezito.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //Nó raiz do firebase, se não especificado
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // referencia.child("usuários").child("001").child("nome").setValue("Cauê");

        DatabaseReference usuarios = referencia.child("usuarios");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override //notifica sempre que um dado for alterado
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Usuario usuario = new Usuario();
        usuario.setNome("Cauê Santos");
        usuario.setEmail("caue.santos@teste.com.br");
        usuario.setIdade(23);
        usuario.setId(1L);

        usuarios.setValue(usuario);

        usuarios.child("001").setValue(usuario);
    }
}