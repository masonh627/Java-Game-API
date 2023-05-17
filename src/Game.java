package src;

// Imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.KeyBoard;

/** A custom designed 2D game devolpment API. Proritizing ease of use. 
 * 
 * @author Mason Hedger
 * @version 1.1
*/
public class Game extends JPanel{
    // Class variables
    private ArrayList<GameScene> scenes = new ArrayList<GameScene>();
		private GameScene currentScene;
    private int[] windowSize = {100,100};
    private JFrame window;
    private Color windowBackGroundColor = new Color(255,255,255);
    // Class designed for game use
    // private SettingsLoader settingsLoader = new SettingsLoader();
    // Class to log keystrokes
    KeyBoard keyBoard; 

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

        // Start logging key strokes
        keyBoard = new KeyBoard(window);
        keyBoard.run();

        // Set current scene
        currentScene = scenes.get(0);

        // Boiler plate JFrame setup
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
     * @param scene The level the game will start with
    */
    public Game(GameScene scene){
        scenes.add(scene);
        generateWindow();
    }

    /**Constructs a game with the passed objects
     * 
     * @param scenes The list of scenes the game will be constructed with
    */
    public Game(Collection<? extends GameScene> scenes){
        for(GameScene c : scenes){
            this.scenes.add(c);
        }
        generateWindow();
    }

    /**Constructs a game with the passed objects
     * 
     * @param windowSize Size of the game window
     * @param scenes The list of scenes the game will be constructed with
    */
    public Game(int[] windowSize, Collection<? extends GameScene> scenes){
        this.windowSize = windowSize;
        for(GameScene c : scenes){
            this.scenes.add(c);
        }
        generateWindow();
    }

    /**Constructs a game with the passed objects
     * 
     * @param windowSize Size of the game window
     * @param scenes The scenes the game will be constructed with
    */
    public Game(int[] windowSize, GameScene scene){
        this.windowSize = windowSize;
        scenes.add(scene);
        generateWindow();
    }
    
    // Getters

    // Setters

    /** Adds a new scene to the window
     * 
     * @param scene The new scene to add
     * @return Current Game object
     */
    public Game addScene(GameScene scene){
        scenes.add(scene);
        return this;
    }

    /** Adds new scenes to the window
     * 
     * @param scenes The new scenes to add
     * @return Current Game object
     */
    public Game addScenes(Collection<? extends GameScene> scenes){
        for(GameScene c : scenes){
            this.scenes.add(c);
        }
        return this;
    }

    /** Adds new scenes to the window
     * 
     * @param scenes The new scenes to add
     * @return Current Game object
     */
    public Game addScenes(GameScene... scenes){
        for(GameScene c : scenes){
            this.scenes.add(c);
        }
        return this;
    }

    /** Sets rendering scene to the passed one, If the scene has not been added to list it is automatically added
    * @param scene GameScene you wish to render
    * @return Current Game object
    */
    public Game setScene(GameScene scene){
        // Check if scene exsists
        if(scenes.contains(scene)){
            currentScene = scene;
        }
        else{
            scenes.add(scene);
            currentScene = scene;
        }
        return this;
    }

    /** Sets the scene to the number passed
    * @param ID Index of the scene, 0 - N scenes
    * @return Current Game object
    */
    public Game setScene(int ID){
        if(ID >= 0 && ID < scenes.size()){
            currentScene = scenes.get(ID);
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
        for(GameComponent component : currentScene.getComponents()){
            // Check if were rendering an image or basic shape
            if(component.getSprite() == null){
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

        // Start logging key strokes
        keyBoard = new KeyBoard(window);
        keyBoard.run();

        // Set current scene
        currentScene = scenes.get(0);

        // Boiler plate JFrame setup
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(windowSize[0],windowSize[1]);
        window.add(this);
        window.setVisible(true);
    }

    /** Takes in two components and returns if they collide using AABB detection
     * 
     * @param component1 
     * @param component2
     * @return True if they collide, false otherwise
     */
    public boolean componentsCollide(GameComponent component1, GameComponent component2){
        // Get bounding boxes
        Rectangle bounds1 = component1.getShape().getBounds();
        Rectangle bounds2 = component2.getShape().getBounds();

        // AABB detection
        boolean xCollision = bounds1.x < bounds2.x + bounds2.width && bounds1.x + bounds1.width > bounds2.x;
        boolean yCollision = bounds1.y < bounds2.y + bounds2.height && bounds1.y + bounds2.height > bounds2.y;

        return xCollision && yCollision;
    }

    /** Returns if the given key is being pressed
     * 
     * @param keyEventID KeyEvent int ID number
     * @return True if key is pressed down, false otherwise
     */
    public boolean isKeyPressed(int keyEventID){
        return keyBoard.isKeyPressed(keyEventID);
    }
}