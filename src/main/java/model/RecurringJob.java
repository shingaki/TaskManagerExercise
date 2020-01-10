package model;

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
