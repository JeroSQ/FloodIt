package juego;

import java.awt.Color;
import java.awt.geom.*;
import java.util.*;

import frame.Frame;

public class CuadroJuego {

	private ArrayList<RectangularShape> cuadros = new ArrayList<RectangularShape>();
	private ArrayList<Color> colorArray = new ArrayList<Color>();
	private double tamCuadro;
	private boolean cuadrados, timer;
	private Color[] paletaColor;
	private int widthSinInset, heightSinInset, tamCuadroPixel, insetCuadroWidth, insetCuadroHeight;
	private double sobras;

	private void obtieneConfig(Configuraciones config) {
		tamCuadro = config.getTamagnoCuadro();
		cuadrados = config.isFormaCuadrados();
		paletaColor = config.getPaletaColor();
		timer = config.isTimerOn();
	}

	public ArrayList<RectangularShape> generaNuevoCuadro(Configuraciones config, Frame frame) { // Genera un ArrayList
																								// de Rectangle2D o Ellipse2D
		obtieneConfig(config);
		int timerHeight = timer ? 25 : -25;
		
		widthSinInset = frame.getContentPane().getWidth(); 
		heightSinInset = frame.getContentPane().getHeight();
		
		tamCuadroPixel = widthSinInset < heightSinInset ? (int) (widthSinInset / 1.26666666666) 
				: (int)(heightSinInset / 1.69);    //Esto me da el 300x300 en la resolución mía
		sobras = (tamCuadroPixel / tamCuadro) % 1 > 0.5 ? 0 : (tamCuadroPixel / tamCuadro) % 1; // Esto es lo que sobra si es que
																								// tamCuadroPixel no es múltiplo de los tamaños
		insetCuadroWidth = (int) (((widthSinInset - tamCuadroPixel) + sobras * tamCuadro) / 2); //Inset que tiene que tener el cuadro ~40px
		insetCuadroHeight = (int)(((heightSinInset - tamCuadroPixel) + sobras * tamCuadro) / 2 + timerHeight);
		
		for (int i = 0; i < tamCuadro; i++) {
			for (int j = 0; j < tamCuadro; j++) {
				if (cuadrados) {
					cuadros.add(new Rectangle2D.Double((i * Math.round(tamCuadroPixel / tamCuadro)) + insetCuadroWidth,
							(j * Math.round(tamCuadroPixel / tamCuadro)) + insetCuadroHeight,
							Math.round(tamCuadroPixel / tamCuadro), Math.round(tamCuadroPixel / tamCuadro)));
				} else {
					cuadros.add(new Ellipse2D.Double((i * Math.round(tamCuadroPixel / tamCuadro)) + insetCuadroWidth,
							(j * Math.round(tamCuadroPixel / tamCuadro)) + insetCuadroHeight,
							Math.round(tamCuadroPixel / tamCuadro), Math.round(tamCuadroPixel / tamCuadro)));
				}
			}
		}
		return cuadros;
	}

	public ArrayList<Color> generaColorRandom() { // Genera un ArrayList de Color aleatorios
		for (int i = 0; i < tamCuadro * tamCuadro; i++) {
			int random = (int) (Math.random() * 6);
			Color color = asignaColor(random);
			colorArray.add(color);
		}
		return colorArray;
	}

	public Rectangle2D getCuadroBackground() {
		Rectangle2D recFondo = new Rectangle2D.Double(insetCuadroWidth, insetCuadroHeight,
				Math.round(tamCuadroPixel / tamCuadro) * tamCuadro, Math.round(tamCuadroPixel / tamCuadro) * tamCuadro);
		return recFondo;
	}

	private Color asignaColor(int num) { // Asigna cada número a un Color
		switch (num) {
		case 0:
			return paletaColor[0];
		case 1:
			return paletaColor[1];
		case 2:
			return paletaColor[2];
		case 3:
			return paletaColor[3];
		case 4:
			return paletaColor[4];
		case 5:
			return paletaColor[5];
		default:
			return null;
		}
	}
}
