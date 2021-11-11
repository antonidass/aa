import java.util.ArrayList;
import java.util.List;

public class Main {
    public static long n = 500;
    public static long array[];
    public static int sum = 0;
    public static final int N = 5;
    public static List<Long> primeNumsArray = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
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
}






