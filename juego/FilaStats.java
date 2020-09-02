package juego;

import java.io.Serializable;

public class FilaStats implements Serializable{
	
	private static final long serialVersionUID = 8401521374775363051L;
	private int[] fila;
	private int tam,juegosTotal, juegosGanados, juegosPerdidos, winPorcentaje, juegoBest, timeBest;
	
	private FilaStats() {};
	
	protected FilaStats(int tam) {
		fila = new int[7];
		this.tam = tam;
		juegosTotal = 0;
		juegosGanados = 0;
		juegosPerdidos = 0;
		winPorcentaje = 0;
		juegoBest = 0;
		timeBest = 0;
		asignaValores();
	}
	
	protected void actualizaFila(boolean win, int best, int time) {
		juegosTotal++;
		if(win)
			juegosGanados++;
		else
			juegosPerdidos++;
		winPorcentaje = juegosGanados * 100 / juegosTotal;
		if(best != 0)
			juegoBest = best;
		if(time != 0)
			timeBest = time;
		asignaValores();
	}
	
	protected void reseteaFila() {
		juegosTotal = 0;
		juegosGanados = 0;
		juegosPerdidos = 0;
		winPorcentaje = 0;
		juegoBest = 0;
		timeBest = 0;
		asignaValores();
	}

	protected int[] getFila() {
		return fila;
	}
	
	protected int getValor(int index) {
		return fila[index];
	}
	
	private void asignaValores() {
		fila[0] = tam;
		fila[1] = juegosTotal;
		fila[2] = juegosGanados;
		fila[3] = juegosPerdidos;
		fila[4] = winPorcentaje;
		fila[5] = juegoBest;
		fila[6] = timeBest;
	}
}
