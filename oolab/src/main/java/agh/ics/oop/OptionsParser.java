package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
public class OptionsParser {
    public static MoveDirection[] parserChange(String[] args) {
        int cnt = 0;
        for(String arg : args) {
            if (arg.equals("f") || arg.equals("r") || arg.equals("l") || arg.equals("b")) {

                cnt += 1;
            }
        }
        MoveDirection[] directions = new MoveDirection[cnt];
        int n = 0;
        for (int i = 0; i < args.length; i++) {
            if (parseSingle(args[i]) != null) {
                directions[n] = parseSingle(args[i]);
                n += 1;
            }
        }

        return directions;
    }
    public static MoveDirection parseSingle(String arg){
        return switch (arg){
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "l" -> MoveDirection.LEFT;
            case "r" -> MoveDirection.RIGHT;
            default -> null;
        };
    }
}
