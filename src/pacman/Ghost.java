package pacman;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class Ghost extends Rectangle {

    String direction;
    Maze maze;
    AnimationTimer animation;

    public Ghost(double x, double y, Color color, Maze maze) {
        this.setX(x);
        this.setY(y);
        this.maze = maze;
        this.setHeight(50);
        this.setWidth(50);
        this.setFill(color);
        this.direction = "down";
        this.createAnimation();
    }

    private void getRandomDirection(String exclude) {
        String[] directions = {"left", "right", "up", "down"};
        int rnd = new Random().nextInt(directions.length);
        while (directions[rnd].equals(exclude)) {
            rnd = new Random().nextInt(directions.length);
        }
        this.direction = directions[rnd];
    }

    private boolean getRandomBoolean() {
        Random rand = new Random();
        return rand.nextBoolean();
    }

    /**
     * Gets the animation for the ghost
     * @return
     */
    public AnimationTimer getAnimation() {
        return animation;
    }

    /**
     * Creates an animation of the ghost
     */
    public void createAnimation() {
        double step = 5;

        this.animation = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double leftEdge = getX();
                double topEdge = getY();
                double rightEdge = getX() + getWidth();
                double bottomEdge = getY() + getHeight();
                double padding = 12;
                switch (direction) {
                    case "left":
                        if (!maze.isTouching(leftEdge, topEdge, padding)) {
                            setX(leftEdge - step);
                        } else {
                            while (maze.isTouching(getX(), getY(), padding)) {
                                setX(getX() + 1);
                            }
                            direction = "down";
                        }
                        if (getRandomBoolean() && !maze.hasObstacle(getX() + getWidth() + 12, getY() - 15)) {
                            direction = "up";
                        }
//                        if (getRandomBoolean() && !maze.hasObstacle(getX() + getWidth() + 12, getY() + 15)) {
//                            direction = "down";
//                        }
                        break;
                    case "right":
                        if (!maze.isTouching(rightEdge, topEdge, padding)) {
                            setX(leftEdge + step);
                        } else {
                            while (maze.isTouching(getX() + getWidth(), getY(), padding)) {
                                setX(getX() - 1);
                            }
                            direction = "up";
                        }
                        break;
                    case "up":
                        if (!maze.isTouching(leftEdge, topEdge, padding)) {
                            setY(topEdge - step);
                        } else {
                            while (maze.isTouching(getX(), getY(), padding)) {
                                setY(getY() + 1);
                            }
                            direction = "left";
                        }
                        break;
                    case "down":
                        if (!maze.isTouching(leftEdge, bottomEdge, padding)) {
                            setY(topEdge + step);
                        } else {
                            while (maze.isTouching(getX(), getY() + getHeight(), padding)) {
                                setY(getY() - 1);
                            }
                            direction = "right";
                        }
                        break;
                }
            }
        };
    }
}
