package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap{
    private final Map<Vector2d, Grass> grass =  new HashMap<>();

    public GrassField(int grassNumber) {
        super();
        int upperGrassCorner = (int) Math.sqrt(grassNumber * 10) + 1;


        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(upperGrassCorner, upperGrassCorner, grassNumber);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (super.objectAt(position) != null) {
            return super.objectAt(position);
        }
        return grass.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(List.copyOf(grass.values()));
        return elements;
    }

    @Override
    public Boundary getCurrentBounds() {
        List<WorldElement> mapElements = getElements();
        Vector2d lowestPosition = mapElements.getFirst().getPosition();
        Vector2d highestPosition = mapElements.getFirst().getPosition();
        for(WorldElement element : mapElements){
            lowestPosition = lowestPosition.lowerLeft(element.getPosition());
            highestPosition = highestPosition.upperRight(element.getPosition());
        }
        return new Boundary(lowestPosition, highestPosition);
    }
}
