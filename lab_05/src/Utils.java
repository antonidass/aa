import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Utils {
    public static String generate() {
        Random random = new Random();
        int length = random.nextInt(100000) + 1;
        char[] text = new char[length];
        for (int i = 0; i < length; i++)  {
            text[i] = (char)(random.nextInt(25)+97);
        }
        return new String(text);
    }


    public static void makeLog(long time) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("log.txt");
        Main.timer.calculate();

        for (int i = 0; i < Main.n; i++) {
            writer.println("Задача № " + (i + 1));
            writer.println("Время ожидания в первой очереди: " + Main.timer.getWaitingTimes().get(i).get(0));

            writer.println("Время начала обработки в первом контейнере(с начала работы программы): " + Main.timer.getTimeFromStart().get(i).get(0));
            writer.println("Время обработки в первом конвейре: " + Main.timer.getWorkingTimes().get(i).get(0));

            writer.println("Время постановки во вторую очередь(с начала работы программы): " + Main.timer.getTimeFromStart().get(i).get(1));
            writer.println("Время ожидания во второй очереди: " + (Main.timer.getWaitingTimes().get(i).get(1)));

            writer.println("Время начала обработки во втором контейнере(с начала работы программы): " + Main.timer.getTimeFromStart().get(i).get(2));
            writer.println("Время обработки во втором конвейре: " + Main.timer.getWorkingTimes().get(i).get(1));

            writer.println("Время постановки в третью очередь(с начала работы программы): " + Main.timer.getTimeFromStart().get(i).get(3));
            writer.println("Время ожидания в третьей очереди: " + Main.timer.getWaitingTimes().get(i).get(2));

            writer.println("Время начала обработки в третьем контейнере(с начала работы программы): " + Main.timer.getTimeFromStart().get(i).get(4));
            writer.println("Время обработки в третьем конвейре: " + Main.timer.getWorkingTimes().get(i).get(2));

            writer.println("Время окончания обработки(с начала работы программы): " + Main.timer.getTimeFromStart().get(i).get(5));

            writer.println("Минимальное время ожидания в очереди: " + Main.timer.getMinTime().get(i));
            writer.println("Максимальное время ожидания в очереди: " + Main.timer.getMaxTime().get(i));
            writer.println("Среднее время ожидания в очереди: " + Main.timer.getAvgTime().get(i));
            writer.println("Время выполнения задачи: " + Main.timer.getProcTime().get(i));
            writer.println("-------------------------------------------------------------------------------------------");
        }
        writer.println("Время работы системы: " + time);
        writer.close();
    }


}
