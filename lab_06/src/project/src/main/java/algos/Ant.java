package algos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Ant {
    public static int l_min = Integer.MAX_VALUE;
    public static int LMIN = Integer.MAX_VALUE;
    public static ArrayList<Integer> routeMin = new ArrayList<>();

    public static ArrayList<Integer> newArrayWithoutLast(ArrayList<Integer> to, int last) {
        ArrayList<Integer> cur = new ArrayList<>();
        for (int i = 0; i < to.size(); i++) {
            if (to.get(i) != last) {
                cur.add(to.get(i));
            }
        }

        return cur;
    }


    public static int getRoute(ArrayList<Integer> all, int start, ArrayList<Integer> route, Integer len, ArrayList<ArrayList<Integer>> matrix, ArrayList<ArrayList<Double>> tao, ArrayList<ArrayList<Double>> attraction, int alpha, int beta) {
        route.clear();
        route.add(start);

        ArrayList<Integer> to = newArrayWithoutLast(all, start);
        int n_1 = tao.size() - 2;
        int from;
        double coin, sum;
        boolean flag;

        for (int i = 0; i < n_1; i++) {
            sum = 0;
            flag = true;
            from = route.get(i);
            ArrayList<Double> p = getProbability(from, to, tao, attraction, alpha, beta);
            coin = Double.valueOf((new Random().nextInt() % 10000)) / 10000;
            for (int j = 0; j < p.size() && flag; j++) {
                sum += p.get(j);
                if (coin < sum) {
                    route.add(to.get(j));
                    len += matrix.get(from).get(to.get(j));
                    to = newArrayWithoutLast(to, to.get(j));
                    flag = false;
                }
            }
        }

        len += matrix.get(route.get(route.size() - 1)).get(to.get(0));
        route.add(to.get(0));
        len += matrix.get(route.get(route.size() - 1)).get(route.get(0));
        route.add(route.get(0));

        return len;
    }


    public static ArrayList<Double> getProbability(int from, ArrayList<Integer> to, ArrayList<ArrayList<Double>> tao, ArrayList<ArrayList<Double>> attraction, int alpha, int beta) {
        double znam = 0.0, chisl = 0.0;
        int n = to.size();
        ArrayList<Double> result = new ArrayList<>(n);
        for (int i =0; i < n; i++) {
            result.add(0.0);
        }

        for (int i = 0; i < n; i++) {
            znam += Math.pow(tao.get(from).get(to.get(i)), alpha) * Math.pow(attraction.get(from).get(to.get(i)), beta);
        }
        for (int j = 0; j < n; j++) {
            chisl = Math.pow(tao.get(from).get(to.get(j)), alpha) * Math.pow(attraction.get(from).get(to.get(j)), beta);
            result.set(j, chisl / znam);
        }

        return result;
    }

    public static boolean inRoute(int a, int b, ArrayList<Integer> route) {
        boolean res = false;
        int m = route.size() - 1;
        for (int i = 0; i < m; i++) {
            if (a == route.get(i) && b == route.get(i + 1)) {
                res = true;
            }
        }

        return res;
    }


    public static void ant(int size, ArrayList<ArrayList<Integer>> matrix, int alpha, int beta, double q, int timeMax, FileWriter fileWriter) throws IOException {
        l_min = Integer.MAX_VALUE;
        routeMin.clear();

        double taoMin, taoStart, Q;
        ArrayList<Integer> all = new ArrayList<>(size);
        Q = 350.0;
        taoMin = 0.001;
        taoStart = 0.5;

        ArrayList<ArrayList<Integer>> routes = new ArrayList<>(size);
        ArrayList<Integer> lens = new ArrayList<>(size);

        ArrayList<ArrayList<Double>> attraction = new ArrayList<>(size);
        ArrayList<ArrayList<Double>> tao = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            attraction.add(new ArrayList<>(size));
            tao.add(new ArrayList<>(size));
            routes.add(new ArrayList<>(size));
            lens.add(0);
            all.add(0);

            for (int j = 0; j < size;j ++) {
                attraction.get(i).add(0.0);
                tao.get(i).add(0.0);
                routes.get(i).add(0);
            }
        }

        for (int i = 0; i < size; i++) {
            lens.set(i, 0);
            all.set(i, i);

            for (int j = 0; j < size; j++) {
                if (i != j) {
                    attraction.get(i).set(j, 1.0 / matrix.get(i).get(j));
                    tao.get(i).set(j, taoStart);
                }
            }
        }


        for (int time = 0; time < timeMax; time++) {
            for (int k = 0; k < size; k++) {
                int len = getRoute(all, k, routes.get(k), lens.get(k), matrix, tao, attraction, alpha, beta);
                lens.set(k, len);
                if (lens.get(k) < l_min) {
                    l_min = lens.get(k);
                    routeMin = routes.get(k);
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    double sum = 0.0;
                    for (int m = 0; m < size; m++) {
                        if (inRoute(i, j, routes.get(m))) {
                            sum += Q / lens.get(m);
                        }
                    }

                    tao.get(i).set(j, tao.get(i).get(j) * (1 - q) + sum);
                    if (tao.get(i).get(j) < taoMin) {
                        tao.get(i).set(j, taoMin);
                    }
                }
            }
        }


        if (l_min < LMIN) {
            LMIN = l_min;
        }

        fileWriter.write(timeMax + " " + alpha + " " + beta + " " + q + " " + l_min + " ");

        for (int i = 0; i < routeMin.size(); i++) {
            fileWriter.write(routeMin.get(i) + " ");
        }
        fileWriter.write("\n");
    }
}
