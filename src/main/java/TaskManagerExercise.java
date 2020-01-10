import service.TaskManager;

public class TaskManagerExercise {

   public static void main(String [] args) {
      System.out.println("Start");
      TaskManager taskManager = new TaskManager(5);
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
