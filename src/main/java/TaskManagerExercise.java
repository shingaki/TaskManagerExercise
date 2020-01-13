import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TaskManager;

public class TaskManagerExercise {

   static final Logger log = LoggerFactory.getLogger(TaskManagerExercise.class);


   public static void main(String [] args) {
      log.info("Start Task Manager Exercise");

      int maxJobsAllowed = 5;

      TaskManager taskManager = new TaskManager(maxJobsAllowed);

      // insert a Task into Job Storage
      taskManager.insertTask(111, 2);
      taskManager.insertRecurringTask(222, 1, 5000);

      int jobNumber = taskManager.getNextJobNumber();
      log.info("Completed with jobNumber: " + jobNumber);

      jobNumber = taskManager.getNextJobNumber();
      log.info("Completed with jobNumber: " + jobNumber);

      try {
         Thread.sleep(10000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      jobNumber = taskManager.getNextJobNumber();
      log.info("Completed with jobNumber: " + jobNumber);
   }


}
