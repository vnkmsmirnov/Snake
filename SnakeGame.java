import java.awt.*;
import javax.swing.*;





public class SnakeGame extends JFrame {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private static final int GOAL = 28;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        Snake s1 = new Snake(WIDTH/2, HEIGHT/2);
        snake = s1;
        createNewApple();
        isGameStopped = false;
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }

    private void drawScene() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                setCellValueEx(i, j, Color.DARKSEAGREEN, "");
            }
        }

        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int x) {
        snake.move(apple);

        if (!apple.isAlive) {
            createNewApple();
        }

        if (!snake.isAlive) {
            gameOver();
        }

        if (snake.getLength() > GOAL) {
            win();
        }

        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {

        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        }

        if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        }

        if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        }

        if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        }

        if (key == Key.SPACE && isGameStopped) {
            createGame();
        }

    }

    private void createNewApple() {
        apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        while(snake.checkCollision(apple)) {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        }
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "Game Over", Color.RED, 50);
    }



    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "You Win", Color.GREEN, 50);
    }
}
