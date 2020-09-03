package juego;

import java.io.*;
import java.awt.*;

public class GestorArchivos {

	public static Color[] getPaleta(int numPaleta) {
		File path = numPaleta <= 5
				? new File("src/colores/colores.predefinidos/color" + Integer.toString(numPaleta) + ".csv")
				: new File("src/colores/colores.custom/colorCustom" + Integer.toString(numPaleta) + ".csv");
		String[] hex = getCSV(path);
		Color[] arrayC = new Color[hex.length - 1];
		for (int i = 0; i < hex.length - 1; i++) {
			arrayC[i] = Color.decode("0x" + hex[i + 1]);
		}
		return arrayC;
	}

	public static Configuraciones getLastConfig() {
		Configuraciones config = new Configuraciones();
		File path = new File("src/lastConfig.csv");
		if (!path.exists()) {
			config.setDefaultConfigs();
			return config;
		}
		String[] arrayConfig = getCSV(path);
		config.setCuadrados(Boolean.parseBoolean(arrayConfig[0]));
		config.setTamagnoCuadro(Integer.parseInt(arrayConfig[1]));
		config.setEsquinaCuadro(Integer.parseInt(arrayConfig[2]));
		config.setPaletaColorIndex(Integer.parseInt(arrayConfig[3]));
		config.setPaletaColor(getPaleta(Integer.parseInt(arrayConfig[3])));
		config.setSonido(Boolean.parseBoolean(arrayConfig[4]));
		config.setArchivoStats(new File(arrayConfig[5]));
		config.setTimer(Boolean.parseBoolean(arrayConfig[6]));
		return config;
	}

	public static String getNombrePaleta(int numPaleta) {
		File path = numPaleta <= 5
				? new File("src/colores/colores.predefinidos/color" + Integer.toString(numPaleta) + ".csv")
				: new File("src/colores/colores.custom/colorCustom" + Integer.toString(numPaleta) + ".csv");
		if (getCSV(path) == null)
			return "Vacío";
		return getCSV(path)[0];
	}

	public static TablaStats getEstadisticas(Configuraciones config) {
		File path = config.getArchivoStats();
		if (path == null || !path.exists()) {
			File pathNuevo = new File("src/stats.txt");
			config.setArchivoStats(pathNuevo);
			return new TablaStats();
		}
		TablaStats tabla;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(path));
			tabla = (TablaStats) input.readObject();
			input.close();
			return tabla;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean setPaleta(Color[] arrayColores, String nombrePaleta, int indexVacio) {
		try {
			File path = new File("src/colores/colores.custom");
			String[] listFiles = path.list();
			int qArchivos = listFiles.length;
			if (qArchivos >= 5 || indexVacio == -1)
				return false;
			String nombrePaletaFinal = nombrePaleta == null ? "Color Personalizado " + Integer.toString(indexVacio + 1)
					: nombrePaleta;
			File pathCompleto = new File(path + "/colorCustom" + Integer.toString(indexVacio + 6) + ".csv");
			pathCompleto.createNewFile();
			FileWriter escritor = new FileWriter(pathCompleto);
			BufferedWriter bw = new BufferedWriter(escritor);
			bw.write(nombrePaletaFinal);
			for (int i = 0; i < arrayColores.length; i++) {
				String hex = String.format("%02X%02X%02X", arrayColores[i].getRed(), arrayColores[i].getGreen(),
						arrayColores[i].getBlue());
				bw.write("," + hex);
			}
			bw.close();
			escritor.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void setLastConfig(Configuraciones config) {
		File path = new File("src/lastConfig.csv");
		try {
			if (!path.exists())
				path.createNewFile();
			FileWriter escritor = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(escritor);
			String lastConfigs = Boolean.toString(config.isFormaCuadrados()) + ","
					+ Integer.toString(config.getTamagnoCuadro()) + "," + Integer.toString(config.getEsquinaCuadro())
					+ "," + Integer.toString(config.getPaletaColorIndex()) + "," + Boolean.toString(config.isSonidoOn())
					+ "," + config.getArchivoStats() + "," + Boolean.toString(config.isTimerOn());
			bw.write(lastConfigs);
			bw.close();
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setEstadisticas(Object obj, Configuraciones config) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(config.getArchivoStats()));
			output.writeObject(obj);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void borraPaleta(int[] numPaleta) {
		for (int i = 0; i < numPaleta.length; i++) {
			File path = new File(
					"src/colores/colores.custom/colorCustom" + Integer.toString(numPaleta[i] + 6) + ".csv");
			path.delete();
		}
	}

	public static boolean renombraPaleta(int index, String nuevoNombre) {
		File path = new File("src/colores/colores.custom/colorCustom" + Integer.toString(index + 6) + ".csv");
		String nuevoNombreFinal = nuevoNombre == null ? "Color Personalizado " + Integer.toString(index + 1)
				: nuevoNombre;
		String[] csv = getCSV(path);
		csv[0] = nuevoNombreFinal;
		try {
			path.delete();
			path.createNewFile();
			FileWriter escritor = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(escritor);
			bw.write(csv[0] + "," + csv[1] + "," + csv[2] + "," + csv[3] + "," + csv[4] + "," + csv[5] + "," + csv[6]);
			bw.close();
			escritor.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean isFileEmpty(File file) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			if (bf.readLine() == null) {
				bf.close();
				return true;
			}
			bf.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}
	
	public static void borraTodo(Configuraciones config) {
		borraPaleta(new int[] {0,1,2,3,4});
		File lastConfig = new File("src/lastConfig.csv");
		lastConfig.delete();
		File stats = config.getArchivoStats();
		stats.delete();
	}

	private static String[] getCSV(File path) {
		if (!path.exists())
			return null;
		try {
			FileReader lector = new FileReader(path);
			BufferedReader br = new BufferedReader(lector);
			String line = br.readLine();
			String[] csv = line.split(",");
			br.close();
			lector.close();
			return csv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
