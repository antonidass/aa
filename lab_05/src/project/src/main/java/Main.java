import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    static int n;
    public static Queue<ConveyorObject> queue1;
    public static Queue<ConveyorObject> queue2;
    public static Queue<ConveyorObject> queue3;
    public static ArrayList<String> words = new ArrayList<>();
    public static ArrayList<String> res = new ArrayList<>();
    public static long startTime, endTime;
    public static Timer timer;

    public static void init() {
        for (int i = 0; i < n; i++) {
            synchronized (queue1) {
                queue1.add(new ConveyorObject(words.get(i), i, System.nanoTime()));
            }
        }
    }

    public static void runLinear() throws InterruptedException {
        queue1 = new ArrayBlockingQueue<>(n);
        Thread threadInit = new Thread(Main::init);
        threadInit.start();
        threadInit.join();

        long startLinear = System.nanoTime();

        for (int i = 0; i < n; i++) {
            String temp = queue1.poll().string;
            long timeLinearTask = System.nanoTime();
            temp = Encryption.reverse(temp);
            timer.addFirstLinear(i, System.nanoTime() - timeLinearTask);

            temp = Encryption.fromLowerToUpper(temp);
            timer.addSecondLinear(i, System.nanoTime() - timeLinearTask);

            temp = Encryption.caesar(temp);
            timer.addThirdLinear(i, System.nanoTime() - timeLinearTask);

            timer.addLinearTaskTime(i, System.nanoTime() - timeLinearTask);
        }

        long endLinear = System.nanoTime();
        timer.addLinearTime(endLinear - startLinear);
    }


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        System.out.print("Input amount of words: ");
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        queue1 = new ArrayBlockingQueue<>(n);
        queue2 = new ArrayBlockingQueue<>(n);
        queue3 = new ArrayBlockingQueue<>(n);

        for (int i = 0; i < n; i++) {
            String s = Utils.generate();
            words.add(s);
        }

        timer = new Timer();
        timer.setSize(n);

        Thread threadInit = new Thread(Main::init);
        threadInit.start();
        threadInit.join();

        startTime = System.nanoTime();

        Thread thread1 = new Thread(ConveyorStаges::firstStage);
        Thread thread2 = new Thread(ConveyorStаges::secondStage);
        Thread thread3 = new Thread(ConveyorStаges::thirdStage);

        thread3.start();
        thread2.start();
        thread1.start();

        thread1.join();
        thread2.join();
        thread3.join();

        endTime = System.nanoTime();
        timer.addConvTime(endTime - startTime);

        runLinear();

        timer.printLog();
    }
}