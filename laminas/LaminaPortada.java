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

	private void inicializaComponentesPortada() { ;
		JLabel labelFoto = new JLabel(new ShrinkIcon("src/img/floodit.png"));
		add(labelFoto, BorderLayout.CENTER);
		//-------------------------------------Combo Jugador----------------------------------
		tipoJugador = new JComboBox<String>();
		tipoJugador.setBounds(5, 15, 90, 20);
		tipoJugador.addItem("Player");
		tipoJugador.addItem("BetaTester");
		tipoJugador.setMaximumSize(new Dimension(tipoJugador.getWidth(), 25));
		tipoJugador.setToolTipText("Cambiar Jugador");
		tipoJugador.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(tipoJugador.getSelectedItem().equals("BetaTester"))
					config.setBetaTester(true);
				else
					config.setBetaTester(false);
			}
			
		});
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(tipoJugador);
		//----------------------------------Label Título-------------------------------------
		JLabel titulo = new JLabel("Flood It");
		titulo.setFont(new Font("Roboto", Font.PLAIN, 30));
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
		JMenuItem info = new JMenuItem("Info");
		info.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.INFO);
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
		menu.add(info);
		menu.add(config);
		menu.add(stats);
		setComponentPopupMenu(menu);
	}
}
