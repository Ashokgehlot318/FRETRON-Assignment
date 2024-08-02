import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class OptimizedSpecializedCastle {

    static List<List<Coordinate>> allPaths = new ArrayList<>();
    static Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Coordinate> soldiers = new ArrayList<>();
        int soldierCount = 1;

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

        // Initialize the path with the castle coordinate and perform depth-first search
        List<Coordinate> currentPath = new ArrayList<>();
        currentPath.add(castle);
        dfs(soldiers, currentPath);

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

    // Depth-first search to find all unique paths for the specialized castle
    public static void dfs(List<Coordinate> soldiers, List<Coordinate> currentPath) {
        // Generate a unique string representation of the current path
        String pathKey = currentPath.toString();
        
        // Check if the path has been visited, and if so, return to avoid redundant calculations
        if (visited.contains(pathKey)) {
            return;
        }
        
        // Mark the current path as visited
        visited.add(pathKey);
        
        // If there are no more soldiers, add the currentPath as a unique path
        if (soldiers.isEmpty()) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        // Retrieve the last coordinate from the current path
        Coordinate lastCoord = currentPath.get(currentPath.size() - 1);
        int lastX = lastCoord.x;
        int lastY = lastCoord.y;

        // Iterate through the remaining soldiers to find valid next moves
        for (Coordinate soldier : soldiers) {
            int soldierX = soldier.x;
            int soldierY = soldier.y;

            // Conditions to determine valid soldier moves according to the specialized castle's rules
            if ((lastX == soldierX && lastY < soldierY) || (lastX < soldierX)) {
                List<Coordinate> newSoldiers = new ArrayList<>(soldiers);
                newSoldiers.remove(soldier);

                List<Coordinate> newPath = new ArrayList<>(currentPath);
                newPath.add(soldier);

                // Recursively explore the new path
                dfs(newSoldiers, newPath);
            }
        }
    }
}
