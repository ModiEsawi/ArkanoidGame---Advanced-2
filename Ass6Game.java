import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.LevelInformation;
import levels.FirstLevel;
import levels.FourthLevel;
import levels.SecondLevel;
import levels.ThirdLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * arkanoid game.
 * mohamad elesawi.
 */
public class Ass6Game {

    /**
     * main method.
     *
     * @param args : levels number from 1 to 4 ,ignore other wise.
     */

    public static void main(String[] args) {
        int screenWidth = 800;
        int screenHeight = 800;

        GUI gui = new GUI("Arkanoid Game", screenWidth, screenHeight);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow game = new GameFlow(animationRunner, keyboard, gui);
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0) { //default levels order.
            levels.add(new FirstLevel(screenHeight, screenWidth, 1));
            levels.add(new SecondLevel(screenHeight, screenWidth, 2));
            levels.add(new ThirdLevel(screenHeight, screenWidth, 3));
            levels.add(new FourthLevel(screenHeight, screenWidth, 4));
        } else { // order the levels according to the arguments.
            for (String arg : args) {
                switch (arg) {
                    case "1":
                        levels.add(new FirstLevel(screenHeight, screenWidth, 1));
                        break;
                    case "2":
                        levels.add(new SecondLevel(screenHeight, screenWidth, 2));
                        break;
                    case "3":
                        levels.add(new ThirdLevel(screenHeight, screenWidth, 3));
                        break;
                    case "4":
                        levels.add(new FourthLevel(screenHeight, screenWidth, 4));
                        break;
                    default:
                       break;
                }
            }
        }
        game.runLevels(levels);
    }
}