package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Recurring Job extends Job
 * It includes the parameger 'interval' while Job does not
 */
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
