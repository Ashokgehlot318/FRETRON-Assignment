import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }   
}

public class SpecializedCastle {

    static List<List<Coordinate>> allPaths = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Coordinate> soldiers = new ArrayList<>();
        int soldierCount = 1;

        // Input the number of soldiers on the board
        System.out.print("Enter the number of soldiers on the board: ");
        int totalSoldiers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Input the coordinates for each soldier
        for (int i = 0; i < totalSoldiers; i++) {
            System.out.print("Enter coordinates for soldier " + soldierCount + ": ");
            String[] coordinates = scanner.nextLine().split(",");
            soldiers.add(new Coordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
            soldierCount++;
        }

        // Input the coordinates for the specialized castle
        System.out.print("Enter the coordinates for your specialized castle: ");
        String[] castleCoordinates = scanner.nextLine().split(",");
        Coordinate castle = new Coordinate(Integer.parseInt(castleCoordinates[0]), Integer.parseInt(castleCoordinates[1]));

        List<Coordinate> currentPath = new ArrayList<>();
        currentPath.add(castle);
        bruteForceSearch(soldiers, currentPath);

        // Output the number of unique paths for the specialized castle
        System.out.println("Thanks. There are " + allPaths.size() + " unique paths for your specialized castle.");

        // Output the unique paths' coordinates
        int pathNumber = 1;
        for (List<Coordinate> path : allPaths) {
            System.out.println("Path " + pathNumber + ":");
            for (Coordinate coord : path) {
                System.out.println("(" + coord.x + "," + coord.y + ")");
            }
            pathNumber++;
        }
    }

    // Brute-force search to find all unique paths for the specialized castle
    public static void bruteForceSearch(List<Coordinate> soldiers, List<Coordinate> currentPath) {
        if (soldiers.isEmpty()) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        Coordinate lastCoord = currentPath.get(currentPath.size() - 1);
        int lastX = lastCoord.x;
        int lastY = lastCoord.y;

        for (Coordinate soldier : soldiers) {
            int soldierX = soldier.x;
            int soldierY = soldier.y;

            // Determine the conditions for the specialized castle's movement
            if ((lastX == soldierX && lastY < soldierY) || (lastX < soldierX)) {
                List<Coordinate> newSoldiers = new ArrayList<>(soldiers);
                newSoldiers.remove(soldier);

                List<Coordinate> newPath = new ArrayList<>(currentPath);
                newPath.add(soldier);

                // Recur with the new set of soldiers and the updated path
                bruteForceSearch(newSoldiers, newPath);
            }
        }
    }
}
