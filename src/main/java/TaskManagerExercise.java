import service.TaskManager;

public class TaskManagerExercise {

   public static void main(String [] args) {
      System.out.println("Start Task Manager Exercise");
      int maxJobsAllowed = 5;
      TaskManager taskManager = new TaskManager(maxJobsAllowed);
      taskManager.insertTask(111, 2);
      taskManager.insertRecurringTask(222, 1, 5000);
      int jobNumber = taskManager.getNextJob();
      System.out.println("Completed with jobNumber: " + jobNumber);
      jobNumber = taskManager.getNextJob();
      System.out.println("Completed with jobNumber: " + jobNumber);
      try {
         Thread.sleep(10000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      jobNumber = taskManager.getNextJob();
      System.out.println("Completed with jobNumber: " + jobNumber);
   }

}
