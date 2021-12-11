package algos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IO {

    public static ArrayList<ArrayList<Integer>> getData(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + fileName));
        String line = br.readLine();

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        while (line != null) {
            String[] strArray = line.split(" ");
            ArrayList<Integer> row = new ArrayList<>();

            for (String s : strArray) {
                row.add(Integer.parseInt(s));
            }

            matrix.add(row);
            line = br.readLine();
        }

        br.close();

        return matrix;
    }

    public static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        System.out.println("Matrix: ");
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void printArray(ArrayList<Integer> array) {
//        System.out.println("Array:");

        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + " ");
        }
        System.out.println();
    }
}

