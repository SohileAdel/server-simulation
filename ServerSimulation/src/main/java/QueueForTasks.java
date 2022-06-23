public class QueueForTasks { // Queue implemented with Linked List


    public Node front, rear;
    public int size;

    public void enqueue(MyTask task){

        Node temp = new Node(task);

        if (rear == null){
            front = rear = temp;
            size++;
            return;
        }

        rear.next = temp;
        rear = temp;
        size++;
    }

    public MyTask dequeue(){
        if (front == null) {
            return null; // Underflow!
        }

        MyTask temp = front.task;
        front = front.next;

        if (front == null){
            rear = null;
        }
        size--;
        return temp;
    }

    public boolean isEmpty(){
        return size==0;
    }
}

class Node {
    MyTask task;
    Node next;

    public Node(MyTask task){
        this.task = task;
    }
}



