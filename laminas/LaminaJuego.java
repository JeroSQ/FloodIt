package laminas;

import javax.sound.sampled.*;
import javax.swing.*;
import frame.Frame;
import juego.*;
import tempo.JTimer;
import tempo.TimeEndedListener;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;
import java.awt.event.*;
import java.awt.geom.RectangularShape;
import java.io.File;

public class LaminaJuego extends JPanel {

	private Configuraciones config;
	private boolean juegoGanado = false;
	private boolean gameStarting = true;
	private boolean quedaTiempo = true;
	//-----------------------------------------------------
	private int MOVIMIENTOS_INICIO;
	private boolean sonido, cuadrados;
	private int tamagnoCuadro;
	private int movimientosRestantes;
	private int esquinaCuadro;
	private Color paletaColores[];
	//-----------------------------------------------------
	private JLabel labelMovRest;
	private JButton btnRestart;
	private ArrayList<Color> colores; 
	private ArrayList<Integer> indexes = new ArrayList<Integer>(); 
	private ArrayList<RectangularShape> cuadros; 
	private Color colorACambiar;
	private CuadroJuego cuadro;
	private Frame frame;
	private TablaStats stats;
	private JTimer timer;
	private JPanel lmnBtn = new JPanel();
	private Box cajaNorth = Box.createHorizontalBox();
	private InputMap mapaInput = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	private ActionMap mapaA = getActionMap();

	public LaminaJuego(Frame frame, Configuraciones config) {
		setLayout(new BorderLayout());
		setBackground(Color.CYAN.darker());
		setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		cuadro = new CuadroJuego();
		this.frame = frame;
		this.config = config;
		stats = config.getTablaStats();
		lmnBtn.setBackground(Color.CYAN.darker());
		reiniciarJuego();
	}

	public void paintComponent(Graphics g) { // Pinta la rejilla del juego
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		actualizaCuadro(g2);
		if (juegoGanado) {
			muestraGanado();
		}
	}

	public void reiniciarJuego() { // Vacía los ArrayList y les vuelve a dar valor inicial
		juegoGanado = false;
		quedaTiempo = true;
		if(!(timer == null)) {
			timer.reset();
			timer.stop();
		}
		removeAll();
		if(!(cuadros==null)) {
		indexes.clear();
		cuadros.clear();
		colores.clear();
		}
		obtieneConfig();
		cuadros = cuadro.generaNuevoCuadro(config, frame);
		colores = cuadro.generaColorRandom();
		colorACambiar = colores.get(getEsquinaIndex());
		MOVIMIENTOS_INICIO = calculaMovimientos(tamagnoCuadro);
		movimientosRestantes = MOVIMIENTOS_INICIO;
		movimientosRestantes++;
		indexes.add(getEsquinaIndex());
		inicializaComponentesJuego();
		hazUnMovimiento();
		repaint();
		gameStarting = false;
	}
	
	private void obtieneConfig() {
		tamagnoCuadro = config.getTamagnoCuadro();
		cuadrados = config.isFormaCuadrados();
		esquinaCuadro = config.getEsquinaCuadro();
		sonido = config.isSonidoOn();
		paletaColores = config.getPaletaColor();
	}

