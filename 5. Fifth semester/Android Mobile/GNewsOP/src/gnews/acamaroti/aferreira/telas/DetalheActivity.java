package gnews.acamaroti.aferreira.telas;

import gnews.acamaroti.aferreira.basicas.Noticia;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class DetalheActivity extends ActionBarActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//Recupera o objeto da intent
		Noticia noticia = (Noticia)getIntent().getSerializableExtra("noticia");
		
		//Carrega o fragment de Detalhes
		DetalheFragment d = DetalheFragment.novaInstancia(noticia);
		
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, d).commit();
		
	}

}
