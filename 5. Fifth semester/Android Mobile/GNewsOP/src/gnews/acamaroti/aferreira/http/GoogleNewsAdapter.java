package gnews.acamaroti.aferreira.http;

import gnews.acamaroti.aferreira.basicas.Noticia;
import gnews.acamaroti.antonioferreira.R;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoogleNewsAdapter extends ArrayAdapter<Noticia> {

	public GoogleNewsAdapter(Context context, List<Noticia> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Noticia noticia = getItem(position);
		
		ViewHolder holder;
		
		if (convertView == null){
			convertView = LayoutInflater.from(getContext()).
					inflate(R.layout.layout_linha_noticia, null);
		
		
		holder = new ViewHolder();
		
		holder.txtTitulo = (TextView)
				convertView.findViewById(R.id.txtTitulo);
		holder.txtData = (TextView)
				convertView.findViewById(R.id.txtAutor);
		convertView.setTag(holder);

	} else {
		holder = (ViewHolder) convertView.getTag();
	}
		
		
		//Setando os valores
		holder.txtTitulo.setText(noticia.titulo);
		holder.txtData.setText(noticia.data);
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView txtTitulo;
		TextView txtData;
	}
}
