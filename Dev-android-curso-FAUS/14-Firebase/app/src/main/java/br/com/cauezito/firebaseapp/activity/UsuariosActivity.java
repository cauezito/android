package br.com.cauezito.firebaseapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.cauezito.firebaseapp.R;
import br.com.cauezito.firebaseapp.dao.UsuarioDAO;
import br.com.cauezito.firebaseapp.model.Usuario;

public class UsuariosActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtSenha;
    private Button btnCadastrar;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);

        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = txtEmail.getText().toString();
                final String senha = txtSenha.getText().toString();
                if(validaUsuario(email, senha)) {
                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    usuarioDAO = new UsuarioDAO();
                    usuarioDAO.insereUsuario(usuario);

                } else {

                }
            }
        });
    }

    private boolean validaUsuario(String email, String senha){
        boolean validado = false;

        if(!email.equals("") || !email.isEmpty()){
            if(!senha.equals("") || !senha.isEmpty()){
                validado = true;
            }
        }

        return validado;
    }


}