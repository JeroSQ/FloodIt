package laminas;

import javax.swing.*;
import frame.Frame;
import icon.*;

import java.awt.*;
import java.awt.event.*;
import juego.*;

public class LaminaPortada extends JPanel {

	private LaminaJuego laminaJuego;
	private Configuraciones config;
	private Frame frame;
	private JComboBox<String> tipoJugador;
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
	
	public void resetComboJugador() {
		tipoJugador.setSelectedIndex(0);
	}

	private void inicializaComponentesPortada() { 
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//pantalla = new Dimension(1366, 768);
		ImageIcon icono = pantalla.height > 1000 ? new ShrinkIcon("src/img/floodit.png") 
				: new ShrinkIcon("src/img/flooditchico.png");
		JLabel labelFoto = new JLabel(icono);
		add(labelFoto, BorderLayout.CENTER);
		//-------------------------------------Combo Jugador----------------------------------
		tipoJugador = new JComboBox<String>();
		tipoJugador.setBounds(5, 15, 90, 20);
		tipoJugador.addItem("Player");
		tipoJugador.addItem("Admin");
		tipoJugador.setPrototypeDisplayValue("TipoJugador");
		tipoJugador.setMaximumSize(new Dimension(tipoJugador.getWidth(), 25));
		tipoJugador.setToolTipText("Cambiar Jugador");
		tipoJugador.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(tipoJugador.getSelectedItem().equals("Admin"))
					config.setBetaTester(true);
				else
					config.setBetaTester(false);
			}
			
		});
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(tipoJugador);
		//----------------------------------Label Título-------------------------------------
		JLabel titulo = new JLabel("Flood It");
		titulo.setFont(new Font("Roboto", Font.PLAIN, frame.getWidth() / 11));
		titulo.setForeground(Color.BLUE);
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(titulo);
		//--------------------------------------Boton Config---------------------------------
		JButton btnConfig = new JButton("Config.");
		btnConfig.setPreferredSize(tipoJugador.getMaximumSize());
		btnConfig.setToolTipText("Ir a Configuraciones");
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
		JButton btnComenzar = new JButton("Comenzar");
		btnComenzar.setPreferredSize(tipoJugador.getMaximumSize());
		btnComenzar.setToolTipText("Comenzar nueva partida");
		btnComenzar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				laminaJuego.reiniciarJuego();
				frame.cambiaLamina(Frame.JUEGO);
			}
		});
		cajaSouthBotones.add(Box.createHorizontalGlue());
		cajaSouthBotones.add(btnComenzar);
		cajaSouthBotones.add(Box.createHorizontalGlue());
		//---------------------------------------Boton Salir----------------------------------
		JButton btnSalir = new JButton("Salir");
		btnSalir.setPreferredSize(tipoJugador.getMaximumSize());
		btnSalir.setToolTipText("Salir del juego");
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
		JMenuItem color = new JMenuItem("Colores P.");
		color.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.COLORES);
			}
			
		});
		JMenuItem config = new JMenuItem("Config");
		config.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.CONFIG);
			}
			
		});
		JMenuItem stats = new JMenuItem("Estadísticas");
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
