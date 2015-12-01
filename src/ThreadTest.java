import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Deus on 01.12.2015.
 */
public class ThreadTest {

    public static void main(String[]args) {

        executorEx();
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
        //Set the priority of a thread -> range is MIN_PRIORITY == 1 to MAX_PRIORITY == 10
        thread.setPriority(Thread.MIN_PRIORITY);
        //for real parallel work do not use setDaemon
        //thread.setDaemon(true);
        thread.start();
    }

    //TODO: USING java API: java.util.concurrent

    public static void executorEx() {
        //Executor handles all new thread creation for us
        //Use executor factory
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //start an executor
        executor.submit(() -> {
                    String name = Thread.currentThread().getName();
            System.out.println("Hello "+name);
        });
        //stop an executor
        try {
            System.out.println("attempt to shutdown executor");
            //soft termination -> we wait till current running tasks(=active threads) are finished
            executor.shutdown();
            //but we will wait only a max of 5 secs
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            //After waiting 5 secs we do a hard termination -> all threads will be stopped immediately
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }

    }
}
