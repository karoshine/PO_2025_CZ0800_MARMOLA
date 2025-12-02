package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap{
    private final Vector2d lowerLeftCorner = new Vector2d(0,0);
    private final Vector2d upperRightCorner;

    public RectangularMap(int width, int height) {
        this.upperRightCorner = new Vector2d(width-1,height-1);
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeftCorner, upperRightCorner);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        return !isOccupied(position) && position.precedes(upperRightCorner) && position.follows(lowerLeftCorner);
    }



}