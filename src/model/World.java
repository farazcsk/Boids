package model;

import java.awt.Canvas;
import java.awt.Graphics;

public class World extends Canvas {
	private static Flock flock;
	
	public World() {
		World.flock = new Flock();
	}
	
	public void paint(Graphics g) {	
		flock.updateBoidsPostion();
		flock.drawBoids(g);
		g.fillRect(600, 450, 10, 10);
}
	

}
