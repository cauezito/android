package br.com.cauezito.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import br.com.cauezito.firebaseapp.database.Firebase;

public class MainActivity extends AppCompatActivity {

    private ImageView imgFoto;
    private Button btnUpload;
    private Firebase firebase;

    private DatabaseReference raizDatabase = firebase.getRaizDatabase();
    private DatabaseReference usuariosDatabase = firebase.getUsuariosDatabase();

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
                uploadImagem();
            }
        });
    }

    private void uploadImagem() {
        final Firebase firebase = new Firebase();

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

                //recupera nós para storage
                StorageReference raiz = firebase.getRaizStorage();
                StorageReference pastaImagens = firebase.getImagens();

                //gera nome para o arquivo
                String nomeArquivo = UUID.randomUUID().toString(); //randômico
                StorageReference imagemRef = pastaImagens.child(nomeArquivo + ".jpeg");

                //retorna o objeto que controlará o upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
                uploadTask.addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(MainActivity.this, "Upload efetuado!", Toast.LENGTH_SHORT).show();
                    }
                });

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Upload não realizado", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void carregaImagem(){
        Glide.with(MainActivity.this).load("imagemReferencia").into(imgFoto);
    }

    public void deletaImagem(){
                /*imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });*/
    }

    private void cadastraUsuario(){
        //---CADASTRANDO USUÁRIO NO FIREBASE---
        auth.createUserWithEmailAndPassword("cauesantos@teste.com", "teste145214").addOnCompleteListener(
                MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("CadastroRealizado", "cadastro realizado");
                        } else {
                            Log.i("CadastroNaoRealizado", "cadastro não realizado");
                        }
                    }
                }
        );
    }

    private boolean verificaSessao(){
        if(auth.getCurrentUser() != null){
            Log.i("Logado", "sim");
            return true;
        } else {
            Log.i("Logado", "nao");
            return false;
        }
    }

    private void terminaSessao(){
        if(verificaSessao()){
            auth.signOut();
        }
    }

    private void iniciaSessao(){
        auth.signInWithEmailAndPassword("cauesantos@teste.com", "teste145214").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.i("Logado", "logado");
            }
        });
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

    private void listenerDados(){
        usuariosDatabase.addValueEventListener(new ValueEventListener() {
            @Override //notifica sempre que um dado for alterado
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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