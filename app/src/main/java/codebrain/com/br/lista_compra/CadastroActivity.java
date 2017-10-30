package codebrain.com.br.lista_compra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import codebrain.com.br.lista_compra.Database.DbController;
import codebrain.com.br.lista_compra.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtSenha;
    Button btnCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtEmail = (EditText) findViewById(R.id.edtEmailCadastro);
        edtSenha = (EditText) findViewById(R.id.edtSenhaCadastro);
        btnCadastro = (Button) findViewById(R.id.btnCadastro);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario; usuario = new Usuario();
                usuario.setEmail(edtEmail.getText().toString());
                usuario.setSenha(edtSenha.getText().toString());
                if(validateView()){
                    if(cadastrar(usuario)){
                        Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else
                       Toast.makeText(getApplicationContext(), "Email Já existe",Toast.LENGTH_SHORT).show();
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

    private boolean cadastrar(Usuario user){
       if(!verificarEmail(user.getEmail())){
           DbController db = new DbController(getBaseContext());
           Boolean resultado = db.insertUsuario(user);

           return resultado;
       }else{
           return false;
       }
    }

    private boolean verificarEmail(String email){
        DbController db = new DbController(getBaseContext());
        Boolean resultado = db.selectUserByEmail(email);
        return resultado;
    }
}
