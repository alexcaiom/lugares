/**
 * 
 */
package com.portoseguro.conecta.lugares.abstratas;

import com.portoseguro.conecta.lugares.interfaces.ClasseActivityInterface;
import com.portoseguro.conecta.lugares.utils.Dialogos;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * @author Alex
 *
 */
public abstract class ClasseActivity extends Activity implements ClasseActivityInterface{

	public Contexto contexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		super.onCreate(savedInstanceState);
		contexto = new Contexto(this);
	}
	
	public void irPara(Class tela, Bundle parametros){
		Intent intencao = new Intent(this, tela);
		if(parametros != null && !parametros.isEmpty()){
			intencao.putExtras(parametros);
		}
		startActivity(intencao);
	}
	
	public void irPara(Class tela){
		irPara(tela, null);
	}
	
	/**
	 * Metodo que lanca um aviso do tipo Toast ao usuario<br/>
	 * Aviso padrao: Longo
	 * @param aviso
	 */
	public void avisar(String aviso, Integer duracao) {
		if(duracao == null){
			Toast.makeText(getApplicationContext(), aviso, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), aviso, duracao).show();
		}
	}

	/**
	 * Metodo que lanca um aviso do tipo Toast ao usuario<br/>
	 * Aviso padrao: Longo
	 * @param aviso
	 */
	public void avisar(String aviso) {
		avisar(aviso, null);
	}
	
	/**
	 * Metodo que verifica se o objeto eh nulo
	 * @param o
	 * @return
	 */
	public static boolean naoExiste(Object o){
		boolean naoExiste = Classe.naoExiste(o);
		return naoExiste;
	}
	
	/**
	 * Metodo que verifica se o objeto nao eh nulo
	 * @param o
	 * @return
	 */
	public static boolean existe(Object o){
		boolean existe = !naoExiste(o);
		return existe;
	}
	
	public void ocultarBarraDeAcoes(){
		ActionBar barraDeAcoes = getActionBar();
		if (existe(barraDeAcoes) && barraDeAcoes.isShowing()) {
			barraDeAcoes.hide();
		}
	}
	
	public void exibirMensagemExcecao(Throwable e){
		Dialogos.Alerta.exibirMensagemErro(e, ClasseActivity.this, null);
	}
	
	@Override
	public void onBackPressed() {
		/*boolean ehATelaMenu = Classe.estouNaClasse(this.getClass(), MenuActivity.class);
		if (ehATelaMenu) {
			OnClickListener escutador = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					deslogar();
					
				}
			};
			
			Dialogos.Alerta.exibirMensagemPergunta(ClasseActivity.this, false, "Deseja mesmo sair?", getString(R.string.app_name), escutador, null, null);
		} else {*/
			super.onBackPressed();
		//}
	}
	

	public void notificar(String mensagem, Bundle parametros) {
		Dialogos.Notificacao.exibir(contexto.getContexto(), mensagem, parametros);
	}
	
	public void log(String textoParaLog) {
		contexto.log(textoParaLog);		
	}
	
	public Contexto getContexto(){
		return contexto;
	}
	
	public Point getTamanhoTela(){
		Display display = getWindowManager().getDefaultDisplay();
		Point tamanho = new Point();
		display.getSize(tamanho);
		return tamanho;
	}
	
	public <T> T mapear(int id) {
		return (T) super.findViewById(id);
	}

}
