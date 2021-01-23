package laminas;

import javax.swing.*;
import frame.Frame;
import icon.*;

import java.awt.*;
import java.awt.event.*;
import juego.*;

public class LaminaPortada extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LaminaJuego laminaJuego;
	private Configuraciones config;
	private Frame frame;
	private JLabel tipoJugador;
	private Box cajaNorth, cajaSouth;
	public LaminaPortada(Frame frame, Configuraciones config) {
		setLayout(new BorderLayout());
		setBackground(Color.CYAN.darker());
		setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		this.frame = frame;
		this.config = config;
		cajaNorth = Box.createHorizontalBox();
		cajaSouth = Box.createVerticalBox();
		inicializaComponentesPortada();
	}

	public void pasameLaminas(LaminaJuego laminaJuego) { 
		this.laminaJuego = laminaJuego;
	}

	private void inicializaComponentesPortada() { 
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//pantalla = new Dimension(1366, 768);
		ImageIcon icono = pantalla.height > 1000 ? new ShrinkIcon("src/img/floodit.png") 
				: new ShrinkIcon("src/img/flooditchico.png");
		JLabel labelFoto = new JLabel(icono);
		add(labelFoto, BorderLayout.CENTER);
		//-------------------------------------Combo Jugador----------------------------------
		tipoJugador = new JLabel("                   ");
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(tipoJugador);
		//----------------------------------Label Título-------------------------------------
		JLabel titulo = new JLabel("Flood It");
		titulo.setFont(new Font("Roboto", Font.PLAIN, frame.getWidth() / 11));
		titulo.setForeground(Color.BLUE);
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(titulo);
		//--------------------------------------Boton Config---------------------------------
		JButton btnConfig = new JButton("Settings");
		btnConfig.setPreferredSize(tipoJugador.getPreferredSize());
		btnConfig.setToolTipText("Go to Settings");
		btnConfig.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.CONFIG);
			}
		});
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(btnConfig);
		cajaNorth.add(Box.createHorizontalGlue());
		add(cajaNorth, BorderLayout.NORTH);
		//---------------------------------------Boton Comenzar-------------------------------
		Box cajaSouthBotones = Box.createHorizontalBox();
		Box cajaFiller = Box.createHorizontalBox();
		JButton btnComenzar = new JButton("Start");
		btnComenzar.setPreferredSize(tipoJugador.getMaximumSize());
		btnComenzar.setToolTipText("Start a new game");
		btnComenzar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int tuto = -1;
				laminaJuego.reiniciarJuego();
				frame.cambiaLamina(Frame.JUEGO);
				if(config.isTutorialOn()) 
					tuto = JOptionPane.showConfirmDialog(null, "Do you want to see the tutorial?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				if(tuto == 0) 
					frame.cambiaLamina(Frame.TUTO);
				config.setTutorial(false);
			}
		});
		cajaSouthBotones.add(Box.createHorizontalGlue());
		cajaSouthBotones.add(btnComenzar);
		cajaSouthBotones.add(Box.createHorizontalGlue());
		//---------------------------------------Boton Salir----------------------------------
		JButton btnSalir = new JButton("Exit");
		btnSalir.setPreferredSize(tipoJugador.getMaximumSize());
		btnSalir.setToolTipText("Exit the game");
		btnSalir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				GestorArchivos.setLastConfig(config);
				System.exit(0);
			}
		});
		cajaSouthBotones.add(btnSalir);
		cajaSouthBotones.add(Box.createHorizontalGlue());
		cajaFiller.add(Box.createVerticalStrut(frame.getWidth() / 10));
		cajaSouth.add(cajaSouthBotones);
		cajaSouth.add(cajaFiller);
		add(cajaSouth, BorderLayout.SOUTH);
		//---------------------------------POP UP MENU-------------------------------------
		JPopupMenu menu = new JPopupMenu();
		JMenuItem color = new JMenuItem("Custom Colors");
		color.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.COLORES);
			}
			
		});
		JMenuItem config = new JMenuItem("Settings");
		config.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.CONFIG);
			}
			
		});
		JMenuItem stats = new JMenuItem("Stats");
		stats.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.STATS);
			}
			
		});
		menu.add(config);
		menu.add(color);
		menu.add(stats);
		setComponentPopupMenu(menu);
	}
}
