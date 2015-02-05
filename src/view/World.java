package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Flock;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


/**
 * World class creates the canvas. Which represents the boids environment.
 * @author Shaun
 *
 */
public class World extends Application {
	private Flock flock;
	private static final int width = 1200;
	private static final int height = 800;
	
	public World() {
		this.flock = new Flock();
	}
	
	/**
	 * Updates the position of all boids in flock and displays all elements on screen.
	 */
	public void paint(final GraphicsContext g) {
		new AnimationTimer() {
            @Override
            public void handle(long now) {
            	flock.updateBoidsPostion();
            	g.clearRect(0, 0, width, height);
            	flock.drawBoids(g);
            }
        }.start();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Boids Flocking Algorithm");
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        paint(gc);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
