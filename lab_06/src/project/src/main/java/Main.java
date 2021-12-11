import algos.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Integer>> matrix = IO.getData("data2.txt");
//        IO.printMatrix(matrix);



        Brute.bruteForce(matrix);


        int[] alphas = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] qs = new double[]{0.1, 0.2, 0.25, 0.3, 0.4, 0.5, 0.6, 0.7, 0.75, 0.8, 0.9};
        int[] tmax = new int[]{5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        FileWriter fileWriter = new FileWriter(new File("result.txt"));

        for (int time = 0; time < tmax.length; time++) {
            for (int koef = 0; koef < alphas.length; koef++) {
                for (int isp = 0; isp < qs.length; isp++) {
                    Ant.ant(matrix.size(), matrix, alphas[koef], 10 - alphas[koef], qs[isp], tmax[time], fileWriter);
                }
            }
        }
        fileWriter.close();


        System.out.println("MIN = " + Ant.LMIN);
    }
}