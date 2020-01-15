import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TaskManager;


/**
 * This class includes the main method and will insert tasks into the Job Storage as well as
 * get jobs from the Job Storage. The maximum number of jobs is defined here.
 * The main class will call the insertTask or insertRecurringTask from the TaskManager.
 * The main class will call the getNextJobNumber from the the Task Manager.
 */

public class TaskManagerExercise {

   static final Logger log = LoggerFactory.getLogger(TaskManagerExercise.class);


   public static void main(String [] args) {
      log.info("Start Task Manager Exercise");

      int maxJobsAllowed = 5;

      TaskManager taskManager = new TaskManager(maxJobsAllowed);

      // insert a Task into Job Storage
      taskManager.insertTask(111, 2);
      taskManager.insertRecurringTask(222, 1, 5000);
      taskManager.insertTask(333, 3);
      taskManager.insertRecurringTask(444, 3, 3000);

      int jobNumber = taskManager.getNextJobNumber();
      log.info("Completed with jobNumber: " + jobNumber);

      jobNumber = taskManager.getNextJobNumber();
      log.info("Completed with jobNumber: " + jobNumber);

      sleepNow(10000);

      jobNumber = taskManager.getNextJobNumber();
      log.info("Completed with jobNumber: " + jobNumber);
   }

   /**
    * This method is used to sleep the thread
    * Used this method when inserting the recurring job after it was handled based on the its interval.
    * When attempting to put the job back into the Job Storage, needed time to make sure that the job
    * was put in the Job Storage - its appropriate priority slot.
    * @param millis - this defines how long the thread should sleep
    */


   public static void sleepNow(long millis) {
      try {
         Thread.sleep(millis);
         log.info("Completed sleeping for " + millis + " milliseconds");
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }


}
