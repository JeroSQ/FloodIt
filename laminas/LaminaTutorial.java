package laminas;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;

import frame.Frame;

public class LaminaTutorial extends JPanel implements MouseWheelListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Frame frame;
	private JPanel panel;
	private JScrollPane scroll;
	private JLabel labelJugar, labelConfig, labelColores, labelTimer, labelStats;
	private Box caja0, caja1, caja2, caja3, caja4, caja5, caja6, caja7, caja8;
	
	public LaminaTutorial(Frame frame) {
		setBackground(Color.CYAN.darker());
		setLayout(new BorderLayout());
		this.frame = frame;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.addMouseWheelListener(new MouseWheelListener() {	
			
			public void mouseWheelMoved(MouseWheelEvent e) {
				int wheelScroll = (int) e.getPreciseWheelRotation();
				frame.cargaLaminas.getVerticalScrollBar().setValue(frame.cargaLaminas.getVerticalScrollBar().getValue() + wheelScroll * 20);
				
			}
		});
		frame.cargaLaminas.getVerticalScrollBar().addMouseWheelListener(panel.getMouseWheelListeners()[0]);
		scroll = new JScrollPane();
		inicializaComponentes();
	}
	
	public void inicializaComponentes() {
		
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//pantalla = new Dimension(1366, 768);
		
		//-------------------------------------------------CAJA NORTH-------------------------------------
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.PORTADA);		
			}
		});
		
		JLabel labelTuto = new JLabel("Tutorial     ");
		labelTuto.setFont(new Font("Roboto", Font.PLAIN, 24));
		
		Box cajaNorth = Box.createHorizontalBox();
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(btnVolver);
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(Box.createHorizontalGlue());
		cajaNorth.add(labelTuto);
		cajaNorth.add(Box.createHorizontalGlue());
		
		add(cajaNorth, BorderLayout.NORTH);
		
		
		//-------------------------------------------------CAJA 0-------------------------------------
		
		JLabel labelFlood = new JLabel("Flood It ");
		labelFlood.setHorizontalAlignment(JLabel.LEFT);
		labelFlood.setFont(new Font("Roboto", Font.ITALIC, frame.getWidth() / 10));
		labelFlood.setForeground(Color.BLUE);
		
		panel.setBackground(Color.CYAN.darker());
		caja0 = Box.createHorizontalBox();
	
		caja0.add(labelFlood);
		caja0.add(Box.createHorizontalGlue());
		
		//-------------------------------------------------CAJA 1-------------------------------------
		
		JLabel labelIndice = new JLabel(" Indices");
		labelIndice.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 17));
		labelIndice.setAlignmentX(LEFT_ALIGNMENT);
		
		JButton btnJugar = new JButton();
	    btnJugar.setText("<HTML><u>Cómo <FONT color=\"#000099\"></FONT>"
	        + "Jugar<u></HTML>");
	    btnJugar.setHorizontalAlignment(SwingConstants.LEFT);
	    btnJugar.setBorderPainted(false);
	    btnJugar.setOpaque(false);
	    btnJugar.setContentAreaFilled(false);
	    btnJugar.setForeground(Color.BLUE.brighter());
	    btnJugar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.cargaLaminas.getVerticalScrollBar().setValue(caja2.getY() + 30);
	    	}
	    });
	    
	    JButton btnConfig = new JButton();
	    btnConfig.setText("<HTML><u>Configuraciones<FONT color=\"#000099\"></FONT>"
	        + "<u></HTML>");
	    btnConfig.setHorizontalAlignment(SwingConstants.LEFT);
	    btnConfig.setBorderPainted(false);
	    btnConfig.setOpaque(false);
	    btnConfig.setContentAreaFilled(false);
	    btnConfig.setForeground(Color.BLUE.brighter());
	    btnConfig.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.cargaLaminas.getVerticalScrollBar().setValue(caja4.getY() + 30);
	    	}
	    });
	    
	    JButton btnColor = new JButton();
	    btnColor.setText("<HTML><u>Colores Personalizados<FONT color=\"#000099\"></FONT>"
	    		+ "<u></HTML>");
	    btnColor.setHorizontalAlignment(SwingConstants.LEFT);
	    btnColor.setBorderPainted(false);
	    btnColor.setOpaque(false);
	    btnColor.setContentAreaFilled(false);
	    btnColor.setForeground(Color.BLUE.brighter());
	    btnColor.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.cargaLaminas.getVerticalScrollBar().setValue((int) (caja5.getY() + 30));
	    	}
	    });
	    
	    JButton btnTimer = new JButton();
	    btnTimer.setText("<HTML><u>Modo Temporizador<FONT color=\"#000099\"></FONT>"
	        + "<u></HTML>");
	    btnTimer.setHorizontalAlignment(SwingConstants.LEFT);
	    btnTimer.setBorderPainted(false);
	    btnTimer.setOpaque(false);
	    btnTimer.setContentAreaFilled(false);
	    btnTimer.setForeground(Color.BLUE.brighter());
	    btnTimer.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.cargaLaminas.getVerticalScrollBar().setValue(caja6.getY() + 30);
	    	}
	    });
	    
	    JButton btnStats = new JButton();
	    btnStats.setText("<HTML><u>Estadísticas<FONT color=\"#000099\"></FONT>"
	    		+ "<u></HTML>");
	    btnStats.setHorizontalAlignment(SwingConstants.LEFT);
	    btnStats.setBorderPainted(false);
	    btnStats.setOpaque(false);
	    btnStats.setContentAreaFilled(false);
	    btnStats.setForeground(Color.BLUE.brighter());
	    btnStats.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.cargaLaminas.getVerticalScrollBar().setValue(caja7.getY() + 30);
	    	}
	    });
	    
	    caja1 = Box.createVerticalBox();
	    caja1.add(Box.createVerticalStrut(20));
	    caja1.add(labelIndice);
	    caja1.add(btnJugar);
	    caja1.add(btnConfig);
	    caja1.add(btnColor);
	    caja1.add(btnTimer);
	    caja1.add(btnStats);
	    
	  //-------------------------------------------------CAJA 2-------------------------------------------------------
	    
	    SimpleAttributeSet sa = new SimpleAttributeSet();
		StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);
		
		labelJugar = new JLabel("Cómo Jugar");
		labelJugar.setAlignmentX(CENTER_ALIGNMENT);
		labelJugar.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 12));
		
	    JTextPane descripcion = new JTextPane();
		descripcion.setFont(new Font("Roboto", Font.PLAIN,  frame.getHeight() / 30));
		descripcion.setBackground(Color.CYAN.darker());
		descripcion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		descripcion.setForeground(new Color(48,48,48));
		descripcion.setEditable(false);
		descripcion.getStyledDocument().setParagraphAttributes(0, frame.getContentPane().getWidth() - 8, sa, false);
		descripcion.setText("Empiezas desde la esquina de inicio seleccionada."
				+ " Para progresar debes pulsar los botones de colores debajo del tablero. "
				+ "Cuando cambias el color, "
				+ "todos los cuadrados adyacentes también cambian de color. "
				+ "Puedes utilizar los botones usando las teclas del teclado. Usa los números para los colores y la tecla R "
				+ "para reiniciar. \n"
				+ "Objetivo: Hacer que todo el cuadro sea del mismo color en el menor número de movimientos.");
		descripcion.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descripcion.getPreferredSize().height * 5.5)));
		
		
		caja2 = Box.createVerticalBox();
		caja2.add(labelJugar);
		caja2.add(descripcion);
	  //-------------------------------------------------CAJA 3-------------------------------------------------------
		
		JLabel labelFotoJugar = new JLabel();
		JLabel labelFotoRestart = new JLabel();
		
		ImageIcon iconoJugar = pantalla.height > 1000 ? new ImageIcon("src/img/botonesJugar.png") : 
	    	new ImageIcon("src/img/botonesJugarChico.png");
		
		ImageIcon iconoRestart = pantalla.height > 1000 ? new ImageIcon("src/img/tutoRestart.png") : 
	    	new ImageIcon("src/img/tutoRestartChico.png");
		
		labelFotoJugar.setIcon(iconoJugar);
		labelFotoRestart.setIcon(iconoRestart);
		
		caja3 = Box.createHorizontalBox();
		caja3.add(Box.createHorizontalGlue());
		caja3.add(labelFotoJugar);
		caja3.add(Box.createHorizontalGlue());
		caja3.add(labelFotoRestart);
		caja3.add(Box.createHorizontalGlue());
	  //-------------------------------------------------CAJA 4--------------------------------------------------------
		labelConfig = new JLabel("Configuraciones");
		labelConfig.setAlignmentX(CENTER_ALIGNMENT);
		labelConfig.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 12));
		
	    JTextPane descConfig = new JTextPane();
	    descConfig.setFont(new Font("Roboto", Font.PLAIN,  frame.getHeight() / 30));
	    descConfig.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    descConfig.setForeground(new Color(48,48,48));
	    descConfig.setOpaque(false);
		descConfig.setEditable(false);
		descConfig.setBackground(null);
		descConfig.getStyledDocument().setParagraphAttributes(0, frame.getContentPane().getWidth() - 8, sa, false);
		descConfig.setText("En Configuraciones podrás cambiar muchos parámetros para cambiar la dificultad y la apariencia. "
				+ "Para acceder a Configuraciones puedes pulsar el bóton Config. en el inicio o hacer click derecho y pulsar Config."
				+ "\n\n\n\n\n\n"
				+ "Allí podrás cambiar: \n\n"
				+ "• La forma de las figuras \n"
				+ "• El tamaño del cuadro \n"
				+ "• La paleta de colores \n"
				+ "• La esquina de inicio \n"
				+ "• El sonido \n"
				+ "• El Modo Temporizador \n"
				+ "• Otras configuraciones avanzadas \n");
		descConfig.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descConfig.getPreferredSize().height * 1.3)));
		
		
		caja4 = Box.createVerticalBox();
		caja4.add(labelConfig);
		caja4.add(descConfig);
		 //-------------------------------------------------CAJA 5--------------------------------------------------------
		labelColores = new JLabel("Colores Personalizados");
		labelColores.setAlignmentX(CENTER_ALIGNMENT);
		labelColores.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 12));
		
	    JTextPane descColores = new JTextPane();
	    descColores.setFont(new Font("Roboto", Font.PLAIN,  frame.getHeight() / 30));
	    descColores.setBackground(null);
	    descColores.setOpaque(false);
	    descColores.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    descColores.setForeground(new Color(48,48,48));
	    descColores.setEditable(false);
	    descColores.getStyledDocument().setParagraphAttributes(0, frame.getContentPane().getWidth() - 8, sa, false);
	    descColores.setText("En Colores Personalizados pordrás crear tus propias paletas de colores para jugar. "
	    		+ "Para ir a Colores Personalizados puedes pulsar en Elegir Colores Personalizados en Configuraciones o "
	    		+ "puedes hacer click derecho y pulsar Colores P. "
	    		+ "\n\n\n\n\n\n"
	    		+ "Para crear una nueva paleta, simplemente pulsa en Crear nueva paleta y se desplegará una ventana donde "
	    		+ "puedes elegir el primer color. Luego, presiona aceptar y se abrirá otra ventana para elegir el segundo color. "
	    		+ "Repite este proceso hasta elegir los 6 colores de tu paleta. Luego, podrás elegir darle un nombre a la paleta "
	    		+ "recién creada o dejar que quede con un nombre por defecto. Ten en cuenta que sólo puedes tener creadas 5 paletas "
	    		+ "y que dentro de cada paleta no pueden haber colores repetidos ni vacíos. Recuerda seleccionar la paleta para "
	    		+ "poder jugar con ella.");
	    int height = (int) (descColores.getFont().getSize() * Toolkit.getDefaultToolkit().getScreenResolution() / 72.0);
	    descColores.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (height * (-0.0273 * frame.getWidth() + 24.4))));
	    
	    Box cajaLaminaColor = Box.createHorizontalBox();
	    cajaLaminaColor.add(new JLabel(new ImageIcon("src/img/laminaColores.png")));
		
		caja5 = Box.createVerticalBox();
		caja5.add(labelColores);
		caja5.add(descColores);
		caja5.add(cajaLaminaColor);
		//-------------------------------------------------CAJA 6--------------------------------------------------------
		labelTimer = new JLabel("Modo Temporizador");
		labelTimer.setAlignmentX(CENTER_ALIGNMENT);
		labelTimer.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 12));
		
	    JTextPane descTimer = new JTextPane();
	    descTimer.setFont(new Font("Roboto", Font.PLAIN,  frame.getHeight() / 30));
	    descTimer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    descTimer.setForeground(new Color(48,48,48));
	    descTimer.setOpaque(false);
	    descTimer.setEditable(false);
	    descTimer.setBackground(null);
	    descTimer.getStyledDocument().setParagraphAttributes(0, frame.getContentPane().getWidth() - 8, sa, false);
	    descTimer.setText("Cuando el Modo Temporizador está activado, el juego funcionará de la misma manera y con los mismos "
	    		+ "movimientos, pero tendrás que intentar ganar sin que se acabe el tiempo. Para activar el Modo Temporizador pulsa "
	    		+ "el reloj en Configuraciones.");
	    descTimer.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descTimer.getPreferredSize().height * 5.2)));
		
		
		caja6 = Box.createVerticalBox();
		caja6.add(labelTimer);
		caja6.add(descTimer);
		//-------------------------------------------------CAJA 7--------------------------------------------------------
		labelStats = new JLabel("Estadísticas");
		labelStats.setAlignmentX(CENTER_ALIGNMENT);
		labelStats.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 12));
		
	    JTextPane descStats = new JTextPane();
	    descStats.setFont(new Font("Roboto", Font.PLAIN,  frame.getHeight() / 30));
	    descStats.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    descStats.setForeground(new Color(48,48,48));
	    descStats.setOpaque(false);
	    descStats.setEditable(false);
	    descStats.setBackground(null);
	    descStats.getStyledDocument().setParagraphAttributes(0, frame.getContentPane().getWidth() - 8, sa, false);
	    descStats.setText("En Estadísticas se almacena todo tu progreso. Allí podrás ver cuántas partidas has jugado, ganado,"
	    		+ " perdido, tu mejor partida (menos movimientos), entre otros datos. Para acceder a Estadísticas haz click derecho"
	    		+ " y pulsa Estadísticas. Si quieres cambiar el archivo donde se guardan las estadísticas, pulsa en el botón "
	    		+ "Cambiar Archivo Est. en Configuraciones. El nuevo archivo tener extensión .txt y debe estar vacío.");
	    descStats.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descStats.getPreferredSize().height * 8.1)));
		
		
		caja7 = Box.createVerticalBox();
		caja7.add(labelStats);
		caja7.add(descStats);
	  //------------------------------------------------CAJA 8---------------------------------------------------------
		JLabel labelCredito = new JLabel("Creado por");
		JLabel labelCreditoNom = new JLabel("✵ ✵ ✵ Jerónimo Squartini✵ ✵ ✵ ");
		labelCredito.setAlignmentX(CENTER_ALIGNMENT);
		labelCredito.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 17));
		labelCredito.setForeground(Color.WHITE);
		labelCreditoNom.setForeground(Color.WHITE);
		labelCreditoNom.setAlignmentX(CENTER_ALIGNMENT);
		labelCreditoNom.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 17));
		
		caja8 = Box.createVerticalBox();
		caja8.add(labelCredito);
		caja8.add(labelCreditoNom);
	  //-------------------------------------------------AÑADIR CAJAS Y CAJA SOUTH-------------------------------------
	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx = 1;
	    c.gridy = 1;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    ImageIcon icono = pantalla.height > 1000 ? new ImageIcon("src/img/flooditchico.png") : 
	    	new ImageIcon("src/img/flooditmini.png");
	    
	    StringBuilder str = new StringBuilder();
	    for(int i = 0; i < frame.getWidth() / (frame.getWidth() * -0.0273 + 17.4) - icono.getIconWidth(); i++) {
	    	str.append(" ");
	    }
	    
	    JLabel espacio = new JLabel();
	    espacio.setText(str.toString());
	    espacio.setIcon(icono);
	    panel.add(espacio, c);
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    c.fill = GridBagConstraints.NONE;
	    panel.add(caja0, c);
	    c.gridy = 1;
	    panel.add(caja1, c);
	    
	    c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 2;
	    c.insets = new Insets(5,8,5,5);
	    c.fill = GridBagConstraints.BOTH;
	    panel.add(caja2, c);
	    
	    c.gridy = 3;
	    panel.add(caja3, c);
	    
	    c.gridy = 4;
	    panel.add(caja4, c);
	    c.gridx = 0;
	    Box cajaPopupConfig = Box.createVerticalBox();
	    cajaPopupConfig.add(Box.createVerticalStrut((int) (frame.getWidth() / 6)));
	    cajaPopupConfig.add(Box.createHorizontalStrut(frame.getWidth() / 3));
	    cajaPopupConfig.add(new JLabel(new ImageIcon("src/img/popupConfig.png")));
	    cajaPopupConfig.add(Box.createVerticalGlue());
	    cajaPopupConfig.add(Box.createVerticalGlue());
	    panel.add(cajaPopupConfig, c);
	    
	    c.gridx = 0;
	    c.gridy = 5;
	    panel.add(caja5, c);
	    
	    c.gridy = 6;
	    panel.add(caja6, c);
	    
	    c.gridy = 8;
	    panel.add(caja7, c);
	    
	    c.gridy = 10;
	    panel.add(caja8, c);
	    
	    Box cajaPopupColor = Box.createVerticalBox();
	    cajaPopupColor.add(Box.createHorizontalStrut(frame.getWidth() / 3));
	    cajaPopupColor.add(Box.createVerticalStrut(frame.getWidth() / 2));
	    cajaPopupColor.add(new JLabel(new ImageIcon("src/img/popupColor.png")));
	    cajaPopupColor.add(Box.createVerticalStrut((int) (descColores.getPreferredSize().height * 1.7)));
	    c.gridy = 5;
	    panel.add(cajaPopupColor, c);
	    
	    Box cajaTimer = Box.createVerticalBox();
	    cajaTimer.add(Box.createHorizontalStrut(frame.getWidth() / 3));
	    cajaTimer.add(new JLabel(new ImageIcon("src/img/timerTuto.png")));
	    c.gridy = 7;
	    panel.add(cajaTimer, c);
	    
	    Box cajaStats = Box.createVerticalBox();
	    cajaStats.add(Box.createHorizontalStrut((int) (frame.getWidth() / ((frame.getWidth() * -0.0273 + 17.4) / 2))));
	    cajaStats.add(new JLabel(new ImageIcon("src/img/popupStats.png")));
	    c.gridy = 9;
	    panel.add(cajaStats, c);
	    
	    scroll.setViewportView(panel);
	    scroll.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker(), 1));
	    scroll.setVerticalScrollBar(new JScrollBar(JScrollBar.VERTICAL, 100,50,0,400));
	    add(scroll, BorderLayout.CENTER);
	    
		JButton btnVolver2 = new JButton("Volver");
		btnVolver2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.PORTADA);
			}
		});
		add(btnVolver2, BorderLayout.SOUTH);
		//-------------------------------------------------POP UP MENU----------------------------------------------------
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
		panel.setComponentPopupMenu(menu);
		descripcion.setComponentPopupMenu(menu);
		descConfig.setComponentPopupMenu(menu);
		descColores.setComponentPopupMenu(menu);
		descTimer.setComponentPopupMenu(menu);
		descStats.setComponentPopupMenu(menu);
		cajaNorth.setComponentPopupMenu(menu);
		caja0.setComponentPopupMenu(menu);
		caja1.setComponentPopupMenu(menu);
		caja2.setComponentPopupMenu(menu);
		caja3.setComponentPopupMenu(menu);
		caja4.setComponentPopupMenu(menu);
		caja5.setComponentPopupMenu(menu);
		caja6.setComponentPopupMenu(menu);
		caja7.setComponentPopupMenu(menu);
		caja8.setComponentPopupMenu(menu);
		
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		panel.getMouseWheelListeners()[0].mouseWheelMoved(e);
	}
}