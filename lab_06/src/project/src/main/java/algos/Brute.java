package algos;

import java.util.ArrayList;

public class Brute {
    private static ArrayList<ArrayList<Integer>> dists;
    private static ArrayList<Integer> routeMin;

    public static void swap(ArrayList<Integer> list, int i, int j) {
        int t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }

    public static void generatePermutations(ArrayList<Integer> arrayList, int leftIndex) {
        if (leftIndex >= arrayList.size()) {
            dists.add((ArrayList<Integer>) arrayList.clone());
            return;
        }

        generatePermutations(arrayList, leftIndex + 1);
        for (int i = leftIndex + 1; i < arrayList.size(); i++) {
            swap(arrayList, leftIndex, i);
            generatePermutations(arrayList, leftIndex + 1);
            swap(arrayList, leftIndex, i);
        }
    }

    public static void bruteForce(ArrayList<ArrayList<Integer>> matrix) {
        dists = new ArrayList<>();
        routeMin = new ArrayList<>();

        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            temp.add(i);
        }
        generatePermutations(temp, 0);

        int minCost = Integer.MAX_VALUE;
        int d;
        for (int i = 0; i < dists.size(); i++) {
            d = matrix.get(dists.get(i).get(0)).get(dists.get(i).get(dists.get(0).size() - 1));
            for (int j = 1; j < dists.get(0).size(); j++) {
                d += matrix.get(dists.get(i).get(j - 1)).get(dists.get(i).get(j));
            }

            if (d < minCost) {
                routeMin = dists.get(i);
                minCost = d;
            }
        }

        System.out.println("brute min route = " + (minCost));
        routeMin.add(routeMin.get(0));
        IO.printArray(routeMin);
    }
}
