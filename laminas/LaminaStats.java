package laminas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import frame.Frame;
import juego.*;
import tempo.JTimer;
import tempo.TimeEndedListener;

public class LaminaStats extends JPanel {
	
	private Frame frame;
	private Configuraciones config;
	private Box[] cajas = new Box[5];
	private JTable tabla;
	
	public LaminaStats(Frame frame, Configuraciones config) {
		setLayout(new BorderLayout());
		setBackground(Color.CYAN.darker());
		setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		this.frame = frame;
		this.config = config;
		inicializaComponentes();
		add(cajas[0], BorderLayout.NORTH);
		add(cajas[1], BorderLayout.CENTER);
		add(cajas[2], BorderLayout.SOUTH);
	}
	
	private void inicializaComponentes() {
		for(int i = 0; i < 3; i++) {
			cajas[i] = Box.createHorizontalBox();
			}
		//----------------------------------BOTON VOLVER----------------------------------------
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.cambiaLamina(Frame.CONFIG);
			}
		});
		cajas[0].add(Box.createHorizontalStrut(frame.getWidth() / 15));
		cajas[0].add(btnVolver);
		cajas[0].add(Box.createHorizontalGlue());
		//-----------------------------------LABEL ESTADISTICAS--------------------------------
		JLabel labelStats = new JLabel("Estadísticas");
		labelStats.setFont(new Font("Roboto", Font.PLAIN, 24));
		cajas[0].add(labelStats);
		cajas[0].add(Box.createHorizontalGlue());
		//----------------------------------JTABLE----------------------------------------------
		tabla = new JTable();
		tabla.setRowHeight(tabla.getRowHeight() * 2);
		ListModel lm = new AbstractListModel() {
			
			String[] headerRows = {"<html><body>Partidas<br>Jugadas</body></html>",
					"<html><body>Partidas<br>Ganadas</body></html>",
					"<html><body>Partidas<br>Perdidas</body></html>",
					"<html><body>% de P.<br>Ganadas</body></html>",
					"<html><body>Mejor<br>Partida</body></html>",
					"<html><body>Mejor<br>Tiempo</body></html>"
			};
			
			public Object getElementAt(int index) {
				
				return headerRows[index];
			}
			
			public int getSize() {
				
				return headerRows.length;
			}
			
		};
		DefaultTableModel dm = new DefaultTableModel(lm.getSize(), 5);
		tabla.setModel(dm);
		JList rowHeader = new JList(lm);
		rowHeader.setFixedCellWidth(60);
		rowHeader.setFixedCellHeight(tabla.getRowHeight());
		rowHeader.setCellRenderer(new RowRenderer(tabla));
		//-------------------------------INFO TABLA---------------------------------------------
		actualizaStats();
		tabla.getTableHeader().setResizingAllowed(false);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setCellSelectionEnabled(false);
		tabla.setGridColor(Color.CYAN.darker());
		//LABEL ESQUINA
		JLabel tamagnos = new JLabel("Tamaño➡");
		tamagnos.setOpaque(true);
		tamagnos.setForeground(tabla.getTableHeader().getForeground());
		tamagnos.setBackground(tabla.getTableHeader().getBackground());
		tamagnos.setFont(tabla.getTableHeader().getFont());
		tamagnos.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		tamagnos.setVisible(true);
		//SCROLLPANE
		JScrollPane scroll = new JScrollPane();
		scroll.setRowHeaderView(rowHeader);
		scroll.setViewportView(tabla);
		scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, tamagnos);
		scroll.setMaximumSize(new Dimension(frame.getWidth(), (int) (tabla.getRowHeight() * 6.6)));
		cajas[1].add(scroll);
		//---------------------------BOTON RESET HS-----------------------------------------
		JButton btnResetHS = new JButton("Resetear Estadísticas");
		btnResetHS.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int opcion = -1;
				opcion = JOptionPane.showOptionDialog(null,
						"¿Está seguro que quiere resetear las Estadísticas?", "Aviso",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				if (opcion != 0)
					return;
				config.getTablaStats().reseteaTabla();
				actualizaStats();
			}
		});
		cajas[2].add(Box.createHorizontalGlue());
		cajas[2].add(btnResetHS);
		cajas[2].add(Box.createHorizontalGlue());
		//--------------------------------------------------------------------------------------
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
	
	public void actualizaStats() {
		tabla.setModel(new ModeloTablaInvertido(new ModeloTabla(config.getTablaStats().toArray2D())));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		for(int i = 0; i < tabla.getModel().getColumnCount(); i++)
			tabla.getColumnModel().getColumn(i).setCellRenderer(renderer);
	}
}

class ModeloTabla extends AbstractTableModel{
	
	private int[][] arrayInt; 
	
	public ModeloTabla(int[][] arrayInt) {
		this.arrayInt = arrayInt;
	}
	
	public int getColumnCount() {
		
		return 6;
	}

	public int getRowCount() {
		
		return 5;
	}
	
	public Object getValueAt(int x, int y) {
		
		if(y==3 && arrayInt[x][y] != 0)
			return arrayInt[x][y] + "%";
		if(y==5 && arrayInt[x][y] != 0) {
			int mins, secs, msecs;
			msecs = (int) ((arrayInt[x][y] % 1000));
			secs = (int) (arrayInt[x][y] / 1000) % 60;
			mins = (int) ((arrayInt[x][y] / (1000 * 60)) % 60);
			StringBuilder str = new StringBuilder();
			str.append(String.format("%02d", mins) + ":" + String.format("%02d", secs) + ":" + String.format("%03d", msecs));
			return str.toString();
		}
			
		return arrayInt[x][y];
	}
	
}

class ModeloTablaInvertido extends AbstractTableModel{
	
	private TableModel modeloOriginal;
	private String[] tamagnos = {"10","12","15","20","25"};
	
	public ModeloTablaInvertido(TableModel modeloOriginal) {
		this.modeloOriginal = modeloOriginal;
	}

	public int getColumnCount() {
		
		return modeloOriginal.getRowCount();
	}

	public int getRowCount() {
		
		return modeloOriginal.getColumnCount();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		return modeloOriginal.getValueAt(columnIndex, rowIndex);
	}
	
	public String getColumnName(int index) {
		return tamagnos[index];
	}
}

class RowRenderer extends JLabel implements ListCellRenderer{

	public RowRenderer(JTable tabla) {
		JTableHeader header = tabla.getTableHeader();
		setOpaque(true);
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(header.getForeground());
		setBackground(header.getBackground());
		setFont(header.getFont());
	}
	
	public Component getListCellRendererComponent(JList list, Object obj, int index, boolean selected,
			boolean focused) {
		setText(obj == null ? "" : obj.toString());
		return this;
	}
	
}