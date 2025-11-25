package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap{
    private static final Vector2d START = new Vector2d(0, 0);

    public RectangularMap(int width, int height) {
        super(new Vector2d(0, 0), new Vector2d(width-1, height-1));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        return !isOccupied(position) && position.precedes(upperRightCorner) && position.follows(lowerLeftCorner);
    }



}