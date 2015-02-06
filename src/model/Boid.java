package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Each boid represents an individual in the flock. Displayed on screen as a small coloured circle.  
 * 
 * @author Shaun
 *
 */
public class Boid {
	/**
	 * Vector objects store the x/y coordinates of the boids grid position. 
	 */
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
		
		if( n != null) {
			this.name = n;
		}
    }
	
	/**
	 * Returns a Vector2d object with the boids x, y position coordinates
	 * 
	 * @return	Vector2d
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
	/**
	 * Set the position of the boid.
	 * 
	 * @param position	A Vector2d object with a grid position
	 */
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
	public void draw(GraphicsContext g) {
		int x = (int)this.position.xPos;
    	int y = (int)this.position.yPos;
    	g.setFill(Color.BLACK);
    	g.fillOval(x, y, radius, radius);
    }

}