	private void inicializaComponentesJuego() { 
		//-------------------------------------LABEL MOV REST----------------------------------------
		cajaNorth.removeAll();
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que quiere volver? Se reiniciará la partida actual.", "Aviso",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(opcion == 0) {
					actualizaStats();
					frame.cambiaLamina(Frame.PORTADA);
					return;
				}
				if(!timer.isTimerReset())
					timer.start();
			}
		});
		labelMovRest = new JLabel(
				"Movimientos Restantes: " + Integer.toString(movimientosRestantes));
		labelMovRest.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		labelMovRest.setForeground(Color.BLACK);
		labelMovRest.setFont(new Font("Roboto", Font.PLAIN, frame.getContentPane().getWidth() / 24));
		
		btnRestart = new JButton();
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//pantalla = new Dimension(1366, 768);
		ImageIcon icono = pantalla.height > 1000 ? new ImageIcon("src/img/restart.png") 
				: new ImageIcon("src/img/restartchico.png");
		btnRestart.setBackground(null);
		btnRestart.setBorder(null);
		btnRestart.setAction(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
					actualizaStats();
					reiniciarJuego();
			}
		});
		mapaInput.put(KeyStroke.getKeyStroke("R"), "restart");
		mapaA.put("restart", btnRestart.getAction());
		btnRestart.setIcon(icono);
		
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(btnVolver);
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(labelMovRest);
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(btnRestart);
		cajaNorth.add(Box.createHorizontalGlue());
		
		Box cajaNorthSur = Box.createHorizontalBox();
		
		int time = tamagnoCuadro * 1000;
		
		timer = new JTimer(2,time,true, SwingConstants.CENTER);
		cajaNorthSur.add(Box.createHorizontalGlue());
		cajaNorthSur.add(timer);
		timer.setFont(new Font("Serif", Font.PLAIN, (int) (frame.getContentPane().getHeight() / 15.36)));
		timer.setBackground(Color.BLUE);
		timer.setOpaque(true);
		timer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		timer.setFlashing(true);
		timer.setFlashThreshold(20000);
		timer.setPreferredSize(new Dimension(400,(int) (frame.getContentPane().getHeight() / 7.68)));
		cajaNorthSur.add(Box.createVerticalStrut(timer.getPreferredSize().height));
		timer.addTimeEndedListener(new TimeEndedListener() {
			public void timeEnded() {
				quedaTiempo = false;
				sinTiempo();
			}
	
		});
		
		Box cajaNorthFinal = Box.createVerticalBox();
		cajaNorthFinal.add(cajaNorth);
		if(config.isTimerOn())
			cajaNorthFinal.add(cajaNorthSur);
		add(cajaNorthFinal, BorderLayout.NORTH);
		
		creaBotonesColor();
		add(lmnBtn, BorderLayout.SOUTH);
	}

	//-----------------------------------------BOTONES COLOR-----------------------------------------
	private void creaBotonesColor() { // Crea 6 botones de colores que cambian el color
		final int TAM_BOTON = 40;
		lmnBtn.removeAll();
		for (int i = 0; i < 6; i++) {
			JButton boton = new JButton();
			boton.setBackground(paletaColores[i]);
			boton.setPreferredSize(new Dimension(TAM_BOTON, TAM_BOTON));
			boton.setAction(new AbstractAction() {
				
				public void actionPerformed(ActionEvent e) {
					boton.getModel().setPressed(true);
					if (colorACambiar.equals(boton.getBackground()) || movimientosRestantes <= 0)
						return;
					colorACambiar = boton.getBackground();
					hazUnMovimiento();
					if(config.isTimerOn() && !timer.isTimerRunning() && !juegoGanado)
						timer.start();
				}
			});
			mapaInput.put(KeyStroke.getKeyStroke(Integer.toString(i + 1)), "accionBtn" + i);
			mapaA.put("accionBtn" + i, boton.getAction());
			lmnBtn.add(boton);
		}
		//----------------------------------------------------------------------------------------------
	}

	private void actualizaCuadro(Graphics2D g2) {
		pintaFondo(g2);
		for (int i = 0; i < cuadros.size(); i++) {
			g2.setColor(colores.get(i)); // Vuelve a pintar todos los Rectangle2D
			g2.fill(cuadros.get(i));
		}
		for (int i = 0; i < indexes.size(); i++) {
			colores.set(indexes.get(i), colorACambiar);
			g2.setColor(colores.get(indexes.get(i))); // Pinta los Rectangle2D dentro de indexes
			g2.fill(cuadros.get(indexes.get(i)));
		}
	}

	// checkea si un hay colores iguales en cualquier dirección de todos los colores
	// y los añade
	// al ArrayList indexes
	private void checkColoresAlrededor(ArrayList<Color> colores, ArrayList<Integer> indexes) {
		int longitudIndexes = indexes.size();
		for (int j = 0; j < longitudIndexes; j++) {
			if (indexes.get(j) < ((tamagnoCuadro * tamagnoCuadro) - 1)
					&& !isBorder(indexes.get(j)).equals("downBorder")
					&& colores.get(indexes.get(j)).equals(colores.get(indexes.get(j) + 1))) {
				indexes.add(indexes.get(j) + 1);
			}
			if (indexes.get(j) > 0 && !isBorder(indexes.get(j)).equals("upBorder")
					&& colores.get(indexes.get(j)).equals(colores.get(indexes.get(j) - 1))) {
				indexes.add(indexes.get(j) - 1);
			}
			if (indexes.get(j) < ((tamagnoCuadro * tamagnoCuadro) - tamagnoCuadro) && colores
					.get(indexes.get(j)).equals(colores.get(indexes.get(j) + tamagnoCuadro))) {
				indexes.add(indexes.get(j) + tamagnoCuadro);
			}
			if (indexes.get(j) > tamagnoCuadro && colores.get(indexes.get(j))
					.equals(colores.get(indexes.get(j) - tamagnoCuadro))) {
				indexes.add(indexes.get(j) - tamagnoCuadro);
			}
		}
	}

	private void eliminaDuplicados(ArrayList<Integer> array) {
		ArrayList<Integer> sinDuplicado = (ArrayList<Integer>) array.stream().distinct()
				.collect(Collectors.toList());
		array.clear();
		array.addAll(sinDuplicado);
	}

	private void checkeaMovimientos() { // Checkea si se quedó sin movimientos el jugador
		if (movimientosRestantes > 0 || juegoGanado)
			return;
		timer.stop();
		actualizaStats();
		labelMovRest.setText("       Sin Movimientos!         ");
		labelMovRest.setForeground(Color.RED);
		String opciones[] = new String[] { "Reiniciar", "Volver al Menú", "Salir" };
		int opcion = -1;
		do {
			opcion = (int) JOptionPane.showOptionDialog(null, "Sin Movimientos!", "Aviso",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones,
					null);
		} while (opcion == -1);
		switch (opcion) {
			case 0:
				Thread hilo = new Thread(new Runnable() {
					public void run() {
						reiniciarJuego();
						try {
							Thread.currentThread().join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				hilo.start();
				break;
			case 1:
				frame.cambiaLamina(Frame.PORTADA);
				break;
			case 2:
				GestorArchivos.setLastConfig(config);
				System.exit(0);
				break;
		}
	}

	private void juegoGanado() { // Checkea si ganó y añade botones si es así
		if (indexes.size() != tamagnoCuadro * tamagnoCuadro || movimientosRestantes < 0) 
			return;
		juegoGanado = true;
		timer.stop();
		actualizaStats();
		lmnBtn.removeAll();
		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				reiniciarJuego();
			}
		});
		lmnBtn.add(btnReiniciar);
		JButton btnVolver = new JButton("Volver al Menú");
		btnVolver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.PORTADA);
			}
		});
		lmnBtn.add(btnVolver);
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				GestorArchivos.setLastConfig(config);
				System.exit(0);
			}
		});
		lmnBtn.add(btnSalir);
	}

	private void muestraGanado() { // Añade el texto ganado
		if(!juegoGanado)
			return;
		juegoGanado = false;
		JLabel ganaste = new JLabel("Ganaste!");
		ganaste.setForeground(isColorDark(colorACambiar) ? Color.WHITE : Color.BLACK.brighter());
		ganaste.setFont(new Font("Roboto", Font.BOLD, 48));
		JLabel movs = new JLabel("Lo hiciste en " + (MOVIMIENTOS_INICIO - movimientosRestantes) + " movimientos");
		movs.setForeground(isColorDark(colorACambiar) ? Color.WHITE : Color.BLACK.brighter());
		movs.setFont(new Font("Roboto", 3, 15));
		
		Box cajaEnclose = Box.createHorizontalBox();
		Box cajaFiller1 = Box.createHorizontalBox();
		Box caja = Box.createVerticalBox();
		Box cajaFiller2 = Box.createHorizontalBox();
		
		cajaFiller1.add(Box.createHorizontalGlue());
		
		caja.add(Box.createVerticalGlue());
		caja.add(ganaste);
		caja.add(movs);
		caja.add(Box.createVerticalGlue());
		caja.add(Box.createVerticalGlue());
		
		cajaFiller2.add(Box.createHorizontalGlue());
		
		cajaEnclose.add(cajaFiller1);
		cajaEnclose.add(caja);
		cajaEnclose.add(cajaFiller2);
		
		add(cajaEnclose, BorderLayout.CENTER);
		updateUI();
	}

	private void hazUnMovimiento() {
		for (int i = 0; i < indexes.size(); i++) { // Actualiza los colores del ArrayList
			colores.set(indexes.get(i), colorACambiar);
		}
		for (int i = 0; i < 20; i++) {
			checkColoresAlrededor(colores, indexes); // Checkea varias veces si hay nuevos colores
														// para
			eliminaDuplicados(indexes); // añadir al ArrayList
		}
		movimientosRestantes--;
		labelMovRest.setText("Movimientos Restantes: " + Integer.toString(movimientosRestantes));
		juegoGanado();
		checkeaMovimientos();
		repaint();
		if(config.isSonidoOn() && !gameStarting)
			playSound(new File("src/pop.wav").getAbsoluteFile());
	}

	private void actualizaStats() {
		if(config.isBetaTester() || MOVIMIENTOS_INICIO == movimientosRestantes)
			return;
		int movCompleto = MOVIMIENTOS_INICIO - movimientosRestantes;
		int best;
		if(juegoGanado && (stats.getBest(config.getTamagnoCuadro()) > movCompleto || 
				stats.getBest(config.getTamagnoCuadro()) == 0))
			best = movCompleto;
		else 
			best = 0;
		int tiempo = config.isTimerOn() && juegoGanado && (stats.getBestTime(config.getTamagnoCuadro()) == 0
				|| stats.getBestTime(config.getTamagnoCuadro()) > timer.getTimeInput() -timer.getTimeLeft())  
				? timer.getTimeInput() - timer.getTimeLeft() : 0; 
		stats.actualizaTabla(config.getTamagnoCuadro(), juegoGanado, best, tiempo);
		config.setTablaStats(stats);
		GestorArchivos.setEstadisticas(stats, config);
	}

	private void sinTiempo() {
		if(quedaTiempo || !config.isTimerOn())
			return;		
		actualizaStats();
		labelMovRest.setText("     Se acabó el Tiempo!        ");
		labelMovRest.setForeground(Color.RED);
		String opciones[] = new String[] { "Reiniciar", "Volver al Menú", "Salir" };
		int opcion = -1;
		do {
			opcion = (int) JOptionPane.showOptionDialog(null, "Se acabó el Tiempo!", "Aviso",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones,
					null);
		} while (opcion == -1);
		switch (opcion) {
		case 0:
			Thread hilo = new Thread(new Runnable() {
				public void run() {
					reiniciarJuego();
					try {
						Thread.currentThread().join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			hilo.start();
			break;
		case 1:
			frame.cambiaLamina(Frame.PORTADA);
			break;
		case 2:
			GestorArchivos.setLastConfig(config);
			System.exit(0);
			break;
		}
	}

	private int getEsquinaIndex() {
		if (esquinaCuadro == 0)
			return tamagnoCuadro - 1;
		if (esquinaCuadro == 1)
			return 0;
		if (esquinaCuadro == 2)
			return tamagnoCuadro * tamagnoCuadro - tamagnoCuadro;
		if (esquinaCuadro == 3)
			return tamagnoCuadro * tamagnoCuadro - 1;
		return -1;
	}

	private String isBorder(int x) { // Detecta si un valor del array pertence al borde del cuadro
		if (x == 0 || x % tamagnoCuadro == 0)
			return "upBorder";
		if (((x + 1) % tamagnoCuadro) == 0)
			return "downBorder";
		return "";
	}

	private void pintaFondo(Graphics2D g2) {
		g2.setColor(Color.GRAY.brighter());
		g2.fill(cuadro.getCuadroBackground());
	}
	
	private boolean isColorDark(Color color) {  
		double darkness = 1-(0.299*color.getRed() + 0.587*color.getGreen() + 0.114*color.getBlue())/255;
		if(darkness<0.5)
			return false; 
		return true;
	}

	private int calculaMovimientos(int tam) {
		return (int)(Math.round(1.4 * tam + 5.53));
	}

	private synchronized void playSound(File absFile) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(absFile);
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		  }).start();
		}
}