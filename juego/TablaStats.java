package juego;

import java.io.Serializable;

public class TablaStats implements Serializable {

	private static final long serialVersionUID = 6294398389307848310L;
	private FilaStats fila0, fila1, fila2, fila3, fila4;
	private FilaStats[] tabla;
	
	public TablaStats() {
		tabla = new FilaStats[5];
		fila0 = new FilaStats(10);
		fila1 = new FilaStats(12);
		fila2 = new FilaStats(15);
		fila3 = new FilaStats(20);
		fila4 = new FilaStats(25);
		tabla[0] = fila0;
		tabla[1] = fila1;
		tabla[2] = fila2;
		tabla[3] = fila3;
		tabla[4] = fila4;
	}
	
	public void reseteaTabla() {
		for(int i = 0; i < tabla.length; i++) {
			tabla[i].reseteaFila();
		}
	}
	
	public void actualizaTabla(int tam, boolean win, int best, int time) {
		int fila = getFila(tam);
		tabla[fila].actualizaFila(win, best, time);
	}
	
	public int[][] toArray2D(){
		int[][] tabla2D = new int[5][6];
		for(int i = 0; i < 5; i++) 
			for(int j = 1; j < 7; j++) 
				tabla2D[i][j - 1] = tabla[i].getValor(j);
		return tabla2D;
	}
	
	public int getBest(int tam) {
		int fila = getFila(tam);
		return tabla[fila].getValor(5);
	}
	
	private int getFila(int tam) {
		int fila = -1;
		switch(tam) {
			case 10:
				fila = 0;
				break;
			case 12:
				fila = 1;
				break;
			case 15:
				fila = 2;
				break;
			case 20:
				fila = 3;
				break;
			case 25:
				fila = 4;
				break;
		}
		return fila;
	}

	public int getBestTime(int tamagnoCuadro) {
		int fila = getFila(tamagnoCuadro);
		return tabla[fila].getValor(6);
	}
}
