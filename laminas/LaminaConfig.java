package laminas;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Enumeration;
import frame.Frame;
import juego.*;

public class LaminaConfig extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Frame frame;
	private JButton btnCambiaArchivo, btnVerTuto, btnVerArchivo, btnResetTodo, btnPredeterminado,
		btnPaleta, btnCustom;
	private JCheckBox boxSonido, btnTimer;
	private JLabel labelShape, labelEsquina;
	private JSpinner spinnerShape;
	private String pathRoot = "src/img/";
	private String pathFormat = ".png";
	private Configuraciones config;
	private ButtonGroup grupoPaleta;
	private Box[] cajas = new Box[11];
	private Box cajaColorPre = Box.createVerticalBox();

	public LaminaConfig(Frame frame, Configuraciones config) {
		setBackground(Color.CYAN.darker());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		this.frame = frame;
		this.config = config;
		for(int i = 0; i < cajas.length; i++){
			cajas[i] = Box.createHorizontalBox();
		}
		inicializaComponentesConfig();
		for(int i = 0; i < cajas.length; i++){
			if(i == 6)
				continue;
			if(i == 7) {
				add(cajaColorPre);
				continue;
			}
			add(cajas[i]);
			add(Box.createVerticalGlue());
		}
		cajas[4].setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Tamaño Cuadro"));
		cajaColorPre.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Colores Predefinidos"));
		cajas[10].setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Esquina de Inicio"));
	}
	
	public void actualizaBotones() {
		Enumeration<AbstractButton> elementos = grupoPaleta.getElements();
		while (elementos.hasMoreElements()) {
			AbstractButton btnActual = elementos.nextElement();
			Border borde = btnActual.getActionCommand().equals(Integer.toString(config.getPaletaColorIndex()))
					? BorderFactory.createLineBorder(Color.BLACK, 2)
							: BorderFactory.createLineBorder(Color.CYAN.darker(), 2);
					btnActual.setBorder(borde);
		}
		if(config.getPaletaColorIndex() > 5)
			btnCustom.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		else
			btnCustom.setBorder(BorderFactory.createLineBorder(new Color(178,178,178), 2));
	}

	private void inicializaComponentesConfig() {
		for(int i = 0; i < cajas.length; i++)
			cajas[i].removeAll();
		creaCompGenerales();
		creaBotonesSize();
		creaBotonesEsq();
		creaBotonesPaleta();
		creaLabels();
		agnadeBotonesDesordenados();
		updateUI();
	}

	private void creaBotonesSize() {
		int tamagnos[] = new int[] { 10, 12, 15, 20, 25 };
		ButtonGroup grupoTam = new ButtonGroup();
		for (int i = 0; i < tamagnos.length; i++) {
			JRadioButton btn = new JRadioButton(Integer.toString(tamagnos[i]));
			btn.setBackground(Color.CYAN.darker());
			grupoTam.add(btn);
			btn.setSelected(tamagnos[i] == config.getTamagnoCuadro());
			btn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					config.setTamagnoCuadro(Integer.parseInt(btn.getText()));
				}
			});
			cajas[4].add(Box.createHorizontalGlue());
			cajas[4].add(btn);
		}
		cajas[4].add(Box.createHorizontalGlue());
	}

	private void creaBotonesEsq() {
		ButtonGroup grupoEsquina = new ButtonGroup();
		for (int i = 0; i < 4; i++) {
			JRadioButton btn = new JRadioButton(
					new ImageIcon(pathRoot + Integer.toString(i) + pathFormat));
			btn.setBackground(Color.CYAN.darker());
			grupoEsquina.add(btn);
			btn.setName(Integer.toString(i));
			btn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Enumeration<AbstractButton> elementos = grupoEsquina.getElements();
					while (elementos.hasMoreElements()) {
						AbstractButton btnActual = elementos.nextElement();
						String pathFull = btnActual.isSelected()
								? pathRoot + Integer.toString(Integer.parseInt(btnActual.getName()) + 4) 
										+ pathFormat
								: pathRoot + btnActual.getName() + pathFormat;
						btnActual.setIcon(new ImageIcon(pathFull));
						if (btnActual.isSelected())
						config.setEsquinaCuadro(Integer.parseInt(btnActual.getName()));
					}
				}
			});
			btn.setSelected(i == config.getEsquinaCuadro());
			if (btn.isSelected()) {
				btn.setIcon(new ImageIcon(pathRoot
						+ Integer.toString(Integer.parseInt(btn.getName()) + 4) + pathFormat));
				config.setEsquinaCuadro(Integer.parseInt(btn.getName()));
			}
			cajas[10].add(Box.createHorizontalGlue());
			cajas[10].add(btn);
		}
		cajas[10].add(Box.createHorizontalGlue());
	}

	private void creaBotonesPaleta() {
		int counter = 0;
		grupoPaleta = new ButtonGroup();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				String pathFull = pathRoot + "paleta" + Integer.toString(counter) + pathFormat;
				btnPaleta = new JButton();
				btnPaleta.setActionCommand(Integer.toString(counter));
				btnPaleta.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker(), 2));
				btnPaleta.setIcon(new ImageIcon(pathFull));
				btnPaleta.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int indexPaleta = Integer.parseInt(e.getActionCommand());
						config.setPaletaColorIndex(indexPaleta);
						config.setPaletaColor(GestorArchivos.getPaleta(indexPaleta));
						actualizaBotones();
						frame.llamaEliminaSeleccion();
					}
				});
				btnPaleta.setSelected(counter == config.getPaletaColorIndex());
				if (btnPaleta.isSelected())
					btnPaleta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				grupoPaleta.add(btnPaleta);
				if(j == 0) {
					cajas[6].add(Box.createHorizontalGlue());
					cajas[6].add(btnPaleta);
				}else {
					cajas[7].add(Box.createHorizontalGlue());
					cajas[7].add(btnPaleta);
				}
				counter++;
			}
		}
		cajas[6].add(Box.createHorizontalGlue());
		cajas[7].add(Box.createHorizontalGlue());
		cajaColorPre.add(cajas[6]);
		cajaColorPre.add(cajas[7]);
	}
	
	private void creaLabels() {
		//----------------------------LABEL FORMA:----------------------------------------
		labelShape = new JLabel("Forma:");
		labelShape.setFont(new Font("Roboto", Font.PLAIN, 14));
		//-----------------------LABEL TAMAÑO-----------------------------------------
		JLabel labelTamagno = new JLabel("Tamaño del Cuadro");
		labelTamagno.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		labelTamagno.setFont(new Font("Roboto", Font.PLAIN, 20));
		cajas[3].add(labelTamagno);
		//-----------------------LABEL COLORES PREDEF-----------------------------------------
		JLabel labelColor = new JLabel("Colores Predefinidos");
		labelColor.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		labelColor.setFont(new Font("Roboto", Font.PLAIN, 20));
		cajas[5].add(labelColor);
		//-----------------------LABEL ESQUINA INICIO-----------------------------------------
		labelEsquina = new JLabel("Esquina de Inicio");
		labelEsquina.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		labelEsquina.setFont(new Font("Roboto", Font.PLAIN, 20));
		
	}
	
	private void creaCompGenerales() {
		//---------------------------BOTON VOLVER-----------------------------------------
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.PORTADA);
			}
		});
		cajas[0].add(Box.createHorizontalGlue());
		cajas[0].add(btnVolver);
		//-----------------------BOTON CAMBIA ARCHIVO HS-----------------------------------------
		btnCambiaArchivo = new JButton("Cambiar Archivo Est.");
		btnCambiaArchivo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = null;
				try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				chooser = new JFileChooser(config.getArchivoStats());
				UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				chooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int seleccion = chooser.showSaveDialog(frame);
				File file = chooser.getSelectedFile();
				if(seleccion != 0)
					return;
				config.setArchivoStats(file);
				if(GestorArchivos.isFileEmpty(file))
					GestorArchivos.setEstadisticas(new TablaStats(), config);
				else
					GestorArchivos.setEstadisticas(GestorArchivos.getEstadisticas(config), config);
				config.setTablaStats(GestorArchivos.getEstadisticas(config));
			}
		});
		btnCambiaArchivo.setPreferredSize(new Dimension((frame.getWidth() / 2) - 20,
				btnCambiaArchivo.getPreferredSize().height));
		cajas[2].add(Box.createHorizontalGlue());
		cajas[2].add(btnCambiaArchivo);
		//-----------------------BOTON VER TUTORIAL-----------------------------------------
		btnVerTuto = new JButton("Ver Tutorial");
		btnVerTuto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.TUTO);
			}
		});
		btnVerTuto.setPreferredSize(new Dimension(btnCambiaArchivo.getPreferredSize()));
		cajas[2].add(Box.createHorizontalGlue());
		cajas[2].add(btnVerTuto);
		cajas[2].add(Box.createHorizontalGlue());
		//-------------------------BOTON VER ARCHIVO HS-----------------------------------------
		btnVerArchivo = new JButton("Ver Archivo Est.");
		btnVerArchivo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "El archivo se encuentra en: " + config.getArchivoStats().getAbsolutePath());
			}
		});
		btnVerArchivo.setPreferredSize(new Dimension(btnCambiaArchivo.getPreferredSize()));
		cajas[1].add(Box.createHorizontalGlue());
		cajas[1].add(btnVerArchivo);
		//-------------------------BOTON RESET TODO.-----------------------------------------
		btnResetTodo = new JButton("Resetear Todo");
		btnResetTodo.setPreferredSize(btnCambiaArchivo.getPreferredSize());
		btnResetTodo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int opcion = -1;
				opcion = JOptionPane.showOptionDialog(null,
						"¿Está seguro que quiere resetear todo el juego? \n"
						+ "El juego volverá a su estado de recién instalado. No se puede volver atrás.", "Aviso",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				if (opcion != 0)
					return;
				config.getTablaStats().reseteaTabla();
				GestorArchivos.borraTodo(config);
				btnPredeterminado.doClick();
				frame.llamaActualizaColores();
			}
		});
		cajas[1].add(Box.createHorizontalGlue());
		cajas[1].add(btnResetTodo);
		cajas[1].add(Box.createHorizontalGlue());
		//-----------------------BOTON COLOR CUSTOM-----------------------------------------
		btnCustom = new JButton("Elegir Colores Personalizados");
		btnCustom.setBorder(BorderFactory.createLineBorder(new Color(178,178,178), 2));
		btnCustom.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.COLORES);
			}
		});
		cajas[8].add(btnCustom);
		//-----------------------BOTON TIMER----------------------------------------------
		btnTimer = new JCheckBox();
		ImageIcon icon = config.isTimerOn() ? new ImageIcon(pathRoot + "timer" + pathFormat) 
				: new ImageIcon(pathRoot + "timeroff" + pathFormat);
		btnTimer.setIcon(icon);
		btnTimer.setBorder(null);
		btnTimer.setBackground(null);
		btnTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = btnTimer.isSelected() ? new ImageIcon(pathRoot + "timer" + pathFormat) 
						: new ImageIcon(pathRoot + "timeroff" + pathFormat);
				btnTimer.setIcon(icon);
				config.setTimer(btnTimer.isSelected());
			}
		});
		//-----------------------CHECKBOX SONIDO---------------------------------------------
		boxSonido = new JCheckBox(new ImageIcon("src/volume0.png"));
		boxSonido.setSelected(config.isSonidoOn());
		ImageIcon icono0 = new ImageIcon(pathRoot + "volume0" + pathFormat);
		ImageIcon icono1 = new ImageIcon(pathRoot + "volume1" + pathFormat);
		boxSonido.setIcon(boxSonido.isSelected() ? icono0 : icono1);
		boxSonido.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boxSonido.setIcon(boxSonido.isSelected() ? icono0 : icono1);
				config.setSonido(boxSonido.isSelected());
			}
		});
		boxSonido.setBackground(Color.CYAN.darker());
		//---------------------------BOTON RESET PREDETERMINADOS---------------------------
		btnPredeterminado = new JButton("Reset");
		btnPredeterminado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.setDefaultConfigs();
				inicializaComponentesConfig();
				frame.llamaEliminaSeleccion();
			}
		});
		cajas[0].add(Box.createHorizontalGlue());
		cajas[0].add(btnPredeterminado);
		//---------------------------SPINNER SHAPE-----------------------------------------
		String shapes[] = config.isFormaCuadrados() ? new String[] { "Cuadrados", "Círculos" }
		: new String[] { "Círculos", "Cuadrados" };
		SpinnerListModel modeloShape = new SpinnerListModel(shapes) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Object getNextValue() {
				String value = super.getValue().equals("Cuadrados") ? "Círculos" : "Cuadrados";
				return value;
			}
			
			public Object getPreviousValue() {
				String value = super.getValue().equals("Cuadrados") ? "Círculos" : "Cuadrados";
				return value;
			}
		};
		spinnerShape = new JSpinner(modeloShape);
		spinnerShape.setMaximumSize(new Dimension(frame.getWidth() / 6, 25));
		spinnerShape.getEditor().setPreferredSize(new Dimension(70, 25));
		spinnerShape.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				config.setCuadrados(spinnerShape.getValue().equals("Cuadrados") ? true : false);
			}
		});
		JFormattedTextField tf = ((JSpinner.DefaultEditor) spinnerShape.getEditor()).getTextField();
		tf.setEditable(false);
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
	
	private void agnadeBotonesDesordenados() {
		//CAJA 0-------------------
		cajas[0].add(Box.createHorizontalGlue());
		cajas[0].add(labelShape);
		cajas[0].add(Box.createHorizontalGlue());
		cajas[0].add(spinnerShape);
		cajas[0].add(Box.createHorizontalGlue());
		//CAJA 9--------------------
		cajas[9].add(Box.createHorizontalGlue());
		cajas[9].add(boxSonido);
		cajas[9].add(Box.createHorizontalGlue());
		cajas[9].add(labelEsquina);
		cajas[9].add(Box.createHorizontalGlue());
		cajas[9].add(btnTimer);
		cajas[9].add(Box.createHorizontalGlue());
	}
}
