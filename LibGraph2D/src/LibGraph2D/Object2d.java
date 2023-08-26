package LibGraph2D;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import LibGraph2D.Events.Object2dEvents;
import LibGraph2D.Exceptions.GraphException;

public abstract class Object2d {
	private Dimension position = new Dimension(0,0);
	private Dimension size = new Dimension(0,0);
	private ArrayList<Object2dEvents> eventOj2d = new ArrayList<Object2dEvents>();
	private ArrayList<BufferedImage> i = new ArrayList<BufferedImage>();
	protected BufferedImage im = null;
	private int min = 0;
	private int max = 0;
	private int act = 0;
	private int sendFPS = 0;
	private int fps = 0;
	protected Scene2d q = null;
	public boolean XCenter = false;
	public boolean YCenter = false;
	public void setPosition(Dimension g) {
		position = g;
	}
	public void setSize(int width,int height) {
		size = new Dimension(width,height);
	}
	public Dimension getPosition() {
		return position;
	}
	public void addEventObject2d(Object2dEvents e) {
		eventOj2d.add(e);
	}
	public abstract void draw(Graphics g);
	public void destroy() {
		if(q != null) {
			q.destroy(this);
		}else {
			throw new GraphException("pon el objeto en un esenario primero");
		}
	}
	protected void next() {
		fps++;
		if(fps >= sendFPS) {
			act++;
			fps = 0;
		}
		if(act >= max) {
			act = min;
		}
		if(act < i.size()) {
			BufferedImage h = i.get(act);
			im = new BufferedImage(size.width,size.height,h.getType());
			Graphics g = im.createGraphics();
			g.drawImage(h, 0, 0, null);
			g.dispose();
		}else {
			im = new BufferedImage(size.width,size.height, BufferedImage.TYPE_INT_ARGB);
		}
		for(Object2dEvents w : eventOj2d) {
			w.fps();
		}
	}
	public void draw() {
		if(im == null) {
			im = new BufferedImage(size.width,size.height, BufferedImage.TYPE_INT_ARGB);
		}
		Graphics g = im.createGraphics();
		draw(g);
		g.dispose();
	}

	public int speedX = 0;
	public int speedY = 0;
	public float gravity = 0;
}
