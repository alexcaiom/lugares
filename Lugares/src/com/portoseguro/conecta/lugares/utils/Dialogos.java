/**
 * 
 */
package com.portoseguro.conecta.lugares.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.portoseguro.conecta.lugares.MainActivity;
import com.portoseguro.conecta.lugares.R;
import com.portoseguro.conecta.lugares.abstratas.Classe;
import com.portoseguro.conecta.lugares.abstratas.ClasseActivity;
import com.portoseguro.conecta.lugares.abstratas.ClasseFragmentoActivity;
import com.portoseguro.conecta.lugares.abstratas.Contexto;


/**
 * @author Alex
 *
 */
public class Dialogos extends Classe{

	public static class Alerta {
		static AlertDialog dialogo;

		/**
		 * Exibe mensagem de Alerta.<br/>
		 * <b>Lembre-se de chamar o metodo fecharDialogo()</b>
		 * @param contexto
		 * @param cancelavel
		 * @param mensagem
		 * @param titulo
		 * @param escutadorOk
		 */
		public static void exibirMensagemInformacao(Context contexto, boolean cancelavel, 
				CharSequence mensagem, CharSequence titulo, 
				OnClickListener escutadorOk){
			AlertDialog.Builder construtor = new AlertDialog.Builder(contexto).setIconAttribute(android.R.drawable.ic_dialog_info)
					.setCancelable(cancelavel)
					.setMessage(mensagem)
					.setTitle(titulo)
					.setPositiveButton("Ok!", escutadorOk);

			(dialogo = construtor.create()).show();

		}
		
		/**
		 * Exibe mensagem de Alerta.<br/>
		 * <b>Lembre-se de chamar o metodo fecharDialogo()</b>
		 * @param contexto
		 * @param cancelavel
		 * @param mensagem
		 * @param titulo
		 * @param escutadorOk
		 */
		public static void exibirMensagemInformacao(Contexto contexto, boolean cancelavel, 
				CharSequence mensagem, OnClickListener escutadorOk){
			exibirMensagemInformacao(contexto.getContexto(), cancelavel, mensagem, contexto.getContexto().getString(R.string.app_name), escutadorOk);			
		}
		public static void exibirMensagemPergunta(Context contexto, boolean cancelavel, 
				CharSequence mensagem, CharSequence titulo, 
				OnClickListener comportamentoSim, OnClickListener comportamentoNao, 
				OnClickListener comportamentoCancelar){
			AlertDialog.Builder construtor = new AlertDialog.Builder(contexto).setIconAttribute(android.R.drawable.ic_menu_zoom)
					.setCancelable(cancelavel)
					.setMessage(mensagem)
					.setTitle(titulo)
					.setPositiveButton("Sim", comportamentoSim)
					.setNeutralButton("Cancelar", comportamentoCancelar)
					.setNegativeButton("Não", comportamentoNao);

			(dialogo = construtor.create()).show();

		}

		public static void fecharDialogo(){
			dialogo.dismiss();
		}

		public static void exibirMensagemErro(Throwable e, Contexto contexto, OnClickListener acao) {
			exibirMensagemInformacao(contexto, false, "Um erro ocorreu: \n"+e.getMessage(), acao);
		}
	}
	
	public static class Progresso {
		static View dialogoProcessamento = null;
		static Activity telaAlvo = null;
		
		/**
		 * Mostra um Dialogo de Progresso de Processamento<br>
		 * <b>Lembre-se de chamar o metodo de fechamento</b>
		 * @param contexto
		 */
		public static void exibirDialogoProgresso(ClasseActivity tela){
			telaAlvo = tela;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				LayoutInflater inflador = (LayoutInflater) tela.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				dialogoProcessamento = inflador.inflate(R.layout.dialogo_progresso, null);
				tela.addContentView(dialogoProcessamento, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				int shortAnimTime = tela.getResources().getInteger(android.R.integer.config_shortAnimTime);
				dialogoProcessamento.setVisibility(View.VISIBLE);
				dialogoProcessamento.animate().setDuration(shortAnimTime)
					.alpha(1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							dialogoProcessamento.setVisibility(View.VISIBLE);
						}
				});
			}
		}
		
		/**
		 * Mostra um Dialogo de Progresso de Processamento<br>
		 * <b>Lembre-se de chamar o metodo de fechamento</b>
		 * @param contexto
		 */
		public static void exibirDialogoProgresso(ClasseFragmentoActivity tela){
			telaAlvo = tela;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				LayoutInflater inflador = (LayoutInflater) tela.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				dialogoProcessamento = inflador.inflate(R.layout.dialogo_progresso, null);
				tela.addContentView(dialogoProcessamento, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				int shortAnimTime = tela.getResources().getInteger(android.R.integer.config_shortAnimTime);
				dialogoProcessamento.setVisibility(View.VISIBLE);
				dialogoProcessamento.animate().setDuration(shortAnimTime)
				.alpha(1)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						dialogoProcessamento.setVisibility(View.VISIBLE);
					}
				});
			}
		}
		
		public static void fecharDialogoProgresso(){
			if (existe(dialogoProcessamento)) {
				int shortAnimTime = telaAlvo.getResources().getInteger(android.R.integer.config_shortAnimTime);
				dialogoProcessamento.animate().setDuration(shortAnimTime)
				.alpha(0)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						dialogoProcessamento.setVisibility(View.GONE);
					}
			});
			}
		}
	}
	
	public static class Notificacao{

		public static void exibir(ClasseActivity tela, int icone, String titulo, String texto, Bundle parametros) {
			NotificationManager notificationManager ;
			String serName = Context.NOTIFICATION_SERVICE ;
			notificationManager = ( NotificationManager ) tela.getSystemService( serName ) ;

			int icon = R.drawable.icon_action_bar ;
			String tickerText = "1. Minha notificacao" ;
			long when = System.currentTimeMillis( ) ;
			Notification notification = new Notification( icone, texto, when ) ;

			/*String extendedTitle = "2. Meu titulo" ;
			String extendedText = "3. Essa eh uma mensagem muito importante" ;*/

			Intent intent = new Intent( tela.getApplicationContext( ), MainActivity.class ) ;
			intent.putExtras(parametros);
			PendingIntent launchIntent = PendingIntent.getActivity( tela, 0, intent, 0 ) ;
			notification.setLatestEventInfo( tela, titulo,	texto, launchIntent ) ;
			int notificationId = 1 ;
			notificationManager.notify( notificationId, notification ) ;
			
		}
		
	}
}
