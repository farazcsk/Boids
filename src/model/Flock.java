package model;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Flock {
    private ArrayList<Boid> boids;
    private int seperation = 10;			//Used in rule 2
    private double movementFactor = 1000; 	//Used in rule 1

    public Flock() {
    	boids = new ArrayList<Boid>();
    	
		boids.add(new TestBoid(400, 100, "Red", Color.RED));		
		boids.add(new TestBoid(700, 100, "Green", Color.GREEN));
		boids.add(new TestBoid(250, 30, "Blue", Color.BLUE));

		Random randNum = new Random();
		RandomName randName = new RandomName();
		
		for(int i = 0; i < 15; i++) {
			int  x = randNum.nextInt(1200) + 1;
			int  y = randNum.nextInt(1000) + 1;
				
			boids.add(new Boid(x,y, randName.getName() ));
		}
		
		System.out.println("Flock size: "+boids.size());
    } // end constructor

    /**
     * Updates the position coordinates of each boid in the flock. By applying the rules that govern 
     * the behaviour of the group to each one in turn.
     */
    public void updateBoidsPostion() {
    	Vector2d v1, v2, v3, v4, v5 = new Vector2d();
    	for (Boid cBoid : boids) {
			System.out.println("Current boid: "+cBoid.getName()+" | ");
			

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
			
		//System.out.print("v1: "+v1.xPos+","+v1.yPos+" | ");
		//System.out.print("v2: "+v2.xPos+","+v2.yPos+" | ");
		//System.out.print("v3: "+v3.xPos+","+v3.yPos+" | ");
		//System.out.print("v4: "+v4.xPos+","+v4.yPos+" | ");
		//System.out.print("v4: "+v5.xPos+","+v5.yPos+" | ");
		//System.out.print("v5: "+v5.xPos+","+v5.yPos+" | ");
		//System.out.print("sum: "+sum.xPos+","+sum.yPos+" | ");
		//System.out.println("");

	}//end iteration through flock
    }
	
    /**
     * Rule 1: Groups the boids together
     * 
     * Returns a vector representing the boids perceived centre of the group (Average position of all boids not including the boid itself).
     * A movement factor is applied to move the boid a percentage of the way towards the center.   
     * 
     * @param	cBoid	The boid which the rule is applied to
     * @return Vector	Grid position moving the boid towards the center of the group
     * 
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
     * Rule 2: Collision Avoidance
     * 
     *  Checks to see if the boid is within a specified distance of other boids by comparing the position coordinates of each. If the flock mate 
     *  is inside the minimum distance,  the vector is updated to move the boid further away by half of the current distance between the two.
     *  
     * 	@param	cBoid	The boid which the rule is applied to
     *  @return	Vector	Grid coordinates of a position that would take the boid away from each flock mate that is too close. 
     *  
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
     * Rule 3: Match flock velocity
     * 
     * Returns a vector representing the boids perceived average velocity of the flock, not including the boid itself. 
     * 
     * @param	cBoid	The boid which the rule is applied to
     *  @return	Vector	Perceived velocity of the flock as a group 
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
     * Encourages the boid to remain in the view frame. 
     * 
     * 	@param	cBoid	The boid which the rule is applied to 
     * 	@return	Vector	A grid position moving the boid towards the view frame.
     */
    private Vector2d bounding(Boid cBoid) {
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
     * Draws the boids towards points of attraction. Acts as a goal for boids helping to provide more predictable group behaviour.
     * 
     * @param cBoid	The boid which the rule is applied to
     * @return	Vector	A grid position moving the boid towards points of attraction
     */
    private Vector2d positionTend(Boid cBoid) {
        Vector2d place = new Vector2d(600,450);	//Sample coordinates in the centre of the screen
    	Vector2d tend = new Vector2d();
		
		tend = new Vector2d(place.subtract(cBoid.getPosition()));
		tend.division(100);		//Movement factor moving the boid a percentage of the distance to the attration point

		return tend;
    }
    /**
     * Paint each boid comprising the flock the canvas.
     * @param g
     */
    public void drawBoids(GraphicsContext g) {
    	for(Boid aBoid : boids) {
    		aBoid.draw(g);
    	}
    }

	
}
