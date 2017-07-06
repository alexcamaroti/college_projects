package gnews.acamaroti.aferreira.http;

import gnews.acamaroti.aferreira.basicas.Noticia;
import gnews.acamaroti.aferreira.interfaces.ClicouNaNoticia;
import gnews.acamaroti.antonioferreira.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GoogleNewsListFragment extends ListFragment {

	List<Noticia> noticias;
	GoogleNewsTask task;
	ProgressBar progress;
	TextView txtMensagem;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_lista, container, false);
		progress = (ProgressBar)layout.findViewById(R.id.progressBar1);
		txtMensagem = (TextView)layout.findViewById(R.id.txtMensagem);
		
		return layout;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		setRetainInstance(true);
		
		ConnectivityManager manager = (ConnectivityManager)
				getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isConnected()){
			if (task == null){
				task = new GoogleNewsTask();
				task.execute();
				
			} else if (task.getStatus() == Status.RUNNING){
				progress.setVisibility(View.VISIBLE);
			}
			
		} else {
			txtMensagem.setText("Sem conex‹o de dados");
			txtMensagem.setVisibility(View.VISIBLE);
			progress.setVisibility(View.INVISIBLE);
//			AlertDialog dialog = new AlertDialog.Builder(getActivity())
//				.setTitle("Informa�‹o")
//				.setMessage("Sem conex‹o de dados no momento.")
//				.setPositiveButton("OK", null)
//				.create();
//			dialog.show();
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
//		Noticia noticia = (Noticia)l.getItemAtPosition(position);
//		Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(noticia.link));
//		startActivity(it);
		
		if (getActivity() instanceof ClicouNaNoticia){
			((ClicouNaNoticia)getActivity()).noticiaFoiClicada(noticias.get(position));
		}
	}
	
	

	class GoogleNewsTask extends
			AsyncTask<String, Void, List<Noticia>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			txtMensagem.setVisibility(View.INVISIBLE);
			progress.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected List<Noticia> doInBackground(String... params) {

			try {
				URL url = new URL("http://news.google.com.br/?output=rss");
				HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
				conexao.setRequestMethod("GET");
				conexao.setDoInput(true);
				conexao.setConnectTimeout(15000);
				conexao.connect();
				
				
				if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
					return readXML(conexao.getInputStream());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(List<Noticia> result) {
			super.onPostExecute(result);
			if (result != null){
				noticias = result;
				GoogleNewsAdapter adapter = new GoogleNewsAdapter(getActivity(), noticias);
				setListAdapter(adapter);
			} else {
				txtMensagem.setVisibility(View.VISIBLE);
				txtMensagem.setText("Nenhuma noticia encontrada");
			}
			
			progress.setVisibility(View.INVISIBLE);
		}

		private List<Noticia> readXML(InputStream is){  
		    List<Noticia> noticias =  
		      new ArrayList<Noticia>();  
		  
		    try {  
		      // Criando os objetos que representam o XML  
		      DocumentBuilderFactory factory =  
		        DocumentBuilderFactory.newInstance();  
		  
		      DocumentBuilder builder =   
		        factory.newDocumentBuilder();  
		      Document xmlDocument = builder.parse(is);  
		     
		      // Cada notícia é representada pela tag <item>  
		      // Aqui obtemos a lista de nós com essa tag  
		      NodeList posts =   
		        xmlDocument.getElementsByTagName("item");  
		  
		      // Vamos iterar sobre a lista de itens  
		      String titulo = null, data = null,   
		        link = null,  descricao = null;
		  
		      for (int i = 0; i < posts.getLength(); i++) {  
		        Node post = posts.item(i);  
		  
		        // Cada nó ITEM tem os filhos:
		        NodeList postInfo = post.getChildNodes();  
		        for (int j = 0; j < postInfo.getLength(); j++){  
		          Node info = postInfo.item(j);  
		  
		          if ("title".equals(info.getNodeName())){  
		            titulo = info.getTextContent();  
		  
		          } else if ("pubDate".equals(  
				            info.getNodeName())){  
			            data = info.getTextContent();  
		        
		          } else if ("link".equals(  
				            info.getNodeName())){  
			            link = info.getTextContent();  
		          
		          } else if ("description".equals(  
				            info.getNodeName())){  
				            descricao = info.getTextContent();  
				          }
		          
		          
		        }  
		        // Com as informações das tags, criamos o  
		        // objeto notícia e adicionamos na lista  
		        noticias.add(  
		          new Noticia(titulo, data, link, descricao));  
		      }  
		    } catch (Throwable e) {  
		      e.printStackTrace();  
		    }  
		    return noticias;  
		  } 

	}
}
