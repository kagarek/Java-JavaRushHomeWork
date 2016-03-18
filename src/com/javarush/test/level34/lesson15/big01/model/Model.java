package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;
import java.util.logging.Level;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class Model
{
    public static final int FIELD_SELL_SIZE = 20;
    EventListener eventListener;
    GameObjects gameObjects;
    int currentLevel = 1;
    LevelLoader levelLoader = new LevelLoader(Paths.get("../res/levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        gameObjects = new GameObjects(
                levelLoader.getLevel(currentLevel).getWalls(),
                levelLoader.getLevel(currentLevel).getBoxes(),
                levelLoader.getLevel(currentLevel).getHomes(),
                levelLoader.getLevel(currentLevel).getPlayer()
                );
        return gameObjects;
    }
    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player,direction)) return;
        if (checkBoxCollision(direction)) return;
        switch (direction) {
            case LEFT: player.move(-FIELD_SELL_SIZE,0); break;
            case RIGHT: player.move(FIELD_SELL_SIZE,0); break;
            case UP: player.move(0,-FIELD_SELL_SIZE); break;
            case DOWN: player.move(0,FIELD_SELL_SIZE); break;
            default:break;
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (CollisionObject g : gameObjects.getWalls())
            if (gameObject.isCollision(g,direction))
                return true;
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {

        /*
15.2.	boolean checkBoxCollision(Direction direction). Этот метод проверяет
столкновение с ящиками. Метод должен:

15.2.1.	Вернуть true, если игрок не может быть сдвинут в направлении direction (там
находится: или ящик, за которым стена; или ящик за которым еще один ящик).

15.2.2.	Вернуть false, если игрок может быть сдвинут в направлении direction (там
находится: или свободная ячейка; или дом; или ящик, за которым свободная
ячейка или дом). При это, если на пути есть ящик, который может быть сдвинут, то
необходимо переместить этот ящик на новые координаты. Обрати внимание, что
все объекты перемещаются на фиксированное значение FIELD_SELL_SIZE, не
зависящее от размеров объекта, которые используются для его отрисовки.
Подсказка: для определения столкновений используй методы isCollision() игровых
объектов и метод checkWallCollision() модели.
         */
        return false;
    }

    public void checkCompletion() {
        Set<Home> homes = gameObjects.getHomes();
        Set<Box> boxes = gameObjects.getBoxes();
        int properPositions = 0;

        for (Home h : homes)
            for (Box b : boxes)
                if (h.getX() == b.getX() && h.getY() == b.getY())
                    properPositions++;

        if (properPositions == homes.size() && properPositions == boxes.size())
            eventListener.levelCompleted(currentLevel);
    }
}