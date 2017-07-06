package gnews.acamaroti.aferreira.telas;

import gnews.acamaroti.aferreira.basicas.Noticia;
import gnews.acamaroti.aferreira.http.GoogleNewsListFragment;
import gnews.acamaroti.aferreira.interfaces.ClicouNaNoticia;
import gnews.acamaroti.aferreira.interfaces.NoticiaNosFavoritos;
import gnews.acamaroti.antonioferreira.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PrincipalActivity extends ActionBarActivity 
	implements TabListener, ClicouNaNoticia, NoticiaNosFavoritos {

	GoogleNewsListFragment f1;
	ListaNoticiasFavoritasFragment f2;
	ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		
		f1 = new GoogleNewsListFragment();
		f2 = new ListaNoticiasFavoritasFragment();
		//2.1
		//É DEFINIDO COMO FINAL POIS DENTRO DA CLASSE SÓ VAI SER UTILIZADO AQUI
		final ActionBar actionBar = getSupportActionBar();
		//END2.1
		
		
		//2.0
		pager = (ViewPager) findViewById(R.id.viewPager);
		FragmentManager fm = getSupportFragmentManager();
		pager.setAdapter(new MeuAdapter(fm));
		//END2.0 
		
		//2.1
		pager.setOnPageChangeListener(new SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int position) {
				super.onPageSelected(position);
				actionBar.setSelectedNavigationItem(position);
			}
		});
		///END2.1
		
		//2.2
		actionBar.setNavigationMode(
				ActionBar.NAVIGATION_MODE_TABS);
		
		Tab aba1 = actionBar.newTab();
		aba1.setText("Utimas Noticias");
		aba1.setTabListener(this);
		
		Tab aba2 = actionBar.newTab();
		aba2.setText("Favoritos");
		aba2.setTabListener(this);
		
		actionBar.addTab(aba1);
		actionBar.addTab(aba2);
		///END2.2
		
//		if (savedInstanceState != null){
//			actionBar.setSelectedNavigationItem(
//					savedInstanceState.getInt("tab1"));
//		}
	}
	
//	@Override
//	protected void onSaveInstanceState(Bundle outState) {
//		super.onSaveInstanceState(outState);
//		outState.putInt("tab1", 
//				getSupportActionBar().getSelectedNavigationIndex());
//	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//2.3
		pager.setCurrentItem(tab.getPosition());
		///END2.3
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
	private boolean isTablet(){
		return findViewById(R.id.detail) != null;
	}
	//2.4
	@Override
	public void noticiaFoiClicada(Noticia noticia) {
		if (isTablet()) {
			DetalheFragment d = DetalheFragment.novaInstancia(noticia);
			
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.detail, d)
			.commit();
		} else {
			Intent it = new Intent(PrincipalActivity.this, DetalheActivity.class);
			it.putExtra("noticia", noticia);
			startActivity(it);
			
		}
	}
	
	@Override
	public void noticiaAdicionadaAoFavorito(Noticia noticia) {
		f2.refreshList();
		
	}
	///END2.4
	
//	public static class Fragment1 extends Fragment {
//		
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			TextView txt = new TextView(getActivity());
//			txt.setText("Aba 1");
//			return txt;
//		}
//		
//	}
//
	
	//2.0
	class MeuAdapter extends FragmentPagerAdapter {

		public MeuAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0){
				return f1;
			}
			return f2;
		}

		@Override
		public int getCount() {
			return 2;
		}
	}
	//END2.0
}
