package model;

import java.awt.Graphics;

/**
 * Each boid represents an individual in the flock. 
 * 
 * @author Shaun
 *
 */
public class Boid {
	protected Vector2d position;
    protected Vector2d velocity;
    protected final int speedLimit = 5;
    protected String name = null;
    protected int radius = 10;

    /**
     * Boid is initialised with it's coordinates on the display area. 
     * 
     * @param x X Position
     * @param y	Y Position
     */
	public Boid(int x, int y, String n) {
		this.position = new Vector2d(x, y);
		this.velocity = new Vector2d(0,0);
		
		if( n == null) {
			this.name = n;
		}
    }
	
	/**
	 * Returns a Vector2d object with the boids x, y position coordinates
	 * 
	 */
	public Vector2d getPosition() {
		return position;
	}
	 
    public Vector2d getVelocity() {
		return velocity;
	}

    /**
     * Set the boid velocity
     *  
     * @param velocity a Vector2d object with the boids x, y coordinates
     * 
     */
	public void setVelocity(Vector2d velocity) {		
		if ( velocity.xPos > speedLimit) {
			velocity.xPos = speedLimit;
		}
		if ( velocity.yPos > speedLimit) {
			velocity.yPos = speedLimit;
		} 
		
		this.velocity = velocity;
	}

	public void setPosition(Vector2d position) {
		this.position = position;
	}
	
	public String getName() {
		return this.name;
	}

	/**
	 * Display the boid on screen
	 * 
	 * @param g	Graphics Context
	 */
	public void draw(Graphics g) {
		int x = (int)this.position.xPos;
    	int y = (int)this.position.yPos;
    	g.fillOval(x, y, radius, radius);
    }

}
