package gnews.acamaroti.aferreira.banco;

import gnews.acamaroti.aferreira.basicas.Noticia;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioDB {
	
	public static final String NOTICIAS="noticias";
	
	//instancia o helper que faz a conexao com o banco
	private DBHelper helper;

	//construtos do rep
	public RepositorioDB(Context contexto) {
		helper = new DBHelper(contexto);
	}
	
	//metodo inserir
	public long inserir(Noticia noticia){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = valoresPorPost(noticia);
		
		long id = db.insert(NOTICIAS, null, values);
		noticia.id = id;
		noticia.favorito = true;
		
		db.close();	//fecha o banco 	
		return id; //retorna o id ao inserir
	}
	
	private ContentValues valoresPorPost(Noticia noticia) {
		ContentValues values = new ContentValues();
		values.put("titulo", noticia.titulo);
		values.put("data", noticia.data);
		values.put("link", noticia.link);
		values.put("content", noticia.descricao);
		
		return values;//retorna os valores do objeto
	}
	
	public List<Noticia> todasAsNoticias(){
		List<Noticia> noticias = new ArrayList<Noticia>();
		//para acessa o banco
		SQLiteDatabase db = helper.getReadableDatabase();
		//para fazer a pesquisa no banco
		Cursor cursor = db.rawQuery(
				"select * from noticias", null);
		
		while (cursor.moveToNext()){
			Noticia noticia = preencherNoticia(cursor);
			noticias.add(noticia);
		}
		cursor.close();//fecha a pesquisa
		db.close();//fecha o banco
		return noticias;//retorna a lista de noticias do banco
	}
	
	private Noticia preencherNoticia(Cursor cursor) {
		long id          = cursor.getLong(0);
		String titulo    = cursor.getString(1);
		String data = cursor.getString(2);
		String link      = cursor.getString(3);
		String descricao   = cursor.getString(4);
		
		Noticia noticia = new Noticia(id, titulo, link, data, descricao);
		noticia.favorito = true;
		
		return noticia;
	}

	public int excluir(Noticia noticia){
		SQLiteDatabase db = helper.getWritableDatabase();
		
		int rows = db.delete("NOTICIAS", "_id = "+ noticia.id, null);
		noticia.favorito = false;
		db.close();
		
		return rows;
	}
	
	public boolean favorito(Noticia noticia){
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from noticias where titulo = ?", 
				new String[]{noticia.titulo});
		
		boolean resultado = false;
		if (cursor != null && cursor.getCount() > 0){
			cursor.moveToNext();
			noticia.id = cursor.getLong(cursor.getColumnIndex("_id"));
			resultado = true;
		}
		cursor.close();
		db.close();
		return resultado;
	}	
	
}
