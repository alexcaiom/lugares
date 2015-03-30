package com.portoseguro.conecta.lugares.adaptador;

import java.lang.ref.WeakReference;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.portoseguro.conecta.lugares.R;
import com.portoseguro.conecta.lugares.abstratas.Contexto;
import com.portoseguro.conecta.lugares.abstratas.Lugares;
import com.portoseguro.conecta.lugares.utils.UtilsImagem;

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

		ImageView imagem = (ImageView) item.findViewById(R.id.item_lugar_img);

		Animation animacao = AnimationUtils.loadAnimation(contexto.getContexto(), R.anim.zoom_in);
//		imagem.setImageResource(lugares.get(position).getImg());
		
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeResource(contexto.getContexto().getResources(), imagem.getId(), options);
//		int imageHeight = options.outHeight;
//		int imageWidth = options.outWidth;
//		String imageType = options.outMimeType;
//		Bitmap bitmap = decodeSampledBitmapFromResource(contexto.getContexto().getResources(), lugares.get(position).getImg(), imageWidth, imageHeight);
		
		
		
		UtilsImagem.loadBitmap(contexto, lugares.get(position).getImg(), imagem);
//		imagem.setImageBitmap(bitmap);
		
		
		imagem.setAnimation(animacao);

		imagem.animate().start();

		ViewHolder holder = new ViewHolder();
		holder.imgLugar = imagem;
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
		ImageView imgLugar;
	}
	
}
