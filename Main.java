import java.awt.Rectangle;

import src.*;

public class Main{
    public static void main(String[] args){
				GameScene scene1 = new GameScene(
					new GameComponent(new Rectangle(
						125,
						125,
						250,
						250
						),
						new int[]{0, 0, 0}
					)
				);
        Game game = new Game(new int[]{500,500}, scene1);
    }
}
