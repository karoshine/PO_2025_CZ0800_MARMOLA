package agh.ics.oop.model;

import agh.ics.oop.World;

public class Animal implements WorldElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);


    public Animal() {
    }

    public Animal(Vector2d initialPosition) {
        this.position = initialPosition;
    }

    @Override
    public String toString() {
        return orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());
                if (validator.canMoveTo(newPosition)) {
                    position = newPosition;
                }
            }
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (validator.canMoveTo(newPosition)) {
                    position = newPosition;
                }
            }

        }
    }

    /*public boolean isWithinBounds(Vector2d pos) {
        Vector2d lowerLeft = World.MIN_MAP;
        Vector2d upperRight = World.MAX_MAP;
        return pos.follows(lowerLeft) && pos.precedes(upperRight);
    }*/

}
