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
			props.put("macStyleScrollBar", "on");
			GraphiteLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (Exception e) {
			System.out.println("Class not Found");
		}
		Frame frame = new Frame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
}
