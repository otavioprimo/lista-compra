package codebrain.com.br.lista_compra;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import codebrain.com.br.lista_compra.Database.DbController;
import codebrain.com.br.lista_compra.model.Produto;
import codebrain.com.br.lista_compra.model.Usuario;

public class NovoProduto extends AppCompatActivity {

    int usuario_id;

    EditText edtDescricao;
    EditText edtQuantidade;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
        btnSalvar = (Button) findViewById(R.id.btnSalvarProduto);

        Bundle extras = getIntent().getExtras();
        usuario_id = extras.getInt("usuario_id");

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateView()){
                    inserirProduto(usuario_id,edtDescricao.getText().toString(),Integer.parseInt(edtQuantidade.getText().toString()));
                    finish();
                }
            }
        });
    }

    private void inserirProduto(int id, String descricao, int quantidade){
        DbController db = new DbController(getBaseContext());

        Produto produto = new Produto();
        Usuario usuario = new Usuario();

        usuario.setId(id);
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);

        produto.setUsuario(usuario);

        Boolean resultado = db.inserirProduto(produto);
        if(resultado){
            Toast.makeText(getApplicationContext(), "Produto cadastrado com sucesso",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Ocorreu um erro ao cadastrar o produto",Toast.LENGTH_SHORT).show();
        }
    }


    private boolean validateView(){
        if(TextUtils.isEmpty(edtDescricao.getText().toString())){
            edtDescricao.setError(getString(R.string.BR_value_isempty));
            return false;
        }
        if(TextUtils.isEmpty(edtQuantidade.getText().toString())){
            edtQuantidade.setError(getString(R.string.BR_value_isempty));
            return false;
        }
        return true;
    }

}
