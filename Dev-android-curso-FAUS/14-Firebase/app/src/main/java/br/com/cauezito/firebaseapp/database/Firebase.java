package br.com.cauezito.firebaseapp.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Firebase {
    private StorageReference raizStorage = FirebaseStorage.getInstance().getReference();
    private StorageReference imagens = raizStorage.child("imagens");

    private DatabaseReference raizDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usuariosDatabase = raizDatabase.child("usuarios");

    public StorageReference getRaizStorage() {
        return raizStorage;
    }

    public void setRaizStorage(StorageReference raizStorage) {
        this.raizStorage = raizStorage;
    }

    public DatabaseReference getRaizDatabase() {
        return raizDatabase;
    }

    public void setRaizDatabase(DatabaseReference raizDatabase) {
        this.raizDatabase = raizDatabase;
    }

    public DatabaseReference getUsuariosDatabase() {
        return usuariosDatabase;
    }

    public void setUsuariosDatabase(DatabaseReference usuariosDatabase) {
        this.usuariosDatabase = usuariosDatabase;
    }

    public StorageReference getImagens() {
        return imagens;
    }

    public void setImagens(StorageReference imagens) {
        this.imagens = imagens;
    }
}
