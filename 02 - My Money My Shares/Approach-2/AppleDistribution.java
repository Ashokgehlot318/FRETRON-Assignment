import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

public class AppleDistribution {

    static class Distribution {
        List<Integer> ram = new ArrayList<>();
        List<Integer> sham = new ArrayList<>();
        List<Integer> rahim = new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Integer> apples = List.of(400, 100, 400, 300, 200, 300, 100, 200);

        int ramAmount = 50; // Ram paid 50%
        int shamAmount = 30; // Sham paid 30%
        int rahimAmount = 20; // Rahim paid 20%

        int totalWeight = apples.stream().mapToInt(Integer::intValue).sum();
        
        // Calculate the target weight for each individual
        int ramTarget = totalWeight * ramAmount / 100;
        int shamTarget = totalWeight * shamAmount / 100;
        int rahimTarget = totalWeight * rahimAmount / 100;

        // Convert the list to an array for easier manipulation
        Integer[] appleWeights = apples.toArray(new Integer[0]);
        
        // Sort apples by weight in descending order for a greedy approach
        Arrays.sort(appleWeights, Comparator.reverseOrder());

        // Initialize distributions
        Distribution distribution = new Distribution();
        int ramWeight = 0, shamWeight = 0, rahimWeight = 0;

        for (int weight : appleWeights) {
            int ramDifference = Math.abs(ramTarget - (ramWeight + weight));
            int shamDifference = Math.abs(shamTarget - (shamWeight + weight));
            int rahimDifference = Math.abs(rahimTarget - (rahimWeight + weight));

            if (ramDifference <= shamDifference && ramDifference <= rahimDifference) {
                distribution.ram.add(weight);
                ramWeight += weight;
            } else if (shamDifference <= rahimDifference) {
                distribution.sham.add(weight);
                shamWeight += weight;
            } else {
                distribution.rahim.add(weight);
                rahimWeight += weight;
            }
        }

        // Output the results
        System.out.println("Distribution Result:");
        System.out.print("Ram: ");
        distribution.ram.forEach(w -> System.out.print(w + " "));
        System.out.println();
        
        System.out.print("Sham: ");
        distribution.sham.forEach(w -> System.out.print(w + " "));
        System.out.println();
        
        System.out.print("Rahim: ");
        distribution.rahim.forEach(w -> System.out.print(w + " "));
        System.out.println();
    }
}
