package src;

// Imports
import java.util.ArrayList;
import java.util.Collection;

/** Class that is used to control levels
 * 
 * @author Mason Hedger
 * @version 1.0
 */
public class GameScene{
	// Class variables
	private ArrayList<GameComponent> components = new ArrayList<GameComponent>();

	// Constructors
	/**Builds empty scene*/
	public GameScene(){
		
	}

	/** Builds a scene with all the passed components
  *
	* @param components Compononents to build with
  */
	public GameScene(Collection<GameComponent> components){
		for(GameComponent c : components){
			this.components.add(c);
		}
	}

	/** Builds a scene with all the passed components
  *
	* @param components Compononents to build with
  */
	public GameScene(GameComponent... components){
		for(GameComponent c : components){
			this.components.add(c);
		}
	}

	// Getters
	/** Returns all current components in scene
  * @return ArrayLits contaning components
  */
	public ArrayList<GameComponent> getComponents(){
		return components;
	}

	// Setters
	/** Adds a new component to scene
 	* @param component New Component to add
	* @return Current GameScene object
  */
	public GameScene addComponent(GameComponent component){
		components.add(component);
		return this;
	}

	/** Adds all components to scene
  * @param componenets Collection of all components to add
	* @return Current GameScene object
 	*/
	public GameScene addComponents(Collection<GameComponent> components){
		for(GameComponent c : components){
			this.components.add(c);
		}
		return this;
	}

	/** Adds all components to scene
  * @param componenets Collection of all components to add
	* @return Current GameScene object
 	*/
	public GameScene addComponents(GameComponent... components){
		for(GameComponent c : components){
			this.components.add(c);
		}
		return this;
	}
}