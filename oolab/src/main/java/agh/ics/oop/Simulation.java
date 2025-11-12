package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {
    private final List<MoveDirection> moves;
    private final List<T> objectsOnMap = new ArrayList<>();
    private final WorldMap<T, P> map;

    public Simulation(List<T> objects, List<MoveDirection> moves, WorldMap<T, P> map) {
        this.moves = moves;
        this.map = map;
        for (T object : objects) {
            if (map.place(object)) {
                objectsOnMap.add(object);
            }
        }
    }

    public List<T> getObjectsOnMap() {
        return objectsOnMap;
    }

    public void run() {
        if (objectsOnMap.isEmpty()) {
            System.out.println("Brak zwierząt do symulacji — kończę działanie.");
            return;
        }

        for (int i = 0; i < moves.size(); i++) {
            int object_idx = i % objectsOnMap.size();
            T currentObject = objectsOnMap.get(object_idx);
            map.move(currentObject, moves.get(i));

            System.out.println("Animal" + object_idx + ": " + currentObject);
            System.out.println(map);
        }
    }
}
