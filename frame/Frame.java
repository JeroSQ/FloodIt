package frame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import laminas.*;
import juego.*;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private LaminaPortada laminaPortada;
	private LaminaJuego laminaJuego;
	private LaminaConfig laminaConfig;
	private LaminaColores laminaColores;
	private LaminaStats laminaStats;
	private LaminaTutorial laminaTutorial;
	public JScrollPane cargaLaminas;
	private Configuraciones config;
	public static final int PORTADA = 0;
	public static final int JUEGO = 1;
	public static final int CONFIG = 2;
	public static final int COLORES = 3;
	public static final int STATS = 4;
	public static final int TUTO = 5;

	public Frame() { 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension pantalla = toolkit.getScreenSize();
		//pantalla = new Dimension(1366, 768);
		pantalla = new Dimension(1920, 1080);
		setMinimumSize(new Dimension((int) (((pantalla.getHeight()) / 2.3) * 0.81 + getInsets().right ), (int) (pantalla.getHeight() / 2.13)));
		setLocation((int) (pantalla.getWidth() / 2) - getWidth() / 2,(int) (pantalla.getHeight() / 2) - getHeight() / 2);
		setResizable(true);
		setTitle("Flood It");
		ImageIcon icono = new ImageIcon("src/img/icono.png");
		setIconImage(icono.getImage());
		config = GestorArchivos.getLastConfig();
		config.setTablaStats(GestorArchivos.getEstadisticas(config));
		creaLaminas();
		addLaminas();
		ArrayList<Image> iconos = new ArrayList<Image>();
		iconos.add(new ImageIcon("src/img/icono32.png").getImage());
		iconos.add(new ImageIcon("src/img/icono48.png").getImage());
		iconos.add(new ImageIcon("src/img/icono64.png").getImage());
		setIconImages(iconos); 
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
		setMinimumSize(new Dimension(390, 541));
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
				setTitle("Flood It | Settings");
				break;
			case COLORES:
				cargaLaminas.setViewportView(laminaColores);
				setTitle("Flood It | Custom Colors");
				laminaColores.cargaSeleccion();
				break;
			case STATS:
				cargaLaminas.setViewportView(laminaStats);
				laminaStats.actualizaStats();
				setTitle("Flood It | Stats");
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
	
	private void creaLaminas() {
		cargaLaminas = new JScrollPane();
		laminaConfig = new LaminaConfig(this, config);
		laminaPortada = new LaminaPortada(this, config);
		laminaJuego = new LaminaJuego(this, config);
		laminaColores = new LaminaColores(this, config);
		laminaStats = new LaminaStats(this, config);
		laminaTutorial = new LaminaTutorial(this);
	}

	private void addLaminas() {
		cargaLaminas.add(laminaPortada);
		cargaLaminas.add(laminaColores);
		cargaLaminas.add(laminaJuego);
		cargaLaminas.add(laminaConfig);
		cargaLaminas.add(laminaStats);
		cargaLaminas.add(laminaTutorial);
		add(cargaLaminas);
	}
}
