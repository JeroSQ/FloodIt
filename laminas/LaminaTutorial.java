package laminas;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;

import frame.Frame;

public class LaminaTutorial extends JPanel {
	
	private Frame frame;
	private JPanel panel;
	public JScrollPane scroll;
	private JLabel labelJugar, labelConfig, labelColores;
	
	public LaminaTutorial(Frame frame) {
		setBackground(Color.CYAN.darker());
		setLayout(new BorderLayout());
		this.frame = frame;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
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
		Box caja0 = Box.createHorizontalBox();
	
		caja0.add(labelFlood);
		caja0.add(Box.createHorizontalGlue());
		
		//-------------------------------------------------CAJA 1-------------------------------------
		
		JLabel labelIndice = new JLabel(" �ndices");
		labelIndice.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 17));
		labelIndice.setAlignmentX(LEFT_ALIGNMENT);
		
		JButton btnJugar = new JButton();
	    btnJugar.setText("<HTML><u>C�mo <FONT color=\"#000099\"></FONT>"
	        + "Jugar<u></HTML>");
	    btnJugar.setHorizontalAlignment(SwingConstants.LEFT);
	    btnJugar.setBorderPainted(false);
	    btnJugar.setOpaque(false);
	    btnJugar.setContentAreaFilled(false);
	    btnJugar.setForeground(Color.BLUE.brighter());
	    btnJugar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.cargaLaminas.getVerticalScrollBar().setValue(200);
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
	    		frame.cargaLaminas.getVerticalScrollBar().setValue(200);
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
	    		frame.cargaLaminas.getVerticalScrollBar().setValue(200);
	    	}
	    });
	    
	    JButton btnStats = new JButton();
	    btnStats.setText("<HTML><u>Estad�sticas<FONT color=\"#000099\"></FONT>"
	        + "<u></HTML>");
	    btnStats.setHorizontalAlignment(SwingConstants.LEFT);
	    btnStats.setBorderPainted(false);
	    btnStats.setOpaque(false);
	    btnStats.setContentAreaFilled(false);
	    btnStats.setForeground(Color.BLUE.brighter());
	    btnStats.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.cargaLaminas.getVerticalScrollBar().setValue(200);
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
	    		frame.cargaLaminas.getVerticalScrollBar().setValue(200);
	    	}
	    });
	    
	    Box caja1 = Box.createVerticalBox();
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
		
		labelJugar = new JLabel("C�mo Jugar");
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
				+ "todos los cuadrados adyacentes tambi�n cambian de color. "
				+ "Puedes utilizar los botones usando las teclas del teclado. Usa los n�meros para los colores y la tecla R "
				+ "para reiniciar. \n"
				+ "Objetivo: Hacer que todo el cuadro sea del mismo color en el menor n�mero de movimientos.");
		descripcion.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descripcion.getPreferredSize().height * 5.5)));
		
		
		Box caja2 = Box.createVerticalBox();
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
		
		Box caja3 = Box.createHorizontalBox();
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
		descConfig.setText("En Configuraciones podr�s cambiar muchos par�metros para cambiar la dificultad y la apariencia. "
				+ "Para acceder a Configuraciones puedes pulsar el b�ton en el inicio o hacer click derecho y pulsar Config."
				+ "\n\n\n\n\n\n"
				+ "All� podr�s cambiar: \n\n"
				+ "� La forma de las figuras \n"
				+ "� El tama�o del cuadro \n"
				+ "� La paleta de colores \n"
				+ "� La esquina de inicio \n"
				+ "� El sonido \n"
				+ "� El Modo Temporizador \n"
				+ "� Otras configuraciones avanzadas \n");
		descConfig.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descConfig.getPreferredSize().height * 1.3)));
		
		
		Box caja4 = Box.createVerticalBox();
		caja4.add(labelConfig);
		caja4.add(descConfig);
		 //-------------------------------------------------CAJA 5--------------------------------------------------------
		labelColores = new JLabel("Colores Personzalizados");
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
	    descColores.setText("En Colores Personalizados pordr�s crear tus propias paletas de colores para jugar. "
	    		+ "Para ir a Colores Personalizados puedes pulsar en Elegir Colores Personalizados en Configuraciones o "
	    		+ "puedes hacer click derecho y pulsar Colores P. "
	    		+ "\n\n\n\n\n\n"
	    		+ "Para crear una nueva paleta, simplemente pulsa en Crear nueva paleta y se desplegar� una ventana donde "
	    		+ "puedes elegir el primer color. Luego, presiona aceptar y se abrir� otra ventana para elegir el segundo color. "
	    		+ "Repite este proceso hasta elegir los 6 colores de tu paleta. Luego, podr�s elegir darle un nombre a la paleta "
	    		+ "reci�n creada o dejar que quede con un nombre por defecto. Ten en cuenta que s�lo puedes tener creadas 5 paletas "
	    		+ "y que dentro de cada paleta no pueden haber colores repetidos ni vac�os. Recuerda seleccionar la paleta para "
	    		+ "poder jugar con ella.");
	    descColores.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descColores.getPreferredSize().height * 2.3)));
		
	    Box cajaLaminaColor = Box.createHorizontalBox();
	    cajaLaminaColor.add(new JLabel(new ImageIcon("src/img/laminaColores.png")));
		
		Box caja5 = Box.createVerticalBox();
		caja5.add(labelColores);
		caja5.add(descColores);
		caja5.add(cajaLaminaColor);
		
	  //-------------------------------------------------A�ADIR CAJAS Y CAJA SOUTH-------------------------------------
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
	    cajaPopupConfig.add(Box.createVerticalStrut(frame.getWidth() / 8));
	    cajaPopupConfig.add(Box.createHorizontalStrut(frame.getWidth() / 3));
	    cajaPopupConfig.add(new JLabel(new ImageIcon("src/img/popupConfig.png")));
	    cajaPopupConfig.add(Box.createVerticalGlue());
	    cajaPopupConfig.add(Box.createVerticalGlue());
	    panel.add(cajaPopupConfig, c);
	    
	    c.gridx = 0;
	    c.gridy = 5;
	    panel.add(caja5, c);
	    
	    Box cajaPopupColor = Box.createVerticalBox();
	    cajaPopupColor.add(Box.createHorizontalStrut(frame.getWidth() / 3));
	    cajaPopupColor.add(Box.createVerticalStrut(frame.getWidth() / 2));
	    cajaPopupColor.add(new JLabel(new ImageIcon("src/img/popupColor.png")));
	    cajaPopupColor.add(Box.createVerticalStrut((int) (descColores.getPreferredSize().height * 1.7)));
	    panel.add(cajaPopupColor, c);
		
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
		JMenuItem stats = new JMenuItem("Estad�sticas");
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
		cajaNorth.setComponentPopupMenu(menu);
		caja0.setComponentPopupMenu(menu);
		caja1.setComponentPopupMenu(menu);
		caja2.setComponentPopupMenu(menu);
		caja3.setComponentPopupMenu(menu);
		caja4.setComponentPopupMenu(menu);
		caja5.setComponentPopupMenu(menu);
	}
}