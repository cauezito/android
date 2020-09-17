package br.com.cauezito.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.cauezito.firebase.model.Usuario;

public class MainActivity extends AppCompatActivity {

    //se refere a raíz da estrutura, a não ser que outra seja especificada
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Salvando dados */
        //referencia.child("usuarios").child("1").child("nome").setValue("Cauê");
        DatabaseReference usuarios = referencia.child("usuarios");
        usuarios.child("1").setValue(criaUsuario());

        /* Recuperando dados */
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override //dado alterado
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override //requisição cancelada
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private Usuario criaUsuario(){
        usuario = new Usuario();
        usuario.setNome("Cauê");
        usuario.setSobrenome("Santos");
        usuario.setIdade(23);

        return usuario;
    }
}