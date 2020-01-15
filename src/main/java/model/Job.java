package model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Job implements because the Job (object) is being sorted based on priority
 */

public class Job implements Comparable {

   static final Logger log = LoggerFactory.getLogger(Job.class);

   private int jobNumber;
   private int priority; // lower number means higher priority (1 = High, 2 = Normal, 3 = Low)
   private long addedDate;

   public Job(int jobNumber, int priority) {
      this.jobNumber = jobNumber;
      this.priority = priority;
      this.addedDate = System.currentTimeMillis();
   }

   public int getJobNumber() {
      return jobNumber;
   }

   public void setJobNumber(int jobNumber) {
      this.jobNumber = jobNumber;
   }

   public int getPriority() {
      return priority;
   }

   public void setPriority(int priority) {
      this.priority = priority;
   }

   public long getAddedDate() {
      return addedDate;
   }

   public void setAddedDate(long addedDate) {
      this.addedDate = addedDate;
   }

   @Override
   public String toString() {
      return "Job{" +
         "jobNumber=" + jobNumber +
         ", priority=" + priority +
         ", addedDate=" + addedDate +
         '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Job job = (Job) o;

      return jobNumber == job.jobNumber;
   }

   @Override
   public int hashCode()  {
      return jobNumber;
   }


   /**
    * This is needed to be used for the SortedSet
    * It will compare the new job's priority with the priority of the job in the Job Storage
    * when the new job's priority is < the existing job - return 1
    * when the new job's priority is > the existing job - return -1
    * when the new job's priority is = the existing job - then check the added date
    * when the new job's added date < the existing job's added date - return 1
    * when the new job's added date > the existing job's added date - return -1
    * finally return 0 the dates are equal
    * @param o
    * @return
    */

   @Override
   public int compareTo(Object o) {
      Job jobToCompare = (Job)o;
      if(this.priority < jobToCompare.priority)
         return 1;
      if(this.priority > jobToCompare.priority)
         return -1;
      else { // In here  this.priority == jobToCompare.priority
         if(this.addedDate < jobToCompare.addedDate)
            return 1;
         else if(this.addedDate > jobToCompare.addedDate)
            return -1;
         else
            return 0;
      }
   }
}

