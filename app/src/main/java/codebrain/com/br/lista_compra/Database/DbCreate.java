package codebrain.com.br.lista_compra.Database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by otavio on 30/10/2017.
 */

public class DbCreate extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "usuarios";
    public static final String ID = "_id";
    public static final String EMAIL = "email";
    public static final String SENHA = "SENHA";

    public static final String TABELA_PRODUTO = "produtos";
    public static final String ID_PRODUTO= "_id";
    public static final String QUANTIDADE_PRODUTO = "quantidade_produto";
    public static final String DESCRICAO_PRODUTO = "descricao";
    public static final String USUARIO_ID_PRODUTO= "usuario_id";

    private static final int VERSAO = 2;

    public DbCreate(Context context) {
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + EMAIL + ","
                + SENHA +
                ");";
        db.execSQL(sql);

        sql = "CREATE TABLE "+TABELA_PRODUTO+"("
                + ID_PRODUTO + " integer primary key autoincrement,"
                + DESCRICAO_PRODUTO + ","
                + QUANTIDADE_PRODUTO + ","
                + USUARIO_ID_PRODUTO +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTO);
        onCreate(db);
    }
}
