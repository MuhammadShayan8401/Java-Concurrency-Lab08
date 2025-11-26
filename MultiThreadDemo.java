class CalculationThread extends Thread {
    private boolean running = true;
    @Override
    public void run() {
        System.out.println("Calculation Thread Started...");

        int sum = 0;
        for (int i = 1; i <= 5 && running; i++) {
            sum += i;
            System.out.println("Calculating... sum = " + sum);

            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Calculation Thread Finished.");
    }
    public void stopThread() {
        running = false;
    }
}
class LoggingThread extends Thread {
    @Override
    public void run() {
        System.out.println("Logging Thread Started...");

        for (int i = 1; i <= 3; i++) {
            System.out.println("Logging data... Entry " + i);
            try {
                Thread.sleep(700); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Logging Thread Completed.");
    }
}
public class MultiThreadDemo {
    public static void main(String[] args) {

        CalculationThread t1 = new CalculationThread();
        LoggingThread t2 = new LoggingThread();

        t1.start();
        try {
            t1.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        t1.stopThread(); 
    }
  
}
