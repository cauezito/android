package br.com.cauezito.firebaseapp.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import br.com.cauezito.firebaseapp.database.Firebase;
import br.com.cauezito.firebaseapp.model.Usuario;

public class UsuarioDAO implements IUsuarioDAO {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Firebase firebase = new Firebase();
    private DatabaseReference usuariosDatabase = firebase.getUsuariosDatabase();

    @Override
    public void insereUsuario(Usuario usuario) {
        if(existeUsuario(usuario.getEmail())){
            auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                               @Override
                                               public void onComplete(@NonNull Task<AuthResult> task) {
                                                   if(task.isSuccessful()){

                                                   } else {

                                                   }
                                               }
                                           }
                    );

        } else {

        }
    }

    @Override
    public boolean existeUsuario(String email) {
        Query usuario = usuariosDatabase.orderByChild("email").equalTo(email);
        if(usuario != null){
            return true;
        }
        return false;
    }

    private void pesquisaUsuarios(){

        //pesquisar usuário por ID
        DatabaseReference usuarioPesquisa = usuariosDatabase.child("-MIZ1NFkSib46WHACokw");

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
        });

        //pesquisa utilizando queries

        //ordena os dados e então realiza a pesquisa com filtros
        Query usuarioNome = usuariosDatabase.orderByChild("nome").equalTo("Cauê");

        //>=40 <=60
        Query usuarioIdadeMaiorIgual = usuariosDatabase.orderByChild("idade").startAt(40).endAt(60);

        //<= 20
        Query usuarioIdadeMenorIgual = usuariosDatabase.orderByChild("idade").endAt(20);

        //palavras que iniciam com C - p/ firebase interpretar o C realmente como C \uf8ff
        Query usuarioFiltro = usuariosDatabase.orderByChild("nome").startAt("C").endAt("C" + "\uf8ff");
    }

    private void salvaDados(){
        Usuario usuario = new Usuario();
        usuario.setNome("Ana");
        usuario.setEmail("ana.santos@teste.com.br");
        usuario.setIdade(78);

        //push gera um identificador único
        usuariosDatabase.push().setValue(usuario);
    }


}
