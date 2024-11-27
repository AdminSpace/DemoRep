package DemoThread;


public class FJMProcess implements Runnable {
    

    public FJMProcess() {
      
    }

    @Override
    public void run() {
        System.out.println("Enter into FJMProcess run .");
        try {
        	synchronized (FirstJavaManagerProcess.class) {
        		System.out.println("Processing task for manager: " );	
        		
        	}
            // Simulate task processing
            
            Thread.sleep(1000); // Simulate processing time
            System.out.println("Task processed for manager: " );
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Exit.");
    }
}