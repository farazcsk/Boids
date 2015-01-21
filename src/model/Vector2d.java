package model;

public class Vector2d {
    public double xPos; //x coordinate
    public double yPos; //y coordinate

    public Vector2d(){
    	this.xPos = 0;
    	this.yPos = 0;
    }

    public Vector2d(Vector2d v){
    	this.xPos = v.xPos;
    	this.yPos = v.yPos;
    }
    
    public Vector2d(double x, double y){
        this.xPos = x;
        this.yPos = y;
    }
    /**
     * Performs addition of two vector.
     * 
     * @param addVector
     * @return A vector holding the product
     */
    public Vector2d add(Vector2d addVector){
    	return new Vector2d(this.xPos += addVector.xPos, this.yPos += addVector.yPos );
    }
    
    /**
     * Add the given integer values
     * 
     * @param x	coordinate
     * @param y	coordinate
     * @return A vector holding the product
     */
    public Vector2d add(double x, double y){
    	return new Vector2d(this.xPos += x, this.yPos += y );
    }
    
    /**
     * subtracts the given integer values
     * 
     * @param x	coordinate
     * @param y	coordinate
     * @return A vector holding the product
     */
    public Vector2d subtract(double x, double y){
    	return new Vector2d(this.xPos += x, this.yPos += y );
    }
    
    /**
     * Performs subtraction of two vectors 
     * @param addVector
     */
    public Vector2d subtract(Vector2d subVector){
    	return new Vector2d(this.xPos = (this.xPos - subVector.xPos), this.yPos = (this.yPos - subVector.yPos));
    }
    
    public Vector2d division(double divider) {
    	return new Vector2d(this.xPos =( xPos / divider), this.yPos =( yPos / divider));
    }
    
    public double absX() {
    	return Math.sqrt(Math.pow(this.xPos, 2));
    }
    
    public double absY() {
    	return Math.sqrt(Math.pow(this.yPos, 2));
    }
    
    public double abs() {
    	return Math.sqrt(Math.pow(this.xPos, 2) + Math.pow(this.yPos, 2));
    }
}

