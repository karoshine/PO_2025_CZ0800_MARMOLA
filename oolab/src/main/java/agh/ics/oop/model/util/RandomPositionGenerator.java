package agh.ics.oop.model.util;
import agh.ics.oop.model.Vector2d;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

//używam jednej z wersji algorytmu Fishera-Yatesa o złożoności obliczeniowej O(maxWidth x maxHeight)
public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int grassCount;
    private final int positionsCount;
    private final int maxWidth;

    public  RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        this.maxWidth =  maxWidth;
        positionsCount = maxWidth * maxHeight;
        this.grassCount = grassCount;
    }

    @Override
    public Iterator<Vector2d> iterator(){
        return new Iterator<>(){
            int idx = 0;
            final int[] positions = new int[positionsCount];
            final Random random = new Random();
            {
                for (int i = 0; i < positionsCount; i++) {
                    positions[i] = i;
                }
            }

            @Override
            public Vector2d next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                int draw = idx + random.nextInt(positionsCount - idx);
                int toSwap = positions[idx];
                int drawn = positions[draw];
                positions[draw] = toSwap;
                positions[idx] = drawn;
                Vector2d drawnPosition = new Vector2d(drawn % maxWidth, drawn / maxWidth);
                idx++;
                return drawnPosition;

            }

            @Override
            public boolean hasNext() {
                return idx < grassCount;

            }
        };
    }
}