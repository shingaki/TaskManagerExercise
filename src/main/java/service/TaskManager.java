package service;


import model.Job;
import model.RecurringJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class TaskManager {

   static final Logger log = LoggerFactory.getLogger(TaskManager.class);

   public int maxNumberOfJobs;
   private JobStorage jobStorage;

   public TaskManager(int maxNumberOfJobs) {
      this.maxNumberOfJobs = maxNumberOfJobs;
      jobStorage = new JobStorage(maxNumberOfJobs);
   }

   public boolean insertTask(int jobNumber, int priority) {

      return jobStorage.addJob(new Job(jobNumber, priority));
   }

   public boolean insertRecurringTask(int jobNumber, int priority, long interval) {
      return jobStorage.addJob(new RecurringJob(jobNumber, priority, interval));
   }

   public Job getNextJob() {
      Job job = jobStorage.getNextJob();
      if(job == null)
         return null;
      else {
         if (job instanceof RecurringJob) {
            createTimer((RecurringJob) job);
         }
         return job;
      }
   }

   public int getNextJobNumber() {
      Job job = getNextJob();
      if(job == null)
         // no more jobs are in storage
         return -1;
      else {
         return job.getJobNumber();
      }
   }

   // create the timer and let it run for the interval time
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
         taskManager.insertRecurringTask(recurringJob.getJobNumber(), recurringJob.getPriority(), recurringJob.getInterval());
         timer.cancel(); // Terminate the timer thread
         log.info("Timer Task Finished..!");
      }
   }


}
