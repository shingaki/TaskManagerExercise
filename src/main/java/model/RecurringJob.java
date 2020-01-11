package model;

// recurring job is a sub-class of Job and the only difference is that
// recurring job includes an interval value
public class RecurringJob extends Job {
   private int interval;

   public RecurringJob(int jobNumber, int priority, int interval) {
      super(jobNumber, priority);
      this.interval = interval;
   }

   public int getInterval() {
      return interval;
   }

   public void setInterval(int interval) {
      this.interval = interval;
   }
}
