class Buffer {
    private int data;
    private boolean hasData = false;
    public synchronized void produce(int value) throws InterruptedException {
        while (hasData) {
            wait(); 
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); 
    }
    public synchronized int consume() throws InterruptedException {
        while (!hasData) {
            wait(); 
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify(); 
        return data;
    }
}
class Producer extends Thread {
    private Buffer buffer;
    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(400);
            } catch (Exception e) {}
        }
    }
}
class Consumer extends Thread {
    private Buffer buffer;
    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                buffer.consume();
                Thread.sleep(600);
            } catch (Exception e) {}
        }
    }
}
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        producer.start();
        consumer.start();
    }
}
