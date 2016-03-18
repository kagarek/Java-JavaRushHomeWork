package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by igormakarychev on 3/10/16.
 */
public abstract class CollisionObject extends GameObject
{

    public CollisionObject(int x, int y) {
        super(x,y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){

        if (direction == Direction.LEFT){
            return (this.getX() - Model.FIELD_SELL_SIZE == gameObject.getX() && this.getY() == gameObject.getY());
        } else if (direction == Direction.RIGHT) {
            return (this.getX() + Model.FIELD_SELL_SIZE == gameObject.getX() && this.getY() == gameObject.getY());
        } else if (direction == Direction.UP) {
            return (this.getX() == gameObject.getX() && this.getY() - Model.FIELD_SELL_SIZE == gameObject.getY());
        } else if (direction == Direction.DOWN) {
            return (this.getX() == gameObject.getX() && this.getY() + Model.FIELD_SELL_SIZE == gameObject.getY());
        } else return false;

    }
}
