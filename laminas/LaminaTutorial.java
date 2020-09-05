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
		
		JLabel labelIndice = new JLabel(" Índices");
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
	    		System.out.println(scroll.getVerticalScrollBar().getValue());
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
	    		
	    	}
	    });
	    
	    Box caja1 = Box.createVerticalBox();
	    caja1.add(Box.createVerticalStrut(20));
	    caja1.add(labelIndice);
	    caja1.add(btnJugar);
	    caja1.add(btnConfig);
	    caja1.add(btnStats);
	    caja1.add(btnColor);
	    
	  //-------------------------------------------------CAJA 2-------------------------------------------------------
	    
	    SimpleAttributeSet sa = new SimpleAttributeSet();
		StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);
		
		JLabel labelJugar = new JLabel("Cómo Jugar");
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
		
	    scroll.setViewportView(panel);
	    scroll.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker(), 1));
	    add(scroll, BorderLayout.CENTER);
	    
		JButton btnVolver2 = new JButton("Volver");
		btnVolver2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.PORTADA);
			}
		});
		add(btnVolver2, BorderLayout.SOUTH);
	}
}