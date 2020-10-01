package br.com.cauezito.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ImageView imgFoto;
    private Button btnUpload;

    //Nó raiz do firebase, se não especificado
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFoto = findViewById(R.id.imgFoto);
        btnUpload = findViewById(R.id.btnUpload);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //salva a imagem na memória
                imgFoto.setDrawingCacheEnabled(true);
                imgFoto.buildDrawingCache();

                //recupera bitmap da imagem
                Bitmap bitmap = imgFoto.getDrawingCache();

                //comprime bitmap para png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);

                //converte baos para pixel bruto em matriz de bytes
                byte[] dadosImagem = baos.toByteArray();

                //define nós para storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");

                //gera nome para o arquivo
                String nome = UUID.randomUUID().toString(); //randômico
                StorageReference imagemRef = imagens.child(nome + ".jpeg");

                //retorna o objeto que controlará o upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
                uploadTask.addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });

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
        //Query usuarioPesquisado = usuarios.orderByChild("nome").equalTo("Cauê");

        //>=40 <=60
        //Query usuarioIdade = usuarios.orderByChild("idade").startAt(40).endAt(60);
        //<= 20
        //Query usuarioIdade2 = usuarios.orderByChild("idade").endAt(20);

        //palavras que iniciam com C - firebase interpreta o J realmente como a letra J \uf8ff
        //Query usuarioNome = usuarios.orderByChild("nome").startAt("C").endAt("C" + "\uf8ff");

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