package src;
// Imports
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Shape;

/** Class that can be added to Game and rendered
 * 
 * @author Mason Hedger
 * @version 1.1
 */
public class GameComponent {
    // Graphical variables
    private Shape shape;
    private int rotation = 0;
    private Color color;
    private BufferedImage sprite;

    // Constructors
    public GameComponent(){}

    /** Constructs a component with the passed paramaters
     * 
     * @param shape Component's shape
     */
    public GameComponent(Shape shape){
        this.shape = shape;
    }

    /** Constructs a component with the passed paramaters
     * 
     * @param shape Component's shape
     * @param color Color of components
     */
    public GameComponent(Shape shape, Color color){
        this.shape = shape;
        this.color = color;
    }

     /** Constructs a component with the passed paramaters
     * 
     * @param shape Component's shape
     * @param color Color of components in RGB
     */
    public GameComponent(Shape shape, int[] color){
        this.shape = shape;
        this.color = new Color(color[0], color[1], color[2]);
    }
    
    // Getters
    /** Gets component's shape
     * 
     * @return Current component's shape
    */
    public Shape getShape(){
        return shape;
    }

    /** Gets component rotation
     * 
     * @return Current component rotation in degrees
    */
    public int getRotation(){
        return rotation;
    }

    /** Gets component color
     * 
     * @return Current component color
    */
    public Color getColor(){
        return color;
    }

    /** Gets component sprite
     *
     * @return BufferedImage representing sprite
     */
    public BufferedImage getSprite(){
        return sprite;
    }

    // Setters

    /** Sets component position
     * 
     * @param shape New shape for the component
     * @return Current GameComponent
     */
    public GameComponent setShape(Shape shape){
        this.shape = shape;
        return this;
    }

    /** Sets component's rotation to degree amount
     * 
     * @param degree Rotation degree
     * @return Current GameComponent
     */
    public GameComponent setRotation(int degree){
        this.rotation = degree;
        return this;
    }

    /** Sets component color
     * 
     * @param color Color object to set to
     * @return Current GameComponent
     */
    public GameComponent setColor(Color color){
        this.color = color;
        return this;
    
    
    }

    /** Sets component sprite
     * 
     * @param sprite BufferedImage of sprite
     * @return Current GameComponent
     */
    public GameComponent setSprite(BufferedImage sprite){
        this.sprite = sprite;
        return this;
    }
}
