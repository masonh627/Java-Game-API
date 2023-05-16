import java.awt.Rectangle;

import src.*;
public class Main{
    public static void main(String[] args){
        GameComponent gameComponent = new GameComponent(
            new Rectangle(125, 125, 250, 250),
            new int[]{0,0,0}
        );
        Game game = new Game(new int[]{500,500,}, gameComponent);   
    }
}
