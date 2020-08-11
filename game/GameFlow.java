package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import sprites.Counter;

import java.util.List;


/**
 * the GameFlow class
 * moving from one level to the next level in the game according to the given arguments .
 */


public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyBoard;
    private GUI gui;
    private Counter score;
    private Counter numberOfLives;

    /**
     * Constructor.
     *
     * @param animationRunner :the animation runner.
     * @param keyBoard        : keyboard sensor.
     * @param gui             : the screen to display the game on.
     */

    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyBoard, GUI gui) {
        this.animationRunner = animationRunner;
        this.keyBoard = keyBoard;
        this.gui = gui;
        this.score = new Counter();
        this.numberOfLives = new Counter();
        numberOfLives.increase(7);
    }

    /**
     * the method used to run the levels.
     *
     * @param levels : all the levels according to the arguments.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (int i = 0; i < levels.size(); i++) { // Levels runner
            GameLevel level = new GameLevel(this.animationRunner, this.keyBoard, levels.get(i), score, numberOfLives);
            levels.get(i).initialize();
            level.initialize();
            level.run();
            AnimationRunner runner = level.getRunner();
            EndScreen winningGameEndScreen = new EndScreen(this.keyBoard, level.getScore(), 1);
            EndScreen losingGameEndScreen = new EndScreen(this.keyBoard, level.getScore(), 0);
            int blocksNumber = level.getremaningBlocks();
            while (level.getLives() != 0 && level.getremaningBlocks() != 0) { //
                level.playOneTurn();
            }
            if (blocksNumber == 0 && i == levels.size() - 1) { // winning game message
                runner.run(new KeyPressStoppableAnimation(this.keyBoard, this.keyBoard.SPACE_KEY,
                        winningGameEndScreen));
                gui.close();
                break;
            }
            if (level.getLives() == 0) { // losing game methods.
                runner.run(new KeyPressStoppableAnimation(this.keyBoard, this.keyBoard.SPACE_KEY, losingGameEndScreen));
                gui.close();
                break;
            }

        }
    }
}