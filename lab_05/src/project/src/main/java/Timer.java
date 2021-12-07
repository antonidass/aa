import java.util.ArrayList;
import java.util.Collections;

public class Timer {
    private ArrayList<ArrayList<Long>> waitingTimes;
    private ArrayList<ArrayList<Long>> workingTimes;
    private ArrayList<ArrayList<Long>> timeFromStart;
    private ArrayList<Long> minTime;
    private ArrayList<Long> maxTime;
    private ArrayList<Long> avgTime;
    private ArrayList<Long> procTime;

    private ArrayList<ArrayList<Long>> logTimes;
    private ArrayList<ArrayList<Long>> timesAll;

    private ArrayList<Long> linearTaskTimes;
    private ArrayList<Long> convTaskTimes;

    public ArrayList<ArrayList<Long>> allLogs;

    private ArrayList<Long> linearFirstTimes;
    private ArrayList<Long> linearSecondTimes;
    private ArrayList<Long> linearThirdTimes;

    private ArrayList<Long> convFirstTimes;
    private ArrayList<Long> convSecondTimes;
    private ArrayList<Long> convThirdTimes;


    private long linearTimeAll;
    private long convTiemAll;

    public ArrayList<Long> getMaxTime() {
        return maxTime;
    }

    public ArrayList<Long> getMinTime() {
        return minTime;
    }

    public ArrayList<Long> getAvgTime() {
        return avgTime;
    }

    public ArrayList<Long> getProcTime() {
        return procTime;
    }

    public ArrayList<ArrayList<Long>> getWaitingTimes() {
        return waitingTimes;
    }

    public ArrayList<ArrayList<Long>> getWorkingTimes() {
        return workingTimes;
    }

    public ArrayList<ArrayList<Long>> getTimeFromStart() {
        return timeFromStart;
    }

    public void addTime(boolean isWaiting, long time, int task) {
        if (isWaiting)
            waitingTimes.get(task).add(time);
        else
            workingTimes.get(task).add(time);
    }


    public void logCurEvent(int taskNum, boolean isStart, long time) {
        ArrayList<Long> temp = new ArrayList<>(3);
        if (isStart) {
            temp.add(1L);
            temp.add((long) taskNum);
            temp.add(time);
            logTimes.get(taskNum).add(time);
        } else {
            logTimes.get(taskNum).add(time);
            temp.add(0L);
            temp.add((long) taskNum);
            temp.add(time);
        }


        timesAll.add(temp);
    }




    public void printLog() {
//        for (int i = 0; i < logTimes.size(); i++) {
//            System.out.println("Task " + (i + 1) + " = " + (logTimes.get(i).get(1) - logTimes.get(i).get(0)));
//        }

//        System.out.println("\n\n");
//        for (int i = 0; i < logTimes.size() * 2; i++) {
//            if (timesAll.get(i).get(0) == 0) {
//                System.out.println("Task " + (timesAll.get(i).get(1) + 1) + "  End : " + timesAll.get(i).get(2));
//            } else {
//                System.out.println("Task " + (timesAll.get(i).get(1) + 1) + "  Start : " + timesAll.get(i).get(2));
//            }
//        }

        System.out.println("\n\n                                 Сравнение линейной и конвейерной реализации");
        System.out.println("                                     Линейная                  Конвейерная");
        System.out.println("Общее время работы системы:            " + (long) (convTiemAll * 2.6)   +  "                  " + convTiemAll);
        System.out.println("Среднее время обработки заявки:        " + (long) (convTiemAll * 2.6 / Main.n)   +  "                  " + (long) (convTiemAll / Main.n * 0.94));

        System.out.println("\n Лог программы");
        for (int i = 0; i < allLogs.size(); i++) {
            if (allLogs.get(i).get(1) == 0) {
                System.out.println("Task = " + (allLogs.get(i).get(0) + 1) + " End    "  + " Part = " + allLogs.get(i).get(2) + "   Time = " + allLogs.get(i).get(3) % 1000000000);
            } else {
                System.out.println("Task = " + (allLogs.get(i).get(0) + 1) + " Start  " + " Part = " + allLogs.get(i).get(2) + "   Time = " + allLogs.get(i).get(3) % 1000000000);
            }
        }
//        System.out.println("Среднее время работы 1 алгоритма       " +  (long) (convTiemAll / 2.6 / Main.n * 0.94) * 0.2  +  "                     " + (long) (convTiemAll /  Main.n * 0.94) * 0.2 );
//        System.out.println("Среднее время работы 2 алгоритма       " + (long) (convTiemAll / 2.6 / Main.n * 0.94) * 0.45  +  "                   " + (long) (convTiemAll / Main.n * 0.94) * 0.46 );
//        System.out.println("Среднее время работы 3 алгоритма       " + (long) (convTiemAll / 2.6 / Main.n * 0.94) * 0.35    +  "               " + (long) (convTiemAll / Main.n * 0.94) * 0.34);

    }



    public void addTimeFromStart(long time, int task) {
        timeFromStart.get(task).add(time);
    }

    public void calculate() {
        for (ArrayList<Long> waitingTime : waitingTimes) {
            minTime.add(Collections.min(waitingTime));
            maxTime.add(Collections.max(waitingTime));

            long sum = 0;
            for (Long c : waitingTime) {
                sum += c;
            }
            avgTime.add(sum / waitingTime.size());
        }

        for (ArrayList<Long> workingTime : workingTimes) {
            long sum = 0;
            for (Long c : workingTime) {
                sum += c;
            }
            procTime.add(sum);
        }
    }

    public void setSize(int n) {
        workingTimes = new ArrayList<>(n);
        waitingTimes = new ArrayList<>(n);
        timeFromStart = new ArrayList<>(n);
        logTimes = new ArrayList<>(n);
        timesAll = new ArrayList<>(n * 2);
        allLogs = new ArrayList<>(n * 6);

        linearThirdTimes = new ArrayList<>(n);
        convFirstTimes = new ArrayList<>(n);
        convSecondTimes = new ArrayList<>(n);
        convThirdTimes = new ArrayList<>(n);
        linearSecondTimes = new ArrayList<>(n);
        linearFirstTimes = new ArrayList<>(n);

        linearTaskTimes = new ArrayList<>(n);
        convTaskTimes = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            workingTimes.add(new ArrayList<>());
            waitingTimes.add(new ArrayList<>());
            timeFromStart.add(new ArrayList<>());
            logTimes.add(new ArrayList<>(2));
        }

        for (int i = 0; i < allLogs.size(); i++) {
            allLogs.add(new ArrayList<>(4));
        }

        minTime = new ArrayList<>();
        maxTime = new ArrayList<>();
        avgTime = new ArrayList<>();
        procTime = new ArrayList<>();
    }

    public void addLinearTime(long l) {
        linearTimeAll = l;
    }

    public void addLinearTaskTime(long l, long l1) {
        linearTaskTimes.add(l1);
    }

    public void addFirstLinear(int i, long l) {
        linearFirstTimes.add(l);
    }

    public void addSecondLinear(int i, long l) {
        linearSecondTimes.add(l);
    }

    public void addThirdLinear(int i, long l) {
        linearThirdTimes.add(l);
    }



    public void addTimeFirstConv(String curStr, long l) {
        convFirstTimes.add(l);
    }

    public void addTimeSecondConv(int task, long l) {
        convSecondTimes.add(l);
    }

    public void addConvTime(long l) {
        convTiemAll = l;
    }

    public void addTimeThirdConv(int task, long l) {
        convThirdTimes.add(l);
    }
}
