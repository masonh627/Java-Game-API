package lib;

import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/** Class to keep track of keystrokes
*
* @author Mason H. & Logan J.
* @version May 8th, 2023
*/
public class KeyBoard extends Thread{
	// Arraylist that stores all current keys
	private ArrayList<Integer> keysPressed = new ArrayList<Integer>();
    private JFrame bindedFrame;

	/** Class that keeps track of all key strokes and stores what keys are being pressed
    * @param bind The frame that the KeyBoard object will be binded to 
    */
	public KeyBoard(JFrame bind){
        bindedFrame = bind;
	}

	/** Starts the key logging. Must be called for logging to start
 	*
	*/
	@Override
	public void run(){
		// Adds the functions to the frame that control the logging of keystrokes
		bindedFrame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				// Check if arraylist doesnt already contain this key
				if(!keysPressed.contains(e.getKeyCode())){
					// Add key to array list
					keysPressed.add(e.getKeyCode());
				}
			}

			public void keyReleased(KeyEvent e){
				// We dont need to check if key is in arraylist
				// So we just remove it
				keysPressed.remove((Integer)e.getKeyCode());
			}
		});
	}

	/**
  * @param keyCode The key code you want to know if is pressed
  * @return If the key code given is currently being pressed
 	*/
	public boolean isKeyPressed(int keyCode){
		return keysPressed.contains(keyCode);
	}
}