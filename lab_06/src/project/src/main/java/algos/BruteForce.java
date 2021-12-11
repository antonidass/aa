package algos;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteForce {

    public static int getPathCost(ArrayList<Integer> cities, ArrayList<ArrayList<Integer>> matrix) {
        int cost = 0;

        for (int i = 0; i < cities.size(); i++) {
            cost += matrix.get(cities.get(i)).get(cities.get(i + 1));
        }

        return cost;
    }

    public static ArrayList<Integer> getShortestPath(ArrayList<Integer> cities, ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>(1500);

        for (int i = 0; i < 1500; i++) {
            result.add(new ArrayList<>(cities.size()));
        }
        ArrayList<Integer> resArr = new ArrayList<>();

        int minCost;
        int currCost;
        int index = 0;
        int countRoutes = 0;

        cities.remove(0);
        resArr.add(0);
        getRoutes(cities, resArr, result, countRoutes);

        IO.printArray(resArr);

        System.out.println(countRoutes);
//        minCost = getPathCost(result.get(index), matrix);

//
//        for (int i = 1; i < countRoutes; i++) {
//            currCost = getPathCost(result.get(i), matrix);
//
//            if (currCost < minCost) {
//                minCost = currCost;
//                index = i;
//            }
//        }
//

        return result.get(index);
    }

    public static void getRoutes(ArrayList<Integer> cities, ArrayList<Integer> resArr, ArrayList<ArrayList<Integer>> result, Integer count) {
        int elem;
        ArrayList<Integer> temp = new ArrayList<>();

        if (cities.size() == 0) {
            resArr.add(resArr.get(0));
            result.add(count, resArr);
            count++;
            resArr.remove(resArr.size() - 1);
        }

        for (int i = 0; i < cities.size(); i++) {
            elem = cities.get(i);
            resArr.add(elem);

            temp = (ArrayList<Integer>) cities.clone();
            temp.remove(i);
            getRoutes(temp, resArr, result, count);
            resArr.remove(resArr.size() - 1);
        }
    }










}
