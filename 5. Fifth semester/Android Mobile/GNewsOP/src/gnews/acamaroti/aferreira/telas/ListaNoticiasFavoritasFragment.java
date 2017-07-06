package gnews.acamaroti.aferreira.telas;

import gnews.acamaroti.aferreira.banco.RepositorioDB;
import gnews.acamaroti.aferreira.basicas.Noticia;
import gnews.acamaroti.aferreira.http.GoogleNewsAdapter;
import gnews.acamaroti.aferreira.interfaces.ClicouNaNoticia;

import java.util.List;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class ListaNoticiasFavoritasFragment extends ListFragment {

	
	List<Noticia> noticias;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		refreshList();
	}
	
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		//2.0
		if (getActivity() instanceof ClicouNaNoticia){
			((ClicouNaNoticia)getActivity()).noticiaFoiClicada(noticias.get(position));
		}
		///END2.0
	}
	
	
	//1.0
	//O método atualiza a lista instanciando o Repositório pela activity e listando todas as notícias cadastradas no banco
	//Ele chama o adapter para carregar todos os arquivos de layout
	public void refreshList(){
		RepositorioDB db = new RepositorioDB(getActivity());
		noticias = db.todasAsNoticias();
		
		GoogleNewsAdapter adapter = new GoogleNewsAdapter(getActivity(), noticias);
		setListAdapter(adapter);
	}
	///END1.0
}
