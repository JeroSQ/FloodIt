package laminas;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import frame.Frame;
import juego.*;
import java.util.*;

public class LaminaColores extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Frame frame;
	private Configuraciones config;
	private JList<String> listaColores;
	private String[] nombres;
	private JButton btnSelec, btnCrear,btnBorrar, btnRename; 
	private boolean btnCreado = false;
	private JLabel[] flechas = new JLabel[5];
	private JLabel[] labelsColor = new JLabel[6];
	private Box[] boxHorizontales = new Box[2];
	private Box caja1 = Box.createVerticalBox();
	private Box caja2 = Box.createVerticalBox();
	private Box caja1y2 = Box.createHorizontalBox();
	private Box boxFlecha = Box.createVerticalBox();
	private JPanel panelBtn = new JPanel();

	public LaminaColores(Frame frame, Configuraciones config) {
		setBackground(Color.CYAN.darker());
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		this.frame = frame;
		this.config = config;
		nombres = new String[5];
		panelBtn.setLayout(new GridLayout(6, 1, 5, 5));
		panelBtn.setBackground(Color.CYAN.darker());
		inicializaComponentesColores();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel1.setBackground(Color.CYAN.darker());
		panel2.setBackground(Color.CYAN.darker());
		caja2.add(panelBtn);
		GridBagConstraints gbc = new GridBagConstraints();
		caja1y2.add(boxFlecha);
		caja1y2.add(caja1);
		panel1.add(caja1y2, gbc);
		gbc.gridy = 1;
		panel1.add(new JLabel("       "), gbc);
		gbc.gridy = 2;
		panel1.add(caja2, gbc);
		panel2.add(Box.createVerticalGlue());
		panel2.add(boxHorizontales[1]);
		panel2.add(Box.createVerticalGlue());
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.EAST);
	}
	
	public void eliminaSeleccion() {
		for(int i = 0; i < flechas.length; i++)
			flechas[i].setForeground(Color.CYAN.darker());
		for(int i = 0; i < labelsColor.length; i++) {
			labelsColor[i].setBackground(Color.CYAN.darker());
		}
		listaColores.clearSelection();
	}
	
	public void cargaSeleccion() {
		if(config.getPaletaColorIndex() - 6 < 0) {
			listaColores.clearSelection();
			return;
		}
		listaColores.setSelectedIndex(config.getPaletaColorIndex() - 6);
	}
	
	public void actualizaColores() {
		updateUI();
	}
	
	private void inicializaComponentesColores() {
		boxHorizontales[0] = Box.createVerticalBox();
		boxHorizontales[1] = Box.createVerticalBox();
		//----------------------------------LABEL FLECHITA-------------------------------------
		for(int i = 0; i < flechas.length; i++) {
		flechas[i] = new JLabel("🡆");
		flechas[i].setFont(new Font("Roboto", Font.BOLD, 14));
		flechas[i].setForeground(Color.CYAN.darker());
		if(config.getPaletaColorIndex() - 6 == i)
			flechas[i].setForeground(Color.BLUE);
		boxFlecha.add(flechas[i]);
		}
		//----------------------------------BOTON VOLVER----------------------------------------
		JButton btnVolver = new JButton("Back");
		btnVolver.setBounds(8, 5, 80, 25);
		btnVolver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.CONFIG);
			}
		});
		Box cajaVolv = Box.createHorizontalBox();
		cajaVolv.add(Box.createHorizontalStrut(frame.getWidth() / 10));
		cajaVolv.add(btnVolver, BorderLayout.NORTH);
		cajaVolv.add(Box.createHorizontalGlue());
		JLabel labelColor = new JLabel("Custom Colors");
		labelColor.setFont(new Font("Roboto", Font.PLAIN, frame.getWidth() / 15));
		cajaVolv.add(labelColor);
		cajaVolv.add(Box.createHorizontalGlue());
		add(cajaVolv, BorderLayout.NORTH);
		//------------------------------------JLIST---------------------------------------------
		listaColores = new JList<String>();
		listaColores.setVisibleRowCount(5);
		listaColores.setFixedCellWidth(135);
		listaColores.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				actualizaBotones(true);
				for(int i = 0; i < labelsColor.length; i++) {
					if(listaColores.getSelectedIndex() != -1 
							&& !nombres[listaColores.getSelectedIndex()].equals("Empty"))
						labelsColor[i].setBackground(GestorArchivos.getPaleta(listaColores.getSelectedIndex() + 6)[i]);
					else
						labelsColor[i].setBackground(Color.CYAN.darker());
				}
			}
		});
		caja1.add(listaColores);
		//--------------------------------LABEL PALETA SELEC--------------------------------------
		JLabel labelPaletaSelec = new JLabel("<html><div style='text-align: center;'>"
				+ "<body>Selected<br> Palette:</body><html>");
		boxHorizontales[1].add(labelPaletaSelec);
		//--------------------------------BOTON SELECCIONAR--------------------------------------
		btnSelec = new JButton("Use selected palette");
		btnSelec.setMinimumSize(new Dimension(frame.getWidth() / 2,25));
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] seleccionados = listaColores.getSelectedIndices();
				config.setPaletaColor(GestorArchivos.getPaleta(seleccionados[0] + 6));
				config.setPaletaColorIndex(seleccionados[0]+6);
				actualizaBotones(false);
				frame.llamaActualizaBotones();
				for(int i = 0; i < flechas.length; i++)
					flechas[i].setForeground(Color.CYAN.darker());
				flechas[seleccionados[0]].setForeground(Color.BLUE);
				listaColores.setSelectedIndex(seleccionados[0]);
			}
		});
		panelBtn.add(btnSelec);
		//------------------------------------BOTON CREAR----------------------------------------
		btnCrear = new JButton("Create new palette");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color[] arrayColores = new Color[6];
				Object resultado = creaSelectoresColor();
				if(resultado == null) {
					JOptionPane.showMessageDialog(null, "There cannot be empty colors.");
					return;
				}
				if(resultado.equals("Duplicado")) {
					JOptionPane.showMessageDialog(null, "There cannot be duplicate colors");
					return;
				}
				String nombre;
				do {
					nombre = JOptionPane.showInputDialog("Set palette name (no commas) Max. 25 characters.");
					if(nombre == null)
						break;
				}while(nombre.length() > 25 || nombre.contains(",") || nombre.trim().isEmpty()); 
				arrayColores = (Color[]) resultado;
				boolean paletaCorrecta = GestorArchivos.setPaleta(arrayColores, nombre, getIndexVacio());
				if(paletaCorrecta)
					JOptionPane.showMessageDialog(null, "Palette was successfully created.");
				else
					JOptionPane.showMessageDialog(null, "Palette could not be created.", "Error",JOptionPane.ERROR_MESSAGE);
				int vacio = getIndexVacio();
				config.setPaletaColorIndex(vacio + 6);
				config.setPaletaColor(GestorArchivos.getPaleta(config.getPaletaColorIndex()));
				for(int i = 0; i < flechas.length; i++)
					flechas[i].setForeground(Color.CYAN.darker());
				flechas[vacio].setForeground(Color.BLUE);
				frame.llamaActualizaBotones();
				actualizaBotones(false);
				listaColores.setSelectedIndex(vacio);
			}

		});
		btnCreado = true;
		btnCrear.setMinimumSize(new Dimension(frame.getWidth() / 2,25));
		panelBtn.add(btnCrear);
		//-----------------------------------------BOTÓN BORRAR-----------------------------------------
		btnBorrar = new JButton("Empty selection");
		btnBorrar.setPreferredSize(new Dimension(frame.getWidth() / 2,25));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.INFORMATION_MESSAGE);
				if(respuesta != 0)
					return;
				GestorArchivos.borraPaleta(listaColores.getSelectedIndices());
				actualizaBotones(false);
				eliminaSeleccion();
				config.setPaletaColorIndex(0);
				config.setPaletaColor(GestorArchivos.getPaleta(config.getPaletaColorIndex()));
				frame.llamaActualizaBotones();
			}
		});
		panelBtn.add(btnBorrar);
		//--------------------------------------BOTON SELEC TODOS--------------------------------------
		JButton btnSelecAll = new JButton("Select All");
		btnSelecAll.setPreferredSize(new Dimension(frame.getWidth() / 2,25));
		btnSelecAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaBotones(false);
				listaColores.setSelectedIndices(new int[] {0,1,2,3,4});
			}
		});
		panelBtn.add(btnSelecAll);
		//------------------------------------BOTON DE-SELEC TODOS--------------------------------------
		JButton btnDeSelecAll = new JButton("Unselect All");
		btnDeSelecAll.setPreferredSize(new Dimension(frame.getWidth() / 2,25));
		btnDeSelecAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaBotones(false);
				listaColores.clearSelection();
			}
		});
		panelBtn.add(btnDeSelecAll);
		//-----------------------------------BOTON RENOMBRAR--------------------------------------------
		btnRename = new JButton("Rename Selected Palette");
		btnRename.setPreferredSize(new Dimension(frame.getWidth() / 2,25));
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevoNombre;
				do {
					nuevoNombre = JOptionPane.showInputDialog("Set palette name (no commas) Max. 25 characters.");
					if(nuevoNombre == null)
						return;
				}while(nuevoNombre.length() > 25 || nuevoNombre.contains(",") || nuevoNombre.trim().isEmpty());
				int indexSeleccionado = listaColores.getSelectedIndex();
				boolean renameCorrecto = GestorArchivos.renombraPaleta(indexSeleccionado, nuevoNombre);
				if(renameCorrecto)
					JOptionPane.showMessageDialog(null, "Palette was successfully renamed.");
				else
					JOptionPane.showMessageDialog(null, "Pallete could not be renamed.", "Error",JOptionPane.ERROR_MESSAGE);
				actualizaBotones(false);
			}
		});
		panelBtn.add(btnRename);
		//----------------------------------LABELS COLORES----------------------------------------------
		for(int i = 0; i < labelsColor.length; i++) {
			labelsColor[i] = new JLabel("<html><div style='text-align: center;'><body><br><br></body></html>");
			labelsColor[i].setOpaque(true);
			labelsColor[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			labelsColor[i].setBackground(Color.CYAN.darker());
			boxHorizontales[1].add(labelsColor[i]);
			boxHorizontales[1].add(Box.createVerticalStrut(5));
		}
		//----------------------------------------------------------------------------------------------
		actualizaBotones(false);
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
	
	private void actualizaBotones(boolean isListCaller) {
		if(btnCreado && !listaColores.isSelectionEmpty())
			btnBorrar.setEnabled(true);
		if(btnCreado && listaColores.isSelectionEmpty())
			btnBorrar.setEnabled(false);
		int vacios = 0;
		for(int i = 0; i < nombres.length; i++) {
			nombres[i] = GestorArchivos.getNombrePaleta(i+6);
				
			if(btnCreado && nombres[i].equals("Empty"))
					vacios++;
			if(vacios > 0)
				btnCrear.setEnabled(true);
			else
				btnCrear.setEnabled(false);
			
			if(btnCreado && listaColores.getSelectedIndices().length == 1 
					&& !nombres[listaColores.getSelectedIndex()].equals("Empty")) {
				btnSelec.setEnabled(true);
				btnRename.setEnabled(true);
			}else if(btnCreado && listaColores.getSelectedIndices().length != 1
					|| nombres[listaColores.getSelectedIndex()].equals("Empty")) {
				btnSelec.setEnabled(false);
				btnRename.setEnabled(false);
			}
		}
		btnCreado = true;
		if(isListCaller)
			return;
		listaColores.setListData(nombres);
	}
	
	private Object creaSelectoresColor() {
		HashMap<Color, String> set = new HashMap<Color, String>();
		Color arrayColores[] = new Color[6];
		JColorChooser color = new JColorChooser();
		for (int i = 0; i < 6; i++) {
			arrayColores[i] = color.showDialog(null, "Select Color " + (i + 1), null);
			if (arrayColores[i] == null)
				return null;
			arrayColores[i] = new Color(arrayColores[i].getRed(), arrayColores[i].getGreen()
					, arrayColores[i].getBlue());
			if (set.containsKey(arrayColores[i]))
				return "Duplicado";
			set.put(arrayColores[i], "");
		}
		return arrayColores;
	}
	
	private int getIndexVacio() {
		int indexVacio = -1;
		for(int i = 0; i < nombres.length; i++) {
			if(nombres[i].equals("Empty")) {
				indexVacio = i;
				break;
			}
		}
		return indexVacio;
	}
	
}
