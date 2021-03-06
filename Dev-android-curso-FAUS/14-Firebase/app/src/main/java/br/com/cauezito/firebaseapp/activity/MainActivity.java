package br.com.cauezito.firebaseapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import br.com.cauezito.firebaseapp.R;
import br.com.cauezito.firebaseapp.model.Usuario;
import br.com.cauezito.firebaseapp.database.Firebase;

public class MainActivity extends AppCompatActivity {

    private ImageView imgFoto;
    private Button btnUpload;
    private Button btnImagem;
    private Button btnUsuario;
    private Firebase firebase = new Firebase();

    private DatabaseReference usuariosDatabase = firebase.getUsuariosDatabase();

    private FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFoto = findViewById(R.id.imgFoto);
        btnUpload = findViewById(R.id.btnUpload);
        btnImagem = findViewById(R.id.btnImagem);
        btnUsuario = findViewById(R.id.btnUsuario);

        btnImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ImagensActivity.class);
                startActivity(intent);
            }
        });

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UsuariosActivity.class);
                startActivity(intent);
            }
        });
    }

    private void uploadImagem() {

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


}