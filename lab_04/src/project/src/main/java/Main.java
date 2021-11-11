import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static long n = 5000000;
    public static long array[];
    public static int sum = 0;
    public static final int N = 1;
    public static List<List<Long>> primeNumsArray = new ArrayList<>();
    public static List<List<Long>> times = new ArrayList(10);

    public static void main(String[] args) throws InterruptedException, IOException {
        array = new long[100000000];

        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

       PrimeNumbersThread[] threads = new PrimeNumbersThread[N];

       for (int i = 0; i < N; i++) {
           threads[i] = new PrimeNumbersThread((int) (i * n / N), (int) ((i + 1) * n / N));
       }

       for (int i = 0; i < N; i++) {
           threads[i].start();
       }

       for (int i = 0; i < N; i++) {
           threads[i].join();
       }

        System.out.println("Success!");
        IO.printArray(primeNumsArray);
    }



    public static void countTimes() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            times.add(new ArrayList<>(1));
        }

        array = new long[100000000];

        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

        for (int t = 1; t < 11; t++) {
            PrimeNumbersThread[] threads = new PrimeNumbersThread[t];

            for (int i = 0; i < t; i++) {
                threads[i] = new PrimeNumbersThread((int) (i * n / t), (int) ((i + 1) * n / t));
            }

            for (int i = 0; i < t; i++) {
                threads[i].start();
            }

            long start = System.nanoTime();
            for (int i = 0; i < t; i++) {
                threads[i].join();
            }
            long finish = System.nanoTime();
            long elapsed = finish - start;

            System.out.println("Прошло времени, нс: " + elapsed);
            System.out.println("Прошло времени, мс: " + elapsed / 1000000);

            List<Long> time = new ArrayList<>(1);
            time.add(elapsed);
            times.set(t - 1, time);
        }
    }
}


