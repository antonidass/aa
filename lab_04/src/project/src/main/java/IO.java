import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IO {

    public static void printArray(List array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + " ");
        }
        System.out.println();
    }

    public static void writeResults() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/akrik/Desktop/aa/lab_04/report/assets/csv/time.csv"));
        stringBuilder.append("threads,time\n");

        for (int i = 1; i < 11; i++) {
            stringBuilder.append(i + "," + Main.times.get(i - 1).get(0) + "\n");
        }

        writer.write(stringBuilder.toString());

        writer.close();
    }
}
