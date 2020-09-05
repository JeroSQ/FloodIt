package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import laminas.*;
import juego.*;

public class Frame extends JFrame {

	private LaminaPortada laminaPortada;
	private LaminaJuego laminaJuego;
	private LaminaConfig laminaConfig;
	private LaminaColores laminaColores;
	private LaminaInfo laminaInfo;
	private LaminaStats laminaStats;
	private LaminaTutorial laminaTutorial;
	public JScrollPane cargaLaminas;
	private Configuraciones config;
	public static final int PORTADA = 0;
	public static final int JUEGO = 1;
	public static final int CONFIG = 2;
	public static final int COLORES = 3;
	public static final int INFO = 4;
	public static final int STATS = 5;
	public static final int TUTO = 6;

	public Frame() { 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension pantalla = toolkit.getScreenSize();
		//pantalla = new Dimension(1366, 768);
		setSize((int) (((pantalla.getHeight()) / 2.3) * 0.81), (int) (pantalla.getHeight() / 2.13));
		setLocation((int) (pantalla.getWidth() / 2) - getWidth() / 2,(int) (pantalla.getHeight() / 2) - getHeight() / 2);
		setResizable(false);
		setTitle("Flood It");
		config = GestorArchivos.getLastConfig();
		config.setTablaStats(GestorArchivos.getEstadisticas(config));
		creaLaminas();
		addLaminas();
		setIconImage(toolkit.getImage("src/img/")); //hacer iconos
		laminaPortada.pasameLaminas(laminaJuego);
		cargaLaminas.setBorder(null);
		cargaLaminas.setViewportView(laminaPortada);
		addWindowListener(new WindowAdapter() {

			public void windowClosed(WindowEvent e) {
				GestorArchivos.setLastConfig(config);
				GestorArchivos.setEstadisticas(config.getTablaStats(), config);
				System.exit(0);
			}
		});
		pack();
		cargaLaminas.setViewportView(laminaTutorial);
	}

	public void cambiaLamina(int lamina) {
		switch (lamina) {
			case PORTADA:
				cargaLaminas.setViewportView(laminaPortada);
				setTitle("Flood It");
				break;
			case JUEGO:
				cargaLaminas.setViewportView(laminaJuego);
				break;
			case CONFIG:
				laminaConfig.actualizaBotones();
				cargaLaminas.setViewportView(laminaConfig);
				setTitle("Flood It | Configuraciones");
				break;
			case COLORES:
				cargaLaminas.setViewportView(laminaColores);
				setTitle("Flood It | Colores Personalizados");
				laminaColores.cargaSeleccion();
				break;
			case INFO:
				cargaLaminas.setViewportView(laminaInfo);
				setTitle("Flood It | Información");
				break;
			case STATS:
				cargaLaminas.setViewportView(laminaStats);
				laminaStats.actualizaStats();
				setTitle("Flood It | Estadísticas");
				break;
			case TUTO:
				cargaLaminas.setViewportView(laminaTutorial);
				setTitle("Flood It | Tutorial");
		}
	}
	
	public void llamaActualizaBotones() {
		laminaConfig.actualizaBotones();
	}
	
	public void llamaEliminaSeleccion() {
		laminaColores.eliminaSeleccion();
	}
	
	public void llamaActualizaColores() {
		laminaColores.actualizaColores();
	}
	
	public void llamaResetComboJugador() {
		laminaPortada.resetComboJugador();
	}
	
	private void creaLaminas() {
		cargaLaminas = new JScrollPane();
		laminaConfig = new LaminaConfig(this, config);
		laminaPortada = new LaminaPortada(this, config);
		laminaJuego = new LaminaJuego(this, config);
		laminaColores = new LaminaColores(this, config);
		laminaStats = new LaminaStats(this, config);
		laminaInfo = new LaminaInfo(this);
		laminaTutorial = new LaminaTutorial(this);
	}

	private void addLaminas() {
		cargaLaminas.add(laminaPortada);
		cargaLaminas.add(laminaColores);
		cargaLaminas.add(laminaJuego);
		cargaLaminas.add(laminaConfig);
		cargaLaminas.add(laminaInfo);
		cargaLaminas.add(laminaStats);
		cargaLaminas.add(laminaTutorial);
		add(cargaLaminas);
	}
}
