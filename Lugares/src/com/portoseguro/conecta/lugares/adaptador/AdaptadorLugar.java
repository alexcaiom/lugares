package com.portoseguro.conecta.lugares.adaptador;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.portoseguro.conecta.lugares.R;
import com.portoseguro.conecta.lugares.orm.modelos.Lugar;

public class AdaptadorLugar extends ArrayAdapter<Lugar> {

	private Activity contexto;
	private List<Lugar> lugares;
	
	public AdaptadorLugar(Activity contexto, List<Lugar> lugares) {
		super(contexto, R.id.lugares_lista);
		this.contexto = contexto;
		this.lugares = lugares;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflador = contexto.getLayoutInflater();
		final RelativeLayout item = (RelativeLayout) inflador.inflate(R.layout.item_lugar, null);
		
		TextView label = (TextView) item.findViewById(R.id.item_lugar_label);
		String nome = lugares.get(position).getNome();
		String hashTags = "";
		if (lugares.get(position).getHashTags().length() > 3) {
			hashTags = " - " + lugares.get(position).getHashTags();
		} 
		label.setText(nome + hashTags);
//		label.setText(contexto.getContexto().getString(lugares.get(position).getDescricao()));
		
		//Adicionando a acao de selecionar
		item.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		
		ViewHolder holder = new ViewHolder();
		holder.label = label;
		if (convertView == null) {
			convertView = item;
		}
		convertView.setTag(holder);
		return convertView;
	}
	
	@Override
	public int getCount() {
		return lugares.size();
	}
	
	@Override
	public Lugar getItem(int position) {
		return lugares.get(position);
	}
	
	
	static class ViewHolder {
		  CheckBox imgChk;
		  TextView label;
		  ImageView imgCategoria;
	}
}
