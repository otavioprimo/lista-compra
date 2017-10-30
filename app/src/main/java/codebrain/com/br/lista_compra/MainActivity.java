package codebrain.com.br.lista_compra;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import codebrain.com.br.lista_compra.Database.DbController;
import codebrain.com.br.lista_compra.Database.DbCreate;
import codebrain.com.br.lista_compra.model.Produto;
import codebrain.com.br.lista_compra.model.Usuario;

public class MainActivity extends AppCompatActivity {

    int usuario_id;
    String usuario_email;
    TextView txtEmail;
    ImageView imgAdd;
    ListView listProdutos;

    private ListView lista;

    @Override
    protected void onResume() {
        carregarProdutos(usuario_id);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = (TextView) findViewById(R.id.txtEmail);
        imgAdd = (ImageView) findViewById(R.id.btnAdd);
        listProdutos = (ListView) findViewById(R.id.listView);

        Bundle extras = getIntent().getExtras();
        usuario_id = extras.getInt("usuario_id");
        usuario_email = extras.getString("usuario_email");

        txtEmail.setText(usuario_email);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NovoProduto.class);
                intent.putExtra("usuario_id", usuario_id);
                startActivity(intent);
            }
        });
    }


    private void carregarProdutos(int id_usuario) {
        DbController db = new DbController(getBaseContext());

        Cursor cursor = db.getAllProdutos(id_usuario);

        String[] nomeCampos = new String[]{DbCreate.DESCRICAO_PRODUTO, DbCreate.QUANTIDADE_PRODUTO};
        int[] idViews = new int[]{R.id.tvDescricao, R.id.tvQuantidade};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_list_view, cursor, nomeCampos, idViews, 0);

        listProdutos.setAdapter(adaptador);
    }
}
