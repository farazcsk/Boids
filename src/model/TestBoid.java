package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



/**
 * Each boids represents an individual in the flock. Test boids provide additional functionality such as:
 * <ul>
 * <li>Console logging of events</li>
 * <li>Boids have optional names</li>
 * <li>Set boid colour</li>
 * </ul> 
 * 
 * 
 */
public class TestBoid extends Boid {
    private Color colour;

	public TestBoid(int x, int y, String n, Color c) {
		super(x, y, n);
		this.position = new Vector2d(x, y);
		this.velocity = new Vector2d(0,0);
		this.name = n;
		this.colour = c;
    	System.out.println("Boid "+name+" created at: "+position.xPos+" , "+position.yPos);
    }
	
	public Vector2d getPosition() {
		return position;
	}
	 
    public Vector2d getVelocity() {
		return velocity;
	}

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

	public void draw(GraphicsContext g) {
    	System.out.println("boid "+name+" position: "+position.xPos+" , "+position.yPos);
		int x = (int)this.position.xPos;
    	int y = (int)this.position.yPos;
    	g.setFill(colour);
    	g.fillOval(x, y, radius, radius);
    }

}
