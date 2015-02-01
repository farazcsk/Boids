package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

public class Flock {
    private ArrayList<Boid> boids;
    private int seperation = 10;			//Used in rule 2
    private double movementFactor = 1000; 	//Used in rule 1

    public Flock() {
    	boids = new ArrayList<Boid>();
    	
		boids.add(new TestBoid(400, 100, "Red", Color.RED));		
		boids.add(new TestBoid(700, 100, "Green", Color.GREEN));
		boids.add(new TestBoid(250, 30, "Blue", Color.BLUE));

		Random rand = new Random();
		
		for(int i = 0; i < 15; i++) {
			int  x = rand.nextInt(1200) + 1;
			int  y = rand.nextInt(1000) + 1;
				
			boids.add(new Boid(x,y, Integer.toString(i)));
		}
		
		System.out.println("Flock size: "+boids.size());
    } // end constructor
    
    public void updateBoidsPostion() {
    	Vector2d v1, v2, v3, v4, v5 = new Vector2d();
    	for (Boid cBoid : boids) {
			System.out.print("Current boid: "+cBoid.getName()+" | ");
		v1 = groupFlock(cBoid);
		v2 = collisionAvoidance(cBoid);
		v3 = matchFlockVelocity(cBoid);
		v4 = bounding(cBoid);
		v5 = positionTend(cBoid);
		
		Vector2d sum = new Vector2d();
		sum = sum.add(v1);
		sum = sum.add(v2);
		sum = sum.add(v3);
		sum = sum.add(v4);
		//sum = sum.add(v5);
		
		cBoid.setVelocity(cBoid.getVelocity().add(sum));	
		cBoid.setPosition(cBoid.getPosition().add(cBoid.getVelocity()));
			
		System.out.print("v1: "+v1.xPos+","+v1.yPos+" | ");
		System.out.print("v2: "+v2.xPos+","+v2.yPos+" | ");
		//System.out.print("v3: "+v3.xPos+","+v3.yPos+" | ");
		//System.out.print("v4: "+v4.xPos+","+v4.yPos+" | ");
		//System.out.print("v4: "+v5.xPos+","+v5.yPos+" | ");
		//System.out.print("v5: "+v5.xPos+","+v5.yPos+" | ");
		System.out.print("sum: "+sum.xPos+","+sum.yPos+" | ");
		System.out.println("");

	}//end iteration through flock
    }
	
    /**
     * Rule 1: Adjust the position of each boid so they fly towards the centre of the group or average position of the flock. Not including the boid itself.
     */
    private Vector2d groupFlock(Boid cBoid) {
    	Vector2d center = new Vector2d();
    	
    	for (Boid aBoid : boids) {
    		if(!aBoid.equals(cBoid)) {
    			center = center.add(aBoid.getPosition());
    		}
    	}
    	center = center.division(boids.size() - 1 );
    	center = center.subtract(cBoid.getPosition());
    	center = center.division(movementFactor);
    	
    	return center;   
    }

    /**
     * Rule 2: Check to see if the boid is within a specified distance of other objects. If so move it as far away again as it already is.
     */
    private Vector2d collisionAvoidance(Boid cBoid) {
    	Vector2d correction = new Vector2d();
    	Vector2d cPosition = new Vector2d(cBoid.getPosition());

    	for (Boid aBoid : boids) {
    		if (!aBoid.equals(cBoid)) {
    			Vector2d aPosition = aBoid.getPosition();
    			Vector2d xD = new Vector2d(aPosition.xPos - cPosition.xPos, aPosition.yPos - cPosition.yPos);
			
    			//System.out.print("| x.abs:"+Math.abs(xD.xPos)+", y.abs"+Math.abs(xD.yPos) +" | ");			
    			if(Math.abs(xD.xPos) < seperation && Math.abs(xD.yPos) < seperation) {	
    				correction = correction.subtract(xD);
    			}
		
    		}
	}
	return correction;
    }
    
    /**
     * Rule 3: Set the velocity of the boid to the average of the flock.
     */
    private Vector2d matchFlockVelocity(Boid cBoid) {
    	Vector2d perceivedVelocity = new Vector2d();
    	
    	for(Boid aBoid : boids) {
    		if(!aBoid.equals(cBoid)) {
    			perceivedVelocity = perceivedVelocity.add(aBoid.getVelocity());
    		}
    	}
    	perceivedVelocity = perceivedVelocity.division(boids.size() - 1);
    	perceivedVelocity = perceivedVelocity.subtract(cBoid.getVelocity());
    	perceivedVelocity = perceivedVelocity.division(8);
    	return perceivedVelocity;
    }

    /**
     * Rule 4: Bounding the position.
     * 
     * Encourages each boid to stay on screen.
     */
    private Vector2d bounding(Boid cBoid) {
    	//TODO
    	Vector2d bound = new Vector2d();
    	int xMin = 0, xMax = 1200, yMin = 0, yMax = 900;

    	Vector2d cPos = cBoid.getPosition();

		if (cPos.xPos < xMin) {
			 bound.xPos += 1;
		} else if (cPos.xPos > xMax){
			bound.xPos += -1;
		}
		if (cPos.yPos < yMin) {
			 bound.yPos += 1;
		} else if (cPos.yPos > yMax){
			bound.yPos += -1;
		}
    	return bound;
    }
    
    /**
     * Rule 5: Tend towards Position
     * 
     * Acting as a goal setting provides predictable behaviour of the group.
     * 
     * @param cBoid
     * @return
     */
    private Vector2d positionTend(Boid cBoid) {
        Vector2d place = new Vector2d(600,450);
    	Vector2d tend = new Vector2d();
		
		tend = new Vector2d(place.subtract(cBoid.getPosition()));
		tend.division(100);

		return tend;
    }
    /**
     * Paint each boid comprising the flock on screen.
     * @param g
     */
    public void drawBoids(GraphicsContext g) {
    	for(Boid aBoid : boids) {
    		aBoid.draw(g);
    	}
    }

	
}
