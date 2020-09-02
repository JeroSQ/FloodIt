package principal;

import java.util.Properties;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import frame.Frame;
import juego.GestorArchivos;

public class Main {

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.put("logoString", "Flood It");
		//	props.put("tooltipBackgroundColor", "255 0 0");
			GraphiteLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (Exception e) {
			System.out.println("No se encontró la clase");
		}
		Frame frame = new Frame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
}
