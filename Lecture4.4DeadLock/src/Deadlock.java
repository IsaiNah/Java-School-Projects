import static java.lang.Thread.sleep;

public class Deadlock {

  public static void main(String[] args) {

    final Object r1 = "r1";
    final Object r2 = "r2";

    Thread t1 = new Thread(() ->
    {
      synchronized (r1) {
        System.out.println("Thread 1: locked resource 1");
        synchronized (r2) {
          System.out.println("Thread 1: locked resource 2");
          try {
            sleep((long) 50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (r2) {
        System.out.println("Thread 2: locked resource 2");
        synchronized (r1) {
          System.out.println("Thread 2: locked resource 1");
          try {
            sleep((long) 50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    t1.start();
    t2.start();
    //this program never ends - Liveness Problem: Deadlock
    //Sample adapted from http://examples.oreilly.com/jenut/Deadlock.java
  }
}