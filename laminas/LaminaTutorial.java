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
		
		JButton btnVolver = new JButton("Back");
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
	    btnJugar.setText("<HTML><u>How to <FONT color=\"#000099\"></FONT>"
	        + "Play<u></HTML>");
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
	    btnConfig.setText("<HTML><u>Settings<FONT color=\"#000099\"></FONT>"
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
	    btnColor.setText("<HTML><u>Custom Colors<FONT color=\"#000099\"></FONT>"
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
	    btnTimer.setText("<HTML><u>Timer Mode<FONT color=\"#000099\"></FONT>"
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
	    btnStats.setText("<HTML><u>Stats<FONT color=\"#000099\"></FONT>"
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
		
		labelJugar = new JLabel("How to Play");
		labelJugar.setAlignmentX(CENTER_ALIGNMENT);
		labelJugar.setFont(new Font("Serif", Font.BOLD, frame.getWidth() / 12));
		
	    JTextPane descripcion = new JTextPane();
		descripcion.setFont(new Font("Roboto", Font.PLAIN,  frame.getHeight() / 30));
		descripcion.setBackground(Color.CYAN.darker());
		descripcion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		descripcion.setForeground(new Color(48,48,48));
		descripcion.setEditable(false);
		descripcion.getStyledDocument().setParagraphAttributes(0, frame.getContentPane().getWidth() - 8, sa, false);
		descripcion.setText("You start from the selected start corner."
				+ " To make progress you must click the buttons under the board. "
				+ "When you change the color, "
				+ "all adjacent squares of the same color will change too. "
				+ "You can instead use your keyboard to change colors. Use numbers 1-6 for the colors and the R key "
				+ "to restart. \n"
				+ "Objective: Make the whole board be one color without runnning out of steps.");
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
		labelConfig = new JLabel("Settings");
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
		descConfig.setText("In Settings you can change many parameters for personalized difficulty and look & feel. "
				+ "To access Settings you can press the Settings button in the Menu or right-click and press Settings"
				+ "\n\n\n\n\n\n"
				+ "There you are able to change: \n\n"
				+ "• The figure's shape \n"
				+ "• Board size \n"
				+ "• Color pallete \n"
				+ "• Start corner \n"
				+ "• Toggle sound \n"
				+ "• Timer Mode \n"
				+ "• Other advanced settings \n");
		descConfig.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descConfig.getPreferredSize().height * 1.3)));
		
		
		caja4 = Box.createVerticalBox();
		caja4.add(labelConfig);
		caja4.add(descConfig);
		 //-------------------------------------------------CAJA 5--------------------------------------------------------
		labelColores = new JLabel("Custom Colors");
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
	    descColores.setText("In Custom Colors you can create your own unique color palletes to play with. "
	    		+ "To access Custom Colors you can click on the Choose Custom Colors button in Settings or "
	    		+ "you can right-click and press Custom Colors "
	    		+ "\n\n\n\n\n\n\n"
	    		+ "To create a new palette, just press the Create Palette button and a window will open where "
	    		+ "you can pick the first color. Then, press Accept and another window will open for you to enter the second color. "
	    		+ "Repeat this process until you choose the 6 colors of your palette. Finally, you can choose to name your palette "
	    		+ "or leave it with a default-name. Keep in mind you can only store 5 palettes at the same time "
	    		+ "and a palette cannot contain empty nor duplicate colors . Don't forget to select the palette "
	    		+ "to play with it.");
	    int height = (int) (descColores.getFont().getSize() * Toolkit.getDefaultToolkit().getScreenResolution() / 72.0);
	    descColores.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (height * (-0.0273 * frame.getWidth() + 24.4))));
	    
	    Box cajaLaminaColor = Box.createHorizontalBox();
	    cajaLaminaColor.add(new JLabel(new ImageIcon("src/img/laminaColores.png")));
		
		caja5 = Box.createVerticalBox();
		caja5.add(labelColores);
		caja5.add(descColores);
		caja5.add(cajaLaminaColor);
		//-------------------------------------------------CAJA 6--------------------------------------------------------
		labelTimer = new JLabel("Timer Mode");
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
	    descTimer.setText("When Timer Mode is on, the game will function the same and with the same amount of "
	    		+ "steps, but you'll have to win before the time runs out. To activate it press "
	    		+ "the little clock in Settings.");
	    descTimer.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descTimer.getPreferredSize().height * 5.2)));
		
		
		caja6 = Box.createVerticalBox();
		caja6.add(labelTimer);
		caja6.add(descTimer);
		//-------------------------------------------------CAJA 7--------------------------------------------------------
		labelStats = new JLabel("Stats");
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
	    descStats.setText("All your progress is stored in Stats. There you can see how many games you've played, won,"
	    		+ " lost, your best game (less steps), and other data. To access your Stats right-click"
	    		+ " and press Stats. If you'd like to change the file where the stats are stored, press the "
	    		+ "Change Stats File button in Settings. The new file must have .txt extension and must be empty");
	    descStats.setPreferredSize(new Dimension(frame.getContentPane().getWidth(), (int) (descStats.getPreferredSize().height * 8.1)));
		
		
		caja7 = Box.createVerticalBox();
		caja7.add(labelStats);
		caja7.add(descStats);
	  //------------------------------------------------CAJA 8---------------------------------------------------------
		JLabel labelCredito = new JLabel("Created by");
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
	    
		JButton btnVolver2 = new JButton("Back");
		btnVolver2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.PORTADA);
			}
		});
		add(btnVolver2, BorderLayout.SOUTH);
		//-------------------------------------------------POP UP MENU----------------------------------------------------
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