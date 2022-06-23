public class Computer {

    public double timeLeft;
    private double timeToSubtractFromTheNextServiceTime;
    MainClass mainClass = new MainClass();

    public void recieveTask(MyTask task) {
        timeLeft = task.getTimeToExecute() - timeToSubtractFromTheNextServiceTime;
    }

    public void continueTask() {
        timeLeft -= mainClass.lastTask.getTimeToArrive();
        if (timeLeft <= 0) {
            if (mainClass.tasksQueue.size == 1) {
                timeToSubtractFromTheNextServiceTime = 0;
            } else {
                timeToSubtractFromTheNextServiceTime = -timeLeft;
            }
            timeLeft = 0;
        }
    }

    public boolean isReady() {
        return timeLeft <= 0;
    }
}
