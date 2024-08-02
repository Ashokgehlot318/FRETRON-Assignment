import java.util.ArrayList;
import java.util.List;

public class AppleDistribution {

    static class Distribution {
        List<Integer> ram = new ArrayList<>();
        List<Integer> sham = new ArrayList<>();
        List<Integer> rahim = new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Integer> apples = List.of(400, 100, 400, 300, 200, 300, 100, 200);

        int ramAmount = 50;
        int shamAmount = 30;
        int rahimAmount = 20;
        int totalWeight = apples.stream().mapToInt(Integer::intValue).sum();

        Distribution bestDistribution = findBestDistribution(apples, ramAmount, shamAmount, rahimAmount, totalWeight);
        
        System.out.println("Distribution Result:");
        System.out.print("Ram: ");
        bestDistribution.ram.forEach(w -> System.out.print(w + " "));
        System.out.println();
        
        System.out.print("Sham: ");
        bestDistribution.sham.forEach(w -> System.out.print(w + " "));
        System.out.println();
        
        System.out.print("Rahim: ");
        bestDistribution.rahim.forEach(w -> System.out.print(w + " "));
        System.out.println();
    }

    private static Distribution findBestDistribution(List<Integer> apples, int ramAmount, int shamAmount, int rahimAmount, int totalWeight) {
        Distribution bestDistribution = new Distribution();
        int minDifference = Integer.MAX_VALUE;
        
        List<List<Integer>> allDistributions = generateAllDistributions(apples);

        for (List<Integer> distribution : allDistributions) {
            Distribution currentDistribution = new Distribution();
            List<Integer> ramApples = new ArrayList<>();
            List<Integer> shamApples = new ArrayList<>();
            List<Integer> rahimApples = new ArrayList<>();
            
            for (int i = 0; i < distribution.size(); i++) {
                if (i % 3 == 0) ramApples.add(distribution.get(i));
                else if (i % 3 == 1) shamApples.add(distribution.get(i));
                else rahimApples.add(distribution.get(i));
            }
            
            int ramWeight = ramApples.stream().mapToInt(Integer::intValue).sum();
            int shamWeight = shamApples.stream().mapToInt(Integer::intValue).sum();
            int rahimWeight = rahimApples.stream().mapToInt(Integer::intValue).sum();

            int ramProportion = ramWeight * 100 / totalWeight;
            int shamProportion = shamWeight * 100 / totalWeight;
            int rahimProportion = rahimWeight * 100 / totalWeight;

            int difference = Math.abs(ramProportion - ramAmount) + Math.abs(shamProportion - shamAmount) + Math.abs(rahimProportion - rahimAmount);

            if (difference < minDifference) {
                minDifference = difference;
                bestDistribution.ram = ramApples;
                bestDistribution.sham = shamApples;
                bestDistribution.rahim = rahimApples;
            }
        }
        
        return bestDistribution;
    }

    private static List<List<Integer>> generateAllDistributions(List<Integer> apples) {
        List<List<Integer>> allDistributions = new ArrayList<>();
        generateDistributions(apples, new ArrayList<>(), allDistributions);
        return allDistributions;
    }

    private static void generateDistributions(List<Integer> apples, List<Integer> current, List<List<Integer>> allDistributions) {
        if (apples.isEmpty()) {
            allDistributions.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < apples.size(); i++) {
            List<Integer> next = new ArrayList<>(current);
            next.add(apples.get(i));
            List<Integer> remaining = new ArrayList<>(apples);
            remaining.remove(i);
            generateDistributions(remaining, next, allDistributions);
        }
    }
}
