/**
 * Created by Deus on 01.12.2015.
 */
public class ThreadTest {

    public static void main(String[]args) {

        exeByNewThread();
        exeByMainThread();
    }

    //implement Runnable interface -> this will define the task you pass to a new thread for execution

    public static class MyTask implements Runnable {

        String str = "Inside MyTask: ";
        private int i=0;

        @Override
        public void run() {
            String threadName = ""+Thread.currentThread().getName();
            System.out.println(str+threadName+"\nBefore starting heavy work..");

            while(i<10) {
                //do the heavy work here
                try {
                    Thread.currentThread().sleep(1000);
                    System.out.println(i++ + "\t" +threadName);
                } catch (InterruptedException e) {
                    System.err.println("InterruptedException occurred. -> see inside MyTask -> run()");
                }
            }

            System.out.println("All work has been done.");
        }
    }

    //This way you will block the main thread from being able to deal with other tasks
    public static void exeByMainThread() {
        Runnable myTask = new MyTask();
        myTask.run();
    }

    public  static void exeByNewThread() {
        Runnable myTask = new MyTask();
        Thread thread = new Thread(myTask);
        //thread.setDaemon(true);
        thread.start();
    }
}
