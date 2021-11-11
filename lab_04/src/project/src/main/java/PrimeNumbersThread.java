import java.util.ArrayList;
import java.util.List;

public class PrimeNumbersThread extends Thread {
    private int start;
    private int finish;

    PrimeNumbersThread(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    @Override
    public void run() {
        List<Long> array = new ArrayList<>();
        for (int i = start; i < finish; i++) {
            if (Prime.isPrime(Main.array[i]) == 1) {
                array.add(Main.array[i]);
            }
        }
    }
}