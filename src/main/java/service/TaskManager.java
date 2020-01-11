package service;


import model.Job;
import model.RecurringJob;

import java.util.Timer;
import java.util.TimerTask;

public class TaskManager {
   private int maxNumberOfJobs;
   private JobStorage jobStorage;

   public TaskManager(int maxNumberOfJobs) {
      this.maxNumberOfJobs = maxNumberOfJobs;
      jobStorage = new JobStorage(maxNumberOfJobs);
   }

   public boolean insertTask(int jobNumber, int priority) {

      return jobStorage.addJob(new Job(jobNumber, priority));
   }

   public boolean insertRecurringTask(int jobNumber, int priority, int interval) {
      return jobStorage.addJob(new RecurringJob(jobNumber, priority, interval));
   }

   public int getNextJob() {
      Job job = jobStorage.getNextJob();
      if(job == null)
         return -1;
      else {
         if (job instanceof RecurringJob) {
            createTimer((RecurringJob) job);
         }

         return job.getJobNumber();
      }
   }

   public void createTimer(RecurringJob recurringJob) {
      Timer timer = new Timer();
      timer.schedule(new JobTimer(timer, this, recurringJob), recurringJob.getInterval());
   }

   class JobTimer extends TimerTask {
      private Timer timer;
      private TaskManager taskManager;
      private RecurringJob recurringJob;


      public JobTimer(Timer timer, TaskManager taskManager, RecurringJob recurringJob) {
         this.timer = timer;
         this.taskManager = taskManager;
         this.recurringJob = recurringJob;


      }

      public void run() {
         System.out.format("Timer Task Finished..!%n");
         taskManager.insertRecurringTask(recurringJob.getJobNumber(), recurringJob.getPriority(), recurringJob.getInterval());
         timer.cancel(); // Terminate the timer thread

      }
   }


}
