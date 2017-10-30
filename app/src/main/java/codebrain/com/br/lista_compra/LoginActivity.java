package codebrain.com.br.lista_compra;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import codebrain.com.br.lista_compra.Database.DbController;
import codebrain.com.br.lista_compra.model.Produto;
import codebrain.com.br.lista_compra.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtSenha;
    Button btnLogin;
    TextView btnCadastrar;

    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnCadastrar = (TextView) findViewById(R.id.btnCadastrar);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateView()){
                    Usuario usuario = new Usuario();
                    DbController db = new DbController(getBaseContext());
                    usuario = db.login(edtEmail.getText().toString(),edtSenha.getText().toString());
                    if(usuario.getId() == 0){
                        Toast.makeText(getApplicationContext(), "Email ou senha inválidos",Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("usuario_id", usuario.getId());
                        intent.putExtra("usuario_email", usuario.getEmail());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }


    private boolean validateView(){
        if(TextUtils.isEmpty(edtEmail.getText().toString())){
            edtEmail.setError(getString(R.string.BR_value_isempty));
            return false;
        }
        if(TextUtils.isEmpty(edtSenha.getText().toString())){
            edtSenha.setError(getString(R.string.BR_value_isempty));
            return false;
        }
        return true;
    }




}
