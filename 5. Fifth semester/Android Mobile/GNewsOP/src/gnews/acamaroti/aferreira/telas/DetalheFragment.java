package gnews.acamaroti.aferreira.telas;

import java.io.Serializable;

import gnews.acamaroti.aferreira.banco.RepositorioDB;
import gnews.acamaroti.aferreira.basicas.Noticia;
import gnews.acamaroti.aferreira.interfaces.NoticiaNosFavoritos;
import gnews.acamaroti.antonioferreira.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetalheFragment extends Fragment{

	Noticia noticia;
	//3.0
	RepositorioDB db;
	///END3.0
	
	TextView txtTitulo;
	WebView webViewConteudo;
	
	
	//1.0
	public static DetalheFragment novaInstancia(Noticia noticia){
		//BUNDLE É A VERSÃO INTENT PRA FRAGMENT
		Bundle args = new Bundle();
		//Se der erro na linha abaixo, implemente o serializable
		args.putSerializable("noticia", noticia);
		DetalheFragment df = new DetalheFragment();
		df.setArguments(args);
		
		return df;
	}
	///END1.0
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("noticia",(Serializable) noticia);
	}
	//2.0
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		//3.0
		db = new RepositorioDB(getActivity());
		if (savedInstanceState != null){
		noticia = (Noticia)savedInstanceState.getSerializable("noticia");
		}
		///END3.0
	}
	//END2.0
	
	//2.5
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_detalhe_noticia, null);
		
		txtTitulo = (TextView)layout.findViewById(R.id.textView1);
		webViewConteudo = (WebView)layout.findViewById(R.id.webView1);
		//Aqui aplica nas configurações pra aceitar acentuação das páginas web.
		WebSettings settings = webViewConteudo.getSettings();
		settings.setDefaultTextEncodingName("UTF-8");
		
		noticia = (Noticia)getArguments().getSerializable("noticia");
		//BANCO
		//3.0
		noticia.favorito = db.favorito(noticia);
		///END3.0
		txtTitulo.setText(noticia.titulo);
		webViewConteudo.loadDataWithBaseURL(null, noticia.descricao, "text/html", "UTF-8", null);
		
		return layout;
	}
	///END2.5
	
	//3.5
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.detalhe, menu);
		
		MenuItem item = menu.findItem(R.id.action_favoritos);
		if (noticia.favorito){
			item.setIcon(android.R.drawable.ic_menu_delete);
		} else {
			item.setIcon(android.R.drawable.ic_menu_save);
		}
	}
	///END3.5
	
	
	//4.0
	//Aqui informa que se a noticia estiver cadastrada, e o usuário clicar nela, ela irá ser excluída, Isso tudo no fragment de detalhes
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
           if (item.getItemId() == R.id.action_favoritos){
			
			if (noticia.favorito){
				db.excluir(noticia);
				Toast.makeText(getActivity(), "Removido dos favoritos", Toast.LENGTH_SHORT).show();
			} else {
				db.inserir(noticia);
				Toast.makeText(getActivity(), "Adicionado aos favoritos", Toast.LENGTH_SHORT).show();
			}
			//Aqui chama o método noticiaAdicionadaAoFavorito para adicionar ao favorito
			if (getActivity() instanceof NoticiaNosFavoritos){
				((NoticiaNosFavoritos)getActivity()).noticiaAdicionadaAoFavorito(noticia);
			}
			
			//Não sei pra que serve.
			((ActionBarActivity)getActivity()).supportInvalidateOptionsMenu();
		}
		return super.onOptionsItemSelected(item);
	}
	///END4.0
	
	
	
	
	
}
