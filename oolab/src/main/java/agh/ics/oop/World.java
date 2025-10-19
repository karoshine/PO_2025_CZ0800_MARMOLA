package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");
        run(args);
        System.out.println("system zakonczyl dzialanie");
    }
    public static void run(String[] args) {
        System.out.println("zwierzak idzie do przodu");
        int n = args.length;
        for (int i = 0; i < n; i++) {
            System.out.print(args[i]);
            if (i < n - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
