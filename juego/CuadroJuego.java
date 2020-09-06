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
	private int widthSinInset, tamCuadroPixel, insetCuadro;
	private double sobras;

	private void obtieneConfig(Configuraciones config) {
		tamCuadro = config.getTamagnoCuadro();
		cuadrados = config.isFormaCuadrados();
		paletaColor = config.getPaletaColor();
		timer = config.isTimerOn();
	}

	public ArrayList<RectangularShape> generaNuevoCuadro(Configuraciones config, Frame frame) { // Genera un ArrayList
																								// de Rectangle2D
		obtieneConfig(config);
		int scale = config.isTimerOn() ? 3 : 2;
		widthSinInset = frame.getContentPane().getWidth();
		tamCuadroPixel = (int) (widthSinInset / 1.26666666666);
		sobras = (tamCuadroPixel / tamCuadro) % 1 > 0.5 ? 0 : (tamCuadroPixel / tamCuadro) % 1;
		insetCuadro = (int) (((widthSinInset - tamCuadroPixel) + sobras * tamCuadro) / 2);
		// System.out.println(widthSinInset + " " + tamCuadroPixel + " " + insetCuadro);
		for (int i = 0; i < tamCuadro; i++) {
			for (int j = 0; j < tamCuadro; j++) {
				if (cuadrados) {
					cuadros.add(new Rectangle2D.Double((i * Math.round(tamCuadroPixel / tamCuadro)) + insetCuadro,
							(j * Math.round(tamCuadroPixel / tamCuadro)) + insetCuadro * scale,
							Math.round(tamCuadroPixel / tamCuadro), Math.round(tamCuadroPixel / tamCuadro)));
				} else {
					cuadros.add(new Ellipse2D.Double((i * Math.round(tamCuadroPixel / tamCuadro)) + insetCuadro,
							(j * Math.round(tamCuadroPixel / tamCuadro)) + insetCuadro * scale,
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
		int scale = timer ? 3 : 2;
		Rectangle2D recFondo = new Rectangle2D.Double(insetCuadro, insetCuadro * scale,
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
