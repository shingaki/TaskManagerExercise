package service;


import model.Job;
import model.RecurringJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class provides a way to
 * (1) insert the task (both non-recurring and recurring tasks) into the Job Storage
 * (2) get the next Job from the Job Storage
 * (3) when the next Job is a recurring job - a task is scheduled to start for the specified interval
 *     once the interval expires, the job task begins - it will cancel the timer, and insert the
 *     job back into the Job Storage (if there is room)
 */
public class TaskManager {

   static final Logger log = LoggerFactory.getLogger(TaskManager.class);

   private int maxNumberOfJobs;
   private JobStorage jobStorage;

   public TaskManager(int maxNumberOfJobs) {
      this.maxNumberOfJobs = maxNumberOfJobs;
      jobStorage = new JobStorage(maxNumberOfJobs);
      log.info("Created Task Manager with capacity of " + maxNumberOfJobs + " jobs");
   }

   /**
    * This method will insert the non-recurring task into the Job Storage
    * @param jobNumber - is the unique number for the job
    * @param priority - is the priority for the job - 1 = high, 2 = normal, 3 = low
    * @return = returns true if the job was added successfully, false if the job was not added
    */
   public boolean insertTask(int jobNumber, int priority) {
      return jobStorage.addJob(new Job(jobNumber, priority));
   }

   /**
    * This method will insert the non-recurring task into the Job Storage
    * @param jobNumber - is the unique number for the job
    * @param priority - is the priority for the job - 1 = high, 2 = normal, 3 = low
    * @param interval  - is the time the Task Manager should wait before it tries to insert the job again (milliseconds)
    * @return = returns true if the job was added successfully, false if the job was not added
    */
   public boolean insertRecurringTask(int jobNumber, int priority, long interval) {
      return jobStorage.addJob(new RecurringJob(jobNumber, priority, interval));
   }

   /**
    * This method will get the new job from the Job Storage
    * If there is no more jobs, this will return null
    * If there is a job, it checks to see if it is a recurring job or not.
    * If it is a recurring job, call to create a timer
    * It is will ultimately return the job if there are jobs
    */
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

   /**
    * This method is used to get the job number only.
    * @return job number
    */
   public int getNextJobNumber() {
      Job job = getNextJob();
      if(job == null)
         // no more jobs are in storage
         return -1;
      else {
         return job.getJobNumber();
      }
   }

   /**
    * Create the objects needed to use the Timer and Job Timer
    * Schedule the task to run when the interval (milliseconds) expires
    * @param recurringJob
    */
   public void createTimer(RecurringJob recurringJob) {
      Timer timer = new Timer();
      JobTimer jobtimer = new JobTimer(timer, this, recurringJob);
      long numberOfMilliseconds = recurringJob.getInterval();
      timer.schedule(jobtimer, numberOfMilliseconds);
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

      /**
       * When the task runs below it will try to insert the recurring task into the Job Storage
       * Cancels the timer
       */
      public void run() {
         boolean insertResult = taskManager.insertRecurringTask(recurringJob.getJobNumber(), recurringJob.getPriority(), recurringJob.getInterval());
         timer.cancel();
         log.info("Timer Task Finished..! Task was added =  " + insertResult);
      }
   }


}
