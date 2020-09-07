package tempo;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class JTimer extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TimeEndedListener> listeners = new ArrayList<TimeEndedListener>();
	private int hours, mins, secs, msecs;
	private static int timeStamps, timeInMS, timeInput;
	private int flashThreshold = 10;
	private double flashsPerSecond = 2;
	private Color backgroundFlash = Color.RED;
	private Color foregroundFlash = Color.BLACK;
	private Color background, foreground;
	private boolean showMS;
	private volatile boolean run;
	private boolean flash = false;
	private StringBuilder str = new StringBuilder("");

	public static final int HOURS = 3;
	public static final int MINUTES = 2;
	public static final int SECONDS = 1;

	private Thread hilo, hiloFlash;

	protected JTimer() {
	}

	public JTimer(int timeStamps, int timeInMS, boolean showMS, int swingConstants) {
		super("", swingConstants);
		JTimer.timeStamps = timeStamps;
		JTimer.timeInMS = timeInMS;
		this.showMS = showMS;

		int showMSint = showMS ? 1 : 0;
		int height = (int) (getFont().getSize() * Toolkit.getDefaultToolkit().getScreenResolution() / 72.0);
		this.setPreferredSize(new Dimension(height * timeStamps + height * showMSint, height));

		if (timeInMS > getMaxMS())
			JTimer.timeInMS = getMaxMS();

		timeInput = JTimer.timeInMS;
		ajustaStamps();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		calculaString();
		super.setText(str.toString());
		int bordes = getBorder() == null ? 0 : getBorder().getBorderInsets(this).left * 2;
		if (super.isPreferredSizeSet())
			this.setPreferredSize(new Dimension(g.getFontMetrics().stringWidth(str.toString()) + bordes, getHeight()));
		else
			this.setPreferredSize(super.getPreferredSize());
	}

	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
	}
	
	public void setBackground(Color background) {
		super.setBackground(background);
		this.background = background;
	}
	
	public void setForeground(Color foreground) {
		super.setForeground(foreground);
		this.foreground = foreground;
	}
	
	public void start() {
		run = true;
		if (!(hilo == null) && hilo.isAlive())
			return;
		creaHilo();
		hilo.start();
	}

	public void stop() {
		run = false;
		try {
			if ((hilo == null) || !hilo.isAlive())
				return;
			hilo.join();
			if(hiloFlash == null || !hiloFlash.isAlive())
				return;
			hiloFlash.join();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	}

	public void reset() {
		JTimer.timeInMS = JTimer.timeInput;
		ajustaStamps();
		repaint();
	}

	public void setFlashing(boolean flash) {
		this.flash = flash;
	}

	public void setFlashThreshold(int flashThreshold) {
		this.flashThreshold = flashThreshold;
	}

	public void setFlashColor(Color background, Color foreground) {
		backgroundFlash = background;
		foregroundFlash = foreground;
	}

	public void setFlashSpeed(double flashsPerSecond) {
		this.flashsPerSecond = flashsPerSecond;
	}

	public boolean isFlashingOn() {
		return flash;
	}

	public boolean isTimerRunning() {
		return run;
	}

	public boolean isTimerReset() {
		return JTimer.timeInput == JTimer.timeInMS;
	}

	public int getFlashThreshold() {
		return flashThreshold;
	}

	public double getFlashSpeed() {
		return flashsPerSecond;
	}

	public int getTimeLeft() {
		if (JTimer.timeInput == JTimer.timeInMS)
			return JTimer.timeInMS;
		else if (JTimer.timeInMS < 0)
			return 0;
		else
			return JTimer.timeInMS + 1;
	}

	public int getTimeInput() {
		return JTimer.timeInput;
	}

	public void addTimeEndedListener(TimeEndedListener listenerToAdd) {
		listeners.add(listenerToAdd);
	}

	public void removeTimeEndedListener(TimeEndedListener listenerToRemove) {
		listeners.remove(listenerToRemove);
	}

	private void calculaString() {
		str.setLength(0);
		switch (timeStamps) {
		case 1:
			str.append(String.format("%02d", secs));
			break;
		case 2:
			str.append(String.format("%02d", mins) + ":" + String.format("%02d", secs));
			break;
		case 3:
			str.append(String.format("%02d", hours) + ":" + String.format("%02d", mins) + ":"
					+ String.format("%02d", secs));
			break;
		}
		str.append(showMS ? ":" + String.format("%03d", msecs) : "");
	}

	private int getMaxMS() {
		switch (timeStamps) {
		case 1:
			return 59999;
		case 2:
			return 3599999;
		case 3:
			return 86399999;
		}
		return 0;
	}

	private void creaHilo() {
	
		hilo = new Thread(new Runnable() {
			public void run() {
				boolean flashOnce = false;
				long lastTime = System.nanoTime();
				final double ns = 1000000;
				double delta = 0;
				while (run) {
					long now = System.nanoTime();
					delta += (now - lastTime) / ns;
					lastTime = now;
					while (delta >= 1) {
						ajustaStamps();
						JTimer.timeInMS--;
						repaint();
						delta--;
					}
					if (JTimer.timeInMS < 0) {
						run = false;
						timeEnded();
					}
					if(JTimer.timeInMS <= flashThreshold && !flashOnce) {
						flashOnce = true;
						flash();
					}
				}
			}
		});
	}

	private void ajustaStamps() {
		msecs = (int) ((timeInMS % 1000));
		secs = (int) (timeInMS / 1000) % 60;
		mins = (int) ((timeInMS / (1000 * 60)) % 60);
		hours = (int) ((timeInMS / (1000 * 60 * 60)) % 24);
	}

	private void flash() {
		if (!flash)
			return;

		hiloFlash = new Thread(new Runnable() {

			public void run() {
				int counter = 0;
				long lastTime = System.nanoTime();
				final double ns = 1000000000 / flashsPerSecond;
				double delta = 0;
				changeColors(counter % 2 == 0);
				counter++;
				while (run && timeInMS <= flashThreshold + ns) {
					long now = System.nanoTime();
					delta += (now - lastTime) / ns;
					lastTime = now;
					while (delta >= 1) {
						changeColors(counter % 2 == 0);
						counter++;
						delta--;
					}
				}
			}
		});
		hiloFlash.start();
	}
	
	private void changeColors(boolean flash) {
		if(flash) {
			super.setBackground(backgroundFlash);
			super.setForeground(foregroundFlash);
		} else {
			super.setBackground(background);
			super.setForeground(foreground);
		}
	}

	private void timeEnded() {
		for (TimeEndedListener tel : listeners)
			tel.timeEnded();
		super.setBackground(background);
		super.setForeground(foreground);
	}
}
