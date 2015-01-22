package view;

import java.awt.Canvas;
import java.awt.Graphics;

import model.Flock;

/**
 * World class creates the canvas. Which represents the boids environment.
 * @author Shaun
 *
 */
public class World extends Canvas {
	private static Flock flock;
	
	public World() {
		World.flock = new Flock();
	}
	
	/**
	 * Updates the position of all boids in flock and displays all elements on screen.
	 */
	public void paint(Graphics g) {	
		flock.updateBoidsPostion();
		flock.drawBoids(g);
		g.fillRect(600, 450, 10, 10);
}
	

}
