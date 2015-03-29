package com.portoseguro.conecta.lugares.adaptador;

import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.portoseguro.conecta.lugares.R;
import com.portoseguro.conecta.lugares.abstratas.Contexto;
import com.portoseguro.conecta.lugares.abstratas.Lugares;
import com.portoseguro.conecta.lugares.orm.modelos.Lugar;

public class AdaptadorLugar extends ArrayAdapter<Lugares> {

	private Contexto contexto;
	private List<Lugares> lugares;
	
	public AdaptadorLugar(Contexto contexto, List<Lugares> lugares) {
		super(contexto.getContexto(), R.id.lugares_lista);
		this.contexto = contexto;
		this.lugares = lugares;
	}
	
	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflador = contexto.getContexto().getLayoutInflater();
		final RelativeLayout item = (RelativeLayout) inflador.inflate(R.layout.item_lugar, null);
		
		TextView label = (TextView) item.findViewById(R.id.item_lugar_label);
//		String nome = lugares.get(position).getNome();
//		String hashTags = "";
//		if (lugares.get(position).getHashTags().length() > 3) {
//			hashTags = " - " + lugares.get(position).getHashTags();
//		} 
//		label.setText(nome + hashTags);
//		label.setText(contexto.getContexto().getString(lugares.get(position).getDescricao()));
		
		//Adicionando a acao de selecionar
		item.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		
		ImageView imagem = (ImageView) item.findViewById(R.id.item_lugar_img);
		
		imagem.setImageResource(lugares.get(position).getImg());
		
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//			Drawable d = contexto.getContexto().getResources().getDrawable(lugares.get(position).getImg());
//			item.setBackground(d);

//			imagem.setImageResource(lugares.get(position).getImg());
//		} else {
//			item.setBackgroundDrawable(contexto.getContexto().getResources().getDrawable(lugares.get(position).getImg()));
//			imagem.setBackgroundDrawable(contexto.getContexto().getResources().getDrawable(lugares.get(position).getImg()));
//		}
		
		Animation animacao = AnimationUtils.loadAnimation(contexto.getContexto(), R.anim.zoom_in);
		item.setAnimation(animacao);
		
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
	public Lugares getItem(int position) {
		return lugares.get(position);
	}
	
	
	static class ViewHolder {
		  CheckBox imgChk;
		  TextView label;
		  ImageView imgCategoria;
	}
}
