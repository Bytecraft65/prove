package LibGraph2D;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import LibGraph2D.Exceptions.GraphException;

public class LibGraph2D extends JFrame{
	private int fps = 20;
	private Scene2d a = null;
	private Timer time = null;
	public LibGraph2D(int y,int y2) {
		super();
		setLayout(null);
		setSize(y,y2);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public LibGraph2D(Dimension g) {
		this(g.width,g.height);
	}
	public LibGraph2D(String h) {
		this(100,100);
		setTitle(h);
	}
	public void showGraph() {
		setVisible(true);
	}
	public void hideGraph() {
		setVisible(false);
	}
	public void setFPS(int y) {
		if(y > 255) {
			throw new GraphException("FPS Superan el maximo de 255");
		}
		fps = y;
	}
	public void start() {
		if(a == null) {
			throw new GraphException("Scene not created");
		}
		if(time != null) {
			time.cancel();
		}
		time = new Timer();
		TimerTask timertask = new TimerTask() {
			public void run() {
				a.repaint();
			}
		};
		time.scheduleAtFixedRate(timertask, 0, 1000 / fps);
	}
	public Scene2d getScene() {
		 a = new Scene2d(this,fps);
		 add(a);
		return a;
	}
}