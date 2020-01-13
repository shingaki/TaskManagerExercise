package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// recurring job is a sub-class of Job and the only difference is that
// recurring job includes an interval value
public class RecurringJob extends Job {

   static final Logger log = LoggerFactory.getLogger(RecurringJob.class);

   private long interval;

   public RecurringJob(int jobNumber, int priority, long interval) {
      super(jobNumber, priority);
      this.interval = interval;
   }

   public Long getInterval() {
      return interval;
   }

   public void setInterval(Long interval) {
      this.interval = interval;
   }
}
