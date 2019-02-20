import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Snake {

    private List<GameObject> snakeParts = new ArrayList<GameObject>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";

    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));

    }

    public void setDirection(Direction direction) {
        if ((direction == Direction.UP) && (this.direction != Direction.DOWN)) {
            this.direction = Direction.UP;
        }

        if ((direction == Direction.RIGHT) && (this.direction != Direction.LEFT)) {
            this.direction = Direction.RIGHT;
        }

        if ((direction == Direction.DOWN) && (this.direction != Direction.UP)) {
            this.direction = Direction.DOWN;
        }

        if ((direction == Direction.LEFT) && (this.direction != Direction.RIGHT)) {
            this.direction = Direction.LEFT;
        }
    }



    public void draw(Game game) {
        if (!isAlive) {
            for (int i = 0; i < snakeParts.size(); i++) {
                if (i == 0) {
                    GameObject go1 = snakeParts.get(i);
                    game.setCellValueEx(go1.x, go1.y, Color.NONE, HEAD_SIGN,
                            Color.RED, 75);
                } else {
                    GameObject go2 = snakeParts.get(i);
                    game.setCellValueEx(go2.x, go2.y, Color.NONE, BODY_SIGN,
                            Color.RED, 75);
                }
            }
        } else {
            for (int i = 0; i < snakeParts.size(); i++) {
                if (i == 0) {
                    GameObject go1 = snakeParts.get(i);
                    game.setCellValueEx(go1.x, go1.y, Color.NONE, HEAD_SIGN,
                            Color.BLACK, 75);
                } else {
                    GameObject go2 = snakeParts.get(i);
                    game.setCellValueEx(go2.x, go2.y, Color.NONE, BODY_SIGN,
                            Color.BLACK, 75);
                }
            }
        }
    }



    public void move(Apple apple){
        GameObject check = createNewHead();

        if (checkCollision(check)){
            isAlive = false;
        } else {
            if (check.x >= 15 || check.y >= 15 || check.x < 0 || check.y < 0) {
                isAlive = false;
            } else if (check.x == apple.x && check.y == apple.y) {
                apple.isAlive = false;
                snakeParts.add(0, check);
            } else {
                snakeParts.add(0, check);
                removeTail();
            }
        }
    }



    public GameObject createNewHead() {
        GameObject head = snakeParts.get(0);

        if (direction == Direction.LEFT) {
            head = new GameObject(head.x - 1, head.y);
        }

        if (direction == Direction.DOWN) {
            head =  new GameObject(head.x, head.y + 1);
        }

        if (direction == Direction.RIGHT) {
            head = new GameObject(head.x + 1, head.y);
        }

        if (direction == Direction.UP) {
            head = new GameObject(head.x, head.y - 1);
        }

        return head;
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {
        for (int i = 0; i < snakeParts.size(); i++) {
            if ((gameObject.x == snakeParts.get(i).x) &&
                    (gameObject.y == snakeParts.get(i).y)) {
                return true;
            }
        }

        return false;
    }



    public int getLength() {
        return snakeParts.size();
    }

}