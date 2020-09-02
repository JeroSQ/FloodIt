package juego;

import java.awt.Color;
import java.io.File;

import laminas.*;

public class Configuraciones {

	private boolean sonido, cuadrados, betaTester, timer;
	private int tamagnoCuadro;
	private int esquinaCuadro;
	private int paletaColorIndex;
	private Color[] paletaColor;
	private TablaStats tablaStats;
	private File archivoStats;
	
	
	public void setTablaStats(TablaStats tablaStats) {
		this.tablaStats = tablaStats;
	}

	public void setPaletaColorIndex(int paletaColorIndex) {
		this.paletaColorIndex = paletaColorIndex;
	}

	public void setSonido(boolean sonido) {
		this.sonido = sonido;
	}

	public void setPaletaColor(Color[] paletaColor) {
		this.paletaColor = paletaColor;
	}

	public void setCuadrados(boolean cuadrados) {
		this.cuadrados = cuadrados;
	}

	public void setBetaTester(boolean betaTester) {
		this.betaTester = betaTester;
	}

	public void setTamagnoCuadro(int tamagnoCuadro) {
		this.tamagnoCuadro = tamagnoCuadro;
	}

	public void setEsquinaCuadro(int esquinaCuadro) {
		this.esquinaCuadro = esquinaCuadro;
	}
	
	public void setArchivoStats(File archivoStats) {
		this.archivoStats = archivoStats;
	}
	
	public void setTimer(boolean timer) {
		this.timer = timer;
	}
	
	public void setDefaultConfigs() {
		setSonido(true);
		setCuadrados(true);
		setBetaTester(false);
		setTamagnoCuadro(15);
		setEsquinaCuadro(0);
		setPaletaColorIndex(0);
		setPaletaColor(GestorArchivos.getPaleta(0));
		setArchivoStats(new File("src/stats.txt"));
		setTimer(false);
	}

	public boolean isSonidoOn() {
		return sonido;
	}

	public boolean isFormaCuadrados() {
		return cuadrados;
	}

	public boolean isBetaTester() {
		return betaTester;
	}
	
	public boolean isTimerOn() {
		return timer;
	}

	public int getTamagnoCuadro() {
		return tamagnoCuadro;
	}

	public int getEsquinaCuadro() {
		return esquinaCuadro;
	}

	public Color[] getPaletaColor() {
		return paletaColor;
	}
	
	public int getPaletaColorIndex() {
		return paletaColorIndex;
	}
	
	public TablaStats getTablaStats() {
		return tablaStats;
	}
	
	public File getArchivoStats() {
		return archivoStats;
	}
}
