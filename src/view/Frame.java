package view;

import javax.swing.JFrame;

import model.World;

public class Frame {
	public static boolean active;
	public static int width;
	public static  int height;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		active = true;
		width = 1000;
		height = 500;
		//set up the frame
		JFrame frame = new JFrame("Paint"); //Create new window and initialise with a name
		frame.setSize(width,height);	//set the window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ensures the program shuts down when the user tries to exit
		frame.setResizable( false ); // prevents the window from being resized
		
		World world = new World();
		frame.add(world); // add everything to the frame
		
		frame.setVisible(true); //make the frame and its contents visible.
		
		while(active) {
			for(int i = 0; i < 500; i++) {
				
	            try {
	                Thread.sleep(100);
	            }catch(InterruptedException ex){
	                Thread.currentThread().interrupt();
	            }				
				if(i >= 5) {
					active = false;
					break;
				}
			
			world.repaint();
			}
		}
	
	}

}
