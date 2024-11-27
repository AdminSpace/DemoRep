package DemoThread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FJMImpl extends ThreadPoolExecutor {
    
    private static ThreadPoolExecutor threadPoolExecutor = null;
    private static final LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
    

    public FJMImpl(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, LinkedBlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public static void main(String[] args) {
        System.out.println("Start Of Manager Process.");
        try {
            
                
               
                System.out.println("Instantiating ThreadPoolExecutor.");
                threadPoolExecutor = new FJMImpl(5, 8, 60, TimeUnit.SECONDS, linkedBlockingQueue);
                System.out.println("PreStarting all CoreThreads.");
                threadPoolExecutor.prestartAllCoreThreads();
               // for (int i = 0; i < 5; i++) {
                    linkedBlockingQueue.offer(new FJMProcess());
               // }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End of Manager Process.");
    }

    @Override
    protected void beforeExecute(Thread thread, Runnable runnable) {
        System.out.println("Enter.");
        try {
            super.beforeExecute(thread, runnable);
            System.out.println("Offering Runnable to BlockingQueue Through BeforeExecute."+thread.getName());
            linkedBlockingQueue.offer(new FJMProcess());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Exit.");
    }
}