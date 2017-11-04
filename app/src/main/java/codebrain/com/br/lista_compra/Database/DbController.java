package codebrain.com.br.lista_compra.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import codebrain.com.br.lista_compra.model.Produto;
import codebrain.com.br.lista_compra.model.Usuario;

/**
 * Created by otavio on 30/10/2017.
 */

public class DbController {
    private SQLiteDatabase db;
    private DbCreate banco;

    public DbController(Context context){
        banco = new DbCreate(context);
    }

    public Boolean insertUsuario(Usuario usuario){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DbCreate.EMAIL,usuario.getEmail());
        valores.put(DbCreate.SENHA,usuario.getSenha());

        resultado = db.insert(DbCreate.TABELA, null, valores);
        db.close();
        if (resultado ==-1)  return false;
        else
            return true;
    }

    public Boolean selectUserByEmail(String email){
        Cursor cursor = null;
        db = banco.getWritableDatabase();
        cursor = db.rawQuery("SELECT _id FROM usuarios WHERE email='?' LIMIT 1",new String[] {email + ""});
        try{

            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                return true;
            }else{
                return false;
            }
        }finally {
            cursor.close();
        }
    }

    public Usuario login(String email, String senha){
        Cursor cursor = null;
        Usuario usuario = new Usuario();
        db = banco.getWritableDatabase();
        cursor = db.rawQuery("SELECT _id,email FROM usuarios WHERE email=? AND senha=? LIMIT 1",new String[] {email + "",senha + ""});
        try{
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                usuario.setEmail(cursor.getString(cursor.getColumnIndex(DbCreate.EMAIL)));
                usuario.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbCreate.ID))));
                return usuario;
            }else{
                return usuario;
            }
        }finally {
            cursor.close();
            db.close();
        }
    }

    public Boolean inserirProduto(Produto produto){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DbCreate.DESCRICAO_PRODUTO,produto.getDescricao());
        valores.put(DbCreate.QUANTIDADE_PRODUTO,produto.getQuantidade());
        valores.put(DbCreate.USUARIO_ID_PRODUTO,produto.getUsuario().getId());

        resultado = db.insert(DbCreate.TABELA_PRODUTO, null, valores);
        db.close();
        if (resultado == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllProdutos(int id_usuario){
        Cursor cursor = null;
        //cursor = db.rawQuery("SELECT _id,descricao,quantidade_produto FROM produtos WHERE " + banco.USUARIO_ID_PRODUTO+"=?",new String[] {id_usuario + "" });

        String[] campos =  {banco.ID_PRODUTO,banco.DESCRICAO_PRODUTO,banco.QUANTIDADE_PRODUTO,banco.USUARIO_ID_PRODUTO};
        String where = banco.USUARIO_ID_PRODUTO + " = " + id_usuario;
        try{
            db = banco.getReadableDatabase();
            cursor = db.query(banco.TABELA_PRODUTO, campos, where, null, null, null, null, null);

            if(cursor!=null){
                cursor.moveToFirst();
                return cursor;
            }else{
                return cursor;
            }

        }finally {
            db.close();
        }
    }

    public void deleteAllProdutos() {
        SQLiteDatabase db = banco.getReadableDatabase();

        db.delete(banco.TABELA_PRODUTO,null,null);

        db.close();
    }

    public void deleteProduto(long id) {
        SQLiteDatabase db = banco.getReadableDatabase();

        String string = String.valueOf(id);
        db.execSQL("DELETE FROM " + banco.TABELA_PRODUTO + " WHERE " + banco.ID_PRODUTO
                + "=" + id + "");

        db.close();
    }
}
