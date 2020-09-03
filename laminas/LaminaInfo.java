package laminas;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import frame.Frame;
import java.awt.*;
import java.awt.event.*;

public class LaminaInfo extends JPanel {

	private Frame frame;
	private Box cajas[] = new Box[4];

	public LaminaInfo(Frame frame) {
		this.frame = frame;
		setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		setBackground(Color.CYAN.darker());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		inicializaComponentesInfo();
		for(int i = 0; i < cajas.length; i++){
			add(cajas[i]);
		}
	}

	private void inicializaComponentesInfo() {
		for(int i = 0; i < cajas.length; i++){
			cajas[i] = Box.createHorizontalBox();
		}
		SimpleAttributeSet sa = new SimpleAttributeSet();
		StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);
		// ---------------------------BOTON VOLVER-----------------------------------------
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.PORTADA);
			}
		});
		cajas[0].add(Box.createHorizontalStrut(frame.getWidth() / 15));
		cajas[0].add(btnVolver);
		cajas[0].add(Box.createHorizontalGlue());
		// ---------------------------LABEL TÍTUTLO---------------------------------------
		JLabel titulo = new JLabel("Cómo Jugar");
		titulo.setFont(new Font("Roboto", Font.BOLD, frame.getWidth() / 10 / 2));
		cajas[1].add(Box.createHorizontalGlue());
		cajas[1].add(titulo);
		cajas[1].add(Box.createHorizontalGlue());
		//---------------------------LABEL FLOOD IT---------------------------------------
		JLabel labelFlood = new JLabel("Flood It");
		labelFlood.setFont(new Font("Roboto", Font.ITALIC, frame.getWidth() / 10));
		labelFlood.setForeground(Color.BLUE);
		cajas[1].add(labelFlood);
		cajas[1].add(Box.createHorizontalGlue());
		// ---------------------------TEXT RESUMEN---------------------------------------
		JTextPane resumen = new JTextPane();
		resumen.setFont(new Font("Roboto", Font.PLAIN, frame.getHeight() / 20));
		resumen.setBackground(Color.CYAN.darker());
		resumen.setEditable(false);
		resumen.getStyledDocument().setParagraphAttributes(0, 1000, sa, false);
		resumen.setText("Llena todo el tablero del mismo color dentro de los movimientos establecidos.");
		cajas[2].add(resumen);
		//---------------------------TEXT DESC------------------------------------------
		JTextPane descripcion = new JTextPane();
		descripcion.setFont(new Font("Roboto", Font.PLAIN,  frame.getHeight() / 25));
		descripcion.setBackground(Color.CYAN.darker());
		descripcion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		descripcion.setForeground(new Color(48,48,48));
		descripcion.setEditable(false);
		descripcion.getStyledDocument().setParagraphAttributes(0, 1000, sa, false);
		descripcion.setText("Empiezas desde la esquina de inicio seleccionada."
				+ " Para progresar debes pulsar los botones de colores debajo del tablero. "
				+ "Cuando cambias el color, "
				+ "todos los cuadrados adyacentes también cambian de color."
				+ "\n"
				+ "Puedes cambiar la forma, colores, esquina de inicio"
				+ " y muchos parámetros más en Configuraciones. "
				+ "\n"
				+ "Para enceder el Modo Timer, presiona el reloj en Configuraciones.");
		cajas[3].add(descripcion);
	}
}
