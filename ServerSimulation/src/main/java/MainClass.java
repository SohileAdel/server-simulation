import java.util.ArrayList;

public class MainClass {
    public static QueueForTasks tasksQueue = new QueueForTasks();
    private static int numberOfComputers = 2, startCountingTheWaitingTasks;
    private static double totalTaskWaitingTime, totalTaskServiceTime, lessTimeLeft, totalInterArrivalTime;
    public static double maxServiceTime, maxInterArrivalTime;
    private static ArrayList<Computer> COMPUTER_LIST = new ArrayList<>();
    private static ArrayList<MyTask> allWaitingTasksGenerated = new ArrayList<>();
    public static ArrayList<Double> serviceTimeList = new ArrayList<>(), interArrivalTimeList = new ArrayList<>();
    public static MyTask lastTask;
    private static boolean firstTask = true;

    public static void main(String[] args) {
        setNumberOfComputers(2);
        addComputers(numberOfComputers);
        startCountingTheWaitingTasks = -numberOfComputers;
        double codeRunnigTime = 0;

        while (codeRunnigTime < 1000) {
            // Exponential distribution formula:
            // pdf: f(x) = 1/M * e^(-x/M) , for M is the average

            double taskTime = -Math.log(Math.random()) * 25;
            serviceTimeList.add(taskTime);
            determineMaxServiceTime(taskTime);

            double interArrivalTime = -Math.log(Math.random()) * 20;
            interArrivalTimeList.add(interArrivalTime);
            determineMaxInterArrivalTime(interArrivalTime);

            codeRunnigTime += interArrivalTime;

            MyTask taskToAdd = new MyTask(taskTime, interArrivalTime);

            tasksQueue.enqueue(taskToAdd);
            lastTask = taskToAdd;

            if (!firstTask) {
                for (Computer currComputer : COMPUTER_LIST) {
                    currComputer.continueTask();
                }
            }
            firstTask = false;


            for (Computer currComputer : COMPUTER_LIST) {
                attemptToSend(currComputer);
            }

            if (startCountingTheWaitingTasks >= 0) {
                addToWaitingTime(lastTask.getTimeToArrive());
            }

            startCountingTheWaitingTasks++;

            totalTaskServiceTime += lastTask.getTimeToExecute();
            totalInterArrivalTime += lastTask.getTimeToArrive();
        }

        double avgServiceTime = totalTaskServiceTime / serviceTimeList.size();
        double avgInterArrivalTime = totalInterArrivalTime / interArrivalTimeList.size();
        System.out.printf("Average task service time = %s seconds\n", avgServiceTime);
        System.out.printf("Average inter arrival time = %s seconds\n", avgInterArrivalTime);
        System.out.printf("Average waiting time for each task = %s seconds\n", getAverageTaskWaitingTime());
    }

    static void setNumberOfComputers(int newNumberOfComputers) {
        numberOfComputers = newNumberOfComputers;
    }

    static void addComputers(int numOfComputers) {
        for (int i = 0; i < numOfComputers; i++) {
            Computer computerToAdd = new Computer();
            COMPUTER_LIST.add(computerToAdd);
        }
    }

    static void attemptToSend(Computer comp) {
        if (comp.isReady()) {
            if (!tasksQueue.isEmpty()) {
                MyTask taskToSend = tasksQueue.dequeue();
                allWaitingTasksGenerated.add(taskToSend);
                comp.recieveTask(taskToSend);
            }
        }
    }

    static double getLessTimeLeft() {
        lessTimeLeft = COMPUTER_LIST.get(0).timeLeft;
        for (int i = 1; i < numberOfComputers; i++) {
            lessTimeLeft = Math.min(lessTimeLeft, COMPUTER_LIST.get(i).timeLeft);
        }
        return lessTimeLeft;
    }

    static void addToWaitingTime(double arrivalTimePassed) {
        Node temp = tasksQueue.front;
        getLessTimeLeft();
        if ((arrivalTimePassed > lessTimeLeft) && (temp != null) && (lessTimeLeft >= 0)) {
            temp.task.addToWaitTime(lessTimeLeft);
            temp = temp.next;
        }
        while ((temp != null) && (temp != tasksQueue.rear)) {
            temp.task.addToWaitTime(arrivalTimePassed);
            temp = temp.next;
        }
    }

    static double getAverageTaskWaitingTime() {
        for (int i = 0; i < allWaitingTasksGenerated.size(); i++) {
            totalTaskWaitingTime += allWaitingTasksGenerated.get(i).getWaitingTime();
        }
        return totalTaskWaitingTime / startCountingTheWaitingTasks;
    }

    static void determineMaxServiceTime(double taskTime) {
        if (firstTask) {
            maxServiceTime = Math.round(taskTime);
        }
        maxServiceTime = Math.round(Math.max(maxServiceTime, taskTime));
    }

    static void determineMaxInterArrivalTime(double interArrivalTime) {
        if (firstTask) {
            maxInterArrivalTime = Math.round(interArrivalTime);
        }
        maxInterArrivalTime = Math.round(Math.max(maxInterArrivalTime, interArrivalTime));
    }
}
