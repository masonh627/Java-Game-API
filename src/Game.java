package src;
// Imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** A custom designed 2D game devolpment API. Proritizing ease of use. 
 * 
 * @author Mason Hedger
 * @version 1.1
*/
public class Game extends JPanel{
    // Class variables
    private ArrayList<GameComponent> components = new ArrayList<GameComponent>();
    private int[] windowSize = {100,100};
    private JFrame window;
    private Color windowBackGroundColor = new Color(255,255,255);
    // Custom class designed for game use
    //private SettingsLoader settingsLoader = new SettingsLoader();

    // Constructors
    public Game(){
        generateWindow();
    }

    /** Only way to have a custom window label
     * 
     * @param text The text of the window
     */
    public Game(String text){
        window = new JFrame(text);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(windowSize[0],windowSize[1]);
		window.add(this);
		window.setVisible(true);
    } 

    /** Creates a game that has a window size of the dimensions passed
     * 
     * @param windowSize The size of the game window in X, Y pixels
     */
    public Game(int[] windowSize){
        this.windowSize = windowSize;
        generateWindow();
    }

    /**Constructs a game with the passed objects
     * 
     * @param component The list of component the game will be constructed with
    */
    public Game(GameComponent component){
        components.add(component);
        generateWindow();
    }

    /**Constructs a game with the passed objects
     * 
     * @param components The list of components the game will be constructed with
    */
    public Game(Collection<? extends GameComponent> components){
        for(GameComponent c : components){
            this.components.add(c);
        }
        generateWindow();
    }

    /**Constructs a game with the passed objects
     * 
     * @param windowSize Size of the game window
     * @param components The list of components the game will be constructed with
    */
    public Game(int[] windowSize, Collection<? extends GameComponent> components){
        this.windowSize = windowSize;
        for(GameComponent c : components){
            this.components.add(c);
        }
        generateWindow();
    }

    /**Constructs a game with the passed objects
     * 
     * @param windowSize Size of the game window
     * @param component The list of component the game will be constructed with
    */
    public Game(int[] windowSize, GameComponent component){
        this.windowSize = windowSize;
        components.add(component);
        generateWindow();
    }
    
    // Getters

    // Setters

    /** Adds a new component to the window
     * 
     * @param component The new component to add
     * @return Current Game object
     */
    public Game addComponent(GameComponent component){
        components.add(component);
        return this;
    }

    /** Adds new components to the window
     * 
     * @param component The new components to add
     * @return Current Game object
     */
    public Game addComponent(Collection<? extends GameComponent> components){
        for(GameComponent c : components){
            this.components.add(c);
        }
        return this;
    }

    // Overrided method that is called by default by a the JFrame
    @Override
    public void paintComponent(Graphics g){
        // Call a new thread to render the frame
        Thread render = new Thread(new Runnable(){
            public void run(){
                render(g);
            }
        });
        render.run();
    }

    // Method that is ran on a different thread to render frames
    private void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        // Color in background
        g2d.setColor(windowBackGroundColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Save default transformation so we can reset before drawing the next object
        AffineTransform AffineTransform = g2d.getTransform();

        // Loop through all components and draw them to the screen
        System.out.println(components.get(0).getSprite());
        for(GameComponent component : components){
            // Check if were rendering an image or basic shape
            if(component.getSprite() == null){
                System.out.println("fuck");
                g2d.setColor(component.getColor());

                // Draw shape on screen
                g2d.rotate(component.getRotation());
                g2d.fill(component.getShape());
            }

            g2d.setTransform(AffineTransform);
        }

        g2d.dispose();
    }

    // Method that generates the window
    private void generateWindow(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(windowSize[0],windowSize[1]);
		window.add(this);
		window.setVisible(true);
    }
}