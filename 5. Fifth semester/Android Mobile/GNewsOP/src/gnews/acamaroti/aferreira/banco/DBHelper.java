package gnews.acamaroti.aferreira.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String NOME_BANCO   = "noticiaDB";
	private static final int VERSAO_BANCO = 1;
	
	//construtor 
	public DBHelper(Context context) {
		super(context, NOME_BANCO, null, VERSAO_BANCO);
		
	}
	//para criar o banco - e script de criação do banco
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE noticias (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"titulo TEXT NOT NULL, data TEXT, link TEXT, content TEXT)");
	}
	//para atualizar o banco
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Utilizar s— na proxima vers‹o :)
	}

}
