package LibGraph2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import LibGraph2D.Exceptions.GraphException;

public class Scene2d extends JPanel{
	/*
	 * 
	 * */
	private static final long serialVersionUID = 1L;
	private JFrame t = null;
	private int fps = 0;
	private Dimension sizeScene = null;
	private Color b = null;
	private ArrayList<Object2d> a = new ArrayList<Object2d>();
	private ArrayList<Object2d> removesList = new ArrayList<Object2d>();
	protected Scene2d(JFrame g,int fps){
		//super();
		t = g;
		setSize(g.getSize());
		this.fps = fps;
	}
	public void addObject(Object2d g) {
		if(g == null) {
			throw new GraphException("me pasaste null?");
		}
		g.q = this;
		a.add(g);
	}
	public void destroy(Object2d y) {
		removesList.add(y);
	}
	private void removed(Object2d q) {
		for(int i = 0; i < removesList.size(); i++) {
			if(q == removesList.get(i)) {
				removesList.remove(i);
				break;
			}
		}
	}
	private void removeListStart() {
		for(Object2d f : removesList) {
			removed(f);
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, t.getWidth(), t.getHeight());
		removeListStart();
		for(Object2d f : a) {
			f.next();
			Dimension q = f.getPosition();

			f.setPosition(new Dimension(
					q.width + f.speedX,
					q.height + f.speedY + (int)(Math.round(f.gravity * 9.8))	
			));
		}
		if( b != null) {
			g2d.setColor(b);
		}else {
			g2d.setColor(new Color(230,230,230));
		}
		
		g2d.fillRect(0, 0, t.getWidth(), t.getHeight());
		removeListStart();
		for(Object2d f : a) {
			Dimension q = f.getPosition();
			g2d.drawImage(f.im, q.width,q.height,null);
		}

	}
}
