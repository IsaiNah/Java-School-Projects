package display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;// Jframe object - Frame
	private Canvas canvas;// Canvas object - All graphics here

	private String title;
	private int width, height;// Pixels
	
	public Display(String title, int width, int height)
	{
		//Setting up display
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();//Calling createDisplay()
	}
	
	private void createDisplay()
	{
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Will close the app
		//Optional
		frame.setResizable(false);//Is this resizable by the user? No
		frame.setLocationRelativeTo(null);//Appear at the center of screen
		frame.setVisible(true);//Invisible by default, must be set to true
	
		//More can be set with frame. 
		
		
		//Canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);//IMPORTANT for jFrame focus
		
		frame.add(canvas);
		frame.pack();
	}
	
	
	public Canvas getCanvas()
	{
		return canvas;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	
}//End of class
