public class MyTask {
    private double timeToExecute, timeToArrive, waitingTime;

    public MyTask(double timeToExecute, double timeToArrive) {
        this.timeToExecute = timeToExecute;
        this.timeToArrive = timeToArrive;
    }

    public double getTimeToExecute() {
        return timeToExecute;
    }

    public double getTimeToArrive() {
        return timeToArrive;
    }

    public void addToWaitTime(double waitingTime) {
        this.waitingTime += waitingTime;
    }

    public double getWaitingTime() {
        return waitingTime;
    }
}
