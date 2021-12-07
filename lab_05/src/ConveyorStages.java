public class ConveyorStages {
    public static void firstStage() {
        while (!Main.queue1.isEmpty()) {
            String curStr;
            int curTask;
            ConveyorObject conveyorObject;

            synchronized (Main.queue1) {
                conveyorObject = Main.queue1.poll();
            }

            curStr = conveyorObject.string;
            curTask = conveyorObject.taskNum;

            long convTimeStart = System.nanoTime();
            String newStr = Encryption.reverse(curStr);
            Main.timer.addTimeFirstConv(curStr, System.nanoTime() - convTimeStart);
            Main.timer.logCurEvent(curTask, true, System.nanoTime());

            synchronized (Main.queue2) {
                Main.queue2.add(new ConveyorObject(newStr, curTask, System.nanoTime()));
            }
        }
    }

    public static void secondStage() {
        do {
            if (!Main.queue2.isEmpty()) {
                String temp;
                int curTask;
                ConveyorObject obj;

                synchronized (Main.queue2) {
                    obj = Main.queue2.poll();
                }

                temp = obj.string;
                curTask = obj.taskNum;

                long convTimeStart = System.nanoTime();
                String newStr = Encryption.fromLowerToUpper(temp);
                Main.timer.addTimeSecondConv(curTask, System.nanoTime() - convTimeStart);

                synchronized (Main.queue3) {
                    Main.queue3.add(new ConveyorObject(newStr, curTask, System.nanoTime()));
                }
            }
        } while (!Main.queue1.isEmpty() || !Main.queue2.isEmpty());
    }

    public static void thirdStage() {
        do {
            if (!Main.queue3.isEmpty()) {
                String temp;
                int curTask;
                ConveyorObject obj;

                synchronized (Main.queue3) {
                    obj = Main.queue3.poll();
                }

                temp = obj.string;
                curTask = obj.taskNum;

                long convTimeStart = System.nanoTime();
                String newStr = Encryption.caesar(temp);
                Main.timer.addTimeThirdConv(curTask, System.nanoTime() - convTimeStart);
                Main.timer.logCurEvent(curTask, false, System.nanoTime());

                synchronized (Main.res) {
                    Main.res.add(newStr);
                }
            }
        } while (!Main.queue1.isEmpty() || !Main.queue2.isEmpty() || !Main.queue3.isEmpty());
    }
}
